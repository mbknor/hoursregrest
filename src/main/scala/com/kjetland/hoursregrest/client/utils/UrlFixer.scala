package com.kjetland.hoursregrest.client.utils

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 20:14:56
 * To change this template use File | Settings | File Templates.
 */

/**
 * Object used to fix urls used to connect to the timereg-server.
 * Since I don't want to publish the correct url into this source, since it is opensource,
 * this fixer is used to accept many forms of the url, and "fix" it into the correct one.
 */
object UrlFixer{

  private val scriptName = "wfrmTimeRegistrering.aspx"

  def fixUrl( _url : String ) : String = {
    var url = _url.toLowerCase
    if( !url.startsWith("http://")) url = "http://" + url

    if( url.endsWith( "/timereg/" ) ) return url + scriptName
    if( url.endsWith( "/timereg" ) ) return url + "/" + scriptName

    //use url as is
    return url
  }
}