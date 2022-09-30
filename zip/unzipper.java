package com.azuga.training.zip;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * unzipper class used to unzip the zipped files
 */
public class unzipper {
    private static final Logger logger = LogManager.getLogger(unzipper.class);

    /**
     * method unzip reads the zip from targetZipPath and unzips the files in it
     * and stores them in the given destinationPath
      * @param targetZipFilePath   - String  that holds the zips path
     * @param destinationFolderPath - String that holds the files path to store unzipped files
     * @param password              - String that stores the password for the zip
     */
    public void unzip(String targetZipFilePath, String destinationFolderPath, String password) {
        logger.info("Unzip method is invoked");
        try {
            ZipFile zipFile = new ZipFile(targetZipFilePath);
            logger.info("searching for the zipFile");
            if (zipFile.isEncrypted()) {
                logger.info("Zip File is Encrypted, enter password");
                zipFile.setPassword(password.toCharArray());
                logger.info("File opened with password");
            }

            zipFile.extractAll(destinationFolderPath);
            logger.info("File Unzipped at "+destinationFolderPath);

        } catch (ZipException e) {
            logger.error("Error due to ZipException: {}",e);
        }
    }


}
