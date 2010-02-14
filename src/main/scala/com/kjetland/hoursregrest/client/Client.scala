package com.kjetland.hoursregrest.client

import model.Project
import parser.ProjectsParser


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

  def projects : Option[List[Project]] = {
    html match{
      case None => None
      case Some( x ) => {
        Some( ProjectsParser.parse( x ) )
      }
    }
  }

}