package com.azuga.training.oops;

import au.com.bytecode.opencsv.CSVReader;
import com.azuga.training.formats.CSVtoHtml;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ApiFileFormatters implements formatter {


    static final Logger log = Logger.getLogger(CSVtoHtml.class);
    static ArrayList<String> list = new ArrayList<>();


    public void apiCall (String Url, String csvPath,String jsonPath) throws Exception {

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

            if(jsonPath != null) {
                FileWriter writer = new FileWriter(jsonPath);
                writer.write(response.body());
                writer.close();
            } else log.error(jsonPath+" Path is given as null");
            flatMe.json2Sheet().headerSeparator("_").write2csv(csvPath);
        } else {
            log.fatal("Fatal error due to Un-successfull Api call -" + Url);
        }
        log.info(Url + " response data is written to file " + csvPath);
    }





    /**
     * method csvToHtml is used to convert the Api's data from csv format to the html format
     * which are obtained from csvPath and htmlPath
     */
    public void csvToHtml(String csvPath,String htmlPath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            log.info("Reader is invoked for file " + csvPath);
            String currentLine;
            if (reader.readLine() == null) log.fatal("error occurred due to empty file " + csvPath);
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
            log.debug("csv data from " + csvPath + " file stored in list " + lines);
        } catch (IOException e) {
            log.error("error while reading from Ghibli.csv file {}", e);
        }

        //embrace <td> and <tr> for lines and columns
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, "<tr><td>" + lines.get(i) + "</td></tr>");
            lines.set(i, lines.get(i).replaceAll(",", "</td><td>"));
        }
        log.info("setting html tags <tr> and <td> for lines list");

        // embrace <table> and </table>
        lines.set(0, "<table border>" + lines.get(0));
        lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "</table>");

        // output result is writing to htmlPath
        try (FileWriter writer = new FileWriter(htmlPath)) {
            for (String line : lines) {
                log.debug("html line writing to converted.html file " + line);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            log.error("error at html file writer {}" + e);
        }
    }

    /**
     * method csvToPdf is used to convert the Ghibli Api's data stored in csvPath
     * to the pdf format and storing in pdfPath
     */

    public void csvToPdf(String csvPath,String pdfPath) throws IOException, DocumentException {
        CSVReader reader;
        if (csvPath == null) {
            log.error("error due to incorrect csv path ");
        }
        File file = new File(csvPath);
        if (file.exists()) {
            reader = new CSVReader(new FileReader(csvPath));
            log.info("Reader is invoked for file " + csvPath);
            String[] nextLine;

            //the instance of document is created
            Document my_pdf_data = new Document();
            log.info("intializing the  document for conversion");
            my_pdf_data.setPageSize(PageSize.A0);
            PdfWriter.getInstance(my_pdf_data, new FileOutputStream(pdfPath));
            my_pdf_data.open();
            if (reader.readNext() == null) log.fatal("error occurred due to empty input file " + csvPath);
            //PdfPTable is used to add Table to the pdf
            PdfPTable my_first_table = new PdfPTable(11);
            PdfPCell table_cell;
            log.info("initializing Table and cell to add into movieApi.pdf");
            while ((nextLine = reader.readNext()) != null) {
                int i = 0;
                while (i <= 10) {
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
    }

    /**
     * method convertToXml is used to convert the Ghibli Api's data stored in csv
     * to the xml format file stored in xmlPath
     */
    public void convertToXml(String jsonPath , String xmlPath) {
        String result;
        try {

            // read byte data from the Sample.json file and convert it into String
            result = new String(Files.readAllBytes(Paths.get(jsonPath)));
            log.info("getting Json Data from the file " + jsonPath);
            log.debug("Json obtained from output.json " + result);

            //Convert JSON to XML
            String[] array = result.substring(3, result.length() - 1).split("}, {2}\\{");
            FileWriter file = new FileWriter(xmlPath);
            for (int i = 0; i < array.length; i++) {
                list.add(i, "{" + array[i] + "}");
                String csvData = list.get(i);

                // This method converts json object to xmlData string
                String xmlData = U.jsonToXml(csvData);
                log.debug("xmlData of Json" + " " + xmlData);
                log.info("json obtained from output.json is converted to xmlData");

                // use write() method of File to write XML data into XMLData.txt
                if (xmlData != null) {
                    file.write(xmlData);
                    log.info("xmlData is written to ToXML.xmlData file");
                    file.write("\n");
                } else log.error("error occurred while converting json to xmlData");
                file.flush();

            }
            // close FileWriter
            file.close();
            System.out.println(" XML data is successfully written " + xmlPath);
            log.info("ToXML.xml  file is created with required data");
        } catch (IOException e1) {
            log.error("IOException occurred while writing to ToXml.xml {}", e1);
        }
    }
}
