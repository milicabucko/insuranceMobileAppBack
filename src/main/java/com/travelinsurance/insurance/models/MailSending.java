package com.travelinsurance.insurance.models;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSending {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInsurance(Insurance insurance, String messageText) {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;

        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom("boxboux@gmail.com");
            messageHelper.setTo(insurance.getBuyer().getEmail());
            messageHelper.setSubject("Informations about insurance");
            messageHelper.setText(messageText);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
