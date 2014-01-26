/***
* AUTHOR: LOCH Vannara
* Created Date: 08-Jan-2014
* Submitted Date: 14-Jan-2014
***/
I. Configuration
datanucleus.properties contain the configuration to connect to the database of choice. In this lab, I choose PostgresSQL Database, if you wish to use different database just change the property in this file.

II. Structure
	1. jdotd2.helper
	In this package there is a singleton class that read the connection properties from the datanucleus.properties to generate PersistenceManagerFactory.
	
	2.jdotd2.ihm
	In this package there are all java classes of the GUI of the application.  
	
	3.jdotd2.dao
	In this package there are class that are needed to be persisted in the database. It also contains a "bank.jdo", the schema file that is generated from all the classes in this package.
	
	4. jdotd2.business
	This package contains many business functions that handle the data manipulation in the database for example, setup Client Type, Account Type, Customer, Create/Delete Account, Withdraw/Deposit Cash into Account, and Displays account information (Transactions, debtor accounts, and balance). 

III. Application
Start an application with MainForm class.
	1. Setup Menu
	Setup some required data to use in the application for example, Client Type, Account Type (see annexes below), and Client.
	Just an example, I implemented Insert/Delete/Update in Account_Type Form. I can update account type information directly in the list and it will update automatically into the database. Deleting also works but sometimes it has problem with the Re-rendering of this List after delete (it throws exception). [Display Client Type and Customer is not implemented]
	
	2. Account Menu
	In this menu, I can create an account by choosing a client and account type that I have setup before (menu Create Account).
	Menu Delete Account allows to delete account by choosing an account number from the list.
	I can also Display all accounts that I have been created and just the accounts that has balance less than 0 (menu Display Debtor Accounts).
	
	3. Transaction
	In this menu, there are transactions such as withdraw cash from an account, deposit cash to an account, display all the transactions of an account in the list. 
	Calculations are used to calculate bank charges and interest to one account of the day. The list below each calculation is the transactions that has been saving in the database before (if any). Instead of running automatically these two transaction (charge and interest) run these two transactions everyday from this menu.

The account balance will update accordingly after the transaction is affected. 
	
IV. Annexes	
=======================Data Setup==================	
	Client_Type
1. Particular
2. Enterprise	
-------------------------------------------------------------------------------------
Account_Type		First_Bank_Charge	Second_Bank_Charge		Interest
1. Standard_Particular		17.0			0.0			  0.0
2. Privilege			10.0 	 	       17.0			  0.0
3. Standard_Enterprise		 8.0			0.0  			  0.0
4. Rewarding			 8.0	 	        0.0 			  6.0

================Automatic Transaction===========
1. bank_charge (-)
2. interest (+)