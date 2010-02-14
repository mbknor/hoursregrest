package com.kjetland.hoursregrest.client.model

import java.util.Date
import org.joda.time.{DateTime, LocalDate}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 11.feb.2010
 * Time: 21:43:50
 * To change this template use File | Settings | File Templates.
 */

class Registration(
        val date: DateTime,
        val hours: Double,
        val customer: String,
        val projectName: String,
        val activity: String,
        val description: String,
        val price: Double,
        val amount: Double
        ) {
  
  override def toString =
    "date: " + date +
            ". hours: " + hours +
            ". customer: " + customer +
            ". projectName: " + projectName +
            ". activity: " + activity +
            ". description: " + description +
            ". price: " + price +
            ". amount: " + amount
}