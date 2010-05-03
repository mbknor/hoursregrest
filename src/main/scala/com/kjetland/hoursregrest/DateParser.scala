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
    val datePatterns = List("yyyy.MM.dd", "dd.MM.yyyy")

    var date : DateMidnight = null

    datePatterns.foreach{ datePattern =>
      try{
        val p = DateTimeFormat.forPattern(datePattern)
        val d = p.parseDateTime(dateString).toDateMidnight
        if( date == null ){
          date = d
        }
      }catch{
        case unknown => {}
      }
    }
    if( date != null){
      return date
    }
    throw new Exception("Error parsing date '"+dateString+"'. Known formats: " + datePatterns)
  }
}