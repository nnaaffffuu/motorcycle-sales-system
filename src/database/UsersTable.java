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
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author nnaaf
 */
public class UsersTable {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Method to compare user information before inserting into the database
    public static boolean compareUserInformation(String username, String role) {
        boolean check = false;
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT username, role FROM users");
            rs = ps.executeQuery();

            // If database table does not contain data
            if (!rs.next()) {
                // Role should be an administrator for the first user
                if ("Administrator".equals(role)) {
                    check = true;
                } else {
                    JOptionPane.showMessageDialog(null, "The first user to be entered should be an administrator!");
                }
                // If database table contains data
            } else {
                do {
                    // If the table contains any matching username
                    if (rs.getString("username").equals(username)) {
                        check = false;
                        JOptionPane.showMessageDialog(null, "Username is already used!");
                        break;
                        // If there isn't a matching username in the table
                    } else {
                        check = true;
                    }
                } while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    // Method to compare user information before updating any user information in the database
    public static boolean compareUserInformation(String fullName, String username, String previousUsername, String password, String role, String status) {
        boolean check = false;
        try {
            rs = DatabaseResultSet.getUsersResultSet();

            // Moves the cursor to the next row
            while (rs.next()) {
                // Compares the user entered information and information stored in the database
                if (rs.getString(2).equals(fullName) && rs.getString(3).equals(username) && rs.getString(4).equals(password) && rs.getString(5).equals(role) && rs.getString(6).equals(status)) {
                    check = false;
                    JOptionPane.showMessageDialog(null, "There is nothing to be updated!");
                    break;
                    // If there is a change of data
                } else {
                    // If national id the user entered (from textfield) is not the same as the one in the database(shows in jTable)
                    if (!username.equals(previousUsername)) {
                        // If the table contains a matching username
                        if (rs.getString(3).equals(username)) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "Username is already used!");
                            break;
                            // If there isn't a matching username in the table
                        } else {
                            check = true;
                        }
                        // If username from textfield is the same as the one in the jTable
                    } else {
                        check = true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    // Method to insert user information into the database
    public static void insertUserInformation(int id, String fullName, String username, String password, String role, String status) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("INSERT INTO users(id, full_name, username, password, role, status, created, last_updated) values(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, fullName);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setString(6, status);
            ps.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(8, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to update user information in the database
    public static void updateUserInformation(int id, String fullName, String username, String password, String role, String status) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("UPDATE users SET full_name = ?, username = ?, password = ?, role = ?, status = ?, last_updated = ?  WHERE id = ?");
            ps.setString(1, fullName);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setString(5, status);
            ps.setTimestamp(6, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to delete user information from the database
    public static void deleteUserInformation(int id) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("DELETE FROM users WHERE id = " + id + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
