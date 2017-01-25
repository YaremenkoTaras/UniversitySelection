package com.epam.autum.selection.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tapac on 25.01.2017.
 */
public class CommandChangeLanguage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String language = request.getParameter(LANGUAGE);

        request.setAttribute(LANGUAGE,language);

        //return PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
        return "/WEB-INF/index.jsp";
    }
}
