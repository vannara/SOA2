package ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSetup = new JMenu("Setup");
		menuBar.add(mnSetup);
		
		JMenuItem mntmCity = new JMenuItem("City");
		mntmCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateCityForm.main(null);
			}
		});
		mnSetup.add(mntmCity);
		
		JMenuItem mntmTravel = new JMenuItem("City Distances");
		mntmTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCityDistanceForm.main(null);
			}
		});
		mnSetup.add(mntmTravel);
		
		JMenuItem mntmTrainStations = new JMenuItem("Train Station");
		mnSetup.add(mntmTrainStations);
		
		JMenuItem mntmTrainStationDiretions = new JMenuItem("Train Station Diretions");
		mntmTrainStationDiretions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTrainDirectionsForm.main(null);
			}
		});
		mnSetup.add(mntmTrainStationDiretions);
		
		JMenu mnOperation = new JMenu("Operation");
		menuBar.add(mnOperation);
		
		JMenuItem mntmCreateTrainTicket = new JMenuItem("Create Train Ticket");
		mntmCreateTrainTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateATicketForm.main(null);
			}
		});
		mnOperation.add(mntmCreateTrainTicket);
		
		JMenuItem mntmReserveATicket = new JMenuItem("Reserve a Ticket");
		mnOperation.add(mntmReserveATicket);
		
		JMenuItem mntmDeleteATicket = new JMenuItem("Delete a Ticket");
		mnOperation.add(mntmDeleteATicket);
		
		JMenuItem mntmModifyATicket = new JMenuItem("Modify a Ticket");
		mnOperation.add(mntmModifyATicket);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenuItem mntmDestinationFromA = new JMenuItem("Destination from a City");
		mntmDestinationFromA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchDestinationFromACityForm.main(null);
			}
		});
		mnSearch.add(mntmDestinationFromA);
		
		JMenuItem mntmDestinationWith = new JMenuItem("Destination with <=x Distance");
		mntmDestinationWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchDestinationFromACityWithLessXDistance.main(null);
			}
		});
		mnSearch.add(mntmDestinationWith);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Travel by Passenger Name");
		mnSearch.add(mntmNewMenuItem);
		
		JMenuItem mntmListOfTickets = new JMenuItem("List of Tickets");
		mnSearch.add(mntmListOfTickets);
	}

}
