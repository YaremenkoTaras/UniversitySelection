package com.epam.autum.selection.database.dao.daofactory;

import com.epam.autum.selection.database.dao.implementation.mysql.*;
import com.epam.autum.selection.database.dao.interfaces.*;
import com.epam.autum.selection.database.connection.WrapperConnection;

/**
 * Created by Tapac on 02.01.2017.
 */
public class DaoFactory {

    public DaoFactory(){

    }

    public static IApplicantMarkDAO createApplicantMarkDAO(WrapperConnection connection){
        return ApplicantMarkDAO.getInstance(connection);
    }

    public static IApplicationDAO createApplicationDAO(WrapperConnection connection){
        return ApplicationDAO.getInstance(connection);
    }

    public static IFacultyDAO createFacultyDAO(WrapperConnection connection){
        return FacultyDAO.getInstanse(connection);
    }

    public static IFacultySubjectDAO createFacultySubjectDAO(WrapperConnection connection){
        return FacultySubjectDAO.getInstance(connection);
    }

    public static ISubjectDAO createSubjectDAO(WrapperConnection connection){
        return SubjectDAO.getInstance(connection);
    }

    public static IUserDAO createUserDAO(WrapperConnection connection){
        return UserDAO.getInstance(connection);
    }

}
