package com.epam.autum.selection.zzzconsoletest;

import com.epam.autum.selection.util.Validator;

/**
 * Created by Tapac on 08.01.2017.
 */
public class TestValidator {

    private static final String REGEXP_EMAIL_FORMAT = "(\\w|\\.|\\d)+@\\w+\\.\\w+";
    private static final String REGEXP_EMAIL_LENGTH = ".{4,40}";

    public static void main(String[] args) {

        String email = "yarem.enko@gmail.com";

        System.out.println(
                Validator.validate(email,REGEXP_EMAIL_FORMAT,REGEXP_EMAIL_LENGTH)

        );
    }

}
