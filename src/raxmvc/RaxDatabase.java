/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raxmvc;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rax
 */
public class RaxDatabase extends RaxConfig{
    private final static MysqlDataSource DATA_SOURCE = new MysqlDataSource();
    static {
        try {
            
            DATA_SOURCE.setAutoReconnect(getAutoConnectDB());
            DATA_SOURCE.setDatabaseName(getDatabase());
            DATA_SOURCE.setServerName(getDBserver());
            DATA_SOURCE.setPort(getDBport());
            DATA_SOURCE.setServerTimezone(getTimeZone());
            DATA_SOURCE.setUser(getDBusername());
            DATA_SOURCE.setPassword(getDBpassword());
            DATA_SOURCE.setServerTimezone(getTimeZone());
            RaxConfig.setConnection(DATA_SOURCE.getConnection());
            //System.out.println("Sets");
        } catch (SQLException ex) {
            Logger.getLogger(RaxDatabase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Configuration database not synchronized.", "Error configuration", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static RaxDatabase getInstance(){
        RaxDatabase rd = new RaxDatabase();
        return rd;
    }
}
