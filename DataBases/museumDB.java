package DataBases;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * class museumDB is used to create a DataBase table for the data obtained from the museum Api's call,
 * contains operations like
 * create Table
 * insert Data into table
 * Read data from table
 * update table
 */
public class museumDB {
    public int count;
    public int column = 1;

    private static final Logger log =  Logger.getLogger(museumDB.class);
    public static void main(String[] args) {
        Api object = new Api();
        museumDB obj = new museumDB();
        //obj.updateTable("delete from museumData where objectID = 221981;");
        //obj.fetchTable("select * from museumData where objectID = 403010;");
        //obj.createTable("create table museumData(objectID int primary key, isHighlight tinyint(1), accessionNumber varchar(50), accessionYear varchar(50), isPublicDomain tinyint(1), primaryImage varchar(255), primaryImageSmall varchar(255), department varchar(255), objectName varchar(100), title varchar(255), culture varchar(150), period varchar(255), dynasty varchar(250), reign varchar(100), portfolio varchar(255), artistRole varchar(250), artistPrefix varchar(100), artistDisplayName varchar(255), artistDisplayBio varchar(255), artistSuffix varchar(255), artistAlphaSort varchar(255), artistNationality varchar(100), artistBeginDate varchar(20), artistEndDate varchar(20), artistGender varchar(10), artistWikidata_URL varchar(255), artistULAN_URL varchar(255), objectDate varchar(100), objectBeginDate int, objectEndDate int, medium varchar(255), dimensions varchar(255), creditLine varchar(255), geographyType varchar(100), city varchar(100), state varchar(100), county varchar(100), country varchar(100), region varchar(100), subregion varchar(100), locale varchar(100), locus varchar(100), excavation varchar(100), river varchar(100), classification varchar(200), rightsAndReproduction varchar(100), linkResource varchar(100), metadataDate varchar(255), repository varchar(255), objectURL varchar(255),objectWikidata_URL varchar(255), isTimelineWork tinyint(1), GalleryNumber varchar(100));");
        obj.insertTable("insert into museumData values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)", object.columnsAndValues("https://collectionapi.metmuseum.org/public/collection/v1/objects/"));
    }

    /**
     * Method createTable is used to create a table in the Data-base using the query which is given as input
     * @param query - input query is string with sql query to create table
     */
    public void createTable(String query) {
        log.info("Create table method is invoked");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstDB", "root", "lokanath@123");
            log.info("Connected with DB");
            PreparedStatement statement = con.prepareStatement(query);
            log.debug("SQl query :- "+query);
            count = statement.executeUpdate();
            System.out.println(count + " rows are affected" + "\n" + "Table is created");
            statement.close();
            con.close();
            log.info("connection is closed");
        } catch (ClassNotFoundException e){
            log.fatal("ClassNotFoundException "+e);
        } catch (SQLException i) {
           log.fatal("SQLException " + i);
        }
    }

    /**
     * Method insertTable is to push the data of museum Api into the Database table
     * @param query - input sql query to insert the data
     * @param value - the arrayList of values of the museumApi
     */
    public void insertTable(String query, ArrayList<Object> value) {
        log.info("inserTable method is invoked");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstDB", "root", "lokanath@123");
            log.info("Connected with DB");
            PreparedStatement statement = con.prepareStatement(query);
            log.debug("SQl query :- "+query);
            for (Object header : value) {
                System.out.println(header);
                String ValueType = (String.valueOf(header.getClass()));
                if ((ValueType).equals("class java.lang.Integer")) {
                    statement.setInt(column, Integer.parseInt(header.toString()));
                } else if (ValueType.equals("class java.lang.Boolean")) {
                    int bool = 0;
                    if ((Boolean) (header)) {
                        bool = 1;
                    }
                    statement.setInt(column, bool);
                } else if (ValueType.equals("class java.lang.String")) {
                    statement.setString(column, header.toString());
                }
                column++;
            }
            log.info("values are inserted to table");
            value.clear();
            count = statement.executeUpdate();
            System.out.println(count + " rows are affected");
            statement.close();
            con.close();
            log.info("Connection is closed");
        } catch (ClassNotFoundException e){
            log.fatal("ClassNotFoundException "+e);
        } catch (SQLException i) {
            log.fatal("SQLException " + i);
        }
    }

    /**
     * Method fetchTable reads the data stored in the Data-base table  and prints in the console
     * @param query - input sql query to read the data from table
     */
    public void fetchTable(String query) {
        log.info("method fetchTable invoked");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstDB","root","lokanath@123");
            log.info("Connected with DB");
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            log.debug("SQl query :- "+query);
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                for (int i = 1; i < 53; i++) {
                    table.append(rs.getString(i));
                    table.append(" , ");
                }
                System.out.println(table);
            }
            statement.close();
            con.close();
            log.info("Connection is closed");
        } catch (ClassNotFoundException e){
            log.fatal("ClassNotFoundException "+e);
        } catch (SQLException i) {
            log.fatal("SQLException " + i);
        }
    }

    /**
     * method updateTable is used to make updates to the table with the help of alter, update, delete, drop etc .
     * @param query - input sql query to update the table data
     */
    public void updateTable(String query) {
        log.info("method updateTable invoked");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstDB", "root", "lokanath@123");
            log.info("Connected with DB");
            PreparedStatement statement = con.prepareStatement(query);
            log.debug("SQl query :- "+query);
            count = statement.executeUpdate();
            System.out.println(count + " rows are affected");
            statement.close();
            con.close();
            log.info("Connection is closed");
        } catch (ClassNotFoundException e){
            log.fatal("ClassNotFoundException "+e);
        } catch (SQLException i) {
            log.fatal("SQLException " + i);
        }
    }
}
