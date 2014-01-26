package jdotd2.ihm;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jdotd2.business.AccountPersistence;
import jdotd2.business.AccountTransactionPersistence;
import jdotd2.dao.Account;
import jdotd2.dao.AccountTransaction;

import javax.swing.JComboBox;

public class WithdrawForm extends JDialog {

	private JPanel contentPane;
	private JTextField txtWithdrawAmt;
	private JTextField txtComment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawForm frame = new WithdrawForm();
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
	public WithdrawForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(50, 50, 450, 450);
		setTitle("Withdraw Money");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAccNumber = new JLabel("Acc Number:");
		GridBagConstraints gbc_lblAccNumber = new GridBagConstraints();
		gbc_lblAccNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccNumber.anchor = GridBagConstraints.EAST;
		gbc_lblAccNumber.gridx = 1;
		gbc_lblAccNumber.gridy = 1;
		contentPane.add(lblAccNumber, gbc_lblAccNumber);
		
		List<Account> accounts = AccountPersistence.getAllAccounts();
		String[] accNumbers = new String[accounts.size()] ; 
		int i=0;
		for(Account acc:accounts) {
			accNumbers[i]= acc.getAccountNumber();
			i = i +1;
		}
		final JComboBox cboAccNumber = new JComboBox(accNumbers);
		cboAccNumber.setEditable(true);
		GridBagConstraints gbc_cboAccNumber = new GridBagConstraints();
		gbc_cboAccNumber.insets = new Insets(0, 0, 5, 0);
		gbc_cboAccNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboAccNumber.gridx = 2;
		gbc_cboAccNumber.gridy = 1;
		contentPane.add(cboAccNumber, gbc_cboAccNumber);
		
		JLabel lblWithdrawAmount = new JLabel("Withdraw Amount:");
		GridBagConstraints gbc_lblWithdrawAmount = new GridBagConstraints();
		gbc_lblWithdrawAmount.anchor = GridBagConstraints.EAST;
		gbc_lblWithdrawAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblWithdrawAmount.gridx = 1;
		gbc_lblWithdrawAmount.gridy = 2;
		contentPane.add(lblWithdrawAmount, gbc_lblWithdrawAmount);
		
		txtWithdrawAmt = new JTextField();
		GridBagConstraints gbc_txtWithdrawAmt = new GridBagConstraints();
		gbc_txtWithdrawAmt.insets = new Insets(0, 0, 5, 0);
		gbc_txtWithdrawAmt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWithdrawAmt.gridx = 2;
		gbc_txtWithdrawAmt.gridy = 2;
		contentPane.add(txtWithdrawAmt, gbc_txtWithdrawAmt);
		txtWithdrawAmt.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 4;
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calendar cal = Calendar.getInstance();
				int cal_for_month = cal.get(Calendar.MONTH);
				int cal_for_year = cal.get(Calendar.YEAR);
				int cal_for_day = cal.get(Calendar.DAY_OF_MONTH);
				Date date = (Date) cal.getTime();
				Date transactionDate = new java.util.Date(date.getYear(),
						cal_for_month, cal_for_day);
				String accNumber = (String)cboAccNumber.getSelectedItem();
				AccountTransactionPersistence.insertCreditTransaction(accNumber,
						transactionDate,txtComment.getText(), Double.parseDouble(txtWithdrawAmt.getText()));
				
			}
		});
		
		JLabel lblComment = new JLabel("Comment: ");
		GridBagConstraints gbc_lblComment = new GridBagConstraints();
		gbc_lblComment.anchor = GridBagConstraints.EAST;
		gbc_lblComment.insets = new Insets(0, 0, 5, 5);
		gbc_lblComment.gridx = 1;
		gbc_lblComment.gridy = 3;
		contentPane.add(lblComment, gbc_lblComment);
		
		txtComment = new JTextField();
		GridBagConstraints gbc_txtComment = new GridBagConstraints();
		gbc_txtComment.insets = new Insets(0, 0, 5, 0);
		gbc_txtComment.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtComment.gridx = 2;
		gbc_txtComment.gridy = 3;
		contentPane.add(txtComment, gbc_txtComment);
		txtComment.setColumns(10);
		contentPane.add(btnSave, gbc_btnSave);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.gridx = 2;
		gbc_btnClose.gridy = 4;
		contentPane.add(btnClose, gbc_btnClose);
	}

}
