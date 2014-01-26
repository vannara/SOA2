package ihm;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import qdb.StationPersistence;
import qdb.TicketPersistence;
import qdb.TrainDirectionPersistence;
import dbo.TrainStation;
import dbo.TrainTicket;

public class CreateATicketForm extends JDialog {

	private JPanel contentPane;
	private JTextField txtTicketNumber;
	private JTable table;
	private JComboBox cboToStation;
	private JComboBox cboFromStation;
	private String selectedFromStation;
	private String selectedToStation;
	private JTextField txtDepartureDate;
	private JLabel lblDepartureDate;
	private JTextField txtArrivalDate;
	private JTextField txtDepartureTime;
	private JLabel lblArrivalDate;
	private JLabel lblArrivalTime;
	private JTextField txtArrivalTime;
	private JLabel lblTrainNumber;
	private JTextField txtTrainNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateATicketForm frame = new CreateATicketForm();
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
	public CreateATicketForm() {
		super();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(800, 800, 650, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 386, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
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

		List<TrainStation> Fromstations = TrainDirectionPersistence.getDepartureStations();
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
				List<TrainStation> Tostations = TrainDirectionPersistence.getArrivalStationFromAStation(stationName);
				String[] Tonames = new String[Tostations.size()];
				int ii = 0;
				for (TrainStation station : Tostations) {
					Tonames[ii] = station.getStationName();
					ii = ii + 1;
				}
				cboToStation.setModel(new JComboBox(Tonames).getModel());
				
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

		List<TrainStation> Tostations = TrainDirectionPersistence.getArrivalStations();
		String[] toNames = new String[Fromstations.size()];
		i = 0;
		for (TrainStation station : Tostations) {
			toNames[i] = station.getStationName();
			i = i + 1;
		}
		
		cboToStation = new JComboBox(toNames);
		cboToStation.setEditable(false);
//		cboToStation.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//			
//		});

		GridBagConstraints gbc_cboToStation = new GridBagConstraints();
		gbc_cboToStation.insets = new Insets(0, 0, 5, 0);
		gbc_cboToStation.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboToStation.gridx = 1;
		gbc_cboToStation.gridy = 1;
		contentPane.add(cboToStation, gbc_cboToStation);
		cboToStation.setEditable(false);

		JLabel lblNewLabel_2 = new JLabel("Ticket Number:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtTicketNumber = new JTextField();
		GridBagConstraints gbc_txtTicketNumber = new GridBagConstraints();
		gbc_txtTicketNumber.insets = new Insets(0, 0, 5, 0);
		gbc_txtTicketNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTicketNumber.gridx = 1;
		gbc_txtTicketNumber.gridy = 2;
		contentPane.add(txtTicketNumber, gbc_txtTicketNumber);
		
		
		lblTrainNumber = new JLabel("Train Number:");
		GridBagConstraints gbc_lblTrainNumber = new GridBagConstraints();
		gbc_lblTrainNumber.anchor = GridBagConstraints.EAST;
		gbc_lblTrainNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblTrainNumber.gridx = 0;
		gbc_lblTrainNumber.gridy = 3;
		contentPane.add(lblTrainNumber, gbc_lblTrainNumber);
		
		txtTrainNumber = new JTextField();
		GridBagConstraints gbc_txtTrainNumber = new GridBagConstraints();
		gbc_txtTrainNumber.insets = new Insets(0, 0, 5, 0);
		gbc_txtTrainNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTrainNumber.gridx = 1;
		gbc_txtTrainNumber.gridy = 3;
		contentPane.add(txtTrainNumber, gbc_txtTrainNumber);
		txtTrainNumber.setColumns(10);
		
		lblDepartureDate = new JLabel("Departure Date (dd/MM/yyyy):");
		GridBagConstraints gbc_lblDepartureDate = new GridBagConstraints();
		gbc_lblDepartureDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartureDate.gridx = 0;
		gbc_lblDepartureDate.gridy = 4;
		contentPane.add(lblDepartureDate, gbc_lblDepartureDate);
		
				txtDepartureDate = new JTextField();
				GridBagConstraints gbc_txtDepartureDate = new GridBagConstraints();
				gbc_txtDepartureDate.insets = new Insets(0, 0, 5, 0);
				gbc_txtDepartureDate.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDepartureDate.gridx = 1;
				gbc_txtDepartureDate.gridy = 4;
				contentPane.add(txtDepartureDate, gbc_txtDepartureDate);
				txtDepartureDate.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Departure Time: (hh:mm)");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: save
				JComboBox cboFrom = (JComboBox) cboFromStation;
				JComboBox cboTo = (JComboBox) cboToStation;
				String fromStationName = (String) cboFrom.getSelectedItem();
				String toStationName = (String) cboTo.getSelectedItem();
				Date departureDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(txtDepartureDate.getText());
				System.out.println(departureDate); 
				Date arrivalDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(txtArrivalDate.getText());
				System.out.println(arrivalDate); 
				
				String str = "08:03";
				DateFormat formatter = new SimpleDateFormat("hh:mm");
				Date date = (Date)formatter.parse(str);
				 
				TicketPersistence.createATicket(fromStationName, toStationName,
						txtTicketNumber.getText(), 
						txtTrainNumber.getText(), 
						departureDate,
						arrivalDate,
						txtDepartureTime.getText(),
						txtArrivalTime.getText());
				reloadData();
			}
		});
		
