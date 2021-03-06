package com.kjetland.hoursregrest.client.parser

import exception.ParseException
import com.kjetland.hoursregrest.client.model.SelectedDate

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 14.feb.2010
 * Time: 20:38:15
 * To change this template use File | Settings | File Templates.
 */

/*
<TD style="WIDTH: 365px" vAlign="top">
							<table id="Calendar1" cellspacing="0" cellpadding="2" title="Calendar" border="0" style="border-width:1px;border-style:solid;border-collapse:collapse;">

			<tr><td colspan="7" style="background-color:#40637A;"><table cellspacing="0" border="0" style="color:White;width:100%;border-collapse:collapse;">
				<tr><td style="color:White;width:15%;"><a href="javascript:__doPostBack('Calendar1','V3653')" style="color:White" title="Go to the previous month">&lt;</a></td><td align="center" style="width:70%;">February 2010</td><td align="right" style="color:White;width:15%;"><a href="javascript:__doPostBack('Calendar1','V3712')" style="color:White" title="Go to the next month">&gt;</a></td></tr>
			</table></td></tr><tr><th align="center" abbr="Monday" scope="col">Mon</th><th align="center" abbr="Tuesday" scope="col">Tue</th><th align="center" abbr="Wednesday" scope="col">Wed</th><th align="center" abbr="Thursday" scope="col">Thu</th><th align="center" abbr="Friday" scope="col">Fri</th><th align="center" abbr="Saturday" scope="col">Sat</th><th align="center" abbr="Sunday" scope="col">Sun</th></tr><tr><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3677')" style="color:White" title="25 January">25</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3678')" style="color:White" title="26 January">26</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3679')" style="color:White" title="27 January">27</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3680')" style="color:White" title="28 January">28</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3681')" style="color:White" title="29 January">29</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3682')" style="color:White" title="30 January">30</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3683')" style="color:White" title="31 January">31</a></td></tr><tr><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3684')" style="color:Black" title="01 February">1</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3685')" style="color:Black" title="02 February">2</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3686')" style="color:Black" title="03 February">3</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3687')" style="color:Black" title="04 February">4</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3688')" style="color:Black" title="05 February">5</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3689')" style="color:Black" title="06 February">6</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3690')" style="color:Black" title="07 February">7</a></td></tr><tr><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3691')" style="color:Black" title="08 February">8</a></td><td align="center" style="color:White;background-color:Silver;width:14%;"><a href="javascript:__doPostBack('Calendar1','3692')" style="color:White" title="09 February">9</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3693')" style="color:Black" title="10 February">10</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3694')" style="color:Black" title="11 February">11</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3695')" style="color:Black" title="12 February">12</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3696')" style="color:Black" title="13 February">13</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3697')" style="color:Black" title="14 February">14</a></td></tr><tr><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3698')" style="color:Black" title="15 February">15</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3699')" style="color:Black" title="16 February">16</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3700')" style="color:Black" title="17 February">17</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3701')" style="color:Black" title="18 February">18</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3702')" style="color:Black" title="19 February">19</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3703')" style="color:Black" title="20 February">20</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3704')" style="color:Black" title="21 February">21</a></td></tr><tr><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3705')" style="color:Black" title="22 February">22</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3706')" style="color:Black" title="23 February">23</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3707')" style="color:Black" title="24 February">24</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3708')" style="color:Black" title="25 February">25</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3709')" style="color:Black" title="26 February">26</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3710')" style="color:Black" title="27 February">27</a></td><td align="center" style="width:14%;"><a href="javascript:__doPostBack('Calendar1','3711')" style="color:Black" title="28 February">28</a></td></tr><tr><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3712')" style="color:White" title="01 March">1</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3713')" style="color:White" title="02 March">2</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3714')" style="color:White" title="03 March">3</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3715')" style="color:White" title="04 March">4</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3716')" style="color:White" title="05 March">5</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3717')" style="color:White" title="06 March">6</a></td><td align="center" style="color:White;width:14%;"><a href="javascript:__doPostBack('Calendar1','3718')" style="color:White" title="07 March">7</a></td></tr>

		</table></TD>
						<TD vAlign="top">


 */


object SelectedDateParser extends BaseParser {
  def findMainPartRegex = """(?s).+<table id="Calendar1" cellspacing="0" cellpadding="2" title="Calendar" border="0">(.+)<STRONG>&nbsp;</STRONG>.+""".r

  def parse(html: String): SelectedDate = {

    // val findYearAndMonth  = """(?s).+ title="Go to the previous month">\&lt;</a></td><td align="center" style="width\:70\%;">(\w+) (\d+)</td>.+""".r
    val findYearAndMonth  = """(?s).+<font color="White">(\w+) (\d\d\d\d)</font>.+""".r

    //println("html: " + html)



    parseMainPart(html) match {
      case Some(mainPart) => {
        //println( "mainPart: " + mainPart )
        //Go to the previous month">&lt;</a></td><td align="center" style="width:70%;">February 2010</td>

        mainPart match {
          case findYearAndMonth(month, year) => {

            val date = new SelectedDate(year.toInt, monthNameToNumber(month))

            //println("month: " + month + ". year: " + year)

            //val findDay = """(?s).+<td align="center" width="14%"><a href="javascript:__doPostBack\('Calendar1','\d+'\)" style="color:Black" title="(\d+) \w+">1</a></td>.+""".r
            val findDay = """(?s).+<td align="center" bgcolor="Silver" width="14%"><font color="White"><a href="javascript:__doPostBack\('Calendar1','\d+'\)" style="color:White" title="\d+ \w+">(\d+)</a></font></td>.+""".r
            //println("mainPartx: "+mainPart)
            mainPart match {
              case findDay(day) => {

                date.setDay( day.toInt )

              }
              case _ => None //didn't find it..
            }

            return date
            
          }
          case _ => throw new ParseException("Cannot find selected date in html") //didn't find it..
        }

      }
      case _ => throw new ParseException("Cannot find selected date in html")

    }

  }

  /**
   * workaround:
   * I have problems parsing full month names with jodatime.
   * This method returns the monthnumber for the monthname
   */
  protected def monthNameToNumber( name : String) : Int ={
    name.toLowerCase match{
      case "january" => 1
      case "february" => 2
      case "march" => 3
      case "april" => 4
      case "may" => 5
      case "june" => 6
      case "july" => 7
      case "august" => 8
      case "september" => 9
      case "october" => 10
      case "november" => 11
      case "december" => 12
      case _ => -1
    }
  }
}