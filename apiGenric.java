package com.azuga.training.Api;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import au.com.bytecode.opencsv.CSVReader;
import com.github.opendevl.JFlat;
import com.github.underscore.U;
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
 * apiGenric class is a generic method that gets the response for different Api calls
 * it holds the methods to create the csv file
 * json file
 * html File
 * xml File
 * and pdf File
 */
public class apiGenric {

    public static String csvpath = "/Users/azuga/Desktop/FilmsApiWe.csv";
    public static String jsonpath = "/Users/azuga/Desktop/FilmsApiWe.json";
    public static String htmlpath = "/Users/azuga/Desktop/FilmsApiWe.html";
    public static String xmlpath = "/Users/azuga/Desktop/FilmsApiWe.xml";
    public static String pdfpath = "/Users/azuga/Desktop/FilmsApiWe.pdf";
    public static String url = "https://fakestoreapi.com/products";
    static final Logger log = Logger.getLogger(apiGenric.class);

    /**
     * method apiCall call the respective api and get response for the given url
     * and creates the csv file
     * json file and xml file
     * @param Url     - Url to call the Api
     * @param csvPath - path to store the csv file
     * @param xmlPath - path to store the xml file
     * @throws Exception - throws different exceptions
     */
        public void apiCall (String Url, String csvPath,String xmlPath) throws Exception {

            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(Url)).build();
            log.info("Request method - GET for APi -"+Url);
            //creating the client object required to send the request
            HttpClient httpClient = HttpClient.newBuilder().build();

            //Sotring the http response in String format
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                log.info("Api call is Successfull for Url -" + Url);
                log.debug("response from Url " + Url + " is - " + response.body());
                JFlat flatMe = new JFlat(response.body());

                //System.out.println(U.jsonToXml(response.body()));
                if(xmlPath != null) {
                    FileWriter writer = new FileWriter(xmlPath);
                    writer.write(U.jsonToXml(response.body()));
                    log.info("xmlFile is created at "+xmlPath);
                    writer.close();
                } else log.error(xmlPath+" Path is given as null");
                if(jsonpath != null) {
                    FileWriter writer = new FileWriter(jsonpath);
                    writer.write((response.body()));
                    log.info("jsonFile is created at "+jsonpath);
                    writer.close();
                } else log.error(xmlPath+" Path is given as null");
                flatMe.json2Sheet().headerSeparator("_").write2csv(csvPath);
            } else {
                log.fatal("Fatal error due to Un-successfull Api call -" + Url);
            }
            log.info(Url + " response data is written to file " + csvPath);
        }

    /**
     * method csvToHtml is used to convert the Api's data from csv format to the html format
     *
     * @param csvPath  - csv path is given as input to access the data from csv file
     * @param htmlPath - html path is given as input to store the html file
     */

    public void csvToHtml(String csvPath, String htmlPath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            log.info("Reader is invoked for file " + csvPath);
            String currentLine;
            if (reader.readLine() == null) log.fatal("error occurred due to empty file");
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
            log.debug("csv data from " + csvPath + " file stored in list " + lines);
        } catch (IOException e) {
            log.error("error while reading from Ghibli.csv file");
        }

        //embrace <td> and <tr> for lines and columns
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
        }
        log.info("setting html tags <tr> and <td> for lines list");

        // embrace <table> and </table> to the lines
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");
        System.out.println("html file is created at " + htmlPath);
        try (FileWriter writer = new FileWriter(htmlPath)) {
            for (String line : lines) {
                log.debug("html line writing to converted.html file " + line);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            log.error("error at html file writer{}", e);
        }
    }


    /**
     * method csvToPdf is used to convert the Api's data from csv format to the pdf format
     *
     * @param csvPath - csv path is given as input to access the data from csv file
     * @param pdfPath - pdf path is given as input to store the pdf file
     */
    public void csvToPdf(String csvPath, String pdfPath) throws DocumentException, IOException {

        CSVReader reader = new CSVReader(new FileReader(csvPath));

        log.info("Reader is invoked for file " + csvPath);
        String[] nextLine;
        String[] data = Arrays.toString(reader.readNext()).split(",");
        // System.out.println(data.length);
        int rows = data.length;
        //the instance of document is created
        Document my_pdf_data = new Document();
        log.info("intializing the  document for conversion");
        my_pdf_data.setPageSize(PageSize.A0.rotate());
        PdfWriter.getInstance(my_pdf_data, new FileOutputStream(pdfPath));
        my_pdf_data.open();
        if (reader.readNext() == null) log.fatal("error occurred due to empty input file " + csvPath);
        //PdfPTable is used to add Table to the pdf
        PdfPTable my_first_table = new PdfPTable(rows);
        PdfPCell table_cell;
        log.info("initializing Table and cell to add into movieApi.pdf");
        while ((nextLine = reader.readNext()) != null) {
            int i = 0;
            while (i <rows) {
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
    }


    public static void main(String[] args) throws Exception {
        //object is created for apiGeneric class
        apiGenric films = new apiGenric();
        films.apiCall(url,csvpath,xmlpath);
        films.csvToHtml(csvpath,htmlpath);
        films.csvToPdf(csvpath,pdfpath);
    }

}


