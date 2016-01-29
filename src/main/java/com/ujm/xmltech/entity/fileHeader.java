package com.ujm.xmltech.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class fileHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  @Column(name="ID_FICHIER")
	  private String MsgId;
	  @Column(name="TX_NB")
	  private String NbOfTxs;
	  @Column(name="CTRL_SUM")
	  private int CtrlSum;
	  @Column(name="NOM_CREANCIER")
	  private String nomCreancier;
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getNbOfTxs() {
		return NbOfTxs;
	}
	public void setNbOfTxs(String nbOfTxs) {
		NbOfTxs = nbOfTxs;
	}
	public int getCtrlSum() {
		return CtrlSum;
	}
	public void setCtrlSum(int ctrlSum) {
		CtrlSum = ctrlSum;
	}
	public long getId() {
		return id;
	}
	public String getNomCreancier() {
		return nomCreancier;
	}
	public void setNomCreancier(String nomCreancier) {
		this.nomCreancier = nomCreancier;
	}
	  
	  
	  

}
