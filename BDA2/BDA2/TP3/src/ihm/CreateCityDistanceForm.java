package ihm;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dialog;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

import qdb.CityPersistence;
import qdb.CityDistancePersistence;
import dbo.City;
import dbo.CityDistance;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CreateCityDistanceForm extends JDialog {

	private JPanel contentPane;
	private JTextField txtDistance;
	private JTable table;
	private JComboBox cboToCity;
	private JComboBox cboFromCity;
	private String selectedFromCity;
	private String selectedToCity;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCityDistanceForm frame = new CreateCityDistanceForm();
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
	public CreateCityDistanceForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(400, 400, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("From City:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		GridBagConstraints gbc_cboFromCity = new GridBagConstraints();
		gbc_cboFromCity.insets = new Insets(0, 0, 5, 0);
		gbc_cboFromCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboFromCity.gridx = 1;
		gbc_cboFromCity.gridy = 0;

		List<City> cities = CityPersistence.getAllCities();
		String[] cityNames = new String[cities.size()];
		int i = 0;
		for (City city : cities) {
			cityNames[i] = city.getCityName();
			i = i + 1;
		}
		cboFromCity = new JComboBox(cityNames);
		cboFromCity.setEditable(false);
		cboFromCity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cboFrom = (JComboBox) e.getSource();
				String cityName = (String) cboFrom.getSelectedItem();
				List<City> to = CityPersistence.getAllCitiesExcept(cityName);
				String[] names = new String[to.size()];
				int i = 0;
				for (City city : to) {
					names[i] = city.getCityName();
					i = i + 1;
				}
				cboToCity.setModel(new JComboBox(names).getModel());
			}

		});

		contentPane.add(cboFromCity, gbc_cboFromCity);

		JLabel lblNewLabel_1 = new JLabel("To City:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		List<City> Tocities = CityPersistence.getAllCitiesExcept(cities.get(0)
				.getCityName());
		String[] names = new String[cities.size()];
		int ii = 0;
		for (City city : Tocities) {
			names[ii] = city.getCityName();
			ii = ii + 1;
		}
		cboToCity = new JComboBox(names);

		GridBagConstraints gbc_cboToCity = new GridBagConstraints();
		gbc_cboToCity.insets = new Insets(0, 0, 5, 0);
		gbc_cboToCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboToCity.gridx = 1;
		gbc_cboToCity.gridy = 1;
		contentPane.add(cboToCity, gbc_cboToCity);
		cboToCity.setEditable(false);

		JLabel lblNewLabel_2 = new JLabel("Distance (km):");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtDistance = new JTextField();
		GridBagConstraints gbc_txtDistance = new GridBagConstraints();
		gbc_txtDistance.insets = new Insets(0, 0, 5, 0);
		gbc_txtDistance.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDistance.gridx = 1;
		gbc_txtDistance.gridy = 2;
		contentPane.add(txtDistance, gbc_txtDistance);
		txtDistance.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: delete
				CityDistancePersistence.deleteCityDistance(selectedFromCity, selectedToCity);
				reloadData();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 3;
		contentPane.add(btnDelete, gbc_btnDelete);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: save
				JComboBox cboFrom = (JComboBox) cboFromCity;
				JComboBox cboTo = (JComboBox) cboToCity;
				String cityNameFrom = (String) cboFrom.getSelectedItem();
				String cityNameTo = (String) cboTo.getSelectedItem();
				CityDistancePersistence.createCityDistance(cityNameFrom,
						cityNameTo,
						(float) Double.parseDouble(txtDistance.getText()));
				reloadData();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 3;
		contentPane.add(btnSave, gbc_btnSave);

		//
		table = new JTable();

		List<CityDistance> trips = CityDistancePersistence.getAllCityDistance();
		// Add all cities to list
		final DefaultTableModel listModel = new DefaultTableModel();
		listModel.addColumn("From City");
		listModel.addColumn("To City");
		listModel.addColumn("Distance");
		for (CityDistance city : trips) {
			listModel.addRow(new Object[] { city.getFromCity().getCityName(),city.getToCity().getCityName(),city.getDistance() });
		}
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						int rowIndex = table.getSelectedRow();
						if (rowIndex >= 0) {
							selectedFromCity = table.getValueAt(
									table.getSelectedRow(), 0).toString();
							
							selectedToCity = table.getValueAt(
									table.getSelectedRow(), 1).toString();
							
							
						}
					}
				});

		listModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					
					float newDistance =(float) Double.parseDouble(table
							.getValueAt(table.getSelectedRow(), 2).toString());
					System.out.println(selectedFromCity + " to "+ selectedToCity );
					CityDistancePersistence.updateCityDistance(selectedFromCity, selectedToCity, newDistance);
					reloadData();
				}

			}

		});
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 4;
		table.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc_table);
	}

	private void reloadData() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		List<CityDistance> cities = CityDistancePersistence.getAllCityDistance();
		for (CityDistance city : cities) {
			((DefaultTableModel) table.getModel()).addRow(new Object[] { city.getFromCity().getCityName(),city.getToCity().getCityName(),city.getDistance() });
		}
	}
}
