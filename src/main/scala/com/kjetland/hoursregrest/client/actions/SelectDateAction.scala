package com.kjetland.hoursregrest.client.actions

import com.kjetland.hoursregrest.client.model.SelectedDate
import collection.mutable.ListBuffer
import com.kjetland.hoursregrest.client.{Client, FormElement}
import org.joda.time.{DateMidnight}
import com.kjetland.hoursregrest.client.parser.DayLinkResolver

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 16.feb.2010
 * Time: 23:43:54
 * To change this template use File | Settings | File Templates.
 */

class SelectDateAction(client : Client, dayLinkResolver : DayLinkResolver){

  def selectDate(date : DateMidnight) {

    val sd = client.selectedDate
    //if date == current selected date, just return
    if( sd.date != null && sd.date == date){
      return
    }

    
    //if year and month does not match we must call server and change it
    if( !(sd.year == date.getYear && sd.month == date.getMonthOfYear) ){
      throw new Exception("year and/or month does not match - handling not implemented yet")
    }

    //select day

    println( "must select date")

    selectDay( date.getDayOfMonth)

  }

  private def selectDay(day : Int){
    println("Selecting day: " + day)

    val dayId = dayLinkResolver.dayLink( day)
    println("dayId: " + dayId)

    //create form to post
    val originalFormElements = client.formElements
    val formElements = new ListBuffer[FormElement]

    originalFormElements.foreach{
      formElements += _
    }

    
    formElements += new FormElement("__EVENTTARGET","Calendar1")
    formElements += new FormElement("__EVENTARGUMENT",dayId)
    formElements += new FormElement("__LASTFOCUS","")
    formElements += new FormElement("dlstProsjektAktivitet","-")

    client.browser.post(client.url, formElements.toList)

    //( client.browser.html)


  }
}