package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import org.springframework.transaction.annotation.Transactional;

/**
 * MuseumServiceImpl class is used to implement the abstract methods of the class MuseumService which is used to do the
 * CRUD operations for the database
 */
@Service
public class MuseumServiceImpl implements MuseumService{
    @Autowired
    private MuseumDAO museumDAO;

    /**
     * get method is used to retrieve all the data from the mysql database
     * @return - returns the list of spring objects from the database
     */
    @Transactional
    @Override
    public List<spring> get() {
        return museumDAO.get();
    }

    /**
     * save method is used to insert the data into database
     * @param museum - takes the input of a museum object
     * @return       - the inserted spring object
     */
    @Transactional
    @Override
    public spring save(spring museum) {
        museumDAO.save(museum);
        return museum;
    }

    /**
     * update method is used to update the data in the database
     * @param museum - takes the input of a museum object
     * @return       - gives updated spring object
     */
    @Transactional
    @Override
    public spring update(spring museum) {
        museumDAO.update(museum);
        return museum;
    }

    /**
     * delete method deletes the data for the given id in the database
     * @param id - input id for the data to delete
     */
    @Transactional
    @Override
    public void delete(int id) {
        museumDAO.delete(id);
    }

}
