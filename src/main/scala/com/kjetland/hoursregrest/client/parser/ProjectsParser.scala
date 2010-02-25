package com.kjetland.hoursregrest.client.parser

import com.kjetland.hoursregrest.client.model.Project
/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 23:32:54
 * To change this template use File | Settings | File Templates.
 */

object ProjectsParser extends BaseListParser[Project]{

  def findMainPartRegex = """(?s).+<select name="dlstProsjektAktivitet" id="dlstProsjektAktivitet">(.+)</select>.+""".r

  def parsePart(part : String) : Option[Project] = {

    val parseOption = """<option value="(\d+)">(.+)</option>""".r


    part match {
            case parseOption( id, name) => {
              //println("id: " + id + " name: " + name)
              return Some( new Project(id.toInt, name.trim) )
            }
            case none => return None
          }
  }
}