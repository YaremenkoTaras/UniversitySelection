package com.epam.autum.selection.command;

import com.epam.autum.selection.database.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.util.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Tapac on 15.01.2017.
 */
public class CommandAddApplication implements ICommand{

    private static Logger log = LogManager.getLogger(CommandAddApplication.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        loadAttribute(request, response);

        String page = (new CommandShowFaculty()).execute(request, response);
        return page;
    }

    private void loadAttribute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer userID = ((User) session.getAttribute(USER)).getId();
        Integer facultyID = Integer.parseInt(request.getParameter(ID));
        String description = request.getParameter(DESCRIPTION);
        try {
            ValidationResult result = ApplicationLogic.addApplication(userID, facultyID, description);
            if (result == ValidationResult.ALL_RIGHT)
                log.info("Application added:" + result);
        } catch (LogicException e) {
            log.error(e);
        }
    }
}
