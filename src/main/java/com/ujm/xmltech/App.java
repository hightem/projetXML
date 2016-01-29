package com.ujm.xmltech;

import java.io.File;
import java.util.Calendar;

import javax.xml.bind.JAXB;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ujm.xmltech.entity.fileHeader;
import com.ujm.xmltech.services.TransactionService;
import com.ujm.xmltech.tasklet.Pain008Checker;
import com.ujm.xmltech.tasklet.Pain008Processor;
import com.ujm.xmltech.tasklet.Pain008Reader;
import com.ujm.xmltech.tasklet.Pain008Writer;
import com.ujm.xmltech.utils.BankSimulationConstants;

public class App {
	


	public static void launch() {
		File input = retrieveFileToProcess();
		if (input != null) {
			String[] springConfig = { "spring/batch/jobs/jobs.xml" };
			ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("integratePain008File");
			try {
				JobExecution execution = jobLauncher.run(job,
						new JobParametersBuilder().addString("inputFile", input.getName()).toJobParameters());
				System.out.println("Exit Status : " + execution.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("[" + Calendar.getInstance().getTime().toString() + "] No file to process");
		}
	}

	private static File retrieveFileToProcess() {
		File toReturn = null;
		File folder = new File(BankSimulationConstants.IN_DIRECTORY);
		for (File file : folder.listFiles()) {
			System.out.println("File found : " + file.getName());
			toReturn = file;
		}
		return toReturn;
	}

	public static void main(String[] args) {
		File input = retrieveFileToProcess();
		Pain008Reader reader = new Pain008Reader();
		Pain008Writer writer = new Pain008Writer();
		Pain008Processor processor = new Pain008Processor();
		// Pain008Checker checker = new Pain008Checker();
		try {
			launch();
			// checker.checkFile(input.getName());
			Object item = reader.read(input.getName());
			Object processedItem = processor.process(item);
			writer.write(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
