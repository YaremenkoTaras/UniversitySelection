package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.User;
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
public class CommandAddApplication implements ICommand {

    private static Logger log = LogManager.getLogger(CommandAddApplication.class);

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();

        int facultyID = Integer.parseInt(request.getParameter("id"));
        int userID = ((User) session.getAttribute(USER)).getId();
        String description = "";

        ValidationResult result = ValidationResult.UNKNOWN_ERROR;
        try {
            result = ApplicationLogic.addApplication(userID, facultyID, description);
        } catch (LogicException e) {
            log.error(e);
        }
        switch (result){
            case ALL_RIGHT: page = (new CommandShowFaculty()).execute(request,responce); break;

            default: break;
        }

        return page;
    }
}
