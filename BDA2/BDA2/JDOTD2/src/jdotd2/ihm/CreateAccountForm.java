package jdotd2.ihm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import java.awt.GridBagLayout;
import java.awt.Dialog;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;

import jdotd2.business.AccountPersistence;
import jdotd2.business.AccountTypePersistence;
import jdotd2.business.CustomerPersistence;
import jdotd2.dao.AccountType;
import jdotd2.dao.Client;

public class CreateAccountForm extends JDialog{

	private JPanel contentPane;
	private JTextField txtAccNumber;
	private JTextField txtInitAmt;
	
	private long clientId;
	private int accTypeId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccountForm frame = new CreateAccountForm();
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
	public CreateAccountForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		setTitle("Create Account");
		setBounds(50, 50, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAccountNumber = new JLabel("Account Number:");
		GridBagConstraints gbc_lblAccountNumber = new GridBagConstraints();
		gbc_lblAccountNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccountNumber.anchor = GridBagConstraints.EAST;
		gbc_lblAccountNumber.gridx = 1;
		gbc_lblAccountNumber.gridy = 1;
		contentPane.add(lblAccountNumber, gbc_lblAccountNumber);
		
		txtAccNumber = new JTextField();
		GridBagConstraints gbc_txtAccNumber = new GridBagConstraints();
		gbc_txtAccNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtAccNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAccNumber.gridx = 2;
		gbc_txtAccNumber.gridy = 1;
		contentPane.add(txtAccNumber, gbc_txtAccNumber);
		txtAccNumber.setColumns(10);
		
		JLabel lblAccountType = new JLabel("Account Type:");
		GridBagConstraints gbc_lblAccountType = new GridBagConstraints();
		gbc_lblAccountType.anchor = GridBagConstraints.EAST;
		gbc_lblAccountType.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccountType.gridx = 1;
		gbc_lblAccountType.gridy = 2;
		contentPane.add(lblAccountType, gbc_lblAccountType);
		
		
		List<AccountType> accTypes = AccountTypePersistence.getAllAccountTypes();
		Vector model = new Vector();
		for(AccountType type:accTypes) {
			model.addElement(new AccountTypeListItem(type.getAccountTypeId(),type.getAccountType()));
		}
		JComboBox cboAccType = new JComboBox(model);
		cboAccType.setRenderer( new AccTypeItemRenderer());
		AccountTypeListItem item = (AccountTypeListItem)cboAccType.getSelectedItem();
		accTypeId = item.getId();
		
		cboAccType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox)e.getSource();
		        AccountTypeListItem item = (AccountTypeListItem)comboBox.getSelectedItem();
		        accTypeId = item.getId();
			}
		});
		
		GridBagConstraints gbc_cboAccType = new GridBagConstraints();
		gbc_cboAccType.insets = new Insets(0, 0, 5, 5);
		gbc_cboAccType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboAccType.gridx = 2;
		gbc_cboAccType.gridy = 2;
		contentPane.add(cboAccType, gbc_cboAccType);
		
		JLabel lblClient = new JLabel("Client :");
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.anchor = GridBagConstraints.EAST;
		gbc_lblClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblClient.gridx = 1;
		gbc_lblClient.gridy = 3;
		contentPane.add(lblClient, gbc_lblClient);
		
		
		List<Client> clients = CustomerPersistence.getAllCustomers();
		Vector clientModel = new Vector();
		for(Client client:clients) {
			clientModel.addElement(new ClientListItem(client.getClientId(),client.getClientName()));
		}
		JComboBox cboClient = new JComboBox(clientModel);
		cboClient.setRenderer( new ClientItemRenderer());
		ClientListItem client = (ClientListItem)cboClient.getSelectedItem();
		clientId = client.getId();
		
		cboClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox)e.getSource();
		        ClientListItem item = (ClientListItem)comboBox.getSelectedItem();
		        clientId =item.getId();
			}
			
		});
		
		GridBagConstraints gbc_cboClient = new GridBagConstraints();
		gbc_cboClient.insets = new Insets(0, 0, 5, 5);
		gbc_cboClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboClient.gridx = 2;
		gbc_cboClient.gridy = 3;
		contentPane.add(cboClient, gbc_cboClient);
		
		JLabel lblInitialAmount = new JLabel("Initial Amount:");
		GridBagConstraints gbc_lblInitialAmount = new GridBagConstraints();
		gbc_lblInitialAmount.anchor = GridBagConstraints.EAST;
		gbc_lblInitialAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblInitialAmount.gridx = 1;
		gbc_lblInitialAmount.gridy = 4;
		contentPane.add(lblInitialAmount, gbc_lblInitialAmount);
		
		txtInitAmt = new JTextField();
		GridBagConstraints gbc_txtInitAmt = new GridBagConstraints();
		gbc_txtInitAmt.insets = new Insets(0, 0, 5, 5);
		gbc_txtInitAmt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInitAmt.gridx = 2;
		gbc_txtInitAmt.gridy = 4;
		contentPane.add(txtInitAmt, gbc_txtInitAmt);
		txtInitAmt.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountPersistence.createAccount(txtAccNumber.getText(),clientId,accTypeId,Double.parseDouble(txtInitAmt.getText()));
				txtAccNumber.setText("");
				txtInitAmt.setText("");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 5;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}

	class AccTypeItemRenderer extends BasicComboBoxRenderer
    {
        public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null)
            {
                AccountTypeListItem item = (AccountTypeListItem) value;
                setText(item.getType().toUpperCase() );
            }

            return this;
        }
    }
	
	class ClientItemRenderer extends BasicComboBoxRenderer
    {
        public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

            if (value != null)
            {
                ClientListItem item = (ClientListItem) value;
                setText(item.getName().toUpperCase() );
            }

            return this;
        }
    }
	class AccountTypeListItem
    {
        private int id;
        private String type;

        public AccountTypeListItem(int id, String description)
        {
            this.id = id;
            this.type = description;
        }

        public int getId()
        {
            return id;
        }

        public String getType()
        {
            return type;
        }

        public String toString()
        {
            return type;
        }
    }
	class ClientListItem
    {
        private long id;
        private String name;

        public ClientListItem(long id, String name)
        {
            this.id = id;
            this.name = name;
        }

        public long getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public String toString()
        {
            return name;
        }
    }
	
	
}
