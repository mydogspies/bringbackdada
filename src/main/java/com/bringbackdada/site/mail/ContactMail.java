package com.bringbackdada.site.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This pojo is used for the contact form "/site/contact-bringbackdada"
 */
@Getter
@Setter
@AllArgsConstructor
public class ContactMail {

    private String from;
    private String to;
    private String subject;
    private String content;

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
