package com.epam.autum.selection.command;

import com.epam.autum.selection.database.dto.ApplicationDTO;
import com.epam.autum.selection.database.entity.Application;
import com.epam.autum.selection.database.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.UserLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tapac on 20.01.2017.
 */
public class CommandShowApplication implements ICommand {
    private static Logger log = LogManager.getLogger(CommandShowApplication.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICATION_PAGE);

        try {
            loadAttributes(request, response);
        } catch (LogicException e) {
            log.error(e);
        }

        return page;
    }

    private void loadAttributes(HttpServletRequest request, HttpServletResponse response) throws LogicException {
        int appID = Integer.parseInt(request.getParameter(ADDITIONAL));

        Application application = ApplicationLogic.findApplication(appID);
        User user = UserLogic.findUser(application.getUserID()).get();
        List<ApplicationDTO> applications = ApplicationLogic.findApplicationsByUser(user.getId());

        request.setAttribute(APPLICANT, user);
        request.setAttribute(USER_APPLICATION,application);
        request.setAttribute(APPLICATIONS,applications);
    }
}
