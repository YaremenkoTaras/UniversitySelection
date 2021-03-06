package com.epam.autum.selection.service;

import com.epam.autum.selection.jdbc.connection.ConnectionPool;
import com.epam.autum.selection.jdbc.connection.WrapperConnection;
import com.epam.autum.selection.jdbc.dao.daofactory.DaoFactory;
import com.epam.autum.selection.jdbc.dao.interfaces.IApplicantMarkDAO;
import com.epam.autum.selection.jdbc.dao.interfaces.ISubjectDAO;
import com.epam.autum.selection.jdbc.dao.interfaces.IUserDAO;
import com.epam.autum.selection.jdbc.dto.ApplicantMarkDTO;
import com.epam.autum.selection.jdbc.entity.ApplicantMark;
import com.epam.autum.selection.jdbc.entity.Subject;
import com.epam.autum.selection.jdbc.entity.User;
import com.epam.autum.selection.exception.DAOException;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.util.ValidationResult;
import com.epam.autum.selection.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 09.01.2017.
 */
public class MarkLogic {

    private static final int MIN_MARK = 0;
    private static final int MAX_MARK = 200;
    private static final String REGEXP_MARK = "(\\d){1,3}";

    public static ValidationResult addMark(int userID, int subjectID, int mark) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;
        Optional<WrapperConnection> optConnection = Optional.empty();
        if (isValid(mark))
            try {
                optConnection = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
                IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
                ApplicantMarkDTO applicantMark = new ApplicantMarkDTO(0,mark,userID,subjectID);
                boolean add = markDAO.create(applicantMark);
                if (add){
                    result = ValidationResult.ALL_RIGHT;
                }
            }catch (SQLException e){
                throw new LogicException("DB connection error : ", e);
            } catch (DAOException e) {
                throw new LogicException(e);
            }finally {
                optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
            }
        return result;
    }

    private static boolean isValid(int mark){
        return (mark > MIN_MARK && mark <= MAX_MARK && Validator.validate(Integer.toString(mark),REGEXP_MARK));
    }

    public static ApplicantMark getMark(int id) throws LogicException {
        ApplicantMark mark = null;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            ISubjectDAO subjectDAO = DaoFactory.createSubjectDAO(connection);
            ApplicantMarkDTO markDTO = markDAO.findEntityById(id).get();
            User user = userDAO.findEntityById(markDTO.getUserID()).get();
            Subject subject = subjectDAO.findEntityById(markDTO.getSubjectID()).get();
            mark = new ApplicantMark(markDTO,user,subject);
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return mark;
    }

    public static List<ApplicantMark> getMarksByUser(int userID) throws LogicException {
        List<ApplicantMark> marks = new ArrayList<>();
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            ISubjectDAO subjectDAO = DaoFactory.createSubjectDAO(connection);
            List<ApplicantMarkDTO> applicantMarks = markDAO.findMarkByUser(userID);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            User user = userDAO.findEntityById(userID).get();
            List<Subject> subjects = subjectDAO.findAll();
            for(ApplicantMarkDTO mark : applicantMarks){
                for(Subject subject : subjects){
                    if(mark.getSubjectID() == subject.getId())
                        marks.add(new ApplicantMark(mark,user,subject));
                }
            }
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return marks;
    }

    public static boolean deleteMark(int markID) throws LogicException {
        boolean delete = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            delete = markDAO.delete(markID);
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return delete;
    }

}
