package com.kjetland.hoursregrest.client.model

import org.junit.{Assert, Test}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 10.feb.2010
 * Time: 20:53:56
 * To change this template use File | Settings | File Templates.
 */

@Test
class ProjectTest{

  @Test
  def testEquals {
    val p1 = new Project(1,"p1");
    val p2 = new Project(1,"p1");
    Assert.assertEquals(p1, p2)
  }
}