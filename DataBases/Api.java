package DataBases;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class Api is used to get the json data from the api call and give the Key-values to insert into the Data base table
 * it has method to create the list of values  from the museum Api's response
 */
public class Api {

        private static final Logger log =  Logger.getLogger(Api.class);
    public static int column = 0;
    public static  String intData = "objectID,objectBeginDate,objectEndDate";
    public static String boolData = "isTimelineWork,isPublicDomain,isHighlight";
    public static String arrays = "additionalImages,Length,Thickness,constituents,role, Height,name, constituentULAN_URL, constituentWikidata_URL, gender,measurements,tags, AAT_URL, Wikidata_URL, AAT_URL, Wikidata_URL, elementDescription, elementMeasurements, Width";
    public static ArrayList<Object> values = new ArrayList<>();
    public static   ArrayList<String> list = new ArrayList<>();
    public String outputResponse="";
    static String ApiUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/";

    /**
     * Method columnsAndValues is used to return a list of values from the museum Api's response data
     * @param Url - input url to the Api call for the data
     * @return - the object ArrayList that stores the key values of museum Api's data
     */
    public ArrayList<Object> columnsAndValues(String Url) {
        try {
            log.info("method columnAndValues is invoked");
            ArrayList<String> lines = new ArrayList<>();
            Random rm = new Random();
            StringBuilder sb = new StringBuilder();
            ArrayList<String> key = new ArrayList<>();
            sb.append("[");
            for (int i = 1; i <= 1; i++) {
                log.info("setting up url for museumApi");
                log.debug("url - "+Url);
                String url = Url + rm.nextInt(471581);
                HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();//Used to build the api request by specifying the particular method
                HttpClient httpClient = HttpClient.newBuilder().build();//Used to create the client object required to send the request
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());//Used to hold the response in string format
                if (response.statusCode() == 200) {
                    System.out.println("connecting with server");
                    log.info("Api call for url "+Url+" is successful");
                    sb.append(response.body().replaceAll("\\[]", "null"));
                } else
                    System.out.println("connection lost");
                log.fatal("Api call for url "+Url+" is unsuccessful");
            }

            sb.append("]");
             outputResponse = String.valueOf(sb);
            log.debug("response obtained "+outputResponse);
            String i = sb.toString().replaceAll("}\\{", "},{");
            String response = i.substring(2, i.length() - 2);
            String[] str = response.split(",");
            for (String s : str) {
                //  System.out.println(s);
                String[] keys = s.split(":");
                key.add(keys[0]);
                lines.add(keys[keys.length - 1]);
                if ((keys[0].startsWith("\"") && (keys[0].endsWith("\"")))) {
                    //System.out.println(keys[0]);
                    list.add(keys[0].substring(1, keys[0].length() - 1));
                }
            }
            log.info("Headers are extracted from museumApi");
        }catch(IOException e){
            log.error("IOException "+e);
        } catch(InterruptedException i){
            log.error("InterruptedException "+i);
        }
        return parse(outputResponse, list);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Api museum = new Api();
        System.out.println(museum.columnsAndValues(ApiUrl));
    }

    /**
     * Method parse takes the Api's response as input and filter it out to get the required values to insert into table
     * @param responseBody - string of response is given as input
     * @param columns - the list of headers to  get their values
     * @return - returns the ArrayList of the object with their values
     */
    public static ArrayList<Object> parse(String responseBody, ArrayList<String> columns) {
        log.info("Parse method invoked");
        JSONArray films = new JSONArray(responseBody);
        for (int i = 0; i < films.length(); i++) {
            JSONObject film = films.getJSONObject(i);
            for(String header : columns){
                if(intData.contains(header)) {
                    int id = film.getInt(header);
                    values.add(id);
                   // System.out.println(id);
                }else if(boolData.contains(header)){
                    boolean bool = film.getBoolean(header);
                    values.add(bool);
                   // System.out.println(bool);
                } else if(arrays.contains(header)){
                    continue;
                } else {
                    String data = film.getString(header);
                    values.add(data);
                   // System.out.println(data);
                }
            }
            column++;
        }
        log.info(" required Values are obtained");
        log.debug("stored key values :- "+values);
        return values;
    }
}

