package com.kjetland.hoursregrest.client.parser

import org.junit.{Assert, Test}

/**
 * Created by IntelliJ IDEA.
 * User: morten
 * Date: 16.feb.2010
 * Time: 22:25:04
 * To change this template use File | Settings | File Templates.
 */

@Test
class FormParserTest {
  @Test
  def test() {

    val html = """	<body>
	<form name="Form1" method="post" action="wfrmTimeRegistrering.aspx" id="Form1">
<input type="hidden" name="__TabStripValg_State__" id="__TabStripValg_State__" value="0" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwULLTExMjY4OTY2MTQPZBYCAgEPZBYCAgEPZBYKAgEPZBYEAgcPZBYCAgEPEGRkFgBkAgsPEA8WAh4LXyFEYXRhQm91bmRnZBAVCQEtG05vcmRlYSBMaXYgLSBCTSAtIFV0dmlrbGluZyBXZWJzdGVwIGFzIC0gRGl2ZXJzZSAtIFBlcm1pc2pvbi9XZWJzdGVwIGFzIC0gU3lrZG9tIC0gRWdlbm1lbGRpbmcgdW5kZXIgMyBkYWdlcjBXZWJzdGVwIGFzIC0gU3lrZG9tIC0gU3lrZW1lbGRpbmcgdW5kZXIgMTYgZGFnZXIwV2Vic3RlcCBhcyAtIFN5a2RvbTIgLSBTeWtlbWVsZGluZyBvdmVyIDE2IGRhZ2VyHFdlYnN0ZXAgYXMgLSBEaXZlcnNlIC0gRmVyaWUoV2Vic3RlcCBhcyAtIERpdmVyc2UgLSBBbmRyZSBha3Rpdml0ZXRlci1XZWJzdGVwIGFzIC0gU3lrZG9tIC0gU3lrdCBiYXJuIChlZ2VubWVsZGluZykVCQEtBDE0MjcEMjEyNgQxMDk5BDExMDAEMTEwMQQxMTAyBDExMDMEMTEwNBQrAwlnZ2dnZ2dnZ2cWAWZkAgMPZBYGAhEPEGRkFgBkAhMPEGRkFgBkAhUPEGRkFgBkAgcPZBYCAgEPPCsACwEADxYKHgtfIUl0ZW1Db3VudAICHghEYXRhS2V5cxYCAuzwDALr8AweB1Zpc2libGVnHglQYWdlQ291bnQCAR4VXyFEYXRhU291cmNlSXRlbUNvdW50AgJkFgJmD2QWBgIBD2QWGgIBDw8WAh4EVGV4dAUKMDIuMDIuMjAxMGRkAgIPDxYCHwYFBDEuNTBkZAIDDw8WAh8GBQpXZWJzdGVwIGFzZGQCBA8PFgIfBgUHRGl2ZXJzZWRkAgUPDxYCHwYFBUZlcmllZGQCBg8PFgIfBgUGdGVzdCAyZGQCBw8PFgIfBgUBMGRkAggPDxYCHwYFBDAuMDBkZAIJDw8WAh8GBQYyMTEwNTJkZAIKDw8WAh8GBQM1MDhkZAILDw8WAh8GBQQxMTAyZGQCDA8PFgIfBgUDMTk3ZGQCDQ8PFgIfBgUBTmRkAgIPZBYaAgEPDxYCHwYFCjAxLjAyLjIwMTBkZAICDw8WAh8GBQQxLjAwZGQCAw8PFgIfBgUKV2Vic3RlcCBhc2RkAgQPDxYCHwYFB0RpdmVyc2VkZAIFDw8WAh8GBQVGZXJpZWRkAgYPDxYCHwYFBHRlc3RkZAIHDw8WAh8GBQEwZGQCCA8PFgIfBgUEMC4wMGRkAgkPDxYCHwYFBjIxMTA1MWRkAgoPDxYCHwYFAzUwOGRkAgsPDxYCHwYFBDExMDJkZAIMDw8WAh8GBQMxOTdkZAINDw8WAh8GBQFOZGQCAw9kFgZmDw8WAh8GBQZUb3RhbHRkZAICDw8WAh8GBQQyLjUwZGQCCA8PFgIfBgUEMC4wMGRkAgkPZBYCAgEPPCsACwBkAgsPZBYCAgMPDxYCHwYFCUFzY2VuZGluZ2RkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYCBQxUYWJTdHJpcFZhbGcFEGNoa0VuZHJlVGltZXByaXMnoJPue0p4LpJVSAWQykRMq9FiZw==" />

	<div id="pnlTimeregHoved">

  <select name="dlstProsjektAktivitet" id="dlstProsjektAktivitet">
			<option selected="selected" value="-">-</option>

                            <span id="Label3">Timer :</span></STRONG></TD>
						<TD style="WIDTH: 365px">
							<input name="txtTimer" type="text" id="txtTimer" />&nbsp;<span id="Label4">Timepris :</span>


							<input id="chkEndreTimepris" type="checkbox" name="chkEndreTimepris" /><label for="chkEndreTimepris">Endre Timepris</label></TD>
						<TD>
						<TD style="WIDTH: 365px">
							<input name="txtBeskrivelse" type="text" id="txtBeskrivelse" /></TD>
						<TD>
							<input type="submit" name="btnLeggTil" value="Legg til" id="btnLeggTil" /></TD>
	</div>

		</table>
			<input type="submit" name="btnGodkjenn" value="Godkjenn" id="btnGodkjenn" />

	</div>


</div>

<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWRQKi7KTcBALZmKLcBwK1tpDaAgLUtLOSAQLUtO/6BwLUtNuVDwL/3o/SBQL/3vuNDQL/3teoBgL/3sPDDwL/3r9/Av/eq5oIAv/eh7UBAv/e89AKAv/er7kPAv/em1QC2vet/QMC2veZmAsC2vf1swwC2vfh7gUC2vfdiQ0C2vfJpAYC2velwA8C2veRewLa983DBQLa97n/DgKXlPiICwKXlNSjDAKXlMDeBQKXlLz6DgKXlKiVBgKXlISwDwKXlPBrApeU7IYIApeUmO8OApeU9IoGAvKthpMBAvKt8s4KAvKt7ukDAvKt2oQLAvKttqAMAvKtotsFAvKtnvYOAvKtipEGAvKtpvoEAre4rfcIAuvX6qoDAurXipYEAtiaj+gBAp35zp4EAp35srUDAp35psgKAp35iu8BAp35/oMJAryE5NMLAoCwwIcGAp3g2Z4JAv2Zu/wFAr3QiuMJAr3QhuMJAr3QkuMJAr3QjuMJAr3QmuMJAr3QluMJAr3QouMJAr3QnuMJAr3Q5uEEAr3Q0usCAoPGze8EpKCgr31N2hAEsl5fo4Tb+8ICFYM=" /></form>
	</body>
</HTML>"""

    val fasit = List(
      new FormElement("__TabStripValg_State__", "0"),
      new FormElement("__VIEWSTATE", "/wEPDwULLTExMjY4OTY2MTQPZBYCAgEPZBYCAgEPZBYKAgEPZBYEAgcPZBYCAgEPEGRkFgBkAgsPEA8WAh4LXyFEYXRhQm91bmRnZBAVCQEtG05vcmRlYSBMaXYgLSBCTSAtIFV0dmlrbGluZyBXZWJzdGVwIGFzIC0gRGl2ZXJzZSAtIFBlcm1pc2pvbi9XZWJzdGVwIGFzIC0gU3lrZG9tIC0gRWdlbm1lbGRpbmcgdW5kZXIgMyBkYWdlcjBXZWJzdGVwIGFzIC0gU3lrZG9tIC0gU3lrZW1lbGRpbmcgdW5kZXIgMTYgZGFnZXIwV2Vic3RlcCBhcyAtIFN5a2RvbTIgLSBTeWtlbWVsZGluZyBvdmVyIDE2IGRhZ2VyHFdlYnN0ZXAgYXMgLSBEaXZlcnNlIC0gRmVyaWUoV2Vic3RlcCBhcyAtIERpdmVyc2UgLSBBbmRyZSBha3Rpdml0ZXRlci1XZWJzdGVwIGFzIC0gU3lrZG9tIC0gU3lrdCBiYXJuIChlZ2VubWVsZGluZykVCQEtBDE0MjcEMjEyNgQxMDk5BDExMDAEMTEwMQQxMTAyBDExMDMEMTEwNBQrAwlnZ2dnZ2dnZ2cWAWZkAgMPZBYGAhEPEGRkFgBkAhMPEGRkFgBkAhUPEGRkFgBkAgcPZBYCAgEPPCsACwEADxYKHgtfIUl0ZW1Db3VudAICHghEYXRhS2V5cxYCAuzwDALr8AweB1Zpc2libGVnHglQYWdlQ291bnQCAR4VXyFEYXRhU291cmNlSXRlbUNvdW50AgJkFgJmD2QWBgIBD2QWGgIBDw8WAh4EVGV4dAUKMDIuMDIuMjAxMGRkAgIPDxYCHwYFBDEuNTBkZAIDDw8WAh8GBQpXZWJzdGVwIGFzZGQCBA8PFgIfBgUHRGl2ZXJzZWRkAgUPDxYCHwYFBUZlcmllZGQCBg8PFgIfBgUGdGVzdCAyZGQCBw8PFgIfBgUBMGRkAggPDxYCHwYFBDAuMDBkZAIJDw8WAh8GBQYyMTEwNTJkZAIKDw8WAh8GBQM1MDhkZAILDw8WAh8GBQQxMTAyZGQCDA8PFgIfBgUDMTk3ZGQCDQ8PFgIfBgUBTmRkAgIPZBYaAgEPDxYCHwYFCjAxLjAyLjIwMTBkZAICDw8WAh8GBQQxLjAwZGQCAw8PFgIfBgUKV2Vic3RlcCBhc2RkAgQPDxYCHwYFB0RpdmVyc2VkZAIFDw8WAh8GBQVGZXJpZWRkAgYPDxYCHwYFBHRlc3RkZAIHDw8WAh8GBQEwZGQCCA8PFgIfBgUEMC4wMGRkAgkPDxYCHwYFBjIxMTA1MWRkAgoPDxYCHwYFAzUwOGRkAgsPDxYCHwYFBDExMDJkZAIMDw8WAh8GBQMxOTdkZAINDw8WAh8GBQFOZGQCAw9kFgZmDw8WAh8GBQZUb3RhbHRkZAICDw8WAh8GBQQyLjUwZGQCCA8PFgIfBgUEMC4wMGRkAgkPZBYCAgEPPCsACwBkAgsPZBYCAgMPDxYCHwYFCUFzY2VuZGluZ2RkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYCBQxUYWJTdHJpcFZhbGcFEGNoa0VuZHJlVGltZXByaXMnoJPue0p4LpJVSAWQykRMq9FiZw=="),
      new FormElement("txtTimer", ""),
      new FormElement("chkEndreTimepris", ""),
      new FormElement("txtBeskrivelse", ""),
      new FormElement("__EVENTVALIDATION", "/wEWRQKi7KTcBALZmKLcBwK1tpDaAgLUtLOSAQLUtO/6BwLUtNuVDwL/3o/SBQL/3vuNDQL/3teoBgL/3sPDDwL/3r9/Av/eq5oIAv/eh7UBAv/e89AKAv/er7kPAv/em1QC2vet/QMC2veZmAsC2vf1swwC2vfh7gUC2vfdiQ0C2vfJpAYC2velwA8C2veRewLa983DBQLa97n/DgKXlPiICwKXlNSjDAKXlMDeBQKXlLz6DgKXlKiVBgKXlISwDwKXlPBrApeU7IYIApeUmO8OApeU9IoGAvKthpMBAvKt8s4KAvKt7ukDAvKt2oQLAvKttqAMAvKtotsFAvKtnvYOAvKtipEGAvKtpvoEAre4rfcIAuvX6qoDAurXipYEAtiaj+gBAp35zp4EAp35srUDAp35psgKAp35iu8BAp35/oMJAryE5NMLAoCwwIcGAp3g2Z4JAv2Zu/wFAr3QiuMJAr3QhuMJAr3QkuMJAr3QjuMJAr3QmuMJAr3QluMJAr3QouMJAr3QnuMJAr3Q5uEEAr3Q0usCAoPGze8EpKCgr31N2hAEsl5fo4Tb+8ICFYM=")

      )

    val list = FormParser.parse( html )

    println("list:")
    list.foreach{ println(_)}
    println("fasit:")
    fasit.foreach{ println(_)}

    Assert.assertEquals(fasit, list)

    ""

  }

}