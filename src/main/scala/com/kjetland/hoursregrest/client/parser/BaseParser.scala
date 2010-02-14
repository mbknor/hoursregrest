package com.kjetland.hoursregrest.client.parser

import util.matching.Regex

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 14.feb.2010
 * Time: 20:42:21
 * To change this template use File | Settings | File Templates.
 */

abstract class BaseParser{
  protected def findMainPartRegex : Regex

  protected def parseMainPart(html: String): Option[String] = {

    val _findMainPartRegex = this.findMainPartRegex

    html match {
      case _findMainPartRegex (_sc) => {
        Some(_sc.trim)
     }
      case _ => None
    }

  }

}