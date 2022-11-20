package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * MusuemRepository is used to implement the repository methods of
 * get, save,update, delete to perform the CRUD operations for the mySql database
 * Annotation Repository indicates this class is repository
 */
@Repository
public class MusuemRepository implements MuseumDAO {

   @Autowired
   private EntityManager entityManager;

   private static final Logger log = Logger.getLogger(MusuemRepository.class);
   /**
    * get method is used to retrieve all the data from the mysql database
    * @return - returns the list of spring objects from the database
    */
   @Override
   public List<spring> get() {
      log.info("Get method invoked");
      Session currentSession = entityManager.unwrap(Session.class);
      Query<spring> query = currentSession.createQuery("from spring", spring.class);
      List<spring> list = query.getResultList();
      log.debug("Obtained list : "+list);
      return list;
   }


   /**
    * save method is used to insert the data into database
    * @param museum - takes the input of a museum object
    * @return       - the inserted spring object
    */
   @Override
   public spring save(spring museum) {
      log.info("save method invoked");
      Session currentSession = entityManager.unwrap(Session.class);
      log.debug("Inserting data : "+museum);
      currentSession.persist(museum);
      return museum;
   }

   /**
    * update method is used to update the data in the database
    * @param museum - takes the input of a museum object
    * @return       - gives updated spring object
    */
   @Override
   public spring update(spring museum) {
      log.info("update method invoked");
      Session currentSession = entityManager.unwrap(Session.class);
      currentSession.update(museum);
      log.debug("Updated data : "+museum);
      return museum;
   }

   /**
    * delete method deletes the data for the given id in the database
    * @param id - input id for the data to delete
    */
   @Override
   public void delete(int id) {
      log.info("Delete method is invoked");
      Session currentSession = entityManager.unwrap(Session.class);
      spring employeeObj = currentSession.get(spring.class, id);
      log.info("Deleting the data for id : "+id);
      currentSession.delete(employeeObj);
   }
}
