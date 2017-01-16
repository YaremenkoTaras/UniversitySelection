package com.epam.autum.selection.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Тарас on 08.01.2017.
 */
public interface ICommand {

    String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;


    String ID = "id";
    String USER = "user";
    String EMAIL = "email";
    String PASSWORD = "password";
    String REGISTER = "register";
    String MARKS = "marks";
    String APPLICATIONS = "applications";
    String FACULTIES = "faculties";
    String FACULTY = "faculty";
    String USER_APPLICATION = "userapp";
    String FACULTY_SUBJECTS = "facultysubjects";
    String SUBJECTS = "subjects";
    String MISSING_MARK = "missingmark";
    String LOW_MARK = "lowmark";

}
