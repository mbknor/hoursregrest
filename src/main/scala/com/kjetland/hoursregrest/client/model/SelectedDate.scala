package com.kjetland.hoursregrest.client.model

import org.joda.time.DateTime

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 19.feb.2010
 * Time: 00:38:54
 * To change this template use File | Settings | File Templates.
 */

class SelectedDate(val year:Int, val month:Int){

  private var day: Option[Int] = None
  private var _date : DateTime = null

  def this(date:DateTime){
    this(date.getYear, date.getMonthOfYear)
    setDay( date.getDayOfMonth)
  }
  

  def setDay(_day:Int){
    this.day = Some(_day)
    this._date = new DateTime(year, month, _day, 0,0,0,0)
  }

  def date: DateTime = {
    return _date
  }


  override def equals(obj: Any) = {
    obj match{
      case o:SelectedDate => year==o.year && month==o.month && day == o.day
      case _ => false
    }
  }

    /**
   * workaround:
   * I have problems parsing full month names with jodatime.
   * This method returns the monthnumber for the monthname
   */
  protected def monthNameToNumber( name : String) : Int ={
    name.toLowerCase match{
      case "january" => 1
      case "february" => 2
      case "march" => 3
      case "april" => 4
      case "may" => 5
      case "june" => 6
      case "july" => 7
      case "august" => 8
      case "september" => 9
      case "october" => 10
      case "november" => 11
      case "december" => 12
      case _ => -1
    }
  }


  override def toString = "year: "+year+" month: " + month + "  day: " + day + " date: " + date
}
