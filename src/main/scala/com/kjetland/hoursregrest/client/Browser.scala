package com.kjetland.hoursregrest.client

import org.apache.http.impl.client.DefaultHttpClient
import java.io.{ByteArrayOutputStream}
import org.apache.http.auth.{AuthScope, NTCredentials}
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import java.util.{ArrayList}
import org.apache.http.{NameValuePair}
import org.apache.http.client.methods.{HttpRequestBase, HttpPost, HttpGet}
import com.kjetland.hoursregrest.utils.LogHelper


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

class Browser extends LogHelper{

  val httpclient = new DefaultHttpClient();

  //turn off some anoying logging
  System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
  System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
  //System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
  System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "error");

  def this( username : String, password : String){
    this()

    if( logger.isDebugEnabled ){
      logger.debug("username: '" + username + "'")
      logger.debug("password: " + getInfoAboutPassword(password))
    }


    val creds = new NTCredentials( username, password, null, null);
    httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);
  }

  
  var html : Option[String] = None

  private def getInfoAboutPassword(pw: String):String={

    var pwAllAscii = false
    val m = """(\w*)""".r
    
    pw match {
      case m(x) => pwAllAscii = true
      case _ => pwAllAscii = false
    }

    return "password length: " + pw.length + "\n"+
      "password all lowercase: " + (pw.toLowerCase == pw) + "\n"+
      "password all uppercase: " + (pw.toUpperCase == pw) + "\n"+
      "password all [a..z0..9]: " + pwAllAscii
  }

  def get(url: String) : Option[String] = {

    if(logger.isDebugEnabled){
      logger.debug("executing http get: " + url)
    }

    val httpget = new HttpGet(url);

    execute( httpget)

  }

  private def execute(request : HttpRequestBase) : Option[String] = {
    val response = httpclient.execute(request);
    val entity = response.getEntity();
    if (entity != null) {
      val buffer = new ByteArrayOutputStream();
      entity.writeTo( buffer );
      val responseString = buffer.toString("iso-8859-1")

      if( logger.isDebugEnabled ){
        logger.debug("http response text:" + responseString)
      }

      this.html = Some( responseString )
    }else{
      this.html = None
    }
    return this.html
  }


  def post(url : String, params : List[FormElement]) : Option[String] = {

    if(logger.isDebugEnabled){
      logger.debug("executing http post: " + url)
      params.foreach{ x =>
          logger.debug(" formParam: " + x)
        }
    }

    val paramList = new ArrayList[NameValuePair](params.size)

    params.foreach(fe => paramList.add( new BasicNameValuePair(fe.name, fe.value)))
    
    val outEntity = new UrlEncodedFormEntity(paramList, "UTF-8");
    val httppost = new HttpPost( url );
    httppost.setEntity(outEntity);
    execute( httppost)

  }


}