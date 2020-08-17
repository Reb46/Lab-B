package playerip;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import clientip.Proxy;
import entitiesip.Validator;
import javax.swing.border.BevelBorder;

public class LoginPlayer {

	// elementi gui
	public JFrame frame;
	private JPanel panel;
	private JTextField textUserEmail;
	private JPasswordField passwordField;
	private JLabel lblTextLogin;
	private JLabel lblUserEmail;
	private JLabel lblPassword;
	private JLabel lblLinkRegister;
	private JLabel lblForgotPassw;
	private JButton btnLogin;
	private JButton btnExit;
	
	public String email ="";
	private String typeUser = "user";
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();

					LoginPlayer window = new LoginPlayer(proxy);

					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginPlayer(Proxy proxy) {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 421, 46);
		frame.getContentPane().add(panel);

		lblTextLogin = new JLabel("IP - LOGIN PLAYER");
		lblTextLogin.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblTextLogin.setForeground(Color.WHITE);
		panel.add(lblTextLogin);

		lblUserEmail = new JLabel("EMAIL");
		lblUserEmail.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		lblUserEmail.setBounds(10, 83, 104, 13);
		frame.getContentPane().add(lblUserEmail);

		textUserEmail = new JTextField();
		textUserEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textUserEmail.setBounds(124, 82, 156, 19);
		frame.getContentPane().add(textUserEmail);
		textUserEmail.setColumns(10);

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(10, 131, 96, 13);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(124, 130, 156, 19);
		frame.getContentPane().add(passwordField);

		btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		btnLogin.setBounds(107, 181, 85, 21);
		frame.getContentPane().add(btnLogin);

		btnExit = new JButton("ESCI");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		btnExit.setBounds(231, 181, 85, 21);
		frame.getContentPane().add(btnExit);

		lblLinkRegister = new JLabel("Non hai un account? Clicca per registrarti");
		lblLinkRegister.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblLinkRegister.setBounds(81, 228, 269, 13);
		frame.getContentPane().add(lblLinkRegister);

		lblForgotPassw = new JLabel("Non ricordi la password? Clicca qui");
		lblForgotPassw.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblForgotPassw.setBounds(81, 260, 247, 13);
		frame.getContentPane().add(lblForgotPassw);

		// jlabel che mi permette di accedere alla finestra di registrazione
		lblLinkRegister.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterPlayer registerPlayer = new RegisterPlayer(proxy);
				registerPlayer.frame.setLocationRelativeTo(null);
				registerPlayer.frame.setVisible(true);
				frame.dispose();

			}

		});
		// jlabel che mi permette di accedere alla finestra per il reset della password
		lblForgotPassw.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ResetPasswordPlayer resetPasswordPlayer = new ResetPasswordPlayer(proxy);
				resetPasswordPlayer.frame.setLocationRelativeTo(null);
				resetPasswordPlayer.frame.setVisible(true);
				frame.dispose();
			}

		});

		// button per effettuare il login
		btnLogin.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				Validator validator = new Validator();
				String pw = validator.sha1(passwordField.getText());
				
				String result = proxy.login(textUserEmail.getText(),pw,typeUser);
				email= textUserEmail.getText();
				
				// se la l'email e la password coincidono con quelle scelte in fase di registrazione ricevo un avviso
				if(result.equals("EXIST")) {
					JOptionPane.showMessageDialog(frame, "LOGIN EFFETTUATO CON SUCCESSO");
					MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy,email);// vengo portato al menu principale
					mainMenuPlayer.frame.setLocationRelativeTo(null);
					mainMenuPlayer.frame.setVisible(true);
					frame.dispose();
				
					//altrimenti ricevo un avviso di errore
				}else if (result.equals("NOT EXIST")) {
					JOptionPane.showMessageDialog(frame, "EMAIL O PASSWORD NON CORRETTI", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;	
				}
			}
		});

		// button di uscita dall'applicazione
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

	}
	// metodo public di apoggio che ritorna l'email dell'utente
	public String getEmail() {
		return email;
	}

}
