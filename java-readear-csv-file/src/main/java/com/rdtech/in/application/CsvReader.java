package com.rdtech.in.application;


import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CsvReader {
	

	
	
	//public static String filename;
	public static String foldername;
	public static String rowsplitter;
	public static String columnsplitter;
	public static String filenames[];
	
	
	 public static List<String>  rows(String str){
		 
		 String[] splitted = Arrays.stream(str.split(rowsplitter)).map(String::trim).toArray(String[]::new);

		return Arrays.asList(splitted);
		 
	 }

	public static void column(List<String> list) {
		
		boolean skipHeader = true;
		
		for (String string : list) {
			String[] line = Arrays.stream(string.split(columnsplitter)).map(String::trim).toArray(String[]::new);

			// System.out.println(Arrays.toString(line));

			if (skipHeader) {
				skipHeader = false;
				continue;
			}

			String firstname = line[0];
			String lastname = line[1];
			String city = line[2];
			String salary = line[3];
			

			System.out.println("firstname="+firstname+", lastname="+lastname+", city="+city+", salary="+salary);
		}
		
		
	}
	

	public static void main(String[] args) throws IOException {
		
		  FileReader reader=new FileReader("configuration/parameter.properties"); 
		  
		  Properties p=new Properties();  
		  p.load(reader);  
		  	  
		  
		  foldername = p.getProperty("foldername").toString();
		  rowsplitter = p.getProperty("rowsplitter").toString();
		  columnsplitter = p.getProperty("columnsplitter").toString();
		  //filename = p.getProperty("filename").toString();
		  
		  String s = p.getProperty("filenames").toString();
	      String strArray[] = s.split(",");
	    	
		  filenames = strArray;
		  
		
		for(int i=0 ; i< filenames.length; i++)		
		{	
			
		//Path path = Paths.get(foldername+filename);
		Path path = Paths.get(foldername+filenames[i]);
		byte[] bytes = Files.readAllBytes(path);

		String str = new String(bytes);

		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("file name ="+filenames[i]);
        System.out.println("-----------------------------------------------");
		
		List<String> list = rows(str);
        column(list);
        
		}
	}

}
