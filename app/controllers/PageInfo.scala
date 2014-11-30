package controllers

import org.jsoup.{Connection, Jsoup}
import org.jsoup.nodes.Element
import play.Logger
import play.api.mvc._
import play.api.libs.json._

import com.infomofo.oroku.models
import com.infomofo.oroku.models.json._
import org.joda.time.DateTime

object PageInfo extends Controller {
  def getInfoForUrl(url: String) = Action {

    val document = Jsoup.connect(url).get()
    val headElement = document.head()

    val ogTitle = headElement.select("meta[property=og:title]").attr("content")
    val ogType = headElement.select("meta[property=og:title]").attr("content")

    val pageInfo = models.PageInfo(
      titles = Seq(ogTitle),
      urls = Seq(url),
      keywords = Nil,
      locations = Nil,
      retrievedAt = new DateTime(),
      site = models.Site(
        name = "site",
        siteType = Some("website")
      )
    )
    Ok(Json.toJson(pageInfo))
  }

  def getOpenGraphMetadataForUrl(url: String) = Action {
    val document = {
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
    Logger.debug(s"$document")
    val headElement = document.head()
    Logger.debug(s"$headElement")

    val ogTitle = headElement.select("meta[property=og:title]").attr("content")
    val ogType = headElement.select("meta[property=og:title]").attr("content")
    val ogImage = headElement.select("meta[property=og:image]").attr("content")
    val ogUrl = headElement.select("meta[property=og:url]").attr("content")

    val openGraphMetadata = models.OpenGraphMetadata(
      title = ogTitle,
      openGraphType = models.OpenGraphType(ogType),
      image = models.OpenGraphMedia(
        url = ogImage
      ),
      url = ogUrl
    )
    Ok(Json.toJson(openGraphMetadata))
  }
}