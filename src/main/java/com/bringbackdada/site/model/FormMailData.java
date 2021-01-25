package com.bringbackdada.site.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormMailData {

    private String onenamefirst;
    private String twonamelast;
    private String yoursomeaddressmail;
    private String talktome;

    @Override
    public String toString() {
        return "FormMailData{" +
                "first name='" + onenamefirst + '\'' +
                ", last name='" + twonamelast + '\'' +
                ", email address='" + yoursomeaddressmail + '\'' +
                ", message='" + talktome + '\'' +
                '}';
    }
}
