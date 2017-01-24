package com.epam.autum.selection.command;

import com.epam.autum.selection.database.dto.MarkDTO;
import com.epam.autum.selection.database.entity.Subject;
import com.epam.autum.selection.database.entity.User;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.service.MarkLogic;
import com.epam.autum.selection.service.PageConfigurator;
import com.epam.autum.selection.service.SubjectLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tapac on 18.01.2017.
 */
public class CommandAddMark implements ICommand {
    private static Logger log = LogManager.getLogger(CommandAddMark.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String parameter = request.getParameter("additional");
        switch (parameter){
            case "add":
                int subjectID = Integer.parseInt(request.getParameter(SUBJECT));
                int userID = ((User) request.getSession().getAttribute(USER)).getId();
                int mark = Integer.parseInt(request.getParameter(MARK));
                try {
                    MarkLogic.addMark(userID, subjectID, mark); //Try to make new method for this? clear code
                } catch (LogicException e) {
                    log.error(e);
                }
                loadAttributes(request, response);
                break;
            case "show":
                loadAttributes(request, response);
                break;
            default:
                break;
        }
        return PageConfigurator.getConfigurator().getPage(PageConfigurator.ADD_MARK_PAGE);
    }

    private void loadAttributes(HttpServletRequest request, HttpServletResponse response){
        int userID = ((User) request.getSession().getAttribute(USER)).getId();
        List<Subject> subjectList = null;
        List<MarkDTO> marks = null;
        List<Subject> subjects = new ArrayList<>();

        try {
            subjectList = SubjectLogic.getSubjects();
            marks = MarkLogic.getMarksByUser(userID);
        } catch (LogicException e) {
            log.error(e);
        }
        for (Subject subj : subjectList) {
            boolean res = false;
            for (MarkDTO mark : marks) {
                if (subj.getName().equals(mark.getSubject())) {
                    res = true;
                }
            }
            if (!res) {
                subjects.add(subj);
            }
        }
        request.setAttribute(MARKS, marks);
        request.setAttribute(SUBJECTS, subjects);
    }
}
