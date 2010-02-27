package com.kjetland.hoursregrest

import client.model.Project
import client.{Client, Browser, ClientImpl}
import org.joda.time.format.DateTimeFormat
import java.util.Properties
import java.io.FileInputStream
import org.joda.time.DateMidnight
import upgradechecker.UpgradeChecker

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 23:31:32
 * To change this template use File | Settings | File Templates.
 */

class ArgException(desc: String, cause : Throwable) extends Exception(desc, cause){
  def this( desc : String ){
    this(desc, null)
  }
}

/**
 * convenience class uses to "pop" one settings after another
 */
class Args(args: Array[String]) {
  private var index = 0

  def more: Boolean = {
    return (index < args.length)
  }

  def reset() = index = 0

  def pop(): String = {
    if (!more) throw new ArgException("Missing param")

    val arg = args(index)
    index = index + 1
    return arg
  }
}

object CmdApp {
  def main(_args: Array[String]) {

    UpgradeChecker.check()

    val args = new Args(_args)

    try {

      //check is user wants to print helpscreen
      while (args.more) {
        args.pop() match {
          case "-help" => printHelp; System.exit(0);
          case _ => None
        }
      }



      var url = ""
      var username = ""
      var password = ""

      var used_urlUserPass_params = false
      args.reset()
      while (args.more) {
        val arg = args.pop()
        arg match {
          case "-url" => url = args.pop(); used_urlUserPass_params = true
          case "-username" => username = args.pop(); used_urlUserPass_params = true
          case "-password" => password = args.pop(); used_urlUserPass_params = true
          case _ => None
        }
      }

      if (!used_urlUserPass_params) {
        val settings = new SettingsFile()
        url = settings.url
        username = settings.username
        password = settings.password
      }


      val browser = new Browser(username, password)
      val client = new ClientImpl(url, browser)

      //must find the cmd param
      var foundCommand = false
      args.reset()
      while (args.more) {
        val arg = args.pop
        arg match {
          case "-c" => {
            val cmdName = args.pop
            cmdName match {
              case "lp" => {
                foundCommand = true
                val projects = client.projects
                println("Projects:")
                projects.foreach {
                  println(_)
                }
              }

              case "lr" => {
                foundCommand = true
                val regs = client.registrations
                println("Registrations:")
                regs.foreach {
                  println(_)
                }
              }

              case "add" => {
                foundCommand = true

                var date : DateMidnight = null
                var project : Project = null
                var hours : Double= 0
                var desc : String = null

                //must find all params for this command

                var tmp = ""
                while( args.more ){
                  args.pop() match {
                    case "-d" => date = DateParser.parseDate( args.pop() )
                    case "-p" => {
                      val projectName = args.pop()
                      project = ProjectResolverObject.resolveProject( projectName, client)
                      println("project: " + projectName + " => " + project)
                    }
                    case "-h" => hours = HoursParser.parseHours( args.pop())
                    case "-t" => desc = args.pop()
                  }
                }

                //verify that we have it all

                if( date == null ) throw new ArgException("missing date")
                if( project == null ) throw new ArgException("missing project")
                if( hours <= 0 ) throw new ArgException("missing hours")
                if( desc == null ) throw new ArgException("missing description")


                client.selectDate(date)
                //validate the date
                if (client.selectedDate.date != date) {
                  throw new ArgException("Failed setting the date")
                }

                //add the registration
                client.addRegistration(project, hours, desc)


                }

              case _ => throw new ArgException("invalid command: " + arg)

              }


            }

            case _ => None

          }
        }
  


      if (!foundCommand) {
        println("Error: Did not find command.")
        printHelp
        System.exit(-1)
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




  def printHelp {
    println("""params:
url, username and password:
-url <url to timereg-website>
-username <username>
-password <password>
(skip these settings to load url, user and password from hoursreg.properties)

-help   prints this help screen

-c <command-name>
   commands:
   lp        list projects
   lr        list registrations
   add       add registration (has params)
        params:
        -d <date> (format: yyyy.mm.dd)
        -p <projectId or unique-part-of-project-name>
        -h <hours>
        -t <description>
   add-file  add registrations from file
        params:
        -f filename to import (see exampleRegistrations.txt)
""")
  }

  class SettingsFile {
    private val settingsFilename = "hoursreg.properties"

    var url = ""
    var username = ""
    var password = ""

    load()

    private def load() {
      println("loading " + settingsFilename)
      val props = new Properties()
      try {
        props.load(new FileInputStream(settingsFilename))
      } catch {
        case unknown => throw new Exception("Error loading " + settingsFilename, unknown)
      }

      url = props.getProperty("url")
      if (url.length == 0) throw new Exception("error loading 'url' from " + settingsFilename)

      username = props.getProperty("username")
      if (username.length == 0) throw new Exception("error loading 'username' from " + settingsFilename)
      if( username == "<specify>") throw new ArgException("You must edit the file " + settingsFilename)

      password = props.getProperty("password")
      if (password.length == 0) throw new Exception("error loading 'password' from " + settingsFilename)
      if( password == "<specify>") throw new ArgException("You must edit the file " + settingsFilename)

    }

  }
}