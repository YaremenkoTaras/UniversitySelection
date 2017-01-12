package com.epam.autum.selection.dao.mysql;

import com.epam.autum.selection.dao.interfaces.IFacultyDAO;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.Faculty;
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
public class FacultyDAO implements IFacultyDAO {

    private static Logger log = LogManager.getLogger(UserDAO.class);

    private WrapperConnection connection;
    private static FacultyDAO instance;

    private static final String FACULTY_ID = "faculty_id";
    private static final String NAME = "name";
    private static final String SHORT_NAME = "short_name";
    private static final String NUMBER_OF_STUDENT = "number_of_student";

    private static final String INSERT_FACULTY = "INSERT INTO faculty (name,short_name,number_of_student) VALUES (?,?,?)";

    private static final String SELECT_ALL = "SELECT faculty_id,name,short_name, number_of_student FROM faculty";
    private static final String SELECT_BY_ID = "SELECT name,short_name, number_of_student FROM faculty WHERE faculty_id=?";

    private static final String UPDATE_FACULTY = "UPDATE faculty SET name=?,short_name=?, number_of_student=? WHERE faculty_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM faculty WHERE faculty_id=?";

    private FacultyDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public static FacultyDAO getInstanse(WrapperConnection connection){
        if (instance == null)
            instance = new FacultyDAO(connection);
        return instance;
    }

    @Override
    public boolean create(Faculty entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_FACULTY)) {
            st.setString(1, entity.getName());
            st.setString(2, entity.getShortName());
            st.setInt(3, entity.getNumberOfStudent());
            int res = st.executeUpdate();
            if (res > 0) {
                created = true;
                HelperDAO.getInstance().updateId(connection, entity);
                log.info("Faculty " + entity + " created");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return created;
    }

    @Override
    public List<Faculty> findAll() throws DAOException {
        List<Faculty> allFaculties = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(FACULTY_ID);
                String name = rs.getString(NAME);
                String shot_name = rs.getString(SHORT_NAME);
                int number = rs.getInt(NUMBER_OF_STUDENT);

                Faculty faculty = new Faculty(id,name,shot_name,number);
                allFaculties.add(faculty);
            }
            log.info("All faculties retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allFaculties;
    }

    @Override
    public Optional findEntityById(int id) throws DAOException {
        Faculty faculty = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString(NAME);
                String shot_name = rs.getString(SHORT_NAME);
                int number = rs.getInt(NUMBER_OF_STUDENT);

                faculty = new Faculty(id,name,shot_name,number);
                log.info("Faculty [id = " + id + "] found");
            } else {
                log.info("Faculty [id = " + id + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(faculty);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("Faculty [id = " + id + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean update(Faculty entity) throws DAOException {

        String sqlQuery = UPDATE_FACULTY;
        boolean update = false;
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            st.setString(1, entity.getName());
            st.setString(2, entity.getShortName());
            st.setInt(3, entity.getNumberOfStudent());
            st.setInt(4, entity.getId());

            int updated = st.executeUpdate();
            if (updated > 0) {
                update = true;
                log.info("Faculty " + entity + " updated");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return update;
    }

}
