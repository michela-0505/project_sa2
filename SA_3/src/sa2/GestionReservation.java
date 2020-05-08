package sa2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class GestionReservation extends JFrame {

	private JPanel contentPane;
	private JTextField txtRef;
	private static JTable ReservationTable;
	private JTextField txtnbPersonne;
	private JLabel txtId;
	private JLabel txtNom;
	private JLabel txtPrenom;
	private JLabel txtRole;
	private JLabel txtUsername;
	private JLabel txtPassword;
	private String dateArr;
	private String dateDep;
	private String id_reservation;
	private String nbrPersonne;
	private JComboBox<String> comboStatut;
	private String dateReservation;
	private JTextField txtIdClient;
	private JTextField txtIdReserv;
	private String id_categorie;
	private String id_client;
	private JTextField txtIdCategorie;
	private String idReservé, idClient, clinom, cliprenom, idChambre;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionReservation frame = new GestionReservation();
					frame.setVisible(true);
					showTablereserv();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionReservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 956, 485);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion des R\u00E9servations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(31, 25, 215, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRef = new JLabel("Ref:");
		lblRef.setBounds(31, 89, 86, 14);
		contentPane.add(lblRef);
		
		JLabel lblDateDeRservation = new JLabel("Date de R\u00E9servation:");
		lblDateDeRservation.setBounds(31, 114, 145, 14);
		contentPane.add(lblDateDeRservation);
		
		JLabel lblDateDarrive = new JLabel("Date d'arriv\u00E9e:");
		lblDateDarrive.setBounds(31, 139, 113, 14);
		contentPane.add(lblDateDarrive);
		
		JLabel lblDateDeDpart = new JLabel("Date de d\u00E9part:");
		lblDateDeDpart.setBounds(31, 164, 86, 14);
		contentPane.add(lblDateDeDpart);
		
		txtRef = new JTextField();
		txtRef.setBounds(177, 83, 171, 20);
		contentPane.add(txtRef);
		txtRef.setColumns(10);
		
		JDateChooser dateChooserReserv = new JDateChooser();
		dateChooserReserv.setDateFormatString("yyyy-MM-dd");
		dateChooserReserv.setBounds(177, 108, 171, 20);
		contentPane.add(dateChooserReserv);
		
		JDateChooser dateChooserdateArr = new JDateChooser();
		dateChooserdateArr.setDateFormatString("yyyy-MM-dd");
		dateChooserdateArr.setBounds(177, 139, 171, 20);
		contentPane.add(dateChooserdateArr);
		
		JDateChooser dateChooserdateDep = new JDateChooser();
		dateChooserdateDep.setDateFormatString("yyyy-MM-dd");
		dateChooserdateDep.setBounds(177, 170, 171, 20);
		contentPane.add(dateChooserdateDep);
		
		JLabel lblnbPersonne = new JLabel("Nombre de personne:");
		lblnbPersonne.setBounds(31, 200, 145, 14);
		contentPane.add(lblnbPersonne);
		
		txtnbPersonne = new JTextField();
		txtnbPersonne.setColumns(10);
		txtnbPersonne.setBounds(177, 201, 171, 20);
		contentPane.add(txtnbPersonne);
		
		JLabel lblIdclient = new JLabel("Id_client:");
		lblIdclient.setBounds(31, 235, 86, 14);
		contentPane.add(lblIdclient);
		
		txtIdClient = new JTextField();
		txtIdClient.setColumns(10);
		txtIdClient.setBounds(177, 232, 171, 20);
		contentPane.add(txtIdClient);
		
		JLabel lblIdcategorie = new JLabel("Id_categorie:");
		lblIdcategorie.setBounds(31, 273, 86, 14);
		contentPane.add(lblIdcategorie);
		
		txtIdCategorie = new JTextField();
		txtIdCategorie.setColumns(10);
		txtIdCategorie.setBounds(177, 263, 171, 20);
		contentPane.add(txtIdCategorie);
		
		
		

		
		///////////////////////////////////////////////////////////////////Ajouter Reservation///////////////////////////////////////////////////////////////////////////
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(60, 179, 113));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
					InterReservation frame = new InterReservation();
					frame.setVisible(true);
				
				}catch(Exception e){
					System.out.print(e);
				}
				
			}
		});
		btnAjouter.setBounds(31, 366, 116, 23);
		contentPane.add(btnAjouter);
		
		
		
		///////////////////////////////////////////////////////////Modifier Reservation////////////////////////////////////////////////////////////////////////////////////////
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(60, 179, 113));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			 
				//step 1 collect info from txtfield
				    String ref = txtRef.getText();
				    String dateArr = ((JTextField)dateChooserdateArr.getDateEditor().getUiComponent()).getText();
				    String dateDep = ((JTextField)dateChooserdateDep.getDateEditor().getUiComponent()).getText();
					String nbrPersonne = txtnbPersonne.getText();
					String id_client=txtIdClient.getText();
					String dateReservation =((JTextField) dateChooserReserv.getDateEditor().getUiComponent()).getText();
					String id_categorie=txtIdCategorie.getText();
					
					
					    System.out.println(ref);
					    System.out.println(dateArr);
					    System.out.println(dateDep);
					    System.out.println(nbrPersonne);
					    System.out.println(id_client);
						System.out.println(dateReservation);
						System.out.println(id_categorie);
			
						if(dateArr.trim().length()==0 && dateDep.trim().length()==0 && nbrPersonne.trim().length()==0 &&
								id_client.trim().length()==0 &&  dateReservation.trim().length()==0 && id_categorie.trim().length()==0) {
							
							JOptionPane.showMessageDialog(null,"Remplissez tous les champs");
							
						}
			
