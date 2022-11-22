package com.azuga.training;
/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class PipeTest is used as test class for the Pipe-command class
 * and developed positive testing and negative testing for different methods init
 */

class PipeTest {
    public static Logger logger = Logger.getLogger(PipeTest.class);
    Pipe object = new Pipe();

    /**
     * This method test cat command.
     */
    @Test
    void testCat() {
        logger.info("cat test invoked");
        try {
            String expected = Files.readString(Path.of("/Users/azuga/Desktop/robots.txt"));
            String actual = object.pipe("cat /Users/azuga/Desktop/robots.txt");

            assertEquals(actual, expected);
            logger.info("cat test case passed");
        } catch (IOException e) {
            logger.error("IOException - " + e);
        }
    }

    /**
     * This method test cat and sorting command.
     */
    @Test
    void testCatSort() {
        logger.info("cat and sort test invoked");
        String expected = "Bhim\nBhindu\nBindu\nGagan\nGanesh\nGanesh\nJaya\nKartik\nKartik\nRajesh\nRakesh\nRamesh\nShankar\nZoya";
        String actual = object.pipe("cat /Users/azuga/Desktop/sortlist.txt | sort");
        assertEquals(actual, expected);
        logger.info("cat and sort test case passed");
    }

    /**
     * This method test cat, sorting and unique command.
     */
    @Test
    void testCatSortUniq() {
        logger.info("cat, sort and unique command test invoked");
        String expected = "Bhim\nBhindu\nBindu\nGagan\nGanesh\nJaya\nKartik\nRajesh\nRakesh\nRamesh\nShankar\nZoya";
        String actual = object.pipe("cat /Users/azuga/Desktop/sortlist.txt | sort | uniq");
        assertEquals(actual, expected);
        logger.info("cat, sort and unique command test case passed");
    }

    /**
     * This method test cat and wc command.
     */
    @Test
    void testCatWc() {
        logger.info("cat and wc test case invoked");
        String expected = "10\t151\t960";
        String actual = object.pipe("cat /Users/azuga/Desktop/robots.txt | wc");

        assertEquals(actual, expected);
        logger.info("cat and wc test case passed");
    }

    /**
     * This method test cat and head command.
     */
    @Test
    void testCatHead() {
        logger.info("cat and head test case invoked");
        String expected = "Ganesh\nRakesh\nBindu\nBhim";
        String actual = object.pipe("cat /Users/azuga/Desktop/sortlist.txt | head -4");

        assertEquals(expected, actual);
        logger.info("cat and head test case passed");
    }

    /**
     * This method test cat and tail command.
     */
    @Test
    void testCatTail() {
        logger.info("cat and tail test case invoked");
        String expected = "Ganesh\nRajesh\nGagan\nZoya";
        String actual = object.pipe("cat /Users/azuga/Desktop/sortlist.txt | tail -4");

        assertEquals(expected, actual);
        logger.info("cat and tail test case passed");
    }

    /**
     * This method test cat, head and tail command.
     */
    @Test
    void testCatHeadTail() {
        logger.info("cat, head and tail case invoked");
        String expected = "Bindu\nBhim\nJaya";
        String actual = object.pipe("cat /Users/azuga/Desktop/sortlist.txt | head -5 | tail -3");

        assertEquals(expected, actual);
        logger.info("cat, head and tail case passed");
    }

    /**
     * This method test passing null parameter.
     */
    @Test
    void testNullPath() {
        logger.info("null path test case invoked");
        Pipe p = new Pipe();
        String actual = p.pipe(null);
        assertNull(actual);
        logger.info("null path test case passed");
    }

    /**
     * This method test invalid command input parameter.
     */
    @Test
    void testNoCommandExists() {
        logger.info("invalid command test case invoked");
        Pipe p = new Pipe();
        String actual = p.pipe("kartik w");
        assertEquals("No such command exists", actual);
        logger.info("invalid command test case passed");
    }

    /**
     * This method test file not found exception.
     */
    @Test
    void testFileNotFound() {
        logger.info("file check test case invoked");
        Pipe p = new Pipe();
        String actual = p.pipe("cat dummy.txt");
        assertEquals("File does not exist", actual);
        logger.info("file check test case passed");
    }

