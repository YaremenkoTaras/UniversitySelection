package com.epam.autum.selection.command;

import com.epam.autum.selection.service.PageConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tapac on 14.01.2017.
 */
public class CommandRegister implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loadAttribute(request, response);

        return PageConfigurator.getConfigurator().getPage(PageConfigurator.REGISTRATION_PAGE);
    }

    private void loadAttribute(HttpServletRequest request, HttpServletResponse response){
        String parameter = request.getParameter(ADDITIONAL);
        switch (parameter){
            case "user":
                request.setAttribute(ID,2);
                break;
            case "admin":
                request.setAttribute(ID,1);
                break;
            default:
                break;
        }
    }
}
