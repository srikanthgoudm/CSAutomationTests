package com.test.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	public Properties propertyFileReader()  {

		//Loan Property class from Java.util
		Properties system = new Properties();

		//Get the location and name of property file
		File file = new File("");
		String filePath = file.getAbsolutePath();
		filePath = filePath + "/src/test/resources/";
		String fileName="";


		fileName = "Test.properties";

		FileInputStream propertyFile;
		try {
			propertyFile = new FileInputStream(filePath+fileName);
			system.load(propertyFile);
		} catch (IOException e) {
			e.printStackTrace();
		}



		return system;

	}

}
