package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import java.util.List;

/**
 * MuseumService interface is used for the Database methods for CRUD operations on mysql database
 */
public interface MuseumService {
    List<spring> get();

    spring save(spring museum);


    spring update(spring museum);

    void delete(int id);
}
