package com.amazon.AmazonUI.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.amazon.AmazonUI.Resultdata;
import com.amazon.AmazonUI.UIActions;
import com.relevantcodes.extentreports.LogStatus;

public class ValidateResults extends UIActions{
	public ValidateResults(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private Logger logger = Logger.getLogger(ValidateResults.class);

	public boolean isEqual(Resultdata actual, String expectedResultFile) throws JAXBException {
		
		File file = new File("src/main/resources/resource/" + expectedResultFile);
		JAXBContext jaxbContext = JAXBContext.newInstance(Resultdata.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Resultdata expectedData = (Resultdata) jaxbUnmarshaller.unmarshal(file);
		if(!expectedData.equals(actual)) {
			
			extentTest.log(LogStatus.FAIL, "Results did not match: Expected: \n"+ expectedData + "\n actual: \n"+actual  );
            logger.info("Results did not match: Expected: \n"+ expectedData + "\n actual: \n"+actual);
			return false;
		}
		
        extentTest.log(LogStatus.PASS, "Results Match");
        logger.info( "Results Match");
		return true;
	}
	
	public boolean saveResult(Resultdata expected, String fileNamexml) throws JAXBException, IOException {
		
		File file = new File("src/main/resources/resource/" + fileNamexml);
		file.createNewFile();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Resultdata.class);

		//Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        //Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         
        //Writes XML file to file-system
        jaxbMarshaller.marshal(expected, file);
        
        return true;
	}
}
