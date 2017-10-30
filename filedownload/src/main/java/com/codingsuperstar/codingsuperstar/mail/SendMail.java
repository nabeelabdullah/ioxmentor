package com.codingsuperstar.codingsuperstar.mail;

import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author NABEEL
 */
public class SendMail {

    private boolean sendmailfunction(String content, String name, String f_name, String to, String by, String subject) throws MessagingException {
        String host = "smtp.gmail.com";
        String Password = "akhtar079723";
        String from = "multikbis.com@gmail.com";
        String toAddress = to;
        String filename = f_name;
        Properties props = System.getProperties();
        props.put("mail.smtps.host", host);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.starttls.enable", "true");
        String send_by = "message send by " + by;

        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, new InternetAddress().parse(toAddress));//toAddress);

        message.setSubject(subject);//("JavaMail Attachment");

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText(send_by);////("Here's the file");

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        BodyPart messageBodyPartContent = new MimeBodyPart();

        messageBodyPartContent.setContent(content, "text/html");

        multipart.addBodyPart(messageBodyPartContent);

        messageBodyPart = new MimeBodyPart();

        if (f_name != null) {

            DataSource source = new FileDataSource(filename);

            messageBodyPart.setDataHandler(new DataHandler(source));

            messageBodyPart.setFileName(name);

            multipart.addBodyPart(messageBodyPart);
        }

        message.setContent(multipart);

        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, 465, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();

        } catch (SendFailedException sfe) {

            System.out.println(sfe);
        }
        return true;
    }

    public static void main(String args[]) {
        try {
            SendMail sm = new SendMail();
            long start = Calendar.getInstance().getTimeInMillis();
            sm.sendmailfunction("  content", "Name", "/Users/nabeelabdullah/Desktop/AWS-strategy-gambar.png", "nabeel.abdullah@freshmenu.com", "nabeel", "");
            // sm.sendmailfunction("  dd", null, "nabeel.abdullah@freshmenu.com", "nabeel", "s");
            long end = Calendar.getInstance().getTimeInMillis();
            System.out.println("time taken is " + (end - start) / 1000);
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean sendmail(String content, String name, String path, String to, String by, String subject) {
        try {
            sendmailfunction(content, name, path, to, by, subject);
        } catch (MessagingException ex) {
            System.out.println(ex);
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}