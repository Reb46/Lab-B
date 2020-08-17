package serverip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import entitiesip.UserIp;
import entitiesip.Word;
import entitiesip.CommCommands;
import entitiesip.Game;
import entitiesip.Match;


public class Skeleton extends Thread {

	ManagementServerDb msdb;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Socket socket;
	UserIp userIp;
	Game newGame;
	Match match;
	Word word;


	public Skeleton(Socket socket,ManagementServerDb msdb) throws IOException {
		this.socket = socket;
		this.msdb = msdb;
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
	}


	public void run() {

		System.out.println("Skeleton: " + socket + " in esecuzione");
		CommCommands cc = null;
		String[] parameters = null;
		String returnValue;

		do {
			try {
				System.out.println();
				System.out.println("Lo skeleton attende...");
				cc=(CommCommands)ois.readObject();
				switch (cc) {

				case CREATEACCOUNT:
					System.out.println("Prendo in carico la creazione dell'account utente....");
					userIp = (UserIp) ois.readObject();
					returnValue=msdb.registerUser(userIp);
					oos.writeObject(returnValue);
					break;

				case DELETEACCOUNT:
					System.out.println("Prendo in carico la cancellazione dell'account utente....");
					String email = (String) ois.readObject();
					returnValue = msdb.delete(email);
					oos.writeObject(returnValue);
					break;

				case ACTIVATION:
					System.out.println("Prendo in carico l'attivazione dell'utente....");
					parameters = (String[])ois.readObject();
					returnValue = msdb.activeUser(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;

				case LOGIN:
					System.out.println("Prendo in carico il login dell'utente....");
					parameters = (String[])ois.readObject();
					returnValue = msdb.login(parameters[0], parameters[1],parameters[2]);
					oos.writeObject(returnValue);
					break;

				case LOGINADMIN:
					System.out.println("Prendo in carico il login dell'admin....");
					parameters = (String[])ois.readObject();
					returnValue = msdb.loginAdmin(parameters[0], parameters[1],parameters[2]);
					oos.writeObject(returnValue);
					break;

				case CHECKNICK:
					System.out.println("Prendo in carico il check sul nickName....");
					String nick = (String)ois.readObject();
					returnValue = msdb.checkNick(nick);
					oos.writeObject(returnValue);
					break;

				case CHECKEMAIL:
					System.out.println("Prendo in carico il check sull'email....");
					String emailCheck = (String) ois.readObject();
					returnValue = msdb.checkEmail(emailCheck);
					oos.writeObject(returnValue);
					break;

				case RESETPASSWORD:
					System.out.println("Prendo in carico il reset della password....");
					String emailU =(String)ois.readObject();
					returnValue = msdb.resetPassword(emailU);
					oos.writeObject(returnValue);
					break;

				case GETNAME:
					System.out.println("Prendo in carico il getname....");
					String emailName= (String) ois.readObject();
					returnValue = msdb.getName(emailName);
					oos.writeObject(returnValue);
					break;

				case GETSURNAME:
					System.out.println("Prendo in carico il getsurname....");
					String emailSurname = (String) ois.readObject();
					returnValue = msdb.getSurname(emailSurname);
					oos.writeObject(returnValue);
					break;

				case GETNICK:
					System.out.println("Prendo in carico il getnick....");
					String emailNick = (String)ois.readObject();
					returnValue=msdb.getNick(emailNick);
					oos.writeObject(returnValue);
					break;

				case GETADMIN:
					System.out.println("Prendo in carico il getadmin....");
					String type = (String)ois.readObject();
					returnValue=msdb.getAdmin(type);
					oos.writeObject(returnValue);
					break;

				case GETISCRITTI:
					System.out.println("Prendo in carico il getIscritti....");
					String nomePartita = (String) ois.readObject();
					int value = msdb.getIscritti(nomePartita);
					oos.writeObject(value);
					break;

				case GETRICHIESTI:
					System.out.println("Prendo in carico il getRichiesti....");
					String nome_Partita = (String) ois.readObject();
					int number = msdb.getRichiesti(nome_Partita);
					oos.writeObject(number);
					break;

				case GETNICKMATCH:
					System.out.println("Prendo in carico il getNickMatch....");
					String nomePartita_1 = (String) ois.readObject();
					ArrayList<String > lista = msdb.getNickMatch(nomePartita_1);
					oos.writeObject(lista);
					break;

				case EDITNAME:
					System.out.println("Prendo in carico l'edit del nome....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.editName(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;

				case EDITSURNAME:
					System.out.println("Prendo in carico l'edit del cognome....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.editSurname(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;

				case EDITNICK:
					System.out.println("Prendo in carico l'edit del nickname....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.editNick(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;

				case CHECKAVVIO:
					System.out.println("Prendo in carico il checkAvvio ....");
					String nomePartita_2 = (String) ois.readObject();
					returnValue = msdb.checkAvvio(nomePartita_2);
					oos.writeObject(returnValue);
					break;

				case CHECKPASSWORD:
					System.out.println("Prendo in carico il check sulla password....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.checkPassword(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;
				case CHECKNAMEGAME:
					System.out.println("Prendo in carico il check sul nome scelto per la partita....");
					String nomePartita_3 = (String) ois.readObject();
					returnValue = msdb.checkNameGame(nomePartita_3);
					oos.writeObject(returnValue);
					break;

				case CHECKPASSWORDADMIN:
					System.out.println("Prendo in carico il check sulla password admin....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.checkPasswordAdmin(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;

				case EDITPASSWORD:
					System.out.println("Prendo in carico l'edit della password....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.editPassword(parameters[0], parameters[1]);
					oos.writeObject(returnValue);
					break;

				case EDITPASSWORDADMIN:
					System.out.println("Prendo in carico l'edit della password admin....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.editPasswordAdmin(parameters[0], parameters[1]);
					oos.writeObject(returnValue);
					break;

				case NEWGAME:
					System.out.println("Prendo in carico la creazione di una nuova partita....");
					newGame = (Game) ois.readObject();
					returnValue= msdb.createNewGame(newGame);
					oos.writeObject(returnValue);
					break;

				case ADDWORD:
					System.out.println("Prendo in carico l'inserimento delle parole....");
					word = (Word) ois.readObject();
					returnValue = msdb.addWord(word);
					oos.writeObject(returnValue);
					break;

				case LISTGAME:
					System.out.println("Prendo in carico la lista delle partite....");
					ArrayList<Game> list = msdb.getMatch();
					oos.writeObject(list);
					break;

				case UPDATEISCRITTI:
					System.out.println("Prendo in carico l'update degli iscritti....");
					String nomePartita_4 = (String) ois.readObject();
					returnValue = msdb.updateIscritti(nomePartita_4);
					oos.writeObject(returnValue);
					break;

				case SETABBANDONO:
					System.out.println("Prendo in carico setAbbandono della partita....");
					parameters = (String[]) ois.readObject();
					returnValue = msdb.setAbbandono(parameters[0],parameters[1]);
					oos.writeObject(returnValue);
					break;

				case GETUSER:
					System.out.println("Prendo in carico la lista degli utenti....");
					ArrayList<UserIp> user = msdb.getUser();
					oos.writeObject(user);
					break;

				case GETRANDOMCHAR:
					System.out.println("Prendo in carico il randomChar....");
					String nomePartita_5 = (String) ois.readObject();
					returnValue = msdb.getRandomChar(nomePartita_5);
					oos.writeObject(returnValue);
					break;

				case NEWMATCH:
					System.out.println("Prendo in carico la creazione del match....");
					match = (Match) ois.readObject();
					returnValue = msdb.createMatch(match);
					oos.writeObject(returnValue);
					break;

				case DELETEGAME:
					System.out.println("Prendo in carico il deleteGame...");
					String nomePartita_6 = (String) ois.readObject();
					returnValue = msdb.deleteGame(nomePartita_6);
					oos.writeObject(returnValue);
					break;

				case GETSCORES:
					System.out.println("Prendo in carico il getscores...");
					parameters = (String[]) ois.readObject();
					int session = (int) ois.readObject();
					int punteggio = msdb.getScores(parameters[0], parameters[1],session);
					oos.writeObject(punteggio);
					break;
				case GETWORD:
					System.out.println("Prendo in carico il getword ...");
					parameters = (String[]) ois.readObject();
					int sessione = (int) ois.readObject();
					ArrayList<String> parolaList =	 msdb.getWord(parameters[0], parameters[1], sessione);
					oos.writeObject(parolaList);
					break;
				case GETSHOST:
					System.out.println("Prendo in carico il getHost ...");
					returnValue = msdb.getHost();
					oos.writeObject(returnValue);
					break;
				case GETUSERPOSTGRES:
					System.out.println("Prendo in carico il getuserPostGres ...");
					returnValue = msdb.userPostGres();
					oos.writeObject(returnValue);
					break;
				case GETPASSWPOSTGRES:
					System.out.println("Prendo in carico il getPassqPostGres ...");
					returnValue = msdb.passwPostGres();
					oos.writeObject(returnValue);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println("Connessione chiusa dal client!");
				try {
					socket.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}while(cc!=CommCommands.CLOSE);

		System.out.println("Chiusura Skeleton");
	}

}
