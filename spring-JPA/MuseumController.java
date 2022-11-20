package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * MuseumController is used as rest controller to perform the methods for CRUD operations
 */
@RestController
public class MuseumController {

    @Autowired
    MuseumRepository mus;
    private static  final Logger log = Logger.getLogger(MuseumController.class);
    @Autowired
    MuseumServiceImpl MusServiceImpl;
    @GetMapping("/")
    public String intial(){
        return "hello Moto";
    }
    @PostMapping("/add")
    public spring add(@RequestBody spring m) {
       log.info("add method in controller is invoked");
        MusServiceImpl.addData(m);
        return m;
    }

    @GetMapping("/findall")
    public ArrayList<spring> getAllData() {
        log.info("getData method in controller is invoked");
        return MusServiceImpl.findAllData();
    }

    @GetMapping("/findbyid/{id}")
    public spring getDataUsingId(@PathVariable Integer id) {
        spring obj = new spring();
        log.info("getDataByID method in controller is invoked");
        obj= MusServiceImpl.findAllDataByID(id);
        if(obj != null) {
            return obj;
        } else return null;
    }


    @PutMapping("/update")
    public spring update(@RequestBody spring s) {
        log.info("update method in controller is invoked");
        return MusServiceImpl.update(s);
    }

    @DeleteMapping("/deleteall")
    public void delete() {
        log.info("deleteAll method in controller is invoked");
        MusServiceImpl.deleteAllData();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        log.info("deleteByID method in controller is invoked");
        MusServiceImpl.deleteDataByID(id);
        return "Deleted successfully";
    }


}
