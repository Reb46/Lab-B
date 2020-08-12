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
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameList {

	private DefaultTableModel model;
	public JFrame frmGameList;
	private JLabel lblList;
	private JTable table;
	private JScrollPane scrollPane;
	public JTextField textNomePartita;
	private JTextField textData;
	private JTextField textOra;
	private JTextField textRichiesti;
	private Object [] row = new Object[6];
	private JButton btnUnisciti;
	private JLabel lblNomeGame;
	boolean start = true;
	boolean flag = true;
	int numeri;
	Match match;
	String nick;
	private JButton btnUpdate;
	private JTextField textIscritti;
	ArrayList<String> listNick;
	private Proxy proxy;
	private JLabel lblNick;
	private JButton btnReturn;
	ArrayList<Game> lista;
	ManagementServerDb sb = new ManagementServerDb("jdbc:postgresql://127.0.0.1:5432/dbip","postgres","pbkwsclc");


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
		table = new JTable();

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
		table.setDefaultEditor(Object.class,null);
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(270);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("Arial Black", Font.PLAIN, 8));
		scrollPane.setSize(577, 388);
		scrollPane.setLocation(10, 34);
		frmGameList.getContentPane().add(scrollPane);

		lblNomeGame = new JLabel("NOME PARTITA");
		lblNomeGame.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNomeGame.setBounds(597, 62, 81, 13);
		frmGameList.getContentPane().add(lblNomeGame);

		textNomePartita = new JTextField();
		textNomePartita.setEditable(false);
		textNomePartita.setHorizontalAlignment(SwingConstants.CENTER);
		textNomePartita.setBounds(708, 59, 96, 19);
		frmGameList.getContentPane().add(textNomePartita);
		textNomePartita.setColumns(10);

		JLabel lbl_Data = new JLabel("DATA");
		lbl_Data.setFont(new Font("Tahoma", Font.BOLD, 9));
		lbl_Data.setBounds(597, 91, 81, 13);
		frmGameList.getContentPane().add(lbl_Data);

		textData = new JTextField();
		textData.setEditable(false);
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setColumns(10);
		textData.setBounds(708, 88, 96, 19);
		frmGameList.getContentPane().add(textData);

		JLabel lblOra = new JLabel("ORA");
		lblOra.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblOra.setBounds(597, 120, 81, 13);
		frmGameList.getContentPane().add(lblOra);

		textOra = new JTextField();
		textOra.setEditable(false);
		textOra.setHorizontalAlignment(SwingConstants.CENTER);
		textOra.setColumns(10);
		textOra.setBounds(708, 117, 96, 19);
		frmGameList.getContentPane().add(textOra);

		JLabel lblRichiesti = new JLabel("RICHIESTI");
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
		JLabel lblIscritti = new JLabel("ISCRITTI");
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
		nick = proxy.getNick(email);
		lblNick = new JLabel("CIAO " + nick);
		lblNick.setHorizontalAlignment(SwingConstants.CENTER);
		lblNick.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblNick.setBounds(246, 436, 96, 13);
		frmGameList.getContentPane().add(lblNick);

		btnUpdate = new JButton("AGGIORNA");
		btnUpdate.setFont(new Font("Century Gothic", Font.BOLD, 11));
		btnUpdate.setBounds(708, 275, 96, 19);
		frmGameList.getContentPane().add(btnUpdate);

		showListGame();
		click();




		textIscritti.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

				try {


					int number = Integer.parseInt(textRichiesti.getText()); // richiesti
					int n = Integer.parseInt(textIscritti.getText()); //iscritti

					if (n< number && !getPlayer(nick)) {

						btnUnisciti.setEnabled(true);

					} else if(n>= number || getPlayer(nick)) { 
						btnUnisciti.setEnabled(false);

					}


				} catch (Exception e2) {

				}




			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {


					int number = Integer.parseInt(textRichiesti.getText()); // richiesti
					int n = Integer.parseInt(textIscritti.getText()); // iscritti

					if (n< number && !getPlayer(nick))  {

						btnUnisciti.setEnabled(true);

					} else if(n>= number || getPlayer(nick)) { 
						btnUnisciti.setEnabled(false);

					}


				} catch (Exception e2) {

				}




			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {


					int number = Integer.parseInt(textRichiesti.getText()); // richiesti
					int n = Integer.parseInt(textIscritti.getText()); // iscritti

					if (n< number && !getPlayer(nick))  {

						btnUnisciti.setEnabled(true);

					} else if(n>= number || getPlayer(nick)) { 
						btnUnisciti.setEnabled(false);
					}


				} catch (Exception e2) {

				}
			}
		});


		btnUnisciti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				match = new Match(textNomePartita.getText(), nick);

				String result = proxy.createMatch(match);// aggiunge al match l'utente

				String result1 = proxy.updateIscritti(textNomePartita.getText());// Controlla che il numero dei giocatori richiesti sia uguale agli iscritti. 

				String result2 = proxy.checkAvvio(textNomePartita.getText()); // Controlla che la partita possa partire

				if(result.equals("FAILED") || result1.equals("NOT UPDATE")){
					JOptionPane.showMessageDialog(frmGameList, "Non sei stato aggiunto alla partita");
				}
				if(result2.equals("SI INIZIA")) { // se la partita pu� iniziare inizia il countdown

					Countdown countdown = new Countdown(proxy,email,textNomePartita.getText());
					countdown.frmCountDown.setLocationRelativeTo(null);
					countdown.frmCountDown.setVisible(true);
					frmGameList.dispose();


				}else if (result2.equals("NON PUO INIZIARE")) { // se la partita non pu� partire si rimane in attesa

					PleaseWait pleaseWait = new PleaseWait(proxy, email,textNomePartita.getText());
					pleaseWait.frmPlease.setLocationRelativeTo(null);
					pleaseWait.frmPlease.setVisible(true);
					frmGameList.dispose();

					Thread thread = new Thread() {

						@Override
						public void run() {


							while(flag) {// il ciclo va impostato a true per partire

								// se la partita ancora non pu� iniziare e non � stata cancellata, si rimane in attesa
								while (!sb.checkStart(textNomePartita.getText()) && sb.checkPlayerMatch(textNomePartita.getText(),nick)){ 

									System.out.println("la partita non pu� essere avviata gamelist----- " + nick);
									try {
										Thread.sleep(1000); // controllo avviene ogni secondo
									} catch (InterruptedException e) {

										System.out.println("thread interrotto");
									}

									// se la partita pu� iniziare, parte il timer di 30 secondi per tutti i player
									if(sb.checkStart(textNomePartita.getText())) { 

										flag = false;
										Countdown countdown = new Countdown(proxy,email,textNomePartita.getText());
										countdown.frmCountDown.setLocationRelativeTo(null);
										countdown.frmCountDown.setVisible(true);
										pleaseWait.frmPlease.dispose();


										// se un giocatore abbandona il match, il thread di controllo viene interrotto
									}else if(!sb.checkPlayerMatch(textNomePartita.getText(), nick)) {
										flag = false;

										System.out.println("thread gamelist chiuso " + nick);



										// se la partita non pu� iniziare ed � stata eliminata il thread viene interrotto
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

		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPlayer mainMenuPlayer =  new MainMenuPlayer(proxy, email);
				mainMenuPlayer.frame.setLocationRelativeTo(null);
				mainMenuPlayer.frame.setVisible(true);
				frmGameList.dispose();

			}
		});

		// aggiorna la jtable
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
	}

	// setta i campi della jtable
	public void showListGame() {


		lista = proxy.getMatch();

		model = (DefaultTableModel)table.getModel();

		for(int i=0;i<lista.size();i++) {
			numeri = proxy.getIscritti(lista.get(i).getName());// numero degli iscritti
			listNick = proxy.getNickMatch(lista.get(i).getName());// listanick degli iscritti alla i-esima partita

			row[0] = lista.get(i).getName();
			row[1] = lista.get(i).getDate();
			row[2] = lista.get(i).getHour();
			row[3] = lista.get(i).getNumberPlayer();
			row[4] = numeri;
			row[5] = listNick;
			model.addRow(row);


		}

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


	// restituisce vero se il giocatore che accede alla lista delle partite � gi� presente in una
	public boolean getPlayer(String nick) {

		for(int i=0;i<lista.size();i++) 

			listNick = proxy.getNickMatch(lista.get(i).getName());// listanick degli iscritti alla i-esima partita

		if(listNick.contains(nick)) 
			return true;
		return false;


	}

	public String getNamePartita() {
		return textNomePartita.getText();
	}


	//aggiorna la lista utenti
	private void refresh() {

		model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		showListGame();

	}
}