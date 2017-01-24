package com.epam.autum.selection.command;

import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tapac on 22.01.2017.
 */
public class CommandProcessApplication implements ICommand{
    private static Logger log = LogManager.getLogger(CommandShowApplication.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String additional = request.getParameter(ADDITIONAL);

        switch (additional){
            case "accept":
                applicationAccept(request,response);
                break;

            case "decline":
                applicationDecline(request, response);
                break;

            default:
                break;
        }
        String page = (new CommandShowFaculty()).execute(request,response);
        return page;
    }
    private void applicationAccept(HttpServletRequest request, HttpServletResponse response){
        try {
            int applicationID = Integer.parseInt(request.getParameter(USER_APPLICATION));
            ApplicationLogic.acceptApplication(applicationID);
        } catch (LogicException | NumberFormatException e) {
            log.error(e);
        }
    }

    private void applicationDecline(HttpServletRequest request, HttpServletResponse response){
        try {
            int applicationID = Integer.parseInt(request.getParameter(USER_APPLICATION));
            ApplicationLogic.declineApplication(applicationID);
        } catch (LogicException | NumberFormatException e) {
            log.error(e);
        }
    }
}
