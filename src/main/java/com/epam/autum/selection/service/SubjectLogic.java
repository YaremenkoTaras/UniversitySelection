package com.epam.autum.selection.service;

import com.epam.autum.selection.database.dao.daofactory.DaoFactory;
import com.epam.autum.selection.database.dao.interfaces.IFacultySubjectDAO;
import com.epam.autum.selection.database.dao.interfaces.ISubjectDAO;
import com.epam.autum.selection.database.connection.ConnectionPool;
import com.epam.autum.selection.database.connection.WrapperConnection;
import com.epam.autum.selection.database.dto.FacultySubjectDTO;
import com.epam.autum.selection.database.entity.FacultySubject;
import com.epam.autum.selection.database.entity.Subject;
import com.epam.autum.selection.exception.DAOException;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.util.ValidationResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 09.01.2017.
 */
public class SubjectLogic {

    public static List<FacultySubject> getSubjectsByFaculty(int facultyID) throws LogicException {
        List<FacultySubject> subjectList = new ArrayList<>();
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IFacultySubjectDAO facultySubjectDAO = DaoFactory.createFacultySubjectDAO(connection);
            ISubjectDAO subjectDAO = DaoFactory.createSubjectDAO(connection);
            List<FacultySubjectDTO> facultySubjectDTOs = facultySubjectDAO.findSubjectsByFaculty(facultyID);
            List<Subject> subjects = subjectDAO.findAll();
            facultySubjectDTOs.forEach(fs -> {
                for (Subject s : subjects) {
                    if (fs.getSubjectID() == s.getId())
                        subjectList.add(new FacultySubject(fs,s));
                }
            });
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return subjectList;
    }

    public static List<Subject> getSubjects() throws LogicException {
        List<Subject> subjectList = new ArrayList<>();
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ISubjectDAO dao = DaoFactory.createSubjectDAO(connection);
            subjectList = dao.findAll();
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return subjectList;
    }

    public static ValidationResult createSubject(String subjectName) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ISubjectDAO dao = DaoFactory.createSubjectDAO(connection);
            if (dao.isExist(subjectName)) {
                result = ValidationResult.SUBJECT_ALREADY_EXIST;
            } else {
                Subject subject = new Subject(0, subjectName);
                dao.create(subject);
                result = ValidationResult.ALL_RIGHT;
            }
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

}
