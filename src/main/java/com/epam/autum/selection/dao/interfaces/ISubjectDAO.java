package com.epam.autum.selection.dao.interfaces;

import com.epam.autum.selection.entity.Subject;
import com.epam.autum.selection.exception.DAOException;

/**
 * Created by Tapac on 02.01.2017.
 */
public interface ISubjectDAO extends IDAO<Subject>{

    boolean isExist(String subjectName) throws DAOException;
}