//			else if(username.trim().length()==0) {
//				
//			
//			}else if(password.trim().length()==0) {
//				
//			}
						
						
			else {
				Connectdb data = new Connectdb();
				try {
					Connection con;
					con = (Connection) data.db_connect();
					java.sql.PreparedStatement reservstmt = con.prepareStatement("UPDATE reservation  SET dateArr=?,dateDep=?,nbrPersonne=?,"
							+ "id_client=?,dateReservation=?,id_categorie=? "
							+ "WHERE id_reservation=?");
					reservstmt.setString(2, dateArr);
					reservstmt.setString(3, dateDep);
					reservstmt.setString(4, nbrPersonne);
					reservstmt.setString(5, id_client);
					reservstmt.setString(6, dateReservation);
					reservstmt.setString(7, id_categorie);
					reservstmt.executeUpdate();
					showTablereserv();
					JOptionPane.showMessageDialog(null, "Modification effectuer");
					
					
					
					
				}catch(Exception e1) {
					
				}
			}
					
				
			}
		});
		btnModifier.setBounds(181, 366, 113, 23);
		contentPane.add(btnModifier);
		
		/////////////////////////////////////////////////////////////////////Effacer reservation////////////////////////////////////////////////////////////////////////////
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setBackground(new Color(60, 179, 113));
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//step 1 collect info from txtfield
//				    String ref = txtRef.getText();
					String id_reservation=txtRef.getText();
			
				System.out.println(id_reservation);

			
