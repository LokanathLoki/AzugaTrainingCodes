package DataBases;
/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import java.sql.*;

public class ConnectingDB {
    public static void main(String[] args) {
        String Fetchquery = "select * from image;";
        String Updatequery = "alter table image drop column serialNum;";
        String createTable = "create table roots(id int primary key, name varchar(21));";
        ConnectingDB FirstDB = new ConnectingDB();
       // FirstDB.fetchTable(Fetchquery);
        FirstDB.updateTable(createTable);
    }
    public void fetchTable(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstDB","root","lokanath@123");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                System.out.println(rs.getString(1)+" : "+rs.getString(2) + " : " + rs.getString(3) +" : " + rs.getString(4)/*+" : " + rs.getString(5)*/);
            }
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("exception "+e);
        }
        }

    public void updateTable(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstDB","root","lokanath@123");
            PreparedStatement statement = con.prepareStatement(query);
            int count = statement.executeUpdate();
            System.out.println(count+" rows are affected");
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("exception "+e);
        }
    }
    }

