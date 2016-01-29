package com.ujm.xmltech.tasklet;

import org.springframework.batch.core.StepContribution;
import iso.std.iso._20022.tech.xsd.pain_008_001.*;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;

import com.ujm.xmltech.utils.BankSimulationConstants;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.ujm.xmltech.entity.Transaction;
import com.ujm.xmltech.entity.fileHeader;
import com.ujm.xmltech.services.TransactionService;

public class Pain008Processor implements Tasklet, ItemProcessor<Object, Object> {

	@Autowired
	private TransactionService service;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
//		service.createTransaction();
//		System.out.println("transaction created");
		return RepeatStatus.FINISHED;
	}

	@Override
	public Object process(Object obj) throws Exception {
//		//service.createTransaction();
//		// TODO Auto-generated method stub
//		Document doc = new Document();
//		doc = (Document) obj;
//	      Iterator<PaymentInstructionInformation4> it = doc.getCstmrDrctDbtInitn().getPmtInf().iterator();
//	      while (it.hasNext()) {
//	          PaymentInstructionInformation4 transaction = it.next();
//	        }
//		
		
		return obj;
	}

}
