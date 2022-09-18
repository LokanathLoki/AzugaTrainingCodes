package com.azuga.training.Api;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import com.fasterxml.jackson.databind.util.Converter;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class ghibliApi {
    //   //defining connection
    public static ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {
//Method - 1 : java.net.HttpURLConnection

     /*   BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
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
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
           // System.out.println(responseContent.toString());
            parse(responseContent.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {                         // the connection must be disconnected at the end
            connection.disconnect();
        }*/

/*Method - 2: java.net.http.HttpClient
        it is new API added in java 11, it handles asynchronus operations */

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://ghibliapi.herokuapp.com/films")).build();

        //sending the above request using client ,sending it asychronusly
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)    // to print the result using (::) lambda
                .thenApply(ghibliApi::parse)
                .join();                               // return the results from completable future to current state

    }

    public static String parse(String responseBody) {
        JSONArray films = new JSONArray(responseBody);
        list.add("id" + "," + "title" + "," + "org_title" + "," + "roman_title" + "," + "director" + "," + "release_date" + "," + "run_time" + "," + "rt_score" + "," + "\n");
        for (int i = 0; i < films.length(); i++) {
            JSONObject film = films.getJSONObject(i);
            String id = film.getString("id");
            String title = film.getString("title");
            String org_title = film.getString("original_title");
            String roman_title = film.getString("original_title_romanised");
            String director = film.getString("director");
            String release_date = film.getString("release_date");
            String run_time = film.getString("running_time");
            String rt_score = film.getString("rt_score");
            list.add(id + "," + title+ ","+org_title+ ","+roman_title+ ","+director+ ","+release_date+ ","+run_time+ ","+rt_score+ "," +"\n");
        }
            try {
                 File file = new File("/Users/azuga/Desktop/api.csv");
                 FileWriter writer = new FileWriter(file);
                 for (String obj : list) {
                writer.write(obj);
            }
            writer.close();
            System.out.println("file created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
        }
    }



