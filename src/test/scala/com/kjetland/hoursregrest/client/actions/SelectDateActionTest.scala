package com.kjetland.hoursregrest.client.actions

import org.easymock.EasyMock
import com.kjetland.hoursregrest.client.Client
import org.junit.{Test}
import com.kjetland.hoursregrest.client.model.SelectedDate
import org.joda.time.{DateMidnight}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 19:54:00
 * To change this template use File | Settings | File Templates.
 */

@Test
class SelectDateActionTest{

  @Test
  def test_isOnCorrectDate{

    val date = new DateMidnight

    val client = EasyMock.createMock( classOf[Client])
    EasyMock.expect(client.selectedDate).andReturn( new SelectedDate(date) )
    EasyMock.replay( client )

    val a = new SelectDateAction( client, null )

    a.selectDate( date )

    EasyMock.verify( client )

    return
  }

  @Test
  def test_differentDaySameMonth{
    
  }
}