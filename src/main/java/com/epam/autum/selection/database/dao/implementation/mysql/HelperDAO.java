package com.epam.autum.selection.database.dao.implementation.mysql;

import com.epam.autum.selection.database.connection.WrapperConnection;
import com.epam.autum.selection.database.entity.Entity;
import com.epam.autum.selection.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tapac on 04.01.2017.
 */
class HelperDAO{

    private static HelperDAO instance;

    private WrapperConnection connection;

    static final String LAST_ID = "SELECT LAST_INSERT_ID() AS id";
    static final String ID = "id";

    private HelperDAO(){

    }

    public static HelperDAO getInstance(){
        if (instance == null)
            instance = new HelperDAO();
        return instance;
    }

    /**
     * Sets the actual id of the entity.
     *
     * @param entity entity to updateMinMark id
     * @throws SQLException if any exceptions occurred on the SQL layer
     */
    public static void updateId(WrapperConnection connection, Entity entity) throws SQLException {
        try (PreparedStatement stId = connection.prepareStatement(LAST_ID)) {
            ResultSet rs = stId.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt(ID));
            }
        }
    }

    /**
     * Retrieves id of the latest added entity of type T.
     *
     * @param sqlQuery query to select id
     * @return id of the latest added entity of type T
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    public static int selectLastId(WrapperConnection connection, String sqlQuery) throws DAOException {
        int id = 0;
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt(ID);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return id;
    }
}
