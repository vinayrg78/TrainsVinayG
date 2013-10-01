package com.tworks.trains;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is a very simple File Helper to help the Train Client with all the file related activities.
 * It relies on the file being present at the same location as the jar itself.
 * 
 * @author VinayG
 */
public class TrainClientFileHelper {

	public static String[] processFileContents(String fileContents){
		String[] edgeData = fileContents.split(",");
		return edgeData;
	}
	
	public static String getFileContents(String fileName) throws FileNotFoundException, IOException{

		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder stringFromFile = new StringBuilder();
		String lineFromFile;
		
		while((lineFromFile = br.readLine()) != null) {
			stringFromFile.append(lineFromFile);
		}
		fr.close();
		
		return stringFromFile.toString();
	}
		
}
