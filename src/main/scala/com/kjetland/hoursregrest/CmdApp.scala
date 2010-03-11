package com.kjetland.hoursregrest

import client.model.Project
import client.{Client, Browser, ClientImpl}
import org.joda.time.format.DateTimeFormat
import java.util.Properties
import org.joda.time.DateMidnight
import upgradechecker.UpgradeChecker
import java.io.{File, FileInputStream}
import org.apache.log4j.Logger
import org.apache.log4j.xml.DOMConfigurator
import utils.LogHelper

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 23:31:32
 * To change this template use File | Settings | File Templates.
 */

class ArgException(desc: String, cause: Throwable) extends Exception(desc, cause) {
  def this(desc: String) {
    this (desc, null)
  }
}



object CmdApp extends LogHelper {
  private def logEnvironemntInfo {
    if (logger.isDebugEnabled) {
      logger.debug("Environment list:")
      System.getenv.keySet.toArray.foreach {
        x =>
          logger.debug("" + x + "=" + System.getenv(x.toString))
      }
    }
  }

  def main(_args: Array[String]) {

    var argsLeft = configureLogging(_args)

    logEnvironemntInfo

    UpgradeChecker.check()

    try {


      var url = ""
      var username = ""
      var password = ""

      argsLeft match {
        case "-help" :: rest => printHelp; System.exit(0);
        case "-url" :: _url :: "-username" :: _username :: "-password" :: _password :: rest => {
          url = _url
          username = _username
          password = _password
          argsLeft = rest
        }
        case _ => {
          val settings = new SettingsFile()
          url = settings.url
          username = settings.username
          password = settings.password
        }
      }


      val browser = new Browser(username, password)
      val client = new ClientImpl(url, browser)

      //must find the cmd param
      argsLeft match {
        case "-c" :: "lp" :: rest => {
          val projects = client.projects
          logger.info("Projects:")
          projects.foreach {
            logger.info(_)
          }
        }
        case "-c" :: "lr" :: rest => {
          val regs = client.registrations
          logger.info("Registrations:")
          regs.foreach {
            logger.info(_)
          }
        }
        case "-c" :: "add" :: "-d" :: _date :: "-p" :: _projectName :: "-h" :: _hours :: "-t" :: _desc :: rest => {
          val registration = new Registration
          registration.date = DateParser.parseDate(_date)
          registration.project = ProjectResolverObject.resolveProject(_projectName, client)
          registration.hours = HoursParser.parseHours(_hours)
          registration.description = _desc

          //verify that we have it all
          logger.info("Going to register: " + registration)

          client.selectDate(registration.date)
          //validate the date
          if (client.selectedDate.date != registration.date) {
            throw new ArgException("Failed setting the date: " + registration.date)
          }

          //add the registration
          client.addRegistration(registration.project, registration.hours, registration.description)

          logger.info("Registration completed.")
          logger.info("* Now you can go to the website and verify the registrations,\n* then confirm them.")

        }

        case _ => {
          logger.info("Argument error")

          printHelp
          System.exit(-1)
        }

      }

      System.exit(0)

    } catch {
      case e: ArgException => {
        if (logger.isDebugEnabled) {
          logger.info("Argument error", e)
        } else {
          logger.info("Argument error: " + e.getMessage)
        }
        printHelp
        System.exit(-1)
      }
      case unknown => {
        logger.error("", unknown);
        System.exit(-1)
      }
    }

    printHelp
    System.exit(0)


  }


  def printHelp {
    logger.info("""params:
url, username and password:
-url <url to timereg-website>
-username <username> (without domain name)
-password <password>
(skip these settings to load url, user and password from hoursreg.properties)

-help   prints this help screen

-c <command-name>
   commands:
   lp        list projects
   lr        list registrations
   add       add registration(*) (has params)
        params:
        -d <date> (format: yyyy.mm.dd)
        -p <projectId or unique-part-of-project-name>
        -h <hours>
        -t <description>
   add-file  add registrations from file(*)
        params:
        -f filename to import (see exampleRegistrations.txt)

-debug  logs additional info to a logfile

* - Note! This program only adds the registrations.
    You have to go to the website and verify the registrations,
    then confirm them manually.
""")
  }

  class SettingsFile {
    private val settingsFilename = "hoursreg.properties"

    var url = ""
    var username = ""
    var password = ""

    load()

    private def load() {
      logger.info("loading " + settingsFilename)
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
      if (username == "<specify>") throw new ArgException("You must edit the file " + settingsFilename)

      password = props.getProperty("password")
      if (password.length == 0) throw new Exception("error loading 'password' from " + settingsFilename)
      if (password == "<specify>") throw new ArgException("You must edit the file " + settingsFilename)

    }

  }

  /**
   * if one of the args to this program is "-debug", then a different
   * log4j.xml file is used to set up the logging.
   */
  private def configureLogging(_args: Array[String]) : List[String] = {

    //check for the -debug arg
    var usingDebug = false

    var argsLeft = _args.toList

    argsLeft match{
      case "-debug" :: rest => {
        usingDebug = true
        argsLeft = rest
      }
      case _ => None
    }

    if (!usingDebug) {
      return argsLeft
    }

    val filename = "debug_log4j.xml"
    val url = this.getClass.getClassLoader.getResource(filename)
    DOMConfigurator.configure(url);
    logger.info("Runing program in debug mode. Extra debug info is written to logfile.")

    return argsLeft
  }
}