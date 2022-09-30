package FileFormatter;

import com.itextpdf.text.DocumentException;


import java.io.IOException;

public interface formatter {

    void csvToHtml(String csvPath, String htmlPath);

    void csvToPdf(String csvPath, String pdfPath) throws IOException, DocumentException;

    void convertToXml(String jsonPath, String xmlPath);

    void apiCall(String ApiUrl, String csvpath, String jsonPath) throws Exception;
}
