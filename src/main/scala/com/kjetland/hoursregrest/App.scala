package com.kjetland.hoursregrest

import client.{Client, ClientImpl, Browser}
import java.util.Properties
import java.io.FileInputStream
import org.joda.time.{DateMidnight}

/**
 * Hello world!
 *
 */
object App{
  def main(args: Array[String]) {

    //System.getProperties().put("socksProxyHost", "localhost")

    val props = new Properties();
    props.load( new FileInputStream("ignoredFiles/settings.properties"))

    val url = props.getProperty("url")
    val username = props.getProperty("username")
    val password = props.getProperty("password")

    val browser = new Browser( username, password);


    val client : Client = new ClientImpl( url, browser )

    println("projects:")
    client.projects.foreach{
      println(_)
    }

    println("registrations:")
    client.registrations.foreach{
      println(_)
    }

    //println("html: " + client.html)
    println("selectedDate: " + client.selectedDate)

    /*
    println("form elements:")
    val formElements = FormParser.parse(client.html)
    formElements.foreach{
      println(_)
    }
    */

    val date = new DateMidnight(2010, 2, 23)
    client.selectDate(date )



    println("selectedDate: " + client.selectedDate)
    
//    val projects = client.getProjects
//    println("Projects: ")
//    projects.foreach( p => println( p ))


  }
}
