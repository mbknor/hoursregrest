package com.kjetland.hoursregrest.client.actions

import com.kjetland.hoursregrest.client.model.Project
import collection.mutable.ListBuffer
import com.kjetland.hoursregrest.client.{FormElement, Client}
import com.kjetland.hoursregrest.utils.LogHelper

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 25.feb.2010
 * Time: 23:05:07
 * To change this template use File | Settings | File Templates.
 */

class AddRegistrationAction(client: Client) extends LogHelper {

  def addRegistration(project: Project, hours: Double, description: String) {

    logger.info("Adding registration. project: " + project + " hours: " + hours + " desc: " + description)

    val originalFormElements = client.formElements
    val formElements = new ListBuffer[FormElement]

    
    originalFormElements.foreach { fe =>
      
      if( fe.name == "__VIEWSTATE"){
        formElements += fe
      }else if( fe.name == "__EVENTVALIDATION"){
        formElements += fe
      }
    }
    
    formElements += new FormElement("__EVENTTARGET","")
    formElements += new FormElement("__EVENTARGUMENT","")
    formElements += new FormElement("__LASTFOCUS","")
    formElements += new FormElement("dlstProsjektAktivitet",project.id.toString)
    formElements += new FormElement("txtTimer", hours.toString)
    formElements += new FormElement("txtBeskrivelse", description)
    formElements += new FormElement("btnLeggTil", "Legg til")

    client.browser.post(client.url, formElements.toList)
  }
}