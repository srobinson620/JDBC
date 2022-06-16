package com.sparta.sakila;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null) {  //as connection is static it may have a value from previous use
            //could locate the database.properties file in the project root folder
            // and pass filename to FileReader
            Properties dbProps = new Properties();
            try {
                dbProps.load(new FileReader("src/main/resources/database.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(
                        dbProps.getProperty("db.url"),
                        dbProps.getProperty("db.username"),
                        dbProps.getProperty("db.password"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    public static void closeConnection(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
