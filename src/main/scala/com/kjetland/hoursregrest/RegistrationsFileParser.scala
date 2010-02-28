package com.kjetland.hoursregrest

import client.Client
import client.model.Project
import java.io.{BufferedReader, FileReader}
import collection.mutable.ListBuffer

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 23:17:19
 * To change this template use File | Settings | File Templates.
 */


object RegistrationsFileParser{

  def parse(filename : String, projectResolver : ProjectResolver): List[Registration] = {

    println("Parsing " + filename)

    var in : BufferedReader = null
    try{
      in = new BufferedReader( new FileReader(filename) )
      return parse( in, projectResolver )
    }catch{
      case unknown => throw new Exception("Error parsing file " + filename + " (error: "+unknown+")", unknown)
    }finally{
      try{
        in.close
      }catch{
        case unknown => None
      }
    }
  }

  def parse(in : BufferedReader, projectResolver : ProjectResolver): List[Registration] = {
    var line = ""
    var lineNo = 0

    val list = new ListBuffer[Registration]

    while( {line = in.readLine(); line != null} ){
      lineNo = lineNo + 1
      line = line.trim
      try{
        //println("line: " + line)
        if( !(line.startsWith("#") || line.isEmpty) ){
          val r = new Registration

          var parts =line.split(";|\t")
          if( parts.length != 4) throw new ArgException("Column-count is not 4 (is: "+parts.length+")")

          r.date = DateParser.parseDate( parts(0).trim)
          r.project = projectResolver.resolveProject( parts(1).trim)
          r.hours = HoursParser.parseHours( parts(2).trim )
          r.description = parts(3).trim

          r.validate

          list += r
        }
      }catch{
        case unknown : Throwable => throw new ArgException("Error parsing file on line " + lineNo, unknown)
      }

    }

    return list.toList

  }


}