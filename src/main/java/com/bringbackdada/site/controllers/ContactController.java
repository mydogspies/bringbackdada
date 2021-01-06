package com.bringbackdada.site.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * The controller for the contact page
 * @author mydogspies@zoho.com
 * @since 1.1.0
 */
@Controller
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final Environment environment;

    @Autowired
    public ContactController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping({"/site/contact-bringbackdada","/site/contact-bringbackdada.html"})
    public String getAboutPage(Model model){
        model.addAttribute("title_text", "Bringbackdada - Contact Form");

        logger.info("--> Calling contact.html");
        return "contact";
    }

    @PostMapping(value={"/site/submit-contact-form"})
    public String processContactForm(@RequestParam("frc-captcha-solution") String solution) {

        // first check FriendlyCaptcha response
        final String url = "https://friendlycaptcha.com/api/v1/siteverify";
        final String apikey = environment.getProperty("captcha.api.key");
        final String sitekey = environment.getProperty("captcha.site.key");

        System.out.println("apikey: " + apikey);
        System.out.println("sitekey: " + sitekey);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String reqBody = "{\"solution\":\"" + solution + "\"," + "\"secret\":\"" + apikey + "\"," + "\"sitekey\":\"" + sitekey + "\"}";

        try {
            HttpEntity<String> entity = new HttpEntity<>(reqBody,headers);
            String result = restTemplate.postForObject(url, entity, String.class);
            logger.info("reqBody: " + reqBody);
            logger.info("Captcha Result: " + result);
        } catch (HttpStatusCodeException e) {
            logger.warn("Http response error: " + e.getStatusCode());
        }

        // TODO implement Json logic for FriendlyCaptcha

        // then check our internal honey pot
        // TODO implement honey pot

        // forward message to admin address
        // TODO implement mailing logic

        logger.info("--> Calling message-sent.html");
        return "message-sent";
    }
}


