package com.ujm.xmltech.dao;

import com.ujm.xmltech.entity.Transaction;
import com.ujm.xmltech.entity.fileHeader;

public interface TransactionDao {

  void createTransaction(Transaction transaction);
  void createFileHeader(fileHeader fh);

  Transaction findTransactionById(long id);

}
