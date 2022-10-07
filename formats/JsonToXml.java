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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * the class JsonToXml is used to convert the json data obtained from the Api-call into the xml format
 */
public class JsonToXml {
    static ArrayList<String> list = new ArrayList<>();
    static final Logger log = Logger.getLogger(JsonToXml.class);
    public String jsonPath, xmlPath;


    /**
     * Method convertToXml used to create xml file of the response content of Ghibli Api's json file from
     * given jsonPath  and stores in xmlPath
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


    public static void main(String[] args) {

        //object is invoked for JsonToXml using its constructor
        JsonToXml toXml = new JsonToXml();

        //method in the class JsonToXml is invoked for the object
        toXml.convertToXml("/Users/azuga/Desktop/FilmsApi.json","/Users/azuga/Desktop/FilmsApi.xml");

    }
}


