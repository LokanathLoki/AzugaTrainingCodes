package com.azuga.training.formats;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import com.github.underscore.U;
import org.apache.log4j.Logger;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * the class JsonToXml is used to convert the json data obtained from the Api-call into the xml format
 */
public class JsonToXml {
    private static HttpURLConnection connection;
    static ArrayList<String> list = new ArrayList<>();
    static final Logger log = Logger.getLogger(JsonToXml.class);

    public static void main(String[] args) {
        BufferedReader reader;
        String line;

        StringBuilder responseContent = new StringBuilder();
        try {

            //definig URL with API end-point
            URL url = new URL("https://ghibliapi.herokuapp.com/films");
            log.info("Api is invoked for GhibliApi url - \"https://ghibliapi.herokuapp.com/films\"");
            // opening connection to the API
            connection = (HttpURLConnection) url.openConnection();
            log.info("url Connection is opened");

            //Request setup
            connection.setRequestMethod("GET");
            log.info(" Api Request Method - GET for url -"+url);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // status variable to check the status of connection
            int status = connection.getResponseCode();

            if (status == 200) {             // if status is not successful i.e. not in the range of 200
            log.info("Api call is succesfull with status -"+status);
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                log.fatal("Api call is unsuccessful for "+url+"  with status "+status);
            }
        } catch (IOException e) {
            log.error("error occurred during Api call for GhibliApi");
            e.printStackTrace();
        } finally {                         // the connection must be disconnected at the end
            log.info("Connection is disconnected");
            connection.disconnect();

        }
        String result;
        try {

            // read byte data from the Sample.json file and convert it into String
            result = new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/output.json")));
            log.info("getting Json Data from the file output.json");
            //Convert JSON to XML
            //System.out.println(result.substring(3,result.length()-1));
           String[] array = result.substring(3, result.length() - 1).split("}, {2}\\{");
            FileWriter file = new FileWriter("/Users/azuga/Desktop/ToXML.xml");

            for(int i=0;i< array.length;i++) {
                list.add(i,"{"+array[i]+"}");
                String lt = list.get(i);
                log.debug("Json Data"+" "+lt);


                // This method converts json object to xml string
                String xml = U.jsonToXml(lt);
                log.debug("xml of Json"+" "+xml);
                log.info("json obtained is converted to xml");

                // use write() method of File to write XML data into XMLData.txt
                if(xml != null) {
                    file.write(xml);
                    log.info("xml is written to ToXML.xml file");
                    file.write("\n");
                } else log.error("error occurred while converting json to xml");
                file.flush();

            }
            // close FileWriter
            file.close();
            System.out.println(" XML data is successfully written into ToXML.xml");
            log.info("ToXML.xml  file is created");
        } catch (IOException e1) {
            log.error("error occured at file writing to ToXml.xml");
            e1.printStackTrace();
        }
    }

    /**
     * the json method is used to create the json file for the data obtained from the Api-call
     * into the file named  output.json
     *
     * @param line - String line is the response obtained from the Api-call
     */
    public static void json(String line) {
        log.info("json method invoked for response obtained from GhibliApi call");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/azuga/Desktop/output.json")));
            writer.write(line.toString());
            writer.flush();
            writer.close();
            log.info("Json file created");
        } catch (IOException ignored) {
            log.error("error at json file creation output.json" );
        }
    }

}


