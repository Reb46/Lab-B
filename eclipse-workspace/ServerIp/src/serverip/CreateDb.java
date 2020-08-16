package serverip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDb {
	
	private Connection connection;
	private PreparedStatement ps;
	private final String host = "jdbc:postgresql://127.0.0.1:5432/";


	public CreateDb(String user,String pw){
		
		try {

			Class.forName("org.postgresql.Driver");


		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC non trovato");
			System.exit(1);

		}
		System.out.println("PostgreSQL JDBC driver 42.2.9 registrato correttamente");
		connection = null;

		//inizializzo l'oggetto connection
		try {

			connection = DriverManager.getConnection(host,user,pw);
		}
		catch (SQLException e)
		{

			System.out.println("Impossibile connettersi al database");
		}
		if (connection != null) 
		{ 
			System.out.println("Connessione al database creata correttamente.");


			try {
				ps= connection.prepareStatement("create database dbip");

				ps.executeUpdate();
				System.out.println("Database creato");

			}
			catch(SQLException ex){

				System.out.println("Database già presente");

			}	

		}else {
			System.out.println("Impossibile connettersi al database.\nRiavvia il server");
			System.exit(1);
		}

	}


}