if(id_reservation.trim().length()==0) {
				
				JOptionPane.showMessageDialog(null,"Remplissez tous les champs");
				
			}

			else {
				Connectdb data = new Connectdb();
				try {
					Connection con;
					con = (Connection) data.db_connect();
					java.sql.PreparedStatement reservstmt = con.prepareStatement("DELETE FROM reservation  WHERE id_reservation = ? ");
					reservstmt.setString(1, id_reservation);
					showTablereserv();
					reservstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Donnée effacer");
					
					
					
					
				}catch(Exception e1) {
					
				}
			}
				
				
			}
		});
		btnEffacer.setBounds(31, 412, 113, 23);
		contentPane.add(btnEffacer);
		
		
		///////////////////////////////////////////////////////////Reinitialiser reservation/////////////////////////////////////////////////////////////////////////////
		
		JButton btnReinitialiser = new JButton("R\u00E9initialiser");
		btnReinitialiser.setBackground(new Color(60, 179, 113));
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				//remet tous les champs a null
txtRef.setText(null);				
((JTextField)dateChooserdateArr.getDateEditor().getUiComponent()).setText("");
((JTextField)dateChooserdateDep.getDateEditor().getUiComponent()).setText("");
txtnbPersonne.setText(null);
txtIdClient.setText(null);
((JTextField) dateChooserReserv.getDateEditor().getUiComponent()).setText("");
txtIdCategorie.setText(null);		
				
				
				}
				
		
			
		});
		btnReinitialiser.setBounds(181, 412, 113, 23);
		contentPane.add(btnReinitialiser);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		scrollPane.setBounds(357, 58, 583, 377);
		contentPane.add(scrollPane);
		
		ReservationTable = new JTable() {
//////////////////////Make Rows Not Editable Step 1////////////////////////
			
public boolean isCellEditable(int row, int colunm) {
	return false;
}
	};
		///////////////////pour afficher la table de reservation quand on click sur une reservation du table//////////////////////
		ReservationTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Connectdb data = new Connectdb();
				try {
					int row = ReservationTable.getSelectedRow();
					//System.out.print(row);
					String tableClick = (ReservationTable.getModel().getValueAt(row,0).toString()); ///0=colunm 
//					System.out.print(tableClick);
					Connection con;
					con = (Connection) data.db_connect();
					java.sql.PreparedStatement callinfo = con.prepareStatement("SELECT * FROM reservation "
							+ "WHERE id_reservation='"+tableClick+"'");
					ResultSet rs = callinfo.executeQuery();
					if(rs.next()) {
						
						String data1 = rs.getString("id_reservation");
						String data2 = rs.getString("dateArr");
						String data3 = rs.getString("dateDep");
						String data4 = rs.getString("nbrPersonne");
						String data5= rs.getString("id_client");
						String data6 = rs.getString("dateReservation");
						String data7 = rs.getString("id_categorie");
						
						System.out.println(data1);
						System.out.println(data2);
						System.out.println(data3);
						System.out.println(data4);
						System.out.println(data5);
						System.out.println(data6);
						System.out.println(data7);
						System.out.println("_____");
						
						txtRef.setText(data1);				
						((JTextField)dateChooserdateArr.getDateEditor().getUiComponent()).setText(data2);
						((JTextField)dateChooserdateDep.getDateEditor().getUiComponent()).setText(data3);
						txtnbPersonne.setText(data4);
						txtIdClient.setText(data5);
						((JTextField) dateChooserReserv.getDateEditor().getUiComponent()).setText(data6);
						txtIdCategorie.setText(data7);		
						
					
					}	
				}
					catch(Exception e1) {
					
					System.out.print(e1);
				}
				
				}
			
				
		});
		
		scrollPane.setViewportView(ReservationTable);
		///////////////////////////////////////////Assigner une chambre a une reservation////////////
		
		JButton btnAssignerChambre = new JButton("Voir/Assigner Chambre");
		btnAssignerChambre.setBackground(new Color(72,138,153));
		btnAssignerChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AssignChambre frame = new AssignChambre("","","","");
                frame.setVisible(true);
				
				
				String idRes = txtIdReserv.getText();
				String statut = comboStatut.getSelectedItem().toString();
				// System.out.print(status);

				if (idRes.trim().length()== 0) {
				JOptionPane.showMessageDialog(null,"Cliquez sur une réservation d'abord SVP");
				}
				if(statut.equals("Non Confirmé")){
				JOptionPane.showMessageDialog(null,"Veuillez d'abord confirmer la réservation SVP");
				}else 

				try {

				
				
				Connectdb data=new Connectdb();
				
				Connection con=data.db_connect();
				PreparedStatement reservStmt=con.prepareStatement
						("SELECT client.id_client,reservation.id_reservation,nom,prenom "
								+ "FROM reservation "
								+ "INNER JOIN client on reservation.id_client=client.id_client "
								+ "WHERE  id_reservation=?"
						);
				reservStmt.setString(1, idRes);
				ResultSet rs = reservStmt.executeQuery();

				if(rs.next()) {
				String idc =rs.getString(1);
				String idr =rs.getString(2);
				String nom =rs.getString(3);
				String prenom =rs.getString(4);

				idClient = rs.getString(1);
				idReservé = rs.getString(2);
				clinom = rs.getString(3);
				cliprenom = rs.getString(4);

				 
				       
				}
				else
				JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe non valide");
				con.close();
				}catch(Exception e1){System.out.print(e1);
				}
			}
		});
		btnAssignerChambre.setBounds(390, 22, 183, 23);
		contentPane.add(btnAssignerChambre);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AppGestion frame = new AppGestion("role","nom");
				frame.setVisible(true);
				
			}
		});
		btnRetour.setBounds(594, 22, 113, 23);
		contentPane.add(btnRetour);
		
		JLabel lblStatut = new JLabel("Statut:");
		lblStatut.setBounds(31, 314, 86, 14);
		contentPane.add(lblStatut);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("Confirmé");
		 comboBox.addItem("Non Confirmé");
		comboBox.setBounds(179, 311, 98, 20);
		contentPane.add(comboBox);
	
}	
	
	/////////////////////////////////////////////////fonction showTable qui permet de faire apparaitre la table des reservations de la bdd///////////////////////////////////////
	public static void showTablereserv(){
		Connectdb data=new Connectdb();
		try {
			Connection con;
			con=data.db_connect();
			java.sql.PreparedStatement reservStmt=con.prepareStatement(
					"SELECT id_reservation AS '#',dateArr AS 'Date d_arrivée',"
					+ "dateDep AS 'Date de départ',nbrPersonne AS 'Nombre de personne',"
					+ "id_client AS 'id_Client',dateReservation AS 'Date de Réservation',"
					+ "id_categorie AS 'Categorie' FROM reservation ");
		    ResultSet rs=reservStmt.executeQuery();
			ReservationTable.setModel(DbUtils.resultSetToTableModel(rs));
			}
		catch(Exception e) {
			System.out.print(e);
		}

}
}