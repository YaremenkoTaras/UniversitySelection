package com.epam.autum.selection.dao.implementation.mysql;

import com.epam.autum.selection.dao.interfaces.IApplicationDAO;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.Application;
import com.epam.autum.selection.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public class ApplicationDAO implements IApplicationDAO {

    private static Logger log = LogManager.getLogger(ApplicationDAO.class);

    private WrapperConnection connection;
    private static ApplicationDAO instance;

    private static final String APPLICATION_ID = "application_id";
    private static final String DATE = "date";
    private static final String DESCRIPTION = "description";
    private static final String OVERALL = "overall";
    private static final String FACULTY_ID = "faculty_id";
    private static final String USER_ID = "user_id";
    private static final String APPLICATION_STATUS_ID = "application_status_id";


    private static final String SELECT_ALL = "SELECT application_id,date,description,overall,faculty_id,user_id,application_status_id FROM application";
    private static final String SELECT_BY_USER = "SELECT application_id,date,description,overall,faculty_id,application_status_id FROM application WHERE user_id=?";
    private static final String SELECT_BY_FACULTY = "SELECT application_id,date,description,overall,user_id,application_status_id FROM application WHERE faculty_id=? ORDER BY overall DESC";
    private static final String SELECT_BY_ID = "SELECT date,description,overall,faculty_id,user_id,application_status_id FROM application WHERE application_id=?";
    private static final String SELECT_BY_USER_FACULTY = "SELECT application_id,date,description,overall,application_status_id FROM application WHERE user_id=? AND faculty_id=?";

    private static final String INSERT_APPLICATION = "INSERT INTO application(date,description,overall,faculty_id,user_id,application_status_id) VALUES(?,?,?,?,?,2)";

    private static final String DELETE_BY_ID = "DELETE FROM application WHERE application_id=?";

    private ApplicationDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public static ApplicationDAO getInstance(WrapperConnection connection){
        if (instance == null)
            instance = new ApplicationDAO(connection);
        return instance;
    }

    @Override
    public boolean create(Application entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_APPLICATION)) {

            /*java.util.Date date = new Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());*/

            st.setDate(1, entity.getDate());
            st.setString(2, entity.getDescription());
            st.setInt(3, entity.getOverall());
            st.setInt(4, entity.getFacultyID());
            st.setInt(5, entity.getUserID());
            int res = st.executeUpdate();
            if (res > 0) {
                created = true;
                HelperDAO.getInstance().updateId(connection, entity);
                log.info("Application " + entity + " created");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return created;
    }

    @Override
    public List findAll() throws DAOException {
        List<Application> allApp = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(APPLICATION_ID);
                Date date = rs.getDate(DATE);
                String desc = rs.getString(DESCRIPTION);
                Integer over = rs.getInt(OVERALL);
                Integer facultyID = rs.getInt(FACULTY_ID);
                Integer userID = rs.getInt(USER_ID);
                Integer statusID = rs.getInt(APPLICATION_STATUS_ID);

                Application app = new Application(id,date,desc,over,facultyID,userID,statusID);
                allApp.add(app);
            }
            log.info("All appclications retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allApp;
    }

    @Override
    public Optional findEntityById(int id) throws DAOException {
        Application app = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate(DATE);
                String desc = rs.getString(DESCRIPTION);
                Integer over = rs.getInt(OVERALL);
                Integer facultyID = rs.getInt(FACULTY_ID);
                Integer userID = rs.getInt(USER_ID);
                Integer statusID = rs.getInt(APPLICATION_STATUS_ID);

                app = new Application(id,date,desc,over,facultyID,userID,statusID);
                log.info("Application [id = " + id + "] found");
            } else {
                log.info("Application [id = " + id + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(app);
    }

    @Override
    public Optional findApplicationByUserFaculty(int userID, int facultyID) throws DAOException {
        Application app = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_USER_FACULTY)) {
            st.setInt(1, userID);
            st.setInt(2, facultyID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt(APPLICATION_ID);
                Date date = rs.getDate(DATE);
                String desc = rs.getString(DESCRIPTION);
                Integer over = rs.getInt(OVERALL);
                Integer statusID = rs.getInt(APPLICATION_STATUS_ID);

                app = new Application(id,date,desc,over,facultyID,userID,statusID);
                log.info("User Application for faculty found, [id = " + id + "]");
            } else {
                log.info("User Application for faculty not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(app);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("Application [id = " + id + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public List<Application> findApplicationsByUser(int userID) throws DAOException {
        List<Application> allApp = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_USER)) {
            st.setInt(1,userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(APPLICATION_ID);
                Date date = rs.getDate(DATE);
                String desc = rs.getString(DESCRIPTION);
                Integer over = rs.getInt(OVERALL);
                Integer facultyID = rs.getInt(FACULTY_ID);
                Integer statusID = rs.getInt(APPLICATION_STATUS_ID);

                Application app = new Application(id,date,desc,over,facultyID,userID,statusID);
                allApp.add(app);
            }
            log.info("Appclications by user retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allApp;
    }

    @Override
    public List<Application> findApplicationsByFaculty(int facultyID) throws DAOException {
        List<Application> allApp = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_FACULTY)) {
            st.setInt(1,facultyID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(APPLICATION_ID);
                Date date = rs.getDate(DATE);
                String desc = rs.getString(DESCRIPTION);
                Integer over = rs.getInt(OVERALL);
                Integer userID = rs.getInt(USER_ID);
                Integer statusID = rs.getInt(APPLICATION_STATUS_ID);

                Application app = new Application(id,date,desc,over,facultyID,userID,statusID);
                allApp.add(app);
            }
            log.info("Appclications by faculty retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allApp;
    }

    @Override
    public boolean update(Application entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

}
