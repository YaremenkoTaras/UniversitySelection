package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.UserLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.autum.selection.service.PageConfigurator.*;

public class CommandLogin implements ICommand{

    private static Logger log = LogManager.getLogger(CommandLogin.class);
    private String page = null;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter(EMAIL);
        String pass = request.getParameter(PASSWORD);
        try {
            User user = UserLogic.checkEmail(email, pass);
            if (user != null) {
                request.getSession().setAttribute(USER_ID, user.getId());
                request.getSession().setAttribute(ROLE, user.getRoleID());
                switch (user.getRoleID()) {
                    case 1:
                        page = PageConfigurator.getConfigurator().getPage(ADMIN_PAGE); break;
                    case 2:
                        page = PageConfigurator.getConfigurator().getPage(APPLICANT_PAGE); break;
                    case 3:
                        page = PageConfigurator.getConfigurator().getPage(GUEST_PAGE); break;
                }
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
}
