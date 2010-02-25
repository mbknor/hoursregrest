package com.kjetland.hoursregrest

import client.model.Project
import client.{Client, Browser, ClientImpl}
import org.joda.time.format.DateTimeFormat

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 23:31:32
 * To change this template use File | Settings | File Templates.
 */

class ArgException(desc: String) extends Exception(desc)

object CmdApp {
  def main(args: Array[String]) {



    //must parse args
    try {

      if (args.length >= 1) {
        if (args(0) == "-h") {
          printHelp
          System.exit(0)
        }
      }

      if (args.length < 4) {
        throw new ArgException("Not enough params")
      }

      val url = args(0)
      val username = args(1)
      val password = args(2)

      val browser = new Browser(username, password)
      val client = new ClientImpl(url, browser)

      val command = args(3)

      command match {
        case "lp" => {
          val projects = client.projects
          println("Projects:")
          projects.foreach {
            println(_)
          }
        }

        case "lr" => {
          val regs = client.registrations
          println("Registrations:")
          regs.foreach {
            println(_)
          }
        }

        case "ar" => {
          if (args.length != 8) {
            throw new ArgException("wrong number of args")
          }

          val date = DateTimeFormat.forPattern("yyyy.MM.dd").parseDateTime(args(4)).toDateMidnight
          val projectName = args(5)
          val hours = args(6).replace(",", ".").toDouble
          val desc = args(7)

          val project = resolveProject(projectName, client)

          println( "project: " + projectName + " => " + project)

          client.selectDate( date )
          //validate the date
          if( client.selectedDate.date != date ){
            throw new ArgException("Failed setting the date")
          }

          //add the registration
          client.addRegistration( project, hours, desc)

        }

        case _ => throw new ArgException("invalid command: " + command)
      }

      System.exit(0)
    } catch {
      case e: ArgException => {
        println("Argument error: " + e.getMessage)
        printHelp
        System.exit(-1)
      }
      case unknown => {
        unknown.printStackTrace;
        System.exit(-1)
      }
    }

    printHelp
    System.exit(0)


  }


  def resolveProject(project: String, client: Client): Project = {

    var _project = project.toLowerCase
    var selectedProject : Project = null
    client.projects.foreach { p =>
      var _p : Project = null
      if( p.id.toString == _project){
        _p = p
      }else if( p.name.toLowerCase.contains(_project) ){
        _p = p
      }

      if( _p != null ){
        if( selectedProject != null ){
          throw new ArgException("project '"+project+"' is not unique");
        }
        selectedProject = _p
      }

    }

    if( selectedProject != null) return selectedProject

    throw new ArgException("Unable to resolve projectId from: " + project)

  }

  def printHelp {
    println("""params:
-h
url username password command [command-options]

commands:
  lp   - list projects
  lr   - list registrations
  ar   - add registration
         Options:
           date (eg: 2010.02.01)
           projectId/unique-part-of-name
           hours
           description

""")
  }
}