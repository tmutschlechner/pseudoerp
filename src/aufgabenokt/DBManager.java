/*
Copyright (c) 2020 Tobias Mutschlechner

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package aufgabenokt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tobias Mutschlechner
 */
public class DBManager {

    private static Connection curConnection;
    
    public static Connection getConnection() { return getConnection("",""); }
    public static Connection getConnection(String uname, String pass){
        if(curConnection==null) {
            try {
                String serverURL = "jdbc:mysql://localhost:3306/faktura?serverTimezone=UTC";
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                curConnection=(Connection) java.sql.DriverManager.getConnection(serverURL, uname, pass);
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Fehler bei der Verbindung", ex);
                JOptionPane.showMessageDialog(new JFrame(), "Fehler bei der Verbindung", "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, "Datenbanktreiber nicht gefunden!", ex);
                JOptionPane.showMessageDialog(new JFrame(), "Der Datenbanktreiber wurde nicht gefunden!","Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return curConnection;
    }
    
}
