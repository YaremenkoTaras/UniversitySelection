package com.epam.autum.selection.jdbc.dao.interfaces;

import com.epam.autum.selection.jdbc.dto.ApplicationDTO;
import com.epam.autum.selection.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IApplicationDAO extends IDAO<ApplicationDTO>{

    List<ApplicationDTO> findApplicationsByUser(int userID) throws DAOException;
    List<ApplicationDTO> findApplicationsByFaculty(int facultyID) throws DAOException;
    Optional<ApplicationDTO> findApplicationByUserFaculty(int userID, int facultyID) throws DAOException;

}
