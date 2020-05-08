package sa2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class AppGestion extends JFrame {

	private JPanel contentPane;
	public String priviledge;//creé ses 2 variables privilege et myname 
	public String myname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGestion frame = new AppGestion("role","nom");
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
	public AppGestion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestion = new JLabel("Gestion de Mon Paradis");
		lblGestion.setForeground(new Color(255, 255, 204));
		lblGestion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblGestion.setBounds(166, 11, 208, 34);
		contentPane.add(lblGestion);
		
		JButton btnChambre = new JButton("Chambres");
		btnChambre.setBackground(new Color(0, 255, 153));
		btnChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				InterfaceChambre chambre = new InterfaceChambre();
				chambre.setVisible(true);
					
			}
		});
		btnChambre.setBounds(87, 76, 131, 49);
		contentPane.add(btnChambre);
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.setBackground(new Color(0, 255, 153));
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				GestionReservation frame = new GestionReservation();
				frame.showTablereserv();
				frame.setVisible(true);
			}
		});
		btnReservation.setBounds(87, 194, 131, 51);
		contentPane.add(btnReservation);
	
		JButton btnUtilisateur = new JButton("Utilisateur");
		btnUtilisateur.setBackground(new Color(0, 255, 153));
		btnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserInterface user = new UserInterface();
				user.showTable();
				user.setVisible(true);
			}
		});
		btnUtilisateur.setBounds(345, 76, 131, 49);
		contentPane.add(btnUtilisateur);
		
		JButton btnClients = new JButton("Clients");
		btnClients.setBackground(new Color(0, 255, 153));
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserInterface frame = new UserInterface();
				frame.showTable();
				frame.setVisible(true);
				
			}
		});
		btnClients.setBounds(345, 194, 131, 53);
		contentPane.add(btnClients);
	//////////////////////////////////////////////////////annuler////////////////////////////////////////////////	
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.BLACK);
		btnAnnuler.setBackground(new Color(204, 255, 51));
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnAnnuler.setBounds(208, 317, 131, 36);
		contentPane.add(btnAnnuler);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Michela\\Downloads\\sa2 photo\\sunset-pool_1203-3192.jpg"));
		lblNewLabel.setBounds(0, 0, 573, 382);
		contentPane.add(lblNewLabel);
		
		
		
		System.out.print(priviledge);
		System.out.print(myname);
		
		
		
		try { if(priviledge.contentEquals("Administrateur")) {
			contentPane.add(btnChambre);
			contentPane.add(btnReservation);
			contentPane.add(btnUtilisateur);
			contentPane.add(btnClients);
			
			
			
		}else if (priviledge.contentEquals("Utilisateur")) {
			contentPane.add(btnChambre);
			contentPane.add(btnReservation);
			contentPane.add(btnClients);
		
		}else {
			JOptionPane.showMessageDialog(null, "Erreur.");
		}
		
		}catch(Exception e) {
			System.out.print(e);
		}
		
		
	
	}

	public AppGestion(String priviledge2, String myname2) {
		// TODO Auto-generated constructor stub
	}
}
