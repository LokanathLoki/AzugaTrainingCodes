/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */


package com.azuga.training.formats;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * the class CSVtoHtml is used to convert the CSV content of the Api into Html format
 */
public class CSVtoHtml {
    static final Logger log = Logger.getLogger(CSVtoHtml.class);
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/Desktop/ghibli.csv"))) {
            log.info("Reader is invoked for file ghibli.csv");
            String currentLine;
            if(reader.readLine() == null) log.fatal("error occurred due to empty file");
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            log.error("error while reading from csvReader");
            e.printStackTrace();
        }

        //embrace <td> and <tr> for lines and columns
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            log.debug("content  line read from csv file "+s);
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
        }log.info("setting html tags <tr> and <td> for line");

        // embrace <table> and </table>
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");

        // output result
        try (FileWriter writer = new FileWriter("/Users/azuga/Desktop/converted.html")) {
            for (String line : lines) {
                log.debug("html line writing to converted.html"+" "+line);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            log.error("error at html file writer");
            e.printStackTrace();
        }
    }
}
