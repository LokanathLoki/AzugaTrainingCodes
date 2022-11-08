package org.example;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.Document;
import java.util.ArrayList;

/**
 * class MongoResource is a resource class to perform http requests for performing CRUD operations in MongoDB database
 */
@Path("/mongodb")
public class MongoResource {
    ArrayList<Document>data = new ArrayList<>();
    MongoDB object = new MongoDB();

    /**
     * Method read performs Read data function from the MongoDB database using GET HttpRequest
     * @return - returns the data read from the database in ArrayList form
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Document> read( ){
    data = (object.readData());
    return data;
    }

    /**
     * Method insert performs insert data function for the MongoDB database using POST HttpRequests
     * @return - returns a string message
     */
    @POST
    @Path("insert")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String insert(String input) {
        object.insertData(input);
        return "{ 'message': 'inserted successfully' }";
    }

    /**
     * Method update performs update data function from the MongoDB database  using PUT HttpRequests
     * @return - returns a string message
     */
    @PUT
    @Path("update")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String upadate(String input) {
        object.updateData(input);
        return  "";
    }

    /**
     * Method delete performs delete data function from the MongoDB database  using DELETE HttpRequests
     * @return - returns a string message
     */
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id")  String id) {
        System.out.println(id);
        object.deleteData((id));
        return "{ 'message' : 'deleted successfully' }";
    }
}
