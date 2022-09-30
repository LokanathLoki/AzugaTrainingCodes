package com.azuga.training.zip;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * class Mail is used to send the mail of the zip file to the recipient Mail from the given Mail
 */
public class Mail {
    private static final Logger logger = Logger.getLogger(Mail.class);

    /**
     * sendMail method sends the mail containing the zip file of the  output files
     * generated from the formatters class
     */
    public void sendMail() {
        logger.info("Mail method is invoked");
        //configure properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.debug", true);
        logger.info("Configuring the Mail properties");

        Session session = Session.getInstance(props);
        logger.info("session is created");
        // Construct the message
        String to = "indukurimr@azuga.com";
        String from = "lokanathkumar09672@gmail.com";
        String subject = "**System-Generated**FormatterClass-Reports of GhibliApi";
        Message msg = new MimeMessage(session);
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        logger.info("Constructing  the message");
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);


            //add attachment to mail
            attachmentBodyPart.attachFile(new File("/Users/azuga/Desktop/loki.zip"));
            logger.info("zip file is attached as attachment");
            String body = "Hi everyone," + "\n\n" + "This is the zipped file of my generated files for Ghibli Api's data " +
                    "from the formatter class." + "\n\n" + "Regards," + "\n" + "Lokanath K";
            mimeBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            msg.setContent(multipart);
            logger.info("Mail constructed successfully");
            // Send the message.

            Transport.send(msg, "lokanathkumar09672@gmail.com", "cvmq odai eecr fehk");

            logger.info("Email sent to " + to);
        } catch (IOException e) {
            logger.error("error occurred due to IOException: {}", e);
        } catch (MessagingException e) {
            logger.error("Error occurred due to MessagingException: {}", e);
        }
    }
}
