package org.example;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.UpdateResult;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * In class MongoDB we connected to the MongoDB database and performed CRUD operations for the database and its collections
 */
public class MongoDB {
    ArrayList<Document> documents = new ArrayList<>();

    private static  final Logger log =  Logger.getLogger(MongoDB.class);
    /**
     * Method getDatabases  is used to retrieve all databases from the MongoDB
     */
    public void getDatabases() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        log.info("Getting data Bases ");
        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        databases.forEach(dbase -> System.out.println(dbase.toJson()));
        mongoClient.close();
    }

    /**
     *  Method getCollections  is used to retrieve all collections in the given database from the MongoDB
     */
    public void getCollections() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("firstDb");

        log.info("Getting collections from firstDb database");
        //Getting all collections in DataBase
        MongoCursor<String> coll = db.listCollectionNames().iterator();
        System.out.println("Collections in DataBase: \n");
        while (coll.hasNext()) {
            System.out.println(coll.next());
        }
        mongoClient.close();
    }

    /**
     * insertData method takes a json input and insert it into the MongoDB collection as a document
     * @param inputJson - the Json data to insert into the Database
     */
    public void insertData(String inputJson) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("firstDb");

        log.info("insertData method is invoked");
        //creating collection or get collection if exists.
        MongoCollection<Document> collection = db.getCollection("arrays");
        log.debug("input data : "+inputJson);
        //Inserting sample records by creating documents.

        collection.insertOne(Document.parse(inputJson));
        System.out.println("Insert is completed");
        mongoClient.close();
    }


    /**
     * readData method reads all the data from the  MongoDB collection and returns the data in json format
     * @return - the data read from the collection is returned in an ArrayList
     */
    public ArrayList<Document> readData() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("firstDb");

        log.info("readData method is invoked");
        //creating collection or get collection if exists.
        MongoCollection<Document> collection = db.getCollection("arrays");
        Bson projection = Projections.excludeId();
        log.info("excluding _id field from read");
        //Listing All Mongo Documents in Collection
        FindIterable<Document> iterDoc = collection.find().projection(projection);
        int i = 1;
        // Getting the iterator
        Iterator<Document> it = iterDoc.iterator();
        while (it.hasNext()) {
            documents.add(it.next());
            i++;
        }
        log.debug("documents in DB: "+documents);
        mongoClient.close();
        return documents;
    }

    /**
     * updateData method takes a json input and update the given data for the given json id in MongoDB collection
     * @param input - the Json data to update in the Database
     */
    public void updateData(String input) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("firstDb");
        String id;
        log.info("UpdateData method is invoked");

        //creating collection or get collection if exists.
        MongoCollection<Document> collection = db.getCollection("arrays");

        try {
            JSONObject json = new JSONObject(input);
            id = json.getString("objectID");
            log.debug("Updating for ObjectID : "+id);
            Bson filter = Filters.eq("objectID", json.get("objectID").toString());
            BasicDBObject searchQuery = new BasicDBObject("objectID", id);
            log.debug("Updating for "+searchQuery);
            BasicDBObject updateFields = new BasicDBObject();

            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String temp = keys.next();
                updateFields.append(temp, json.get(temp).toString());
            }
           log.debug("Updating data : "+updateFields);
            BasicDBObject setQuery = new BasicDBObject();
            setQuery.append("$set", updateFields);
            UpdateResult updateResult = collection.updateOne(searchQuery, setQuery);
            log.info("Document updated successfully..");
        } catch (JSONException j) {
            log.error("JsonException "+j.getMessage());
        }

        log.info("Printing data to verify update");
        readData();
        mongoClient.close();
    }

    /**
     * deleteData method takes a value input and delete the data for the given  id in MongoDB collection
     * @param value - the id to delete in the database
     */
    public void deleteData(Object value) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("firstDb");

        log.info("deleteData method is invoked");
        //creating collection or get collection if exists.
        MongoCollection<Document> collection = db.getCollection("arrays");

        log.debug("Deleting data for objectID : "+value);
        //deleting documents
        collection.deleteOne(Filters.eq("objectID", value));

        readData();
        mongoClient.close();
    }

    public static void main(String[] args) {
        MongoDB obj = new MongoDB();
        //obj.getDatabases();
        // obj.getCollections();
        // obj.insertData( "{\"objectID\":348437986,\"isHighlight\":false,\"accessionNumber\":\"49.3.3\",\"accessionYear\":\"1949\",\"isPublicDomain\":false,\"primaryImage\":\"\",\"primaryImageSmall\":\"\",\"additionalImages\":null,\"constituents\":[{\"constituentID\":79798,\"role\":\"Artist\",\"name\":\"Stereotyped by J. S. Redfield\",\"constituentULAN_URL\":\"\",\"constituentWikidata_URL\":\"\",\"gender\":\"\"}],\"department\":\"Drawings and Prints\",\"objectName\":\"Book\",\"title\":\"Deacon Giles' Distellery. . .Entered according to act of congress in 1835\",\"culture\":\"\",\"period\":\"\",\"dynasty\":\"\",\"reign\":\"\",\"portfolio\":\"\",\"artistRole\":\"Artist\",\"artistPrefix\":\"Stereotyped by\",\"artistDisplayName\":\"J. S. Redfield\",\"artistDisplayBio\":\"New York, NY\",\"artistSuffix\":\"\",\"artistAlphaSort\":\"Redfield, J. S.\",\"artistNationality\":\"American\",\"artistBeginDate\":\"1800\",\"artistEndDate\":\"1900\",\"artistGender\":\"\",\"artistWikidata_URL\":\"\",\"artistULAN_URL\":\"\",\"objectDate\":\"1835\",\"objectBeginDate\":1835,\"objectEndDate\":1835,\"medium\":\"\",\"dimensions\":\"\",\"measurements\":null,\"creditLine\":\"The Elisha Whittelsey Collection, The Elisha Whittelsey Fund, 1949\",\"geographyType\":\"\",\"city\":\"\",\"state\":\"\",\"county\":\"\",\"country\":\"\",\"region\":\"\",\"subregion\":\"\",\"locale\":\"\",\"locus\":\"\",\"excavation\":\"\",\"river\":\"\",\"classification\":\"Books\",\"rightsAndReproduction\":\"\",\"linkResource\":\"\",\"metadataDate\":\"2020-03-02T21:50:01.377Z\",\"repository\":\"Metropolitan Museum of Art, New York, NY\",\"objectURL\":\"https://www.metmuseum.org/art/collection/search/348437\",\"tags\":null,\"objectWikidata_URL\":\"\",\"isTimelineWork\":false,\"GalleryNumber\":\"\"}");
        // obj.deleteData("id",4);
        obj.updateData("{\"objectID\":120,\"isHighlight\":true,\"accessionNumber\":\"4000.3.3\",\"accessionYear\":\"2949\",\"isPublicDomain\":true,\"primaryImage\":\"\",\"primaryImageSmall\":\"\",\"additionalImages\":null,\"constituents\":[{\"constituentID\":79798,\"role\":\"Artist\",\"name\":\"Stereotyped by J. S. Redfield\",\"constituentULAN_URL\":\"\",\"constituentWikidata_URL\":\"\",\"gender\":\"\"}],\"department\":\"Drawings and Prints\",\"objectName\":\"Book\",\"title\":\"Deacon Giles' Distellery. . .Entered according to act of congress in 1835\",\"culture\":\"\",\"period\":\"\",\"dynasty\":\"\",\"reign\":\"\",\"portfolio\":\"\",\"artistRole\":\"Artist\",\"artistPrefix\":\"Stereotyped by\",\"artistDisplayName\":\"J. S. Redfield\",\"artistDisplayBio\":\"New York, NY\",\"artistSuffix\":\"\",\"artistAlphaSort\":\"Redfield, J. S.\",\"artistNationality\":\"American\",\"artistBeginDate\":\"1800\",\"artistEndDate\":\"1900\",\"artistGender\":\"\",\"artistWikidata_URL\":\"\",\"artistULAN_URL\":\"\",\"objectDate\":\"1835\",\"objectBeginDate\":1835,\"objectEndDate\":1835,\"medium\":\"\",\"dimensions\":\"\",\"measurements\":null,\"creditLine\":\"The Elisha Whittelsey Collection, The Elisha Whittelsey Fund, 1949\",\"geographyType\":\"\",\"city\":\"\",\"state\":\"\",\"county\":\"\",\"country\":\"\",\"region\":\"\",\"subregion\":\"\",\"locale\":\"\",\"locus\":\"\",\"excavation\":\"\",\"river\":\"\",\"classification\":\"Books\",\"rightsAndReproduction\":\"\",\"linkResource\":\"\",\"metadataDate\":\"2020-03-02T21:50:01.377Z\",\"repository\":\"Metropolitan Museum of Art, New York, NY\",\"objectURL\":\"https://www.metmuseum.org/art/collection/search/348437\",\"tags\":null,\"objectWikidata_URL\":\"\",\"isTimelineWork\":false,\"GalleryNumber\":\"\"}");
        ArrayList<Document> d = obj.readData();
        for (Document doc : d) {
            System.out.println(doc.toJson());
        }


    }
}
