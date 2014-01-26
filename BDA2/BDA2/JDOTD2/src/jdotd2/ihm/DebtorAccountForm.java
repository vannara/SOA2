package jdotd2.ihm;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import jdotd2.business.AccountPersistence;
import jdotd2.dao.Account;

public class DebtorAccountForm extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DebtorAccountForm frame = new DebtorAccountForm();
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
	public DebtorAccountForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	
		setTitle("Debtor Accounts");
		setBounds(50, 50, 450, 450);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		// Add all debtor accounts to list
		final DefaultTableModel listModel = new DefaultTableModel();
		List<Account> accounts = AccountPersistence.getAllDebtorAccounts();
		listModel.addColumn("");
		listModel.addColumn("Acc Number");
		listModel.addColumn("Acc Type");
		listModel.addColumn("Customer Name");
		listModel.addColumn("Remaining Amount");
		for (Account acc : accounts) {
			listModel
					.addRow(new Object[] { acc.getAccountId(),
							acc.getAccountNumber(),
							acc.getAccountType().getAccountType(),
							acc.getClient().getClientName(),
							acc.getRemainingAmount() });

		}
		table = new JTable();
		table.setModel(listModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel()
		.removeColumn(table.getColumnModel().getColumn(0));

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, gbc_table);

	}

}
