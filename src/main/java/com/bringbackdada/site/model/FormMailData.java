package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormMailData {

    private String firstName;
    private String lastName;
    private String mailAddress;
    private String message;

    @Override
    public String toString() {
        return "FormMailData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
