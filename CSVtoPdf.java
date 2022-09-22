package com.azuga.training.formats;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import java.io.*;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import com.itextpdf.text.*;
import org.apache.log4j.Logger;

/**
 * the class CSVtoPdf is derived to convert the csv content of the Api into the pdf format
 */
public class CSVtoPdf {

    static final Logger log = Logger.getLogger(CSVtoPdf.class);
    public static void main(String[] args) throws IOException, DocumentException {

                String inputCSVFile = "/Users/azuga/Desktop/ghibli.csv";
                CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
                log.info("Reader is invoked for file "+inputCSVFile);
                String [] nextLine;

                //the instance of document is created
                Document my_pdf_data = new Document();
                log.info("intializing pdf document for conversion");
                my_pdf_data.setPageSize(PageSize.A0);
                PdfWriter.getInstance(my_pdf_data, new FileOutputStream("/Users/azuga/Desktop/movieApi.pdf"));
                my_pdf_data.open();
                if (reader.readNext() ==null) log.fatal("error occurred due to empty input file "+inputCSVFile);
                //PdfPTable is used to add Table to the pdf
                PdfPTable my_first_table = new PdfPTable(11);
                PdfPCell table_cell ;
                log.info("initializing Table and cell to add into movieApi.pdf");
                while ((nextLine = reader.readNext()) != null) {
                    int i = 0;
                    while (i <= 10){
                        table_cell = new PdfPCell(new Phrase(nextLine[i]));
                    my_first_table.addCell(table_cell);
                    i++;
                }
                }
                my_pdf_data.add(my_first_table);
                log.info("table is added to movieApi.pdf file");
               // my_pdf_data.setPageSize();
                my_pdf_data.close();
                log.info("movieApi.pdf is created from csv file");
            }
        }

