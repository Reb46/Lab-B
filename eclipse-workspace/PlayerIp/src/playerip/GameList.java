package playerip;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clientip.Proxy;
import entitiesip.Game;
import entitiesip.Match;
import serverip.ManagementServerDb;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameList {

	// elementi gui
	public JFrame frmGameList;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private Object [] row = new Object[6];
	public JTextField textNomePartita;
	private JTextField textData;
	private JTextField textOra;
	private JTextField textRichiesti;
	private JTextField textIscritti;

	private JButton btnReturn;
	private JButton btnUnisciti;

	private JLabel lblNomePart;
	private JLabel lblNick;
	private JLabel lblList;
	private JLabel lblIscritti;
	private JLabel lblRichiesti;
	private JLabel lblData;
	private JLabel lblOra;


	boolean start = true;
	boolean flag = true;
	int numeroIscritti;
	private Timer timer; // timer per l'aggiornamento della jtable contenente la lista partite
	private Match match;
	private String nick;
	private ArrayList<String> listNick; // arraylist degli iscritti alla i-esima partita
	private Proxy proxy;
	private ArrayList<Game> lista; // arraylist contenente tutti i campi della partita
	private ManagementServerDb sb;
	private String host; 
	private String userPostGres; 
	private String passwPostGres;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proxy proxy = new Proxy();
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					String email = loginPlayer.getEmail();
					GameList window = new GameList(proxy,email);
					window.frmGameList.setLocationRelativeTo(null);
					window.frmGameList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GameList(Proxy proxy,String email) {
		this.proxy = proxy;
		frmGameList = new JFrame();
		frmGameList.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		frmGameList.setBounds(100, 100, 911, 505);
		frmGameList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameList.getContentPane().setLayout(null);

		lblList = new JLabel("LISTA PARTITE");
		lblList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblList.setHorizontalAlignment(SwingConstants.CENTER);
		lblList.setBounds(169, 10, 281, 13);
		frmGameList.getContentPane().add(lblList);

		table = new JTable(); // jtable lista partite
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Arial Black", Font.BOLD, 10));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"MATCH", "DATA", "ORA", "RICHIESTI","ISCRITTI","GIOCATORI"
				}
				));
		table.setBounds(10, 44, 164, 182);
		table.getTableHeader().setReorderingAllowed(false); // non consentire il riordino delle colonne
		table.getTableHeader().setResizingAllowed(false); // ridimensionamento non consentito
		table.setDefaultEditor(Object.class,null); // campi non editabili
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(270);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // autoridimensionamento disattivato

		scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("Arial Black", Font.PLAIN, 8));
		scrollPane.setSize(577, 388);
		scrollPane.setLocation(10, 34);
		frmGameList.getContentPane().add(scrollPane);

		lblNomePart = new JLabel("NOME PARTITA");
		lblNomePart.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNomePart.setBounds(597, 62, 81, 13);
		frmGameList.getContentPane().add(lblNomePart);

		textNomePartita = new JTextField();
		textNomePartita.setEditable(false);
		textNomePartita.setHorizontalAlignment(SwingConstants.CENTER);
		textNomePartita.setBounds(708, 59, 96, 19);
		frmGameList.getContentPane().add(textNomePartita);
		textNomePartita.setColumns(10);

		lblData = new JLabel("DATA");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblData.setBounds(597, 91, 81, 13);
		frmGameList.getContentPane().add(lblData);

		textData = new JTextField();
		textData.setEditable(false);
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setColumns(10);
		textData.setBounds(708, 88, 96, 19);
		frmGameList.getContentPane().add(textData);

		lblOra = new JLabel("ORA");
		lblOra.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblOra.setBounds(597, 120, 81, 13);
		frmGameList.getContentPane().add(lblOra);

		textOra = new JTextField();
		textOra.setEditable(false);
		textOra.setHorizontalAlignment(SwingConstants.CENTER);
		textOra.setColumns(10);
		textOra.setBounds(708, 117, 96, 19);
		frmGameList.getContentPane().add(textOra);

		lblRichiesti = new JLabel("RICHIESTI");
		lblRichiesti.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblRichiesti.setBounds(597, 146, 81, 13);
		frmGameList.getContentPane().add(lblRichiesti);

		textRichiesti = new JTextField();
		textRichiesti.setEditable(false);
		textRichiesti.setHorizontalAlignment(SwingConstants.CENTER);
		textRichiesti.setColumns(10);
		textRichiesti.setBounds(708, 143, 96, 19);
		frmGameList.getContentPane().add(textRichiesti);

		btnUnisciti = new JButton("UNISCITI");
		btnUnisciti.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnUnisciti.setBounds(708, 216, 96, 19);
		frmGameList.getContentPane().add(btnUnisciti);
		btnUnisciti.setEnabled(false);

		lblIscritti = new JLabel("ISCRITTI");
		lblIscritti.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblIscritti.setBounds(597, 175, 81, 13);
		frmGameList.getContentPane().add(lblIscritti);

		textIscritti = new JTextField();
		textIscritti.setHorizontalAlignment(SwingConstants.CENTER);
		textIscritti.setEditable(false);
		textIscritti.setBounds(708, 172, 96, 19);
		frmGameList.getContentPane().add(textIscritti);
		textIscritti.setColumns(10);

		btnReturn = new JButton("INDIETRO");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnReturn.setBounds(708, 413, 96, 19);
		frmGameList.getContentPane().add(btnReturn);

		nick = proxy.getNick(email); // recupero il nick del giocatore
		lblNick = new JLabel("CIAO " + nick);
		lblNick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNick.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblNick.setBounds(246, 436, 96, 13);
		frmGameList.getContentPane().add(lblNick);

		refresh();
		click();



		// listener utilizzato per attivare il button unisciti, in base alla variazione del valore presente nella jtextfield textiscritti
		textIscritti.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

				try {

					int playerRichiesti = Integer.parseInt(textRichiesti.getText()); // giocatori richiesti
					int playerIscritti = Integer.parseInt(textIscritti.getText()); // giocatori iscritti

					if (playerIscritti<playerRichiesti || !getPlayer(nick)) {

						btnUnisciti.setEnabled(true);

					} else if(playerIscritti>= playerRichiesti ||getPlayer(nick)) { 
						btnUnisciti.setEnabled(false);

					}


				} catch (Exception e2) {
					e2.printStackTrace();
				}




			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {

					int playerRichiesti = Integer.parseInt(textRichiesti.getText()); // giocatori richiesti
					int playerIscritti = Integer.parseInt(textIscritti.getText()); // giocatori iscritti

					if (playerIscritti<playerRichiesti || !getPlayer(nick)) {

						btnUnisciti.setEnabled(true);

					} else if(playerIscritti>= playerRichiesti ||getPlayer(nick)) { 
						btnUnisciti.setEnabled(false);

					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}




			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {

					int playerRichiesti = Integer.parseInt(textRichiesti.getText()); // giocatori richiesti
					int playerIscritti = Integer.parseInt(textIscritti.getText()); // giocatori iscritti

					if (playerIscritti<playerRichiesti || !getPlayer(nick)) {

						btnUnisciti.setEnabled(true);

					} else if(playerIscritti>= playerRichiesti ||getPlayer(nick)) { 
						btnUnisciti.setEnabled(false);

					}


				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		});
		host = proxy.getHost();
		userPostGres = proxy.userPostGres();
		passwPostGres = proxy.passwPostGres();
		sb = new ManagementServerDb(host,userPostGres,passwPostGres);

		btnUnisciti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				match = new Match(textNomePartita.getText(), nick); // crea l'oggetto match

				String result = proxy.createMatch(match);// aggiunge al match l'utente

				String result1 = proxy.updateIscritti(textNomePartita.getText());// Controlla che il numero dei giocatori richiesti sia uguale agli iscritti. 

				String result2 = proxy.checkAvvio(textNomePartita.getText()); // Controlla che la partita possa partire

				// se il giocatore non viene aggiunto al match o il numero degli iscritti non viene aggiornato ricevo un ms di errore
				if(result.equals("FAILED") || result1.equals("NOT UPDATE")){
					JOptionPane.showMessageDialog(frmGameList, "Non sei stato aggiunto alla partita");

				}
				// se la partita può iniziare, inizia il countdown
				if(result2.equals("SI INIZIA")) {
					timer.cancel(); // timer di aggiornamento della jtable bloccato
					Countdown countdown = new Countdown(proxy,email,textNomePartita.getText()); // vengo portato nella schermata di countdown
					countdown.frmCountDown.setLocationRelativeTo(null);
					countdown.frmCountDown.setVisible(true);
					frmGameList.dispose();

					// se la partita non può ancora iniziare e non è stata annullata, si rimane in attesa
				}else if (result2.equals("NON PUO INIZIARE") && sb.checkDeleteGame(textNomePartita.getText())) { 
					timer.cancel();// timer di aggiornamento della jtable bloccato
					PleaseWait pleaseWait = new PleaseWait(proxy, email,textNomePartita.getText());
					pleaseWait.frmPlease.setLocationRelativeTo(null);
					pleaseWait.frmPlease.setVisible(true);
					frmGameList.dispose();

					Thread thread = new Thread() {

						@Override
						public void run() {


							while(flag) {// il ciclo va impostato a true per partire

								// se la partita ancora non può iniziare e non è stata cancellata, si rimane in attesa
								while (!sb.checkStart(textNomePartita.getText()) && sb.checkPlayerMatch(textNomePartita.getText(),nick)){ 


									try {
										Thread.sleep(1000); // controllo avviene ogni secondo
									} catch (InterruptedException e) {

										System.out.println("thread interrotto");
									}

									// se la partita può iniziare, parte il timer di 30 secondi per tutti i player
									if(sb.checkStart(textNomePartita.getText())) { 
										timer.cancel();
										flag = false;
										Countdown countdown = new Countdown(proxy,email,textNomePartita.getText()); // schermata di attesa
										countdown.frmCountDown.setLocationRelativeTo(null);
										countdown.frmCountDown.setVisible(true);
										pleaseWait.frmPlease.dispose();


										// se un giocatore abbandona il match, il thread di controllo viene interrotto
									}else if(!sb.checkPlayerMatch(textNomePartita.getText(), nick)) {

										flag = false;


										// se la partita non può iniziare ed è stata eliminata il thread viene interrotto
									}	else if(!sb.checkStart(textNomePartita.getText()) && !sb.checkDeleteGame(textNomePartita.getText())){
										flag = false;

									}
								}
							}

						}

					};
					thread.start();
				}

			}
		});


		// button di ritorno al menu principale
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timer.cancel();
				MainMenuPlayer mainMenuPlayer =  new MainMenuPlayer(proxy, email);
				mainMenuPlayer.frame.setLocationRelativeTo(null);
				mainMenuPlayer.frame.setVisible(true);
				frmGameList.dispose();

			}
		});
	}



	// i campi della jtable vengono settati sulle jtextfield
	public void click() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int sel=table.getSelectedRow();
				model = (DefaultTableModel) table.getModel();
				textNomePartita.setText(model.getValueAt(sel, 0).toString());
				textData.setText(model.getValueAt(sel, 1).toString());
				textOra.setText(model.getValueAt(sel, 2).toString());
				textRichiesti.setText(model.getValueAt(sel, 3).toString());
				textIscritti.setText(model.getValueAt(sel, 4).toString());


			}
		});
	}

	// restituisce vero se il giocatore che accede alla lista delle partite è già presente in una
	public boolean getPlayer(String nick) {

		for(int i=0;i<lista.size();i++) 

			listNick = proxy.getNickMatch(lista.get(i).getName()); // arraylist degli iscritti alla i-esima partita

		if(listNick.contains(nick)) 
			return true;
		return false;


	}


	// restituisce il nome della partita
	public String getNamePartita() {
		return textNomePartita.getText();
	}


	//aggiorna ogni 3 secondi la jtable contenente la lista delle partita
	private void refresh() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				lista = proxy.getMatch(); // lista contenente tutti i campi della partita

				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setRowCount(0);

				for(int i=0;i<lista.size();i++) {
					numeroIscritti = proxy.getIscritti(lista.get(i).getName());// numero degli iscritti alla i-esima partita
					listNick = proxy.getNickMatch(lista.get(i).getName());// listaNickPlayer degli iscritti alla i-esima partita

					row[0] = lista.get(i).getName(); // campo contenente il nome partita
					row[1] = lista.get(i).getDate(); // campo contenente la data di creazione della partita
					row[2] = lista.get(i).getHour(); // campo contenente ora di creazione
					row[3] = lista.get(i).getNumberPlayer(); // campo contenente il numero dei giocatori richiesti
					row[4] = numeroIscritti; // campo contenente il numero degli iscritti
					row[5] = listNick; // campo contenente la lista dei giocatori iscritti alla i-esima partita
					model.addRow(row);
				}
			}
		}, 100, 3000); // Pianifica l'attività specificata per l'esecuzione ripetuta a partire dal ritardo specificato.

	}



}