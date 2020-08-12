package serverip;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import entitiesip.Validator;


public class ServerIp {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException,ClassNotFoundException, SQLException, NoSuchAlgorithmException, SendFailedException, MessagingException {

		String type = "admin";
		String uid = "";
		String password = "";
		Validator validator = new Validator();
		String pw = validator.sha1(password);
		int portNumber = 8080;
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(portNumber);
		System.out.println("Porta " + portNumber);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		CreateDb db = new CreateDb();
		boolean adminNotExist;
		try {
			System.out.println("Digitare host Database PostgreSQL");
			String hostPostgreSQL = in.readLine();
			System.out.println("Digitare user Database PostgreSQL");
			String userPostgreSQL = in.readLine();
			System.out.println("Digitare password Database PostgreSQL");
			String passwPostgreSQL = in.readLine();

			ManagementServerDb ms = new ManagementServerDb(hostPostgreSQL, userPostgreSQL, passwPostgreSQL);
			
			// se non esiste un utente con privilegi admin, ne chiede la creazione, altrimenti verrà richiesto il login dell'admin
			if(!ms.checkAdmin(type)) { 
				adminNotExist = false;
				System.out.println("Necessario user con privilegi di amministratore.\nIniziamo la registrazione");


				System.out.println("Scegli il tuo Uid"); 
				uid = in.readLine();
				while(!validator.isUidValid(uid)) {
					System.out.println("ATTENZIONE FORMATO NON VALIDO");
					System.out.println("Scegli il tuo Uid"); 
					uid = in.readLine(); 
				}

				System.out.println();
				System.out.println("Scegli una password");
				System.out.println("\nLa password deve rispettare le seguenti regole: " +
						"\nalmeno 8 caratteri, almeno un numero, almeno una lettera maiuscola, almeno una lettere minuscola," +
						"\nalmeno un carattere speciale(.#$%^&+=),nessuno spazio");

				password = in.readLine(); 
				while(!validator.isPassword(password)) {
					System.out.println("ATTENZIONE FORMATO NON VALIDO");
					System.out.println("Scegli una password"); 
					password= in.readLine(); 

				} 
				password = validator.sha1(password);
				ms.registerAdmin(uid,password,type);
				System.out.println();
				System.out.println("Registrazione avvenuta con successo " + uid);


			}else {

				System.out.println();
				System.out.println("Per avviare il server è necessario effettuare il login"); 
				System.out.println();
				System.out.println("--- LOGIN ---"); 
				System.out.println("Digitare Uid"); 
				uid = in.readLine();
				System.out.println("Digitare password"); 
				password= in.readLine(); 
				pw = validator.sha1(password);
				while(!ms.LoginAdminServer(uid, pw, type)) {
					System.out.println("Login fallito.");
					System.out.println("Digitare Uid"); 
					uid = in.readLine();
					System.out.println("Digitare password"); 
					password= in.readLine(); 
					pw = validator.sha1(password);
				}
				System.out.println("Login amministatore effettuato con successo!");
			}

			while (true) {

				System.out.println("Server pronto: in attesa sulla porta: " + portNumber);
				Socket socket = server.accept();
				System.out.println("Connesso con: " + socket);
				new Skeleton(socket, ms).start();

			}

		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);

		}


	}
}