    /*
    public ArrayList<String> input = new ArrayList<>();
    public ArrayList<String> output = new ArrayList<>();

*/
    /**
     * catOutputTest is a test method is checking for the output from the file
     *//*
    @Test
    void catOutputTest() {
        Pipe obj = new Pipe();
        ArrayList<String> obtained = new ArrayList<>(obj.cat("/Users/azuga/Desktop/test.txt"));
        String result1 = obtained.toString();
        System.out.println(obtained);
        assertEquals("hi, hello i am lokanath," +
                " hehehhe," +
                " from hindupur which is 100kms from banglore ,," +
                " sorrry ," +
                " i had completed my btech in svce ," +
                " hello hi if," +
                " no one is ," +
                " college of engineering tirupati.," +
                " currently i am working as software engineer at azuga",result1.substring(1,result1.length()-1));
    }
   @Test
    void catIllegalInputTest() {
        Pipe obj = new Pipe();
       assertEquals(input,obj.cat("file"));
    }

    @Test
    void lsIllegalInputTest() {
        Pipe obj = new Pipe();
        assertEquals(input,obj.ls("azuga"));
    }

    @Test
    void wcIllegalInputTest() {
        Pipe obj = new Pipe();
       assertEquals(0,obj.wc(input));
    }

    @Test
    void headIllegalInputTest() {
        Pipe obj = new Pipe();
        assertTrue(obj.head(input,4).isEmpty(),"Empty input to head");
    }

    @Test
    void tailIllegalInputTest() {
        Pipe obj = new Pipe();
        assertFalse(obj.tail(input,7).size() > 0);
    }

    @Test
    void sortIllegalInputTest() {
        Pipe obj = new Pipe();
        assertTrue(obj.sort(input).isEmpty());
    }

    @Test
    void lsOutputTest() {
        Pipe obj = new Pipe();
        ArrayList<String> obtained = new ArrayList<>(obj.ls("/Users/azuga/Desktop/OpenZip"));
        String result1 = obtained.toString();
       assertEquals("Ghibli1.csv, converted2.html, ap12.pdf",result1.substring(1,result1.length()-1));
    }

    @Test
    void headOutputTest() {
        Pipe obj = new Pipe();
        ArrayList<String> cat = new ArrayList<>(obj.cat("/Users/azuga/Desktop/test.txt"));
        ArrayList<String> obtained = new ArrayList<>(obj.head(cat,6));
        String result = obtained.toString();
        assertEquals("hi, hello i am lokanath, hehehhe, from hindupur which is 100kms from banglore ,," +
                " sorrry ," +
                " i had completed my btech in svce ," +
                " hello hi if",result.substring(1,result.length()-1));
    }


    @Test
    void TailOutputTest() {
        Pipe obj = new Pipe();
        ArrayList<String> cat = new ArrayList<>(obj.cat("/Users/azuga/Desktop/test.txt"));
        ArrayList<String> obtained = new ArrayList<>(obj.tail(cat,6));
        String result = obtained.toString();
        assertEquals("sorrry ," +
                " i had completed my btech in svce ," +
                " hello hi if, " +
                "no one is , " +
                "college of engineering tirupati., " +
                "currently i am working as software engineer at azuga",result.substring(1,result.length()-1));
    }

    @Test
    void grepOutputTest() {
        Pipe obj = new Pipe();
        ArrayList<String> cat = new ArrayList<>(obj.cat("/Users/azuga/Desktop/test.txt"));
        ArrayList<String> obtained = new ArrayList<>(obj.grep(cat,"hello"));
        String result = obtained.toString();
        assertEquals("hi, hello i am lokanath, " + "hello hi if",result.substring(1,result.length()-1));
    }

    @Test
    void sortOutputTest(){
        Pipe obj = new Pipe();
        ArrayList<String> cat = new ArrayList<>(obj.cat("/Users/azuga/Desktop/test.txt"));
        ArrayList<String> obtained = new ArrayList<>(obj.sort(cat));
        String result = obtained.toString();assertEquals("college of engineering tirupati.," + " currently i am working as software engineer at azuga," +
                " from hindupur which is 100kms from banglore ,," + " hehehhe," + " hello hi if," + " hi, hello i am lokanath," +
                " i had completed my btech in svce ," + " no one is ," +
                " sorrry ",result.substring(1,result.length()-1));
    }
    @Test
    void pipeCommandTestOne() {
        Pipe obj = new Pipe();
        ArrayList<String> ret = obj.pipeCommand("cat /Users/azuga/Desktop/test.txt | head 6 | sort");
        String result = ret.toString();
        assertEquals("from hindupur which is 100kms from banglore ,, hehehhe, hello hi if, hi, hello i am lokanath," +
                " i had completed my btech in svce , " +
                "sorrry ",result.substring(1,result.length()-1));
    }
*/
}