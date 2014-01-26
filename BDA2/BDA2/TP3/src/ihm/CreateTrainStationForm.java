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
import qdb.StationPersistence;
import dbo.City;
import dbo.CityDistance;
import dbo.TrainStation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CreateTrainStationForm extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cboFromCity;
	private JTextField txtStationName;
	private String selectedStation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTrainStationForm frame = new CreateTrainStationForm();
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
	public CreateTrainStationForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(400, 400, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("City:");
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
		contentPane.add(cboFromCity, gbc_cboFromCity);

		JLabel lblNewLabel_1 = new JLabel("Train Station Name:");
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

		txtStationName = new JTextField();
		GridBagConstraints gbc_txtStationName = new GridBagConstraints();
		gbc_txtStationName.anchor = GridBagConstraints.NORTH;
		gbc_txtStationName.insets = new Insets(0, 0, 5, 0);
		gbc_txtStationName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStationName.gridx = 1;
		gbc_txtStationName.gridy = 1;
		contentPane.add(txtStationName, gbc_txtStationName);
		txtStationName.setColumns(10);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: delete
				StationPersistence.deleteStation(selectedStation);
				reloadData();
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 2;
		contentPane.add(btnDelete, gbc_btnDelete);

		table = new JTable();
		//
		List<TrainStation> stations = StationPersistence.getAllTrainStations();
		// Add all train station to list
		final DefaultTableModel listModel = new DefaultTableModel();
		listModel.addColumn("City Name");
		listModel.addColumn("Station Name");
		for (TrainStation station : stations) {
			listModel.addRow(new Object[] { station.getCity().getCityName(),
					station.getStationName() });
		}
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						int rowIndex = table.getSelectedRow();
						if (rowIndex >= 0) {
							selectedStation = table.getValueAt(
									table.getSelectedRow(), 1).toString();
						}
					}
				});

		listModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					int index = table.getSelectedRow();
					if (index >= 0) {
						String newName = table.getValueAt(index, 1).toString();
						StationPersistence.updateStationName(selectedStation,
								newName);
						reloadData();
					}
				}

			}

		});
		table.setModel(listModel);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: save
				JComboBox cboFrom = (JComboBox) cboFromCity;
				String cityNameFrom = (String) cboFrom.getSelectedItem();
				StationPersistence.createStation(cityNameFrom,
						txtStationName.getText());
				reloadData();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 2;
		contentPane.add(btnSave, gbc_btnSave);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		JScrollPane scroll = new JScrollPane(table);

		contentPane.add(scroll, gbc_table);
	}

	private void reloadData() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		List<TrainStation> stations = StationPersistence.getAllTrainStations();
		for (TrainStation station : stations) {
			((DefaultTableModel) table.getModel())
					.addRow(new Object[] { station.getCity().getCityName(),
							station.getStationName() });
		}
	}
}
