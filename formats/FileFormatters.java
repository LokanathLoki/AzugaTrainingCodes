package com.azuga.training.formats;

import au.com.bytecode.opencsv.CSVReader;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileFormatters {
    public static String jsonPath = "/Users/azuga/Desktop/output.json";
    public static String csvPath = "/Users/azuga/Desktop/Ghibli1.csv";
    public static String htmlPath = "/Users/azuga/Desktop/converted1.html";
    public static String xmlPath = "/Users/azuga/Desktop/ToXML.xml";
    public static String pdfPath = "/Users/azuga/Desktop/movieApi.pdf";

    static final Logger log = Logger.getLogger(CSVtoHtml.class);
    static ArrayList<String> list = new ArrayList<>();

    /**
     * method csvToHtml is used to convert the Api's data from csv format to the html format
     * which are obtained from csvPath and htmlPath
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

    /**
     * method csvToPdf is used to convert the Ghibli Api's data stored in csvPath
     * to the pdf format and storing in pdfPath
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

    /**
     * method convertToXml is used to convert the Ghibli Api's data stored in csv
     * to the xml format file stored in xmlPath
     */
    public int convertToXml(String jsonPath, String xmlPath) {
        if (jsonPath != null) {
            File jsonFile = new File(jsonPath);
            if (jsonFile.exists()) {
                try {

                    // read byte data from the Sample.json file and convert it into String
                    FileWriter file = new FileWriter(xmlPath);
                    // This method converts json object to xml string
                    BufferedReader reader = new BufferedReader(new FileReader(jsonPath));
                    log.info("getting Json Data from the file " + jsonPath);
                    StringBuilder json = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        json.append(line);
                    }
                    String xml = U.jsonToXml(json.toString());
                    //System.out.println(xml);
                    log.debug("xml of Json" + " " + xml);
                    log.info("json obtained from output.json is converted to xml");

                    // use write() method of File to write XML data into XMLData.txt
                    if (xml != null) {
                        file.write(xml);
                        log.info("xml is written to ToXML.xml file");
                        file.write("\n");
                    } else log.error("error occurred while writing to xml file " + xmlPath);
                    file.flush();

                    // close FileWriter
                    file.close();
                    System.out.println(" XML data is successfully written " + xmlPath);
                    log.info("ToXML.xml  file is created with required data");
                } catch (IOException e1) {
                    log.error("IOException occurred while writing to ToXml.xml {}" + e1);
                    return -1;
                }
            }else {log.error("Json file does not exists in given path"); return -1;}
        }else {log.error(" given Json path is null"); return -1;}
        return 1;
    }
}
