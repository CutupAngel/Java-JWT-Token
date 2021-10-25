package com.chainhaus.mtg.service;


import com.chainhaus.mtg.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Asad Sarwar on 24/09/2019.
 */
@Service
public class EmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private String username;
    private String password;
    private Boolean emailEnabled;

    private Properties props = new Properties();

    public EmailService(@Autowired Environment environment){
        emailEnabled = environment.getProperty(Constants.PROPERTY_EMAIL_ENABLED, Boolean.class);
        username = environment.getProperty(Constants.PROPERTY_EMAIL_USER_NAME, String.class);
        password = environment.getProperty(Constants.PROPERTY_EMAIL_PASSWORD, String.class);

        props.put(Constants.MAIL_SMTP_AUTH, environment.getProperty(Constants.MAIL_SMTP_AUTH, String.class));
        props.put(Constants.MAIL_SMTP_STARTTLS_ENABLE, environment.getProperty(Constants.MAIL_SMTP_STARTTLS_ENABLE, String.class));
        props.put(Constants.MAIL_SMTP_HOST, environment.getProperty(Constants.MAIL_SMTP_HOST, String.class));
        props.put(Constants.MAIL_SMTP_PORT, environment.getProperty(Constants.MAIL_SMTP_PORT, String.class));

    }


    public boolean sendEmail(String recipient,String subject, String messageText){
        return sendEmailCC(recipient, null, subject, messageText);
    }

    public boolean sendEmailCC(String recipient, String cc, String subject, String messageText){
        if(emailEnabled) {
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                if(cc != null){
                    message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
                }
                message.setSubject(subject);
                message.setText(messageText);
                Transport.send(message);

                LOGGER.info("Email sent to: '"+ recipient +"' cc: '"+cc+"' subject: '"+ subject + "'");
            } catch (MessagingException e) {
                LOGGER.error("Error sending email to '"+ recipient +"' subject '"+ subject +"' error:" + e.getMessage());
                throw new RuntimeException(e);
            }

            return true;
        }

        return false;
    }

}
