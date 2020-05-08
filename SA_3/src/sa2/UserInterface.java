package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserInterface extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrenom;
	private JTextField txtRole;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtNom;
	private static JTable userTable;
	private JComboBox<String> comboBox,comboBoxSearch;
	private JTextField txtId;
	private JTextField txtSearchbox;
	public String filterSearch;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
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
	public UserInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUtilisateur.setBounds(227, 13, 92, 25);
		contentPane.add(lblUtilisateur);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(12, 118, 56, 16);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(12, 158, 56, 16);
		contentPane.add(lblPrenom);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(12, 203, 56, 16);
		contentPane.add(lblRole);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 240, 75, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Mot De Passe");
		lblPassword.setBounds(12, 273, 82, 22);
		contentPane.add(lblPassword);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(102, 114, 140, 25);
		contentPane.add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(102, 154, 140, 25);
		contentPane.add(txtPrenom);
		
		txtRole = new JTextField();
		txtRole.setColumns(10);
		txtRole.setBounds(102, 199, 140, 25);
		contentPane.add(txtRole);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(102, 237, 140, 22);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(101, 272, 141, 25);
		contentPane.add(txtPassword);
		
/////////////////////////////////////Ajouter//////////////////////////////////////////////////////////////
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//step 1 collect info from txtfield
				String id = txtId.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String role = txtRole.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				//String value = comboBox.getSelectedItem().toString();
			
			System.out.println(id);
			System.out.println(nom);
			System.out.println(prenom);
			System.out.println(role);
			System.out.println(username);
			System.out.println(password);
			//System.out.println(value);
			
			if(id.trim().length()==0 && nom.trim().length()==0 && prenom.trim().length()==0 && role.trim().length()==0 && username.trim().length()==0 && password.trim().length()==0 ) {
				
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("INSERT INTO user (prenom,nom,role,username,password) VALUES (?,?,?,?,?)");
					
					stmt1.setString(1, prenom);
					stmt1.setString(2, nom);
					stmt1.setString(3, role);
					stmt1.setString(4, username);
					stmt1.setString(5, password);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Mise a jour effectuer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					System.out.print(e1);
					
				}
			}
				
			}
		});
		btnAjouter.setBounds(49, 437, 97, 35);
		contentPane.add(btnAjouter);
		
/////////////////////////////////////Effacer//////////////////////////////////////////////////////////////
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//step 1 collect info from txtfield
				String id = txtId.getText();
				
			
			System.out.println(id);
			
			
			if(id.trim().length()==0 ) {
				
				JOptionPane.showMessageDialog(null,"Remplissez le champs");
				
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("DELETE FROM user  WHERE id_user = ? ");
					stmt1.setString(1, id);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Donneé effacer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					
				}
			}
		}
	});
		btnEffacer.setBounds(335, 437, 109, 35);
		contentPane.add(btnEffacer);
		
		
		
////////////////permet pour pa faire errer ler pe rentre role e affiche le role kan click lor nom///////////////////////
		 comboBox = new JComboBox();
		 comboBox.addItem("Administrateur");
		 comboBox.addItem("Utilisateur");
		 comboBox.setBounds(96, 320, 175, 25);
		 comboBox.setEditable(false); ////////faire Editable pa click/////
		 contentPane.add(comboBox);
		
	
/////////////////////////////////////Modifier//////////////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//step 1 collect info from txtfield
				String id = txtId.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String role = txtRole.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				String value = comboBox.getSelectedItem().toString();
			
			System.out.println(id);
			System.out.println(nom);
			System.out.println(prenom);
			System.out.println(role);
			System.out.println(username);
			System.out.println(password);
			System.out.println(value);
			
			if(id.trim().length()==0 && nom.trim().length()==0 && prenom.trim().length()==0 && role.trim().length()==0 && username.trim().length()==0 && password.trim().length()==0 && value.trim().length()==0) {
				
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("UPDATE user  SET prenom=?,nom=?,role=?,username=?,password=? WHERE id_user=?");
					stmt1.setString(6, id);
					stmt1.setString(1, prenom);
					stmt1.setString(2, nom);
					stmt1.setString(3, role);
					stmt1.setString(4, username);
					stmt1.setString(5, password);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modification effectuer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					
				}
			}
		
			}
		});
		btnModifier.setBounds(191, 437, 97, 35);
		contentPane.add(btnModifier);
		
