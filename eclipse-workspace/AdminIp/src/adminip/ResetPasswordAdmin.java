package adminip;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import clientip.Proxy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ResetPasswordAdmin {
	//elementi gui
	private JFrame frame;
	private JTextField textEmail;
	private JPanel panel;
	private JLabel lblTitle;
	private JLabel lblEmail;
	private JLabel lblInfo;
	private JButton btnSendAdmin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					ResetPasswordAdmin window = new ResetPasswordAdmin(proxy);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ResetPasswordAdmin(Proxy proxy) {

		frame = new JFrame();
		frame.setBounds(100, 100, 493, 263);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 488, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblTitle = new JLabel("RESET PASSWORD ADMIN");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(40, 10, 396, 28);
		panel.add(lblTitle);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblEmail.setBounds(60, 79, 85, 13);
		frame.getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		textEmail.setBounds(149, 76, 174, 19);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		lblInfo = new JLabel("<html>Inserisci la mail utilizzata per la registrazione. " +
				"Ricevereai una nuova password</html>");
		lblInfo.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblInfo.setBounds(90, 168, 300, 34);
		frame.getContentPane().add(lblInfo);

		btnSendAdmin = new JButton("INVIA");
		btnSendAdmin.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnSendAdmin.setBounds(188, 118, 85, 21);
		frame.getContentPane().add(btnSendAdmin);



		// button di invio mail
		btnSendAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String result = proxy.resetPassword(textEmail.getText());
				if(result.equals("ESISTE")) { // se la mail è corretta viene inviata una nuova password 
					JOptionPane.showMessageDialog(frame, "Hai ricevuto una mail con una nuova password");
					LoginAdmin loginAdmin = new LoginAdmin(proxy); // l'utente viene portato nella schermata di login
					loginAdmin.frame.setLocationRelativeTo(null);
					loginAdmin.frame.setVisible(true);
					frame.dispose();
				}else if(result.equals("NON ESISTE")) { // altrimenti ricevo un avviso di errore
					JOptionPane.showMessageDialog(frame, "L'email inserita non esiste", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});


	}
}
