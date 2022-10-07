package com.azuga.training.oops;
/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

/**
 * class FormatConverter is used to change the file formats of the data containing
 * Ghibli Api's response
 */
public class FormatConverter {

    public static String jsonPath = "/Users/azuga/Desktop/output.json";
    public static String csvPath = "/Users/azuga/Desktop/Ghibli1.csv";
    public static String htmlPath = "/Users/azuga/Desktop/converted1.html";
    public static String xmlPath = "/Users/azuga/Desktop/ToXML.xml";
    public static String pdfPath = "/Users/azuga/Desktop/movieApi.pdf";
    public static String Url = "https://ghibliapi.herokuapp.com/films";


    public static void main(String[] args) throws Exception {

        //new object is created for interface formatter using class FileFormatters
        formatter format = new ApiFileFormatters();
        format.convertToXml(jsonPath,xmlPath);
        format.csvToHtml(csvPath,htmlPath);
        format.csvToPdf(csvPath,pdfPath);
        format.apiCall(Url, csvPath,jsonPath);
    }
}
