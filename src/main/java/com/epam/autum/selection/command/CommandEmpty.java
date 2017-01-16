package com.epam.autum.selection.command;

import com.epam.autum.selection.service.PageConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tapac on 08.01.2017.
 */
public class CommandEmpty implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PageConfigurator.getConfigurator().getPage(PageConfigurator.ERROR_PAGE);
    }
}
