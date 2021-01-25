package com.bringbackdada.site.mail.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailTestConfig {

    @Bean
    public JavaMailSenderImpl emailTestSender() {
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost("localhost");
        emailSender.setPort(3025);
        emailSender.setUsername("testUser");
        emailSender.setPassword("testPassword");
        return emailSender;
    }
}
