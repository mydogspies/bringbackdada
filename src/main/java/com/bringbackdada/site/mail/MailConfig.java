package com.bringbackdada.site.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private final Environment environment;

    public MailConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JavaMailSenderImpl emailSender() {

        final String server = environment.getProperty("mail.server.address");
        final String user = environment.getProperty("mail.server.user");
        final String password = environment.getProperty("mail.server.key");

        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(server);
        emailSender.setPort(465);
        emailSender.setUsername(user);
        emailSender.setPassword(password);
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
        emailSender.setJavaMailProperties(props);
        return emailSender;
    }
}
