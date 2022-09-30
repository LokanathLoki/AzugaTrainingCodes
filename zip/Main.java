package com.azuga.training.zip;


/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import java.io.File;

/**
 * Main class that invokes the Mail, unzipper and zipper class methods
 * used to zip the files and send through the mail
 */
public class Main {
    public static void main(String[] args) {
        zipper zip = new zipper();
        //zigPath that stores created zip file
        String zipPath = "/Users/azuga/Desktop/loki.zip";

        //path of files to zip
        String path = "/Users/azuga/Desktop/zip";
        File[] files = zip.getFiles(path);
        zip.makeZip(zipPath,files);

        //Mail class is invoked to send the mail
        Mail mail = new Mail();
        mail.sendMail();

        //path to store the openzip files
        String openZip = "/Users/azuga/Desktop/OpenZip";
        unzipper unzip = new unzipper();
        unzip.unzip(zipPath,openZip,"");
    }
}
