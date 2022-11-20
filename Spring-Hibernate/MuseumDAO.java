package com.example.springBoot.demo;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */
import java.util.List;

public interface MuseumDAO {

    List<spring> get();

    spring save(spring museum);

    spring update(spring museum);

    void delete(int id);
}
