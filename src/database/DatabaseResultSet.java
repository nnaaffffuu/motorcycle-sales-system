/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnaaf
 */
public class DatabaseResultSet {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Method to return the ResultSet of users table
    public static ResultSet getUsersResultSet() {
        try {
            // Executes SQL query after connecting to the database
            con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT * FROM users");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // Method to return the ResultSet of login_history table
    public static ResultSet getLoginHistoryResultSet() {
        try {
            // Executes SQL query after connecting to the database
            con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT * FROM login_history");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // Method to return the ResultSet of customers table
    public static ResultSet getCustomersResultSet() {
        try {
            // Executes SQL query after connecting to the database
            con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT * FROM customers");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // Method to return the ResultSet of inventory table
    public static ResultSet getInventoryResultSet() {
        try {
            // Executes SQL query after connecting to the database
            con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT * FROM inventory");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // Method to return the ResultSet of sales report table
    public static ResultSet getSalesReportResultSet() {
        try {
            // Executes SQL query after connecting to the database
            con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT * FROM sales_report");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // Method to return the ResultSet of installments table
    public static ResultSet getInstallmentsResultSet() {
        try {
            // Executes SQL query after connecting to the database
            con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT * FROM installments");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseResultSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
