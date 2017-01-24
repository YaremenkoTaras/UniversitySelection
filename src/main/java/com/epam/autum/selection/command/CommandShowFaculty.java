package com.epam.autum.selection.command;

import com.epam.autum.selection.database.dto.ApplicationDTO;
import com.epam.autum.selection.database.dto.SubjectDTO;
import com.epam.autum.selection.database.entity.Faculty;
import com.epam.autum.selection.database.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.ApplicationLogic;
import com.epam.autum.selection.service.FacultyLogic;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.SubjectLogic;
import com.epam.autum.selection.util.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tapac on 14.01.2017.
 */
public class CommandShowFaculty implements ICommand {

    private static Logger log = LogManager.getLogger(CommandShowFaculty.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTIES_PAGE);
        try {
            int userRoleID = ((User)request.getSession().getAttribute(USER)).getRoleID();
            switch (userRoleID){
                case 1:
                    loadAdminAttributes(request, response);
                    page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTY_COMMISSION_PAGE);
                    break;

                case 2:
                    loadUserAttributes(request, response);
                    page = PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTY_ADMISSION_PAGE);
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
        List<ApplicationDTO> applications = ApplicationLogic.findApplicationsByFaculty(id);

        request.setAttribute(FACULTY, faculty);
        request.setAttribute(APPLICATIONS, applications);
    }

    private void loadUserAttributes(HttpServletRequest request, HttpServletResponse response) throws LogicException {

        int id = Integer.parseInt(request.getParameter(ID));
        User user = (User) request.getSession().getAttribute(USER);
        int userID = user.getId();

        Faculty faculty = FacultyLogic.findFacultyByID(id);
        List<SubjectDTO> subjects = SubjectLogic.getSubjectsByFaculty(id);
        List<ApplicationDTO> applications = ApplicationLogic.findApplicationsByFaculty(id);
        ApplicationDTO application = ApplicationLogic.findApplication(userID, faculty.getId());;

        ValidationResult result = ApplicationLogic.checkMarkForFaculty(userID,faculty.getId());
        switch (result){
            case MISSING_MARK:
                request.setAttribute(MISSING_MARK, true); break;
            case LOW_MARK:
                request.setAttribute(LOW_MARK, true); break;
            default: break;
        }
        request.setAttribute(FACULTY, faculty);
        request.setAttribute(FACULTY_SUBJECTS, subjects);
        request.setAttribute(APPLICATIONS, applications);
        request.setAttribute(USER_APPLICATION, application);
    }
}
