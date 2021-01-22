/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author nnaaf
 */
public class DateAndTime {

    public static String getCurrentDateAndTime() {
        // Gets the current date
        LocalDateTime dateTimeObj = LocalDateTime.now();
        
        // Set date and time format
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy    HH:mm:ss");
        
        // Format the LocalDateTime object and assign it into a String variable
        String formattedDateTime = dateTimeObj.format(formatObj);
        
        return formattedDateTime;
    }
    
    public static int calculateAge(String dateOfBirth) {
        // Gets the current date
        LocalDate currentDate = LocalDate.now();
        
        // Set date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        // Parses the String into the date object
        LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);

        // Calculates the age only if both the parameters has a value
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
