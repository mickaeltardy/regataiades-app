package org.mtdev.regataiades.business.interfaces;

public interface MailManager {

	boolean sendMail(String pRecipients, String pSubject, String pBody);

	
}
