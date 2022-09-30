package com.azuga.training.zip;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import net.lingala.zip4j.ZipFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * zipper class is used to zip the files that are created from the output of formatterClass
 */
public class zipper {
    static final Logger logger = LogManager.getLogger(zipper.class);

    /**
     * getFiles method is used to obtain the files given in the path
     * @param path  - String that takes the path that holds the outputFiles
     * @return      - return the files Array
     */

    public File[] getFiles(String path) {
        logger.info("getFiles method invoked for " + path);
        File file;
        File[] files = null;
        if (path != null) {
            file = new File(path);
            files = file.listFiles();
            logger.debug("List of files: " + Arrays.toString(files));
        } else {
            logger.error("Given Path is null");
        }
        return files;
    }

    /**
     * makeZip method creates the Zip for the given files and stores in ZipPath
     * @param zipPath  - Path to store the created zip
     * @param files    - files that are needed to zip
     */
    public void makeZip(String zipPath, File[] files) {

        try {
            new ZipFile(zipPath).addFiles(Arrays.asList(files));
        } catch (IOException e) {
            logger.error("Error due to ZipException: " + e);
        }
    }
}
