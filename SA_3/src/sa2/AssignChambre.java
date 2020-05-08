package sa2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AssignChambre extends JFrame {

/**
* 
*/
private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTextField txtIdReservation;
private JTextField txtIdClient;
private JTextField txtNomClient;
private JTextField txtprenomClient;
private JComboBox<String> disponibiliteChambre;
private JButton btnAssignerChambre;
private JTable tableConcerner;
private String idReserv�, idClient, clinom, cliprenom, id_chambre,id_reservation;
private JTextField txtIdChambre;
private JTextField txtNom;
 
/**
* Launch the application.
*/
    public static void main(String[] args) {
               EventQueue.invokeLater(new Runnable() {
              public void run() {
                      try {
                    	  /////////////////fini de declarer le parametre dans gestionReservation
                             AssignChambre frame = new AssignChambre("","","","");
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
public AssignChambre(String idReserv�,String idClient,String clinom,String cliprenom) {
setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
setBounds(100, 100, 865, 619);
contentPane = new JPanel();
contentPane.setBackground(Color.WHITE);
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

// System.out.println(idReserved);

JPanel panel = new JPanel();
panel.setBackground(Color.WHITE);
panel.setBounds(10, 11, 885, 569);
contentPane.add(panel);
panel.setLayout(null);

txtIdReservation = new JTextField();
txtIdReservation.setText(idReserv�);
txtIdReservation.setBounds(134, 85, 136, 27);
txtIdReservation.setFont(new Font("Tahoma", Font.BOLD, 11));
panel.add(txtIdReservation);
txtIdReservation.setColumns(10);

txtIdClient = new JTextField();
txtIdClient.setText(idClient);
txtIdClient.setFont(new Font("Tahoma", Font.BOLD, 11));
txtIdClient.setBounds(134, 123, 136, 27);
panel.add(txtIdClient);
txtIdClient.setColumns(10);

txtNomClient = new JTextField();
txtNomClient.setText(clinom);
txtNomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
txtNomClient.setBounds(134, 163, 136, 27);
panel.add(txtNomClient);
txtNomClient.setColumns(10);

txtprenomClient = new JTextField();
txtprenomClient.setText(cliprenom);
txtprenomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
txtprenomClient.setBounds(134, 207, 194, 27);
panel.add(txtprenomClient);
txtprenomClient.setColumns(10);

JLabel lblIdRservation = new JLabel("ID R\u00E9servation :");
lblIdRservation.setBounds(10, 78, 136, 39);
lblIdRservation.setFont(new Font("Arial", Font.BOLD, 14));
panel.add(lblIdRservation);

JLabel lblIdClient = new JLabel("ID Client :");
lblIdClient.setBounds(10, 128, 112, 14);
lblIdClient.setFont(new Font("Arial", Font.BOLD, 14));
panel.add(lblIdClient);

JLabel lblNom = new JLabel("Nom :");
lblNom.setBounds(12, 166, 112, 14);
lblNom.setFont(new Font("Arial", Font.BOLD, 14));
panel.add(lblNom);

JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
lblPrnom.setBounds(10, 207, 112, 14);
lblPrnom.setFont(new Font("Arial", Font.BOLD, 14));
panel.add(lblPrnom);
Connectdb data =new Connectdb();



disponibiliteChambre = new JComboBox<String>();
disponibiliteChambre.setBounds(205, 388, 145, 27);
         try {
               disponibiliteChambre.removeAllItems();

                Connection con;
                con = data.db_connect();
             PreparedStatement stmt = con.prepareStatement("SELECT nom FROM chambre WHERE statut='libre' ");
             ResultSet rs = stmt.executeQuery();
        while(rs.next())
       {
           this.disponibiliteChambre.addItem(rs.getString("nom"));
       }
con.close();
rs.close();
}catch(Exception e){}
panel.add(disponibiliteChambre);

btnAssignerChambre = new JButton("Assigner Cette Chambre");
btnAssignerChambre.setBackground(new Color(72,138,153));
btnAssignerChambre.addActionListener(new ActionListener() {
	
public void actionPerformed(ActionEvent arg0) {
	
////////////////Pour aller chercher les informations dans les tables
String idReservation =txtIdReservation.getText();
// String idClient =txtIdClient.getText();
String chambre =disponibiliteChambre.getSelectedItem().toString();
try {
Connection con;
con = data.db_connect();
PreparedStatement stmt = con.prepareStatement("SELECT * FROM chambre WHERE nom=?");
stmt.setString(1, chambre);
ResultSet rs = stmt.executeQuery();
if(rs.next()) {

String add1 =rs.getString("id_chambre");
id_chambre=add1;
}
PreparedStatement stmt2 = con.prepareStatement("INSERT INTO appartenir (id_chambre,id_reservation) VALUES(?,?)");
stmt2.setString(2, id_chambre);
stmt2.setString(3, id_reservation);
stmt2.executeUpdate();
showTable();
refreshRoomList();

}catch(Exception e) {System.out.print(e);};
System.out.print(id_chambre);

}

});
btnAssignerChambre.setBounds(134, 425, 179, 39);
panel.add(btnAssignerChambre);

JScrollPane scrollPane = new JScrollPane();
scrollPane.setBounds(399, 84, 414, 383);
panel.add(scrollPane);
scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

tableConcerner = new JTable() {
private static final long serialVersionUID = 1L;
public boolean isCellEditable(int row,int column){  
                 return false;  
      }
};

///// nous avons affaire � une bo�te de liste de type MULTIPLE_INTERVAL_SELECTION.
//S�lection d'un nombre quelconque de plages de valeurs.
tableConcerner.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
tableConcerner.setBackground(Color.LIGHT_GRAY);
tableConcerner.setFont(new Font("Tahoma", Font.BOLD, 11));
tableConcerner.setRowHeight(30);
tableConcerner.addMouseListener(new MouseAdapter() {
@Override
public void mouseClicked(MouseEvent e) {
try {
// String idReservation =txtIdReservation.getText();
int row=tableConcerner.getSelectedRow();
String tableClick =(tableConcerner.getModel().getValueAt(row, 0).toString());

Connection con;
con = data.db_connect();
PreparedStatement stmt = con.prepareStatement("SELECT appartenir.id_chambre,appartenir.id_reservation FROM appartenir "
		+ "INNER JOIN chambre ON appartenir.id_chambre=chambre.id_chambre "
		+ "WHERE appartenir.id_chambre='"+tableClick+ "'");
ResultSet rs = stmt.executeQuery();



if(rs.next()) {

String add1 =rs.getString("id_chambre");
txtIdChambre.setText(add1);

String add2 =rs.getString("nom");
txtNom.setText(add2);

}

} catch(Exception ex) {
JOptionPane.showMessageDialog(null, ex);
}
}
});
        

tableConcerner.setAutoCreateRowSorter(true);
scrollPane.setViewportView(tableConcerner);

JSeparator separator_1 = new JSeparator();
separator_1.setOrientation(SwingConstants.VERTICAL);
separator_1.setBounds(371, 11, 6, 616);
panel.add(separator_1);

txtIdChambre = new JTextField();
txtIdChambre.setFont(new Font("Tahoma", Font.BOLD, 11));
txtIdChambre.setColumns(10);
txtIdChambre.setBounds(134, 245, 136, 27);
panel.add(txtIdChambre);

JLabel lblIdChambre = new JLabel("ID Chambre :");
lblIdChambre.setFont(new Font("Arial", Font.BOLD, 14));
lblIdChambre.setBounds(10, 251, 112, 14);
panel.add(lblIdChambre);

JLabel lblNumro = new JLabel("N :");
lblNumro.setFont(new Font("Arial", Font.BOLD, 14));
lblNumro.setBounds(10, 288, 112, 14);
panel.add(lblNumro);

txtNom = new JTextField();
txtNom.setFont(new Font("Tahoma", Font.BOLD, 11));
txtNom.setColumns(10);

txtNom.setBounds(134, 286, 136, 27);
panel.add(txtNom);

JButton btnDelete = new JButton("Effacer");
btnDelete.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
        	   
                     String nom = txtNom.getText();
                     String id = txtIdChambre.getText();

            if ( id.trim().length() == 0 ||nom.trim().length() == 0 ) {
           JOptionPane.showMessageDialog(null,"Veuillez d'abord s�lectionner une chambre ou r�servation");
          }else { 
         Connectdb data= new Connectdb(); 


try {
Connection con;
con = data.db_connect();
PreparedStatement stmtUpdate = con.prepareStatement("UPDATE chambre SET Statut='Libre' WHERE id_chambre=?");
stmtUpdate.setString(1, id);
stmtUpdate.executeUpdate();

PreparedStatement stmt = con.prepareStatement("DELETE FROM `appatenir` WHERE id_chambre=?");
stmt.setString(1, id);
stmt.executeUpdate();
JOptionPane.showMessageDialog(null,"Suppression effectu�e");
showTable();


} catch (ClassNotFoundException e) {

JOptionPane.showMessageDialog(null, e);
e.printStackTrace();
} catch (SQLException e) {
JOptionPane.showMessageDialog(null,e); 
e.printStackTrace();
}

showTable();}
}
});
btnDelete.setBackground(new Color(72, 138, 153));
btnDelete.setBounds(415, 493, 179, 39);
panel.add(btnDelete);
JButton btnAnnuler = new JButton("Annuler");
btnAnnuler.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
		
	}
});
btnAnnuler.setBackground(new Color(72, 138, 153));
btnAnnuler.setBounds(623, 493, 179, 39);
panel.add(btnAnnuler);
JLabel lblChambre = new JLabel("Chambre Disponible:");
lblChambre.setFont(new Font("Arial", Font.BOLD, 14));
lblChambre.setBounds(10, 393, 157, 22);
panel.add(lblChambre);
showTable();

}

