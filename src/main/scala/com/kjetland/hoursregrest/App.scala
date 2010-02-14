package com.kjetland.hoursregrest

import client.{Client, Browser}
import xml.XML
import java.util.Properties
import java.io.FileInputStream

/**
 * Hello world!
 *
 */
object App{
  def main(args: Array[String]) {

    val props = new Properties();
    props.load( new FileInputStream("ignoredFiles/settings.properties"))

    val url = props.getProperty("url")
    val username = props.getProperty("username")
    val password = props.getProperty("password")

    val browser = new Browser( username, password);


    val client = new Client( url, browser )

    println("projects:")
    client.projects.get.foreach{
      println(_)
    }

    
//    val projects = client.getProjects
//    println("Projects: ")
//    projects.foreach( p => println( p ))


  }
}
