package com.kjetland.hoursregrest.client.parser

import com.kjetland.hoursregrest.client.model.Project
import org.junit.{Assert, Test}


/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 23:35:41
 * To change this template use File | Settings | File Templates.
 */

@Test
class ProjectParserTest{

  @Test
  def test(){
    val html = """<TD style="WIDTH: 365px" colSpan="2">
							<select name="dlstProsjektAktivitet" id="dlstProsjektAktivitet">
			<option selected="selected" value="-">-</option>
			<option value="1">aaa</option>
			<option value="2">bbb</option>
			<option value="3">ccc</option>
			<option value="4">ddd</option>

		</select></TD>"""

    val fasit = List(
            new Project(1, "aaa"),
            new Project(2, "bbb"),
            new Project(3, "ccc"),
            new Project(4, "ddd"))

    Assert.assertEquals(fasit, ProjectsParser.parse( html ) )

  }

  @Test
  def testNotFound(){
    val html = """<TD style="WIDTH: 365px" colSpan="2">
							<select name="dlst___XXXX__ProsjektAktivitet" id="dlstProsjektAktivitet">
			<option selected="selected" value="-">-</option>
			<option value="1">aaa</option>
			<option value="2">bbb</option>
			<option value="3">ccc</option>
			<option value="4">ddd</option>

		</select></TD>"""


    Assert.assertTrue(ProjectsParser.parse( html ).isEmpty )

  }
}