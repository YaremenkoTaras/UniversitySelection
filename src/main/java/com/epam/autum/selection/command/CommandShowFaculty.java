package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.entity.Faculty;
import com.epam.autum.selection.entity.FacultySubject;
import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.service.FacultyLogic;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.SubjectLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Tapac on 14.01.2017.
 */
public class CommandShowFaculty implements ICommand {

    private static Logger log = LogManager.getLogger(CommandShowFaculty.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTIES_PAGE);
        try {
            int userID = ((User)request.getSession().getAttribute(USER)).getId();
            switch (userID){
                case 1:
                    loadAdminAttributes(request, response);
                    page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTY_COMMISSION_PAGE);
                    break;

                case 2:
                    loadUserAttributes(request, response);
                    page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTY_PAGE);
                    break;

                case 3:
                    page = PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
                    break;

                default:
                    page = PageConfigurator.getConfigurator().getPage(PageConfigurator.INDEX_PAGE);
                    break;
            }
        } catch (LogicException e) {
            log.error(e);
        }
        return page;
    }

    private void loadAdminAttributes(HttpServletRequest request, HttpServletResponse response) throws LogicException {
        int id = Integer.parseInt(request.getParameter(ID));

        Faculty faculty = FacultyLogic.findFacultyByID(id);
        List<Application> applications = ApplicationLogic.findApplicationsByFaculty(id);

        request.setAttribute(FACULTY, faculty);
        request.setAttribute(APPLICATIONS, applications);
    }

    private void loadUserAttributes(HttpServletRequest request, HttpServletResponse response) throws LogicException {

        int id = Integer.parseInt(request.getParameter(ID));
        User user = (User) request.getSession().getAttribute(USER);
        int userID = user.getId();

        Faculty faculty = FacultyLogic.findFacultyByID(id);
        List<FacultySubject> subjects = SubjectLogic.getSubjectsByFaculty(id);
        List<Application> applications = ApplicationLogic.findApplicationsByFaculty(id);
        Application application = ApplicationLogic.findApplication(userID, id);

        request.setAttribute(FACULTY, faculty);
        request.setAttribute(FACULTY_SUBJECTS, subjects);
        request.setAttribute(APPLICATIONS, applications);
        request.setAttribute(USER_APPLICATION, application);
    }
}
