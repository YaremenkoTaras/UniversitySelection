package com.epam.autum.selection.service;

import com.epam.autum.selection.dao.daofactory.DaoFactory;
import com.epam.autum.selection.dao.interfaces.IUserDAO;
import com.epam.autum.selection.database.ConnectionPool;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.DAOException;
import com.epam.autum.selection.exception.LogicException;
import com.epam.autum.selection.util.ValidationResult;
import com.epam.autum.selection.util.Validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A service layer class implementing all the logic concerning users.
 */
public class UserLogic {
    /**
     * Folder where all profile photos are saved.
     */
    private static final String REGEXP_PASSWORD = "(\\w|\\d){3,20}";
    private static final String REGEXP_EMAIL_FORMAT = "(\\w|\\.|\\d)+@\\w+\\.\\w+";
    private static final String REGEXP_EMAIL_LENGTH = ".{4,40}";
    private static final String REGEXP_NAME = "([A-Z][A-Za-z]*([ -][A-Z][A-Za-z]*)*)|([А-Я][А-Яа-я]*([ -][А-Я][А-Яа-я]*)*)";
    private static final String REGEXP_NAME_LENGTH = ".{1,30}";
    /**
     * Checks user's login and password.
     *
     * @param enterEmail entered login
     * @param enterPass  entered password
     * @return user if login and password are correct, null otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static User checkEmail(String enterEmail, String enterPass) throws LogicException {
        User user = null;
        if (!validateEmail(enterEmail) || !validatePassword(enterPass)) {
            return null;
        }
        String md5password = enterPass;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            Optional<String> optPassword = userDAO.findPasswordByEmail(enterEmail);
            if (optPassword.isPresent()) {
                String pass = optPassword.get();
                if (pass.equals(md5password)) {
                    user = userDAO.findEntityByEmail(enterEmail).get();
                }
            }
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return user;
    }

    /**
     * Checks if passwords match.
     *
     * @param password string with a password
     * @param repeatPassword string with repeated password
     * @return true if both passwords are not empty and password matches repeated password, false otherwise
     */
    public static boolean checkRepeatPassword(String password, String repeatPassword) {
        return password != null && !password.isEmpty() && repeatPassword != null && !repeatPassword.isEmpty() &&
                validatePassword(password) && validatePassword(repeatPassword) && password.equals(repeatPassword);
    }

    /**
     * Finds the user with a specified id.
     *
     * @param userId user id
     * @return user, wrapped in Optional if exists, Optional.empty() otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static Optional<User> findUser(int userId) throws LogicException {
        Optional<User> user = null;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            user = userDAO.findEntityById(userId);
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return user;
    }

    /**
     * Edits the user.
     *
     * @param user user to edit
     * @return result of user validation and editing
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    private static ValidationResult editUser(User user) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;
        if (!validateName(user.getName())) {
            result = ValidationResult.NAME_INCORRECT;
        } else if (!validateEmail(user.getEmail())) {
            result = ValidationResult.EMAIL_INCORRECT;
        } else {
            Optional<WrapperConnection> optConnection = Optional.empty();
            try {
                optConnection = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
                IUserDAO userDAO = DaoFactory.createUserDAO(connection);
                userDAO.update(user);

                result = ValidationResult.ALL_RIGHT;
            } catch (SQLException e) {
                throw new LogicException("DB connection error: ", e);
            } catch (DAOException e) {
                throw new LogicException(e);
            } finally {
                optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
            }
        }
        return result;
    }

    /**
     * Deletes the user.
     *
     * @param userId user id
     * @return true if the user was deleted, false otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static boolean deleteUser(int userId) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            result = userDAO.delete(userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    /**
     * Retrieves all users registered in the system.
     *
     * @return list of all users registered in the system
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static List<User> takeAllUsers() throws LogicException {
        List<User> userList = new ArrayList<>();
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            userList = userDAO.findAll();
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return userList;
    }

    /**
     * Resgisters the user in the system.
     *
     * @param password password of the user
     * @param repeatPassword repeated password
     * @param email email of the user
     * @param user returned object User, should be empty on call
     * @return result of user validation and registration
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static ValidationResult register(String email, String password, String repeatPassword, User user) throws LogicException {
        ValidationResult result;
        if (!validateEmail(email)) {
            result = ValidationResult.EMAIL_INCORRECT;
        } else if (!password.equals(repeatPassword)) {
            result = ValidationResult.PASS_NOT_MATCH;
        } else if (!validatePassword(password)){
            result = ValidationResult.PASS_INCORRECT;
        } else {
            Optional<WrapperConnection> optConnection = Optional.empty();
            try {
                optConnection = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
                IUserDAO userDAO = DaoFactory.createUserDAO(connection);
                if (userDAO.isEmailFree(email)) {

                    user.setEmail(email);
                    user.setPassword(password);
                    boolean registered = userDAO.create(user);
                    result = registered ? ValidationResult.ALL_RIGHT : ValidationResult.UNKNOWN_ERROR;

                } else {
                    result = ValidationResult.EMAIL_NOT_UNIQUE;
                }
            } catch (SQLException e) {
                throw new LogicException("DB connection error: ", e);
            } catch (DAOException e) {
                throw new LogicException(e);
            } finally {
                optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
            }
        }
        return result;
    }

    /**
     * Validates password. A password consists of from 3 to 20 english letters of digits.
     *
     * @param password String containing password
     * @return true if password is valid, false otherwise
     */
    private static boolean validatePassword(String password) {
        return Validator.validate(password, REGEXP_PASSWORD);
    }
    /**
     * Validates e-mail. An e-mail has format aaa@bbb.ccc Parts bbb, ccc consist of at least 1 english
     * letter. Part aaa consists of at least 1 english letter or digit. E-mail contains not more than 40 symbols.
     *
     * @param email String containing e-mail
     * @return true if e-mail is valid, false otherwise
     */
    private static boolean validateEmail(String email) {
        return Validator.validate(email, REGEXP_EMAIL_FORMAT, REGEXP_EMAIL_LENGTH);
    }

    /**
     * Validates name. A name contains either english or russian letters, spaces and hyphens. Every word after a
     * space or a hyphen begins with a capital letter. A name contains from 1 to 30 symbols.
     *
     * @param name String containing user name
     * @return true if name is valid, false otherwise
     */
    private static boolean validateName(String name) {
        return Validator.validate(name, REGEXP_NAME, REGEXP_NAME_LENGTH);
    }

}
