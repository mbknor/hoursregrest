package com.kjetland.hoursregrest.client.parser

import com.kjetland.hoursregrest.client.model.SelectedDate

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 20:28:58
 * To change this template use File | Settings | File Templates.
 */

trait DayLinkResolver{
  def dayLink(day : Int) : String
}

class FindDayLinkParser(html : String) extends Object with DayLinkResolver{

  def dayLink(day : Int) : String = {
     val p = ("""(?s).+<td align="center" width="14%"><a href="javascript:__doPostBack\('Calendar1','(\d+)'\)" style="color:Black" """ + "title=\"\\d+ \\w+\">"+day+"</a></td>.+").r
    //println("html: " + client.html)
    html match{
      case p(id) => return id;
      case _ => throw new RuntimeException("Cannot find day-id")
    }
  }
}