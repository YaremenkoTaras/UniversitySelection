package com.epam.autum.selection.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Тарас on 08.01.2017.
 */
public interface ICommand {

    String execute(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException;


    String USER_ID = "user_id";
    String USER = "user";
    String EMAIL = "email";
    String PASSWORD = "password";
    String REGISTER = "register";
    String MARKS = "marks";
    String APPLICATIONS = "applications";
    String FACULTIES = "faculties";
    String FACULTY = "faculty";


}
