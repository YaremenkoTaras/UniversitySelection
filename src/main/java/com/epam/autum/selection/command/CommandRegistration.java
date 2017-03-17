package com.epam.autum.selection.command;

import com.epam.autum.selection.jdbc.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.UserLogic;
import com.epam.autum.selection.util.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by Tapac on 13.01.2017.
 */
public class CommandRegistration implements ICommand {

    private static Logger log = LogManager.getLogger(CommandRegistration.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;

        User user = createUser(request, response);


        try {
            result = UserLogic.register(user.getEmail(), user.getPassword(), user.getPassword(), user);
        } catch (LogicException e) {
            log.error(e);
        }
        switch (result) {
            case ALL_RIGHT:
                break;
            case EMAIL_NOT_UNIQUE:
                reloadAttributes(request, response);
                page = PageConfigurator.getConfigurator().getPage(PageConfigurator.REGISTRATION_PAGE);
                request.setAttribute(ID, request.getParameter(ID));
                request.setAttribute(REGISTER, true);
                break;
            case UNKNOWN_ERROR:
                log.error("Register:" + ValidationResult.UNKNOWN_ERROR);
                break;
            default:
                break;
        }
        return page;
    }

    private User createUser(HttpServletRequest request, HttpServletResponse response) {
        User newUser = new User();
        try {
            newUser.setName(request.getParameter(PARAMETER_FIRST_NAME) + " " + request.getParameter(PARAMETER_LAST_NAME));
            newUser.setAddress(request.getParameter(PARAMETER_ADDRESS));
            newUser.setEmail(request.getParameter(PARAMETER_EMAIL));
            newUser.setPhone(request.getParameter(PARAMETER_PHONE));
            newUser.setSex(request.getParameter(PARAMETER_SEX));
            newUser.setRoleID(Integer.valueOf(request.getParameter(ID)));
            newUser.setBirth(getDateOfBirth(request));
            newUser.setPassword(request.getParameter(PARAMETER_PASSWORD));
        } catch (NumberFormatException e) {
            log.error(e);
        }
        return newUser;
    }

    private Date getDateOfBirth(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getParameter(PARAMETER_YEAR) + "-");
        builder.append(request.getParameter(PARAMETER_MONTH) + "-");
        builder.append(request.getParameter(PARAMETER_DAY));
        return Date.valueOf(builder.toString());
    }

    private void reloadAttributes(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PARAMETER_FIRST_NAME, request.getParameter(PARAMETER_FIRST_NAME));
        request.setAttribute(PARAMETER_LAST_NAME , request.getParameter(PARAMETER_LAST_NAME));
        request.setAttribute(PARAMETER_ADDRESS, request.getParameter(PARAMETER_ADDRESS));
        request.setAttribute(PARAMETER_EMAIL, request.getParameter(PARAMETER_EMAIL));
        request.setAttribute(PARAMETER_PHONE, request.getParameter(PARAMETER_PHONE));
        request.setAttribute(PARAMETER_SEX, request.getParameter(PARAMETER_SEX));
        request.setAttribute(PARAMETER_PASSWORD,request.getParameter(PARAMETER_PASSWORD));
        request.setAttribute(PARAMETER_PASSWORD_TWO, request.getParameter(PARAMETER_PASSWORD_TWO));
        request.setAttribute(PARAMETER_DAY, request.getParameter(PARAMETER_DAY));
        request.setAttribute(PARAMETER_MONTH, request.getParameter(PARAMETER_MONTH));
        request.setAttribute(PARAMETER_YEAR, request.getParameter(PARAMETER_YEAR));
    }
}
