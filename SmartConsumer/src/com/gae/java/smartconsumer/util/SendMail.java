package com.gae.java.smartconsumer.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    public static void sendMessage(String from, String to, String subject, String body){
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
        }catch(AddressException e){
            System.out.println(e.getMessage());
        }catch(MessagingException ex){
            System.out.println(ex.getMessage());
        }
    }
}
