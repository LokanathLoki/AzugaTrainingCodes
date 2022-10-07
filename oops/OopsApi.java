package com.azuga.training.oops;

import com.azuga.training.Api.ghibliApi;
import com.azuga.training.charts.LineChart;
import com.azuga.training.charts.PieChart;
import com.azuga.training.charts.barChart;
import com.azuga.training.formats.CSVtoHtml;
import com.azuga.training.formats.CSVtoPdf;
import com.azuga.training.formats.JsonToXml;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

/**
 * class OopsApi is implementing the oops object, constructors and method concepts using the
 * methods, constructors in  other classes ,by invoking object and calling their methods
 */
public class OopsApi {
    public static void main(String[] args) throws DocumentException, IOException {

        CSVtoHtml obj = new CSVtoHtml();
        obj.csvToHtml("/Users/azuga/Desktop/Ghibli1.csv", "/Users/azuga/Desktop/converted1.html");

       /* ghibliApi films = new ghibliApi();
        films.apiCall(" https://ghibliapi.herokuapp.com/films ");*/

        CSVtoPdf topdf = new CSVtoPdf();
        topdf.csvToPdf("/Users/azuga/Desktop/Ghibli1.csv", "/Users/azuga/Desktop/movieApi.pdf");

        JsonToXml toXml = new JsonToXml();
        toXml.convertToXml("/Users/azuga/Desktop/output.json", "/Users/azuga/Desktop/ToXML.xml");

        PieChart pie = new PieChart("pieChart");
        pie.piechart();

        LineChart line = new LineChart("Movie vs runTime", "Line chart for movie nd their RunTime");
        line.lineChart();

        barChart bar = new barChart("Ratings of the movie", "Movies Vs Ratings");
        //bar.BarChart();
    }
}
