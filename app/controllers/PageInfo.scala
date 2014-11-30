package controllers

import play.api.mvc._
import play.api.libs.json._

import com.infomofo.oroku.models
import com.infomofo.oroku.models.json._
import org.joda.time.DateTime

object PageInfo extends Controller {
  def getInfoForUrl(url: String) = Action {
    val pageInfo = models.PageInfo(
      titles = Seq.empty[String],
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
}