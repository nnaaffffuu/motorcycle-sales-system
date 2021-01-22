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
public class UniqueIDGenerator {

    //Initialize objects
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static int generateUserID() {
        int id = 0;
        boolean uniqueIDFound = false;
        
        while (uniqueIDFound == false) {
            try {
                double x = Math.random() * ((99999 - 10001) + 1) + 10001;
                id = (int) Math.round(x);

                rs = DatabaseResultSet.getUsersResultSet();
                
                boolean matchingIDFound = false;
                
                if (!rs.next()) {
                    break;
                } else {
                    do {
                        if (rs.getInt("id") == id) {
                            matchingIDFound = true;
                            break;
                        }
                    } while (rs.next());
                }
                
                if (matchingIDFound == false) {
                    uniqueIDFound = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UniqueIDGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public static int generateCustomerID() {
        int id = 0;
        boolean uniqueIDFound = false;
        
        while (uniqueIDFound == false) {
            try {
                double x = Math.random() * ((99999 - 10001) + 1) + 10001;
                id = (int) Math.round(x);

                rs = DatabaseResultSet.getCustomersResultSet();
                
                boolean matchingIDFound = false;
                
                if (!rs.next()) {
                    break;
                } else {
                    do {
                        if (rs.getInt("id") == id) {
                            matchingIDFound = true;
                            break;
                        }
                    } while (rs.next());
                }
                
                if (matchingIDFound == false) {
                    uniqueIDFound = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UniqueIDGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public static int generatePaymentID() {
        int id = 0;
        boolean uniqueIDFound = false;
        
        while (uniqueIDFound == false) {
            try {
                double x = Math.random() * ((999999 - 100001) + 1) + 100001;
                id = (int) Math.round(x);

                rs = DatabaseResultSet.getSalesReportResultSet();
                
                boolean matchingIDFound = false;
                
                if (!rs.next()) {
                    break;
                } else {
                    do {
                        if (rs.getInt("payment_id") == id) {
                            matchingIDFound = true;
                            break;
                        }
                    } while (rs.next());
                }
                
                if (matchingIDFound == false) {
                    uniqueIDFound = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UniqueIDGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
    
    public static int generateInstallmentID() {
        int id = 0;
        boolean uniqueIDFound = false;
        
        while (uniqueIDFound == false) {
            try {
                double x = Math.random() * ((9999999 - 1000001) + 1) + 1000001;
                id = (int) Math.round(x);

                rs = DatabaseResultSet.getInstallmentsResultSet();
                
                boolean matchingIDFound = false;
                
                if (!rs.next()) {
                    break;
                } else {
                    do {
                        if (rs.getInt("installment_id") == id) {
                            matchingIDFound = true;
                            break;
                        }
                    } while (rs.next());
                }
                
                if (matchingIDFound == false) {
                    uniqueIDFound = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UniqueIDGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }
}
