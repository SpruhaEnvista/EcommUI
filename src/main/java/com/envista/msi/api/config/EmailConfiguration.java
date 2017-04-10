package com.envista.msi.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

/**
 * Created by Sreenivas on 4/5/2017.
 */

@Configuration
public class EmailConfiguration {

    @Value("${host}")
    String host;
    @Value("${username}")
    String username;
    String password="";

    @Bean
    JavaMailSenderImpl javaMailSenderImpl() {

        String host =this.host;
        String username = this.username;
        String password =  this.password;
        HashMap<String, Object> props = new HashMap<String, Object>();
        props.put("mail.smtp.auth",false);
        props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.port",465);

        return new JavaMailSenderImpl();
    }
}
