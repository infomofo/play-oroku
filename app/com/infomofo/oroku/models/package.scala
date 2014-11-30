package com.infomofo.oroku.models {

  /**
   * the useful information on this webpage
   */
  case class PageInfo(
    titles: Seq[String],
    images: Seq[String] = Nil,
    descriptions: Seq[String] = Nil,
    urls: Seq[String],
    keywords: Seq[String],
    categories: Seq[String] = Nil,
    locations: Seq[String],
    retrievedAt: _root_.org.joda.time.DateTime,
    site: com.infomofo.oroku.models.Site
  )

  /**
   * information about the site
   */
  case class Site(
    name: String,
    siteType: scala.Option[String] = None
  )

}

package com.infomofo.oroku.models {
  package object json {
    import play.api.libs.json.__
    import play.api.libs.json.JsString
    import play.api.libs.json.Writes
    import play.api.libs.functional.syntax._

    private[oroku] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

    private[oroku] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
      def writes(x: java.util.UUID) = JsString(x.toString)
    }

    private[oroku] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
      import org.joda.time.format.ISODateTimeFormat.dateTimeParser
      dateTimeParser.parseDateTime(str)
    }

    private[oroku] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
      def writes(x: org.joda.time.DateTime) = {
        import org.joda.time.format.ISODateTimeFormat.dateTime
        val str = dateTime.print(x)
        JsString(str)
      }
    }


    implicit def jsonReadsOrokuPageInfo: play.api.libs.json.Reads[PageInfo] = {
      (
        (__ \ "titles").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "images").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "descriptions").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "urls").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "keywords").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "categories").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "locations").readNullable[Seq[String]].map(_.getOrElse(Nil)) and
        (__ \ "retrieved_at").read[_root_.org.joda.time.DateTime] and
        (__ \ "site").read[com.infomofo.oroku.models.Site]
      )(PageInfo.apply _)
    }

    implicit def jsonWritesOrokuPageInfo: play.api.libs.json.Writes[PageInfo] = {
      (
        (__ \ "titles").write[Seq[String]] and
        (__ \ "images").write[Seq[String]] and
        (__ \ "descriptions").write[Seq[String]] and
        (__ \ "urls").write[Seq[String]] and
        (__ \ "keywords").write[Seq[String]] and
        (__ \ "categories").write[Seq[String]] and
        (__ \ "locations").write[Seq[String]] and
        (__ \ "retrieved_at").write[_root_.org.joda.time.DateTime] and
        (__ \ "site").write[com.infomofo.oroku.models.Site]
      )(unlift(PageInfo.unapply _))
    }

    implicit def jsonReadsOrokuSite: play.api.libs.json.Reads[Site] = {
      (
        (__ \ "name").read[String] and
        (__ \ "site_type").readNullable[String]
      )(Site.apply _)
    }

    implicit def jsonWritesOrokuSite: play.api.libs.json.Writes[Site] = {
      (
        (__ \ "name").write[String] and
        (__ \ "site_type").write[scala.Option[String]]
      )(unlift(Site.unapply _))
    }
  }
}

package com.infomofo.oroku {

  class Client(apiUrl: String, apiToken: scala.Option[String] = None) {
    import com.infomofo.oroku.models.json._

    private val UserAgent = "apidoc:0.7.16 http://www.apidoc.me/infomofo/code/oroku/0.0.1-dev/play_2_3_client"
    private val logger = play.api.Logger("com.infomofo.oroku.client")

    logger.info(s"Initializing com.infomofo.oroku.client for url $apiUrl")

    def pageInfo: PageInfo = PageInfo

    object PageInfo extends PageInfo {
      override def getInfoForUrl(
        url: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.PageInfo]] = {
        val queryParameters = Seq(
          Some("url" -> url)
        ).flatten

        _executeRequest("GET", s"page_info/info_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => Some(r.json.as[com.infomofo.oroku.models.PageInfo])
          case r if r.status == 404 => None
          case r => throw new FailedRequest(r)
        }
      }
    }

    def _requestHolder(path: String): play.api.libs.ws.WSRequestHolder = {
      import play.api.Play.current

      val holder = play.api.libs.ws.WS.url(apiUrl + path).withHeaders("User-Agent" -> UserAgent)
      apiToken.fold(holder) { token =>
        holder.withAuth(token, "", play.api.libs.ws.WSAuthScheme.BASIC)
      }
    }

    def _logRequest(method: String, req: play.api.libs.ws.WSRequestHolder)(implicit ec: scala.concurrent.ExecutionContext): play.api.libs.ws.WSRequestHolder = {
      val queryComponents = for {
        (name, values) <- req.queryString
        value <- values
      } yield name -> value
      val url = s"${req.url}${queryComponents.mkString("?", "&", "")}"
      apiToken.fold(logger.info(s"curl -X $method $url")) { _ =>
        logger.info(s"curl -X $method -u '[REDACTED]:' $url")
      }
      req
    }

    def _executeRequest(
      method: String,
      path: String,
      queryParameters: Seq[(String, String)] = Seq.empty,
      body: Option[play.api.libs.json.JsValue] = None
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[play.api.libs.ws.WSResponse] = {
      method.toUpperCase match {
        case "GET" => {
          _logRequest("GET", _requestHolder(path).withQueryString(queryParameters:_*)).get()
        }
        case "POST" => {
          _logRequest("POST", _requestHolder(path).withQueryString(queryParameters:_*)).post(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PUT" => {
          _logRequest("PUT", _requestHolder(path).withQueryString(queryParameters:_*)).put(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "PATCH" => {
          _logRequest("PATCH", _requestHolder(path).withQueryString(queryParameters:_*)).patch(body.getOrElse(play.api.libs.json.Json.obj()))
        }
        case "DELETE" => {
          _logRequest("DELETE", _requestHolder(path).withQueryString(queryParameters:_*)).delete()
        }
         case "HEAD" => {
          _logRequest("HEAD", _requestHolder(path).withQueryString(queryParameters:_*)).head()
        }
        case _ => {
          _logRequest(method, _requestHolder(path).withQueryString(queryParameters:_*))
          sys.error("Unsupported method[%s]".format(method))
        }
      }
    }

  }

  trait PageInfo {
    /**
     * shreds page info from a specified web page
     */
    def getInfoForUrl(
      url: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.PageInfo]]
  }

  case class FailedRequest(
    response: play.api.libs.ws.WSResponse,
    message: Option[String] = None
  ) extends Exception(message.getOrElse(response.status + ": " + response.body))

  object Bindables {

    import play.api.mvc.{PathBindable, QueryStringBindable}
    import org.joda.time.{DateTime, LocalDate}
    import org.joda.time.format.ISODateTimeFormat
    import com.infomofo.oroku.models._

    // Type: date-time-iso8601
    implicit val pathBindableTypeDateTimeIso8601 = new PathBindable.Parsing[DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    implicit val queryStringBindableTypeDateTimeIso8601 = new QueryStringBindable.Parsing[DateTime](
      ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
    )

    // Type: date-iso8601
    implicit val pathBindableTypeDateIso8601 = new PathBindable.Parsing[LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )

    implicit val queryStringBindableTypeDateIso8601 = new QueryStringBindable.Parsing[LocalDate](
      ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: Exception) => s"Error parsing date $key. Example: 2014-04-29"
    )



  }

}