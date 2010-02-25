package com.kjetland.hoursregrest.client.parser

import collection.mutable.ListBuffer

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 11.feb.2010
 * Time: 21:54:02
 * To change this template use File | Settings | File Templates.
 */

abstract class BaseListParser[T] extends BaseParser {

  protected def partSplitter = """\r?\n"""

  def parse(html: String): List[T] = {

    val _partSplitter = this.partSplitter

    val list = new ListBuffer[T]

    parseMainPart( html ) match {
      case Some( mainPart ) => {

        //println("mainPart: \n"+mainPart)

        val part = mainPart.split( _partSplitter )
        part.foreach( p => {

          parsePart( p.trim ) match {
            case Some(x) => list += x
            case None => false
          }
          
        })

      }
      case _ => None
    }

    list.toList;
  }

  protected def parsePart(part : String) : Option[T]

}