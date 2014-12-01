package controllers

import org.jsoup.{Connection, Jsoup}
import play.Logger
import play.api.mvc._
import play.api.libs.json._

import com.infomofo.oroku.models
import com.infomofo.oroku.models.json._
import org.joda.time.DateTime

import scala.collection.JavaConverters._
import scala.util.Try

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

  lazy val openGraphMetadata = {
    val ogMetaTags = metaTags.select("meta[property^=og:]")

    ogMetaTags.iterator().asScala.foreach {
      element =>
        element.attr("property") match {
          case "og:title" | "og:type" | "og:image" | "og:url" | "og:site_name" | "og:description" =>

          case x =>
            Logger.warn(s"Unhandled OpenGraph meta tag with property name $x and value ${element.attr("content")}")
        }
    }
    val ogTitle = ogMetaTags.select("meta[property=og:title]").attr("content")
    val ogType = ogMetaTags.select("meta[property=og:type]").attr("content")
    val ogImage = ogMetaTags.select("meta[property=og:image]").attr("content")
    val ogUrl = ogMetaTags.select("meta[property=og:url]").attr("content")
    val ogSiteName = Try {
      Some(ogMetaTags.select("meta[property=og:site_name]").attr("content"))
    }.getOrElse(None)
    val ogDescription = Try {
      Some(ogMetaTags.select("meta[property=og:description]").attr("content"))
    }.getOrElse(None)

    val fbMetaTags = metaTags.select("meta[property^=fb:]")
    fbMetaTags.iterator().asScala.foreach {
      element =>
        element.attr("property") match {
          case "fb:app_id" =>

          case x =>
            Logger.warn(s"Unhandled Facebook meta tag with property name $x and value ${element.attr("content")}")
        }
    }
    
    val fbAppId = Try {
      Some(fbMetaTags.select("meta[property=fb:app_id").attr("content"))
    }.getOrElse(None)

    models.OpenGraphMetadata(
      title = ogTitle,
      openGraphType = {
        val openGraphType = models.OpenGraphType(ogType)
        openGraphType match {
          case models.OpenGraphType.UNDEFINED(unknownType) =>
            Logger.warn(s"Page found with an unknown open graph type $unknownType")
          case _ =>
        }
        openGraphType
      },
      image = models.OpenGraphMedia(
        url = ogImage
      ),
      url = ogUrl,
      siteName = ogSiteName,
      description = ogDescription,
      appId = fbAppId
    )
  }

  lazy val pageInfo = {
//    Logger.debug(s"meta tags: $metaTags")
    val unhandledMetaTags = metaTags.iterator.asScala.flatMap {
      element =>
        element.attr("property") match {
          case PageShredder.openGraphPropertyPattern(property) =>
            None
          case PageShredder.facebookPropertyPattern(property) =>
            None
          case x =>
//            Logger.warn(s"Unhandled OpenGraph meta tag $element")
            Some(element)
        }
    }.toList

    if (unhandledMetaTags.nonEmpty) {
      Logger.warn(s" Unhandled meta tags ${unhandledMetaTags.mkString(", ")}")
    }

    models.PageInfo(
      titles = Seq(openGraphMetadata.title),
      urls = Seq(openGraphMetadata.url, url).distinct,
      keywords = Nil,
      locations = Nil,
      retrievedAt = new DateTime(),
      site = models.Site(
        name = openGraphMetadata.siteName.getOrElse(url),
        siteType = Some(openGraphMetadata.openGraphType.toString)
      ),
      openGraphMetadata = Some(openGraphMetadata)
    )
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