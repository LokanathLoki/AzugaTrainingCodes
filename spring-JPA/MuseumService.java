package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */
import java.util.ArrayList;


/**
 * MuseumService interface is used to derive  methods for CRUD operations
 */
public interface MuseumService {
    ArrayList<spring> findAllData();
    spring findAllDataByID(Integer id);
    spring update(spring id);
    void addData(spring s);
    void deleteAllData();
    void deleteDataByID(Integer id);

}
