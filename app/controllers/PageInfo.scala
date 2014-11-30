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

  lazy val openGraphMetadata = {
    val metaTags = headElement.select("meta[property^=og:]")

    metaTags.iterator().asScala.foreach {
      element =>
        element.attr("property") match {
          case "og:title" | "og:type" | "og:image" | "og:url" | "og:site_name" | "og:description" =>

          case x =>
            Logger.warn(s"Unhandled OpenGraph meta tag with property name $x and value ${element.attr("content")}")
        }
    }
    val ogTitle = metaTags.select("meta[property=og:title]").attr("content")
    val ogType = metaTags.select("meta[property=og:type]").attr("content")
    val ogImage = metaTags.select("meta[property=og:image]").attr("content")
    val ogUrl = metaTags.select("meta[property=og:url]").attr("content")
    val ogSiteName = Try {
      Some(metaTags.select("meta[property=og:site_name]").attr("content"))
    }.getOrElse(None)
    val ogDescription = Try {
      Some(metaTags.select("meta[property=og:description]").attr("content"))
    }.getOrElse(None)

    models.OpenGraphMetadata(
      title = ogTitle,
      openGraphType = {
        val openGraphType = models.OpenGraphType(ogType)
        openGraphType match {
          case models.OpenGraphType.UNDEFINED(unknownType) =>
            Logger.warn(s"Page found with an unknown open graph type $unknownType")
        }
        openGraphType
      },
      image = models.OpenGraphMedia(
        url = ogImage
      ),
      url = ogUrl,
      siteName = ogSiteName,
      description = ogDescription
    )
  }

  lazy val pageInfo = {
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
}