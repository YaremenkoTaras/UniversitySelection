package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.UserLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ICommand{

    private static Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        try {
            User user = UserLogic.checkEmail(login, pass);
            if (user != null) {
                request.getSession().setAttribute(USER_ID, user.getId());
                request.getSession().setAttribute(ROLE, user.getRoleID());
                page = "/jsp/main.jsp";
            } else {
                request.setAttribute(LOGIN, login);
                request.setAttribute(PASSWORD, pass);
                /*request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");*/
                page = "signin";
            }
        } catch (LogicException e) {
            log.error(e);

        }
        return page;
    }
}
