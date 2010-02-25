package com.kjetland.hoursregrest.client.model

import org.joda.time.{DateMidnight}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 11.feb.2010
 * Time: 21:43:50
 * To change this template use File | Settings | File Templates.
 */

class Registration(
        val date: DateMidnight,
        val hours: Double,
        val customer: String,
        val projectName: String,
        val activity: String,
        val description: String,
        val price: Double,
        val amount: Double
        ) {

  override def equals(obj: Any) = {
    obj match{
      case o : Registration => {
        val e = date == o.date && hours == o.hours && customer == o.customer && projectName == o.projectName &&
          activity == o.activity && description == o.description && price == o.price && amount == o.amount;
        e
      }
      case _ => false
    }
  }

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