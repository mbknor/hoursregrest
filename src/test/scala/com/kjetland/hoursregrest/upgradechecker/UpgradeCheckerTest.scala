package com.kjetland.hoursregrest.upgradechecker

import org.junit.{Assert, Test}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 27.feb.2010
 * Time: 22:42:15
 * To change this template use File | Settings | File Templates.
 */

@Test
class UpgradeCheckerTest{

  @Test
  def test(){
    UpgradeChecker.check();
    UpgradeChecker.currentVersion = "X"
    Assert.assertTrue(UpgradeChecker.check());
  }
}