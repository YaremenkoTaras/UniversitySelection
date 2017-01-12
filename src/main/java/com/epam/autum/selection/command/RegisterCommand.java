/*
package com.epam.autum.selection.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RegisterCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RegisterCommand.class);

    private static final String REGISTER = "register";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        FormType formType = FormType.valueOf(((String) request.getSession().getAttribute(FORM)).toUpperCase());
        if (!FormType.REGISTER.equals(formType)) {
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            String repeatPassword = request.getParameter(REPEAT_PASSWORD);
            String email = request.getParameter(EMAIL);
            User user = new User();
            try {
                ValidationResult result = UserLogic.register(login, password, repeatPassword, email, user);
                switch (result) {
                    case ALL_RIGHT:
                        request.getSession().setAttribute(USER_ID, user.getId());
                        request.getSession().setAttribute(ROLE, user.getRoleID());
                        request.getSession().setAttribute(FORM, REGISTER);
                        List<Movie> list = MovieLogic.takeLatestAddedMovies(SIZE_MOVIE);
                        request.setAttribute(LATEST_MOVIES, list);
                        list = MovieLogic.takeTopMovies(SIZE_MOVIE);
                        request.setAttribute(TOP_MOVIES, list);
                        List<String> movieNames = new ArrayList<>();
                        List<Review> reviewList = ReviewLogic.takeLatestReviews(SIZE_REVIEW, movieNames);
                        request.setAttribute(REVIEWS, reviewList);
                        request.setAttribute(MOVIES, movieNames);
                        page = ConfigurationManager.getProperty("path.page.main");
                        break;
                    case LOGIN_PASS_INCORRECT:
                        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                        break;
                    case EMAIL_INCORRECT:
                        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.emailerror"));
                        break;
                    case PASS_NOT_MATCH:
                        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.passnotmatch"));
                        break;
                    case LOGIN_NOT_UNIQUE:
                        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginduplicate"));
                        break;
                    case EMAIL_NOT_UNIQUE:
                        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.emailduplicate"));
                        break;
                    case UNKNOWN_ERROR:
                        request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.unknown"));
                        break;
                }
                if (result != ValidationResult.ALL_RIGHT) {
                    request.setAttribute(LOGIN, login);
                    request.setAttribute(PASSWORD, password);
                    request.setAttribute(REPEAT_PASSWORD, repeatPassword);
                    request.setAttribute(EMAIL, email);
                    page = ConfigurationManager.getProperty("path.page.register");
                }
            } catch (LogicException e) {
                log.error(e);
                page = ConfigurationManager.getProperty("path.page.error");
            }
        } else {
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}
*/
