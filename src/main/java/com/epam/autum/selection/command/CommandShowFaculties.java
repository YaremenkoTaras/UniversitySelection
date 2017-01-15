package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.Faculty;
import com.epam.autum.selection.exception.LogicException;
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
public class CommandShowFaculties implements ICommand{

    private static Logger log = LogManager.getLogger(CommandShowFaculties.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {

        try {
            loadAttributes(request,responce);
        } catch (LogicException e) {
            log.error(e);
        }

        return PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTIES_PAGE);
    }

    private void loadAttributes(HttpServletRequest request, HttpServletResponse responce) throws LogicException {
        List<Faculty> faculties = FacultyLogic.findAllFaculties();
        request.setAttribute(FACULTIES, faculties);
    }
}