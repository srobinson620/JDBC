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
            Properties dbProps = new Properties();
            dbProps.load( new FileReader ("src/main/resources/database.properties") );
            //could locate the database.properties file in the project root folder
            // and pass filename to FileReader
            Connection conn = DriverManager.getConnection(
                    dbProps.getProperty("db.url"),
                    dbProps.getProperty("db.username"),
                    dbProps.getProperty("db.password"));
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
