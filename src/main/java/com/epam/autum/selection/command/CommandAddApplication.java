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

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute(USER_APPLICATION)!= null) {
            return (new CommandShowFaculty()).execute(request,response);
        }
        String page = null;
        HttpSession session = request.getSession();
        String description = "";
        Integer userID    = null;
        Integer facultyID = null;
        try {
            facultyID = Integer.parseInt(request.getParameter(ID));
            userID = ((User) session.getAttribute(USER)).getId();
            description = request.getParameter(DESCRIPTION);
        }catch (Exception e) {
            log.error(e);
        }
        ValidationResult result;
        try {
            result = ApplicationLogic.checkMarkForFaculty(userID,facultyID);
            switch (result){
                case ALL_RIGHT:
                    result = ApplicationLogic.addApplication(userID,facultyID,description);
                    if (result == ValidationResult.ALL_RIGHT)
                        log.info("Application add:" + result);
                    break;
                case MISSING_MARK:
                    request.setAttribute(MISSING_MARK, true); break;
                case LOW_MARK:
                    request.setAttribute(LOW_MARK, true); break;
                default:
                    break;
            }

        } catch (LogicException e) {
            log.error(e);
        }
        page = (new CommandShowFaculty()).execute(request,response);
        return page;
    }
}
