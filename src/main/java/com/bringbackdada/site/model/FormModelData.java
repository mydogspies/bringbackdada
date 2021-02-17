package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FormModelData {

    private String myfirstnome;
    private String andthelastone;
    private String emailmesomestuff;
    private String whoami;
    private String linkaway;


    @Override
    public String toString() {
        return "FormModelData{" +
                "first name='" + myfirstnome + '\'' +
                ", last name='" + andthelastone + '\'' +
                ", email address='" + emailmesomestuff + '\'' +
                ", message='" + whoami + '\'' +
                ", link='" + linkaway + '\'' +
                '}';
    }
}
