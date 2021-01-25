package com.bringbackdada.site.controllers;

import com.bringbackdada.site.mail.ContactMail;
import com.bringbackdada.site.mail.MailConfig;
import com.bringbackdada.site.mail.MailService;
import com.bringbackdada.site.model.FormMailData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for the contact page
 * @author mydogspies@zoho.com
 * @since 1.1.0
 */
@Controller
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final MailService sender;

    public ContactController(MailConfig config) {
        this.sender = new MailService(config.emailSender());
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
            System.out.println("FREAKING ERROR");
            // TODO throw exception
            return "404error"; // TODO should really return a 503
        } else {
            // check for bots
            if(!botcheck.isEmpty()) {
                // TODO return error
            }

            ContactMail contactMail = new ContactMail(
                    data.getYoursomeaddressmail(),
                    "admin@mydogspies.com",
                    "Contact form submission",
                    data.getTalktome());

            System.out.println(contactMail.toString());
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


