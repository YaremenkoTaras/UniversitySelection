package com.epam.autum.selection.zzzconsoletest;

import com.epam.autum.selection.dao.daofactory.DaoFactory;
import com.epam.autum.selection.dao.interfaces.*;
import com.epam.autum.selection.database.ConnectionPool;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.*;
import com.epam.autum.selection.exception.DAOException;

import java.sql.SQLException;

/**
 * Created by Tapac on 05.01.2017.
 */
public class TestDAO {

    public static void main(String[] args) {

        ConnectionPool pool = ConnectionPool.getInstance();

        WrapperConnection connection = null;

        //WrapperConnection connection = pool.takeConnection().get();

        Application application = null;
        ApplicantMark mark = null;
        Faculty faculty = null;
        Subject subject = null;
        User user = null;
        FacultySubject facultySubject = null;

        try {
            connection = pool.takeConnection().orElseThrow(SQLException::new);

            IApplicationDAO applicationDAO = DaoFactory.createApplicationDAO(connection);
            IApplicantMarkDAO appMarkDAO = DaoFactory.createApplicantMarkDAO(connection);
            IFacultyDAO facultyDAO = DaoFactory.createFacultyDAO(connection);
            ISubjectDAO subjectDAO = DaoFactory.createSubjectDAO(connection);
            IUserDAO userDAO = DaoFactory.createUserDAO(connection);
            IFacultySubjectDAO facultySubjectDAO = DaoFactory.createFacultySubjectDAO(connection);

            application = applicationDAO.findEntityById(1).get();
            mark = appMarkDAO.findEntityById(1).get();
            faculty = facultyDAO.findEntityById(1).get();
            subject = subjectDAO.findEntityById(1).get();
            user = userDAO.findEntityById(1).get();
            facultySubject = facultySubjectDAO.findEntityById(faculty.getId(),subject.getId()).orElse(new FacultySubject(1,1,999));

            applicationDAO.create(application);
            appMarkDAO.create(mark);
            facultyDAO.create(faculty);
            subjectDAO.create(subject);
            userDAO.create(user);
            facultySubjectDAO.create(facultySubject);

            mark.setMark(777);
            faculty.setName("MyName");
            subject.setName("MYSubject");
            user.setName("MYNAME");
            facultySubject.setMinMark(666);

            //applicationDAO.update(application);
            appMarkDAO.update(mark);
            facultyDAO.update(faculty);
            subjectDAO.update(subject);
            userDAO.update(user);
            facultySubjectDAO.updateMinMark(facultySubject);

            applicationDAO.findAll();
            appMarkDAO.findAll();
            facultyDAO.findAll();
            subjectDAO.findAll();
            userDAO.findAll();
            facultySubjectDAO.findAll();
            facultySubjectDAO.findSubjectsByFaculty(1);

            applicationDAO.delete(application.getId());
            appMarkDAO.delete(mark.getId());
            facultyDAO.delete(faculty.getId());
            subjectDAO.delete(subject.getId());
            userDAO.delete(user.getId());
            facultySubjectDAO.delete(facultySubject);

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.returnConnection(connection);
        pool.closePool();
    }
}
