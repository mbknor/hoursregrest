package com.kjetland.hoursregrest.upgradechecker

import com.kjetland.hoursregrest.client.Browser
import com.kjetland.hoursregrest.utils.LogHelper

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 22:18:58
 * To change this template use File | Settings | File Templates.
 */

/**
 * Class that  tries to get a specific file from github
 * This file contains a version-string.
 * if this version-string is larger than
 * the string pressent in the code, then
 * the user is alerted that a newer version of this program is pressent for download.
 * 
 */

object UpgradeChecker extends LogHelper{
  private val versionUrl = "http://github.com/mbknor/hoursregrest/raw/master/latestReleaseVersion.txt"

  var currentVersion = "1.1"

  def check() : Boolean = {

    logger.debug("checking for online for newer version")

    val remoteVersion = getRemoteVersion()
    if( remoteVersion != null ){
      //is remote version newer?
      if( remoteVersion != currentVersion){
        logger.info("""**
A new version of this program is ready to be downloaded from:
http://github.com/mbknor/hoursregrest/downloads
**""")
        return true
      }else{
        return false
      }
    }else{
      return false
    }
  }

  private def getRemoteVersion() : String = {
    val browser = new Browser();

    try{
      logger.debug("upgrade check url: " + versionUrl)
      val remoteString = browser.get( versionUrl ).get

      logger.debug("remoteString: " + remoteString)

      //parse it
      val findIt = "(?s).+\ncurrentVersion=(.+)\r?\n?.*".r

      remoteString match {
        case findIt(x) => return x
        case _ => None
      }
    }catch{
      case unknown => logger.warn("(Error checking for upgrade("+unknown.getMessage+"))");
    }

    return null
  }

}