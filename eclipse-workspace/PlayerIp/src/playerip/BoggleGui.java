package playerip;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import clientip.Proxy;
import entitiesip.Word;
import serverip.ManagementServerDb;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BoggleGui {

	// elementi gui
	public JFrame frmParoliere;

	private JPanel parolierePanel;
	private JPanel panelTitle;
	private Font font = new Font("Arial",Font.BOLD, 24);

	private JTable jTableWord;
	private JTable jTableScores;
	private JScrollPane scrollWord;
	private JScrollPane scrollPaneScores;


	private JLabel lblSession;
	private JLabel lblTime;
	private JLabel lblNumSession;
	private JLabel lblTitle;

	private JLabel lblMin;
	private JLabel lblSec;
	private JLabel lblHello;
	private JLabel lblWord;
	private JLabel lblNameGame;
	private JLabel lblColons;

	private Object [] rowWord = new Object[1];
	private Object [] rowListScores = new Object[2];

	private JTextField textPartita;
	private JTextField[][] boggle;
	private JTextField textWord;

	private JButton btnSendWord;
	private JButton btnExit;



	private String boxChar[][] = new String[4][4];
	boolean flag = true;
	int sec = 0;
	int min = 2;
	int punteggio;
	private Word word;
	private Proxy proxy;
	private String nick;
	int sessione = 1;
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
					GameList gameList = new GameList(proxy, email);
					String nomePartita = gameList.getNamePartita();
					BoggleGui window = new BoggleGui(proxy,email,nomePartita);
					window.frmParoliere.setLocationRelativeTo(null);
					window.frmParoliere.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BoggleGui(Proxy proxy,String email,String nomePartita) {
		this.proxy = proxy;

		frmParoliere = new JFrame();
		frmParoliere.setTitle("IL PAROLIERE");
		frmParoliere.setBounds(100, 100, 638, 557);
		frmParoliere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmParoliere.getContentPane().setLayout(null);

		// JPanel del paroliere
		parolierePanel = new JPanel();
		parolierePanel.setBounds(10, 118, 220, 200);
		parolierePanel.setLayout(new GridLayout(4,4));
		parolierePanel.setFont(new Font("Arial Black", Font.BOLD, 12));
		frmParoliere.getContentPane().add(parolierePanel);

		nick = proxy.getNick(email); // recupero il nick del giocatore

		lblHello = new JLabel("Ciao " + nick );
		lblHello.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblHello.setBounds(276, 60, 111, 13);

		frmParoliere.getContentPane().add(lblHello);

		lblNameGame = new JLabel("PARTITA");
		lblNameGame.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblNameGame.setBounds(22, 95, 45, 13);

		frmParoliere.getContentPane().add(lblNameGame);

		textPartita = new JTextField(nomePartita);
		textPartita.setHorizontalAlignment(SwingConstants.CENTER);
		textPartita.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textPartita.setEditable(false);
		textPartita.setBounds(92, 92, 125, 19);
		frmParoliere.getContentPane().add(textPartita);
		textPartita.setColumns(10);

		panelTitle = new JPanel();
		panelTitle.setBackground(Color.GRAY);
		panelTitle.setBounds(0, 0, 624, 50);
		frmParoliere.getContentPane().add(panelTitle);
		panelTitle.setLayout(null);

		lblTitle = new JLabel("IL PAROLIERE");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblTitle.setBounds(153, 10, 350, 30);
		panelTitle.add(lblTitle);

		btnExit = new JButton("ABBANDONA");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnExit.setBounds(490, 478, 111, 21);
		frmParoliere.getContentPane().add(btnExit);

		lblTime = new JLabel("CLESSIDRA");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblTime.setBounds(512, 95, 62, 13);
		frmParoliere.getContentPane().add(lblTime);

		lblMin = new JLabel("");
		lblMin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMin.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblMin.setBounds(512, 118, 16, 13);
		frmParoliere.getContentPane().add(lblMin);

		lblSec = new JLabel("");
		lblSec.setHorizontalAlignment(SwingConstants.LEFT);
		lblSec.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblSec.setBounds(558, 118, 16, 13);
		frmParoliere.getContentPane().add(lblSec);

		jTableScores = new JTable(); // jtable punteggio

		jTableScores.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jTableScores.setFont(new Font("Arial Black", Font.BOLD, 10));
		jTableScores.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"GIOCATORE","PUNTI"}
				));
		jTableScores.setBounds(10, 44, 164, 182);
		jTableScores.getTableHeader().setReorderingAllowed(false); // non consentire il riordino delle colonne
		jTableScores.getTableHeader().setResizingAllowed(false); // ridimensionamento non consentito
		jTableScores.setDefaultEditor(Object.class,null);
		jTableScores.getColumnModel().getColumn(0).setPreferredWidth(80);
		jTableScores.getColumnModel().getColumn(1).setPreferredWidth(58);
		jTableScores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneScores = new JScrollPane(jTableScores);
		scrollPaneScores.setBounds(466, 143, 135, 175);
		frmParoliere.getContentPane().add(scrollPaneScores);

		lblSession = new JLabel("SESSIONE");
		lblSession.setBounds(240, 95, 62, 13);
		frmParoliere.getContentPane().add(lblSession);

		lblNumSession = new JLabel(String.valueOf(sessione));
		lblNumSession.setBounds(304, 95, 20, 13);
		frmParoliere.getContentPane().add(lblNumSession);

		textWord = new JTextField();
		textWord.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textWord.setBounds(10, 351, 96, 19);
		frmParoliere.getContentPane().add(textWord);
		textWord.setColumns(10);

		lblWord = new JLabel("SCRIVI LA PAROLA");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblWord.setBounds(10, 328, 102, 13);
		frmParoliere.getContentPane().add(lblWord);

		btnSendWord = new JButton("INVIA");
		btnSendWord.setBounds(116, 350, 85, 21);
		frmParoliere.getContentPane().add(btnSendWord);

		jTableWord = new JTable(); // jtable per parole individuate
		jTableWord.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				"PAROLE"}
				));

		jTableWord.setBounds(10, 44, 164, 182);
		jTableWord.getTableHeader().setReorderingAllowed(false); // non consentire il riordino delle colonne
		jTableWord.getTableHeader().setResizingAllowed(false); // ridimensionamento non consentito
		jTableWord.setDefaultEditor(Object.class,null); // modifica campi non consentita
		jTableWord.getColumnModel().getColumn(0).setPreferredWidth(80);

		scrollWord = new JScrollPane(jTableWord);
		scrollWord.setBounds(211, 328, 148, 171);
		frmParoliere.getContentPane().add(scrollWord);

		lblColons = new JLabel(":");
		lblColons.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblColons.setHorizontalAlignment(SwingConstants.CENTER);
		lblColons.setBounds(536, 118, 16, 13);
		frmParoliere.getContentPane().add(lblColons);

		// variabile contenente le 16 lettere dello scacchiere per questa partita
		String lettere = proxy.getRandomChar(textPartita.getText());

		// 16 jtextfield che conterranno una delle 16 lettere per la composizione dello scacchiere
		boggle = new JTextField[boxChar.length][boxChar.length];


		for(int i=0; i<4;i++) {
			for (int j = 0; j < 4; j++) {
				boxChar[i][j] = Character.toString(lettere.charAt(i*4 + j)); // costruisco una matrice 4x4 di tipo string
				if(boxChar[i][j].contains("Q")) { // se è presente la lettera q verra aggiunta la u
					boxChar[i][j] = "Qu";
				}

				boggle[i][j] = new JTextField(boxChar[i][j]); // ognuna delle 16 jtextfield riceverà una lettera
				boggle[i][j].setFont(font); // setto il font
				boggle[i][j].setHorizontalAlignment(JTextField.CENTER); // centro la lettera
				boggle[i][j].setEditable(false); // non editabile
				boggle[i][j].setBorder(new BevelBorder(BevelBorder.RAISED)); // bordo jtext rialzato
				boggle[i][j].setBackground(Color.WHITE); // colore della jtext bianco
				boggle[i][j].setHighlighter(null); // disabilita evindaziatore di testo
				parolierePanel.add(boggle[i][j]); // aggiungo al panel la matrice di jtetxfield


				// se decido di annullare una partita vengo disconnesso dalla piattaforma
				btnExit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int response = JOptionPane.showConfirmDialog(frmParoliere, nick + " Confermi l'abbandono della partita? Verrai disconnesso", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(response == JOptionPane.YES_OPTION) {
							String result = proxy.deleteGame(nomePartita);
							if(result.equals("ANNULLATA")) {
								frmParoliere.dispose();						
								System.exit(0);
							}
						}
					}
				});
			}
		}

		host = proxy.getHost();
		userPostGres = proxy.userPostGres();
		passwPostGres = proxy.passwPostGres();
		sb = new ManagementServerDb(host,userPostGres,passwPostGres);

		// countdown clessidra di gioco
		timer();
		showListScores();

		Thread t = new Thread() {

			@Override
			public void run() {

				while(flag) {

					// controlla che la partita non sia stata cancellata
					while(sb.checkDeleteGame(nomePartita)) { 
					
						try {
							Thread.sleep(1000); // controllo avviene ogni secondo
						} catch (InterruptedException e) {
							System.out.println("thread chiuso");

						}

						// se la partita è stata cancellata si ritorna al menu principale
						if(!sb.checkDeleteGame(nomePartita)){
							flag = false;
							MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
							mainMenuPlayer.frame.setLocationRelativeTo(null);
							mainMenuPlayer.frame.setVisible(true);
							frmParoliere.dispose();
							JOptionPane.showMessageDialog(mainMenuPlayer.frame, "PARTITA ANNULLATA DA UN GIOCATORE");

						}else if (min == 0 && sec == 0) {
							flag = false;
							btnExit.setEnabled(false);
							JOptionPane.showMessageDialog(frmParoliere, "TEMPO SCADUTO " + nick);
							break;
						}
					}
				}
			}
		};
		t.start();

		// invia le parole al database
		btnSendWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// se il campo usato per la parola scelta è vuoto o superiore alle 16 lettere viene mostrato un avviso
				if(textWord.getText().equals("") || textWord.getText().length() > 16) {
					JOptionPane.showMessageDialog(frmParoliere,"ERRORE PAROLA TROPPO LUNGA O CAMPO VUOTO","FAI ATTENZIONE",JOptionPane.ERROR_MESSAGE);
					return;
				}

				// creo l'oggetto di tipo Word a cui passo come parametro il nome della partita,il nick, la parola individuata e la sessione di gioco
				word = new Word(textPartita.getText(), nick, textWord.getText(), sessione);

				// dopo aver inviato l'oggetto di tipo word ricevo il risultato.
				String result = proxy.addWord(word); 

				showWord(); // le parole digitate dal giocatore vengono aggiunte alla jtable

				// se il valore di ritorno è: "non aggiunta", ricevo un messaggio di avviso
				if(result.equals("NON AGGIUNTA")) {
					JOptionPane.showMessageDialog(frmParoliere, "PAROLA AGGIUNTA IN PRECEDENZA");
				}
				textWord.setText(""); // setta il campo su vuoto dopo aver premuto il pulsante invia

			}
		});

	}

	// countdown clessidra di gioco
	public  void timer() {

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				lblMin.setText("" + min);
				lblSec.setText("" + sec);

				if(sec == 0 && min !=0) {

					sec = 59;
					min = min-1;
				}

				lblMin.setText("" + min);
				lblSec.setText("" + sec--);


				if (sec == 0 && min == 0) {
					timer.cancel(); // quando il tempo della clessidra scade, il timer viene bloccato

				}

			}
		}, 100, 1000); // Pianifica l'attività specificata per l'esecuzione ripetuta a partire dal ritardo specificato.
		// 100 Questo è il ritardo in millisecondi prima dell'esecuzione dell'attività.
		// 1000 Questo è il tempo in millisecondi tra le esecuzioni successive di attività. 
	}



	// inserisce nella jtable il nick dei giocatori partecipanti con il rispettivo punteggio
	public void showListScores() {
		ArrayList<String> listNick = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel)jTableScores.getModel();
		
		listNick = proxy.getNickMatch(textPartita.getText()); 
		
		for(int i=0;i<listNick.size();i++) {
			punteggio = proxy.getScores(textPartita.getText(), listNick.get(i),sessione);

			rowListScores[0] = listNick.get(i);
			rowListScores[1] = punteggio;
			model.addRow(rowListScores);

		}
	}

	// le parole scelte dal giocatore vengono inserite nella jtable
	public void showWord() {

		ArrayList<String> parolaList = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel)jTableWord.getModel();
		model.setRowCount(0);

		// arraylist contenente le parole individuate dal giocatote nella partita/sessione di gioco
		parolaList = proxy.getWord(textPartita.getText(),nick,sessione); 

		for(int i=0;i<parolaList.size();i++) {
			rowWord[0] = parolaList.get(i);
			model.addRow(rowWord);

		}

	}
}

