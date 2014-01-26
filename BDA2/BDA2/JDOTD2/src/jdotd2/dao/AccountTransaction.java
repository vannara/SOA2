package jdotd2.dao;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;

@PersistenceCapable
public class AccountTransaction {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
	private long transactionId;
	private Account account;
	private Double debit;
	private Double credit;
	private String sourceOfTransaction;
	private Date transactionDate;
	private Double amountAfterTransaction;
	
	public Double getAmountAfterTransaction() {
		return amountAfterTransaction;
	}

	public void setAmountAfterTransaction(Double amountAfterTransaction) {
		this.amountAfterTransaction = amountAfterTransaction;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getSourceOfTransaction() {
		return sourceOfTransaction;
	}

	public void setSourceOfTransaction(String sourceOfTransaction) {
		this.sourceOfTransaction = sourceOfTransaction;
	}

	public AccountTransaction(){
		
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
}
