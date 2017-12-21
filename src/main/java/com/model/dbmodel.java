package com.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xsd on 02.09.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */

public class dbmodel {
    public static class sqllite {
        // http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/

    }

    public static class MysqlCon {

        static String sqlUser = "devuser";
        static String sqlPass = "devuser";
        static String sqlHost = "jdbc:mysql://localhost:3306/clpr";

        public static int getWaterFromPeriod(long chatid, String dateFrom, String dateTo) throws ClassNotFoundException, SQLException {
            int water = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement stmt = con.createStatement();
            String query = "select sum(watercount) from diary.waterdiary WHERE tid = + '" + chatid + "' AND datetime > curdate();";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                water = rs.getInt(1);
            }
            return water;
        }

        public static void addWaterToDiary(Long chatid, int count) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement statement = connection.createStatement();

            System.out.println("Water added by" + chatid + "/n" + " count" + count);

            String query = "insert into diary.waterdiary (tid, watercount) " +
                    "values ('" + chatid + "', '" + count + "');";
            statement.execute(query);
            System.out.println(query);
        }

        // todo i need to generate db objects, need to test
        public static List<Integer> getWaterSubscribers() throws ClassNotFoundException, SQLException {
            List<Integer> subscrabers = new ArrayList<Integer>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement stmt = con.createStatement();
            String query = "select tid from diary.water.subscribers"; //"select sum(watercount) from diary.waterdiary WHERE tid = + '" + chatid + "' AND datetime BETWEEN  '2017-12-10 00:00:00' AND '2017-12-10 23:59:00'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                subscrabers.add(rs.getInt(1));
            }
            return subscrabers;
        }


        public static void addUnrecognizedQuestion(String nrbotname, String nrbottoken, int nrmessageid, long nrchatid, String nrmessagetext) throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement statement = connection.createStatement();

            System.out.println(nrbotname + ":" + nrbottoken + ":" + nrmessageid + ":" + nrchatid + ":" + nrmessagetext);

            String query = "insert into telegram.notrecognizedmessages (botname, bottoken, messageid, chatid, messagetext) " +
                    //" VALUES " + "('" + nrbotname + "'," '" nrbottoken, nrchatid, nrmessageid, nrmessagetext)";
                    "values ('" + nrbotname + "', '" + nrbottoken + "' , '" + nrmessageid + "' , '" + nrchatid + "' , '" + nrmessagetext + "');";

            System.out.println(query);
            statement.execute(query);

        }

        public void addUser(long tid, String tname, String tnummber) throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement statement = connection.createStatement();

            String query = "insert into telegram.users (telegramid, telegramname, telegramnumber) " +
                    "values ('" + tid + "', '" + tname + "' , " + tnummber + ");";
            System.out.println(query);

            statement.execute(query);

        }

        public static void addConsult(long tid, String consultantCode) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement statement = connection.createStatement();

            String query = null;

            if (consultantCode == "shoroh") {
                query = "insert into telegram.consults (idconsultant, idconsulted) " +
                        "values ('" + 70437788 + "', '" + tid + "');";
            } else if (consultantCode == "levcom") {
                query = "insert into telegram.consults (ifconsultant, idconsulted) " +
                        "values ('" + 120890854 + "', '" + tid + "');";
            }

            System.out.println("adConsultant: " + query);

            try {
                statement.execute(query);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        public static List<Integer> getAdmins() {
            List<Integer> admins = new ArrayList<Integer>();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
                Statement statement = connection.createStatement();

                String query = "SELECT telegramid FROM telegram.users WHERE userrole";

                ResultSet results = statement.executeQuery(query);

                while (results.next()) {
                    results.getStatement();
                    admins.add(results.getInt("telegramid"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return admins;
        }

        public static ArrayList<Integer> getConsultans() throws SQLException, ClassNotFoundException {
            // todo needed for getEveryDayWaterDiaryReport
            ArrayList<Integer> consultants = new ArrayList<Integer>();

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement statement = connection.createStatement();

            /*
            SELECT idconsultant FROM telegram.consults group by consults.idconsultant;
             */
            String query = "SELECT idconsultant FROM telegram.consults group by consults.idconsultant";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                results.getStatement();
                consultants.add(results.getInt(1));
            }
            return consultants;
        }

        public static String getEveryDayWaterDiaryReportView(long consul_tid) throws SQLException, ClassNotFoundException {
            String stroke = "";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT watercountsum, telegramname, datetime, wdv.idconsultant  FROM diary.waterdiaryview wdv where datetime > curdate() and wdv.idconsultant = '" + consul_tid + "';");

                while (rs.next()) {
                    // todo rewrite for needs
                    // System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4));
                    stroke += rs.getString(2) + " за " + rs.getString(3) + " выпил: " + rs.getInt(1) + "\n";
                }
            } catch (SQLException sqlE) {
                System.out.println(sqlE.getErrorCode() + "\n" + sqlE.toString());
            }
            return stroke;
        }

        public void addProduct(String name, int pro, int fats, int carb) throws SQLException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
                Statement statement = connection.createStatement();

                String query = "insert into clpr.products (name, protein, fat, carbohydrate)\n" +
                        "values ('" + name + "', " + pro + "," + fats + "," + carb + ");";

                // ResultSet results = statement.executeQuery(query);

                statement.execute(query);

//                while (results.next()) {
//                    results.getStatement();
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void testMsqlConnection() throws SQLException, ClassNotFoundException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from People");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4));
                }
            } catch (SQLException sqlE) {
                System.out.println(sqlE.getErrorCode() + "\n" + sqlE.toString());
            }
        }
    }


}
