package playerip;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import clientip.Proxy;
import entitiesip.Game;
import entitiesip.Match;
import entitiesip.RandomChar;
import entitiesip.Validator;
import serverip.ManagementServerDb;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

public class NewGame {

	// elementi gui
	public JFrame frmNewGame;
	private JPanel panel;
	private JSpinner spinnerPlayer;
	
	private JTextField textNameGame;
	private JTextField textDate;
	private JTextField textHour;
	
	private JLabel lblNewGame;
	private JLabel lblNewName;
	private JLabel lblDate;
	private JLabel lblHour;
	private JLabel lblHello;
	private JLabel lblSetErr;
	private JLabel lblPlayer;
	
	private JButton btnReturn;
	private JButton btnCreateGame;
	
	
	private Game newGame;
	private Match match;
	private Validator validator =  new Validator();
	boolean flag = true;
	boolean isName;
	boolean checkName;
	int playerRichiesti = 2; // numero min giocatori
	private String nick = "";
	private ManagementServerDb sb;
	private String host; 
	private String userPostGres; 
	private String passwPostGres;
	private RandomChar randomChar = new RandomChar();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proxy proxy = new Proxy();
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					String email  = loginPlayer.getEmail();
					NewGame window = new NewGame(proxy,email);
					window.frmNewGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewGame(Proxy proxy,String email)  {
		frmNewGame = new JFrame();
		frmNewGame.setBounds(100, 100, 544, 378);
		frmNewGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNewGame.getContentPane().setLayout(null);
		frmNewGame.setResizable(false);
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 540, 48);
		frmNewGame.getContentPane().add(panel);
		panel.setLayout(null);

		lblNewGame = new JLabel("NUOVA PARTITA");
		lblNewGame.setFont(new Font("Century Gothic", Font.BOLD, 26));
		lblNewGame.setForeground(Color.WHITE);
		lblNewGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewGame.setBounds(95, 10, 349, 30);
		panel.add(lblNewGame);

		lblNewName = new JLabel("NOME PARTITA");
		lblNewName.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewName.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNewName.setBounds(98, 87, 77, 13);
		frmNewGame.getContentPane().add(lblNewName);

		textNameGame = new JTextField();
		textNameGame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNameGame.setBounds(202, 85, 96, 19);
		frmNewGame.getContentPane().add(textNameGame);
		textNameGame.setColumns(10);

		lblDate = new JLabel("DATA");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblDate.setBounds(98, 125, 46, 13);
		frmNewGame.getContentPane().add(lblDate);

		textDate = new JTextField();
		textDate.setEditable(false);
		textDate.setHorizontalAlignment(SwingConstants.CENTER);
		textDate.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		textDate.setBounds(202, 122, 96, 19);
		frmNewGame.getContentPane().add(textDate);
		textDate.setColumns(10);

		lblHour = new JLabel("ORA");
		lblHour.setHorizontalAlignment(SwingConstants.LEFT);
		lblHour.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblHour.setBounds(98, 163, 46, 13);
		frmNewGame.getContentPane().add(lblHour);

		textHour = new JTextField();
		textHour.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		textHour.setHorizontalAlignment(SwingConstants.CENTER);
		textHour.setEditable(false);
		textHour.setBounds(202, 159, 96, 19);
		frmNewGame.getContentPane().add(textHour);

		textHour.setColumns(10);
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String hour = format.format(date);
		textHour.setText(hour);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		String dateString = dtf.format(localDate);
		textDate.setText(dateString);

		btnCreateGame = new JButton("CREA PARTITA");
		btnCreateGame.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnCreateGame.setBounds(98, 244, 116, 19);
		frmNewGame.getContentPane().add(btnCreateGame);

		lblSetErr = new JLabel("");
		lblSetErr.setFont(new Font("Century Gothic", Font.BOLD, 9));
		lblSetErr.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetErr.setBounds(308, 87, 85, 13);
		frmNewGame.getContentPane().add(lblSetErr);

		spinnerPlayer = new JSpinner();
		spinnerPlayer.setFont(new Font("Century Gothic", Font.BOLD, 10));
		spinnerPlayer.setModel(new SpinnerNumberModel(2, 2, 6, 1));
		spinnerPlayer.setBounds(234, 196, 46, 20);
		spinnerPlayer.setEditor(new JSpinner.DefaultEditor(spinnerPlayer));
		frmNewGame.getContentPane().add(spinnerPlayer);

		lblPlayer = new JLabel("N\u00B0 GIOCATORI");
		lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblPlayer.setBounds(98, 199, 96, 13);
		frmNewGame.getContentPane().add(lblPlayer);
		nick = proxy.getNick(email);

		lblHello = new JLabel("Benvenuto " + nick.toUpperCase());
		lblHello.setBounds(205, 58, 152, 13);
		frmNewGame.getContentPane().add(lblHello);

