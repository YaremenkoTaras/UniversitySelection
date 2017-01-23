package com.epam.autum.selection.database.dao.interfaces;

import com.epam.autum.selection.database.entity.ApplicantMark;
import com.epam.autum.selection.exception.DAOException;

import java.util.List;

/**
 * Created by Tapac on 02.01.2017.
 */
public interface IApplicantMarkDAO extends IDAO<ApplicantMark>{
    List<ApplicantMark> findMarkByUser(int userID)throws DAOException;
}