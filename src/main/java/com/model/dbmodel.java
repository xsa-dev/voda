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
    static class sqllite {
        // http://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/



    }
    static class MysqlCon{

        String sqlUser = "devuser";
        String sqlPass = "devuser";
        String sqlHost = "jdbc:mysql://localhost:3306/clpr";

        public void testMsqlConnection() throws SQLException, ClassNotFoundException {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(sqlHost, sqlUser, sqlPass);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from Users");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                }
            } catch (SQLException sqlE) {
                System.out.println(sqlE.getErrorCode() + "\n" + sqlE.toString());
            }
        }

        public void sqlExec(String qString) throws SQLException, ClassNotFoundException {
            System.out.println("sqlExec()");
            testMsqlConnection();
            System.out.println("SqlExce Done");
        }
    }


}
