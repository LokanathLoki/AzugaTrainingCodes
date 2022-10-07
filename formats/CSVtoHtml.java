/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */


package com.azuga.training.formats;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * the class CSVtoHtml is used to convert the CSV content of the Api into Html format
 */
public class CSVtoHtml {

    static final Logger log = Logger.getLogger(CSVtoHtml.class);
    /**
     * method csvToHtml is used to convert the Api's data from csv format to the html format
     *
     * @param csvPath  - csv path is given as input to access the data from csv file
     * @param htmlPath - html path is given as input to store the html file
     */
    public int csvToHtml(String csvPath, String htmlPath) {
        if(csvPath != null) {
            File csvFile = new File(csvPath);
            if(csvFile.exists()){
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
                log.info("Reader is invoked for file " + csvPath);
                String currentLine;
                if (reader.readLine() == null) log.fatal("error occurred due to empty file");
                while ((currentLine = reader.readLine()) != null) {
                    lines.add(currentLine);
                }
                log.debug("csv data from " + csvPath + " file stored in list " + lines);

            //embrace <td> and <tr> for lines and columns
            for (int i = 0; i < lines.size(); i++) {
                lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
                lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
            }
            log.info("setting html tags <tr> and <td> for lines list");

            // embrace <table> and </table> to the lines
            lines.set(0, "<table border>" + lines.get(0));
            lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");
            if (htmlPath != null) {
                    try (FileWriter writer = new FileWriter(htmlPath)) {
                        for (String line : lines) {
                            log.debug("html line writing to converted.html file " + line);
                            writer.write(line + "\n");
                        }
                        System.out.println("html file is created at " + htmlPath);
                    } catch (IOException e) {
                        log.error("error at html file writer{}", e);return -1;
                    }
                } else {log.error("Input html path is null") ; return -1;}
            } catch (IndexOutOfBoundsException  e ) {
                log.error("error while reading from Ghibli.csv file");return -1;
            } catch (IOException f) {
                log.error("IOException  at csv file reader"+f);return -1;
            }
            } else {
                log.error("Csv-file does not exist at "+csvPath);return -1;
            }
        } else { log.error("Input csv path is null"); return -1;}
    return 1;
    }
}
