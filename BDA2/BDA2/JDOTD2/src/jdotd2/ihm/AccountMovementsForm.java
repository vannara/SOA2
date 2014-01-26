package jdotd2.ihm;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import jdotd2.business.AccountPersistence;
import jdotd2.business.AccountTransactionPersistence;
import jdotd2.dao.Account;
import jdotd2.dao.AccountTransaction;

public class AccountMovementsForm extends JDialog implements ActionListener {
	private JTable table;
	//JScrollPane scrollPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountMovementsForm frame = new AccountMovementsForm();
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
	public AccountMovementsForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Account movements");
		setBounds(50, 50, 450, 450);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		List<Account> accounts = AccountPersistence.getAllAccounts();
		String[] accNumbers = new String[accounts.size()] ; 
		int i=0;
		for(Account acc:accounts) {
			accNumbers[i]= acc.getAccountNumber();
			i = i +1;
		}
		final JComboBox cboAccNumber = new JComboBox(accNumbers);
		cboAccNumber.setEditable(true);
		cboAccNumber.addActionListener(this);
		
		GridBagConstraints gbc_cboAccNumber = new GridBagConstraints();
		gbc_cboAccNumber.insets = new Insets(0, 0, 5, 0);
		gbc_cboAccNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboAccNumber.gridx = 0;
		gbc_cboAccNumber.gridy = 0;
		getContentPane().add(cboAccNumber, gbc_cboAccNumber);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, gbc_table);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/***
		 *  generate transaction history based on selected account number
		 */
		JComboBox cbo= (JComboBox)e.getSource();
		String accNumber = (String)cbo.getSelectedItem();
		final DefaultTableModel listModel = new DefaultTableModel();
		List<AccountTransaction> accountTrans = AccountTransactionPersistence.getAllTransactions(accNumber);
//		Date date1 = new java.util.Date(2012,1, 1);
//		Date date2 = new java.util.Date(2014,12, 30);
		Date date1=new Date(113,0,12);
		Date date2=new Date();
		
		//List<AccountTransaction> accountTrans = AccountTransactionPersistence.getTransactionsBetweenDates(accNumber,date1,date2);
		listModel.addColumn("Acc Type");
		listModel.addColumn("Customer Name");
		listModel.addColumn("Transaction Date");
		listModel.addColumn("Comment");
		listModel.addColumn("Debit");
		listModel.addColumn("Credit");
		listModel.addColumn("Remain Amount");
		
		for(AccountTransaction acc: accountTrans) {
			listModel.addRow(new Object[] { acc.getAccount().getAccountType().getAccountType(),
					acc.getAccount().getClient().getClientName(), 
					acc.getTransactionDate(),
					acc.getSourceOfTransaction(),
					acc.getDebit(),
					acc.getCredit(),
					acc.getAmountAfterTransaction()
					});
		}
	
		table.setModel(listModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        validate();
		repaint();
		
	}

}
