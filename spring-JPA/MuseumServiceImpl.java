package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * MuseumServiceImpl implements  the methods  in the MuseumService
 */
@Service
public class MuseumServiceImpl implements MuseumService{

    @Autowired
    MuseumRepository MuseRepo;
    private static final Logger log =  Logger.getLogger(MuseumServiceImpl.class);

    /**
     * findAllData method is to get all data in the newmuseum table in databse
     * @return  - Returns the arrayList of the objects in the table
     */
    @Override
    public ArrayList<spring> findAllData() {
        log.info("findAllData method invoked");
        return (ArrayList<spring>) MuseRepo.findAll();
    }

    @Override
    public spring findAllDataByID(Integer id) {
        log.info("findDataByID method is invoked");
        Optional<spring> opt = MuseRepo.findById(id);
        log.debug("Finding data for id : "+id);
        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }

    /**
     * Update method is used to update the data in the database
     * @param m - input museum to update the data
     * @return - returns the updated data
     */
    @Override
    public spring update(spring m) {
        log.info("update method invoked");
        MuseRepo.save(m);
        return m;
    }


    /**
     * addData method is used  to insert the data into the database
     * @param s - takes the data as input
     */
    @Override
    public void addData(spring s) {
        log.info("addData method invoked");
        MuseRepo.save(s);
    }

    /**
     * deleteAll method is used to delete all the data in the table of database
     */
    @Override
    public  void deleteAllData() {
        log.info("DeleteAll method is ivoked");
        MuseRepo.deleteAll();
    }

    /**
     * deleteDataByID method is invoked  to delete the data for the given ID
     * @param id - input ID to delete the ID's data
     */
    @Override
    public void deleteDataByID(Integer id) {
        log.info("deleteDataByID method invoked");
        MuseRepo.deleteById(id);
    }

}
