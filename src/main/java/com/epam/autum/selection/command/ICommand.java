package com.epam.autum.selection.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Тарас on 08.01.2017.
 */
public interface ICommand {

    String ID = "id";
    String USER = "user";
    String EMAIL = "email";
    String PASSWORD = "password";
    String REGISTER = "register";
    String MARKS = "marks";
    String MARK = "mark";
    String APPLICANT = "applicant";
    String APPLICATIONS = "applications";
    String USER_APPLICATION = "userapp";
    String FACULTIES = "faculties";
    String FACULTY = "faculty";
    String FACULTY_SUBJECTS = "facultysubjects";
    String SUBJECTS = "subjects";
    String MISSING_MARK = "missingmark";
    String LOW_MARK = "lowmark";
    String SUBJECT = "subject";
    String DESCRIPTION = "description";
    String ADDITIONAL = "additional";
    String LANGUAGE = "language";

    String MESSAGE = "message";

    /*Paramrters for user information*/
    String PARAMETER_FIRST_NAME = "firstName";
    String PARAMETER_LAST_NAME = "lastName";
    String PARAMETER_ADDRESS = "address";
    String PARAMETER_SEX = "sex";
    String PARAMETER_PHONE = "phone";
    String PARAMETER_EMAIL = "email";
    String PARAMETER_PASSWORD = "password_one";
    String PARAMETER_PASSWORD_TWO = "password_two";
    String PARAMETER_YEAR  = "year";
    String PARAMETER_MONTH = "month";
    String PARAMETER_DAY   = "day";

    String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
