package com.infomofo.oroku.models {

  /**
   * Information provided to show a smart app banner to direct the user to an
   * equivalent app store listing when viewing this webpage on iOS.  See
   * https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/PromotingAppswithAppBanners/PromotingAppswithAppBanners.html
   */
  case class AppleItunesMetadata(
    appId: com.infomofo.oroku.models.MetaInteger,
    affiliateData: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    appArgument: scala.Option[com.infomofo.oroku.models.MetaString] = None
  )

  /**
   * A brand is a name used by an organization or business person for labeling a
   * product, product group, or similar.
   */
  case class BrandObject(
    logo: scala.Option[String] = None,
    name: scala.Option[String] = None
  )

  /**
   * An html head meta tag that has been parsed for boolean content.
   */
  case class MetaBoolean(
    value: Boolean,
    tag: String
  )

  /**
   * An html head meta tag that has been parsed for date time content.
   */
  case class MetaDateTimeIso8601(
    value: _root_.org.joda.time.DateTime,
    tag: String
  )

  /**
   * An html head meta tag that has been parsed for integer content.
   */
  case class MetaInteger(
    value: Int,
    tag: String
  )

  /**
   * An html head meta tag that has been parsed for open_graph_type content.
   */
  case class MetaOpenGraphType(
    value: com.infomofo.oroku.models.OpenGraphType,
    tag: String
  )

  /**
   * An html head meta tag that has been parsed for string content.
   */
  case class MetaString(
    value: String,
    tag: String
  )

  /**
   * An html head meta tag that has been parsed for twitter_card_type content.
   */
  case class MetaTwitterCardType(
    value: com.infomofo.oroku.models.TwitterCardType,
    tag: String
  )

  /**
   * A media type specified by open graph. The og:image, og:audio, and og:video
   * property has some optional structured properties.
   */
  case class OpenGraphMedia(
    url: com.infomofo.oroku.models.MetaString,
    secureUrl: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    mimeType: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    width: scala.Option[com.infomofo.oroku.models.MetaInteger] = None,
    height: scala.Option[com.infomofo.oroku.models.MetaInteger] = None
  )

  /**
   * Information about the site presented in opengraph format (see ogp.me)
   */
  case class OpenGraphMetadata(
    title: com.infomofo.oroku.models.MetaString,
    openGraphType: com.infomofo.oroku.models.MetaOpenGraphType,
    image: com.infomofo.oroku.models.OpenGraphMedia,
    url: com.infomofo.oroku.models.MetaString,
    description: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    determiner: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    locale: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    localeAlternative: Seq[com.infomofo.oroku.models.MetaString] = Nil,
    siteName: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    video: scala.Option[com.infomofo.oroku.models.OpenGraphMedia] = None,
    audio: scala.Option[com.infomofo.oroku.models.OpenGraphMedia] = None,
    updatedTime: scala.Option[com.infomofo.oroku.models.MetaDateTimeIso8601] = None,
    seeAlso: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    appId: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    profileId: scala.Option[com.infomofo.oroku.models.MetaString] = None
  )

  /**
   * The basic information on this webpage, according to major metadata tagging
   * standards and conventions, as well as information derived from the content of
   * the page itself.
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
    site: com.infomofo.oroku.models.Site,
    openGraphMetadata: scala.Option[com.infomofo.oroku.models.OpenGraphMetadata] = None,
    appleItunesMetadata: scala.Option[com.infomofo.oroku.models.AppleItunesMetadata] = None,
    twitterCardMetadata: scala.Option[com.infomofo.oroku.models.TwitterCardMetadata] = None,
    schemaOrgItems: Seq[com.infomofo.oroku.models.SchemaOrgItem] = Nil,
    searchMetadata: scala.Option[com.infomofo.oroku.models.SearchMetadata] = None
  )

  /**
   * The representation of an item according to schema.org. schema.org is a
   * collaboration by Google, Microsoft, and Yahoo! to improve the web by creating a
   * structured data markup schema supported by major search engines. Multiple item
   * scopes can be included on one page.  Only the most common properties are
   * currently parsed. See: http://schema.org/docs/gs.html.
   */
  case class SchemaOrgItem(
    itemType: scala.Option[String] = None,
    name: scala.Option[String] = None,
    alternateName: scala.Option[String] = None,
    description: scala.Option[String] = None,
    image: scala.Option[String] = None,
    url: scala.Option[String] = None,
    sameAs: scala.Option[String] = None,
    brand: scala.Option[com.infomofo.oroku.models.BrandObject] = None,
    color: scala.Option[String] = None,
    productId: scala.Option[String] = None,
    sku: scala.Option[String] = None,
    model: scala.Option[String] = None,
    gtin8: scala.Option[String] = None,
    gtin13: scala.Option[String] = None,
    gtin14: scala.Option[String] = None
  )

  /**
   * Meta tags provide search engines with information about their sites. Meta tags
   * can be used to provide information to all sorts of clients, and each system
   * processes only the meta tags they understand and ignores the rest.  See
   * https://support.google.com/webmasters/answer/79812?hl=en.
   */
  case class SearchMetadata(
    description: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    title: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    robots: Seq[com.infomofo.oroku.models.MetaString] = Nil,
    googlebot: Seq[com.infomofo.oroku.models.MetaString] = Nil,
    googleSiteLinkSearchBox: scala.Option[com.infomofo.oroku.models.MetaBoolean] = None,
    googleNotranslate: scala.Option[com.infomofo.oroku.models.MetaBoolean] = None,
    googleSiteVerification: Seq[com.infomofo.oroku.models.MetaString] = Nil,
    charset: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    contentType: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    keywords: Seq[com.infomofo.oroku.models.MetaString] = Nil
  )

  /**
   * information about the domain that this site is hosted on.
   */
  case class Site(
    name: String,
    siteType: scala.Option[String] = None,
    domain: scala.Option[String] = None
  )

  /**
   * A twitter card to detail a mobile app with direct download.
   */
  case class TwitterAppInfo(
    country: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    iphone: scala.Option[com.infomofo.oroku.models.TwitterAppStoreInfo] = None,
    ipad: scala.Option[com.infomofo.oroku.models.TwitterAppStoreInfo] = None,
    googleplay: scala.Option[com.infomofo.oroku.models.TwitterAppStoreInfo] = None
  )

  /**
   * Information linking twitter card data to an app store. See
   * https://dev.twitter.com/cards/types/app
   */
  case class TwitterAppStoreInfo(
    name: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    id: com.infomofo.oroku.models.MetaString,
    url: scala.Option[com.infomofo.oroku.models.MetaString] = None
  )

  /**
   * Additinal information provided by a webpage to control how a link to that page
   * is rendered when posted on twitter. See also:
   * https://dev.twitter.com/cards/markup.
   */
  case class TwitterCardMetadata(
    card: com.infomofo.oroku.models.MetaTwitterCardType,
    domain: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    site: scala.Option[com.infomofo.oroku.models.TwitterUser] = None,
    creator: scala.Option[com.infomofo.oroku.models.TwitterUser] = None,
    description: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    title: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    images: Seq[com.infomofo.oroku.models.TwitterImage] = Nil,
    app: scala.Option[com.infomofo.oroku.models.TwitterAppInfo] = None,
    customFields: Seq[com.infomofo.oroku.models.TwitterCustomField] = Nil
  )

  /**
   * A customizable data field to display on a twitter card
   */
  case class TwitterCustomField(
    label: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    data: com.infomofo.oroku.models.MetaString
  )

  /**
   * An image represented on a twitter card for a page.
   */
  case class TwitterImage(
    src: com.infomofo.oroku.models.MetaString,
    width: scala.Option[com.infomofo.oroku.models.MetaInteger] = None,
    height: scala.Option[com.infomofo.oroku.models.MetaInteger] = None
  )

  /**
   * User info declared for parsing into a twitter card.  See:
   * https://dev.twitter.com/cards/markup
   */
  case class TwitterUser(
    username: scala.Option[com.infomofo.oroku.models.MetaString] = None,
    id: scala.Option[com.infomofo.oroku.models.MetaInteger] = None
  )

  /**
   * The type of your object according to the open graph protocol.  When the
   * community agrees on the schema for a type, it is added to the list of global
   * types. All other objects in the type system are CURIEs of the form.
   */
  sealed trait OpenGraphType

  object OpenGraphType {

    case object Article extends OpenGraphType { override def toString = "article" }
    case object Book extends OpenGraphType { override def toString = "book" }
    case object Profile extends OpenGraphType { override def toString = "profile" }
    case object Website extends OpenGraphType { override def toString = "website" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends OpenGraphType

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all = Seq(Article, Book, Profile, Website)

    private[this]
    val byName = all.map(x => x.toString -> x).toMap

    def apply(value: String): OpenGraphType = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): scala.Option[OpenGraphType] = byName.get(value)

  }

  /**
   * The card type specified for this page that will affect how a link to this page
   * will be rendered if posted on twitter. See: https://dev.twitter.com/cards/types.
   */
  sealed trait TwitterCardType

  object TwitterCardType {

    /**
     * Default Card, including a title, description, thumbnail, and Twitter account
     * attribution. See https://dev.twitter.com/cards/types/summary.
     */
    case object Summary extends TwitterCardType { override def toString = "summary" }
    /**
     * Similar to a Summary Card, but with a prominently featured image. See
     * https://dev.twitter.com/cards/types/summary-large-image
     */
    case object SummaryLargeImage extends TwitterCardType { override def toString = "summary_large_image" }
    /**
     * A Card with a photo only. See https://dev.twitter.com/cards/types/photo.
     */
    case object Photo extends TwitterCardType { override def toString = "photo" }
    /**
     * A Card highlighting a collection of four photos. See
     * https://dev.twitter.com/cards/types/gallery
     */
    case object Gallery extends TwitterCardType { override def toString = "gallery" }
    /**
     * A Card to detail a mobile app with direct download.  See
     * https://dev.twitter.com/cards/types/app
     */
    case object App extends TwitterCardType { override def toString = "app" }
    /**
     * A Card to provide video/audio/media. See
     * https://dev.twitter.com/cards/types/player
     */
    case object Player extends TwitterCardType { override def toString = "player" }
    /**
     * A Card optimized for product information. See
     * https://dev.twitter.com/cards/types/product.
     */
    case object Product extends TwitterCardType { override def toString = "product" }

    /**
     * UNDEFINED captures values that are sent either in error or
     * that were added by the server after this library was
     * generated. We want to make it easy and obvious for users of
     * this library to handle this case gracefully.
     *
     * We use all CAPS for the variable name to avoid collisions
     * with the camel cased values above.
     */
    case class UNDEFINED(override val toString: String) extends TwitterCardType

    /**
     * all returns a list of all the valid, known values. We use
     * lower case to avoid collisions with the camel cased values
     * above.
     */
    val all = Seq(Summary, SummaryLargeImage, Photo, Gallery, App, Player, Product)

    private[this]
    val byName = all.map(x => x.toString -> x).toMap

    def apply(value: String): TwitterCardType = fromString(value).getOrElse(UNDEFINED(value))

    def fromString(value: String): scala.Option[TwitterCardType] = byName.get(value)

  }

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

    implicit val jsonReadsOrokuEnum_OpenGraphType = __.read[String].map(OpenGraphType.apply)
    implicit val jsonWritesOrokuEnum_OpenGraphType = new Writes[OpenGraphType] {
      def writes(x: OpenGraphType) = JsString(x.toString)
    }

    implicit val jsonReadsOrokuEnum_TwitterCardType = __.read[String].map(TwitterCardType.apply)
    implicit val jsonWritesOrokuEnum_TwitterCardType = new Writes[TwitterCardType] {
      def writes(x: TwitterCardType) = JsString(x.toString)
    }
    implicit def jsonReadsOrokuAppleItunesMetadata: play.api.libs.json.Reads[AppleItunesMetadata] = {
      (
        (__ \ "app_id").read[com.infomofo.oroku.models.MetaInteger] and
        (__ \ "affiliate_data").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "app_argument").readNullable[com.infomofo.oroku.models.MetaString]
      )(AppleItunesMetadata.apply _)
    }

    implicit def jsonWritesOrokuAppleItunesMetadata: play.api.libs.json.Writes[AppleItunesMetadata] = {
      (
        (__ \ "app_id").write[com.infomofo.oroku.models.MetaInteger] and
        (__ \ "affiliate_data").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "app_argument").write[scala.Option[com.infomofo.oroku.models.MetaString]]
      )(unlift(AppleItunesMetadata.unapply _))
    }

    implicit def jsonReadsOrokuBrandObject: play.api.libs.json.Reads[BrandObject] = {
      (
        (__ \ "logo").readNullable[String] and
        (__ \ "name").readNullable[String]
      )(BrandObject.apply _)
    }

    implicit def jsonWritesOrokuBrandObject: play.api.libs.json.Writes[BrandObject] = {
      (
        (__ \ "logo").write[scala.Option[String]] and
        (__ \ "name").write[scala.Option[String]]
      )(unlift(BrandObject.unapply _))
    }

    implicit def jsonReadsOrokuMetaBoolean: play.api.libs.json.Reads[MetaBoolean] = {
      (
        (__ \ "value").read[Boolean] and
        (__ \ "tag").read[String]
      )(MetaBoolean.apply _)
    }

    implicit def jsonWritesOrokuMetaBoolean: play.api.libs.json.Writes[MetaBoolean] = {
      (
        (__ \ "value").write[Boolean] and
        (__ \ "tag").write[String]
      )(unlift(MetaBoolean.unapply _))
    }

    implicit def jsonReadsOrokuMetaDateTimeIso8601: play.api.libs.json.Reads[MetaDateTimeIso8601] = {
      (
        (__ \ "value").read[_root_.org.joda.time.DateTime] and
        (__ \ "tag").read[String]
      )(MetaDateTimeIso8601.apply _)
    }

    implicit def jsonWritesOrokuMetaDateTimeIso8601: play.api.libs.json.Writes[MetaDateTimeIso8601] = {
      (
        (__ \ "value").write[_root_.org.joda.time.DateTime] and
        (__ \ "tag").write[String]
      )(unlift(MetaDateTimeIso8601.unapply _))
    }

    implicit def jsonReadsOrokuMetaInteger: play.api.libs.json.Reads[MetaInteger] = {
      (
        (__ \ "value").read[Int] and
        (__ \ "tag").read[String]
      )(MetaInteger.apply _)
    }

    implicit def jsonWritesOrokuMetaInteger: play.api.libs.json.Writes[MetaInteger] = {
      (
        (__ \ "value").write[Int] and
        (__ \ "tag").write[String]
      )(unlift(MetaInteger.unapply _))
    }

    implicit def jsonReadsOrokuMetaOpenGraphType: play.api.libs.json.Reads[MetaOpenGraphType] = {
      (
        (__ \ "value").read[com.infomofo.oroku.models.OpenGraphType] and
        (__ \ "tag").read[String]
      )(MetaOpenGraphType.apply _)
    }

    implicit def jsonWritesOrokuMetaOpenGraphType: play.api.libs.json.Writes[MetaOpenGraphType] = {
      (
        (__ \ "value").write[com.infomofo.oroku.models.OpenGraphType] and
        (__ \ "tag").write[String]
      )(unlift(MetaOpenGraphType.unapply _))
    }

    implicit def jsonReadsOrokuMetaString: play.api.libs.json.Reads[MetaString] = {
      (
        (__ \ "value").read[String] and
        (__ \ "tag").read[String]
      )(MetaString.apply _)
    }

    implicit def jsonWritesOrokuMetaString: play.api.libs.json.Writes[MetaString] = {
      (
        (__ \ "value").write[String] and
        (__ \ "tag").write[String]
      )(unlift(MetaString.unapply _))
    }

    implicit def jsonReadsOrokuMetaTwitterCardType: play.api.libs.json.Reads[MetaTwitterCardType] = {
      (
        (__ \ "value").read[com.infomofo.oroku.models.TwitterCardType] and
        (__ \ "tag").read[String]
      )(MetaTwitterCardType.apply _)
    }

    implicit def jsonWritesOrokuMetaTwitterCardType: play.api.libs.json.Writes[MetaTwitterCardType] = {
      (
        (__ \ "value").write[com.infomofo.oroku.models.TwitterCardType] and
        (__ \ "tag").write[String]
      )(unlift(MetaTwitterCardType.unapply _))
    }

    implicit def jsonReadsOrokuOpenGraphMedia: play.api.libs.json.Reads[OpenGraphMedia] = {
      (
        (__ \ "url").read[com.infomofo.oroku.models.MetaString] and
        (__ \ "secure_url").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "mime_type").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "width").readNullable[com.infomofo.oroku.models.MetaInteger] and
        (__ \ "height").readNullable[com.infomofo.oroku.models.MetaInteger]
      )(OpenGraphMedia.apply _)
    }

    implicit def jsonWritesOrokuOpenGraphMedia: play.api.libs.json.Writes[OpenGraphMedia] = {
      (
        (__ \ "url").write[com.infomofo.oroku.models.MetaString] and
        (__ \ "secure_url").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "mime_type").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "width").write[scala.Option[com.infomofo.oroku.models.MetaInteger]] and
        (__ \ "height").write[scala.Option[com.infomofo.oroku.models.MetaInteger]]
      )(unlift(OpenGraphMedia.unapply _))
    }

    implicit def jsonReadsOrokuOpenGraphMetadata: play.api.libs.json.Reads[OpenGraphMetadata] = {
      (
        (__ \ "title").read[com.infomofo.oroku.models.MetaString] and
        (__ \ "open_graph_type").read[com.infomofo.oroku.models.MetaOpenGraphType] and
        (__ \ "image").read[com.infomofo.oroku.models.OpenGraphMedia] and
        (__ \ "url").read[com.infomofo.oroku.models.MetaString] and
        (__ \ "description").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "determiner").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "locale").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "locale_alternative").readNullable[Seq[com.infomofo.oroku.models.MetaString]].map(_.getOrElse(Nil)) and
        (__ \ "site_name").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "video").readNullable[com.infomofo.oroku.models.OpenGraphMedia] and
        (__ \ "audio").readNullable[com.infomofo.oroku.models.OpenGraphMedia] and
        (__ \ "updated_time").readNullable[com.infomofo.oroku.models.MetaDateTimeIso8601] and
        (__ \ "see_also").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "app_id").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "profile_id").readNullable[com.infomofo.oroku.models.MetaString]
      )(OpenGraphMetadata.apply _)
    }

    implicit def jsonWritesOrokuOpenGraphMetadata: play.api.libs.json.Writes[OpenGraphMetadata] = {
      (
        (__ \ "title").write[com.infomofo.oroku.models.MetaString] and
        (__ \ "open_graph_type").write[com.infomofo.oroku.models.MetaOpenGraphType] and
        (__ \ "image").write[com.infomofo.oroku.models.OpenGraphMedia] and
        (__ \ "url").write[com.infomofo.oroku.models.MetaString] and
        (__ \ "description").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "determiner").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "locale").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "locale_alternative").write[Seq[com.infomofo.oroku.models.MetaString]] and
        (__ \ "site_name").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "video").write[scala.Option[com.infomofo.oroku.models.OpenGraphMedia]] and
        (__ \ "audio").write[scala.Option[com.infomofo.oroku.models.OpenGraphMedia]] and
        (__ \ "updated_time").write[scala.Option[com.infomofo.oroku.models.MetaDateTimeIso8601]] and
        (__ \ "see_also").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "app_id").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "profile_id").write[scala.Option[com.infomofo.oroku.models.MetaString]]
      )(unlift(OpenGraphMetadata.unapply _))
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
        (__ \ "site").read[com.infomofo.oroku.models.Site] and
        (__ \ "open_graph_metadata").readNullable[com.infomofo.oroku.models.OpenGraphMetadata] and
        (__ \ "apple_itunes_metadata").readNullable[com.infomofo.oroku.models.AppleItunesMetadata] and
        (__ \ "twitter_card_metadata").readNullable[com.infomofo.oroku.models.TwitterCardMetadata] and
        (__ \ "schema_org_items").readNullable[Seq[com.infomofo.oroku.models.SchemaOrgItem]].map(_.getOrElse(Nil)) and
        (__ \ "search_metadata").readNullable[com.infomofo.oroku.models.SearchMetadata]
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
        (__ \ "site").write[com.infomofo.oroku.models.Site] and
        (__ \ "open_graph_metadata").write[scala.Option[com.infomofo.oroku.models.OpenGraphMetadata]] and
        (__ \ "apple_itunes_metadata").write[scala.Option[com.infomofo.oroku.models.AppleItunesMetadata]] and
        (__ \ "twitter_card_metadata").write[scala.Option[com.infomofo.oroku.models.TwitterCardMetadata]] and
        (__ \ "schema_org_items").write[Seq[com.infomofo.oroku.models.SchemaOrgItem]] and
        (__ \ "search_metadata").write[scala.Option[com.infomofo.oroku.models.SearchMetadata]]
      )(unlift(PageInfo.unapply _))
    }

    implicit def jsonReadsOrokuSchemaOrgItem: play.api.libs.json.Reads[SchemaOrgItem] = {
      (
        (__ \ "item_type").readNullable[String] and
        (__ \ "name").readNullable[String] and
        (__ \ "alternate_name").readNullable[String] and
        (__ \ "description").readNullable[String] and
        (__ \ "image").readNullable[String] and
        (__ \ "url").readNullable[String] and
        (__ \ "same_as").readNullable[String] and
        (__ \ "brand").readNullable[com.infomofo.oroku.models.BrandObject] and
        (__ \ "color").readNullable[String] and
        (__ \ "productId").readNullable[String] and
        (__ \ "sku").readNullable[String] and
        (__ \ "model").readNullable[String] and
        (__ \ "gtin8").readNullable[String] and
        (__ \ "gtin13").readNullable[String] and
        (__ \ "gtin14").readNullable[String]
      )(SchemaOrgItem.apply _)
    }

    implicit def jsonWritesOrokuSchemaOrgItem: play.api.libs.json.Writes[SchemaOrgItem] = {
      (
        (__ \ "item_type").write[scala.Option[String]] and
        (__ \ "name").write[scala.Option[String]] and
        (__ \ "alternate_name").write[scala.Option[String]] and
        (__ \ "description").write[scala.Option[String]] and
        (__ \ "image").write[scala.Option[String]] and
        (__ \ "url").write[scala.Option[String]] and
        (__ \ "same_as").write[scala.Option[String]] and
        (__ \ "brand").write[scala.Option[com.infomofo.oroku.models.BrandObject]] and
        (__ \ "color").write[scala.Option[String]] and
        (__ \ "productId").write[scala.Option[String]] and
        (__ \ "sku").write[scala.Option[String]] and
        (__ \ "model").write[scala.Option[String]] and
        (__ \ "gtin8").write[scala.Option[String]] and
        (__ \ "gtin13").write[scala.Option[String]] and
        (__ \ "gtin14").write[scala.Option[String]]
      )(unlift(SchemaOrgItem.unapply _))
    }

    implicit def jsonReadsOrokuSearchMetadata: play.api.libs.json.Reads[SearchMetadata] = {
      (
        (__ \ "description").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "title").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "robots").readNullable[Seq[com.infomofo.oroku.models.MetaString]].map(_.getOrElse(Nil)) and
        (__ \ "googlebot").readNullable[Seq[com.infomofo.oroku.models.MetaString]].map(_.getOrElse(Nil)) and
        (__ \ "google_site_link_search_box").readNullable[com.infomofo.oroku.models.MetaBoolean] and
        (__ \ "google_notranslate").readNullable[com.infomofo.oroku.models.MetaBoolean] and
        (__ \ "google_site_verification").readNullable[Seq[com.infomofo.oroku.models.MetaString]].map(_.getOrElse(Nil)) and
        (__ \ "charset").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "content_type").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "keywords").readNullable[Seq[com.infomofo.oroku.models.MetaString]].map(_.getOrElse(Nil))
      )(SearchMetadata.apply _)
    }

    implicit def jsonWritesOrokuSearchMetadata: play.api.libs.json.Writes[SearchMetadata] = {
      (
        (__ \ "description").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "title").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "robots").write[Seq[com.infomofo.oroku.models.MetaString]] and
        (__ \ "googlebot").write[Seq[com.infomofo.oroku.models.MetaString]] and
        (__ \ "google_site_link_search_box").write[scala.Option[com.infomofo.oroku.models.MetaBoolean]] and
        (__ \ "google_notranslate").write[scala.Option[com.infomofo.oroku.models.MetaBoolean]] and
        (__ \ "google_site_verification").write[Seq[com.infomofo.oroku.models.MetaString]] and
        (__ \ "charset").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "content_type").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "keywords").write[Seq[com.infomofo.oroku.models.MetaString]]
      )(unlift(SearchMetadata.unapply _))
    }

    implicit def jsonReadsOrokuSite: play.api.libs.json.Reads[Site] = {
      (
        (__ \ "name").read[String] and
        (__ \ "site_type").readNullable[String] and
        (__ \ "domain").readNullable[String]
      )(Site.apply _)
    }

    implicit def jsonWritesOrokuSite: play.api.libs.json.Writes[Site] = {
      (
        (__ \ "name").write[String] and
        (__ \ "site_type").write[scala.Option[String]] and
        (__ \ "domain").write[scala.Option[String]]
      )(unlift(Site.unapply _))
    }

    implicit def jsonReadsOrokuTwitterAppInfo: play.api.libs.json.Reads[TwitterAppInfo] = {
      (
        (__ \ "country").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "iphone").readNullable[com.infomofo.oroku.models.TwitterAppStoreInfo] and
        (__ \ "ipad").readNullable[com.infomofo.oroku.models.TwitterAppStoreInfo] and
        (__ \ "googleplay").readNullable[com.infomofo.oroku.models.TwitterAppStoreInfo]
      )(TwitterAppInfo.apply _)
    }

    implicit def jsonWritesOrokuTwitterAppInfo: play.api.libs.json.Writes[TwitterAppInfo] = {
      (
        (__ \ "country").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "iphone").write[scala.Option[com.infomofo.oroku.models.TwitterAppStoreInfo]] and
        (__ \ "ipad").write[scala.Option[com.infomofo.oroku.models.TwitterAppStoreInfo]] and
        (__ \ "googleplay").write[scala.Option[com.infomofo.oroku.models.TwitterAppStoreInfo]]
      )(unlift(TwitterAppInfo.unapply _))
    }

    implicit def jsonReadsOrokuTwitterAppStoreInfo: play.api.libs.json.Reads[TwitterAppStoreInfo] = {
      (
        (__ \ "name").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "id").read[com.infomofo.oroku.models.MetaString] and
        (__ \ "url").readNullable[com.infomofo.oroku.models.MetaString]
      )(TwitterAppStoreInfo.apply _)
    }

    implicit def jsonWritesOrokuTwitterAppStoreInfo: play.api.libs.json.Writes[TwitterAppStoreInfo] = {
      (
        (__ \ "name").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "id").write[com.infomofo.oroku.models.MetaString] and
        (__ \ "url").write[scala.Option[com.infomofo.oroku.models.MetaString]]
      )(unlift(TwitterAppStoreInfo.unapply _))
    }

    implicit def jsonReadsOrokuTwitterCardMetadata: play.api.libs.json.Reads[TwitterCardMetadata] = {
      (
        (__ \ "card").read[com.infomofo.oroku.models.MetaTwitterCardType] and
        (__ \ "domain").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "site").readNullable[com.infomofo.oroku.models.TwitterUser] and
        (__ \ "creator").readNullable[com.infomofo.oroku.models.TwitterUser] and
        (__ \ "description").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "title").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "images").readNullable[Seq[com.infomofo.oroku.models.TwitterImage]].map(_.getOrElse(Nil)) and
        (__ \ "app").readNullable[com.infomofo.oroku.models.TwitterAppInfo] and
        (__ \ "custom_fields").readNullable[Seq[com.infomofo.oroku.models.TwitterCustomField]].map(_.getOrElse(Nil))
      )(TwitterCardMetadata.apply _)
    }

    implicit def jsonWritesOrokuTwitterCardMetadata: play.api.libs.json.Writes[TwitterCardMetadata] = {
      (
        (__ \ "card").write[com.infomofo.oroku.models.MetaTwitterCardType] and
        (__ \ "domain").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "site").write[scala.Option[com.infomofo.oroku.models.TwitterUser]] and
        (__ \ "creator").write[scala.Option[com.infomofo.oroku.models.TwitterUser]] and
        (__ \ "description").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "title").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "images").write[Seq[com.infomofo.oroku.models.TwitterImage]] and
        (__ \ "app").write[scala.Option[com.infomofo.oroku.models.TwitterAppInfo]] and
        (__ \ "custom_fields").write[Seq[com.infomofo.oroku.models.TwitterCustomField]]
      )(unlift(TwitterCardMetadata.unapply _))
    }

    implicit def jsonReadsOrokuTwitterCustomField: play.api.libs.json.Reads[TwitterCustomField] = {
      (
        (__ \ "label").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "data").read[com.infomofo.oroku.models.MetaString]
      )(TwitterCustomField.apply _)
    }

    implicit def jsonWritesOrokuTwitterCustomField: play.api.libs.json.Writes[TwitterCustomField] = {
      (
        (__ \ "label").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "data").write[com.infomofo.oroku.models.MetaString]
      )(unlift(TwitterCustomField.unapply _))
    }

    implicit def jsonReadsOrokuTwitterImage: play.api.libs.json.Reads[TwitterImage] = {
      (
        (__ \ "src").read[com.infomofo.oroku.models.MetaString] and
        (__ \ "width").readNullable[com.infomofo.oroku.models.MetaInteger] and
        (__ \ "height").readNullable[com.infomofo.oroku.models.MetaInteger]
      )(TwitterImage.apply _)
    }

    implicit def jsonWritesOrokuTwitterImage: play.api.libs.json.Writes[TwitterImage] = {
      (
        (__ \ "src").write[com.infomofo.oroku.models.MetaString] and
        (__ \ "width").write[scala.Option[com.infomofo.oroku.models.MetaInteger]] and
        (__ \ "height").write[scala.Option[com.infomofo.oroku.models.MetaInteger]]
      )(unlift(TwitterImage.unapply _))
    }

    implicit def jsonReadsOrokuTwitterUser: play.api.libs.json.Reads[TwitterUser] = {
      (
        (__ \ "username").readNullable[com.infomofo.oroku.models.MetaString] and
        (__ \ "id").readNullable[com.infomofo.oroku.models.MetaInteger]
      )(TwitterUser.apply _)
    }

    implicit def jsonWritesOrokuTwitterUser: play.api.libs.json.Writes[TwitterUser] = {
      (
        (__ \ "username").write[scala.Option[com.infomofo.oroku.models.MetaString]] and
        (__ \ "id").write[scala.Option[com.infomofo.oroku.models.MetaInteger]]
      )(unlift(TwitterUser.unapply _))
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

        _executeRequest("GET", s"/page_info/info_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => Some(r.json.as[com.infomofo.oroku.models.PageInfo])
          case r if r.status == 404 => None
          case r => throw new FailedRequest(r)
        }
      }

      override def getOpenGraphMetadataForUrl(
        url: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.OpenGraphMetadata]] = {
        val queryParameters = Seq(
          Some("url" -> url)
        ).flatten

        _executeRequest("GET", s"/page_info/open_graph_metadata_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => Some(r.json.as[com.infomofo.oroku.models.OpenGraphMetadata])
          case r if r.status == 404 => None
          case r => throw new FailedRequest(r)
        }
      }

      override def getAppleItunesMetadataForUrl(
        url: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.AppleItunesMetadata]] = {
        val queryParameters = Seq(
          Some("url" -> url)
        ).flatten

        _executeRequest("GET", s"/page_info/apple_itunes_metadata_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => Some(r.json.as[com.infomofo.oroku.models.AppleItunesMetadata])
          case r if r.status == 404 => None
          case r => throw new FailedRequest(r)
        }
      }

      override def getSearchMetadataForUrl(
        url: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.SearchMetadata]] = {
        val queryParameters = Seq(
          Some("url" -> url)
        ).flatten

        _executeRequest("GET", s"/page_info/search_metadata_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => Some(r.json.as[com.infomofo.oroku.models.SearchMetadata])
          case r if r.status == 404 => None
          case r => throw new FailedRequest(r)
        }
      }

      override def getTwitterCardMetadataForUrl(
        url: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.TwitterCardMetadata]] = {
        val queryParameters = Seq(
          Some("url" -> url)
        ).flatten

        _executeRequest("GET", s"/page_info/twitter_card_metadata_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => Some(r.json.as[com.infomofo.oroku.models.TwitterCardMetadata])
          case r if r.status == 404 => None
          case r => throw new FailedRequest(r)
        }
      }

      override def getSchemaOrgItemsForUrl(
        url: String
      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[com.infomofo.oroku.models.SchemaOrgItem]] = {
        val queryParameters = Seq(
          Some("url" -> url)
        ).flatten

        _executeRequest("GET", s"/page_info/schema_org_items_for_url", queryParameters = queryParameters).map {
          case r if r.status == 200 => r.json.as[Seq[com.infomofo.oroku.models.SchemaOrgItem]]
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

    /**
     * shreds open graph metadata from a specified web page
     */
    def getOpenGraphMetadataForUrl(
      url: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.OpenGraphMetadata]]

    /**
     * shreds apple itunes metadata from a specified web page
     */
    def getAppleItunesMetadataForUrl(
      url: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.AppleItunesMetadata]]

    /**
     * shreds search metadata from a specified web page
     */
    def getSearchMetadataForUrl(
      url: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.SearchMetadata]]

    /**
     * shreds twitter card metadata from a specified web page
     */
    def getTwitterCardMetadataForUrl(
      url: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[scala.Option[com.infomofo.oroku.models.TwitterCardMetadata]]

    /**
     * shreds schema.org items from a specified web page
     */
    def getSchemaOrgItemsForUrl(
      url: String
    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[Seq[com.infomofo.oroku.models.SchemaOrgItem]]
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

    // Enum: OpenGraphType
    private val enumOpenGraphTypeNotFound = (key: String, e: Exception) => s"Unrecognized $key, should be one of ${OpenGraphType.all.mkString(", ")}"

    implicit val pathBindableEnumOpenGraphType = new PathBindable.Parsing[OpenGraphType] (
      OpenGraphType.fromString(_).get, _.toString, enumOpenGraphTypeNotFound
    )

    implicit val queryStringBindableEnumOpenGraphType = new QueryStringBindable.Parsing[OpenGraphType](
      OpenGraphType.fromString(_).get, _.toString, enumOpenGraphTypeNotFound
    )

    // Enum: TwitterCardType
    private val enumTwitterCardTypeNotFound = (key: String, e: Exception) => s"Unrecognized $key, should be one of ${TwitterCardType.all.mkString(", ")}"

    implicit val pathBindableEnumTwitterCardType = new PathBindable.Parsing[TwitterCardType] (
      TwitterCardType.fromString(_).get, _.toString, enumTwitterCardTypeNotFound
    )

    implicit val queryStringBindableEnumTwitterCardType = new QueryStringBindable.Parsing[TwitterCardType](
      TwitterCardType.fromString(_).get, _.toString, enumTwitterCardTypeNotFound
    )

  }

}