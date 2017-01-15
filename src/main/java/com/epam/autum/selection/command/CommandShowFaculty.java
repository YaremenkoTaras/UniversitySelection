package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.entity.Faculty;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.service.FacultyLogic;
import com.epam.autum.selection.service.PageConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tapac on 14.01.2017.
 */
public class CommandShowFaculty implements ICommand {

    private static Logger log = LogManager.getLogger(CommandShowFaculty.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTY_PAGE);

        try {
            loadAtributes(request,responce);
        } catch (LogicException e) {
            log.error(e);
        }

        return page;
    }

    private void loadAtributes(HttpServletRequest request, HttpServletResponse responce) throws LogicException {

        int id = Integer.parseInt(request.getParameter("id"));

        Faculty faculty = FacultyLogic.findFacultyByID(id);
        List<Application> applications = ApplicationLogic.findApplicationsByFaculty(id);

        request.setAttribute(FACULTY, faculty);
        request.setAttribute(APPLICATIONS, applications);
    }
}
