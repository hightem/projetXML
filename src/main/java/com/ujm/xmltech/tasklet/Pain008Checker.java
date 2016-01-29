package com.ujm.xmltech.tasklet;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.xml.sax.SAXException;

import com.ujm.xmltech.utils.BankSimulationConstants;
import com.ujm.xmltech.utils.FileManagementUtils;

public class Pain008Checker implements Tasklet {

    public boolean checkFile(String fileName) {
        try {
            File fileToValidate = new File(BankSimulationConstants.WORK_DIRECTORY + fileName);
            File xsdFile = new File("src/main/resources/xsd/pain.008.001.02.xsd");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileToValidate));
            System.out.println(fileToValidate.getName());
            System.out.println("the file is valid, yohoo !!");
        } catch (Exception e) {
            System.out.println("the file is NOT valid ! move to the rejected dir -> SAXException");
            //oh JAVA !!
          	System.gc();
          	//moving the rejected file to the /rejected directory
            File filerejected = new File(BankSimulationConstants.WORK_DIRECTORY + fileName);
          	filerejected.renameTo(new File(BankSimulationConstants.REJECTED_DIRECTORY + fileName));
            return false;
        }
        return true;
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
    	String fileName = (String) arg1.getStepContext().getJobParameters().get("inputFile");
    	boolean fileStatus = checkFile(fileName);
      	System.gc();
      	//we check if the file has been previously integrated, if so we delete it
      	if (fileStatus) {
            File fileToCheck = new File(BankSimulationConstants.ARCHIVE_DIRECTORY + fileName);
            if (fileToCheck.exists()) {
            	new File(BankSimulationConstants.WORK_DIRECTORY + fileName).delete();
            	System.out.println("le fichier " + fileName + " a été déjà intégré, donc on va le supprimer, adios!");
			}
		}
        return RepeatStatus.FINISHED;
    }

}
