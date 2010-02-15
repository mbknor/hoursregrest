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

  //retrieve html and store it so
  //we can parse it later

  val html = browser.get( url );
  //println(html)

  def projects : Option[List[Project]] = {
    html match{
      case None => None
      case Some( x ) => {
        Some( ProjectsParser.parse( x ) )
      }
    }
  }

  def registrations : Option[List[Registration]] = {
    html match {
      case None => None
      case Some( x ) => {
        Some( RegistrationParser.parse(x))
      }
    }
  }

  /**
   * before a date is selected manually, no selected date can be returned
   */
  def selectedDate : Option[DateTime] = {
    html match {
      case None => None
      case Some( x ) => {
        SelectedDateParser.parse(x)
      }
    }
  }

}