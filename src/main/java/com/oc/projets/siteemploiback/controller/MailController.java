package com.oc.projets.siteemploiback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oc.projets.siteemploiback.mailing.Mail;
import com.oc.projets.siteemploiback.mailing.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/mail")
public class MailController {

/*    @Autowired
    public JavaMailSender javaMailSender;*/

    /*@Autowired
    private ObjectMapper objectMapper;*/

/*    @Value("${spring.mail.host}")
    private String hostName;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.password}")
    private String password;*/

/*    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String protocol;*/

    @Autowired
    private Mailer mailService;

    final String htmlContent = "<h1>Test<h1/><a href='https://www.google.fr/'>Google<a/>";

    @Bean public ObjectMapper objectMapper() {
//        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);}
        return new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    }
    @GetMapping("/send")
    public ResponseEntity sendMail() throws MessagingException {

        try {
/*            Mail message = new Mail();
            message.setMailTo("romaindemellier@gmail.com");
            message.setMailFrom("romaindemellier@gmail.com");
            message.setMailSubject("Test");
            message.setMailContent(htmlContent);

            mailService.sendMail(message,true);

            return ResponseEntity.ok(message);*/

/*            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            helper.setTo("romaindemellier@gmail.com");
            helper.setText(htmlContent,true);
            helper.setSubject("Test");

            this.javaMailSender.send(message);
            return ResponseEntity.ok(message);*/
//            helper.setTo("romaindemellier@gmail.com");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
