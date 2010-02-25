package com.kjetland.hoursregrest.client

import org.apache.http.impl.client.DefaultHttpClient
import java.io.{ByteArrayOutputStream}
import org.apache.http.auth.{AuthScope, NTCredentials}
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import java.util.{ArrayList}
import org.apache.http.{NameValuePair}
import org.apache.http.client.methods.{HttpRequestBase, HttpPost, HttpGet}


/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 09.feb.2010
 * Time: 21:44:11
 * To change this template use File | Settings | File Templates.
 */

class FormElement(val name: String, val value: String) {
  override def toString = "name: " + name + ". value: " + value

  override def equals(other: Any) = {
    other match {
      case o: FormElement => this.name == o.name && this.value == o.value
      case _ => false
    }

  }
}

class Browser(
        username : String,
        password : String) {

  val httpclient = new DefaultHttpClient();

  val creds = new NTCredentials( username, password, null, null);
  httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

  var html : Option[String] = None

  def get(url: String) : Option[String] = {
    val httpget = new HttpGet(url);
    execute( httpget)

  }

  private def execute(request : HttpRequestBase) : Option[String] = {
    val response = httpclient.execute(request);
    val entity = response.getEntity();
    if (entity != null) {
      val buffer = new ByteArrayOutputStream();
      entity.writeTo( buffer );
      this.html = Some( buffer.toString("iso-8859-1") )
    }else{
      this.html = None
    }
    return this.html
  }


  def post(url : String, params : List[FormElement]) : Option[String] = {

/*
    println("params: ")
    params.foreach{
      println(  _)
    }
*/    

    val paramList = new ArrayList[NameValuePair](params.size)

    params.foreach(fe => paramList.add( new BasicNameValuePair(fe.name, fe.value)))
    
    val outEntity = new UrlEncodedFormEntity(paramList, "UTF-8");
    val httppost = new HttpPost( url );
    httppost.setEntity(outEntity);
    execute( httppost)

  }


}