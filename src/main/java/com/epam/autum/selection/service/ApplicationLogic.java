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

    public static ValidationResult checkMarkForFaculty(int userID, int facultyID) throws LogicException {

        ValidationResult result = ValidationResult.UNKNOWN_ERROR;

        List<ApplicantMark> markList;
        List<FacultySubject> subjectList;

        Optional<WrapperConnection> connectionOptional = Optional.empty();
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);
            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            IFacultySubjectDAO subjectDAO = DaoFactory.createFacultySubjectDAO(connection);

            markList = markDAO.findMarkByUser(userID);
            subjectList = subjectDAO.findSubjectsByFaculty(facultyID);

            result = ValidationResult.ALL_RIGHT;
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }

        List<Integer> mark = new ArrayList<>();
        List<Integer> subj = new ArrayList<>();

        markList.forEach(v -> mark.add(v.getSubjectID()));
        subjectList.forEach(s -> subj.add(s.getSubjectID()));

        if (!mark.containsAll(subj))
            result = ValidationResult.MISSING_MARK;
        for (FacultySubject fs : subjectList) {
            for (ApplicantMark m : markList) {
                if (fs.getSubjectID() == m.getSubjectID() && m.getMark() < fs.getMinMark()) {
                    result = ValidationResult.LOW_MARK;
                }
            }
        }

        return result;
    }

    public static int findOverall(int userID, int facultyID) throws LogicException {
        int overall = 0;

        Optional<WrapperConnection> connectionOptional = Optional.empty();
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);

            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            IFacultySubjectDAO subjectDAO = DaoFactory.createFacultySubjectDAO(connection);

            List<ApplicantMark> markList = markDAO.findMarkByUser(userID);
            List<FacultySubject> subjectList = subjectDAO.findSubjectsByFaculty(facultyID);

            for (FacultySubject fs : subjectList) {
                for (ApplicantMark m : markList) {
                    if (fs.getSubjectID() == m.getSubjectID()) {
                        overall += m.getMark();
                    }
                }
            }
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return overall;
    }

    public static ValidationResult addApplication(int userID, int facultyID, String description) throws LogicException {

        ValidationResult result;

        int overall = findOverall(userID, facultyID);

        Optional<WrapperConnection> connectionOptional = Optional.empty();
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);
            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            Application application = new Application(0, DateConverter.getCurrentDate(), description, overall, facultyID, userID, 2);
            applicationDAO.create(application);
            result = ValidationResult.ALL_RIGHT;
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }

        return result;
    }

    public static List<Application> findApplicationsByUser(int userID) throws LogicException {
        List<Application> applications;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            applications = markDAO.findApplicationsByUser(userID);
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }

        return applications;
    }

    public static Application findApplication(int applicationID) throws LogicException {
        Application application;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            application = applicationDAO.findEntityById(applicationID).orElse(null);
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return application;
    }

    public static Application findApplication(int userID, int facultyId) throws LogicException {
        Application application;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            application = markDAO.findApplicationByUserFaculty(userID, facultyId).orElse(null);
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return application;
    }

    public static List<Application> findApplicationsByFaculty(int facultyID) throws LogicException {
        List<Application> applications;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            applications = markDAO.findApplicationsByFaculty(facultyID);
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return applications;
    }


}
