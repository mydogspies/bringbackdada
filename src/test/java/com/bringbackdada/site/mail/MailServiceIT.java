package com.bringbackdada.site.mail;

import com.bringbackdada.site.mail.test.MailTestConfig;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MailServiceIT {

    @Autowired
    private MailTestConfig mailConfig;

    @Mock
    private MailService mailService;


    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetup.SMTP.withPort(3025))
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("testUser", "testPassword"))
            .withPerMethodLifecycle(false);

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.mailService = new MailService(mailConfig.emailTestSender());
    }

    @Test
    void sendContactFormMailTest() throws MessagingException {

        ContactMail mail = new ContactMail("test@sender.com",
                "test@receiver.com",
                "test subject",
                "this is a test message body");

        mailService.sendContactFormMessage(mail);

        Message[] messages = greenMail.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals("test subject", messages[0].getSubject());
        String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
        assertEquals("this is a test message body", body);
    }

    @AfterAll
    static void afterAll() {
        greenMail.stop();
    }
}