package com.kjetland.hoursregrest.client

import actions.SelectDateAction
import model.{SelectedDate, Registration, Project}
import parser._
import org.joda.time.{DateMidnight}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 22:54:00
 * To change this template use File | Settings | File Templates.
 */

trait Client{
  def url : String
  def browser : Browser
  def projects : List[Project]
  def registrations : List[Registration]
  def selectedDate : SelectedDate
  def formElements : List[FormElement]
  def html : String
  def selectDate(date : DateMidnight)

}


class ClientImpl(
        var url : String,
        var browser : Browser) extends Object with Client{

  var projects : List[Project] = List()
  var registrations : List[Registration] = List()
  var selectedDate : SelectedDate = null
  var formElements : List[FormElement] = List()



  //retrieve html and store it so
  //we can parse it later


  //init code...
  load()

  private def load(){

    //do we have html?
    if( browser.html.isEmpty ){
      browser.get( url )
    }
    parse()
  }

  private def parse(){
    if( !browser.html.isEmpty){
      val html = browser.html.get
      projects = ProjectsParser.parse( html )
      registrations = RegistrationParser.parse(html)
      //before a date is selected manually, no selected date can be returned
      selectedDate = SelectedDateParser.parse(html)

      //must get hold of form-elements - need them when posting
      formElements = FormParser.parse(html)
      
    }
  }

  def html : String = {
    browser.html match{
      case Some(html) => return html
      case None => throw new RuntimeException("No html pressent")
    }

  }

  def selectDate(date : DateMidnight){
    val action = new SelectDateAction( this, new FindDayLinkParser( html) )
    action.selectDate( date )
    //must parse resulting page
    parse
  }
 
}