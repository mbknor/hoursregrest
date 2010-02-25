package com.kjetland.hoursregrest.client.parser

import com.kjetland.hoursregrest.client.model.Registration
import org.joda.time.format.DateTimeFormat

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 11.feb.2010
 * Time: 21:47:17
 * To change this template use File | Settings | File Templates.
 */

/*
    			<table cellspacing="0" cellpadding="3" rules="cols" bordercolor="#40637A" border="1" id="dgIkkeGodkjent" bgcolor="White" width="750">
			<tr bgcolor="#40637A">
				<td><font color="White"><b>  </b></font></td><td><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl0','')"><font color="White">Dato</font></a></b></font></td><td align="right"><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl1','')"><font color="White">Timer</font></a></b></font></td><td><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl2','')"><font color="White">Kunde</font></a></b></font></td><td><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl3','')"><font color="White">Prosjekt</font></a></b></font></td><td><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl4','')"><font color="White">Aktivitet</font></a></b></font></td><td><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl5','')"><font color="White">Beskrivelse</font></a></b></font></td><td align="right"><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl6','')"><font color="White">Timepris</font></a></b></font></td><td align="right"><font color="White"><b><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl1$_ctl7','')"><font color="White">BelÃ¸p</font></a></b></font></td>
			</tr><tr bgcolor="WhiteSmoke">
				<td><font color="Black"><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl2$_ctl0','')"><font color="Black">Slett</font></a></font></td><td><font color="Black">02.02.2010</font></td><td align="right"><font color="Black">1.50</font></td><td><font color="Black">firma as</font></td><td><font color="Black">Diverse</font></td><td><font color="Black">Ferie</font></td><td><font color="Black">test 2</font></td><td align="right"><font color="Black">0</font></td><td align="right"><font color="Black">0.00</font></td>
			</tr><tr bgcolor="Gainsboro">
				<td><font color="Black"><a href="javascript:__doPostBack('dgIkkeGodkjent$_ctl3$_ctl0','')"><font color="Black">Slett</font></a></font></td><td><font color="Black">01.02.2010</font></td><td align="right"><font color="Black">1.00</font></td><td><font color="Black">firma2 as</font></td><td><font color="Black">Diverse</font></td><td><font color="Black">Ferie</font></td><td><font color="Black">test</font></td><td align="right"><font color="Black">0</font></td><td align="right"><font color="Black">0.00</font></td>
			</tr><tr bgcolor="#CCCCCC">
				<td><font color="Black"><b>Totalt</b></font></td><td><font color="Black">&nbsp;</font></td><td align="right"><font color="Black"><b>2.50</b></font></td><td><font color="Black">&nbsp;</font></td><td><font color="Black">&nbsp;</font></td><td><font color="Black">&nbsp;</font></td><td><font color="Black">&nbsp;</font></td><td><font color="Black">&nbsp;</font></td><td align="right"><font color="Black"><b>0.00</b></font></td>
			</tr>
		</table>

 */

object RegistrationParser extends BaseListParser[Registration]{

  def findMainPartRegex = """(?s).+<table [^>]+ id="dgIkkeGodkjent" [^>]+>(.+)</table>.+""".r

  def parsePart(part : String) : Option[Registration] = {

    //println("part: \n"+part)

    //val parseOption = """<td><font color="Black"><a href="javascript\:\_\_doPostBack('dgIkkeGodkjent\$.+','')"><font color="Black">Slett</font></a></font></td><td><font color="Black">(.+)</font></td><td align="right"><font color="Black">(.+)</font></td><td><font color="Black">(.+)</font></td><td><font color="Black">(.+)</font></td><td><font color="Black">(.+)</font></td><td><font color="Black">(.+)</font></td><td align="right"><font color="Black">(.+)</font></td><td align="right"><font color="Black">(.+)</font></td>""".r
    val parseOption = ("""<td><font color\="Black"><a href\="javascript\:\_\_doPostBack\('dgIkkeGodkjent\$.+',''\)">"""+
            """<font color\="Black">Slett</font></a></font></td><td><font color\="Black">(.+)</font>"""+
            """</td><td align\="right"><font color\="Black">(.+)</font></td><td>"""+
            """<font color\="Black">(.+)</font></td><td><font color\="Black">(.+)</font>"""+
            """</td><td><font color\="Black">(.+)</font></td><td><font color\="Black">(.+)</font>"""+
            """</td><td align\="right"><font color\="Black">(.+)</font></td><td align\="right"><font color\="Black">(.+)</font>"""+
            """</td>""").r


    part match {

            case parseOption( date, hours, customer, projectName, activity, description, price, amount) => {

              val reg = new Registration(
                DateTimeFormat.forPattern("dd.MM.yyyy").parseDateTime(date).toDateMidnight,
                hours.toDouble,
                customer,
                projectName,
                activity,
                description,
                price.toDouble,
                amount.toDouble)

              return Some( reg )
            }
            case none => return None
          }
  }
}