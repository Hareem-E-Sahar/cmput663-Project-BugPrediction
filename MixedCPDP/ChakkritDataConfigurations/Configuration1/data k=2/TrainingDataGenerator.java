package configuration.first;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.opencsv.CSVWriter;

public class TrainingDataGenerator {
	static String folderPath = "C:\\Users\\HP\\Desktop\\AbdulAli\\663\\";
	File folder;
	CSVWriter writer;
	 FileWriter outputfile;

	public ArrayList fileList=new ArrayList<String>();
	
	
	public void listFilesForFolder(final File folder) {
	
	//	fileList.add("Dummy");
		for (final File fileEntry : folder.listFiles()) {
			/*if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
				
			} */ 
				
				fileList.add(folderPath+fileEntry.getName());		//save name of all files in directory to list
				//readLogFiles(folderPath + fileEntry.getName());
				//System.out.println("===================================");
			
		}
		System.out.println("number of files:"+fileList.size());	//return count of number of files in folder
		Collections.sort(fileList);
	}

	
	//CREATE CSV TO COPY DATA TO 
	
	void createFile(int number)
	{
	
		File file = new File("C:\\Users\\HP\\Desktop\\AbdulAli\\663\\File"+ number+"&"+(number+1)+".arff"); 
	    
	        // create FileWriter object with file as parameter 
	       
			try {
				outputfile = new FileWriter(file);
				writer = new CSVWriter(outputfile); 
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		//	System.out.println("FILE CREATED:"+file.toString());
	  
	        // create CSVWriter object filewriter object as parameter 
	        
	}
	
	//READ FILES
	void readFiles()
	{
		int size =32;
		int constant =2;
		//int file_counter=0;
		String name;
		String line=null;
		String cvsSplitBy=",";
		
	
		for (int i=1;i<fileList.size(); i++)
		{	
			System.out.println("creating file "+i+"&"+(i+1));
		    createFile(i); //(file_counter+1);
		    
		    String[] header={"PercentLackOfCohesion", "MaxInheritanceTree", "CountClassDerived", "CountClassCoupled", "CountClassBase", "HeuBug"};
			writer.writeNext(header);	//write header to the file once, as soon as it is created
		
			for (int j=i;j<=i+1; j++)
			{
			
				System.out.println("Open file:"+j +" and copy to result");
				name=(String) fileList.get(j-1);  //j-1 because list starts at 0 index
				System.out.println(name);
				 
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader(name));
					
					//read header and discard it i.e. do not write to file
					line = br.readLine();
					header = line.split(cvsSplitBy);
					//System.out.println(header[0]+ " "+header[1]+" "+header[2]+" "+header[3]+" "+header[4]+" "+header[5] );
					
					
					while ((line = br.readLine()) != null) {	//to read data from each file
						
					    // use comma as separator 
						//write line by line to file
					    String[] temp =line.split(cvsSplitBy);
					    writer.writeNext(temp);
    
					}
					br.close();
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
								
			}
			try {
				outputfile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
	
	
	void testMethod()
	{
		int size =32;
		//int constant =2;

		for (int i=1;i<size; i++)
		{
			System.out.println("creating file "+i+"to"+(i+1));
			for (int j=i;j<=i+1; j++)
			{
				//System.out.println("Value of i "+i+" value of j "+j);
				System.out.println("Open file:"+j +" and copy to result");
				
				
			}
		}	
		
		
	}

}
