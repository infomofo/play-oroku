package controllers

import java.net.URL

import com.infomofo.oroku.shredder.PageShredder
import com.typesafe.scalalogging.LazyLogging
import play.api.mvc._
import play.api.libs.json._
import com.infomofo.oroku.v0.models.json._

object PageInfo extends Controller with LazyLogging {
  private val HttpPattern = """(http.*)""".r
  def getInfoForUrl(url: String) = Action {
    val newUrl = url.toLowerCase match {
      case HttpPattern(matchUrl) =>
        matchUrl
      case _ =>
        "http://" + url
    }
    logger.warn(newUrl)
    val pageInfo = PageShredder(new URL(newUrl)).pageInfo

    Ok(Json.toJson(pageInfo))
  }

  def getOpenGraphMetadataForUrl(url: String) = Action {
    val openGraphMetadata = PageShredder(new URL(url)).openGraphMetadata
    Ok(Json.toJson(openGraphMetadata))
  }

  def getAppleItunesMetadataForUrl(url: String) = play.mvc.Results.TODO

  def getSearchMetadataForUrl(url: String) = play.mvc.Results.TODO

  def getTwitterCardMetadataForUrl(url: String) = play.mvc.Results.TODO

  def getSchemaOrgItemsForUrl(url: String) = play.mvc.Results.TODO
}