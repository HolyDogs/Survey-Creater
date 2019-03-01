package com.me.utils;

import com.me.config.EmailMessage;
import com.me.beans.Mymail;

/**
 * @author fenghere
 */
public class SendEmailUtils {

	public static int sendEmails(String body,String addr) {

		Mymail mymail=new Mymail(EmailMessage.getSENDTEMP());
		mymail.setNeedAuth(true);
		if(mymail.setSubject(EmailMessage.getSENDNAME())==false) {
			return 0;
		}
		if(mymail.setBody(body)==false) {
			return 0;
		}
		if(mymail.setTo(addr)==false) {
			return 0;
		}
		if(mymail.setFrom(EmailMessage.getSENDFROM())==false) {
			return 0;
		}
		mymail.setNamePass(EmailMessage.getQQNUMBER(), EmailMessage.getPASSWORD());
		
		if(mymail.sendout()==false) {
			return 0;
		}
		return 1;
	}
}
