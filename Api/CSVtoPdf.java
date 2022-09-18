package com.azuga.training.Api;

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

/**
 * the class CSVtoPdf is derived to convert the csv content of the Api into the pdf format
 */
public class CSVtoPdf {
    public static void main(String[] args) throws IOException, DocumentException {

                String inputCSVFile = "/Users/azuga/Desktop/api.csv";
                CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
                String [] nextLine;

                //the instance of document is created
                Document my_pdf_data = new Document();
                PdfWriter.getInstance(my_pdf_data, new FileOutputStream("/Users/azuga/Desktop/ap12.pdf"));
                my_pdf_data.open();

                //PdfPTable is used to add Table to the pdf
                PdfPTable my_first_table = new PdfPTable(8);
                PdfPCell table_cell ;
                while ((nextLine = reader.readNext()) != null) {
                    int i = 0;
                    while (i <= 7){
                        table_cell = new PdfPCell(new Phrase(nextLine[i]));
                    my_first_table.addCell(table_cell);
                    i++;
                }
                }
                my_pdf_data.add(my_first_table);
                my_pdf_data.close();
            }
        }

