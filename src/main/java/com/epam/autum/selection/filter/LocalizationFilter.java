package com.epam.autum.selection.filter;

import com.epam.autum.selection.localization.Localizer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if (request.getParameter("locale") != null) {
            session.setAttribute("locale", request.getParameter("locale"));
            session.setAttribute("content", Localizer.
                    loadResources(Localizer.getLocale((String) session.getAttribute("locale"))));
        } else if (session.getAttribute("content") == null || session.getAttribute("locale") == null) {
            session.setAttribute("content", Localizer.loadResources(httpRequest.getLocale()));
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
