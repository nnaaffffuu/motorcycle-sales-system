/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motorcyclesalessystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.LoginUI;
import ui.MenuUI;

/**
 *
 * @author nnaaf
 */
public class MotorcycleSalesSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MotorcycleSalesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LoginUI().setVisible(true);
        //new MenuUI().setVisible(true);
    }
    
}
