package playerip;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import entitiesip.Validator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import clientip.Proxy;
import entitiesip.UserIp;
import serverip.ThreadCheckActivation;
import javax.swing.border.BevelBorder;


public class RegisterPlayer {

	//elementi gui
	public JFrame frame;
	private JPanel panel;

	private JTextField textSurname;
	private JTextField textName;
	private JTextField textNick;
	private JTextField textEmail;
	private JTextField textCodeAct;
	private JTextField textType;

	private JPasswordField passwordField;
	private JPasswordField confPasswField;

	private JButton btnRegister;
	private JButton btnAct;

	private JLabel lblErrName;
	private JLabel lblErrNick;
	private JLabel lblErrSurn;
	private JLabel lblErrEmail;
	private JLabel lblErrPassw;
	private JLabel lblErrConf;
	private JLabel lblType;
	private JLabel lblTitle;
	private JLabel lblSurname;
	private JLabel lblName;
	private JLabel lblNick;
	private JLabel lblEmail;
	private JLabel lblPass;
	private JLabel lblConfPassw;
	private JLabel lblCodeAct;


	boolean isOKSurname;
	boolean isOKname;
	boolean isOKnick;
	boolean isOKemail;
	boolean isOKpassw;
	boolean isOKconfirm;
	boolean checkNick;
	boolean checkEmail;
	private UserIp userIp;
	private Validator validator = new Validator();
	private String type = "user";


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					RegisterPlayer window = new RegisterPlayer(proxy);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RegisterPlayer(Proxy proxy) {

		frame = new JFrame();
		frame.setBounds(100, 100, 581, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(580, 480);
		frame.setResizable(false);
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 576, 68);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblTitle = new JLabel("IP - REGISTRAZIONE PLAYER");
		lblTitle.setBounds(35, 5, 476, 34);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitle.setForeground(Color.WHITE);
		panel.add(lblTitle);

		lblSurname = new JLabel("Cognome");
		lblSurname.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblSurname.setBounds(10, 86, 134, 19);
		frame.getContentPane().add(lblSurname);

		lblName = new JLabel("Nome");
		lblName.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblName.setBounds(10, 122, 134, 13);
		frame.getContentPane().add(lblName);

		lblNick = new JLabel("NickName");
		lblNick.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblNick.setBounds(10, 158, 134, 13);
		frame.getContentPane().add(lblNick);

		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblEmail.setBounds(10, 194, 134, 13);
		frame.getContentPane().add(lblEmail);

		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblPass.setBounds(10, 230, 134, 13);
		frame.getContentPane().add(lblPass);

		lblConfPassw = new JLabel("Conferma Password");
		lblConfPassw.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblConfPassw.setBounds(10, 266, 134, 13);
		frame.getContentPane().add(lblConfPassw);

		lblCodeAct = new JLabel("Codice Attivazione");
		lblCodeAct.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblCodeAct.setBounds(10, 338, 134, 13);
		frame.getContentPane().add(lblCodeAct);

		textSurname = new JTextField();
		textSurname.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textSurname.setBounds(185, 86, 140, 19);
		frame.getContentPane().add(textSurname);
		textSurname.setColumns(10);

		textName = new JTextField();
		textName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textName.setColumns(10);
		textName.setBounds(185, 122, 140, 19);
		frame.getContentPane().add(textName);

		textNick = new JTextField();
		textNick.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNick.setColumns(10);
		textNick.setBounds(185, 158, 140, 19);
		frame.getContentPane().add(textNick);

		textEmail = new JTextField();
		textEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textEmail.setColumns(10);
		textEmail.setBounds(185, 194, 140, 19);
		frame.getContentPane().add(textEmail);

		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(185, 230, 140, 19);
		passwordField.getEchoChar();
		frame.getContentPane().add(passwordField);

		confPasswField = new JPasswordField();
		confPasswField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		confPasswField.setBounds(185, 266, 140, 19);
		frame.getContentPane().add(confPasswField);

		textCodeAct = new JTextField();
		textCodeAct.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textCodeAct.setColumns(10);
		textCodeAct.setBounds(185, 338, 140, 19);
		frame.getContentPane().add(textCodeAct);

		textType = new JTextField();
		textType.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textType.setEditable(false);
		textType.setBounds(185, 302, 140, 19);
		frame.getContentPane().add(textType);
		textType.setColumns(10);
		textType.setText(type);

		btnRegister = new JButton("Registra");
		btnRegister.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnRegister.setBounds(127, 399, 115, 21);
		frame.getContentPane().add(btnRegister);

		btnAct = new JButton("Attivazione");
		btnAct.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		btnAct.setBounds(301, 399, 115, 21);
		btnAct.setEnabled(false);
		frame.getContentPane().add(btnAct);


		lblErrSurn = new JLabel("");
		lblErrSurn.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblErrSurn.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrSurn.setBounds(350, 87, 196, 19);
		frame.getContentPane().add(lblErrSurn);

		lblErrName = new JLabel("");
		lblErrName.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrName.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblErrName.setBounds(350, 123, 196, 19);
		frame.getContentPane().add(lblErrName);

		lblErrNick = new JLabel("");
		lblErrNick.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrNick.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblErrNick.setBounds(350, 159, 196, 19);
		frame.getContentPane().add(lblErrNick);

		lblErrEmail = new JLabel("");
		lblErrEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrEmail.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblErrEmail.setBounds(350, 195, 196, 19);
		frame.getContentPane().add(lblErrEmail);

		lblErrPassw = new JLabel("");
		lblErrPassw.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrPassw.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblErrPassw.setBounds(350, 231, 196, 19);
		frame.getContentPane().add(lblErrPassw);

		lblErrConf = new JLabel("");
		lblErrConf.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrConf.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblErrConf.setBounds(350, 267, 196, 19);
		frame.getContentPane().add(lblErrConf);

		lblType = new JLabel("Tipo Utente");
		lblType.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblType.setBounds(10, 302, 134, 13);
		frame.getContentPane().add(lblType);



		textSurname.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// se il formato del cognome è valido
				if(validator.isNameSurname(textSurname.getText())) {

					textSurname.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde
					lblErrSurn.setText("Formato valido"); // avviso da jlabel
					isOKSurname = true;
				
				// se il formato non è valido
				}else if(!validator.isNameSurname(textSurname.getText())){
					textSurname.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di verde
					lblErrSurn.setText("Errore solo caratteri in minuscolo"); // avviso da jlabel
					isOKSurname = false;
				}


			}

		});


		textName.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// se il formato del nome è valido
				if(validator.isNameSurname(textName.getText())) {
					textName.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde
					lblErrName.setText("Formato valido"); // avviso da jlabel
					isOKname = true;
				// se il formato non è valido 
				}else if(!validator.isNameSurname(textName.getText())){
					textName.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblErrName.setText("Errore solo caratteri in minuscolo"); // avviso da jlabel
					isOKname = false;
				}
			}

		});

		textNick.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String result = proxy.checkNick(textNick.getText());
				// se il nick scelto è stato gia usato ricevo un avviso
				if(validator.isNickName(textNick.getText())) {
					if(result.equals("ESISTE")) {
						JOptionPane.showMessageDialog(frame, "Il nick scelto esiste già\nScegline un altro");
						checkNick = false;

						// altrimenti non ricevo nessun avviso
					}else if(result.equals("NON ESISTE")) {
						checkNick = true;

					}

					textNick.setBorder(new LineBorder(Color.GREEN,1));
					lblErrNick.setText("Formato valido");
					isOKnick = true;

				}else if(!validator.isNickName(textNick.getText())){
					textNick.setBorder(new LineBorder(Color.RED,1));
					lblErrNick.setText("Scegli almeno 4 caratteri");
					isOKnick = false;
				}
			}

		});

		textEmail.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String result = proxy.checkEmail(textEmail.getText());

				// se il formato dell'email è valido il bordo della jtextfield si tinge di verde e la jlabel mi informa che il formato è valido 
				if(validator.isEmailValid(textEmail.getText())) {
					textEmail.setBorder(new LineBorder(Color.GREEN,1));
					lblErrEmail.setText("Formato valido");
					isOKemail = true;

					// se l'email scelta è gia stata usata da un altro utente ricevo un avviso
					if(result.equals("ESISTE")){
						JOptionPane.showMessageDialog(frame, "L'email scelta esiste già\nScegline un'altra");

						checkEmail = false;

						// altrimenti non ricevo nessun avviso
					}else if(result.equals("NON ESISTE"))  {
						checkEmail = true;

					}

					// se il formato dell'email non è valido il bordo della jtextfield si tinge di rosso e la jlabel mi informa che il formato non è valido 
				}else if(!validator.isEmailValid(textEmail.getText())) {
					textEmail.setBorder(new LineBorder(Color.RED,1));
					lblErrEmail.setText("Formato email non valido");
					isOKemail = false;
				}

			}


		});


		passwordField.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				// se la password scelta rispetta i criteri dell'applicazione 
				if(validator.isPassword(passwordField.getText().toString())) {
					isOKpassw = true;
					passwordField.setBorder(new LineBorder(Color.GREEN)); // bordo si tinge di verde
					lblErrPassw.setText("Formato valido"); // avviso dalla jlabel


					// altrimenti 
				}else if(!validator.isPassword(passwordField.getText().toString())) {
					isOKpassw = false;
					passwordField.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblErrPassw.setText("Formato non valido"); // avviso dalla jlabel

				}
				empty(); // il campo viene azzerato se ritorno su passwordfield e devo reinserire la password scelta

			}

		});

		confPasswField.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				// se le due password inserite coincidono
				if(confPasswField.getText().toString().equals(passwordField.getText().toString())) {
					isOKconfirm = true;
					confPasswField.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde
					lblErrConf.setText("Le password sono uguali"); // avviso da jlabel

					// se le password non coincidono
				}else if(!passwordField.getText().toString().equals(confPasswField.getText().toString())) {
					isOKconfirm = false;
					confPasswField.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblErrConf.setText("Le password non sono uguali"); // avviso da jlabel

				}

			}

		});


		btnRegister.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				// se in fase di registrazione non tutti i campi sono alimentati ricevo un avviso di errore
				if(isOKSurname == false || isOKname == false || isOKnick == false || isOKemail == false || isOKpassw == false || isOKconfirm == false) {
					JOptionPane.showMessageDialog(frame,"NESSUN CAMPO VUOTO","Errore",JOptionPane.ERROR_MESSAGE);
					return;

					// se la mail o il nick sono stati scelti da altri utenti ricevo un messaggio di errore
				}else if(checkNick== false || checkEmail==false){
					JOptionPane.showMessageDialog(frame,"EMAIL O NICK GIA' PRESENTI","Errore",JOptionPane.ERROR_MESSAGE);

				}else {

					// se la registrazione va a buon fine ricevo un avviso
					userIp = new UserIp(textSurname.getText(), textName.getText(), textNick.getText(), textEmail.getText(), passwordField.getText(), type);
					String result = proxy.registerUser(userIp);
					if(result.equals("REGISTER OK"))
						new	ThreadCheckActivation(textNick.getText());
					JOptionPane.showMessageDialog(frame, "Registrazione avvenuta con successo\n Controlla la tua mail e inserisci il codice di attivazione");

					setFieldsFalse();// tutti i campi eccetto quelli di attivazione vengono disattivati

					btnAct.setEnabled(true); // il pulsante di attivazione viene attivato
				}


			}

		});

		// button attivazione utente
		btnAct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String result = proxy.activeUser(textNick.getText(), textCodeAct.getText());

				// se l'attivazione è andata a buon fine, ricevo un avviso e vengo portato nella schermata di login
				if(result.equals("CODICE OK")) {
					JOptionPane.showMessageDialog(frame, "Il tuo account è stato attivato " + textNick.getText());
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					loginPlayer.frame.setVisible(true);
					frame.dispose();
					// altrimenti dovro reinserire correttamente il codice
				}else if (result.equals("CODICE ERRATO")) {
					JOptionPane.showMessageDialog(frame, "Codice errato riprova", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}


			}
		});



	}

	// se l'utente dopo aver confermato la password ritorna nel campo superione password, dovrà nuovamente riconfermarla
	private void empty() {
		passwordField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {


			}

			@Override
			public void focusGained(FocusEvent e) {
				confPasswField.setText(""); // il campo settato come vuoto
				lblErrConf.setText("Le password non sono uguali");
				confPasswField.setBorder(new LineBorder(Color.RED));
				isOKconfirm = false;
			}
		});

	}

	// se la registrazione è andata a buon fine tutti i campi e il button register, eccetto quelli di attivazione, verranno disattivati
	private void setFieldsFalse() {

		textSurname.setEditable(false);
		textName.setEditable(false);
		textNick.setEditable(false);
		textEmail.setEditable(false);
		passwordField.setEditable(false);
		passwordField.setEnabled(false);
		confPasswField.setEditable(false);
		btnRegister.setEnabled(false);
	}
}