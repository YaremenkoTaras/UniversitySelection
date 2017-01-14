package com.epam.autum.selection.zzzconsoletest;

import com.epam.autum.selection.database.ConnectionPool;
import com.epam.autum.selection.database.WrapperConnection;

import java.sql.SQLException;

/**
 * Created by Tapac on 28.12.2016.
 */
public class TestConnection {

    public static void main(String[] args) {

        ConnectionPool pool = ConnectionPool.getInstance();
        WrapperConnection connection = null;

        try {
            connection = pool.takeConnection().orElseThrow(SQLException::new);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
