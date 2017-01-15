package com.epam.autum.selection.dao.interfaces;

import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.exception.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public interface IApplicationDAO extends IDAO<Application>{

    List<Application> findApplicationsByUser(int userID) throws DAOException;
    List<Application> findApplicationsByFaculty(int facultyID) throws DAOException;
    Optional<Application> findApplicationByUserFaculty(int userID, int facultyID) throws DAOException;

}
