package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.User;
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

/**
 * Created by Tapac on 18.01.2017.
 */
public class CommandChangeUser implements ICommand {
    private static Logger log = LogManager.getLogger(CommandChangeUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        String add = request.getParameter(ADDITIONAL);
        switch (add) {
            case "edit":
                loadEditAttribute(request, response);
                page = PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_USER_INFORMATION_PAGE);
                break;
            case "submit":
                page = updateUser(request, response);
                break;
            default:
                page = PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
                break;
        }
        return page;
    }

    private void loadEditAttribute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute(USER);
        request.setAttribute(PARAMETER_ADDRESS, user.getAddress());
        request.setAttribute(PARAMETER_EMAIL, user.getEmail());
        request.setAttribute(PARAMETER_PHONE, user.getPhone());
    }

    private String updateUser(HttpServletRequest request, HttpServletResponse response) {
        String page;
        User user = (User) request.getSession().getAttribute(USER);
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;

        String address = request.getParameter(PARAMETER_ADDRESS);
        String email = request.getParameter(PARAMETER_EMAIL);
        String phone = request.getParameter(PARAMETER_PHONE);
        String pass = request.getParameter(PARAMETER_PASSWORD);
        String newPass = request.getParameter(PARAMETER_PASSWORD_TWO);
        User newUser = null;
        try {
            newUser = UserLogic.checkEmail(user.getEmail(), pass);
            if (newUser != null) {
                newUser.setAddress(address);
                newUser.setPhone(phone);
                newUser.setPassword(newPass);
                if (newUser.getEmail().equals(email)) {
                    result = UserLogic.editUser(newUser);
                }else if(UserLogic.isEmailFree(email)){
                    newUser.setEmail(email);
                    result = UserLogic.editUser(newUser);
                }else {
                    result = ValidationResult.EMAIL_NOT_UNIQUE;
                }
            }

        } catch (LogicException e) {
            log.error(e);
        }
        switch (result) {
            case ALL_RIGHT:
                request.getSession().setAttribute(USER,newUser);
                page = (new CommandShowApplicant()).execute(request, response);
                break;
            case EMAIL_NOT_UNIQUE:
                loadEditAttribute(request, response);
                request.setAttribute(DESCRIPTION, ValidationResult.EMAIL_NOT_UNIQUE);
                page = PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_USER_INFORMATION_PAGE);
                break;
            default:
                loadEditAttribute(request, response);
                page = PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_USER_INFORMATION_PAGE);
                break;
        }
        return page;
    }
}
