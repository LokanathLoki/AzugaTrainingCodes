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
 * Class AnalyticsDB is used to write the sql commands for data analytics
 * In this we have created different queries using different sql concepts like
 * distinct, like, in, where, joins, nested query's, function's etc
 */
public class AnalyticsDB {

    private static final Logger log =  Logger.getLogger(AnalyticsDB.class);
    Connection con;

    public static ArrayList<String> data = new ArrayList<>();

    public AnalyticsDB(){
        String url = "jdbc:mysql://localhost:3306/FirstDB";
        String username = "root";
        String password = "lokanath@123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            log.info("Connected with the Data Base");
        } catch (SQLException s) {
           log.error("sql exception "+s);
        } catch (ClassNotFoundException c) {
           log.error("ClassNotFoundException"+c);
        }

    }

    /**
     * In distinct method the sql query to get the distinct data is written by taking table name and
     * required column name
     * @param tableName - table name from which data should be taken
     * @param columnName - column name for its distinct values
     */
    public void distinct(String tableName, String columnName)
    {
        log.info("Distinct method is invoked for "+columnName);
        String query = "select distinct "+columnName+" from "+tableName;
        log.debug("sql query "+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            StringBuilder table = new StringBuilder();
            while(rs.next()) {
                table.append(rs.getString(1));
                table.append("\n");
            }
            System.out.println(table);
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }

    /**
     * In method where we get the data from the table with a given condition
     * @param tableName - Table form which data to be read
     * @param condition - condition to get the data from table
     */
    public void where(String tableName, String condition){
        log.info("invoked where method ");
        String query = "select * from "+tableName+" where "+condition;
        log.debug("sql query "+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCount = rsMeta.getColumnCount();
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    table.append(rs.getString(i));
                    table.append(" , ");
                }
                System.out.println(table);
            }
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }

    /**
     * Method function is used to work for  functions like
     * sum, max, count, avg etc. for the given column data
     * @param tableName - table name for which operation should be done
     * @param function  - input function name for the method
     * @param dataToCount - input column in table to analyze
     */
    public void functions(String tableName, String function, String dataToCount){
        log.info("invoked functions method for "+function);
        String query = "select "+function+"("+dataToCount+") from "+tableName+" as total_objects;";
        log.debug("sql query -"+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            int count =0;
            while(rs.next()) {
                count = rs.getInt(1);
                System.out.println(count);
            }
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }


    /**
     * Method orderBy gets the data from the given table name in a given order
     * @param tableName - table name for which operation should be done
     * @param dataToGet - input column in table to analyze
     * @param order     - order condition to get the column data
     */
    public void orderBy(String tableName, String dataToGet, String order){
        log.info("orderBy method is invoked ");
        String query = "select "+dataToGet+" from "+tableName+" order by "+order;
        log.debug("sql query "+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                if(dataToGet.equals("*")) {
                    for (int i = 1; i <= columnCount; i++) {
                        table.append(rs.getString(i));
                        table.append(" , ");
                    }
                    System.out.println(table);
                    } else {
                    table.append(rs.getString(1));
                    table.append(" , ");
                    table.append(rs.getString(2));
                    System.out.println(table);
                }
            }
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }

    /**
     * Method wildCard performs the operations to get the data from table with required format using like keyword
     * @param tableName    - table name for which operation should be done
     * @param ColumnToCheck -  input column in table to analyze
     * @param value         - input string that contains format to check the data in column
     */
    public void wildCard(String tableName, String ColumnToCheck, String value){
        log.info("wildCard method is invoked");
        String query = "select * from "+tableName+" where "+ColumnToCheck+" like '"+value+"'";
        log.debug("sql query "+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    table.append(rs.getString(i));
                    table.append(" , ");
                }
                System.out.println(table);
            }
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }


    /**
     * method between mimics the functionality of the sql keyword between a given range of values
     * @param tableName  -  table name for which operation should be done
     * @param columnName -  input column in table to analyze
     * @param range      -  range to get the values from given column
     */
    public void between(String tableName, String columnName, String range){
        log.info("between method invoked");
        String query = "select * from "+tableName+" where "+columnName+" between "+range;
        log.debug("sql query "+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    table.append(rs.getString(i));
                    table.append(" , ");
                }
                System.out.println(table);
            }
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }


    /**
     * Method dataIn is used to show the functinality of in() function in sql, which gives records
     * for the data given in the in()
     * @param tableName  - table name for which operation should be done
     * @param columnName - input column in table to analyze
     * @param reqData    - set of required data is given as input
     */
    public void dataIn(String tableName, String columnName, String reqData){
        log.info("dataIn method invoked");
        String query = "select * from "+tableName+" where "+columnName+"  in ("+reqData+")";
        log.debug("sql query "+query);
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    table.append(rs.getString(i));
                    table.append(" , ");
                }
                System.out.println(table);
            }
            statement.close();
            con.close();
            log.info("Connection closed with DB");
        } catch (SQLException sq) {
            log.error("SQlException" + sq);
        }
    }



    public static void main(String[] args) {
        AnalyticsDB db = new AnalyticsDB();
       //db.orderBy("museumData","objectName,accessionYear","objectID desc");
        //db.where("museumData","objectId=10539");
        //db.functions("museumData","sum","accessionYear");
       //db.distinct("museumData","objectID");
        //db.wildCard("museumData","objectName","Ph%");
        //db.between("museumData","accessionYear","1900 and 1980");
        //db.dataIn("museumData","objectName","'Photograph','Print','Drawing'");
    }
}

