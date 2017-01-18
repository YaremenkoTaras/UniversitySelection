package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.ApplicantMark;
import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.service.MarkLogic;
import com.epam.autum.selection.service.PageConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tapac on 18.01.2017.
 */
public class CommandShowApplicant implements ICommand {
    private static Logger log = LogManager.getLogger(CommandShowApplicant.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
        if (request.getSession().getAttribute(USER) == null)
            page = PageConfigurator.getConfigurator().getPage(PageConfigurator.LOGIN_PAGE);
        try {
            loadAttributes(request, response);
        } catch (LogicException e) {
            log.error(e);
        }
        return page;
    }

    private void loadAttributes(HttpServletRequest request, HttpServletResponse response) throws LogicException {
        User user = (User) request.getSession().getAttribute(USER);

        List<ApplicantMark> marks = MarkLogic.getMarksByUser(user.getId());
        List<Application> applications = ApplicationLogic.findApplicationsByUser(user.getId());
        request.setAttribute(MARKS, marks);
        request.setAttribute(APPLICATIONS, applications);
    }
}
