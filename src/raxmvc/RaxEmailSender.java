/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raxmvc;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rax
 */
public class RaxEmailSender extends RaxConfig{
  
    public static enum EmailType {
        SSL,
        TLS
    }

    public static enum EmailSendType {
        text_html,
        text_only
    }

    static  {
      
        Properties prop = new Properties();
        prop.put("mail.smtp.host", getEmailHost());//"smtp.gmail.com"
        prop.put("mail.smtp.port", Integer.toString(getEmailPort()));//"587"       
        prop.put("mail.smtp.auth", Boolean.toString(getEmailAuth()));//"true"
        switch (getEmailType()) {
            case SSL:
                prop.put("mail.smtp.socketFactory.port",  Integer.toString(getEmailPort()));
                prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                break;
            default:
                prop.put("mail.smtp.starttls.enable", "true"); //true
                break;
        }

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getEmailUsername(), getEmailPassword());
            }
        });
        setEmailSession(session);
    }
    
    public static RaxEmailSender getInstance(){
        RaxEmailSender emailSender = new RaxEmailSender();
        return emailSender;
    }

    public static boolean sendEmail(String tujuan, String judul, String isi, EmailSendType tipe) {
        String to = tujuan;
        try {
            Message message = new MimeMessage(getEmailSession());
            message.setFrom(new InternetAddress(getEmailUsername()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(judul);
            switch (tipe) {
                case text_html:
                    message.setContent(isi, "text/html");
                    break;
                default:
                    message.setText(isi);
                    break;
            }
            
            Transport.send(message);
            System.out.println("Terkirim");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
