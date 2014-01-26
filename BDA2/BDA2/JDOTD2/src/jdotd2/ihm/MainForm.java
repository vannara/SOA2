package jdotd2.ihm;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				   // frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 700, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSetup = new JMenu("File");
		menuBar.add(mnSetup);
		
		JMenu mnFile = new JMenu("Setup");
		mnSetup.add(mnFile);
		
		JMenuItem mntmAccountType_1 = new JMenuItem("Account Type");
		mntmAccountType_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountTypeSetup.main(null);
			}
		});
		mnFile.add(mntmAccountType_1);
		
		JMenuItem mntmClientType_1 = new JMenuItem("Client Type");
		mntmClientType_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientTypeSetup.main(null);
			}
		});
		mnFile.add(mntmClientType_1);
		
		JMenuItem mntmClient = new JMenuItem("Customer");
		mntmClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSetup.main(null);
			}
		});
		mnFile.add(mntmClient);
		
		JMenu mnDisplay = new JMenu("Display");
		mnSetup.add(mnDisplay);
		
		JMenuItem mntmAllAccountTypes = new JMenuItem("All Account Types");
		mntmAllAccountTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountTypeList.main(null);
			}
		});
		mnDisplay.add(mntmAllAccountTypes);
		
		JMenuItem mntmAllClientTypes = new JMenuItem("All Client Types");
		mntmAllClientTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO:
				//not implemented
			}
		});
		mnDisplay.add(mntmAllClientTypes);
		
		JMenuItem mntmAllCustomers = new JMenuItem("All Customers");
		mntmAllCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO:
				//not implemented
			}
		});
		mnDisplay.add(mntmAllCustomers);
		
		JMenuItem mntmSetup = new JMenuItem("Exit");
		mntmSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnSetup.add(mntmSetup);
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem mntmCreateAccount = new JMenuItem("Create Account");
		mntmCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccountForm.main(null);
			}
		});
		mnAccount.add(mntmCreateAccount);
		
		JMenuItem mntmDeleteAccount = new JMenuItem("Delete Account");
		mntmDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteAccountForm.main(null);
			}
		});
		mnAccount.add(mntmDeleteAccount);
		
		JMenuItem mntmDisplayAllAccounts = new JMenuItem("Display All Accounts");
		mntmDisplayAllAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountList.main(null);
			}
		});
		mnAccount.add(mntmDisplayAllAccounts);
		
		JMenuItem mntmDisplayDebitorsAccuont = new JMenuItem("Display Debtor Accuonts");
		mntmDisplayDebitorsAccuont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DebtorAccountForm.main(null);
			}
		});
		mnAccount.add(mntmDisplayDebitorsAccuont);
		
		JMenu mnTransaction = new JMenu("Transaction");
		menuBar.add(mnTransaction);
		
		JMenuItem mntmWithdraw = new JMenuItem("Withdraw");
		mntmWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithdrawForm.main(null);
			}
		});
		mnTransaction.add(mntmWithdraw);
		
		JMenuItem mntmDeposit = new JMenuItem("Deposit");
		mntmDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositForm.main(null);
			}
		});
		mnTransaction.add(mntmDeposit);
		
		JMenuItem mntmDisplayAccountMovement = new JMenuItem("Display Account Movement");
		mntmDisplayAccountMovement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountMovementsForm.main(null);
			}
		});
		mnTransaction.add(mntmDisplayAccountMovement);
		
		JMenu mnCalculations = new JMenu("Calculations");
		mnTransaction.add(mnCalculations);
		
		JMenuItem mntmBankCharges = new JMenuItem("Bank Charges");
		mntmBankCharges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankChargeCalculationForm.main(null);
			}
		});
		mnCalculations.add(mntmBankCharges);
		
		JMenuItem mntmInterest = new JMenuItem("Interest");
		mntmInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterestCalculationForm.main(null);
			}
		});
		mnCalculations.add(mntmInterest);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
