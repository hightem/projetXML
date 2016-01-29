package com.ujm.xmltech.tasklet;

import iso.std.iso._20022.tech.xsd.pain_008_001.CustomerDirectDebitInitiationV02;
import iso.std.iso._20022.tech.xsd.pain_008_001.Document;
import iso.std.iso._20022.tech.xsd.pain_008_001.GroupHeader39;
import iso.std.iso._20022.tech.xsd.pain_008_001.ObjectFactory;
import iso.std.iso._20022.tech.xsd.pain_008_001.PaymentInstructionInformation4;

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
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.ujm.xmltech.entity.fileHeader;
import com.ujm.xmltech.services.TransactionService;
import com.ujm.xmltech.utils.BankSimulationConstants;

public class Pain008Reader implements Tasklet {
	
	@Autowired
	private TransactionService service;

  @Override
  public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
    Object obj = read((String) arg1.getStepContext().getJobParameters().get("inputFile"));
    fileHeader fh = new fileHeader();
	CustomerDirectDebitInitiationV02 cd = (CustomerDirectDebitInitiationV02) obj;
	System.out.println(cd.getGrpHdr().getMsgId());
	fh.setMsgId(cd.getGrpHdr().getMsgId());
	fh.setCtrlSum(cd.getGrpHdr().getCtrlSum().intValue());
	fh.setNbOfTxs(cd.getGrpHdr().getNbOfTxs());
	fh.setNomCreancier(cd.getGrpHdr().getInitgPty().getNm());
	service.createTransaction(fh);
	System.out.println("transaction created");
    return RepeatStatus.FINISHED;
  }

  @SuppressWarnings("rawtypes")
  public Object read(String fileName) throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    JAXBContext jc;
    try {
      jc = JAXBContext.newInstance(ObjectFactory.class);
      Unmarshaller u = jc.createUnmarshaller();
      File f = null;
      File f_work = new File(BankSimulationConstants.WORK_DIRECTORY + fileName);
      File f_archive = new File(BankSimulationConstants.ARCHIVE_DIRECTORY + fileName);
      if (f_work.exists()) {
		f = f_work;
	}
      if (f_archive.exists()) {
  		f = f_archive;
	}
      FileReader fileReader = new FileReader(f);
      JAXBElement element = (JAXBElement) u.unmarshal(fileReader);
      Document document = (Document) element.getValue();
      GroupHeader39 header = document.getCstmrDrctDbtInitn().getGrpHdr();
      System.out.println(header.getMsgId());
      Iterator<PaymentInstructionInformation4> it = document.getCstmrDrctDbtInitn().getPmtInf().iterator();
      while (it.hasNext()) {
        PaymentInstructionInformation4 transaction = it.next();
        System.out.println(transaction.getPmtInfId());
      }
      return document.getCstmrDrctDbtInitn();
    } catch (JAXBException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return RepeatStatus.FINISHED;
  }

}
