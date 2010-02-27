package com.kjetland.hoursregrest

import client.model.Project
import org.joda.time.DateMidnight

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 23:40:01
 * To change this template use File | Settings | File Templates.
 */

class Registration{
  var date : DateMidnight = null
  var project : Project = null
  var hours : Double = 0
  var description : String = null


  override def toString = "date: " + date + " project: " + project + " hours: " + hours + " desc: " + description
}