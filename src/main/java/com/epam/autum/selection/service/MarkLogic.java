package com.epam.autum.selection.service;

import com.epam.autum.selection.dao.daofactory.DaoFactory;
import com.epam.autum.selection.dao.interfaces.IApplicantMarkDAO;
import com.epam.autum.selection.database.ConnectionPool;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.ApplicantMark;
import com.epam.autum.selection.exception.DAOException;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.util.ValidationResult;
import com.epam.autum.selection.util.Validator;

import java.sql.SQLException;
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
                ApplicantMark applicantMark = new ApplicantMark(0,mark,userID,subjectID);
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

    public static Optional<ApplicantMark> getMark(int id) throws LogicException {
        ApplicantMark mark;

        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            mark = markDAO.findEntityById(id).get();
        }catch (SQLException e){
            throw new LogicException("DB connection error : ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        }finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return Optional.ofNullable(mark);
    }

    public static List<ApplicantMark> getMarksByUser(int userID) throws LogicException {
        List<ApplicantMark> marks;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IApplicantMarkDAO markDAO = DaoFactory.createApplicantMarkDAO(connection);
            marks = markDAO.findMarkByUser(userID);
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
