package com.kjetland.hoursregrest.client

import model.{Registration, Project}
import org.joda.time.DateTime
import parser.{SelectedDateParser, RegistrationParser, ProjectsParser}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 22:54:00
 * To change this template use File | Settings | File Templates.
 */

class Client(
        url : String,
        browser : Browser){

  var projects : List[Project] = List()
  var registrations : List[Registration] = List()
  var selectedDate : Option[DateTime] = None

  //retrieve html and store it so
  //we can parse it later

  var html : Option[String] = None
  //println(html)

  //init code...
  load()

  private def load(){

    //do we have html?
    html match {
      case None => html = browser.get( url )
      case Some(x) => None //do nothing - we allready have the html
    }
    parse()
  }

  private def parse(){
    html match{
      case None => None
      case Some( x ) => {
        projects = ProjectsParser.parse( x )
        registrations = RegistrationParser.parse(x)
        //before a date is selected manually, no selected date can be returned
        selectedDate = SelectedDateParser.parse(x)
      }
    }
  }


}