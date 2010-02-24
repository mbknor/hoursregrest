package com.kjetland.hoursregrest.client.actions

import org.joda.time.DateTime
import com.kjetland.hoursregrest.client.{FormElement, Client, Browser}
import com.kjetland.hoursregrest.client.model.SelectedDate
import com.kjetland.hoursregrest.client.parser.FormParser
import collection.jcl.ArrayList
import collection.mutable.ListBuffer

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 16.feb.2010
 * Time: 23:43:54
 * To change this template use File | Settings | File Templates.
 */

class SelectDateAction(client : Client){

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

    //select day

    println( "must select date")

    selectDay( date.getDayOfMonth, sd )

  }


  private def findDayLink(day : Int, sd : SelectedDate) : String ={
    val p = ("""(?s).+<td align="center" width="14%"><a href="javascript:__doPostBack\('Calendar1','(\d+)'\)" style="color:Black" """ + "title=\"\\d+ \\w+\">"+day+"</a></td>.+").r
    //println("html: " + client.html)
    client.html match{
      case p(id) => return id;
      case _ => throw new RuntimeException("Cannot find day-id")
    }
  }

  private def selectDay(day : Int, sd : SelectedDate){
    println("Selecting day: " + day)

    val dayId = findDayLink( day, sd)
    println("dayId: " + dayId)

    //create form to post
    val originalFormElements = FormParser.parse(client.html)
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