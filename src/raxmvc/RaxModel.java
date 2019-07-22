/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raxmvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rax
 */
public abstract class RaxModel extends RaxDatabase{

    
    public static class result {

        static result r = new result();
        static ResultSet result;
        static boolean affected;

        public boolean isAffected() {
            return affected;
        }

        public ResultSet resultIs() {
            return result;
        }

    }

    public static class insert {
     
        
        static Connection con = RaxDatabase.getConnection();
       
        static String tabel;
        static insert i = new insert();
        static String kolom;

        public static insert into(String tabel) {
            insert.tabel = tabel;
            return i;
        }

        public insert on(String kolom) {
            insert.kolom = kolom;
            return i;
        }

        public result values(Object... l) {
            PreparedStatement ps = null;
            try {
                Object[] s = l;
                con.setAutoCommit(false);
                String q = "?";
                for (int a = 1; a < s.length; a++) {
                    q = q.concat(",?");
                }
                System.out.println(q);
                ps = con.prepareStatement("insert into " + tabel + " values (" + q + ")");
                System.out.println("insert into " + tabel + " values (" + Arrays.deepToString(l) + ")");
                for (int b = 0; b < Arrays.asList(l).size(); b++) {
                    ps.setObject(b + 1, l[b]);
                }

                result.affected = (ps.executeUpdate() == 1);
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    con.rollback();

                } catch (SQLException ex1) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex1);
                }
                result.affected = false;

            } finally {
                try {
                    con.setAutoCommit(true);
                    if (ps != null) {
                        ps.close();
                    }
                    //   con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result.r;
        }
    }

    public static class update {

        private String STATEMENT;
        private static String SET = "";
        private static String WHERE = "";
        private static String tabel;
        private static Object[][] s;
        private static Object[][] w;
        private static Object[][] d;
        static update u = new update();
        static Connection con =  RaxDatabase.getConnection();

        public result where(Object[][] kondisi) {
//            k = kondisi;
//            for (Object[] item : k) {
//                for (int j = 1; j < k[0].length; j++) {
//                    WHERE = WHERE + item[0] + "='" + item[1] + "',";
//                }
//            }
            WHERE = Arrays.deepToString(kondisi).replace("[[", "").replace("[", "").replace("], ", " ").replace(", ", " = ").replace("]]", "");
//            w = kondisi;
//            WHERE = "";
//            for (Object[] item : w) {
//                for (int j = 1; j < w[0].length; j++) {
//                    WHERE = WHERE + item[0] + " = \'" + item[1] + "\', ";
//                }
//            }
//            WHERE = WHERE.substring(0, WHERE.length() - 2) + " ";
            System.out.println(" where  " + WHERE);
            
            //WHERE = WHERE.substring(0, WHERE.length() - 1);
            Statement ps = null;
            try {
                con.setAutoCommit(false);
                ps = con.createStatement();
                STATEMENT = "update " + tabel + " set " + SET + " where " + WHERE;
                result.affected = ps.executeUpdate(STATEMENT) == 1;
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex1);
                }
                result.affected = false;
            } finally {
                try {
                    con.setAutoCommit(true);
                    if (ps != null) {
                        ps.close();
                    }
                    // con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result.r;
        }

        public static update tabel(String tabel) {
            update.tabel = tabel;
            return u;
        }

        public update set(Object[]... l) {
            s = l;
            SET = "";
            for (Object[] item : s) {
                for (int j = 1; j < s[0].length; j++) {
                    SET = SET + item[0] + " = \'" + item[1] + "\', ";
                }
            }
            SET = SET.substring(0, SET.length() - 2) + " ";
            System.out.print(" set  " + SET);

            return u;

        }
    }

    public static class delete {

        private static String stmnt = "";
        private static String tabel;
        private static Object[][] where_is;
        static delete dl = new delete();

        public static delete from(String tabel) {
            delete.tabel = tabel;
            return delete.dl;
        }
        static Connection con =  RaxDatabase.getConnection();

        public result where(Object[]... kondisi) {
            Statement st = null;
            try {
                where_is = kondisi;
                stmnt = "";
//                for (Object[] item : where_is) {
//                    for (int j = 1; j < where_is[0].length; j++) {
//                        stmnt = stmnt + item[0] + " = \'" + item[1] + "\', ";
//                    }
//                }
//                stmnt = stmnt.substring(0, stmnt.length() - 2) + " ";
                con.setAutoCommit(false);
//                System.out.println("where  " + stmnt);
                stmnt = Arrays.deepToString(kondisi).replace("[[", "").replace("[", "").replace("], ", " ").replace(", ", " = ").replace("]]", "");
                st = con.createStatement();
                System.out.println(stmnt);
                result.affected = st.executeUpdate("delete from " + tabel + " where " + stmnt) == 1;
               con.commit();
            } catch (SQLException ex) {
                try {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                    con.rollback();
                    result.affected = false;
                } catch (SQLException ex1) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                    if (st != null) {
                        st.close();
                    }
                    //   con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result.r;
        }
    }

    public static class query {

        static Connection con =  RaxDatabase.getConnection();

        public static result setQuery(String sql) {
            try {
                con.setAutoCommit(false);
                Statement st = con.createStatement();
                result.result = st.executeQuery(sql);
                con.commit();
            } catch (SQLException ex) {
                try {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                    con.rollback();
                    result.result.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                    //    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RaxModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result.r;
        }
    }
}
