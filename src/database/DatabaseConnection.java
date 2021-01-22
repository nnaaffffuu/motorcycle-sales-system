/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnaaf
 */
public class DatabaseConnection {

    // Declare variables
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:database";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // Initialize Connection object
    private static Connection con;

    // Method to connect to the database and returns the connection object
    public static Connection connectToDatabase() throws SQLException {
        try {
            // Register driver
            Class.forName(DRIVER);
            // Connect to database
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
