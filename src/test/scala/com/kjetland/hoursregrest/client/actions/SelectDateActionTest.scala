package com.kjetland.hoursregrest.client.actions


import org.easymock.EasyMock.expect
import org.easymock.classextension.EasyMock._
import org.junit.{Test}
import com.kjetland.hoursregrest.client.model.SelectedDate
import org.joda.time.{DateMidnight}
import com.kjetland.hoursregrest.client.parser.DayLinkResolver
import com.kjetland.hoursregrest.client.{Browser, FormElement, Client}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 19:54:00
 * To change this template use File | Settings | File Templates.
 */

@Test
class SelectDateActionTest {
  @Test
  def test_isOnCorrectDate {

    val date = new DateMidnight

    val client = createMock(classOf[Client])
    expect(client.selectedDate).andReturn(new SelectedDate(date))
    replay(client)

    val a = new SelectDateAction(client, null)

    a.selectDate(date)

    verify(client)

    return
  }

  @Test
  def test_correctMonthButNoDayIsSelected {
    val date = new DateMidnight

    val client = createMock(classOf[Client])
    //return this year and month but no selected day
    expect(client.selectedDate).andReturn(new SelectedDate(date.year.get, date.monthOfYear.get))

    val originalFormElements = List(new FormElement("name", "value"))

    expect( client.formElements ).andReturn( originalFormElements )
    

    val dayLinkId = "1234"

    val dayLinkResolver = createMock( classOf[DayLinkResolver])
    
    expect(dayLinkResolver.dayLink( date.dayOfMonth.get )).andReturn(dayLinkId)

    val browser = createMock( classOf[Browser])

    val url = "myUrl"
    expect( browser.url).andReturn( url )


    expect( client.browser ).andReturn( browser)

    replay(dayLinkResolver, client, browser)

    val a = new SelectDateAction(client, dayLinkResolver)

    a.selectDate( date )

    verify(client, dayLinkResolver)
  }
}