package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MuseumController {

    @Autowired
    private MuseumService museumService;
    private static final Logger log = Logger.getLogger(MuseumController.class);

    @GetMapping("/products")
    public String findAllProducts() {
        return "Hello moto";
    }

   @GetMapping("/get")
    public List<spring> get() {
        log.info("Get Controller method invoked");
        return museumService.get();
    }

    @PostMapping("/add")
    public spring save(@RequestBody spring springobj) {
        log.info("Save controller method invoked ");
        return  museumService.save(springobj);
    }

    @PutMapping("/update")
    public spring update(@RequestBody spring springobj) {
        log.info("update controller method invoked");
        return  museumService.update(springobj);
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        log.info("delete controller method is invoked");
         museumService.delete(id);
         return "success";
    }
}