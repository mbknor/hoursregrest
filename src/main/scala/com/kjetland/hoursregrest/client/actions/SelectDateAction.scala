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

    //if no date is selected, just select the first day in current month
    if( client.selectedDate == None){
      println("no date is selected - select first day in current month")
      selectDay(date.getDayOfMonth)
      selectDate( date )
      return ;
    }

    //if date == current selected date, just return
    if( client.selectedDate == date){
      return
    }

    
    //if year and month does not match we must call server and change it

    //TODO: must be possible to find out which year and month is selected,
    //even though now day is selected.
    //solution: selectedDate returns SelectedDate-object instead, which
    //holds year, month and day, and a resolved date if possible
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