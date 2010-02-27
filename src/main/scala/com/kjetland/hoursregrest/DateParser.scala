package com.kjetland.hoursregrest

import org.joda.time.format.DateTimeFormat
import org.joda.time.DateMidnight

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 28.feb.2010
 * Time: 00:23:32
 * To change this template use File | Settings | File Templates.
 */

object DateParser{
    def parseDate(dateString : String ) : DateMidnight = {
    val datePattern = "yyyy.MM.dd"
    try{
      return DateTimeFormat.forPattern(datePattern).parseDateTime(dateString).toDateMidnight
    }catch{
      case unknown => throw new Exception("Error parsing date '"+dateString+"'. Known formats: " + datePattern)
    }
  }
}