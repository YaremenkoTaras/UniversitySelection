package com.epam.autum.selection.dao.interfaces;

import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.DAOException;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public interface IUserDAO extends IDAO<User> {

    Optional<String> findPasswordByEmail(String email) throws DAOException, SQLException;

    Optional<User> findEntityByEmail(String email) throws DAOException, SQLException;

    boolean isEmailFree(String email) throws DAOException;
}
