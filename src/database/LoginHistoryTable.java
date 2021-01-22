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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnaaf
 */
public class LoginHistoryTable {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Method to return a boolean value after seraching for the last user
    public static boolean checkLastUser(int id) {
        boolean currentUser = false;

        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT id FROM login_history WHERE row = (SELECT MAX(row) FROM login_history)");
            rs = ps.executeQuery();

            while (rs.next()) {
                // If id from input parameter is the same as the id in the table
                if (rs.getInt(1) == id) {
                    currentUser = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginHistoryTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return currentUser;
    }

    // Method to insert user information into the login history table after login is successful
    public static void insertUserInformation(int id, String fullName, String username, String role) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("INSERT INTO login_history(id, full_name, username, role, login) values(?, ?, ?, ?, ?)");
            
            ps.setInt(1, id);
            ps.setString(2, fullName);
            ps.setString(3, username);
            ps.setString(4, role);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method to update logout timestamp in the login history table after while program is being closed
    public static void updateUserInformation(int row) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("UPDATE login_history SET logout = ? WHERE row = ?");
            
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2, row);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method to update user information after user information is updated in the users table
    public static void updateUserInformation(int id, String fullName, String username) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("UPDATE login_history SET full_name = ?, username = ? WHERE id = ?");
            
            ps.setString(1, fullName);
            ps.setString(2, username);
            ps.setInt(3, id);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
