package FileFormatter;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class FormatConverter is used to change the file formats of the data containing
 * Ghibli Api's response
 */
public class FormatConverter {
     public static String jsonPath = "./././InputFiles/output.json";
     public static String csvPath = "./././InputFiles/Ghibli1.csv";
     public static String htmlPath = "./././OutputFiles/converted2.html";
     public static String xmlPath = "./././OutputFiles/ToXML1.xml";
     public static String pdfPath = "./././OutputFiles/movieApi1.pdf";

     public static String url = "https://ghibliapi.herokuapp.com/films";
    public static void main(String[] args) throws Exception {
        String path2 = "." + File.separator + "." + File.separator + "." + File.separator + "InputFiles" + File.separator;
        File input = new File(path2);
        if(input.mkdir()) {
            System.out.println("input directory is created");
        }
        //new object is created for interface formatter using class FileFormatters
        formatter format = new FileFormatters();
        format.apiCall(url,csvPath,jsonPath);


        String path = "." + File.separator + "." + File.separator + "." + File.separator + "OutputFiles" + File.separator;
        File file = new File(path);
        if (file.mkdir()) {
            System.out.println("Output directory created");
        }
        String path1 = "." + File.separator + "." + File.separator + "." + File.separator + "LogFiles" + File.separator;
        File logFile = new File(path1);
        if (logFile.mkdir()) {
            System.out.println("logs directory is created");
        }


        format.convertToXml(jsonPath, xmlPath);
        format.csvToHtml(csvPath, htmlPath);
        format.csvToPdf(csvPath, pdfPath);

        Path pathOFCsv = Paths.get(csvPath);
        Path pathOfJson = Paths.get(jsonPath);
        if(csvPath != null && jsonPath != null) {
            Files.delete(pathOFCsv);
            Files.delete(pathOfJson);
            System.out.println("input files deleted succesfully");
        }
    }
}
