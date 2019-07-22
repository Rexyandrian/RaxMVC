/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raxmvc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.mail.Session;
import raxmvc.RaxConfig.timeZoneGMT;
import raxmvc.RaxEmailSender.EmailSendType;
import raxmvc.RaxEmailSender.EmailType;

/**
 *
 * @author rax
 */
public class RaxConfig {
    
    
    
    public static enum timeZoneGMT{
        GMT_PLUS_0,
        GMT_PLUS_1,
        GMT_PLUS_2,
        GMT_PLUS_3,
        GMT_PLUS_4,
        GMT_PLUS_5,
        GMT_PLUS_6,
        GMT_PLUS_7,
        GMT_PLUS_8,
        GMT_PLUS_9,
        GMT_PLUS_10,
        GMT_PLUS_11,
        GMT_PLUS_12,
        GMT_MIN_1,
        GMT_MIN_2,
        GMT_MIN_3,
        GMT_MIN_4,
        GMT_MIN_5,
        GMT_MIN_6,
    }

    
    private static String emailServer,emailHost,emailUsername,emailPassword,timeZone,database,DBserver,DBusername,DBpassword;
    private static int DBport,emailPort;
    private static EmailType emailType;
    private static EmailSendType emailSendType;
    private static boolean autoConnectDB=true,autoCommit=true,emailAuth=true;
    private static Connection connection;
    private static Session emailSession;

    public static void setEmailSession(Session emailSession) {
        RaxConfig.emailSession = emailSession;
    }

    public static Session getEmailSession() {
        return emailSession;
    }

    public static void setEmailSendType(EmailSendType emailSendType) {
        RaxConfig.emailSendType = emailSendType;
    }

    public static EmailSendType getEmailSendType() {
        return emailSendType;
    }

    public static void setEmailType(EmailType emailType) {
        RaxConfig.emailType = emailType;
    }

    public static EmailType getEmailType() {
        return emailType;
    }    
    

    public static void setEmailServer(String emailServer) {
        RaxConfig.emailServer = emailServer;
    }

    public static String getEmailServer() {
        return emailServer;
    }

    public static void setEmailHost(String emailHost) {
        RaxConfig.emailHost = emailHost;
    }

    public static void setEmailAuth(boolean emailAuth) {
        RaxConfig.emailAuth = emailAuth;
    }    
    
    public static boolean getEmailAuth(){
        return RaxConfig.emailAuth;
    }

    public static void setEmailPassword(String emailPassword) {
        RaxConfig.emailPassword = emailPassword;
    }

    public static String getEmailPassword() {
        return emailPassword;
    }
    
    
    
    public static void setEmailUsername(String emailUsername) {
        RaxConfig.emailUsername = emailUsername;
    }

    public static String getEmailHost() {
        return emailHost;
    }
    
    public static String getEmailUsername() {
        return emailUsername;
    }

    public static void setEmailPort(int emailPort) {
        RaxConfig.emailPort = emailPort;
    }

    public static int getEmailPort() {
        return emailPort;
    }
    
    
    
    protected static void setConnection(Connection connection) {
        RaxConfig.connection = connection;
    }

    public static Connection getConnection() {
        return RaxConfig.connection;
    }

    public static void setAutoCommit(boolean autoCommit) throws SQLException {
        RaxConfig.autoCommit = autoCommit;
        connection.setAutoCommit(autoCommit);
    }

    public static boolean getAutoCommit(){
        return RaxConfig.autoCommit;
    }
    
    public static void setAutoConnectDB(boolean autoConnectDB) {
        RaxConfig.autoConnectDB = autoConnectDB;
    }
    
    public static boolean getAutoConnectDB(){
        return RaxConfig.autoConnectDB;
    }

    public static void setTimeZone(timeZoneGMT zone) {
        if (zone.name().contains("_PLUS_")) {
            RaxConfig.timeZone = zone.name().replace("_PLUS_", "+");
        }else{
            RaxConfig.timeZone = zone.name().replace("_MIN_", "-");
        }
        
    }

    public static String getTimeZone() {
        
        return timeZone;
    }

    public static void setDBpassword(String DBpassword) {
        RaxConfig.DBpassword = DBpassword;
    }

    public static void setDBport(int DBport) {
        RaxConfig.DBport = DBport;
    }

    public static void setDBserver(String DBserver) {
        RaxConfig.DBserver = DBserver;
    }

    public static void setDBusername(String DBusername) {
        RaxConfig.DBusername = DBusername;
    }

    public static void setDatabase(String database) {
        RaxConfig.database = database;
    }

    public static String getDBpassword() {
        return DBpassword;
    }

    public static int getDBport() {
        return DBport;
    }

    public static String getDBserver() {
        return DBserver;
    }

    public static String getDBusername() {
        return DBusername;
    }

    public static String getDatabase() {
        return database;
    }
    
    
    
}
