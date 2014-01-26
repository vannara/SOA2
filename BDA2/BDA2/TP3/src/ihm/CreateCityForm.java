package ihm;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.GridBagLayout;

import javax.swing.JTable;

import dbo.City;
import qdb.CityPersistence;

import java.awt.GridBagConstraints;
import java.awt.Dialog;
import java.util.List;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateCityForm extends JDialog {
	private JTextField txtCityName;
	private JTable table;
	
	private static String selectedCityName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCityForm frame = new CreateCityForm();
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
	public CreateCityForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(400, 400, 450, 450);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("City Name:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtCityName = new JTextField();
		GridBagConstraints gbc_txtCityName = new GridBagConstraints();
		gbc_txtCityName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCityName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCityName.gridx = 1;
		gbc_txtCityName.gridy = 0;
		getContentPane().add(txtCityName, gbc_txtCityName);
		txtCityName.setColumns(10);
		
		table = new JTable();
		
		JButton btnNewButton = new JButton("Save");
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CityPersistence.createCity(txtCityName.getText());
				txtCityName.setText("");
				reloadData();				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
	
		// Add all cities to list
		final DefaultTableModel listModel = new DefaultTableModel();
		List<City> cities = CityPersistence.getAllCities();
		listModel.addColumn("City Name");
		for (City city : cities) {
			listModel.addRow(new Object[] { city.getCityName() });
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						int rowIndex = table.getSelectedRow();
						if (rowIndex >= 0) {
							selectedCityName = table.getValueAt(
									table.getSelectedRow(), 0).toString();
							System.out.println(selectedCityName);
						}
					}

				});
		
		listModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					String newName = table.getValueAt(table.getSelectedRow(), 0).toString();
					System.out.println("new name" + newName);
					CityPersistence.updateCity(selectedCityName, newName);
					selectedCityName =newName;
				}

			}
			
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListSelectionModel model = table.getSelectionModel();
				int rowIndex = model.getLeadSelectionIndex();
				String cityName = (String) listModel
						.getValueAt(rowIndex, 0);
				CityPersistence.deleteCity(cityName);
				reloadData();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 1;
		getContentPane().add(btnDelete, gbc_btnDelete);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 2;
		table.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, gbc_table);
	}

	private void reloadData() {
		((DefaultTableModel) table.getModel()).setRowCount(0); 
		
	    List<City> cities = CityPersistence.getAllCities();
	    for(City city:cities){
	    	((DefaultTableModel) table.getModel()).addRow(new Object[]{ city.getCityName()});
	    }
	}
}
