package com.envista.msi.api.service;

import com.envista.msi.api.config.EmailConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.File;

/**
 * Created by Sreenivas on 4/5/2017.
 */
@Service
public class EmailService {

    @Inject
    private JavaMailSender mailSender;

    @Inject
    private EmailConfiguration emailConfiguration;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(final String from, final String to,final String subject,final String msg) {
        try{
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg);

            mailSender.send(message);
        }catch(MessagingException e){e.printStackTrace();}
    }
    public void sendMail(final String from, final String to,final String subject,final String msg, String fileName, String filePath) {

        try{
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(fileName, file);

            mailSender.send(message);

        }catch(MessagingException e){e.printStackTrace();}
    }
}
