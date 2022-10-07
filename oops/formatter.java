package com.azuga.training.oops;

import com.itextpdf.text.DocumentException;


import java.io.IOException;

public interface formatter {

    void csvToHtml(String csvPath , String htmlpath);

    void csvToPdf(String csvPath , String pdfpath) throws IOException, DocumentException;

    void convertToXml(String jsonPath , String xmlpath);

    void apiCall(String Url,String csvPath,String jsonPath) throws Exception;
}
