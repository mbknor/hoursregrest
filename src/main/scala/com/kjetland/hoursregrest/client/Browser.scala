package com.kjetland.hoursregrest.client

import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.{HttpEntity, HttpResponse}
import org.apache.http.client.methods.HttpGet
import java.io.{ByteArrayOutputStream, InputStream}
import org.apache.http.auth.{AuthScope, NTCredentials}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 21:44:11
 * To change this template use File | Settings | File Templates.
 */

class Browser(
        username : String,
        password : String) {

  val httpclient = new DefaultHttpClient();

  val creds = new NTCredentials( username, password, null, null);
  httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);


  def get(url: String) : Option[String] = {
    val httpget = new HttpGet(url);
    val response = httpclient.execute(httpget);
    val entity = response.getEntity();
    if (entity != null) {
      val buffer = new ByteArrayOutputStream();
      entity.writeTo( buffer );
      Some( buffer.toString("iso-8859-1") )
    }else{
      None
    }

  }


}