package playerip;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import clientip.Proxy;
import entitiesip.Validator;
import javax.swing.border.BevelBorder;

public class EditProfilePlayer {

	// elementi gui
	public JFrame frame;
	private JTextField textNamePlayer;
	private JTextField textSurnPlayer;
	private JTextField textNickPlayer;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textNick;
	private JTextField textViewEmail;
	private JPanel panel;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	private JButton btnEditName;
	private JButton btnEditSurn;
	private JButton btnEditNick;
	private JButton btnEditPw;
	private JButton btnMain;
	private JLabel lblNamePlayer;
	private JLabel lblEditAdmin;
	private JLabel lblSurnPlayer;
	private JLabel lblNickPlayer;
	private JLabel lblPasswPlayer;
	private JLabel lblCheckName;
	private JLabel lblCheckSurn;
	private JLabel lblCheckNick;
	private JLabel lblEmailPlayer;
	private JLabel lblOldPw;
	private JLabel lblNewPw;
	private JLabel lblCheckOld;
	private JLabel lblCheckNew;


	boolean isOkName;
	boolean isOkPw;
	boolean isOkSurname;
	boolean isOknick;
	boolean checkNick;
	boolean checkOld;
	private Validator validator;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					String email = loginPlayer.getEmail();
					EditProfilePlayer window = new EditProfilePlayer(proxy,email);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public EditProfilePlayer(Proxy proxy,String email)  {
		validator = new Validator();
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 9));
		frame.setBounds(100, 100, 562, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 548, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblEditAdmin = new JLabel("IP - MODIFICA PROFILO PLAYER");
		lblEditAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAdmin.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblEditAdmin.setForeground(Color.WHITE);
		lblEditAdmin.setBounds(10, 10, 538, 25);
		panel.add(lblEditAdmin);

		lblNamePlayer = new JLabel("NOME");
		lblNamePlayer.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblNamePlayer.setBounds(10, 83, 46, 13);
		frame.getContentPane().add(lblNamePlayer);

		lblSurnPlayer = new JLabel("COGNOME");
		lblSurnPlayer.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblSurnPlayer.setBounds(10, 141, 80, 13);
		frame.getContentPane().add(lblSurnPlayer);

		lblNickPlayer = new JLabel("NICKNAME");
		lblNickPlayer.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblNickPlayer.setBounds(10, 206, 74, 13);
		frame.getContentPane().add(lblNickPlayer);

		lblPasswPlayer = new JLabel("PASSWORD");
		lblPasswPlayer.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblPasswPlayer.setBounds(10, 275, 77, 13);
		frame.getContentPane().add(lblPasswPlayer);

		textNamePlayer = new JTextField();
		textNamePlayer.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textNamePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		textNamePlayer.setEditable(false);
		textNamePlayer.setBounds(135, 82, 96, 19);
		frame.getContentPane().add(textNamePlayer);
		textNamePlayer.setColumns(10);
		String name = proxy.getName(email); // recupero il nome dell'utente
		textNamePlayer.setText(name);

		textSurnPlayer = new JTextField();
		textSurnPlayer.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textSurnPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		textSurnPlayer.setEditable(false);
		textSurnPlayer.setBounds(135, 140, 96, 19);
		frame.getContentPane().add(textSurnPlayer);
		textSurnPlayer.setColumns(10);
		String surname = proxy.getSurname(email); // recupero il cognome dell'utente
		textSurnPlayer.setText(surname);

		textNickPlayer = new JTextField();
		textNickPlayer.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textNickPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		textNickPlayer.setEditable(false);
		textNickPlayer.setBounds(135, 205, 96, 19);
		frame.getContentPane().add(textNickPlayer);
		textNickPlayer.setColumns(10);
		String nick = proxy.getNick(email); // recupero il nick dell'utente
		textNickPlayer.setText(nick);

		textName = new JTextField();
		textName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textName.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		textName.setBounds(263, 82, 96, 19);
		frame.getContentPane().add(textName);
		textName.setColumns(10);

		textSurname = new JTextField();
		textSurname.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textSurname.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		textSurname.setBounds(263, 140, 96, 19);
		frame.getContentPane().add(textSurname);
		textSurname.setColumns(10);

		textNick = new JTextField();
		textNick.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNick.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		textNick.setBounds(263, 205, 96, 19);
		frame.getContentPane().add(textNick);
		textNick.setColumns(10);

		btnEditName = new JButton("MOFICA NOME");
		btnEditName.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditName.setBounds(395, 81, 143, 21);
		frame.getContentPane().add(btnEditName);

		btnEditSurn = new JButton("MODIFICA COGNOME");
		btnEditSurn.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditSurn.setBounds(395, 139, 143, 21);
		frame.getContentPane().add(btnEditSurn);

		btnEditNick = new JButton("MODIFICA NICK");
		btnEditNick.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditNick.setBounds(395, 204, 143, 21);
		frame.getContentPane().add(btnEditNick);

		btnEditPw = new JButton("MODIFICA PASSWORD");
		btnEditPw.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditPw.setBounds(395, 273, 143, 21);
		frame.getContentPane().add(btnEditPw);

		lblCheckName = new JLabel("");
		lblCheckName.setFont(new Font("Century Gothic", Font.BOLD, 8));
		lblCheckName.setBounds(263, 111, 96, 13);
		frame.getContentPane().add(lblCheckName);

		lblCheckSurn = new JLabel("");
		lblCheckSurn.setFont(new Font("Century Gothic", Font.BOLD, 8));
		lblCheckSurn.setBounds(263, 171, 96, 13);
		frame.getContentPane().add(lblCheckSurn);

		lblCheckNick = new JLabel("");
		lblCheckNick.setFont(new Font("Tahoma", Font.BOLD, 8));
		lblCheckNick.setBounds(263, 234, 96, 13);
		frame.getContentPane().add(lblCheckNick);

		lblEmailPlayer = new JLabel("EMAIL");
		lblEmailPlayer.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 12));
		lblEmailPlayer.setBounds(10, 341, 46, 13);
		frame.getContentPane().add(lblEmailPlayer);

		textViewEmail = new JTextField();
		textViewEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textViewEmail.setEditable(false);
		textViewEmail.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textViewEmail.setBounds(135, 341, 143, 19);
		frame.getContentPane().add(textViewEmail);
		textViewEmail.setColumns(10);
		textViewEmail.setText(email);

		lblOldPw = new JLabel("PASSWORD ATTUALE");
		lblOldPw.setFont(new Font("Constantia", Font.BOLD, 8));
		lblOldPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblOldPw.setBounds(136, 255, 95, 13);
		frame.getContentPane().add(lblOldPw);

		lblNewPw = new JLabel("NUOVA PASSWORD");
		lblNewPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPw.setFont(new Font("Constantia", Font.BOLD, 8));
		lblNewPw.setBounds(263, 255, 96, 13);
		frame.getContentPane().add(lblNewPw);

		passwordOld = new JPasswordField();
		passwordOld.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordOld.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		passwordOld.setBounds(135, 274, 96, 19);
		frame.getContentPane().add(passwordOld);

		passwordNew = new JPasswordField();
		passwordNew.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordNew.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		passwordNew.setBounds(263, 274, 96, 19);
		frame.getContentPane().add(passwordNew);

		lblCheckOld = new JLabel("");
		lblCheckOld.setFont(new Font("Century Gothic", Font.BOLD, 8));
		lblCheckOld.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOld.setBounds(135, 305, 96, 13);
		frame.getContentPane().add(lblCheckOld);

		lblCheckNew = new JLabel("");
		lblCheckNew.setFont(new Font("Century Gothic", Font.BOLD, 8));
		lblCheckNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckNew.setBounds(263, 303, 96, 13);
		frame.getContentPane().add(lblCheckNew);

		btnMain = new JButton("INDIETRO");
		btnMain.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMain.setBounds(205, 401, 103, 19);
		frame.getContentPane().add(btnMain);


		textName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// se il nuovo nome scelto ha un formato valido
				if(validator.isNameSurname(textName.getText())) {
					textName.setBorder(new LineBorder(Color.GREEN,1)); // bordo si riempie di verde
					lblCheckName.setText("Formato valido"); // avviso da jlabel
					isOkName = true;

					//altrimenti se non è valido
				}else if(!validator.isNameSurname(textName.getText())){
					textName.setBorder(new LineBorder(Color.RED,1)); // bordo si riempie di rosso
					lblCheckName.setText("Solo caratteri in minuscolo"); // avviso da jlabel
					isOkName = false;
				}
			}

		});


		btnEditName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// se il formato del nome non è valido o il campo è vuoto ricevo un avviso di errore
				if(isOkName == false || textName.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
					String result = proxy.editName(email, textName.getText());
					// se la modifica avviene con successo
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo."); // ricevo un msg di avviso
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email); // vengo portato al menu principale
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();

						//// se la modifica non viene effettuata
					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE); // ricevo msg di errore
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email); // vengo portato al menu principale
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();
					}
				}

			}
		});


		textSurname.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				// se il nuovo cognome scelto ha un formato valido
				if(validator.isNameSurname(textSurname.getText())) {
					textSurname.setBorder(new LineBorder(Color.GREEN,1)); // il bordo si riempie di verde
					lblCheckSurn.setText("Formato valido"); // avviso da jlabel
					isOkSurname = true;

					// se il formato non è valido
				}else if(!validator.isNameSurname(textSurname.getText())){
					textSurname.setBorder(new LineBorder(Color.RED,1));  // il bordo si riempie di rosso
					lblCheckSurn.setText("Solo caratteri in minuscolo"); // avviso da jlabel
					isOkSurname = false;
				}

			}
		});


		btnEditSurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// se il formato del cognome non è valido o il campo è vuoto ricevo un avviso di errore
				if(isOkSurname == false || textSurname.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					String result = proxy.editSurname(email, textSurname.getText());


					// se la modifica avviene con successo
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo."); // ricevo un msg di avviso
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email); // vengo portato al menu principale
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();

					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);// ricevo un msg di errore
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);// vengo portato al menu principale
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();
					}
				}
			}
		});


		textNick.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String result = proxy.checkNick(textNick.getText()); // controllo che il nuovo nick non sia stato scelto da un altro utente

				// se il formato del nick è valido
				if(validator.isNickName(textNick.getText())) {
					if(result.equals("ESISTE")) { // se il nuovo nick scelto esiste gia
						JOptionPane.showMessageDialog(frame, "Il nick scelto esiste già\nScegline un altro"); // ricevo un msg di avviso
						checkNick = false;

					}else if(result.equals("NON ESISTE")) { // altrimenti se il nick esiste
						checkNick = true;

					}

					textNick.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde
					lblCheckNick.setText("Formato valido");	// avviso da jlabel
					isOknick = true;

					// se il formato non è valido
				}else if(!validator.isNickName(textNick.getText())){
					textNick.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblCheckNick.setText("Scegli almeno 3 caratteri"); // avviso da jlabel
					isOknick = false;
				}
			}

		});

		btnEditNick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// se il formato del nick non è valido o il campo è vuoto ricevo un avviso di errore
				if(isOknick == false || textNick.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
					// il nick esiste ricevo un msg di errore
				}else if (checkNick == false) {
					JOptionPane.showMessageDialog(frame, "Errore: il nick scelto esiste già\n Scegline un altro", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
					// se la modifica avviene con successo
					String result = proxy.editNick(email, textNick.getText());
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");// ricevo un msg di avviso
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email); // vengo portato al menu principale
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();
						// se la modifica non avviene con successo
					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);// ricevo un msg di errore
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);// vengo portato al menu principale
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();
					}
				}
			}
		});


		passwordOld.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {

				// l'inserimento della vecchia password viene criptato come avvenuto in fase di registrazione nel db
				String pw = validator.sha1(passwordOld.getText());

				String result = proxy.checkPassword(email, pw);
				if(result.equals("EQUAL")){ // se la password coincide con quella usata in fase di registrazione
					checkOld = true;
					lblCheckOld.setText("PASSWORD ESATTA"); // avviso da jlabel
					passwordOld.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde

				}else { // altrimenti se è errata
					checkOld = false;	
					lblCheckOld.setText("PASSWORD ERRATA"); // avviso da jlabel
					passwordOld.setBorder(new LineBorder(Color.RED,1));//// bordo si tinge di rosso
				}

			}



		});

		passwordNew.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				// se il formato della nuova password è valido
				if(validator.isPassword(passwordNew.getText())) {
					isOkPw = true;
					passwordNew.setBorder(new LineBorder(Color.GREEN)); // il bordo si riempie di verde
					lblCheckNew.setText("Formato valido"); // avviso da jlabel

				}else if(!validator.isPassword(passwordNew.getText())) { // se il formato non è valido
					isOkPw = false;
					passwordNew.setBorder(new LineBorder(Color.RED,1)); // bordo si riempie di rosso		
					lblCheckNew.setText("Formato non valido"); // avviso da jlabel

				}
			}

		});

		// button modifica password
		btnEditPw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// se la vecchia password non coincide o il formato della password non è valido ricevo un avviso di errore
				if(checkOld == false || isOkPw == false) {
					JOptionPane.showMessageDialog(frame, "La vecchia password non coincide o\n formato non valido", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					@SuppressWarnings("deprecation")
					String result = proxy.editPassword(email, passwordNew.getText());
					
					// se l'aggiornamento della password avviene con successo
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");// ricevo un messaggio di avviso
						LoginPlayer loginPlayer = new LoginPlayer(proxy);// vengo riportato nella schermata di login
						loginPlayer.frame.setLocationRelativeTo(null);
						loginPlayer.frame.setVisible(true);
						frame.dispose();
					}else { // altrimenti ricevo un messaggio di errore
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();
					}
				}


			}
		});

		// button di ritorno al menu principale
		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuPlayer mainMenuPlayer =  new MainMenuPlayer(proxy, email);
				mainMenuPlayer.frame.setLocationRelativeTo(null);
				mainMenuPlayer.frame.setVisible(true);
				frame.dispose();
			}
		});


	}
}
