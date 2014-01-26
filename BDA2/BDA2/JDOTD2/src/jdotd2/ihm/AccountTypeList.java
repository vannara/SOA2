package jdotd2.ihm;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JList;

import java.awt.GridBagConstraints;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import jdotd2.business.AccountTypePersistence;
import jdotd2.dao.AccountType;

import javax.swing.JTable;

public class AccountTypeList extends JDialog {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountTypeList frame = new AccountTypeList();
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
	public AccountTypeList() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	
		setTitle("Account Type Lists");
		setBounds(50, 50, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		//Add all account types to list
		final DefaultTableModel listModel = new DefaultTableModel();
	    List<AccountType> accTypes = AccountTypePersistence.getAllAccountTypes();
	    listModel.addColumn("");
	    listModel.addColumn("Bank Type");
	    listModel.addColumn("Bank Charge 1");
	    listModel.addColumn("Bank Charge 2");
	    listModel.addColumn("Interest");
	    listModel.addColumn("Delete");
	    for(AccountType type:accTypes){
	    	listModel.addRow(new Object[]{type.getAccountTypeId(),type.getAccountType(), type.getBankCharge(), type.getSecondBankCharge(), type.getInterest(), "Delete"});
	    	
	    }
	    table = new JTable();
	    table.setCellSelectionEnabled(true);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		table.setModel(listModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					ListSelectionModel model = table.getSelectionModel();
					int rowIndex = model.getLeadSelectionIndex();
					if(rowIndex<=0) rowIndex=0;
					int accTypeId = (Integer) listModel.getValueAt(rowIndex, 0);
					int columnIndex =tme.getColumn();

					switch (columnIndex) {
					case 1: 
						AccountTypePersistence.updateAccountType(accTypeId,listModel.getValueAt(rowIndex, tme.getColumn()).toString());
						break;
					case 2: 
						AccountTypePersistence.updateAccountTypeBankCharge(accTypeId,Double.parseDouble(listModel.getValueAt(rowIndex, tme.getColumn()).toString()));
						break;
					case 3: 
						AccountTypePersistence.updateAccountTypeSecondBankCharge(accTypeId,Double.parseDouble(listModel.getValueAt(rowIndex, tme.getColumn()).toString()));
						break;
					case 4: 
						AccountTypePersistence.updateAccountTypeInterest(accTypeId,Double.parseDouble(listModel.getValueAt(rowIndex, tme.getColumn()).toString()));
						break;
						
					}
					
				}
			}
		});
		table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
		table.getColumn("Delete").setCellEditor(
				new ButtonEditor(new JCheckBox()));
		table.getColumnModel()
				.removeColumn(table.getColumnModel().getColumn(0));
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc_table);
		
	}
	
}
class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
	    	
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label;

	  private boolean isPushed;
	  
	  private JTable table;
	  private int row;
	  private int col;
	  private TableModel listModel;
		

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	//fireEditingStopped();
	    	  
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
		  this.table = table;
	      this.row=row;
	      this.col=column;
	      this.listModel = table.getModel();
	      AccountTypePersistence.deleteAccountType(Integer.parseInt(listModel.getValueAt(row,0).toString()));
	      ((DefaultTableModel) this.listModel).setRowCount(0); 
		
	    List<AccountType> accTypes = AccountTypePersistence.getAllAccountTypes();
	    System.out.println("count" + accTypes.size());
		for(AccountType type:accTypes){
	    	((DefaultTableModel) this.table.getModel()).addRow(new Object[]{type.getAccountTypeId(),type.getAccountType(), type.getBankCharge(), type.getSecondBankCharge(), type.getInterest(), "Delete"});
	    }
	     
		((AbstractTableModel) this.listModel).fireTableDataChanged();
		 
	     if (isSelected) {

	    } 
	    else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      // 
	      // 
	      //JOptionPane.showMessageDialog(button, label + "row: " + row + " col:" + col );
	     
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}
