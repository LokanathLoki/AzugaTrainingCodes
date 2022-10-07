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
import java.util.Arrays;

import com.itextpdf.text.*;
import org.apache.log4j.Logger;

/**
 * the class CSVtoPdf is derived to convert the csv content of the Api into the pdf format
 */
public class CSVtoPdf {

    static final Logger log = Logger.getLogger(CSVtoPdf.class);

    /**
     * method csvToPdf is used to convert the Api's data from csv format to the pdf format
     *
     * @param csvPath - csv path is given as input to access the data from csv file
     * @param pdfPath - pdf path is given as input to store the pdf file
     */

    public int csvToPdf(String csvPath, String pdfPath)  {
        if(csvPath != null) {
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
                    }else { log.error("given pdf path is null"); return -1;}
                } catch (DocumentException d) {
                    log.error("Document exception " + d);
                    return -1;
                } catch (IOException i) {
                    log.error("IOException occyred" + i);
                    return -1;
                }
            }else { log.error("Csv file does not exists "+ csvPath); return -1;}
        }else { log.error("given csv path is null"); return -1;}
        return 1;
    }

    public static void main(String[] args)  {
        CSVtoPdf toPdf = new CSVtoPdf();
        toPdf.csvToPdf("/Users/azuga/Desktop/FilmsApi.csv", "/Users/azuga/Desktop/FilmsApiNew.pdf");

    }
}
