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
import qdb.TrainDirectionPersistence;
import dbo.City;
import dbo.CityDistance;
import dbo.TrainDirections;
import dbo.TrainStation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CreateTrainDirectionsForm extends JDialog {

	private JPanel contentPane;
	private JTextField txtDistance;
	private JTable table;
	private JComboBox cboToStation;
	private JComboBox cboFromStation;
	private String selectedFromStation;
	private String selectedToStation;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTrainDirectionsForm frame = new CreateTrainDirectionsForm();
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
	public CreateTrainDirectionsForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(400, 400, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("From Station:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		GridBagConstraints gbc_cboFromStation = new GridBagConstraints();
		gbc_cboFromStation.insets = new Insets(0, 0, 5, 0);
		gbc_cboFromStation.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboFromStation.gridx = 1;
		gbc_cboFromStation.gridy = 0;

		List<TrainStation> Fromstations = StationPersistence
				.getAllTrainStations();
		String[] names = new String[Fromstations.size()];
		int i = 0;
		for (TrainStation station : Fromstations) {
			names[i] = station.getStationName();
			i = i + 1;
		}
		cboFromStation = new JComboBox(names);
		cboFromStation.setEditable(false);
		cboFromStation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cboFrom = (JComboBox) e.getSource();
				String stationName = (String) cboFrom.getSelectedItem();
				List<TrainStation> Tostations = StationPersistence
						.getAllStationsExcept(stationName);
				String[] Tonames = new String[Tostations.size()];
				int ii = 0;
				for (TrainStation station : Tostations) {
					Tonames[ii] = station.getStationName();
					ii = ii + 1;
				}
				cboToStation.setModel(new JComboBox(Tonames).getModel());
				reloadDistance();
			}

		});

		contentPane.add(cboFromStation, gbc_cboFromStation);

		JLabel lblNewLabel_1 = new JLabel("To Station:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		List<TrainStation> Tostations = StationPersistence
				.getAllStationsExcept(Fromstations.get(0).getStationName());
		String[] Tonames = new String[Tostations.size()];
		int ii = 0;
		for (TrainStation station : Tostations) {
			Tonames[ii] = station.getStationName();
			ii = ii + 1;
		}
		cboToStation = new JComboBox(Tonames);
		cboToStation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reloadDistance();
			}
			
		});

		GridBagConstraints gbc_cboToStation = new GridBagConstraints();
		gbc_cboToStation.insets = new Insets(0, 0, 5, 0);
		gbc_cboToStation.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboToStation.gridx = 1;
		gbc_cboToStation.gridy = 1;
		contentPane.add(cboToStation, gbc_cboToStation);
		cboToStation.setEditable(false);

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
		reloadDistance();

		JLabel lblNewLabel_3 = new JLabel("Price:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtPrice = new JTextField();
		GridBagConstraints gbc_txtPrice = new GridBagConstraints();
		gbc_txtPrice.insets = new Insets(0, 0, 5, 0);
		gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrice.gridx = 1;
		gbc_txtPrice.gridy = 3;
		contentPane.add(txtPrice, gbc_txtPrice);
		txtPrice.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: save
				JComboBox cboFrom = (JComboBox) cboFromStation;
				JComboBox cboTo = (JComboBox) cboToStation;
				String fromStationName = (String) cboFrom.getSelectedItem();
				String toStationName = (String) cboTo.getSelectedItem();
				TrainDirectionPersistence.createTrainDirection(fromStationName,
						toStationName,
						(float) Double.parseDouble(txtDistance.getText()),
						(float) Double.parseDouble(txtPrice.getText()));
				reloadData();
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 4;
		contentPane.add(btnSave, gbc_btnSave);

		//
		table = new JTable();

		List<TrainDirections> directions = TrainDirectionPersistence
				.getAllDirections();
		// Add all directions to list
		final DefaultTableModel listModel = new DefaultTableModel();
		listModel.addColumn("From Station");
		listModel.addColumn("To Station");
		listModel.addColumn("Distance");
		listModel.addColumn("Price");
		for (TrainDirections direction : directions) {
			listModel.addRow(new Object[] {
					direction.getFromStation().getStationName(),
					direction.getToStation().getStationName(),
					direction.getDistance(), direction.getPrice() });
		}
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						int rowIndex = table.getSelectedRow();
						if (rowIndex >= 0) {
							selectedFromStation = table.getValueAt(
									table.getSelectedRow(), 0).toString();

							selectedToStation = table.getValueAt(
									table.getSelectedRow(), 1).toString();

						}
					}
				});

		listModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {

					float newDistance = (float) Double.parseDouble(table
							.getValueAt(table.getSelectedRow(), 2).toString());
					float newPrice = (float) Double.parseDouble(table
							.getValueAt(table.getSelectedRow(), 3).toString());
					TrainDirectionPersistence.updateTrainDirectionDistance(selectedFromStation, selectedToStation, newDistance);
					TrainDirectionPersistence.updateTrainDirectionPrice(selectedFromStation, selectedToStation, newPrice);
					reloadData();
				}

			}

		});
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 5;
		table.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc_table);
	}

	private void reloadData() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		List<TrainDirections> directions = TrainDirectionPersistence
				.getAllDirections();
		for (TrainDirections direction : directions) {
			((DefaultTableModel) table.getModel()).addRow(new Object[] {
					direction.getFromStation().getStationName(),
					direction.getToStation().getStationName(),
					direction.getDistance(), direction.getPrice() });
		}
	}

	private void reloadDistance() {
		// reload distance
		selectedFromStation = (String) cboFromStation.getSelectedItem();
		selectedToStation = (String) cboToStation.getSelectedItem();

		String fromCity = StationPersistence
				.getCityByStationName(selectedFromStation);
		String toCity = StationPersistence
				.getCityByStationName(selectedToStation);
		CityDistance cityDistance = CityDistancePersistence.getCityDistance(
				fromCity, toCity);
		float defaultDistance = 0;
		if (cityDistance != null) {
			defaultDistance = cityDistance.getDistance();
		}

		txtDistance.setText(Float.toString(defaultDistance));
	}
}
