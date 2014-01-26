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

public class SearchDestinationFromACityWithLessXDistance extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cboFromCity;
	private JLabel lblNewLabel_1;
	private JTextField txtDistance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDestinationFromACityWithLessXDistance frame = new SearchDestinationFromACityWithLessXDistance();
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
	public SearchDestinationFromACityWithLessXDistance() {
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
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
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
		
		contentPane.add(cboFromCity, gbc_cboFromCity);
		
		lblNewLabel_1 = new JLabel("Distance:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		//
		table = new JTable();

//		List<TrainDirections> directions = TrainDirectionPersistence
//				.getAllDirections();
		// Add all directions to list
		final DefaultTableModel listModel = new DefaultTableModel();
		listModel.addColumn("Destination City ");
		listModel.addColumn("Destination Station");
		listModel.addColumn("Distance");
		listModel.addColumn("Price");
		
				JButton btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO: search
						JComboBox cboFrom = (JComboBox) cboFromCity;
						String fromCityName = (String) cboFrom.getSelectedItem();
						
						((DefaultTableModel) table.getModel()).setRowCount(0);
						List<TrainDirections> directions = TrainDirectionPersistence.getDestinationsFromCityWithLessDistance(fromCityName, Float.parseFloat(txtDistance.getText()));
						
						for (TrainDirections direction : directions) {
							((DefaultTableModel) table.getModel()).addRow(new Object[] {
									direction.getToStation().getCity().getCityName(),
									direction.getToStation().getStationName(),
									direction.getDistance(), direction.getPrice() });
						}
						
					}
				});
				
				txtDistance = new JTextField();
				GridBagConstraints gbc_txtDistance = new GridBagConstraints();
				gbc_txtDistance.insets = new Insets(0, 0, 5, 0);
				gbc_txtDistance.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDistance.gridx = 1;
				gbc_txtDistance.gridy = 1;
				contentPane.add(txtDistance, gbc_txtDistance);
				txtDistance.setColumns(10);
				GridBagConstraints gbc_btnSearch = new GridBagConstraints();
				gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
				gbc_btnSearch.gridx = 1;
				gbc_btnSearch.gridy = 2;
				contentPane.add(btnSearch, gbc_btnSearch);
	
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 3;
		table.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc_table);
	}

}
