package adminip;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import clientip.Proxy;
import entitiesip.UserIp;
import entitiesip.Validator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;

// questa classe si occupa della modifica e cancellazione del profilo di tutti gli utenti registrati
public class ProfilePlayer {

	//elementi gui
	public JFrame frame;
	private JPanel panel;


	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;


	private JTextField textSurname;
	private JTextField textName;
	private JTextField textNick;
	private JTextField textEmail;
	private JTextField textNewSurname;
	private JTextField textNewName;
	private JTextField textNewNick;
	private JTextField textSearch;

	private JLabel lblEditPlayer;
	private JLabel lblSurname;
	private JLabel lblName;
	private JLabel lblNick;
	private JLabel lblEmail;
	private JLabel lblErrSurn;
	private JLabel lblErrName;
	private JLabel lblErrNick;

	private JButton btnDelete;
	private JButton btnEditName;
	private JButton btnEditSurn;
	private JButton btnEditNick;
	private JButton btnReturn;


	boolean isOkSurname;
	boolean isOkName;
	boolean isOkNick;
	boolean checkNick;
	private Validator validator = new Validator();
	private Proxy proxy;
	private Object [] rowUserList = new Object[6]; 
	private ArrayList<UserIp> user; //// arraylist con i campi relativi a ogni utente registrato



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proxy proxy = new Proxy();
					LoginAdmin loginAdmin = new LoginAdmin(proxy);
					String uid = loginAdmin.getUid();
					ProfilePlayer window = new ProfilePlayer(proxy,uid);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ProfilePlayer(Proxy proxy,String uid)  {
		this.proxy = proxy;
		frame = new JFrame();
		frame.setBounds(100, 100, 1236, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 1222, 50);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblEditPlayer = new JLabel("EDIT PROFILO UTENTE");
		lblEditPlayer.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblEditPlayer.setForeground(Color.WHITE);
		lblEditPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditPlayer.setBounds(344, 10, 520, 30);
		panel.add(lblEditPlayer);



		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.getTableHeader().setReorderingAllowed(false); // non consentire il riordino delle colonne
		table.getTableHeader().setResizingAllowed(false); // ridimensionamento non consentito
		table.setDefaultEditor(Object.class,null);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"COGNOME", "NOME", "NICK", "PASSWORD","TIPO","EMAIL"}
				));
		table.setBounds(10, 44, 164, 182);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 86, 550, 426);
		scrollPane.setFont(new Font("Arial Black", Font.PLAIN, 8));
		frame.getContentPane().add(scrollPane);

		lblSurname = new JLabel("Cognome");
		lblSurname.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblSurname.setBounds(584, 91, 55, 13);
		frame.getContentPane().add(lblSurname);

		lblName = new JLabel("Nome");
		lblName.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblName.setBounds(584, 150, 45, 13);
		frame.getContentPane().add(lblName);

		lblNick = new JLabel("Nick");
		lblNick.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblNick.setBounds(584, 209, 45, 13);
		frame.getContentPane().add(lblNick);

		textSurname = new JTextField();
		textSurname.setEditable(false);
		textSurname.setBounds(659, 88, 96, 19);
		frame.getContentPane().add(textSurname);
		textSurname.setColumns(10);

		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		textName.setBounds(659, 147, 96, 19);
		frame.getContentPane().add(textName);

		textNick = new JTextField();
		textNick.setEditable(false);
		textNick.setColumns(10);
		textNick.setBounds(659, 206, 96, 19);
		frame.getContentPane().add(textNick);

		btnDelete = new JButton("ELIMINA");
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnDelete.setBounds(954, 254, 85, 21);
		frame.getContentPane().add(btnDelete);

		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setBounds(659, 255, 96, 19);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblEmail.setBounds(584, 258, 45, 13);
		frame.getContentPane().add(lblEmail);

		btnEditName = new JButton("MODIFICA");
		btnEditName.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditName.setBounds(954, 150, 85, 21);
		frame.getContentPane().add(btnEditName);

		textNewSurname = new JTextField();
		textNewSurname.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNewSurname.setBounds(803, 88, 96, 19);
		frame.getContentPane().add(textNewSurname);
		textNewSurname.setColumns(10);

		textNewName = new JTextField();
		textNewName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNewName.setColumns(10);
		textNewName.setBounds(803, 147, 96, 19);
		frame.getContentPane().add(textNewName);

		textNewNick = new JTextField();
		textNewNick.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNewNick.setColumns(10);
		textNewNick.setBounds(803, 206, 96, 19);
		frame.getContentPane().add(textNewNick);

		lblErrSurn = new JLabel("");
		lblErrSurn.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrSurn.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblErrSurn.setBounds(802, 117, 146, 13);
		frame.getContentPane().add(lblErrSurn);

		lblErrName = new JLabel("");
		lblErrName.setFont(new Font("Century Gothic", Font.BOLD, 8));
		lblErrName.setBounds(779, 176, 154, 13);
		frame.getContentPane().add(lblErrName);

		lblErrNick = new JLabel("");
		lblErrNick.setFont(new Font("Century Gothic", Font.BOLD, 8));
		lblErrNick.setBounds(779, 235, 154, 13);
		frame.getContentPane().add(lblErrNick);

		btnEditSurn = new JButton("MODIFICA");
		btnEditSurn.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditSurn.setBounds(954, 87, 85, 21);
		frame.getContentPane().add(btnEditSurn);

		btnEditNick = new JButton("MODIFICA");
		btnEditNick.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditNick.setBounds(954, 205, 85, 21);
		frame.getContentPane().add(btnEditNick);

		textSearch = new JTextField();
		textSearch.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textSearch.setBounds(191, 60, 185, 19);
		frame.getContentPane().add(textSearch);
		textSearch.setColumns(10);

		JLabel lblSearch = new JLabel("CERCA");
		lblSearch.setBounds(89, 63, 45, 13);
		frame.getContentPane().add(lblSearch);

		btnReturn = new JButton("INDIETRO");
		btnReturn.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnReturn.setBounds(792, 361, 85, 21);
		frame.getContentPane().add(btnReturn);
		showUser();
		click();

		textNewSurname.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// se il formato del nuovo cognome è valido
				if(validator.isNameSurname(textNewSurname.getText())) {

					textNewSurname.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde
					lblErrSurn.setText("Formato valido"); // avviso da jlabel
					isOkSurname = true;

					// se il formato del nuovo cognome non è valido
				}else if(!validator.isNameSurname(textNewSurname.getText())){
					textNewSurname.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblErrSurn.setText("Solo caratteri in minuscolo"); // avviso da jlabel
					isOkSurname = false;
				}
			}

		});

		textNewName.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// se il formato del nuovo nome è valido
				if(validator.isNameSurname(textNewName.getText())) {
					textNewName.setBorder(new LineBorder(Color.GREEN,1));  // bordo si tinge di verde
					lblErrName.setText("Formato valido");// avviso da jlabel
					isOkName = true;

					// se il formato del nuovo nome non è valido
				}else if(!validator.isNameSurname(textNewName.getText())){
					textNewName.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblErrName.setText("Solo caratteri in minuscolo");// avviso da jlabel
					isOkName = false;
				}
			}

		});

		textNewNick.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				String result = proxy.checkNick(textNewNick.getText());
				// se il formato del nick è valido
				if(validator.isNickName(textNewNick.getText())) {
					if(result.equals("ESISTE")) { // se il nick scelto esiste gia ricevo un msg di avviso
						JOptionPane.showMessageDialog(frame, "Il nick scelto esiste già\nScegline un altro");
						checkNick = false;

					}else if(result.equals("NON ESISTE")) {
						checkNick = true;
					}

					textNewNick.setBorder(new LineBorder(Color.GREEN,1)); // bordo si riempie di verde
					lblErrNick.setText("Formato valido"); // avviso da jlabel
					isOkNick = true;

					// se il formato del nick non è valido
				}else if(!validator.isNickName(textNewNick.getText())){
					textNewNick.setBorder(new LineBorder(Color.RED,1)); // bordo si riempie di rosso
					lblErrNick.setText("Scegli almeno 4 caratteri");   // avviso da jlabel
					isOkNick = false;
				}
			}

		});

		btnEditSurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// se il nuovo cognome non corrisponde al formato o il campo cognome e nuovo cognome sono vuoti, ricevo un msg di errore
				if(isOkSurname == false || textNewSurname.getText().equals("") || textSurname.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica del cognome?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					String result = proxy.editSurname(textEmail.getText(), textNewSurname.getText());
					if(result.equals("UPDATE")) { // se l'aggiornamento va a buon fine
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						refresh();// jtable aggiornata
						clear();	 // campi resettati

					}else if(result.equals("NOT UPDATE")) { // se l'aggiornamento non va a buon fine
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		});


		btnEditName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// se il nuovo nome non corrisponde al formato o il campo nome e nuovo nome sono vuoti, ricevo un msg di errore
				if(isOkName == false || textNewName.getText().equals("") || textName.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica del nome?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
					String result = proxy.editName(textEmail.getText(), textNewName.getText());

					if(result.equals("UPDATE")) { // se l'aggiornamento va a buon fine
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						refresh(); // jtable aggiornata
						clear(); // campi resettati

					}else if(result.equals("NOT UPDATE")) { // se l'aggiornamento nn va a buon fine
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);

					}
				}

			}
		});

		btnEditNick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// se il nuovo nick non corrisponde al formato o il campo nick e nuovo nick sono vuoti, ricevo un msg di errore
				if(isOkNick == false || textNewNick.getText().equals("") || textNick.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;

				}else if (checkNick == false) { // se il nick scelto esiste ricevo un msg di errore
					JOptionPane.showMessageDialog(frame, "Errore: il nick scelto esiste già\n Scegline un altro", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica del nick?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
					String result = proxy.editNick(textEmail.getText(), textNewNick.getText());
					if(result.equals("UPDATE")) { // se l'aggiornamento va a buon fine
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						refresh(); // jtable aggiornata
						clear(); // campi resettati

					}else if(result.equals("NOT UPDATE")) { // se l'aggiornamento non va a buon fine
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		});

		// button di cancellazione utente
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// se il campo email è vuoto non posso procedere con la cancellazione
				if(textEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo email vuoto", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la cancellazione dell'account?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					String result = proxy.delete(textEmail.getText()); // passo l'email dell'utente come parametro per la cancellazione

					// se la cancellazione è andata a buon fine
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Cancellazione utente avvenuta con successo.");
						refresh(); // jtable viene aggiornata
						clear(); // campi resettati

						// se la cancellazione non è andata a buon fine
					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Cancellazione non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);

					}

				}


			}
		});

		// button di ritorno al menu principale
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				MainMenuAdmin mainMenuAdmin = new MainMenuAdmin(proxy, uid);
				mainMenuAdmin.frame.setLocationRelativeTo(null);
				mainMenuAdmin.frame.setVisible(true);
				frame.dispose();
			}
		});



		// se il campo di ricerca è vuoto tutte le jtexfield cognome,nome,nick,email saranno vuote
		textSearch.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if(textSearch.getText().equals("")) { // imposto le jtextfield con campo vuoto
					textSurname.setText("");
					textName.setText("");
					textNick.setText("");
					textEmail.setText("");
				}
				String word = textSearch.getText().toLowerCase(); // stringa di ricerca
				search(word);
			}



		});

	}

	// cerca un utente tramite jtable
	private void search(String word) {

		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(word));
	}



	//aggiorna la lista utenti
	private void refresh() {

		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		showUser();

	}




	// mostra la lista degli utente registrati
	private void showUser() {

		user = proxy.getUser(); // arraylist con i campi relativi a ogni utente registrato
		model = (DefaultTableModel)table.getModel();
		for(int i=0; i<user.size();i++) {

			rowUserList[0] = user.get(i).getSurname();
			rowUserList[1] = user.get(i).getName();
			rowUserList[2] = user.get(i).getNickName();
			rowUserList[3] = user.get(i).getType();
			rowUserList[4] = user.get(i).getEmail();
			rowUserList[5] = user.get(i).getPassword();
			model.addRow(rowUserList);

		}

	}

	// setta i campi della riga selezionata nella jtable,  nelle rispettive jtextfield
	private void click() {
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int sel=table.getSelectedRow();
				sel = table.convertRowIndexToModel(sel);
				model = (DefaultTableModel) table.getModel();
				textSurname.setText(model.getValueAt(sel, 0).toString());
				textName.setText(model.getValueAt(sel, 1).toString());
				textNick.setText(model.getValueAt(sel, 2).toString());
				textEmail.setText(model.getValueAt(sel, 5).toString());


			}

		});

	}


	// i campi vengono cancellati
	private void clear() {
		textSurname.setText("");
		textName.setText("");
		textNick.setText("");
		textEmail.setText("");
		textNewSurname.setText("");
		textNewName.setText("");
		textNewNick.setText("");
		lblErrSurn.setText("");
		lblErrName.setText("");
		lblErrNick.setText("");
		textNewSurname.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNewName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNewNick.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));


	}
}
