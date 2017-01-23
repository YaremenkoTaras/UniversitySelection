package com.epam.autum.selection.database.dao.implementation.mysql;

import com.epam.autum.selection.database.dao.interfaces.IApplicantMarkDAO;
import com.epam.autum.selection.database.connection.WrapperConnection;
import com.epam.autum.selection.database.entity.ApplicantMark;
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
public class ApplicantMarkDAO implements IApplicantMarkDAO {

    private static Logger log = LogManager.getLogger(ApplicantMarkDAO.class);

    private WrapperConnection connection;
    private static ApplicantMarkDAO instance = null;

    private static final String APPLICANT_MARK_ID = "applicant_mark_id";
    private static final String MARK = "mark";
    private static final String USER_ID = "user_id";
    private static final String SUBJECT_ID = "subject_id";

    private static final String INSERT_APPLICANT_MARK = "INSERT INTO applicant_mark (mark,user_id,subject_id) VALUES (?,?,?)";

    private static final String SELECT_ALL = "SELECT applicant_mark_id,mark,user_id,subject_id FROM applicant_mark";
    private static final String SELECT_BY_ID = "SELECT mark,user_id,subject_id FROM applicant_mark WHERE applicant_mark_id=?";
    private static final String SELECT_BY_USER_ID = "SELECT applicant_mark_id,mark,subject_id FROM applicant_mark WHERE user_id=?";

    private static final String UPDATE_FACULTY = "UPDATE applicant_mark SET mark=?,user_id=?, subject_id=? WHERE applicant_mark_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM applicant_mark WHERE applicant_mark_id=?";


    private ApplicantMarkDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public static ApplicantMarkDAO getInstance(WrapperConnection connection){
        if (instance == null) instance = new ApplicantMarkDAO(connection);
        return instance;
    }


    @Override
    public boolean create(ApplicantMark entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_APPLICANT_MARK)) {
            st.setInt(1, entity.getMark());
            st.setInt(2, entity.getUserID());
            st.setInt(3, entity.getSubjectID());
            int res = st.executeUpdate();
            if (res > 0) {
                created = true;
                HelperDAO.getInstance().updateId(connection, entity);
                log.info("Mark " + entity + " created");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return created;
    }

    @Override
    public List<ApplicantMark> findAll() throws DAOException {
        List<ApplicantMark> allMarks = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(APPLICANT_MARK_ID);
                int mark = rs.getInt(MARK);
                int userID = rs.getInt(USER_ID);
                int subjectID = rs.getInt(SUBJECT_ID);

                ApplicantMark appMark = new ApplicantMark(id,mark,userID,subjectID);
                allMarks.add(appMark);
            }
            log.info("All marks retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allMarks;
    }

    @Override
    public Optional findEntityById(int id) throws DAOException {
        ApplicantMark appMark = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int mark = rs.getInt(MARK);
                int userID = rs.getInt(USER_ID);
                int subjectID = rs.getInt(SUBJECT_ID);

                appMark = new ApplicantMark(id,mark,userID,subjectID);
                log.info("Mark [id = " + id + "] found");
            } else {
                log.info("Mark [id = " + id + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(appMark);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("Mark [id = " + id + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean update(ApplicantMark entity) throws DAOException {

        String sqlQuery = UPDATE_FACULTY;
        boolean update = false;
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            st.setInt(1, entity.getMark());
            st.setInt(2, entity.getUserID());
            st.setInt(3, entity.getSubjectID());
            st.setInt(4, entity.getId());

            int updated = st.executeUpdate();
            if (updated > 0) {
                update = true;
                log.info("Mark " + entity + " updated");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return update;
    }

    @Override
    public List<ApplicantMark> findMarkByUser(int userID) throws DAOException {
        List<ApplicantMark> markList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_USER_ID)){
            st.setInt(1,userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(APPLICANT_MARK_ID);
                int mark = rs.getInt(MARK);
                int subjectID = rs.getInt(SUBJECT_ID);

                ApplicantMark appMark = new ApplicantMark(id,mark,userID,subjectID);
                markList.add(appMark);
            }
            log.info("All user marks retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return markList;
    }
}
