package com.kjetland.hoursregrest.client

import actions.SelectDateAction
import model.{SelectedDate, Registration, Project}
import org.joda.time.DateTime
import parser._

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 22:54:00
 * To change this template use File | Settings | File Templates.
 */

class Client(
        var url : String,
        var browser : Browser){

  var projects : List[Project] = List()
  var registrations : List[Registration] = List()
  var selectedDate : SelectedDate = null
  private var formElements : List[FormElement] = List()

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

  def selectDate(date : DateTime){
    val action = new SelectDateAction( this )
    action.selectDate( date )
    //must parse resulting page
    parse
  }


}