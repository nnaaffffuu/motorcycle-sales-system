/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnaaf
 */
public class SalesReportTable {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;

    // Method to insert payment information into the sales report table after a successful payment
    public static void insertSalesReport(int paymentID, int customerID, String modelName, String chassisNo, String payingIn, double originalPrice, double discountAmount, double gstAmount, double totalPrice, double paidAmount, double balance, String salesPerson) {
        try {
            con = con = DatabaseConnection.connectToDatabase();
            ps = con.prepareStatement("INSERT INTO sales_report(payment_id, customer_id, model_name, chassis_no, paying_in, original_price, discount_amount, gst_amount, total_price, paid_amount, balance, sales_person, created) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, paymentID);
            ps.setInt(2, customerID);
            ps.setString(3, modelName);
            ps.setString(4, chassisNo);
            ps.setString(5, payingIn);
            ps.setDouble(6, originalPrice);
            ps.setDouble(7, discountAmount);
            ps.setDouble(8, gstAmount);
            ps.setDouble(9, totalPrice);
            ps.setDouble(10, paidAmount);
            ps.setDouble(11, balance);
            ps.setString(12, salesPerson);
            ps.setTimestamp(13, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
