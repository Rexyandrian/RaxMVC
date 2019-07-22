/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import raxmvc.RaxConfig;
import raxmvc.RaxDatabase;

/**
 *
 * @author rax
 */
public class Play extends RaxConfig{
    public static void main(String[] args) {
       
        
        setDBport(3306);
        setAutoConnectDB(true);
        setTimeZone(RaxConfig.timeZoneGMT.GMT_PLUS_7);
        setDBpassword("");
        setDBserver("localhost");
        setDBusername("root");
        setDatabase("db_adit");
      
        RaxDatabase.getInstance();
        try {
            System.out.println("Connection : "+getConnection().getCatalog());
        } catch (SQLException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("GMT : "+getTimeZone());
    }
}
