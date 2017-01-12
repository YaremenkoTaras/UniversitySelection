package com.epam.autum.selection.dao.mysql;

import com.epam.autum.selection.dao.interfaces.ISubjectDAO;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.Subject;
import com.epam.autum.selection.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public class SubjectDAO implements ISubjectDAO {

    private static Logger log = LogManager.getLogger(UserDAO.class);

    private WrapperConnection connection;
    private static SubjectDAO instance;

    private static final String SUBJECT_ID = "subject_id";
    private static final String NAME = "name";

    private static final String INSERT_SUBJECT = "INSERT INTO subject (name) VALUE (?)";

    private static final String SELECT_ALL = "SELECT subject_id,name FROM subject";
    private static final String SELECT_BY_ID = "SELECT name FROM subject WHERE subject_id=?";

    private static final String UPDATE_SUBJECT = "UPDATE subject SET name=? WHERE subject_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM subject WHERE subject_id=?";

    private static final String SELECT_BY_NAME = "SELECT subject_id FROM subject WHERE name=?";



    private SubjectDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public static SubjectDAO getInstance(WrapperConnection connection){
        if (instance == null)
            instance = new SubjectDAO(connection);
        return instance;
    }


    @Override
    public boolean create(Subject entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_SUBJECT)) {
            st.setString(1, entity.getName());

            int res = st.executeUpdate();
            if (res > 0) {
                created = true;
                HelperDAO.getInstance().updateId(connection, entity);
                log.info("Subject " + entity + " created");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return created;
    }

    @Override
    public List<Subject> findAll() throws DAOException {
        List<Subject> allSubjects = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(SUBJECT_ID);
                String name = rs.getString(NAME);

                Subject faculty = new Subject(id,name);
                allSubjects.add(faculty);
            }
            log.info("All subjects retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allSubjects;
    }

    @Override
    public Optional findEntityById(int id) throws DAOException {
        Subject subject = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString(NAME);

                subject = new Subject(id,name );
                log.info("Subject [id = " + id + "] found");
            } else {
                log.info("Subject [id = " + id + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(subject);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("Subject [id = " + id + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean update(Subject entity) throws DAOException {
        String sqlQuery = UPDATE_SUBJECT;
        boolean update = false;
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            st.setString(1, entity.getName());
            st.setInt(2, entity.getId());

            int updated = st.executeUpdate();
            if (updated > 0) {
                update = true;
                log.info("Subject " + entity + " updated");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return update;
    }

    @Override
    public boolean isExist(String subjectName) throws DAOException {
        boolean exist = false;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_NAME)) {
            st.setString(1, subjectName);
            ResultSet rs = st.executeQuery();
            exist = !rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return exist;
    }
}
