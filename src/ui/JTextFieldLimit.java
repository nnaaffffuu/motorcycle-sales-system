/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author nnaaf
 */
public class JTextFieldLimit extends PlainDocument{
    
    private int limit;

    public JTextFieldLimit(int limitation) {   //constructor
        this.limit = limitation;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
        if (str == null) {
            return;
        } else if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, set);
        }
    }
}
