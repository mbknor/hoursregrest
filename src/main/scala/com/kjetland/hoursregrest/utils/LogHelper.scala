package com.kjetland.hoursregrest.utils

import org.apache.log4j.Logger

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 07.mar.2010
 * Time: 20:28:43
 * To change this template use File | Settings | File Templates.
 */

trait LogHelper{
  val loggerName = this.getClass.getName
  lazy protected val logger = Logger.getLogger(loggerName)
}