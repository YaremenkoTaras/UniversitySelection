package com.epam.autum.selection.command;

import com.epam.autum.selection.database.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.UserLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.epam.autum.selection.service.PageConfigurator.*;

public class CommandLogin implements ICommand {

    private static Logger log = LogManager.getLogger(CommandLogin.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String email = request.getParameter(EMAIL);
        String pass = request.getParameter(PASSWORD);
        try {
            User user = UserLogic.checkEmail(email, pass);
            if (user != null) {
                page = loadAttribute(request, response, user);
            } else {
                request.setAttribute(EMAIL, email);
                request.setAttribute(PASSWORD, pass);
                page = PageConfigurator.getConfigurator().getPage(LOGIN_PAGE);
            }
        } catch (LogicException e) {
            log.error(e);
        }
        return page;
    }

    private String loadAttribute(HttpServletRequest request, HttpServletResponse response, User user) throws LogicException, ServletException, IOException {
        String page;
        HttpSession session = request.getSession(true);
        session.setAttribute(USER, user);

        switch (user.getRoleID()){
            case 1:
                page = (new CommandShowAdmin()).execute(request,response);
                break;
            case 2:
                page = (new CommandShowApplicant()).execute(request,response);
                break;
            case 3:
                page = PageConfigurator.getConfigurator().getPage(GUEST_PAGE);
                break;
            default:
                page = PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
                break;
        }
        return page;
    }
}
