package jdotd2.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class AccountType {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.NATIVE)
	private int accountTypeId;
	private String accountType;
	private Double bankCharge;
	private Double secondBankCharge;
	private Double interest;
	
	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	
	
	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public AccountType(){
		
	}
	
	public AccountType(String type){
		this.accountType=type;
	}

	/**
	 * @return the bankCharge
	 */
	public Double getBankCharge() {
		return bankCharge;
	}

	/**
	 * @param bankCharge the bankCharge to set
	 */
	public void setBankCharge(Double bankCharge) {
		this.bankCharge = bankCharge;
	}
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the secondBankCharge
	 */
	public Double getSecondBankCharge() {
		return secondBankCharge;
	}

	/**
	 * @param secondBankCharge the secondBankCharge to set
	 */
	public void setSecondBankCharge(Double secondBankCharge) {
		this.secondBankCharge = secondBankCharge;
	}
	
}
