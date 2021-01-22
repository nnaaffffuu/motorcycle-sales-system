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
public class InventoryTable {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Method to compare motorcycle information before inserting into the database
    public static boolean compareMotorcycleInformation(String chassisNo, String engineNo) {
        boolean check = false;
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("SELECT chassis_no, engine_no FROM inventory");
            rs = ps.executeQuery();

            // If database does not contain any data
            if (!rs.next()) {
                check = true;
                // If database contains data
            } else {
                do {
                    // If the table contains matching chassis No. and engine No.
                    if (rs.getString("chassis_no").equals(chassisNo) && rs.getString("engine_no").equals(engineNo)) {
                        check = false;
                        JOptionPane.showMessageDialog(null, "Another motorcycle is already entered with the same chassis No. & engine No. !");
                        break;
                        // If the table contains a matching chassis No.
                    } else if (rs.getString("chassis_no").equals(chassisNo)) {
                        check = false;
                        JOptionPane.showMessageDialog(null, "Another motorcycle is already entered with the same chassis No. !");
                        break;
                        // If the table contains a matching engine No.
                    } else if (rs.getString("engine_no").equals(engineNo)) {
                        check = false;
                        JOptionPane.showMessageDialog(null, "Another motorcycle is already entered with the same engine No. !");
                        break;
                        // If the table does not contain a matching chassis No. and engine No.
                    } else {
                        check = true;
                    }
                    // Moves the cursor to the next row
                } while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    // Method to compare motorcycle information before updating any information in the database
    public static boolean compareMotorcycleInformation(int itemCode, String type, String brand, String modelNo, String modelName, String chassisNo, String previousChassisNo, String engineNo, String previousEngineNo, String engineCapacity, String colour, double price, String manuYear) {
        boolean check = false;
        try {
            rs = DatabaseResultSet.getInventoryResultSet();

            // Moves the cursor to the next row
            while (rs.next()) {
                // Compares the user entered information and information stored in the database
                if (rs.getString(2).equals(type) && rs.getString(3).equals(brand) && rs.getString(4).equals(modelNo) && rs.getString(5).equals(modelName) && rs.getString(6).equals(chassisNo) && rs.getString(7).equals(engineNo) && rs.getString(8).equals(engineCapacity) && rs.getString(9).equals(colour) && rs.getDouble(10) == price && rs.getString(11).equals(manuYear)) {
                    check = false;
                    JOptionPane.showMessageDialog(null, "There is nothing to be updated!");
                    break;
                } else {
                    // If chassis No. and engine No. the user entered (from textfield) is not the same as the ones in the database(shows in jTable)
                    if (!chassisNo.equals(previousChassisNo) && !engineNo.equals(previousEngineNo)) {
                        // If the table contains matching chassis No. and engine No.
                        if (rs.getString("chassis_no").equals(chassisNo) && rs.getString("engine_no").equals(engineNo)) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "Another motorcycle is already entered with the same chassis No. & engine No. !");
                            break;
                            // If the table does not contain a matching chassis No. and engine No.
                        } else {
                            check = true;
                        }
                        // If chassis No. the user entered (from textfield) is not the same as the one in the database(shows in jTable)
                    } else if (!chassisNo.equals(previousChassisNo) && engineNo.equals(previousEngineNo)) {
                        // If the table contains a matching chassis No.
                        if (rs.getString("chassis_no").equals(chassisNo)) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "Another motorcycle is already entered with the same chassis No. !");
                            break;
                            // If the table does not contain a matching chassis No.
                        } else {
                            check = true;
                        }
                        // If engine No. the user entered (from textfield) is not the same as the one in the database(shows in jTable)
                    } else if (chassisNo.equals(previousChassisNo) && !engineNo.equals(previousEngineNo)) {
                        // If the table contains a matching engine No.
                        if (rs.getString("engine_no").equals(engineNo)) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "Another motorcycle is already entered with the same engine No. !");
                            break;
                            // If the table does not contain a matching engine No.
                        } else {
                            check = true;
                        }
                        // If chassis No. and engine No. the user entered (from textfield) is same as the ones in the database(shows in jTable)
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

    // Method to insert motorcycle information into the database
    public static void insertMotorcycleInformation(String type, String brand, String modelNo, String modelName, String chassisNo, String engineNo, String engineCapacity, String colour, double price, String manuYear) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("INSERT INTO inventory(type, brand, model_no, model_name, chassis_no, engine_no, engine_capacity, colour, price, manu_year, created, last_updated) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, type);
            ps.setString(2, brand);
            ps.setString(3, modelNo);
            ps.setString(4, modelName);
            ps.setString(5, chassisNo);
            ps.setString(6, engineNo);
            ps.setString(7, engineCapacity);
            ps.setString(8, colour);
            ps.setDouble(9, price);
            ps.setString(10, manuYear);
            ps.setTimestamp(11, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(12, java.sql.Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to update motorcycle information in the database
    public static void updateMotorcycleInformation(int itemCode, String type, String brand, String modelNo, String modelName, String chassisNo, String engineNo, String engineCapacity, String colour, double price, String manuYear) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("UPDATE inventory SET type = ?, brand = ?, model_no = ?, model_name = ?, chassis_no = ?, engine_no = ?, engine_capacity = ?, colour = ?, price = ?, manu_year = ?, last_updated = ? WHERE item_code = ?");

            ps.setString(1, type);
            ps.setString(2, brand);
            ps.setString(3, modelNo);
            ps.setString(4, modelName);
            ps.setString(5, chassisNo);
            ps.setString(6, engineNo);
            ps.setString(7, engineCapacity);
            ps.setString(8, colour);
            ps.setDouble(9, price);
            ps.setString(10, manuYear);
            ps.setTimestamp(11, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(12, itemCode);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to delete motorcycle information from the database
    public static void deleteMotorcycleInformation(int itemCode) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("DELETE FROM inventory WHERE item_code = " + itemCode + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
