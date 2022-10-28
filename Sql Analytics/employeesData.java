package DataBases;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * class employeesData is used to run the sql nested and queries using joins
 */
public class employeesData {
    private static final Logger log =  Logger.getLogger(employeesData.class);
    public static void main(String[] args) {
        employeesData employee = new employeesData();
       String nestedQuery1 = " select * from salaries where salaries.emp_no in (select emp_no from employees);";
        String nestedQuery2 = " select last_name , first_name from employees where hire_date in (select from_date from salaries)limit 20;";
        String nestedQuery3 = "select max(salaries.salary) from salaries where salaries.salary in (select salaries.salary from employees left join salaries on employees.emp_no = salaries.emp_no)limit 150;";
      String joinQuery1 = "select employees.emp_no,employees.first_name, employees.last_name,salaries.salary from employees left join salaries on employees.emp_no = salaries.emp_no limit 150;";
       String joinQuery2 = "select employees.emp_no, employees.first_name, employees.last_name, salaries.salary from employees inner join salaries on employees.emp_no=salaries.emp_no";
        employee.fetchTable(joinQuery1);
    }

    /**
     * Method fetchTable is used to run the mySql query and print's the data in the console for the
     * given input query
     * @param query - input query to get the required data
     */
    public void fetchTable(String query) {
        log.info("fetchMethod is invoked");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            log.info("Connecting with database employeesDB");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeesDB","root","lokanath@123");
            Statement statement = con.createStatement();
            log.debug("sql query "+query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                StringBuilder table = new StringBuilder();
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    table.append(rs.getString(i));
                    table.append("\t|\t ");
                }
                System.out.println(table);
            }
            statement.close();
            con.close();
            log.info("Connection is closed with database");
        } catch (ClassNotFoundException | SQLException e) {
           log.info("Exception "+e.getMessage());
        }
    }
}
