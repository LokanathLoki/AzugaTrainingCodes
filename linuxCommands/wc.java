/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK
 */

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

class wc
{
	public static void main(String[] args)
	{	
		int charCount = 0;
		int totalWords = 0;
	
		try {
			File file = new File(args[1]);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int lines = 0;
			String line;
			while ((line = reader.readLine())!= null)
			{
			 lines++;
			String[] words = line.replaceAll("\\s+", " ").split(" ");
			totalWords += words.length;
			for(int i = 0;i<words.length;i++)
			{
				char[] word = words[i].toCharArray();
				charCount += word.length;
			}
			}
			reader.close();
			
			switch (args[0])
			{
			case "-c":
			System.out.println(file.length()+" "+args[1]);
			break;
		
			case "-l":
			System.out.println(lines+" "+args[1]);
			break;

			case "-w":
			System.out.println(totalWords+" "+args[1]);
			break;

			case "-m":
			System.out.println(charCount+" "+args[1]);
			break;
			}
			} catch (Exception e) {
				}
				     														
	}
}
