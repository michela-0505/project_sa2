package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class InterfaceClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JTextField txtTelephone;
	private JTextField txtPays;
	private JComboBox<String> comboBox;
	private static JTable clientTable;
//	private JTable clientTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceClient frame = new InterfaceClient();
					frame.setVisible(true);
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 864, 546);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClient = new JLabel("Gestion des Clients");
		lblClient.setForeground(new Color(46, 139, 87));
		lblClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblClient.setBounds(354, 15, 181, 22);
		contentPane.add(lblClient);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(12, 54, 56, 16);
		contentPane.add(lblId);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNom.setBounds(12, 99, 56, 16);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom:");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrenom.setBounds(12, 144, 56, 16);
		contentPane.add(lblPrenom);
		
		JLabel lblSexe = new JLabel("Sexe:");
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSexe.setBounds(12, 191, 56, 16);
		contentPane.add(lblSexe);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAge.setBounds(12, 242, 56, 16);
		contentPane.add(lblAge);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(12, 289, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelephone.setBounds(12, 337, 79, 16);
		contentPane.add(lblTelephone);
		
		JLabel lblPays = new JLabel("Pays:");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPays.setBounds(12, 389, 56, 16);
		contentPane.add(lblPays);
		
		txtId = new JTextField();
		txtId.setBounds(140, 51, 116, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(140, 96, 116, 22);
		contentPane.add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(140, 141, 116, 22);
		contentPane.add(txtPrenom);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(140, 239, 116, 22);
		contentPane.add(txtAge);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(140, 286, 116, 22);
		contentPane.add(txtEmail);
		
		txtTelephone = new JTextField();
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(140, 334, 116, 22);
		contentPane.add(txtTelephone);
		
		txtPays = new JTextField();
		txtPays.setColumns(10);
		txtPays.setBounds(140, 386, 116, 22);
		contentPane.add(txtPays);
		
/////////////////////////////////////Nouveau//////////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("Nouveau");
		btnNewButton.setBackground(new Color(46, 139, 87));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//step 1 collect info from txtfield
				String id = txtId.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String age = txtAge.getText();
				String email = txtEmail.getText();
				String telephone = txtTelephone.getText();
				String pays = txtPays.getText();
				String value = comboBox.getSelectedItem().toString();
			
			System.out.println(id);
			System.out.println(nom);
			System.out.println(prenom);
			System.out.println(age);
			System.out.println(email);
			System.out.println(telephone);
			System.out.println(pays);
			System.out.println(value);
			
			if(id.trim().length()==0 && nom.trim().length()==0 && prenom.trim().length()==0 && age.trim().length()==0 && email.trim().length()==0 && telephone.trim().length()==0 && pays.trim().length()==0 && value.trim().length()==0 ) {
				
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("INSERT INTO client (prenom,nom,age,sexe,email,phone,pays_origine) VALUES (?,?,?,?,?,?,?)");
					
					stmt1.setString(1, prenom);
					stmt1.setString(2, nom);
					stmt1.setString(3, age);
					stmt1.setString(4, value);
					stmt1.setString(5, email);
					stmt1.setString(6, telephone);
					stmt1.setString(7, pays);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Mise a jour effectuer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					System.out.print(e1);
					
				}
			}
				
			}
		});
			
		btnNewButton.setBounds(50, 450, 97, 36);
		contentPane.add(btnNewButton);
		
/////////////////////////////////////Modifier//////////////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(46, 139, 87));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//step 1 collect info from txtfield
				String id = txtId.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String age = txtAge.getText();
				String email = txtEmail.getText();
				String telephone = txtTelephone.getText();
				String pays = txtPays.getText();
				String value = comboBox.getSelectedItem().toString();
			
			System.out.println(id);
			System.out.println(nom);
			System.out.println(prenom);
			System.out.println(age);
			System.out.println(email);
			System.out.println(telephone);
			System.out.println(pays);
			System.out.println(value);
			
			if(id.trim().length()==0 && nom.trim().length()==0 && prenom.trim().length()==0 && age.trim().length()==0 && email.trim().length()==0 && telephone.trim().length()==0 && pays.trim().length()==0 && value.trim().length()==0 ) {
				
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("UPDATE client  SET prenom=?,nom=?,age=?,sexe=?,email=?,phone=?,pays_origine=? WHERE id_client=?");
					
					stmt1.setString(1, prenom);
					stmt1.setString(2, nom);
					stmt1.setString(3, age);
					stmt1.setString(4, value);
					stmt1.setString(5, email);
					stmt1.setString(6, telephone);
					stmt1.setString(7, pays);
					stmt1.setString(8, id);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modification effectuer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					System.out.print(e1);
					
				}
			}
				
			}
		});
		btnModifier.setBounds(198, 450, 97, 36);
		contentPane.add(btnModifier);
		
