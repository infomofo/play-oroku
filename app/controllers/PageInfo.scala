package controllers

import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.jsoup.{Connection, Jsoup}
import play.Logger
import play.api.mvc._
import play.api.libs.json._

import com.infomofo.oroku.models
import com.infomofo.oroku.models.json._
import org.joda.time.DateTime

import scala.collection.JavaConverters._
import scala.collection.mutable

private object PageShredder {
  val openGraphPropertyPattern = "og:(.*)".r
  val facebookPropertyPattern = "fb:(.*)".r
}

private class PageShredder(url: String) {
  private val document = {
    val res: Connection.Response = {
      val response = Jsoup.connect(url)
        .ignoreHttpErrors(true)
        .followRedirects(true)
        .ignoreContentType(true)
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
        .referrer("http://www.google.com")
        .execute()
      response match {
        case x if x.statusCode == 307 =>
          val newUrl = x.header("Location")
          if (newUrl != null && newUrl.length > 7) {
            Jsoup.connect(newUrl).execute()
          } else {
            throw new IllegalStateException(s"Invalid url specified by 307 redirect: $newUrl")
          }
        case x =>
          x
      }
    }
    res.parse
  }

  private lazy val headElement = document.head
  private lazy val metaTags = headElement.select("meta")

  private val usedMetaTags = new mutable.HashSet[Element]()

  private def getMetaString(tagName: String, metaTags: Elements = metaTags): Option[models.MetaString] = {
    val matchingTag = metaTags.select(s"meta[property=$tagName]").iterator().asScala.toList.headOption
    matchingTag map {
      element =>
        usedMetaTags += element
        models.MetaString(value = element.attr("content"), tag = element.toString)
    }
  }

  private def getMetaType(tagName: String, metaTags: Elements = metaTags): Option[models.MetaOpenGraphType] = {
    val matchingTag = metaTags.select(s"meta[property=$tagName]").iterator().asScala.toList.headOption
    matchingTag map {
      element =>
        usedMetaTags += element
        val matchedOgType = models.OpenGraphType(element.attr("content"))
        matchedOgType match {
          case models.OpenGraphType.UNDEFINED(unknownType) =>
            Logger.warn(s"Page found with an unknown open graph type $unknownType")
          case _ =>
        }
        models.MetaOpenGraphType(value = matchedOgType, tag = element.toString)
    }
  }

  lazy val openGraphMetadata = {
    val ogTitle = getMetaString("og:title")
    val ogType = getMetaType("og:type")
    val ogImage = getMetaString("og:image")
    val ogUrl = getMetaString("og:url")
    val ogSiteName = getMetaString("og:site_name")
    val ogDescription = getMetaString("og:description")

    val fbAppId = getMetaString("fb:app_id")

    models.OpenGraphMetadata(
      title = ogTitle.get,
      openGraphType = ogType.get,
      image = models.OpenGraphMedia(
        url = ogImage.get
      ),
      url = ogUrl.get,
      siteName = ogSiteName,
      description = ogDescription,
      appId = fbAppId
    )
  }

  lazy val pageInfo = {
    val returnValue = models.PageInfo(
      titles = Seq(openGraphMetadata.title.value),
      urls = Seq(openGraphMetadata.url.value, url).distinct,
      keywords = Nil,
      locations = Nil,
      retrievedAt = new DateTime(),
      site = models.Site(
        name = openGraphMetadata.siteName.map(_.value).getOrElse(url),
        siteType = Some(openGraphMetadata.openGraphType.value.toString)
      ),
      openGraphMetadata = Some(openGraphMetadata)
    )

    val unhandledMetaTags = metaTags.iterator.asScala.filterNot(usedMetaTags.contains)
    if (unhandledMetaTags.nonEmpty) {
      Logger.warn(s" Unhandled meta tags ${unhandledMetaTags.mkString(", ")}")
    }
    returnValue
  }
}

object PageInfo extends Controller {
  def getInfoForUrl(url: String) = Action {

    val pageInfo = new PageShredder(url).pageInfo

    Ok(Json.toJson(pageInfo))
  }

  def getOpenGraphMetadataForUrl(url: String) = Action {
    val openGraphMetadata = new PageShredder(url).openGraphMetadata
    Ok(Json.toJson(openGraphMetadata))
  }

  def getAppleItunesMetadataForUrl(url: String) = play.mvc.Results.TODO

  def getSearchMetadataForUrl(url: String) = play.mvc.Results.TODO

  def getTwitterCardMetadataForUrl(url: String) = play.mvc.Results.TODO

  def getSchemaOrgItemsForUrl(url: String) = play.mvc.Results.TODO
}