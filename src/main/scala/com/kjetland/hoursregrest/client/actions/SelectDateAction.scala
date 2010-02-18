package com.kjetland.hoursregrest.client.actions

import com.kjetland.hoursregrest.client.parser.FormElement
import org.joda.time.DateTime
import com.kjetland.hoursregrest.client.{Client, Browser}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 16.feb.2010
 * Time: 23:43:54
 * To change this template use File | Settings | File Templates.
 */

class SelectDateAction(client : Client, formElements : List[FormElement]){

  def selectDate(date : DateTime) {

    val sd = client.selectedDate
    //if date == current selected date, just return
    if( sd == date){
      return
    }

    
    //if year and month does not match we must call server and change it
    if( !(sd.year == date.getYear && sd.month == date.getMonthOfYear) ){
      throw new Exception("year and/or month does not match - handling not implemented yet")
    }


    val currentYearMonth : DateTime = null

    val yearMonthCompare = compareYearAndMonth(date,  currentYearMonth)

    if( yearMonthCompare != 0 ){
      //must change month and year before we can select day
      throw new RuntimeException("month and year select not done yet")
    }

    //select day

    println( "must select date")


    
  }

  private def selectDay(day : Int){
    println("Selecting day: " + day)
    throw new RuntimeException("Not implemented yet")
  }

  private def compareYearAndMonth( d1 : DateTime, d2 : DateTime) : Int ={
    if( d1.getYear < d2.getYear) return -1
    if( d1.getYear > d2.getYear) return 1

    if( d1.getMonthOfYear < d2.getMonthOfYear) return -1
    if( d1.getMonthOfYear > d2.getMonthOfYear) return 1

    return 0
  }
}