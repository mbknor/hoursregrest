package com.kjetland.hoursregrest.client.parser

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 28.feb.2010
 * Time: 16:32:27
 * To change this template use File | Settings | File Templates.
 */

class PrevNextMonthLinkIdResolver(html : String){

  def findNextMonthLinkId() : String = {

    val p = """(?s).+<a href="javascript:__doPostBack\('Calendar1','(.+)'\)" .+ title="Go to the next month">.+""".r
    html match{
      case p(id) => return id;
      case _ => throw new RuntimeException("Cannot find previous month link id")
    }
  }

  def findPrevMonthLinkId() : String = {

    val p = """(?s).+<a href="javascript:__doPostBack\('Calendar1','(.+)'\)" .+ title="Go to the previous month">.+""".r
    html match{
      case p(id) => return id;
      case _ => throw new RuntimeException("Cannot find previous month link id")
    }
  }
  
}