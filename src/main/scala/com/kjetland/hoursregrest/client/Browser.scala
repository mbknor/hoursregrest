package com.kjetland.hoursregrest.client

import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import java.util.{ArrayList}
import org.apache.http.{NameValuePair}
import org.apache.http.client.methods.{HttpRequestBase, HttpPost, HttpGet}
import com.kjetland.hoursregrest.utils.LogHelper
import jcifs.ntlmssp.Type1Message
import java.io.{ByteArrayOutputStream}
import org.apache.http.params.HttpParams
import org.apache.http.impl.auth.{NTLMScheme, NTLMEngine, NTLMEngineException}
import org.apache.http.auth.{AuthScheme, AuthSchemeFactory, AuthScope, NTCredentials};
import jcifs.ntlmssp.Type2Message;
import jcifs.ntlmssp.Type3Message;
import jcifs.util.Base64;


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

class Browser extends LogHelper {
  val httpclient = new DefaultHttpClient();

  //turn off some anoying logging
  System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
  System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
  //System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
  System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "error");

  def this(username: String, password: String) {
    this ()

    if (logger.isDebugEnabled) {
      logger.debug("username: '" + username + "'")
      logger.debug("password: " + getInfoAboutPassword(password))
    }


    prepareAuthentication(username, password)

  }


  var html: Option[String] = None

  private def getInfoAboutPassword(pw: String): String = {

    var pwAllAscii = false
    val m = """(\w*)""".r

    pw match {
      case m(x) => pwAllAscii = true
      case _ => pwAllAscii = false
    }

    return "password length: " + pw.length + "\n" +
            "password all lowercase: " + (pw.toLowerCase == pw) + "\n" +
            "password all uppercase: " + (pw.toUpperCase == pw) + "\n" +
            "password all [a..z0..9]: " + pwAllAscii
  }

  def get(url: String): Option[String] = {

    if (logger.isDebugEnabled) {
      logger.debug("executing http get: " + url)
    }

    val httpget = new HttpGet(url);

    execute(httpget)

  }

  private def execute(request: HttpRequestBase): Option[String] = {
    val response = httpclient.execute(request);
    val entity = response.getEntity();
    if (entity != null) {
      val buffer = new ByteArrayOutputStream();
      entity.writeTo(buffer);
      val responseString = buffer.toString("iso-8859-1")

      if (logger.isDebugEnabled) {
        logger.debug("http response text:" + responseString)
      }

      this.html = Some(responseString)
    } else {
      this.html = None
    }
    return this.html
  }


  def post(url: String, params: List[FormElement]): Option[String] = {

    if (logger.isDebugEnabled) {
      logger.debug("executing http post: " + url)
      params.foreach {
        x =>
          logger.debug(" formParam: " + x)
      }
    }

    val paramList = new ArrayList[NameValuePair](params.size)

    params.foreach(fe => paramList.add(new BasicNameValuePair(fe.name, fe.value)))

    val outEntity = new UrlEncodedFormEntity(paramList, "UTF-8");
    val httppost = new HttpPost(url);
    httppost.setEntity(outEntity);
    execute(httppost)

  }


  /**
   * Sets up authentication according to this description.
   * http://hc.apache.org/httpcomponents-client/ntlm.html
   */
  private def prepareAuthentication(username : String, password: String) {

    class JCIFSEngine extends NTLMEngine {
      @throws(classOf[NTLMEngineException])
      def generateType1Msg(domain: String, workstation: String): String = {

        val t1m = new Type1Message(
          Type1Message.getDefaultFlags(),
          domain,
          workstation);
        return Base64.encode(t1m.toByteArray());
      }

      @throws(classOf[NTLMEngineException])
      def generateType3Msg(
              username: String,
              password: String,
              domain: String,
              workstation: String,
              challenge: String): String = {
        var t2m: Type2Message = null;
        try {
          t2m = new Type2Message(Base64.decode(challenge));
        } catch {
          case unknown => throw new NTLMEngineException("Invalid Type2 message", unknown);
        }
        val t3m = new Type3Message(
          t2m,
          password,
          domain,
          username,
          workstation);
        return Base64.encode(t3m.toByteArray());
      }

    }

    class NTLMSchemeFactory extends AuthSchemeFactory {
      def newInstance(params: HttpParams): AuthScheme = {
        return new NTLMScheme(new JCIFSEngine());
      }

    }

    httpclient.getAuthSchemes().register("ntlm", new NTLMSchemeFactory());

    httpclient.getCredentialsProvider().setCredentials(
    AuthScope.ANY, 
    new NTCredentials(username, password, null, null));

   
  }


}