package com.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con
                    = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf_crud",
                             "root", "root");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
