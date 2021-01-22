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
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ui.MenuUI;

/**
 *
 * @author nnaaf
 */
public class CustomersTable {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Method to compare customer information before inserting into the database
    public static boolean compareCustomersInformation(String nid, String dob) {
        boolean check = false;
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT nid FROM customers");
            rs = ps.executeQuery();

            try {
                // Calculate the age of the customer
                int age = DateAndTime.calculateAge(dob);
                // If the customer is atleast 18 years old
                if (age >= 18) {
                    // If table does not contain data
                    if (!rs.next()) {
                        check = true;
                        // If the table contains data
                    } else {
                        do {
                            // If the table contains a matching national id
                            if (rs.getString("nid").equals(nid)) {
                                check = false;
                                JOptionPane.showMessageDialog(null, "Another person is already registered with the same ID card No. !");
                                break;
                                // If there isn't a matching national id in the table
                            } else {
                                check = true;
                            }
                            // Moves the cursor to the next row
                        } while (rs.next());
                    }
                    // If the customer is under the age of 18
                } else {
                    JOptionPane.showMessageDialog(null, "The customer should be atleast 18 years old!", "", JOptionPane.ERROR_MESSAGE);
                }
                // If the entered date of birth is incorrect
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format!", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    // Method to compare customer information before updating any information in the database
    public static boolean compareUserInformation(String nid, String previousNID, String fullName, String dob, String address, String phoneNo, String email, String accountNo, String ridingZone) {
        boolean check = false;
        try {
            // Gets the customers result set
            rs = DatabaseResultSet.getCustomersResultSet();

            try {
                // Calculate the age of the customer
                int age = DateAndTime.calculateAge(dob);

                // If the customer is atleast 18 years old
                if (age >= 18) {
                    // Moves the cursor to the next row
                    while (rs.next()) {
                        // Compares the user entered information and information stored in the database
                        if (rs.getString(2).equals(nid) && rs.getString(3).equals(fullName) && rs.getString(4).equals(dob) && rs.getString(5).equals(address) && rs.getString(6).equals(phoneNo) && rs.getString(7).equals(email) && rs.getString(8).equals(accountNo) && rs.getString(9).equals(ridingZone)) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "There is nothing to be updated!");
                            break;
                            // If there is a change of data
                        } else {
                            // If national id the user entered (from textfield) is not the same as the one in the database(shows in jTable)
                            if (!nid.equals(previousNID)) {
                                // If the table contains matching national id
                                if (rs.getString(3).equals(nid)) {
                                    check = false;
                                    JOptionPane.showMessageDialog(null, "Another person is already registered with the same ID card No. !");
                                    break;
                                    // If there isn't a matching national id in the table
                                } else {
                                    check = true;
                                }
                                // If national id from textfield is the same as the one in the jTable
                            } else {
                                check = true;
                            }
                        }
                    }
                    // If the customer is under the age of 18
                } else {
                    JOptionPane.showMessageDialog(null, "The customer should be atleast 18 years old!", "", JOptionPane.ERROR_MESSAGE);
                }
                // If the entered date of birth is incorrect
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format!", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    // Method to search for the customer using id
    public static boolean searchForCustomer(int id) {
        boolean customerFound = false;

        try {
            rs = DatabaseResultSet.getCustomersResultSet();

            while (rs.next()) {
                // id from database and from the input parameter is compared
                if (rs.getInt(1) == id) {
                    customerFound = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerFound;
    }

    // Method to insert customer information into the database
    public static void insertCustomerInformation(int id, String nid, String fullName, String dob, String address, String phoneNo, String email, String accountNo, String ridingZone) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("INSERT INTO customers(id, nid, full_name, dob, address, phone_no, email, account_no, riding_zone, created, last_updated) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, nid);
            ps.setString(3, fullName);
            ps.setString(4, dob);
            ps.setString(5, address);
            ps.setString(6, phoneNo);
            ps.setString(7, email);
            ps.setString(8, accountNo);
            ps.setString(9, ridingZone);
            ps.setTimestamp(10, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(11, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to update customer information in the database
    public static void updateCustomerInformation(int id, String nid, String fullName, String dob, String address, String phoneNo, String email, String accountNo, String ridingZone) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("UPDATE customers SET nid = ?, full_name = ?, dob = ?, address = ?, phone_no = ?, email = ?, account_no = ?, riding_zone = ?, last_updated = ? WHERE id = ?");

            ps.setString(1, nid);
            ps.setString(2, fullName);
            ps.setString(3, dob);
            ps.setString(4, address);
            ps.setString(5, phoneNo);
            ps.setString(6, email);
            ps.setString(7, accountNo);
            ps.setString(8, ridingZone);
            ps.setTimestamp(9, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(10, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to delete customer information from the database
    public static void deleteCustomerInformation(int id) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("DELETE FROM customers WHERE id = " + id + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
