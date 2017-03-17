package com.epam.autum.selection.jdbc.dao.implementation.mysql;

import com.epam.autum.selection.jdbc.dao.interfaces.IFacultySubjectDAO;
import com.epam.autum.selection.jdbc.connection.WrapperConnection;
import com.epam.autum.selection.jdbc.dto.FacultySubjectDTO;
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
public class FacultySubjectDAO implements IFacultySubjectDAO {

    private static Logger log = LogManager.getLogger(FacultySubjectDTO.class);

    private WrapperConnection connection;
    private static FacultySubjectDAO instance;

    private static final String FACULTY_ID = "faculty_id";
    private static final String SUBJECT_ID = "subject_id";
    private static final String MIN_MARK = "min_mark";

    private static final String SELECT_ALL = "SELECT faculty_id,subject_id,min_mark FROM faculty_subject";
    private static final String SELECT_BY_FACULTY = "SELECT subject_id,min_mark FROM faculty_subject WHERE faculty_id=?";
    private static final String SELECT_MIN_MARK_BY_KEY = "SELECT min_mark FROM faculty_subject WHERE faculty_id=? AND subject_id=?";


    private static final String DELETE_BY_ID = "DELETE FROM faculty_subject WHERE faculty_id=? AND subject_id =?";

    private static final String UPDATE = "UPDATE faculty_subject SET min_mark=? WHERE faculty_id=? AND subject_id =?";

    private static final String INSERT_FACULTY_SUBJECT =
            "INSERT INTO faculty_subject (faculty_id,subject_id,min_mark) VALUES (?,?,?)";


    private FacultySubjectDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public static FacultySubjectDAO getInstance(WrapperConnection connection) {
        if (instance == null)
            instance = new FacultySubjectDAO(connection);
        return instance;
    }

    @Override
    public boolean delete(FacultySubjectDTO subject) throws DAOException {
        int id1 = subject.getFacultyID();
        int id2 = subject.getSubjectID();
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id1);
            st.setInt(2, id2);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("Subject [faculty_id = " + id1 + ", subject_id= " + id2 + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean create(FacultySubjectDTO entity) throws DAOException {
        boolean created = false;
        if (!checkEntity(entity.getFacultyID(), entity.getSubjectID()))
            try (PreparedStatement st = connection.prepareStatement(INSERT_FACULTY_SUBJECT)) {
                st.setInt(1, entity.getFacultyID());
                st.setInt(2, entity.getSubjectID());
                st.setInt(3, entity.getMinMark());
                int res = st.executeUpdate();
                if (res > 0) {
                    created = true;
                    log.info("Faculty subject " + entity + " created");
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        else log.info("Faculty already have this subject");
        return created;
    }

    private boolean checkEntity(int facultyID, int subjectID) throws DAOException {
        boolean present = false;
        try (PreparedStatement st = connection.prepareStatement(SELECT_MIN_MARK_BY_KEY)) {
            st.setInt(1, facultyID);
            st.setInt(2, subjectID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                present = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return present;
    }

    @Override
    public List<FacultySubjectDTO> findAll() throws DAOException {
        List<FacultySubjectDTO> allSubjects = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int fID = rs.getInt(FACULTY_ID);
                int sID = rs.getInt(SUBJECT_ID);
                int minMark = rs.getInt(MIN_MARK);

                FacultySubjectDTO subject = new FacultySubjectDTO(fID, sID, minMark);
                allSubjects.add(subject);
            }
            log.info("All faculty subjects retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allSubjects;
    }

    @Override
    public List<FacultySubjectDTO> findSubjectsByFaculty(int facultyID) throws DAOException {
        List<FacultySubjectDTO> allSubjects = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_FACULTY)) {
            st.setInt(1, facultyID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int sID = rs.getInt(SUBJECT_ID);
                int minMark = rs.getInt(MIN_MARK);

                FacultySubjectDTO subject = new FacultySubjectDTO(facultyID, sID, minMark);
                allSubjects.add(subject);
            }
            log.info("Faculty  [id = " + facultyID + "] subjects found");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allSubjects;
    }

    @Override
    public Optional<FacultySubjectDTO> findEntityById(int facultyID, int subjectID) throws DAOException {
        FacultySubjectDTO minMark = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_MIN_MARK_BY_KEY)) {
            st.setInt(1, facultyID);
            st.setInt(2, subjectID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int mark = rs.getInt(MIN_MARK);

                minMark = new FacultySubjectDTO(facultyID, subjectID, mark);
                log.info("Minimal mark  [faculty_id = " + facultyID + ", subject_id= " + subjectID + "] found");
            } else {
                log.info("Minimal mark  [faculty_id = " + facultyID + ", subject_id= " + subjectID + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(minMark);
    }

    @Override
    public boolean updateMinMark(FacultySubjectDTO entity) throws DAOException {

        String sqlQuery = UPDATE;
        boolean update = false;
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            st.setInt(1, entity.getMinMark());
            st.setInt(2, entity.getFacultyID());
            st.setInt(3, entity.getSubjectID());

            int updated = st.executeUpdate();
            if (updated > 0) {
                update = true;
                log.info("Faculty subject mark" + entity + " updated");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return update;
    }
}
