package com.epam.autum.selection.command;

import com.epam.autum.selection.entity.ApplicantMark;
import com.epam.autum.selection.entity.Subject;
import com.epam.autum.selection.entity.User;
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

        String parameter = request.getParameter("add");
        System.err.println("\n\n"+parameter+"\n\n");

        try {
            if(parameter != null && parameter.equals("true")) {
                int subjectID = Integer.parseInt(request.getParameter(SUBJECT));
                int userID = ((User) request.getSession().getAttribute(USER)).getId();
                int mark = Integer.parseInt(request.getParameter(MARK));
                MarkLogic.addMark(userID, subjectID, mark);
            }
            loadAttributes(request,response);
        } catch (LogicException e) {
            log.error(e);
        } catch (Exception e){
            log.error(e);
        }

        return PageConfigurator.getConfigurator().getPage(PageConfigurator.ADD_MARK_PAGE);
    }

    private void loadAttributes(HttpServletRequest request, HttpServletResponse response) throws LogicException {

        int userID = ((User)request.getSession().getAttribute(USER)).getId();

        List<Subject> subjectList = SubjectLogic.getSubjects();
        List<ApplicantMark> marks = MarkLogic.getMarksByUser(userID);
        List<Subject> subjects  = new ArrayList<>();
        for (Subject subj : subjectList) {
            boolean res = false;
            for (ApplicantMark mark : marks) {
                if (subj.getId() == mark.getSubjectID()){
                    res = true;
                }
            }
            if (!res){
                subjects.add(subj);
            }
        }
        request.setAttribute(MARKS,marks);
        request.setAttribute(SUBJECTS,subjects);
        System.out.println(subjectList);
        System.out.println(subjects);

    }
}
