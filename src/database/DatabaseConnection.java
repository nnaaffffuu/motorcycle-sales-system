/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnaaf
 */
public class DatabaseConnection {

    // Declare variables
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:database;create=true";
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
            System.out.println("Connected to database successfully.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    /*
    This functions checks if a given table exists by looking at the database metadata,
    and creates if a table is missing. This allows the application to start from scratch
    in the event the database gets deleted.
    */
    public static void checkTable(String tableName, DatabaseMetaData dbmd, Statement statement, String createTable) throws SQLException {
        boolean checkStatus;
        // Gets the result set from the metadata
        ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(), null);
        
        // Checks the metadata
        if (rs.next()) {
            System.out.println("Table " + tableName + " already exists.");
        } else {
            System.out.println("Table " + tableName + " does not exist.");
            statement.execute(createTable);
            System.out.println(tableName + " table created.");
        }
    }
    
    public static Connection initDB() throws ClassNotFoundException, SQLException {
        Connection connection = connectToDatabase();
        Statement initTables = connection.createStatement();
        DatabaseMetaData dbmd = connection.getMetaData();
        checkTable("users", dbmd, initTables, DatabaseTables.CREATE_USERS_TABLE);
        checkTable("login_history", dbmd, initTables, DatabaseTables.CREATE_LOGIN_HISTORY_TABLE);
        checkTable("customers", dbmd, initTables, DatabaseTables.CREATE_CUSTOMERS_TABLE);
        checkTable("inventory", dbmd, initTables, DatabaseTables.CREATE_INVENTORY_TABLE);
        checkTable("sales_report", dbmd, initTables, DatabaseTables.CREATE_SALES_REPORT_TABLE);
        checkTable("installments", dbmd, initTables, DatabaseTables.CREATE_INSTALLMENTS_TABLE);
        return connection;
    }
}
