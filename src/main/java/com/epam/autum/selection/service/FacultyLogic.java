package com.epam.autum.selection.service;

import com.epam.autum.selection.database.dao.daofactory.DaoFactory;
import com.epam.autum.selection.database.dao.interfaces.IFacultyDAO;
import com.epam.autum.selection.database.connection.ConnectionPool;
import com.epam.autum.selection.database.connection.WrapperConnection;
import com.epam.autum.selection.database.entity.Faculty;
import com.epam.autum.selection.exception.DAOException;
import com.epam.autum.selection.exception.LogicException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 14.01.2017.
 */
public class FacultyLogic {

    public static List<Faculty> findAllFaculties() throws LogicException {
        List<Faculty> faculties = new ArrayList<>();
        Optional<WrapperConnection> connectionOptional = Optional.empty();
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = connectionOptional.orElseThrow(SQLException::new);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            faculties = facultyDAO.findAll();
        }catch (SQLException | DAOException e){
            throw new LogicException(e);
        }finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return faculties;
    }

    public static Faculty findFacultyByID(int facultID) throws LogicException {
        Faculty faculty;

        Optional<WrapperConnection> connectionOptional = Optional.empty();
        WrapperConnection connection = null;
        try {
            connectionOptional = ConnectionPool.getInstance().takeConnection();
            connection = connectionOptional.orElseThrow(SQLException::new);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            faculty = facultyDAO.findEntityById(facultID).get();
        } catch (DAOException | SQLException e) {
            throw new LogicException(e);
        }finally {
            connectionOptional.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return faculty;
    }

}
