package sa2;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connectdb {

	Connection con;

	

	 
	public Connectdb() {
		
	}
	
public Connection db_connect() throws SQLException,	ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sa2?autoReconnect=true&useSSL=false","root","");
				
		return con;
	
	}
	public void close_connect() throws Exception {
		con.close();
	}

	

	
	

}
