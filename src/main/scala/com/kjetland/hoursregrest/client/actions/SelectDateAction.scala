package com.kjetland.hoursregrest.client.actions

import com.kjetland.hoursregrest.client.model.SelectedDate
import collection.mutable.ListBuffer
import com.kjetland.hoursregrest.client.{Client, FormElement}
import org.joda.time.{DateMidnight}
import com.kjetland.hoursregrest.client.parser.{PrevNextMonthLinkIdResolver, DayLinkResolver}

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
    val yearMonthCompare = new SelectedDate(date).compareYearMonth( sd )
    if( yearMonthCompare != 0 ){
      println("server date is on different year/month ("+sd+"). must change it")

      if( yearMonthCompare > 0 ){
        //destination date is in the future. must go to next month
        gotoNextMonth()
        //call this same method again and recheck
        selectDate( date )
        return

      }else{
        //destination date is in the past. must go to prev month
        gotoPrevMonth()
        //call this same method again and recheck
        selectDate( date )
        return
      }

    }

    //select day

    println( "must select date")

    selectDay( date.getDayOfMonth)

  }

  private def selectDay(day : Int){
    println("Selecting day: " + day)

    val dayId = dayLinkResolver.dayLink( day)
    println("dayId: " + dayId)

    performRemoteAction( dayId )

  }

  private def performRemoteAction(linkId : String){
    //create form to post
    val originalFormElements = client.formElements
    val formElements = new ListBuffer[FormElement]

    originalFormElements.foreach{
      formElements += _
    }


    formElements += new FormElement("__EVENTTARGET","Calendar1")
    formElements += new FormElement("__EVENTARGUMENT",linkId)
    formElements += new FormElement("__LASTFOCUS","")
    formElements += new FormElement("dlstProsjektAktivitet","-")

    client.browser.post(client.url, formElements.toList)

  }

  private def gotoNextMonth() {
    val linkId = new PrevNextMonthLinkIdResolver( client.html ).findNextMonthLinkId()
    performRemoteAction( linkId)
    //client must reparse to discovere the new selected date
    client.parse()
  }

  private def gotoPrevMonth(){
    val linkId = new PrevNextMonthLinkIdResolver( client.html ).findPrevMonthLinkId()
    performRemoteAction( linkId)
    //client must reparse to discovere the new selected date
    client.parse()
  }

}