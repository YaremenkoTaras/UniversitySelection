package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.ApplicantMark;
import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.service.MarkLogic;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.UserLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

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
                switch (user.getRoleID()) {
                    case 1:
                        page = PageConfigurator.getConfigurator().getPage(ADMIN_PAGE); break;
                    case 2:
                        page = PageConfigurator.getConfigurator().getPage(APPLICANT_PAGE); break;
                    case 3:
                        page = PageConfigurator.getConfigurator().getPage(GUEST_PAGE); break;
                }
                loadAttribute(request,response,user);

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

    private void loadAttribute(HttpServletRequest request, HttpServletResponse response, User user) throws LogicException {

        HttpSession session = request.getSession(true);

        if(session.getAttribute("reload") != null &&
                session.getAttribute("reload").equals("TRUE")){
            reloadUser(user);
        }
        session.setAttribute(USER, user);

        if (user.getRoleID() == 2){
            List<ApplicantMark> marks = MarkLogic.getMarksByUser(user.getId());
            List<Application> applications = ApplicationLogic.findApplicationsByUser(user.getId());
            request.setAttribute(MARKS,marks);
            request.setAttribute(APPLICATIONS,applications);
        }
    }

    private User reloadUser(User user) throws LogicException {
        return UserLogic.findUser(user.getId()).orElseThrow(LogicException::new);
    }
}
