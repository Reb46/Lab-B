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

	public JFrame frmParoliere;
	private JPanel parolierePanel = new JPanel();
	private Font font = new Font("Arial",Font.BOLD, 24);
	boolean flag = true;
	int sec =0;
	int min = 2;
	int punteggio;
	private JScrollPane scrollWord;
	Word word;
	private JLabel lblMin;
	private JLabel lblSec;
	private JTable jTableWord;
	private JTable jTableScores;
	ArrayList<String> listNick;
	private Proxy proxy;
	private Object [] rowWord = new Object[1];
	private Object [] row = new Object[2];
	String nick;
	JLabel lblNumSession;
	int sessione = 1;
	ManagementServerDb sb = new ManagementServerDb("jdbc:postgresql://127.0.0.1:5432/dbip","postgres","pbkwsclc");
	
	private DefaultTableModel model;

	//Arraylist usato come supporto per creare una matrice contente le 16 lettere generate dai 16 dadi
	private ArrayList<String> randomChar = new ArrayList<String>();

	//Arraylist di Arraylist usato come supporto per creare una matrice contente le 16 lettere generate dai 16 dadi
	private ArrayList<ArrayList<String>> listaLettere = new ArrayList<ArrayList<String>>();

	private JLabel lblNameGame = new JLabel("PARTITA");
	private JTextField textPartita;
	private JScrollPane scrollPaneScores;
	private JTextField textWord;


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
		parolierePanel.setFont(new Font("Arial Black", Font.BOLD, 12));

		// JPanel del paroliere
		parolierePanel.setBounds(10, 118, 220, 200);
		frmParoliere.getContentPane().add(parolierePanel);
		parolierePanel.setLayout(new GridLayout(4,4));
		parolierePanel.setFont(new Font("Arial Black", Font.BOLD, 12));
		nick = proxy.getNick(email); // recupero il nick del giocatore
		JLabel lblHello = new JLabel("Ciao " + nick );
		lblHello.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblHello.setBounds(276, 60, 111, 13);

		frmParoliere.getContentPane().add(lblHello);
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

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 624, 50);
		frmParoliere.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("IL PAROLIERE");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblNewLabel.setBounds(153, 10, 350, 30);
		panel.add(lblNewLabel);

		JButton btnExit = new JButton("ABBANDONA");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnExit.setBounds(490, 478, 111, 21);
		frmParoliere.getContentPane().add(btnExit);

		JLabel lblTime = new JLabel("CLESSIDRA");
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

		jTableScores = new JTable();

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

		JLabel lblSession = new JLabel("SESSIONE");
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

		JLabel lblWord = new JLabel("SCRIVI LA PAROLA");
		lblWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblWord.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblWord.setBounds(10, 328, 102, 13);
		frmParoliere.getContentPane().add(lblWord);

		JButton btnSendWord = new JButton("INVIA");
		btnSendWord.setBounds(116, 350, 85, 21);
		frmParoliere.getContentPane().add(btnSendWord);

		jTableWord = new JTable();
		jTableWord.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				"PAROLE"}
				));
		jTableWord.setBounds(10, 44, 164, 182);
		jTableWord.getTableHeader().setReorderingAllowed(false); // non consentire il riordino delle colonne
		jTableWord.getTableHeader().setResizingAllowed(false); // ridimensionamento non consentito
		jTableWord.setDefaultEditor(Object.class,null);
		jTableWord.getColumnModel().getColumn(0).setPreferredWidth(80);




		scrollWord = new JScrollPane(jTableWord);
		scrollWord.setBounds(211, 328, 148, 171);
		frmParoliere.getContentPane().add(scrollWord);

		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(536, 118, 16, 13);
		frmParoliere.getContentPane().add(lblNewLabel_1);


		String lettere = proxy.getRandomChar(textPartita.getText());

		randomChar = stringToArrayList(lettere); // arraylist contenente  le 16 lettere
		listaLettere.add(randomChar); // arraylist di arraylist 

		// 16 jtextfield che conterranno ognuna le 16 lettere
		JTextField[][] boggle = new JTextField[randomChar.size()][randomChar.size()];

		// matrice di tipo String contente le 16 lettere create da ognuno dei 16 dadi
		String[][] matrixLetters = BoggleGui.matrixLetter(listaLettere);



		for(int i=0; i<matrixLetters.length;i++) {
			for (int j = 0; j < matrixLetters[0].length; j++) {
				if(matrixLetters[i][j].contains("Q")) {
					matrixLetters[i][j] = "Qu";
				}


				boggle[i][j] = new JTextField((matrixLetters[i][j]));
				boggle[i][j].setFont(font);
				boggle[i][j].setHorizontalAlignment(JTextField.CENTER);
				boggle[i][j].setEditable(false);
				boggle[i][j].setBorder(new BevelBorder(BevelBorder.RAISED));
				boggle[i][j].setBackground(Color.WHITE);
				boggle[i][j].setHighlighter(null); // disabilita evindaziatore di testo
				parolierePanel.add(boggle[i][j]); // gui contenente le 16 lettere

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

		timer();



		Thread t = new Thread() {

			@Override
			public void run() {

				while(flag) {

					while(sb.checkDeleteGame(nomePartita)) { // controlla che la partita non sia stata cancellata
						System.out.println("partita non cancellata " + nick);
						try {
							Thread.sleep(1000); // controllo avviene ogni secondo
						} catch (InterruptedException e) {
							System.out.println("thread chiuso");

						}

						// se la partita � stata cancellata si ritorna al menu principale
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
		showListScores(); // punteggio giocatori nella jtable


		// invia le parole al database
		btnSendWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(textWord.getText().equals("") || textWord.getText().length() > 16) {
					JOptionPane.showMessageDialog(frmParoliere,"ERRORE PAROLA TROPPO LUNGA O CAMPO VUOTO","FAI ATTENZIONE",JOptionPane.ERROR_MESSAGE);
					return;
				}
				word = new Word(textPartita.getText(), nick, textWord.getText(), sessione);
				String result = proxy.addWord(word);
				showWord(); // le parole vengono inviate alla jtable
				if(result.equals("NON AGGIUNTA")) {
					JOptionPane.showMessageDialog(frmParoliere, "PAROLA AGGIUNTA IN PRECEDENZA");
				}
				textWord.setText("");

			}
		});

	}



	// metodo che permette di creare una matrice per le 16 lettere del paroliere
	public static String[][] matrixLetter(ArrayList<ArrayList<String>> lista){

		String[][] r = new String[lista.size()][];
		int i =0;
		for(ArrayList<String> n:lista) {

			r[i++] = n.toArray(new String[n.size()]);

		}
		return r;

	}

	// metodo che permette di convertire una stringa presa dal db in un arraylist
	public static ArrayList<String> stringToArrayList(String word) {
		ArrayList<String> list = new ArrayList<String>();

		for(int i=0;i<word.length();i++) {

			char c = word.charAt(i);
			String boogle = Character.toString(c);
			list.add(boogle);
		}
		return list;


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
					timer.cancel();

				}

			}
		}, 100, 1000); // Pianifica l'attivit� specificata per l'esecuzione ripetuta a partire dal ritardo specificato.

	}



	// inserisce nella jtable il nick e il rispettivo punteggio totale dei giocatori
	public void showListScores() {

		model = (DefaultTableModel)jTableScores.getModel();
		listNick = proxy.getNickMatch(textPartita.getText()); 
		for(int i=0;i<listNick.size();i++) {
			punteggio = proxy.getScores(textPartita.getText(), listNick.get(i),lblNumSession.getText());

			row[0] = listNick.get(i);
			row[1] = punteggio;
			model.addRow(row);

		}
	}

	// le parole scelte dal giocatore vengono inserite nella jtable
	public void showWord() {
		ArrayList<String> parolaList = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel)jTableWord.getModel();
		model.setRowCount(0);
		
		parolaList = proxy.getWord(textPartita.getText(),nick,sessione);
		
		for(int i=0;i<parolaList.size();i++) {
			rowWord[0] = parolaList.get(i);
			model.addRow(rowWord);

		}

	}
}

