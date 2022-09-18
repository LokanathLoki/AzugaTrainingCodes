/**
 * copyright (c) 2022.   -  All Rights Reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited-
 * @Author - LokanathK
 * @version - java 11
 */

package com.azuga.training;

import java.util.*;
import java.io.*;

/**
 * class ls is created to mimic the functionality of the List command in linux
 * with all its options
 * ls -l
 * ls -a
 * ls -al
 * ls -1
 * ls -t
 */
class ls {
	public static void main(String[] args)
	{
		File file = new File(System.getProperty("user.dir")); // getting the directory path from SystemProperty
		File[] folder = file.listFiles();
		assert folder != null;
		Arrays.sort(folder);            // sorting the obtained list of files
		String ch = args[0];
		switch(ch)                  //using switch case to get the result for the given commandLineArgument at args[0]
			{
			case "ls":
				for (File value : folder) {                                                        // ls to print list of files from current directory
					System.out.print(value.getName() + " \t");
				}
			break;
			case "-a":
				for (File value : folder) {
					if (value.isHidden())                  // checking for hidden files to print them
					{
						System.out.print(value.getName() + " \t");
					}
				}
			break;
			case "-l":
				for (File value : folder) {
					if (value.isHidden()) {
						continue;                       // avoiding the hidden files to print, since ls -l doesn't give hidden files
					} else {
						if (value.isFile()) {
							System.out.print("-");
						}                                    // checking if it is a file or diectory
						if (value.isDirectory()) {
							System.out.print("d");
						}
						System.out.print("\t" + System.getProperty("user.name"));            // getting username and ownername  from the systemProperty
						System.out.print("\t" + System.getProperty("owner.name") + "\t");
						System.out.print(value.length());                                // printing the length of the file
						System.out.print(("\t" + new Date(value.lastModified())) + "\t");     // printing the files with their lastModefied date
						System.out.println(value.getName());
					}

				}
			break;
			case "-t":
				for (File value : folder) {
					System.out.println(new Date(value.lastModified()) + "\t\t" + value.getName());
				}
			break;
			case "-1":
			for(int i=0;i<folder.length;i++)
			{												// printing the list of files in line by line
				System.out.println(folder[i].getName());
			}
			break;
			case "-al":
				for(int i=0;i<folder.length;i++)
				{
					if(folder[i].isHidden())
					{									// printing list of files including with hidden files and thier properties
						if(folder[i].isFile())
						{
							System.out.print("-");
						}
						if(folder[i].isDirectory())
						{
							System.out.print("d");
						}
						System.out.print("\t"+System.getProperty("user.name"));
						System.out.print("\t"+System.getProperty("owner.name")+"\t");
						System.out.print(folder[i].length() );
						System.out.print(("\t"+new Date(folder[i].lastModified())) + "\t");
						System.out.println(folder[i].getName());
					}
						if(folder[i].isFile())
						{
							System.out.print("-");
						}
						if(folder[i].isDirectory())
						{
							System.out.print("d");
						}
							System.out.print("\t"+System.getProperty("user.name"));
							System.out.print("\t"+System.getProperty("owner.name")+"\t");
							System.out.print(folder[i].length() );
							System.out.print(("\t"+new Date(folder[i].lastModified())) + "\t");
							System.out.println(folder[i].getName());
					}
			break;
			case "-r":
			for(int i=folder.length-1;i>=0;i--)
			{												// gives the list of files in line by line
				System.out.println(folder[i].getName()+" \t");
			}
			break;
			default:
			System.out.println("Choose Correct option");
			}
	}
}