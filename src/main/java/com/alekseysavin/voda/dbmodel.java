package com.alekseysavin.voda;

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
    static class MysqlCon{
        public void main(String args[]){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con= DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/clpr","devuser","devuser");
//here sonoo is database name, root is username and password
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from USERS");
                while(rs.next())
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                con.close();
            }catch(Exception e){ System.out.println(e);}
        }
    }
}
