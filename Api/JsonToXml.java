package com.azuga.training.Api;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
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

    public static void main(String[] args) {
        BufferedReader reader;
        String line;
        ArrayList<String> str = new ArrayList<>();
        StringBuilder responseContent = new StringBuilder();
        try {

            //definig URL with API end-point
            URL url = new URL("https://ghibliapi.herokuapp.com/films");

            // opening connection to the API
            connection = (HttpURLConnection) url.openConnection();

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // status variable to check the status of connection
            int status = connection.getResponseCode();

            if (status > 299) {             // if status is not successful i.e. not in the range of 200

                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                String result = responseContent.substring(2,responseContent.length()-2);

            }
            //System.out.println(responseContent.toString());
            // json(responseContent.substring(1,responseContent.length()-1));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {                         // the connection must be disconnected at the end
            connection.disconnect();
        }
            String result;
        try {

            // read byte data from the Sample.json file and convert it into String
            result = new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/output.json")));
            //Convert JSON to XML
            //System.out.println(result.substring(3,result.length()-1));
            String[] array = result.substring(3,result.length()-1).split("}, {2}\\{");
            FileWriter file = new FileWriter("/Users/azuga/Desktop/ToXML.txt");
            file.write("<?xml version=\\\"1.0\\\" encoding=\\\"ISO-8859-15\\\"?>"+"\n"+"<root>\n");
            for(int i=0;i< array.length;i++) {
                str.add(i, "{" + array[i] + "}");
                String list = str.get(i);
                //System.out.println(list);

                // This method converts json object to xml string
                String xml = convertToXML(list);

                // use write() method of File to write XML data into XMLData.txt
                file.write(xml);
                file.write("\n");
                file.flush();
            }
            file.write("</root>");

            // close FileWriter
            file.close();
            System.out.println("Your XML data is successfully written into ToXML.xml");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * the json method is used to create the json file for the data obtained from the Api-call
     * into the file named  output.json
     * @param line - String line is the response obtained from the Api-call
     */
    public static void json(String line) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/azuga/Desktop/output.json")));
            writer.write(line.toString());
            writer.flush();
            writer.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * The method ConvertToXml method is used to convert the string into the xml string
     * @param jsonString - the String read from the json file is given as argument
     * @return  -  it returns the XMl string which is converted from the given input string
     */
    public static String convertToXML(String jsonString) throws JSONException {    // handle JSONException

        // create instance of JSONObject by passing jsonString to the constructor
        JSONObject jsonObject = new JSONObject(jsonString);

        // use XML.toString() method to convert JSON into XML and store the result into a string

        // pass the XML data to the main() method
       return XML.toString(jsonObject);
      }
}

