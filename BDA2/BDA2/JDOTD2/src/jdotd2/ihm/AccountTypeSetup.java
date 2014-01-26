package jdotd2.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;

import java.awt.GridLayout;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jdotd2.business.AccountTypePersistence;

public class AccountTypeSetup extends JDialog {
	private JTextField txtAccType;
	private JTextField txtFirstBankCharge;
	private JTextField txtSecondBankCharge;
	private JTextField txtInterest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountTypeSetup frame = new AccountTypeSetup();
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
	public AccountTypeSetup() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(50, 50, 450,450);
		setTitle("Setup account type");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{131, 55, 35, 9, 85, 0};
		gridBagLayout.rowHeights = new int[]{30, 28, 28, 28, 28, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblAccountType = new JLabel("Account Type:");
		GridBagConstraints gbc_lblAccountType = new GridBagConstraints();
		gbc_lblAccountType.anchor = GridBagConstraints.EAST;
		gbc_lblAccountType.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccountType.gridx = 0;
		gbc_lblAccountType.gridy = 1;
		getContentPane().add(lblAccountType, gbc_lblAccountType);
		
		txtAccType = new JTextField();
		GridBagConstraints gbc_txtAccType = new GridBagConstraints();
		gbc_txtAccType.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAccType.insets = new Insets(0, 0, 5, 0);
		gbc_txtAccType.gridwidth = 4;
		gbc_txtAccType.gridx = 1;
		gbc_txtAccType.gridy = 1;
		getContentPane().add(txtAccType, gbc_txtAccType);
		txtAccType.setColumns(10);
		
		JLabel lblBankCharge = new JLabel("First Bank Charge:");
		GridBagConstraints gbc_lblBankCharge = new GridBagConstraints();
		gbc_lblBankCharge.anchor = GridBagConstraints.EAST;
		gbc_lblBankCharge.insets = new Insets(0, 0, 5, 5);
		gbc_lblBankCharge.gridx = 0;
		gbc_lblBankCharge.gridy = 2;
		getContentPane().add(lblBankCharge, gbc_lblBankCharge);
		
		txtFirstBankCharge = new JTextField();
		txtFirstBankCharge.setText("0.0");
		GridBagConstraints gbc_txtFirstBankCharge = new GridBagConstraints();
		gbc_txtFirstBankCharge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstBankCharge.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstBankCharge.gridx = 1;
		gbc_txtFirstBankCharge.gridy = 2;
		getContentPane().add(txtFirstBankCharge, gbc_txtFirstBankCharge);
		txtFirstBankCharge.setColumns(10);
		
		JLabel label = new JLabel("%");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 2;
		getContentPane().add(label, gbc_label);
		
		JLabel lblPerYear = new JLabel("per year");
		GridBagConstraints gbc_lblPerYear = new GridBagConstraints();
		gbc_lblPerYear.insets = new Insets(0, 0, 5, 0);
		gbc_lblPerYear.gridx = 4;
		gbc_lblPerYear.gridy = 2;
		getContentPane().add(lblPerYear, gbc_lblPerYear);
		
		JLabel lblSecondBankCharge = new JLabel("Second Bank Charge:");
		GridBagConstraints gbc_lblSecondBankCharge = new GridBagConstraints();
		gbc_lblSecondBankCharge.anchor = GridBagConstraints.EAST;
		gbc_lblSecondBankCharge.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecondBankCharge.gridx = 0;
		gbc_lblSecondBankCharge.gridy = 3;
		getContentPane().add(lblSecondBankCharge, gbc_lblSecondBankCharge);
		
		txtSecondBankCharge = new JTextField();
		txtSecondBankCharge.setText("0.0");
		GridBagConstraints gbc_txtSecondBankCharge = new GridBagConstraints();
		gbc_txtSecondBankCharge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSecondBankCharge.insets = new Insets(0, 0, 5, 5);
		gbc_txtSecondBankCharge.gridx = 1;
		gbc_txtSecondBankCharge.gridy = 3;
		getContentPane().add(txtSecondBankCharge, gbc_txtSecondBankCharge);
		txtSecondBankCharge.setColumns(10);
		
		JLabel label_1 = new JLabel("%");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 3;
		getContentPane().add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("per year");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 4;
		gbc_label_2.gridy = 3;
		getContentPane().add(label_2, gbc_label_2);
		
		JLabel lblInterest = new JLabel("Interest:");
		GridBagConstraints gbc_lblInterest = new GridBagConstraints();
		gbc_lblInterest.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterest.gridx = 0;
		gbc_lblInterest.gridy = 4;
		getContentPane().add(lblInterest, gbc_lblInterest);
		
		txtInterest = new JTextField();
		txtInterest.setText("0.0");
		GridBagConstraints gbc_txtInterest = new GridBagConstraints();
		gbc_txtInterest.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterest.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterest.gridx = 1;
		gbc_txtInterest.gridy = 4;
		getContentPane().add(txtInterest, gbc_txtInterest);
		txtInterest.setColumns(10);
		
		JLabel labelInterestPer = new JLabel("%");
		GridBagConstraints gbc_labelInterestPer = new GridBagConstraints();
		gbc_labelInterestPer.insets = new Insets(0, 0, 5, 5);
		gbc_labelInterestPer.gridx = 3;
		gbc_labelInterestPer.gridy = 4;
		getContentPane().add(labelInterestPer, gbc_labelInterestPer);
		
		JLabel label_3 = new JLabel("per year");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 4;
		getContentPane().add(label_3, gbc_label_3);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountTypePersistence.insertAccountType(
						txtAccType.getText(),
						Double.parseDouble(txtFirstBankCharge.getText()),
						Double.parseDouble(txtSecondBankCharge.getText()),
						Double.parseDouble(txtInterest.getText()));
			
				txtAccType.setText("");
				txtFirstBankCharge.setText("0.0");
				txtSecondBankCharge.setText("0.0");
				txtInterest.setText("0.0");
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 6;
		getContentPane().add(btnSave, gbc_btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 6;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		
	}

}
