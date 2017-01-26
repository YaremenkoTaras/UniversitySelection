package com.epam.autum.selection.service;

import com.epam.autum.selection.database.connection.ConnectionPool;
import com.epam.autum.selection.database.connection.WrapperConnection;
import com.epam.autum.selection.database.dao.daofactory.DaoFactory;
import com.epam.autum.selection.database.dao.interfaces.*;
import com.epam.autum.selection.database.dto.ApplicantMarkDTO;
import com.epam.autum.selection.database.dto.ApplicationDTO;
import com.epam.autum.selection.database.dto.FacultySubjectDTO;
import com.epam.autum.selection.database.entity.Application;
import com.epam.autum.selection.database.entity.*;
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
        List<ApplicantMarkDTO> markList;
        List<FacultySubjectDTO> subjectList;

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
        for (FacultySubjectDTO fs : subjectList) {
            for (ApplicantMarkDTO m : markList) {
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

            List<ApplicantMarkDTO> markList = markDAO.findMarkByUser(userID);
            List<FacultySubjectDTO> subjectList = subjectDAO.findSubjectsByFaculty(facultyID);

            for (FacultySubjectDTO fs : subjectList) {
                for (ApplicantMarkDTO m : markList) {
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
            ApplicationDTO applicationDTO = new ApplicationDTO(0, DateConverter.getCurrentDate(), description, overall, facultyID, userID, 2);
            applicationDAO.create(applicationDTO);
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
        List<ApplicationDTO> applicationDTOs;
        List<Application> applications = new ArrayList<>();

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            applicationDTOs = markDAO.findApplicationsByUser(userID);
            for (ApplicationDTO app : applicationDTOs) {
                applications.add(new Application(
                        app,
                        facultyDAO.findEntityById(app.getFacultyID()).get(),
                        userDAO.findEntityById(app.getUserID()).get()
                ));
            }
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
        Application application = null;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            Optional<ApplicationDTO> applicationDTO = applicationDAO.findEntityById(applicationID);
            if (applicationDTO.isPresent()) {
                User user = userDAO.findEntityById(applicationDTO.get().getUserID()).get();
                Faculty faculty = facultyDAO.findEntityById(applicationDTO.get().getFacultyID()).get();

                application = new Application(
                        applicationDTO.get(),
                        faculty,
                        user
                );
            }
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
        Application application = null;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            Optional<ApplicationDTO> applicationDTO = applicationDAO.findApplicationByUserFaculty(userID, facultyId);
            Optional<User> user = userDAO.findEntityById(userID);
            Optional<Faculty> faculty = facultyDAO.findEntityById(facultyId);
            if (applicationDTO.isPresent())
                application = new Application(
                        applicationDTO.get(),
                        faculty.get(),
                        user.get()
                );
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
        List<ApplicationDTO> applicationDTOs;
        List<Application> applications = new ArrayList<>();

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicationDAO markDAO = DaoFactory.createApplicationDAO(connection);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            applicationDTOs = markDAO.findApplicationsByFaculty(facultyID);
            for (ApplicationDTO app : applicationDTOs) {
                applications.add(new Application(
                        app,
                        facultyDAO.findEntityById(app.getFacultyID()).get(),
                        userDAO.findEntityById(app.getUserID()).get()
                ));
            }
        } catch (SQLException e) {
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return applications;
    }

    public static ValidationResult acceptApplication(int applicationID) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;

        Optional<WrapperConnection> connectionOptional = Optional.empty();
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);
            connection.setAutoCommit(false);
            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            ApplicationDTO app = applicationDAO.findEntityById(applicationID).get();
            List<ApplicationDTO> applicationDTOList = applicationDAO.findApplicationsByUser(app.getUserID());
            boolean updated = true;
            for (ApplicationDTO applicationDTO : applicationDTOList) {
                if (applicationDTO.getId() == applicationID) {
                    applicationDTO.setStatusID(1);
                } else {
                    applicationDTO.setStatusID(3);
                }
                updated = (applicationDAO.update(applicationDTO) && updated);
            }
            connection.commit();
            connection.setAutoCommit(true);
            if (updated)
                result = ValidationResult.ALL_RIGHT;
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    public static ValidationResult declineApplication(int applicationID) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;

        Optional<WrapperConnection> connectionOptional = Optional.empty();
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);
            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            ApplicationDTO app = applicationDAO.findEntityById(applicationID).orElseThrow(LogicException::new);
            app.setStatusID(3);
            boolean updated = applicationDAO.update(app);
            if (updated)
                result = ValidationResult.ALL_RIGHT;

        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }
}
