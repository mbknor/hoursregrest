package com.kjetland.hoursregrest

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 28.feb.2010
 * Time: 00:25:52
 * To change this template use File | Settings | File Templates.
 */

object HoursParser{

  def parseHours(s : String) : Double = {
    var hours = 0.0
    try{
      hours = s.replace(",", ".").toDouble
    }catch{
      case unknwon => throw new Exception("Error parsing hours '"+s+"'")
    }

    if( hours <= 0 ){
      throw new Exception("hours must be > 0. was: " + hours)
    }
    return hours;
  }
}