package com.bringbackdada.site.controllers;

import com.bringbackdada.site.mail.ContactMail;
import com.bringbackdada.site.mail.MailConfig;
import com.bringbackdada.site.mail.MailService;
import com.bringbackdada.site.model.FormMailData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * The controller for the contact page
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


    @GetMapping({"/site/contact-bringbackdada","/site/contact-bringbackdada.html"})
    public String getAboutPage(Model model){

        model.addAttribute("formdata", new FormMailData());
        model.addAttribute("title_text", "Bringbackdada - Contact Form");

        logger.info("--> Calling contact.html");
        return "contact";
    }

    @PostMapping(value = "/site/submit-contact-form")
    public String processContactForm(@ModelAttribute FormMailData data, BindingResult result,
                                     @RequestParam("postcode") String botcheck) {

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong with the contact form. Please try again or contact admin at https://github.com/mydogspies/bringbackdada");
        } else {
            // check for bots
            if(!botcheck.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No automated form requests allowed! Go away bot!");
            }

            // TODO refactor at some point the from and to address logic
            final String internalFromAddress = environment.getProperty("mail.server.user");
            String toAddress = internalFromAddress; // in this case simply the same
            String subject = "Contact form submission <bringbackada.com> ";
            String body = "Following message was submitted via Contact page form:\n" +
                    "Sender> " + data.getOnenamefirst() + " " + data.getTwonamelast() + "\n" +
                    "Address> " + data.getYoursomeaddressmail() + "\n\n" +
                    data.getTalktome();
            ContactMail contactMail = new ContactMail(internalFromAddress, toAddress, subject, body);

            sender.sendContactFormMessage(contactMail);


            // TODO show submitted info on the confirmation page
            return "contact-mail-sent";
        }


        //        // first check FriendlyCaptcha response
//        final String url = "https://friendlycaptcha.com/api/v1/siteverify";
//        final String apikey = environment.getProperty("captcha.api.key");
//        final String sitekey = environment.getProperty("captcha.site.key");

//        // forward message to admin address
//        // TODO implement mailing logic


    }

}


