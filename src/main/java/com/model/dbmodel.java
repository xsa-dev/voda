package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
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
    public static class MysqlCon{

        String sqlUser = "devuser";
        String sqlPass = "devuser";
        String sqlHost = "jdbc:mysql://localhost:3306/clpr";

        public void addUser(long tid, String tname, String tnummber) throws ClassNotFoundException, SQLException {
           // INSERT INTO `telegram`.`users` (`telegramid`, `telegramname`, `telegramnumber`)
           // VALUES (tid, tnumb, tname);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
            Statement statement = connection.createStatement();

            String query = "insert into telegram.users (telegramid, telegramname, telegramnumber) " +
                    "values ('" + tid + "', '" + tname + "' , " +  tnummber + ");";
            System.out.println(query);

            statement.execute(query);

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

        public void sqlExec(String qString) throws SQLException, ClassNotFoundException {
            //Connection con = DriverManager;
            //System.out.println("SqlExce Done");
        }
    }


}
