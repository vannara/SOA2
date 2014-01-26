package jdotd2.ihm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import jdotd2.business.AccountPersistence;
import jdotd2.dao.Account;

public class DeleteAccountForm extends JDialog {

	private JPanel contentPane;
	private String accountNumber;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAccountForm frame = new DeleteAccountForm();
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
	public DeleteAccountForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Delete Account");
		setBounds(50, 50, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAccountNumber = new JLabel("Account Number :");
		GridBagConstraints gbc_lblAccountNumber = new GridBagConstraints();
		gbc_lblAccountNumber.anchor = GridBagConstraints.EAST;
		gbc_lblAccountNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccountNumber.gridx = 1;
		gbc_lblAccountNumber.gridy = 2;
		contentPane.add(lblAccountNumber, gbc_lblAccountNumber);
		
		java.util.List<Account> accounts = AccountPersistence.getAllAccounts();
		Vector<AccountListItem> model = new Vector<AccountListItem>();
		for(Account acc:accounts) {
			model.addElement(new AccountListItem(acc.getAccountId(),acc.getAccountNumber()));
		}
		final JComboBox cboAccNumber = new JComboBox(model);
		cboAccNumber.setRenderer( new AccountItemRenderer());
		AccountListItem item = (AccountListItem)cboAccNumber.getSelectedItem();
		accountNumber = item.getAccNumber();
		cboAccNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox)e.getSource();
				if(comboBox.getSelectedIndex()!=-1) {
					AccountListItem item = (AccountListItem)comboBox.getSelectedItem();
			        accountNumber = item.getAccNumber();
				}
				
			}
			
		});
		
		GridBagConstraints gbc_cboAccNumber = new GridBagConstraints();
		gbc_cboAccNumber.insets = new Insets(0, 0, 5, 0);
		gbc_cboAccNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboAccNumber.gridx = 2;
		gbc_cboAccNumber.gridy = 2;
		contentPane.add(cboAccNumber, gbc_cboAccNumber);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isDeleted= AccountPersistence.deleteAccount(accountNumber);
				if(isDeleted) {
					cboAccNumber.removeItemAt(cboAccNumber.getSelectedIndex());
					cboAccNumber.setSelectedIndex(-1);
					System.out.println("Account Number :" + accountNumber + " is deleted");
					validate();
					repaint();
				}
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 5;
		contentPane.add(btnDelete, gbc_btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.gridx = 2;
		gbc_btnClose.gridy = 5;
		contentPane.add(btnClose, gbc_btnClose);
	}
	
	class AccountItemRenderer extends BasicComboBoxRenderer
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null)
            {
            	AccountListItem item = (AccountListItem) value;
                setText(item.getAccNumber());
            }

            return this;
        }
    }
	
	class AccountListItem{
		  private long id;
	        private String accNumber;

	        public AccountListItem(long id, String number)
	        {
	            this.id = id;
	            this.accNumber = number;
	        }

	        public long getId()
	        {
	            return id;
	        }

	        public String getAccNumber()
	        {
	            return accNumber;
	        }

	        public String toString()
	        {
	            return accNumber;
	        }
	}

}
