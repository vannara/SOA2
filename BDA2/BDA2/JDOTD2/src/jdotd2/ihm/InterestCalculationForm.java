package jdotd2.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import jdotd2.business.AccountPersistence;
import jdotd2.business.AccountTransactionPersistence;
import jdotd2.dao.Account;
import jdotd2.dao.AccountTransaction;

public class InterestCalculationForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtBalance;
	private JTable table;
	
	private String accNumber;
	private Double balance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterestCalculationForm frame = new InterestCalculationForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterestCalculationForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Acc Number:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		List<Account> accounts = AccountPersistence.getAllAccounts();
		String[] accNumbers = new String[accounts.size()] ; 
		int i=0;
		for(Account acc:accounts) {
			accNumbers[i]= acc.getAccountNumber();
			i = i +1;
		}
		final JComboBox cboAccNumber = new JComboBox(accNumbers);
		cboAccNumber.setEditable(true);
		cboAccNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cbo =(JComboBox)e.getSource();
				accNumber = (String) cbo.getSelectedItem();
				balance = AccountPersistence.getAccountBalance(accNumber);
				txtBalance.setText(balance.toString());
			}
		});
		
		GridBagConstraints gbc_cboAccNumber = new GridBagConstraints();
		gbc_cboAccNumber.insets = new Insets(0, 0, 5, 0);
		gbc_cboAccNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboAccNumber.gridx = 1;
		gbc_cboAccNumber.gridy = 0;
		contentPane.add(cboAccNumber, gbc_cboAccNumber);
		
		JLabel lblNewLabel_1 = new JLabel("Balance:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtBalance = new JTextField();
		GridBagConstraints gbc_txtBalance = new GridBagConstraints();
		gbc_txtBalance.insets = new Insets(0, 0, 5, 0);
		gbc_txtBalance.anchor = GridBagConstraints.NORTH;
		gbc_txtBalance.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBalance.gridx = 1;
		gbc_txtBalance.gridy = 1;
		contentPane.add(txtBalance, gbc_txtBalance);
		txtBalance.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO:calculate bank interest
				Double debit = AccountPersistence.calculateInterest(accNumber);
				AccountTransactionPersistence.insertDebitTransaction(accNumber, new Date(), "Interest", debit);
			
				//clear old lists 
				((DefaultTableModel)table.getModel()).setRowCount(0); 
				    
				    List<AccountTransaction> accountTrans = AccountTransactionPersistence
							.getInterestTransaction(accNumber);
				 // display
					for (AccountTransaction acc : accountTrans) {
						((DefaultTableModel) table.getModel()).addRow(new Object[] {
								acc.getAccount().getAccountType().getAccountType(),
								acc.getAccount().getClient().getClientName(),
								acc.getTransactionDate(), acc.getSourceOfTransaction(), acc.getDebit(),
								acc.getAmountAfterTransaction() });
					}
			}
		});
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalculate.gridx = 1;
		gbc_btnCalculate.gridy = 2;
		contentPane.add(btnCalculate, gbc_btnCalculate);
		
		final DefaultTableModel listModel = new DefaultTableModel();
		String accNumber = (String) cboAccNumber.getSelectedItem();
		List<AccountTransaction> accountTrans = AccountTransactionPersistence
				.getInterestTransaction(accNumber);
		listModel.addColumn("Acc Type");
		listModel.addColumn("Customer Name");
		listModel.addColumn("Transaction Date");
		listModel.addColumn("Comment");
		listModel.addColumn("Amount");
		listModel.addColumn("Remain Amount");

		for (AccountTransaction acc : accountTrans) {
			listModel.addRow(new Object[] {
					acc.getAccount().getAccountType().getAccountType(),
					acc.getAccount().getClient().getClientName(),
					acc.getTransactionDate(), acc.getSourceOfTransaction(), acc.getDebit(),
					acc.getAmountAfterTransaction() });
		}
		table = new JTable();
		table.setModel(listModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 2;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 3;
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc_table);
	}

}
