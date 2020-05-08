package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;

public class InterfaceChambre extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtTarif;
	private JComboBox <String> comboBox1,combostatut,comboBoxSearch;
	private JTextField txtsearch;
	public String filterCriteria;
	private JTextField txtId;
	private static JTable chambretable;
	private JTextField txtCapacite;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceChambre frame = new InterfaceChambre();
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
	public InterfaceChambre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(22, 242, 56, 16);
		contentPane.add(lblType);
		
		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setBounds(22, 329, 56, 16);
		contentPane.add(lblTarif);
		
		JLabel lblCapacite = new JLabel("Capacite");
		lblCapacite.setBounds(22, 293, 56, 16);
		contentPane.add(lblCapacite);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(22, 154, 56, 16);
		contentPane.add(lblId);
		
		txtNom = new JTextField();
		txtNom.setBounds(121, 186, 116, 22);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtTarif = new JTextField();
		txtTarif.setColumns(10);
		txtTarif.setBounds(121, 326, 116, 22);
		contentPane.add(txtTarif);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(121, 151, 116, 22);
		contentPane.add(txtId);
		

		////////////// capacite field//////////////////
		
		txtCapacite = new JTextField();
		txtCapacite.setColumns(10);
		txtCapacite.setBounds(121, 290, 116, 22);
		contentPane.add(txtCapacite);
		
		
		//////////////////Supprimer////////////////////
		JButton btnsupprimer = new JButton("Supprimer");
		btnsupprimer.setBackground(new Color(192, 192, 192));
		btnsupprimer.addActionListener(new ActionListener() {
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
					java.sql.PreparedStatement stmt1 = con.prepareStatement("DELETE FROM chambre  WHERE id_chambre = ? ");
					
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
		
		
		btnsupprimer.setBounds(81, 424, 97, 37);
		contentPane.add(btnsupprimer);
		
		//////////////////Update/////////////////////////
		JButton btnUpdate = new JButton("Mis-a-jour");
		btnUpdate.setBackground(new Color(192, 192, 192));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//step 1 collect info from txtfield
				String id = txtId.getText();
				String nom = txtNom.getText();
				String tarif = txtTarif.getText();
				String capacité = txtCapacite.getText();
				String value = comboBox1.getSelectedItem().toString();
				String value1 = combostatut.getSelectedItem().toString();
			
			System.out.println(id);
			System.out.println(nom);
			System.out.println(tarif);
			System.out.println(capacité);
			System.out.println(value);
			System.out.println(value1);
			
			if(id.trim().length()==0 && nom.trim().length()==0 && tarif.trim().length()==0 && capacité.trim().length()==0 &&value.trim().length()==0 &&value1.trim().length()==0 ) {
				
				JOptionPane.showMessageDialog(null,"Remplissez tous les champs");
				
			}
			else {
				Connectdb data = new Connectdb();
				try {
					Connection con;
					con = (Connection) data.db_connect();
					java.sql.PreparedStatement stmt1 = con.prepareStatement("UPDATE chambre INNER JOIN categorie ON chambre.id_categorie=categorie.id_categorie   SET tarif=?,nom=?,type=?,Capacité=?,Statue=? WHERE id_chambre=?");
					
					stmt1.setString(1, tarif);
					stmt1.setString(2, nom);
					stmt1.setString(3, value);
					stmt1.setString(6, id);
					stmt1.setString(4, capacité);
					stmt1.setString(5, value1);
					stmt1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modification effectuer");
					showTable(); //////Reexecute le code qui fait apparaisse la table(Refresh)////////
					
					
					
				}catch(Exception e1) {
					System.out.print(e1);
					
				}
			}
				
			}
		});
		
		btnUpdate.setBounds(301, 424, 97, 37);
		contentPane.add(btnUpdate);
		
		JButton btnReinitialiser = new JButton("Reinitialiser");
		btnReinitialiser.setBackground(new Color(192, 192, 192));
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtId.setText (null);
				txtNom.setText (null);
				txtTarif.setText (null);
				txtCapacite.setText (null);
				comboBox1.setSelectedItem(null);
				combostatut.setSelectedItem(null);
			}
		});
		btnReinitialiser.setBounds(507, 424, 116, 37);
		contentPane.add(btnReinitialiser);
		
		
		
		comboBox1 = new JComboBox();
		comboBox1.addItem("");
		comboBox1.addItem("simple");
		comboBox1.addItem("double");
		comboBox1.addItem("double comfort");
		comboBox1.addItem("suite");
		comboBox1.setBounds(121, 239, 116, 22);
		comboBox1.setEditable(false);
		contentPane.add(comboBox1);
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				Connectdb data = new Connectdb();
				try { 
			    String searchObject = txtsearch.getText();
				java.sql.Connection con;
				con=data.db_connect();
				PreparedStatement stmt =(PreparedStatement) con.prepareStatement("SELECT id_chambre as '#',nom as 'Nom',type as 'Type', tarif as 'Tarif', capacité as 'Capacite', statut as 'Statut'  from chambre inner join categorie on chambre.id_categorie = categorie.id_categorie WHERE "+filterCriteria+" LIKE?");
				stmt.setString(1,  "%" + searchObject + "%");
				
				ResultSet rs = stmt.executeQuery();
				chambretable.setModel(DbUtils.resultSetToTableModel(rs));
				
				}catch(Exception e1) {
					
					System.out.print(e1);
				}
			}
		});
		
		
		txtsearch.setBounds(457, 88, 129, 22);
		contentPane.add(txtsearch);
		txtsearch.setColumns(10);
		
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object selected=comboBoxSearch.getSelectedItem();
				if (selected.toString().contentEquals("Nom"))
					filterCriteria="nom";
				if (selected.toString().contentEquals("Type"))
					filterCriteria="type";
			}
		});
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[] 
				{"","Nom","Type"}
				));
		
		comboBoxSearch.setBounds(619, 85, 178, 25);
		contentPane.add(comboBoxSearch);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 129, 527, 280);
		contentPane.add(scrollPane);
		
		chambretable = new JTable(){
			
			///////////////////////make rows not editable step 1//////
				
				public boolean isCellEditable (int row, int column) {
					return false;
				}
				
			};
			
			//////////////make rows clickable step 2///////////////////////
			
			chambretable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					try { 
						int row = chambretable.getSelectedRow();
						
						///// pour savoir si la methode marche (console)////
						//System.out.print(row);                        //
						///////////////////////////////////////////////////
						
						String tableClick =(chambretable.getModel().getValueAt(row, 0).toString());
						
						
					//	System.out.print(tableClick);
						Connectdb data= new Connectdb();
						Connection con;
						con =(Connection) data.db_connect();
						java.sql.PreparedStatement callInfo=con.prepareStatement("SELECT * FROM chambre INNER JOIN categorie ON chambre.id_categorie=categorie.id_categorie WHERE id_chambre='"+tableClick+"'");
						ResultSet rs = callInfo.executeQuery();
						if(rs.next()) {
							
							String data1 = rs.getString("id_chambre");
							String data2 = rs.getString("nom");
							String data3 = rs.getString("type");
							String data4 = rs.getString("tarif");
							String data5 = rs.getString("capacité");
							String data6 = rs.getString("statut");
								                     
							System.out.println(data1);
							System.out.println(data2);
							System.out.println(data3);
							System.out.println(data4);
							System.out.println(data5);
							System.out.println(data6);
							System.out.println("____");
							
							txtNom.setText(data2);
							txtId.setText(data1);
							txtTarif.setText(data4);
							txtCapacite.setText(data5);
							comboBox1.setSelectedItem(data3);
							combostatut.setSelectedItem(data6);
						}
						
					}catch(Exception e1) {
						
						System.out.print(e1);
					}
					
					
				}
			});
		scrollPane.setViewportView(chambretable);
		
		
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBackground(new Color(192, 192, 192));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnRetour.setBounds(713, 424, 116, 37);
		contentPane.add(btnRetour);
		
		combostatut= new JComboBox();
		combostatut.addItem("");
		combostatut.addItem("Libre");
		combostatut.addItem("Occuper");
		combostatut.addItem("En maintenance");
		combostatut.setEditable(false);
		combostatut.setBounds(121, 370, 116, 22);
		contentPane.add(combostatut);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setBounds(22, 373, 56, 16);
		contentPane.add(lblStatut);
		
		JLabel lblNewLabel = new JLabel("Gestion de Chambres");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(364, 24, 267, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search:");
		lblNewLabel_1.setBounds(378, 92, 56, 18);
		contentPane.add(lblNewLabel_1);
		}
	
	public static void showTable() {
		Connectdb data= new Connectdb();
		
		try {
			Connection con;
			con =(Connection) data.db_connect();
			java.sql.PreparedStatement userStmt=con.prepareStatement("SELECT id_chambre as '#',nom as 'Nom',type as 'Type', tarif as 'Tarif', capacité as 'Capacite',statut as 'Statut' from chambre inner join categorie on chambre.id_categorie = categorie.id_categorie");
			ResultSet rs = userStmt.executeQuery();
			chambretable.setModel(DbUtils.resultSetToTableModel(rs));
			
		}catch (Exception e) {
			System.out.print(e);
		}
	
}
}