public void showTable(){
Connectdb data =new Connectdb();
try {
String idReservation =txtIdReservation.getText();
Connection con;
con = data.db_connect();
PreparedStatement stmt = con.prepareStatement
("SELECT id_chambre as 'ID Chambre', id_reservation as 'ID Reservation' "
		+ "FROM appartenir "
		+ "WHERE id_reservation=?");
stmt.setString(1, idReservation);
ResultSet rs = stmt.executeQuery();
tableConcerner.setModel(DbUtils.resultSetToTableModel(rs));
tableConcerner.getColumnModel().getColumn(0).setPreferredWidth(15);
tableConcerner.getColumnModel().getColumn(1).setPreferredWidth(50);


}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
}//===END SHOWTABLE()===// 

public void refreshRoomList() {
try {
disponibiliteChambre.removeAllItems();
Connectdb data =new Connectdb();
Connection con;
con = data.db_connect();
PreparedStatement stmt = con.prepareStatement("SELECT nom FROM chambre WHERE status='libre' ");
ResultSet rs = stmt.executeQuery();
while(rs.next())
       {
           this.disponibiliteChambre.addItem(rs.getString("n"));
       }
con.close();
rs.close();
}catch(Exception e) {JOptionPane.showMessageDialog(null, e);}

}
public String getIdClient() {
return idClient;
}
public void setIdClient(String idClient) {
this.idClient = idClient;
}
public String getIdReservation() {
return idReserv�;
}
public void setIdReservation(String idReserv�) {
this.idReserv� = idReserv�;
}
public String getCNom() {
return clinom;
}
public void setCNom(String cnom) {
this.clinom = cnom;
}
public String getCPrenom() {
return cliprenom;
}
public void setCPrenom(String cprenom) {
this.cliprenom = cprenom;
}
}
