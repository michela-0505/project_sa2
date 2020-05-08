package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;


public class InterReservation extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JTextField txtTel;
	private JTextField txtPays;
	private JTextField txtnbPersonne;
	private JComboBox comboCategorie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterReservation frame = new InterReservation();
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
	public InterReservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFormReserv = new JLabel("Formulaire de r\u00E9servation");
		lblFormReserv.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFormReserv.setBounds(253, 11, 216, 14);
		contentPane.add(lblFormReserv);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(29, 84, 103, 14);
		contentPane.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom:");
		lblPrnom.setBounds(29, 109, 103, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblSexe = new JLabel("Sexe:");
		lblSexe.setBounds(29, 134, 103, 14);
		contentPane.add(lblSexe);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(29, 159, 103, 14);
		contentPane.add(lblAge);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(29, 193, 103, 14);
		contentPane.add(lblEmail);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone:");
		lblTlphone.setBounds(29, 218, 115, 14);
		contentPane.add(lblTlphone);
		
		JLabel lblPaysorigine = new JLabel("Pays_Origine:");
		lblPaysorigine.setBounds(29, 255, 115, 14);
		contentPane.add(lblPaysorigine);
		
		JLabel lblDateDarrive = new JLabel("Date d'arriv\u00E9e:");
		lblDateDarrive.setBounds(339, 84, 115, 14);
		contentPane.add(lblDateDarrive);
		
		JLabel lblDateDeDpart = new JLabel("Date de d\u00E9part:");
		lblDateDeDpart.setBounds(339, 109, 115, 14);
		contentPane.add(lblDateDeDpart);
		
		JLabel lblDateDeRservation = new JLabel("Date de r\u00E9servation:");
		lblDateDeRservation.setBounds(339, 140, 115, 14);
		contentPane.add(lblDateDeRservation);
		
		JLabel lblNombreDePersonne = new JLabel("Nombre de personne:");
		lblNombreDePersonne.setBounds(339, 180, 130, 14);
		contentPane.add(lblNombreDePersonne);
		
		JLabel lblI = new JLabel("Id_categorie:");
		lblI.setBounds(339, 218, 115, 14);
		contentPane.add(lblI);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(107, 81, 146, 20);
		contentPane.add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(107, 106, 146, 20);
		contentPane.add(txtPrenom);
		
		JComboBox comboSexe = new JComboBox();
		comboSexe.addItem("");
		comboSexe.addItem("Masculin");
		comboSexe.addItem("Féminin");
		comboSexe.setBounds(107, 134, 146, 20);
		contentPane.add(comboSexe);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(107, 159, 146, 20);
		contentPane.add(txtAge);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(107, 190, 146, 20);
		contentPane.add(txtEmail);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(107, 218, 146, 20);
		contentPane.add(txtTel);
		
		txtPays = new JTextField();
		txtPays.setColumns(10);
		txtPays.setBounds(107, 252, 146, 20);
		contentPane.add(txtPays);
		
		txtnbPersonne = new JTextField();
		txtnbPersonne.setColumns(10);
		txtnbPersonne.setBounds(488, 177, 133, 20);
		contentPane.add(txtnbPersonne);
		
		comboCategorie = new JComboBox();
		comboCategorie.addItem("");
		comboCategorie.addItem("1");
		comboCategorie.addItem("2");
		comboCategorie.addItem("3");
		comboCategorie.addItem("4");
		comboCategorie.addItem("");
        comboCategorie.setBounds(488, 215, 133, 20);
		contentPane.add(comboCategorie);
		
		JDateChooser dateChooserdateArr = new JDateChooser();
		dateChooserdateArr.setDateFormatString("yyyy-MM-dd");
		dateChooserdateArr.setBounds(488, 84, 133, 20);
		contentPane.add(dateChooserdateArr);
		
		JDateChooser dateChooserDateDep = new JDateChooser();
		dateChooserDateDep.setDateFormatString("yyyy-MM-dd");
		dateChooserDateDep.setBounds(488, 109, 133, 20);
		contentPane.add(dateChooserDateDep);
		
		JDateChooser dateChooserDateReservation = new JDateChooser();
		dateChooserDateReservation.setDateFormatString("yyyy-MM-dd");
		dateChooserDateReservation.setBounds(488, 140, 133, 20);
		contentPane.add(dateChooserDateReservation);
		
		
		/////////////////////////////////Enregistrer reservation et client/////////////////
		JButton btnInsert = new JButton("Enr\u00E9gistrer");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//step 1 collect info from txtfield
			    String nom = txtNom.getText();
			    String prenom = txtPrenom.getText();
			    String email = txtEmail.getText();
			    String phone = txtTel.getText();
			    String dateArr = ((JTextField)dateChooserdateArr.getDateEditor().getUiComponent()).getText();
			    String dateDep = ((JTextField)dateChooserDateDep.getDateEditor().getUiComponent()).getText();
				String nbrPersonne = txtnbPersonne.getText();
				String dateReservation =((JTextField) dateChooserDateReservation.getDateEditor().getUiComponent()).getText();
			
				
				
//				    System.out.println(nom);
//				    System.out.println(prenom);
//				    System.out.println(email);
//				    System.out.println(phone);
//				    System.out.println(dateArr);
//				    System.out.println(dateDep);
//				    System.out.println(nbrPersonne);
//					System.out.println(dateReservation);
//				
		
					if(nom.trim().length()==0 && prenom.trim().length()==0 && email.trim().length()==0 && phone.trim().length()==0 && dateArr.trim().length()==0 && dateDep.trim().length()==0 && nbrPersonne.trim().length()==0  &&  dateReservation.trim().length()==0 ) {
						
						JOptionPane.showMessageDialog(null,"Remplissez tous les champs");
						
					}
				
				
				else {
						Connectdb data = new Connectdb();
						try {
							Connection con;
						
							con = (Connection) data.db_connect();
							String id;
							String sexe = null;
							String age=null;
							String pays_origine=null;
						
							java.sql.PreparedStatement reservstmt = con.prepareStatement
									("INSERT INTO client(nom,prenom,sexe,age,email,phone,pays_origine) "
											+ "VALUES (?,?,?,?,?,?,?)",
											Statement.RETURN_GENERATED_KEYS);
							
							reservstmt.setString(2,nom);
							reservstmt.setString(3, prenom);
							reservstmt.setString(4, sexe);
							reservstmt.setString(5, age);
							reservstmt.setString(6, email);
							reservstmt.setString(7,phone);
							reservstmt.setString(8, pays_origine);
							reservstmt.executeUpdate();
							
							ResultSet rs=reservstmt.getGeneratedKeys();
							if(rs.next()) {
								
								id=rs.getString(1);
								sexe=rs.getString(4);
								age=rs.getString(5);
								pays_origine=rs.getString(8);
							
								String id_client = null;
								String id_categorie = null ;
						        PreparedStatement stmt = con.prepareStatement
								("INSERT INTO reservation(id_client,id_categorie)VALUES (?,?)");
						
						        
								stmt.setString(5, id_client);
								stmt.setString(7, id_categorie);
								stmt.executeUpdate();		
								
								
								
								JOptionPane.showMessageDialog(null,"Votre réservation a bien été effectuer!");	
								
							}
							
							
							
						}catch(Exception e1) {
							
						}
					}
						
			}
		});
		btnInsert.setBounds(166, 304, 125, 42);
		contentPane.add(btnInsert);
		
		
		
		///////////////////////////////////////////////annuler reservation///////////////
		JButton btnExit = new JButton("Annuler");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(339, 304, 115, 42);
		contentPane.add(btnExit);
	}
}
