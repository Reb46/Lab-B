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

	public JFrame frame;
	private JTextField textNamePlayer;
	private JTextField textSurnPlayer;
	private JTextField textNickPlayer;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textNick;
	private JTextField textViewEmail;
	private Validator validator = new Validator();
	private JPanel panel = new JPanel();
	boolean isOkName;
	boolean isOkPw;
	boolean isOkSurname;
	boolean isOknick;
	boolean checkNick;
	boolean checkOld;
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
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 9));
		frame.setBounds(100, 100, 562, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
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
		String name = proxy.getName(email);
		textNamePlayer.setText(name);

		textSurnPlayer = new JTextField();
		textSurnPlayer.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textSurnPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		textSurnPlayer.setEditable(false);
		textSurnPlayer.setBounds(135, 140, 96, 19);
		frame.getContentPane().add(textSurnPlayer);
		textSurnPlayer.setColumns(10);
		String surname = proxy.getSurname(email);
		textSurnPlayer.setText(surname);

		textNickPlayer = new JTextField();
		textNickPlayer.setFont(new Font("Century Gothic", Font.BOLD, 10));
		textNickPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		textNickPlayer.setEditable(false);
		textNickPlayer.setBounds(135, 205, 96, 19);
		frame.getContentPane().add(textNickPlayer);
		textNickPlayer.setColumns(10);
		String nick = proxy.getNick(email);
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
				if(validator.isNameSurname(textName.getText())) {
					textName.setBorder(new LineBorder(Color.GREEN,1));
					lblCheckName.setText("Formato valido");
					isOkName = true;
				}else if(!validator.isNameSurname(textName.getText())){
					textName.setBorder(new LineBorder(Color.RED,1));
					lblCheckName.setText("Solo caratteri in minuscolo");
					isOkName = false;
				}
			}

		});
		
		
		btnEditName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				if(isOkName == false || textName.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
					String result = proxy.editName(email, textName.getText());

					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();

					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
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
				if(validator.isNameSurname(textSurname.getText())) {

					textSurname.setBorder(new LineBorder(Color.GREEN,1));
					lblCheckSurn.setText("Formato valido");
					isOkSurname = true;
				}else if(!validator.isNameSurname(textSurname.getText())){
					textSurname.setBorder(new LineBorder(Color.RED,1));
					lblCheckSurn.setText("Solo caratteri in minuscolo");
					isOkSurname = false;
				}

			}
		});

		btnEditSurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(isOkSurname == false || textSurname.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}


				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					String result = proxy.editSurname(email, textSurname.getText());



					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();

					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
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
				String result = proxy.checkNick(textNick.getText());

				if(validator.isNickName(textNick.getText())) {
					if(result.equals("ESISTE")) {
						JOptionPane.showMessageDialog(frame, "Il nick scelto esiste gi�\nScegline un altro");
						checkNick = false;

					}else if(result.equals("NON ESISTE")) {
						checkNick = true;

					}

					textNick.setBorder(new LineBorder(Color.GREEN,1));
					lblCheckNick.setText("Formato valido");
					isOknick = true;
				}else if(!validator.isNickName(textNick.getText())){
					textNick.setBorder(new LineBorder(Color.RED,1));
					lblCheckNick.setText("Scegli almeno 3 caratteri");
					isOknick = false;
				}
			}

		});

		btnEditNick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(isOknick == false || textNick.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Errore: campo vuoto o non conforme", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}else if (checkNick == false) {
					JOptionPane.showMessageDialog(frame, "Errore: il nick scelto esiste gi�\n Scegline un altro", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {
					String result = proxy.editNick(email, textNick.getText());
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();

					}else if(result.equals("NOT UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
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

				String pw = validator.sha1(passwordOld.getText());

				String result = proxy.checkPassword(email, pw);
				if(result.equals("EQUAL")){
					checkOld = true;
					lblCheckOld.setText("PASSWORD ESATTA");
					passwordOld.setBorder(new LineBorder(Color.GREEN,1));
				}else {
					checkOld = false;	
					lblCheckOld.setText("PASSWORD ERRATA");
					passwordOld.setBorder(new LineBorder(Color.RED,1));
				}

			}



		});
		passwordNew.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				if(validator.isPassword(passwordNew.getText())) {
					isOkPw = true;
					passwordNew.setBorder(new LineBorder(Color.GREEN));
					lblCheckNew.setText("Formato valido");

				}else if(!validator.isPassword(passwordNew.getText())) {
					isOkPw = false;
					passwordNew.setBorder(new LineBorder(Color.RED,1));
					lblCheckNew.setText("Formato non valido");

				}
			}

		});

		btnEditPw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(checkOld == false|| isOkPw == false) {
					JOptionPane.showMessageDialog(frame, "La vecchia password non coincide o\n formato non valido", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					@SuppressWarnings("deprecation")
					String result = proxy.editPassword(email, passwordNew.getText());
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						LoginPlayer loginPlayer = new LoginPlayer(proxy);
						loginPlayer.frame.setLocationRelativeTo(null);
						loginPlayer.frame.setVisible(true);
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frame.dispose();
					}
				}


			}
		});

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
