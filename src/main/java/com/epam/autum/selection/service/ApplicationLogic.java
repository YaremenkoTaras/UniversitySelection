package com.epam.autum.selection.service;

import com.epam.autum.selection.dao.daofactory.DaoFactory;
import com.epam.autum.selection.dao.interfaces.IApplicantMarkDAO;
import com.epam.autum.selection.dao.interfaces.IApplicationDAO;
import com.epam.autum.selection.dao.interfaces.IFacultySubjectDAO;
import com.epam.autum.selection.database.ConnectionPool;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.ApplicantMark;
import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.entity.FacultySubject;
import com.epam.autum.selection.exception.DAOException;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.util.DateConverter;
import com.epam.autum.selection.util.ValidationResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 09.01.2017.
 */
public class ApplicationLogic {

    private static boolean hasAllSubjectMark(List<ApplicantMark> markList, List<FacultySubject> subjectList) throws LogicException {

        List<Integer> listMark = new ArrayList<>();
        List<Integer> listSubj = new ArrayList<>();

        markList.forEach(v->listMark.add(v.getSubjectID()));
        subjectList.forEach(s->listSubj.add(s.getSubjectID()));

        return markList.containsAll(listSubj);
    }

    private static int hasAllMinMark(List<ApplicantMark> markList, List<FacultySubject> subjectList){
        int sum = 0;
        for (FacultySubject fs : subjectList) {
            for (ApplicantMark m : markList){
                if (fs.getSubjectID() == m.getSubjectID()){
                    if (m.getMark() > fs.getMinMark())
                        sum += m.getMark();
                    else return -1;
                }
            }
        }
        return sum;
    }

    public static int findOverall(int userID, int facultyID) throws LogicException {
        int overall = 0;

        Optional<WrapperConnection> connectionOptional;
        try{
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);

            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            IFacultySubjectDAO subjectDAO = DaoFactory.createFacultySubjectDAO(connection);

            List<ApplicantMark> markList = markDAO.findMarkByUser(userID);
            List<FacultySubject> subjectList = subjectDAO.findSubjectsByFaculty(facultyID);

            if(hasAllSubjectMark(markList,subjectList))
                overall = hasAllMinMark(markList,subjectList);
        }catch (SQLException e){
            throw new LogicException("DB connection error : " , e);
        }catch (DAOException e){
            throw new LogicException(e);
        }
        return overall;
    }

    public static ValidationResult addApplication(int userID, int facultyID, String description) throws LogicException {
        ValidationResult result;

        int overall = findOverall(userID, facultyID);

        if (overall == 0)
            result = ValidationResult.MISSING_MARK;
        else if (overall == -1)
            result = ValidationResult.LOW_MARK;
        else {
            Optional<WrapperConnection> connectionOptional = Optional.empty();
            try {
                connectionOptional = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);
                IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
                Application application = new Application(0, DateConverter.getCurrentDate(),description,overall,facultyID,userID,2);
                applicationDAO.create(application);
                result = ValidationResult.ALL_RIGHT;
            } catch (SQLException e) {
                throw new LogicException("DB connection error : ", e);
            } catch (DAOException e) {
                throw new LogicException(e);
            }
        }
        return result;
    }

    public static List<Application> findApplicationsByUser(int userID) throws LogicException {
        List<Application> applications;

        Optional<WrapperConnection> optConnection;
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            applications = markDAO.findApplicationsByUser(userID);
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }

        return applications;
    }

    public static Application findApplication(int userID, int facultyId) throws LogicException {
        Application application;

        Optional<WrapperConnection> optConnection;
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            application = markDAO.findApplicationByUserFaculty(userID,facultyId).orElse(null);
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return application;
    }

    public static List<Application> findApplicationsByFaculty(int facultyID) throws LogicException {
        List<Application> applications;

        Optional<WrapperConnection> optConnection;
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            applications = markDAO.findApplicationsByFaculty(facultyID);
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return applications;
    }


}
