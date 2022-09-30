package FileFormatter;

import com.github.opendevl.JFlat;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiGenric {
    static final Logger log = Logger.getLogger(ApiGenric.class);
    public static String csvPath, jsonPath;
    public static String Url ;

    ApiGenric(String url, String PathOfCsv ) {
        Url = url;
        csvPath=PathOfCsv ;

    }

    public static void main(String[] args) throws Exception {
        ApiGenric films = new ApiGenric("https://ghibliapi.herokuapp.com/films","/Users/azuga/Desktop/FilmsApi.csv");
        films.apiCall();

    }
        public void apiCall () throws Exception {

            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(Url)).build();
            log.info("Request method - GET for APi -"+Url);
            //creating the client object required to send the request
            HttpClient httpClient = HttpClient.newBuilder().build();

            //Sotring the http response in String format
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                log.info("Api call is Successfull for Url -" + Url);
                log.debug("response from Url " + Url + " is - " + response.body());
               /* if(jsonPath != null) {
                    FileWriter writer = new FileWriter(jsonPath);
                    writer.write(response.body());
                } else log.error(jsonPath+" Path is given as null");*/
                JFlat flatMe = new JFlat(response.body());
                flatMe.json2Sheet().headerSeparator("_").write2csv(csvPath);
            } else {
                log.fatal("Fatal error due to Un-successfull Api call -" + Url);
            }
            log.info(Url + " response data is written to file " + csvPath);
        }

}


