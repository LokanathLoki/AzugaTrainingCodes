package com.azuga.training.Api;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ghibliApi {
    //   //defining connection
    private static HttpURLConnection connection;
    static final Logger log = Logger.getLogger(ghibliApi.class);
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
//Method - 1 : java.net.HttpURLConnection

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {

        //definig URL with API end-point
            URL url = new URL("https://ghibliapi.herokuapp.com/films");
            log.info("url is defined with Ghibli Api endpoint  "+url);

        // opening connection to the API
            connection = (HttpURLConnection) url.openConnection();
            log.info("setting up urlConnection for Api");

            //Request setup
            connection.setRequestMethod("GET");
            log.info("Api request method is set to GET for endPoint films");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

        // status variable to check the status of connection
            int status = connection.getResponseCode();
            log.info("getting response code from Api connection");

            if (status > 299) {             // if status is not successful i.e. not in the range of 200
            log.fatal(" Api call for url --"+url+" is unsuccessful ");
            log.debug("status for Ghibli api call --"+status);
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                log.info("Api call for url "+url+" is successfull");
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);

                }
                log.debug("response obtained from GhibliApi call "+responseContent);
                reader.close();
                log.debug("status for GhibliApi call -"+status);
            }
           System.out.println(responseContent);
           // parse(responseContent.toString());
        } catch (IOException e) {
            log.error("error occurs in file writer -"+e );
            e.printStackTrace();
        } finally {                         // the connection must be disconnected at the end
            connection.disconnect();
        }
/*Method - 2: java.net.http.HttpClient
        it is new API added in java 11, it handles asynchronus operations */
/*

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://ghibliapi.herokuapp.com/films")).build();

        //sending the above request using client ,sending it asychronusly
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
               //.thenAccept(System.out::println)    // to print the result using (::) lambda
               .thenApply(ghibliApi::parse)
                .join();                               // return the results from completable future to current state
*/

    }

    public static String parse(String responseBody) {
        JSONArray films = new JSONArray(responseBody);
        log.info("creating array for response of GhibliApi ");
        list.add("id" + "," + "title" + "," + "org_title" + "," + "roman_title" + "," +"image"+","+ "director" +","+"banner"+ "," + "release_date" + "," + "run_time" + "," + "rt_score" + "," +"url"+"\n");
        log.info("Accessing the value using key from created Array "+films);
        for (int i = 0; i < films.length(); i++) {
            JSONObject film = films.getJSONObject(i);
            String id = film.getString("id");
            String title = film.getString("title");
            String org_title = film.getString("original_title");
            String roman_title = film.getString("original_title_romanised");
            String image = film.getString("image");
            String director = film.getString("director");
            String banner = film.getString("movie_banner");
            String release_date = film.getString("release_date");
            String run_time = film.getString("running_time");
            String rt_score = film.getString("rt_score");
            String url = film.getString("url");

            list.add(id + "," + title+ ","+org_title+ ","+roman_title+","+image+ ","+director+","+banner+ ","+release_date+ ","+run_time+ ","+rt_score+ ","+url+"\n");
        }
        log.info("list is created for the Json data to write to csv file");
            try {
                 File file = new File("/Users/azuga/Desktop/ghibli.csv");
                 FileWriter writer = new FileWriter(file);
                 for (String obj : list) {
                     log.debug("line writing to csv file "+obj);
                writer.write(obj);
            }
                 log.info("writing the obtained data to ghibli.csv file --"+file);
            writer.close();
            System.out.println("file created");
        } catch (IOException e) {
                log.error("error occurred while writing to the csv file ");
            throw new RuntimeException(e);
        }

        return null;
        }
    }