		txtDepartureTime = new JTextField();
		GridBagConstraints gbc_txtDepartureTime = new GridBagConstraints();
		gbc_txtDepartureTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtDepartureTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDepartureTime.gridx = 1;
		gbc_txtDepartureTime.gridy = 5;
		contentPane.add(txtDepartureTime, gbc_txtDepartureTime);
		txtDepartureTime.setColumns(10);
		
		lblArrivalDate = new JLabel("Arrival Date (dd/MM/yyyy):");
		GridBagConstraints gbc_lblArrivalDate = new GridBagConstraints();
		gbc_lblArrivalDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrivalDate.anchor = GridBagConstraints.EAST;
		gbc_lblArrivalDate.gridx = 0;
		gbc_lblArrivalDate.gridy = 6;
		contentPane.add(lblArrivalDate, gbc_lblArrivalDate);
		
		txtArrivalDate = new JTextField();
		GridBagConstraints gbc_txtArrivalDate = new GridBagConstraints();
		gbc_txtArrivalDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtArrivalDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtArrivalDate.gridx = 1;
		gbc_txtArrivalDate.gridy = 6;
		contentPane.add(txtArrivalDate, gbc_txtArrivalDate);
		txtArrivalDate.setColumns(10);
		
		lblArrivalTime = new JLabel("Arrival Time (hh:mm);");
		GridBagConstraints gbc_lblArrivalTime = new GridBagConstraints();
		gbc_lblArrivalTime.anchor = GridBagConstraints.EAST;
		gbc_lblArrivalTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrivalTime.gridx = 0;
		gbc_lblArrivalTime.gridy = 7;
		contentPane.add(lblArrivalTime, gbc_lblArrivalTime);
		
		txtArrivalTime = new JTextField();
		GridBagConstraints gbc_txtArrivalTime = new GridBagConstraints();
		gbc_txtArrivalTime.insets = new Insets(0, 0, 5, 0);
		gbc_txtArrivalTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtArrivalTime.gridx = 1;
		gbc_txtArrivalTime.gridy = 7;
		contentPane.add(txtArrivalTime, gbc_txtArrivalTime);
		txtArrivalTime.setColumns(10);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 8;
		contentPane.add(btnSave, gbc_btnSave);

		//
		table = new JTable();

		List<TrainTicket> tickets = TicketPersistence.getAllTickets();
		// Add all directions to list
		final DefaultTableModel listModel = new DefaultTableModel();
		listModel.addColumn("Ticket#");
		listModel.addColumn("Train#");
		listModel.addColumn("From");
		listModel.addColumn("To");
		listModel.addColumn("From Date");
		listModel.addColumn("To Date");
		listModel.addColumn("From Time");
		listModel.addColumn("To Time");
		listModel.addColumn("Duration");
		listModel.addColumn("Price");
		for (TrainTicket ticket : tickets) {
			listModel.addRow(new Object[] {
					ticket.getTicketNumber(),
					ticket.getTrainNumber(),
					ticket.getDepartureStation().getStationName(),
					ticket.getArrivalStation().getStationName(),
					ticket.getDepartureDate(),
					ticket.getDepartureTime(),
					ticket.getArrivalDate(),
					ticket.getArrivalTime(),
					ticket.getDuration(),
					ticket.getPrice()});
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
		gbc_table.gridwidth = 2;
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 9;
		table.setModel(listModel);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, gbc_table);
	}

	private void reloadData() {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		List<TrainTicket> tickets = TicketPersistence.getAllTickets();
		for (TrainTicket ticket : tickets) {
			((DefaultTableModel) table.getModel()).addRow(new Object[] {
					ticket.getTicketNumber(),
					ticket.getTrainNumber(),
					ticket.getDepartureStation().getStationName(),
					ticket.getArrivalStation().getStationName(),
					ticket.getDepartureDate(),
					ticket.getDepartureTime(),
					ticket.getArrivalDate(),
					ticket.getArrivalTime(),
					ticket.getDuration(),
					ticket.getPrice()});
		}
	}	
}
