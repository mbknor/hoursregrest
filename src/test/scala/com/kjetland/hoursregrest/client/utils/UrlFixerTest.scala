package com.kjetland.hoursregrest.client.utils

import org.junit.Test
import org.junit.Assert._

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 20:25:16
 * To change this template use File | Settings | File Templates.
 */

@Test
class UrlFixerTest{

  @Test
  def test(){
    val f = "http://example.com/timereg/wfrmTimeRegistrering.aspx"
    assertEquals(f.toLowerCase, UrlFixer.fixUrl("http://example.com/timereg/wfrmTimeRegistrering.aspx").toLowerCase)
    assertEquals(f.toLowerCase, UrlFixer.fixUrl("example.com/timereg/wfrmTimeRegistrering.aspx").toLowerCase)
    assertEquals(f.toLowerCase, UrlFixer.fixUrl("example.com/timereg/").toLowerCase)
    assertEquals(f.toLowerCase, UrlFixer.fixUrl("example.COM/timeReg").toLowerCase)
    val someOtherUrl = "http://abc.com/a/b"
    assertEquals(someOtherUrl, UrlFixer.fixUrl(someOtherUrl))
  }
}