/////////////////////////////////////Effacer//////////////////////////////////////////////////////////////
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setBackground(new Color(46, 139, 87));
		btnEffacer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				//step 1 collect info from txtfield
				String id = txtId.getText();
				
			
			System.out.println(id);
			
			
			if(id.trim().length()==0 ) {
				
				JOptionPane.showMessageDialog(null,"Remplit le champ #");
				
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("DELETE FROM client  WHERE id_client = ? ");
					
					stmt1.setString(1, id);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Donnée effacer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					System.out.print(e1);
					
				}
			}
			}
		});
		btnEffacer.setBounds(344, 450, 97, 36);
		contentPane.add(btnEffacer);
		
/////////////////////////////////////Reinitialiser//////////////////////////////////////////////////////////////
		JButton btnReinitialiser = new JButton("Reinitialiser");
		btnReinitialiser.setBackground(new Color(46, 139, 87));
		btnReinitialiser.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//remet tout les field a null
				txtId.setText(null);
				txtPrenom.setText(null);
				txtNom.setText(null);
				txtAge.setText(null);
				txtEmail.setText(null);
				txtTelephone.setText(null);
				txtPays.setText(null);
				comboBox.setSelectedItem(null);
			}
		});
		btnReinitialiser.setBounds(496, 450, 124, 36);
		contentPane.add(btnReinitialiser);
		
////////////////permet pour pa faire errer ler pe rentre role e affiche le role kan click lor nom///////////////////////
		comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("F");
		 comboBox.addItem("M");
		comboBox.setBounds(140, 188, 124, 22);
		 comboBox.setEditable(false); ////////faire Editable pa click/////
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(276, 48, 558, 372);
		contentPane.add(scrollPane);
		
		
		
		clientTable = new JTable() {
//////////////////////Make Rows Not Editable Step 1////////////////////////
			
	public boolean isCellEditable(int row, int colunm) {
		return false;
	}

		};
		
//////////////////Pour afficher les valeur en clickant////////////////////////////
clientTable.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		Connectdb data = new Connectdb();
		try {
			int row = clientTable.getSelectedRow();
			
//			System.out.print(row);
			
			String tableClick = (clientTable.getModel().getValueAt(row,0).toString()); ///0=colunm 
//			System.out.print(tableClick);
			Connection con;
			con = (Connection) data.db_connect();
			java.sql.PreparedStatement callinfo = con.prepareStatement("SELECT * FROM client"
					+ " WHERE id_client='"+tableClick+"'");
			ResultSet rs = callinfo.executeQuery();
			if(rs.next()) {
				String data1 = rs.getString("id_client");
				String data2 = rs.getString("nom");
				String data3 = rs.getString("prenom");
				String data4 = rs.getString("sexe");
				String data5 = rs.getString("age");
				String data6 = rs.getString("email");
				String data7 = rs.getString("phone");
				String data8 = rs.getString("pays_origine");
				
				System.out.println(data1);
				System.out.println(data2);
				System.out.println(data3);
				System.out.println(data4);
				System.out.println(data5);
				System.out.println(data6);
				System.out.println(data7);
				System.out.println(data8);
				System.out.println("_____");
				
				txtId.setText(data1);
				txtPrenom.setText(data2);
				txtNom.setText(data3);
				comboBox.setSelectedItem(data4);
				txtAge.setText(data5);
				txtEmail.setText(data6);
				txtTelephone.setText(data7);
				txtPays.setText(data8);
				
			}
			
		} catch(Exception e1) {
			
			System.out.print(e1);
		}
		
	}
});
		scrollPane.setViewportView(clientTable);
		
	 
	}	
		
		/////////////////Pour Afficher la base de donner////////////////////////////
	
	
	public static void showTable() {
		
		Connectdb data = new Connectdb();
		
		try {
			Connection con;
			con = (Connection) data.db_connect();
			java.sql.PreparedStatement userStmt = con.prepareStatement("SELECT id_client AS'#',nom AS'Nom',prenom AS 'Prénom',sexe AS 'Sexe',age AS 'Age',email AS 'Email',phone AS 'Telephone',pays_origine AS 'Pays' FROM client");
			ResultSet rs = userStmt.executeQuery();
			clientTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			
			System.out.print(e);
		}
		
	}
}
