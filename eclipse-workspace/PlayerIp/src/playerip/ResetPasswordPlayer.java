package playerip;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clientip.Proxy;
import javax.swing.border.BevelBorder;



public class ResetPasswordPlayer {

	public JFrame frame;
	private JTextField textEmailPlayer;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					ResetPasswordPlayer window = new ResetPasswordPlayer(proxy);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ResetPasswordPlayer(Proxy proxy) {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 263);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 436, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("RESET PASSWORD PLAYER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, 10, 364, 22);
		panel.add(lblNewLabel);

		JLabel lblEmailRpl = new JLabel("EMAIL");
		lblEmailRpl.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblEmailRpl.setBounds(60, 79, 85, 13);
		frame.getContentPane().add(lblEmailRpl);

		textEmailPlayer = new JTextField();
		textEmailPlayer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textEmailPlayer.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		textEmailPlayer.setBounds(149, 76, 174, 19);
		frame.getContentPane().add(textEmailPlayer);
		textEmailPlayer.setColumns(10);

		JLabel lblInfo = new JLabel("<html>Inserisci la mail utilizzata per la registrazione. " +
				"Ricevereai una nuova password</html>");
		lblInfo.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblInfo.setBounds(90, 168, 300, 34);
		frame.getContentPane().add(lblInfo);

		JButton btnSendMail = new JButton("INVIA");
		btnSendMail.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnSendMail.setBounds(149, 128, 85, 21);
		frame.getContentPane().add(btnSendMail);
		
		JButton btnExit = new JButton("ESCI");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExit.setBounds(257, 129, 85, 21);
		frame.getContentPane().add(btnExit);


		btnSendMail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = proxy.resetPassword(textEmailPlayer.getText());
				if(result.equals("ESISTE")) {
					JOptionPane.showMessageDialog(frame, "Hai ricevuto una mail con una nuova password");
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					loginPlayer.frame.setLocationRelativeTo(null);
					loginPlayer.frame.setVisible(true);
					frame.dispose();
				}else if(result.equals("NON ESISTE")) {
					JOptionPane.showMessageDialog(frame, "L'email inserita non esiste", "ERRORE", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
			btnExit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					loginPlayer.frame.setLocationRelativeTo(null);
					loginPlayer.frame.setVisible(true);
					frame.dispose();
				}
			});
	}

}