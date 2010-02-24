package com.kjetland.hoursregrest.client.parser

import com.kjetland.hoursregrest.client.FormElement
import java.lang.String

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 16.feb.2010
 * Time: 22:21:02
 * To change this template use File | Settings | File Templates.
 */



object FormParser extends BaseListParser[FormElement] {
  def findMainPartRegex = """(?s)(.+)""".r

  def parsePart(part: String): Option[FormElement] = {

    val parseInput = """.*<input ([^>]+)>.*""".r
    val parseName = """.*name="([^"]+)".*""".r
    val parseValue = """.*value="([^"]+)".*""".r
    val parseType = """.*type="([^"]+)".*""".r


    part match {
      case parseInput(x) => {
        //println("X: " + x)

        x match {
          case parseName(name) => {

            var value = ""

            var typeString = ""
            x match {
              case parseType(ts) => typeString = ts
              case _ => None
            }

            x match {
              case parseValue(_value) => {
                value = _value
              }
              case none => None
            }

            //we only need it if not type = submit
            if (!typeString.equalsIgnoreCase("submit")) {
              return Some(new FormElement(name, value))
            }

          }
          case none => return None
        }


        return None
      }
      case none => return None
    }
  }

}