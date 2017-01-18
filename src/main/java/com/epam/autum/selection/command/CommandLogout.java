package com.epam.autum.selection.command;

import com.epam.autum.selection.service.PageConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tapac on 18.01.2017.
 */
public class CommandLogout implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(USER,null);
        return PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
    }
}