		btnReturn = new JButton("INDIETRO");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnReturn.setBounds(238, 244, 96, 19);
		frmNewGame.getContentPane().add(btnReturn);
		textNameGame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String result = proxy.checkNameGame(textNameGame.getText());
				// se il formato del nome della partita è valido
				if(validator.isNameSurname(textNameGame.getText())) {
					// il nome scelto per la partita esiste gia ricevo un avviso
					if(result.equals("ESISTE")) {
						JOptionPane.showMessageDialog(frmNewGame, "Il nome scelto esiste già\nScegline un altro");
						checkName = false;
						// altrimenti non ricevo nessun avviso
					}else if(result.equals("NON ESISTE")) {
						checkName = true;

					}

					isName = true;
					textNameGame.setBorder(new LineBorder(Color.GREEN)); // bordo si tinge di verde
					lblSetErr.setText("Formato valido"); // avviso da jlabel

					// se il formato del nome non è valido
				}else if(!validator.isPassword(textNameGame.getText())) {
					
					isName = false;
					textNameGame.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblSetErr.setText("Formato non valido"); // avviso da jlabel

				}

			}

		});


		spinnerPlayer.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				playerRichiesti = (Integer)spinnerPlayer.getValue();

			}
		});

		host = proxy.getHost();
		userPostGres = proxy.userPostGres();
		passwPostGres = proxy.passwPostGres();
		sb = new ManagementServerDb(host,userPostGres,passwPostGres);

		// button per creare una nuova partita
		btnCreateGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// la partita deve necessariamente avere un nome non scelto gia per altre partite e il formato del nome valido
				if(isName == false || checkName == false) { 
					JOptionPane.showMessageDialog(frmNewGame, "IL CAMPO NON PUO' ESSERE VUOTO O IL NOME ESISTE GIA'", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
					// se il formato è rispettato e il nome non è stato scelto per altre partite
				}else if(isName == true  && checkName == true){

					String random = randomChar.setChar(); // memorizzo la stringa random per lo scacchiere

					int number = (Integer)spinnerPlayer.getValue();// memorizzo il numero di giocatori scelto tramite lo spinner

					newGame = new Game(textNameGame.getText(),textDate.getText(),textHour.getText(),number,random);//creo l'oggetto per la nuova partita

					match = new Match(textNameGame.getText(), nick); // creo l'oggetto match

					String result = proxy.createNewGame(newGame); // passo l'oggetto newgame al metodo createnewgame

					String result_1 = proxy.createMatch(match); // chi crea la partita viene aggiunto al match come primo iscritto

					String result_2 = proxy.updateIscritti(textNameGame.getText()); // Controlla che il numero dei giocatori richiesti sia uguale agli iscritti. 

					String result_3 = proxy.checkAvvio(textNameGame.getText()); // // Controlla che la partita possa cominciare


					// se la partita non è stata creata o il match non è stato creato o il numero dei giocatori iscritti non viene aggionato
					if (result.equals("FAILED") || result_1.equals("FAILED") || result_2.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frmNewGame, "Regitrazione non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);

					}

					if(result_3.equals("NON PUO INIZIARE")) { // se la partita non può ancora iniziare(essendoci solo colui che l'ha creata)

						PleaseWait pleaseWait = new PleaseWait(proxy,email,textNameGame.getText()); // si rimane in attesa che si iscrivino tutti
						pleaseWait.frmPlease.setLocationRelativeTo(null);
						pleaseWait.frmPlease.setVisible(true);
						frmNewGame.dispose();

						// si avvia un thread
						Thread thread = new Thread() {

							@Override
							public void run() {


								while(flag) {// il ciclo impostato a true per partire

									// se la partita ancora non può iniziare e non è stata cancellata, si rimane in attesa
									while (!sb.checkStart(textNameGame.getText()) && sb.checkPlayerMatch(textNameGame.getText(),nick)){ 
										
										try {
											Thread.sleep(1000); // controllo avviene ogni secondo
										} catch (InterruptedException e) {
										
											System.out.println("thread interrotto");
										}

										// se la partita può iniziare, parte il timer di 30 secondi per tutti i player
										if(sb.checkStart(textNameGame.getText())) { 

											flag = false; // blocco il ciclo while e quindi il thread
											// vengo portato nel frame countdown
											Countdown countdown = new Countdown(proxy,email,textNameGame.getText());
											countdown.frmCountDown.setLocationRelativeTo(null);
											countdown.frmCountDown.setVisible(true);
											pleaseWait.frmPlease.dispose();


											// se un giocatore abbandona il match, il thread di controllo viene interrotto
										}else if(!sb.checkPlayerMatch(textNameGame.getText(), nick)) {
											flag = false; // // blocco il ciclo while e quindi il thread
											

											// se la partita non può iniziare ed è stata eliminata il thread viene interrotto
										}	else if(!sb.checkStart(textNameGame.getText()) && !sb.checkDeleteGame(textNameGame.getText())){
											flag = false;
											

										}
									}


								}

							}

						};
						thread.start();
					}
				}
			}
		});



		// button di ritorno al menu principale
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPlayer mainMenuPlayer =  new MainMenuPlayer(proxy, email);
				mainMenuPlayer.frame.setLocationRelativeTo(null);
				mainMenuPlayer.frame.setVisible(true);
				frmNewGame.dispose();

			}
		});


	}
}
