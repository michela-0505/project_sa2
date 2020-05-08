package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.*;

public class TestLogin extends JFrame {
 
	private JFrame frame;
	private JPanel contentPane;
    private JTextField txtUsername;
	public String priviledge;//créé  2 variables priviledge et myname
	public String myname;
	private JButton btnCnx;
	private JLabel alertUsername;
	private JLabel alertPassword;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestLogin frame = new TestLogin();
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
	public TestLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUtilisateur = new JLabel("Mon Paradis");
		lblUtilisateur.setForeground(Color.WHITE);
		lblUtilisateur.setBackground(Color.WHITE);
		lblUtilisateur.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 22));
		lblUtilisateur.setBounds(205, 51, 183, 35);
		contentPane.add(lblUtilisateur);
		
		JLabel lblUsername = new JLabel("Nom d'utilisateur:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(44, 85, 151, 42);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Mot de passe:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(48, 162, 151, 38);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(215, 98, 129, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
	
		
		/////////////////////////////////validation de bouton////////////////////////
		
		btnCnx = new JButton("Connexion");
		btnCnx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username =txtUsername.getText();
				String password=passwordField.getText();
				
				
				///////partie validation/////
             if(username.trim().length()==0 && password.trim().length()==0) {
            	 /////si les deux champs username et mot de passe n'ont pas de caractere//
					
					JOptionPane.showMessageDialog(null, "Les deux champs sont vides, veuillez les remplir!");              
					alertUsername.setText("Remplissez le champs utilisateur svp!");
					alertPassword.setText("Entrer votre mot de passe!");
					
				}
				
				else if (username.trim().length()==0){
					alertUsername.setText("Username is empty!");
					
				}
				else if(password.trim().length()==0) {
					alertPassword.setText("Password is empty!");
				}
				else {
					//JOptionPane.showMessageDialog(null, "Validation OK");
				Connectdb data=new Connectdb();
					try{    
						Connection con=data.db_connect();////variable data de type connexion fait appel a la methode db_connect
						java.sql.PreparedStatement stmt=con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
						stmt.setString(1,username);
						stmt.setString(2,password);
					    ResultSet rs= stmt.executeQuery();
			           if(rs.next()) {
			        	   
			        	   
			        	   //String nom=rs.getString(1);
			        	  //System.out.print(nom);
	
			        	   ///pour faire un choix entre utilisateur ou administrateur
			        	   
			        	   
			        	   String role=rs.getString(4);////ligne de colonne de la base de donnee
			        	    priviledge = role;
			        	    
			        	    String prenom= rs.getString(2);////pour avoir le prenom de la personne de la base de donnee 
			        	    JOptionPane.showMessageDialog(null, "Bienvenue" +   prenom);
			        	    myname = prenom;
			        	    
			        	    
			        	    frame.dispose();
			        	    AppGestion welcome= new AppGestion(priviledge,myname);////le dashboard de l'AppGestion de type welcome
			        	    welcome.setVisible(true);		   
			        			
					}
					else {
						JOptionPane.showMessageDialog(null,"not in database");
					}
						}
					
					catch(Exception error) {
						
						
					System.out.print(error);
						
						}
					
				}
       AppGestion frame = new AppGestion();////////////////////////////////////////////////Cette fonction permet de relier les interfaces///////////
       frame.setVisible(true);////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
			
		});
		btnCnx.setBounds(95, 331, 140, 50);
		contentPane.add(btnCnx);
		
		alertUsername = new JLabel("");
		alertUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				alertUsername.setText("");
			}
		});
		alertUsername.setBounds(138, 83, 61, 14);
		contentPane.add(alertUsername);
		
		alertPassword = new JLabel("");
		alertPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				alertPassword.setText("");
			}
		});
		alertPassword.setBounds(148, 138, 61, 14);
		contentPane.add(alertPassword);
		
		
		
		
		
		/////////////////////////////////////////validation exit////////////////////////////////
		JButton btnExit = new JButton("D\u00E9connexion");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(354, 331, 156, 50);
		contentPane.add(btnExit);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Michela\\Downloads\\sa2 photo\\sunset-pool_1203-3192.jpg"));
		lblNewLabel.setBounds(20, 0, 617, 442);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(48, 0, -53, 21);
		contentPane.add(panel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(215, 173, 129, 21);
		contentPane.add(passwordField);
	}
}
