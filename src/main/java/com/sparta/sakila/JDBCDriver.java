package com.sparta.sakila;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCDriver {
    public static void main(String[] args) {
        //A try with resources ensures objects that implement Autocloseable are closed after statement
        //alternatively a finally clause could be used.
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM film_text");
            while (rs.next()) {
                System.out.println(rs.getString("description"));
            }
            rs.close();
            statement.close();
            conn.close(); // not needed as try with resources is used
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
