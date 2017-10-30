package com.codingsuperstar.codingsuperstar.mail;

/**
 * Created by nabeelabdullah on 30/10/17.
 */

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class testemail {

    /**
     * @param args
     */
    private static final String SMTP_HOST_NAME = "smtp.zoho.com";
    private static final String SMTP_PORT = "587";
    private static final String emailMsgTxt = "Test Message Contents";
    private static final String emailSubjectTxt = "A test from gmail";
    private static final String emailFromAddress = "nabeel@codingsuperstar.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String[] sendTo = {"nabeel.abdullah@freshmenu.com"};


    public static void main(String args[]) throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        new testemail().sendSSLMessage(sendTo, emailSubjectTxt, emailMsgTxt, emailFromAddress);
        System.out.println("Sucessfully mail to All Users");
    }

    public void sendSSLMessage(String recipients[], String subject,
                               String message, String from) throws MessagingException {
        boolean debug = true;


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.zoho.com");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.startssl.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFromAddress, "nabeel@123");
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport transport = session.getTransport("smtp");
        transport.connect(SMTP_HOST_NAME, 587, emailFromAddress, "*****");
        transport.sendMessage(msg, addressTo);
        transport.close();


    }

}