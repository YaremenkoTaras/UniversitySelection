package com.epam.autum.selection.zzzconsoletest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Tapac on 28.12.2016.
 */
public class TestDB {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/selection";
        //String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "root";

        try {
           // Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,user,pass);
            System.out.println(conn.isClosed());
        } catch (SQLException e) {
            System.err.println("Error connection");
        }


    }
}
