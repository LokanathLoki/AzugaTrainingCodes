package com.azuga.training.Api;
/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import au.com.bytecode.opencsv.CSVReader;
import com.github.opendevl.JFlat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class Api-Generic is a generic class that gets response for the given url
 * uses that response to create csv and json files in given paths
 */
public class GenericApi {
    static final Logger log = Logger.getLogger(com.azuga.training.oops.ApiGenric.class);
    public HttpResponse<String> response;


    public static void main(String[] args) throws Exception {
        GenericApi films = new GenericApi();
        films.apiCall("https://ghibliapi.herokuapp.com/films", "/Users/azuga/Desktop/FilmsApi.csv", "/Users/azuga/Desktop/newApi.json");

    }

    /**
     * Api call method gets the response for the url and create the csv file for the obtained response
     *
     * @param Url       - Input Url to get its response data
     * @param csvPath   - Path to store the created csv file of url response
     * @param jsonPath- Path to store the created json file of url response
     */
    public int apiCall(String Url, String csvPath, String jsonPath) {
        if (Url != null) {
            try {
                HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(Url)).build();
                log.info("Request method - GET for APi -" + Url);
                //creating the client object required to send the request
                HttpClient httpClient = HttpClient.newBuilder().build();

                //Sotring the http response in String format
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    log.info("Api call is Successfull for Url -" + Url);
                    log.debug("response from Url " + Url + " is - " + response.body());
                    JFlat flatMe = new JFlat(response.body());
                    if (csvPath != null) {
                        File file = new File(csvPath);
                        if (file.exists()) {
                            flatMe.json2Sheet().headerSeparator("_").write2csv(csvPath);
                        } else {
                            log.error("File does not exist at " + csvPath);
                            return -1;
                        }
                    } else {
                        log.error("input path is null");
                        return -1;
                    }
                } else {
                    log.fatal("Fatal error due to Un-successfull Api call -" + Url);
                    return -1;
                }
                jsonFile(response.body(), jsonPath);
                log.info(Url + " response data is written to file " + csvPath);
            } catch (Exception e) {
                log.error("Exception in ApiCall" + e);
                return -1;
            }
        } else {
            log.error("invalid input for url " + Url);
            return -1;
        }
        return 1;
    }

    /**
     * method csvToHtml is used to convert the Api's data from csv format to the html format
     *
     * @param csvPath  - csv path is given as input to access the data from csv file
     * @param htmlPath - html path is given as input to store the html file
     */
    public int csvToHtml(String csvPath, String htmlPath) {
        if (csvPath != null) {
            File csvFile = new File(csvPath);
            if (csvFile.exists()) {
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
                            log.error("error at html file writer{}", e);
                            return -1;
                        }
                    } else {
                        log.error("Input html path is null");
                        return -1;
                    }
                } catch (IndexOutOfBoundsException e) {
                    log.error("error while reading from Ghibli.csv file");
                    return -1;
                } catch (IOException f) {
                    log.error("IOException  at csv file reader" + f);
                    return -1;
                }
            } else {
                log.error("Csv-file does not exist at " + csvPath);
                return -1;
            }
        } else {
            log.error("Input csv path is null");
            return -1;
        }
        return 1;
    }

    /**
     * method csvToPdf is used to convert the Api's data from csv format to the pdf format
     *
     * @param csvPath - csv path is given as input to access the data from csv file
     * @param pdfPath - pdf path is given as input to store the pdf file
     */

    public int csvToPdf(String csvPath, String pdfPath) {
        if (csvPath != null) {
            File csvFile = new File(csvPath);
            if (csvFile.exists()) {
                try {
                    CSVReader reader = new CSVReader(new FileReader(csvPath));
                    log.info("Reader is invoked for file " + csvPath);
                    String[] nextLine;
                    String[] data = Arrays.toString(reader.readNext()).split(",");
                    int rows = data.length;
                    //the instance of document is created
                    Document my_pdf_data = new Document();
                    log.info("intializing the  document for conversion");
                    my_pdf_data.setPageSize(PageSize.A0.rotate());
                    if (pdfPath != null) {
                        PdfWriter.getInstance(my_pdf_data, new FileOutputStream(pdfPath));
                        my_pdf_data.open();
                        if (reader.readNext() == null) log.fatal("error occurred due to empty input file " + csvPath);
                        //PdfPTable is used to add Table to the pdf
                        PdfPTable my_first_table = new PdfPTable(rows);
                        PdfPCell table_cell;
                        log.info("initializing Table and cell to add into movieApi.pdf");
                        while ((nextLine = reader.readNext()) != null) {
                            int i = 0;
                            while (i < rows) {
                                table_cell = new PdfPCell(new Phrase(nextLine[i]));
                                my_first_table.addCell(table_cell);
                                i++;
                            }
                        }
                        my_pdf_data.add(my_first_table);
                        log.info("table is added to movieApi.pdf file");
                        // my_pdf_data is closed
                        my_pdf_data.close();
                        log.info("movieApi.pdf is created from ghibliApi.csv file");
                    } else {
                        log.error("given pdf path is null");
                        return -1;
                    }
                } catch (DocumentException d) {
                    log.error("Document exception " + d);
                    return -1;
                } catch (IOException i) {
                    log.error("IOException occyred" + i);
                    return -1;
                }
            } else {
                log.error("Csv file does not exists " + csvPath);
                return -1;
            }
        } else {
            log.error("given csv path is null");
            return -1;
        }
        return 1;
    }

    public void jsonFile(String response, String jsonPath) {
        log.info("json method invoked for GhibliApi call's response");
        if (response != null) {
            try {
                if (jsonPath != null) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter((jsonPath)));
                    writer.write(response.substring(1, response.length() - 1));
                    writer.flush();
                    writer.close();
                    log.info("created /Users/azuga/Desktop/output.json file for GhibliApi");
                } else log.error("Input jsonPath is null");
            } catch (IOException ignored) {
                log.error("error at json file creation /Users/azuga/Desktop/output.json");
            }
        } else log.error("response to the json method is null");
    }
}



