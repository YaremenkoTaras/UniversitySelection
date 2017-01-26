package com.epam.autum.selection.database.dao.interfaces;

import com.epam.autum.selection.database.dto.ApplicantMarkDTO;
import com.epam.autum.selection.exception.DAOException;

import java.util.List;

public interface IApplicantMarkDAO extends IDAO<ApplicantMarkDTO>{
    List<ApplicantMarkDTO> findMarkByUser(int userID)throws DAOException;
}
