package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.UserLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.autum.selection.service.PageConfigurator.LOGIN_PAGE;
import static com.epam.autum.selection.service.PageConfigurator.REGISTRATION_PAGE;

public class CommandLogin implements ICommand{

    private static Logger log = LogManager.getLogger(CommandLogin.class);
    private String page = null;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        try {
            User user = UserLogic.checkEmail(login, pass);
            if (user != null) {
                request.getSession().setAttribute(USER_ID, user.getId());
                request.getSession().setAttribute(ROLE, user.getRoleID());
                page = PageConfigurator.getConfigurator().getPage(LOGIN_PAGE);
            } else {
                request.setAttribute(LOGIN, login);
                request.setAttribute(PASSWORD, pass);

                page = PageConfigurator.getConfigurator().getPage(REGISTRATION_PAGE);
            }
        } catch (LogicException e) {
            log.error(e);

        }
        return page;
    }
}