/////////////////////////////////////Reinitialiser//////////////////////////////////////////////////////////////
JButton btnReinitialiser = new JButton("Reinitialiser");
btnReinitialiser.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) {

//remet tout les field a null
txtId.setText(null);
txtPrenom.setText(null);
txtNom.setText(null);
txtRole.setText(null);
txtUsername.setText(null);
txtPassword.setText(null);
comboBox.setSelectedItem(null);

}
});

btnReinitialiser.setBounds(484, 437, 109, 35);
contentPane.add(btnReinitialiser);

		
		JScrollPane scrollPane = new JScrollPane();
		
		
		scrollPane.setBounds(283, 78, 369, 313);
		contentPane.add(scrollPane);
		
		
		userTable = new JTable() {
		
		//////////////////////Make Rows Not Editable Step 1////////////////////////
		
		public boolean isCellEditable(int row, int colunm) {
			return false;
		}
	};
	

	//////////////////Pour afficher les valeur en clickant////////////////////////////
	
	userTable.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Connectdb data = new Connectdb();
			try {
				int row = userTable.getSelectedRow();
				
//				System.out.print(row);
				
				String tableClick = (userTable.getModel().getValueAt(row,0).toString()); ///0=colunm 
//				System.out.print(tableClick);
				Connection con;
				con = (Connection) data.db_connect();
				java.sql.PreparedStatement callinfo = con.prepareStatement("SELECT * FROM user where id_user='"+tableClick+"'");
				ResultSet rs = callinfo.executeQuery();
				if(rs.next()) {
					String data1 = rs.getString("id_user");
					String data2 = rs.getString("prenom");
					String data3 = rs.getString("nom");
					String data4 = rs.getString("role");
					String data5 = rs.getString("username");
					String data6 = rs.getString("password");
					
					System.out.println(data1);
					System.out.println(data2);
					System.out.println(data3);
					System.out.println(data4);
					System.out.println(data5);
					System.out.println(data6);
					System.out.println("_____");
					
					txtId.setText(data1);
					txtPrenom.setText(data2);
					txtNom.setText(data3);
					txtRole.setText(data4);
					txtUsername.setText(data5);
					txtPassword.setText(data6);
					comboBox.setSelectedItem(data4);
				}
				
			} catch(Exception e1) {
				
				System.out.print(e1);
			}
			
			
		}
	});
	
	scrollPane.setViewportView(userTable);
	
	JLabel lblId = new JLabel("Id");
	lblId.setBounds(12, 78, 56, 16);
	contentPane.add(lblId);
	
	txtId = new JTextField();
	txtId.setBounds(102, 75, 56, 22);
	contentPane.add(txtId);
	txtId.setColumns(10);
	
	
	
	////////////////////////////////////////SearchBox/////////////////////////////////////////////////
	txtSearchbox = new JTextField();
	txtSearchbox.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			
			Connectdb data = new Connectdb();
			
			try {
				String searchObject = txtSearchbox.getText();
				Connection con;
				con = (Connection) data.db_connect();
				java.sql.PreparedStatement userStmt = con.prepareStatement("SELECT id_user as'#',nom as'Nom',prenom as 'Prnom',role as 'Role',username as 'Username',password as 'Password' FROM user WHERE "+filterSearch+" LIKE?");
				userStmt.setString(1,"%"+ searchObject + "%");
				
				ResultSet rs = userStmt.executeQuery();
				userTable.setModel(DbUtils.resultSetToTableModel(rs));
			
			} catch (Exception e) {
				
				System.out.print(e);
			}
		}
	});
	txtSearchbox.setBounds(313, 43, 116, 22);
	contentPane.add(txtSearchbox);
	txtSearchbox.setColumns(10);
	
	comboBoxSearch = new JComboBox();
	comboBoxSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			Object selected = comboBoxSearch.getSelectedItem();
			if(selected.toString().equals("Nom"))
				filterSearch = "nom";
			if(selected.toString().equals("Prénom"))
				filterSearch = "prenom";
			
		}
	});
	
//////////////les menu du comboBoxSearch/////////////////////////////////
	comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[]
			{"","Nom","Prénom"}
	));
	
	comboBoxSearch.setBounds(472, 42, 131, 25);
	contentPane.add(comboBoxSearch);
	
	}
	
	public static void showTable() {
		
		Connectdb data = new Connectdb();
		
		try {
			Connection con;
			con = (Connection) data.db_connect();
			java.sql.PreparedStatement userStmt = con.prepareStatement("SELECT id_user as'#',nom as'Nom',prenom as 'Prénom',role as 'Role',username as 'Username' FROM user");
			ResultSet rs = userStmt.executeQuery();
			userTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			
			System.out.print(e);
		}
		
	}
}
