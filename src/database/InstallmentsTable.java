/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnaaf
 */
public class InstallmentsTable {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;

    // Method to insert installment information into the installment table after intial payment(down payment) in sales panel
    public static void insertInstallments(int installmentID, int customerID, String modelName, String chassisNo, double totalPrice, double downPayment, double monthlyPayment, double paidAmount, int totalMonths, int monthsPaid, Date dueDate, String status) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("INSERT INTO installments(installment_id, customer_id, model_name, chassis_no, total_price, down_payment, monthly_payment, paid_amount, total_months, months_paid, due_date, last_paid, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, installmentID);
            ps.setInt(2, customerID);
            ps.setString(3, modelName);
            ps.setString(4, chassisNo);
            ps.setDouble(5, totalPrice);
            ps.setDouble(6, downPayment);
            ps.setDouble(7, monthlyPayment);
            ps.setDouble(8, paidAmount);
            ps.setInt(9, totalMonths);
            ps.setInt(10, monthsPaid);
            ps.setDate(11, dueDate);
            ps.setTimestamp(12, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(13, status);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method to update installment table after successfully paying for installment
    public static void updateInstallments(int installmentID, double paidAmount, int monthsPaid, String status) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("UPDATE installments SET paid_amount = ?, months_paid = ?, last_paid = ?, status = ? WHERE installment_id = ?");
            
            ps.setDouble(1, paidAmount);
            ps.setInt(2, monthsPaid);
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, status);
            ps.setInt(5, installmentID);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
