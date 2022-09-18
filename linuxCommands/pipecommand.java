/**
 * copyright (c) 2022.   -  All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - LokanathK
 * @version - java 11
 */

package com.azuga.training;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/** pipeCommand Class is created to mimic the functions of pipe command
 * the supported commands are
 * cat
 * ls
 * wc
 * sort
 * head
 * tail
 */
public class pipecommand {
    public static ArrayList<String> lines = new ArrayList<>(); // array lists were created for processing input and outputs
    public static ArrayList<String> result = new ArrayList<>();
    public static ArrayList<String> head = new ArrayList<>();

    /**
     * Method of cat with a string input returns the content
     * of given file in an arrayList
     *
     * @param f   - String that contains the path of the file
     * @return    - it returns the ArrayList of file content
     */
    public static ArrayList<String> cat(String f) {
        try {
            File file = new File(f);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);                // every readerLine is adding to the ArrayList
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
    /**
     * Method 'ls' gives the List of files present in the given input directory and the
     * result is returned through an ArrayList
     *
     * @param s  - string s which has directory path is taken as input
     * @return   - ArrayList of the files in directory
     */
    public static ArrayList<String> ls(String s) {

        File file = new File(s);
        File[] folder;
        folder = file.listFiles();
        String element;
        assert folder != null;
        for (File value : folder) {
            element = value.getName();
            // checking for hidden files to
            if (!(value.isHidden())) {
                lines.add(element);
            }
        }
        return lines;
    }

    /**
     * Method wc takes the input of ArrayList to mimic the functionality of WC linux command
     * the result is directly printed in the console
     *
     * @param s -  ArrayList of the content is passed to the wc method
     */
        public static void wc(ArrayList<String> s)
        {
            int line = 0;
            int charC = 0;
            int word = 0;
            for (String ele : s) {
                line++;

                // the line is split into the array of words with " "
                String[] words = ele.split(" ");
                for (String value : words) {
                    word += value.length();

                    // every word is converted to charArray to get the char Count
                    char[] ch = value.toCharArray();
                    for (int k = 0; k < ch.length; k++) {
                        charC += ch.length;
                    }
                }

            }
              System.out.println(line +"\t"+word+"\t"+charC);
                }
    /**
     * Method head with inputs of arrayList and len process them to give the result of the
     * head command in linux and the result is returned through an ArrayList
     *
     * @param s     - ArrayList of the content is given to get its head
     * @param len   - number of head-lines required from the content
     * @return      -  ArrayList of the head-lines as per len
     */
    public static  ArrayList<String> head(ArrayList<String> s, int len) {
        for(int i=0;i<len;i++) {
            String element = s.get(i);
            head.add(element);
        }
        lines.clear();
       lines = head;
       return lines;
    }

    /**
     * Method grep takes inputs of arraylist and the word to search in the contenet of the list
     * to mimic the functionality of grep command in linux
     *
     * @param s     -  ArrayList of the content is given to get its grep
     * @param w     -  the string to search in the content
     * @return      -  ArrayList of the lines that contains required word
     */
    public static  ArrayList<String> grep(ArrayList<String> s, String w) {
        for (String element : s) {
            if(element.contains(w)) {
                head.add(element);                   //checking for the line that contains the required word
            }
        }
        lines.clear();
        lines = head;
        return lines;
    }
    /**
     * Method tail with inputs of arrayList and len process them to give the result of the
     * tail command in linux and the result is returned through an ArrayList
     *
     * @param s   - ArrayList of the content is given to get its tail
     * @param len - number of tail-lines required from the content
     * @return    - ArrayList of the tail-lines as per len
     */
    public static  ArrayList<String> tail(ArrayList<String> s, int len) {
        for(int i=(s.size() - len);i<s.size();i++) {      // for loop iterating from the sizeOf ArrayList - given length to arrayList length to get the tail part of the input
            String element = s.get(i);
            head.add(element);
        }
        lines.clear();
        lines = head;
        return lines;
    }

    /**
     * Sort method is created to sort the contents of the arrayList that is given as input
     * by using simple collection sort method
     * @param s  - ArrayList of the content is given to get its sorted list
     * @return   - ArrayList of the sorted content of input
     */
    public static  ArrayList<String> sort(ArrayList<String> s) {
        Collections.sort(s);
        return s;
    }
    public static void main(String[] args) {
        String[] str = args[0].split(Pattern.quote("|")); // input argument is split into string array with '|'
        for (String s : str) {
            if (s.contains("cat")) {
                String[] ch = s.split(" ");
                result = cat(ch[1]);              // the method's result is stored in the result arrayList
            } else if (s.contains("head")) {
                String[] he = s.split(" ");
                int len = Integer.parseInt(he[2]);    //String type is changed into int type using parseInt method
                result = head(result, len);
            } else if (s.contains("tail")) {
                String[] he = s.split(" ");
                int len = Integer.parseInt(he[2]);
                result = tail(result, len);
            } else if (s.contains("wc")) {
                wc(result);               // calling the wc method with result as argument, it gives its void output
                result.clear();
            } else if (s.contains("sort")) {
                result = sort(result);
            } else if (s.contains("ls")) {
                String[] li = s.split(" ");
                result = ls(li[1]);
            } else if (s.contains("grep")) {
                String[] g = s.split(" ");
                result = grep(result, g[2]);
            }
        }
        for (String element : result) {
            System.out.println(element);      // printing the content in the result arrayList
        }
    }
}
