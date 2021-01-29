package com.bringbackdada.site.controllers;

import com.bringbackdada.site.mail.ContactMail;
import com.bringbackdada.site.mail.MailConfig;
import com.bringbackdada.site.mail.MailService;
import com.bringbackdada.site.model.FormMailData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * The controller for the contact page
 *
 * @author mydogspies@zoho.com
 * @since 1.1.0
 */
@Controller
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final MailService sender;
    private final Environment environment;

    public ContactController(MailConfig config, Environment environment) {
        this.sender = new MailService(config.emailSender());
        this.environment = environment;
    }


    @GetMapping({"/site/contact-bringbackdada", "/site/contact-bringbackdada.html"})
    public String getAboutPage(Model model) {

        model.addAttribute("formdata", new FormMailData());
        model.addAttribute("title_text", "Bringbackdada - Contact Form");

        logger.info("--> Calling contact.html");
        return "contact";
    }

    @PostMapping(value = "/site/submit-contact-form")
    public String processContactForm(@ModelAttribute FormMailData data, BindingResult result,
                                     @RequestParam("postcode") String botcheck,
                                     @RequestParam("ip") String ip, Model model) {

        // check for incoming errors
        //
        if (result.hasErrors()) {
            logger.error("processContactForm(): An error with the form/ajax submission triggered a 404 - Bad Request.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong with the contact form. Please try again or contact admin at https://github.com/mydogspies/bringbackdada");
        }
        if (!botcheck.isEmpty()) {
            logger.debug("processContactForm(): The form honeypot logic triggered a 404 - Bad Request.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No automated form requests allowed! Go away bot!");
        }

        // validate the incoming data
        //
        // TODO additional validation logic


        final String internalFromAddress = environment.getProperty("mail.server.user");
        String ipaddress = "n/a";
        if (!ip.equals("")) {
            ipaddress = ip;
        }
        String toAddress = internalFromAddress; // in this case simply the same
        String subject = "Contact form submission <bringbackdada.com> ";
        String body = "Following message was submitted via Contact page from from ip [" + ipaddress + "]:\n" + // TODO format this all properly!
                "Sender> " + data.getOnenamefirst() + " " + data.getTwonamelast() + "\n" +
                "Address> " + data.getYoursomeaddressmail() + "\n\n" +
                data.getTalktome();
        ContactMail contactMail = new ContactMail(internalFromAddress, toAddress, subject, body);

        sender.sendContactFormMessage(contactMail);

        // TODO respond back with sent content showing on the page
        model.addAttribute("title_text", "Bringbackdada - Thank you for your message!");
        logger.info("processContactForm(): Message received via Contact form.");

        return "contact-mail-sent";

    }

}


