package com.kjetland.hoursregrest

import client.model.Project
import org.junit.Test
import java.lang.String

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 23:28:22
 * To change this template use File | Settings | File Templates.
 */

@Test
class RegistrationsFileParserTest{

  @Test
  def test(){

    val projectResolver = new ProjectResolver{
      def resolveProject(projectName: String) = new Project(1, projectName )
    }

    val projects = RegistrationsFileParser.parse( "src/test/resources/file.txt", projectResolver)

    projects.foreach{
      println( _ )
    }
  }

  @Test{ val expected = classOf[ Exception] }
  def testWithErrors(){

    val projectResolver = new ProjectResolver{
      def resolveProject(projectName: String) = new Project(1, projectName )
    }

    try{
      val projects = RegistrationsFileParser.parse( "src/test/resources/fileWithErrors.txt", projectResolver)
       projects.foreach{
        println( _ )
      }
    }catch{
      case unknown => throw new Exception(unknown)
    }
  }

}