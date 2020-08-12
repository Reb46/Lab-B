package adminip;


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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clientip.Proxy;

import entitiesip.Validator;

public class LoginAdmin {

	public JFrame frame;
	public JTextField textAdminUid;
	private JPasswordField passwordField;
	public String uid ="";
	private String type = "admin";
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					LoginAdmin window = new LoginAdmin(proxy);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LoginAdmin(Proxy proxy) {
		frame = new JFrame();
		frame.setBounds(100, 100, 389, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 375, 48);
		frame.getContentPane().add(panel);

		JLabel lblTextLogin = new JLabel("IP - LOGIN ADMIN");
		lblTextLogin.setFont(new Font("Century Gothic", Font.PLAIN, 28));
		lblTextLogin.setForeground(Color.WHITE);
		panel.add(lblTextLogin);

		JLabel lblAdminUid = new JLabel("UID");
		lblAdminUid.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		lblAdminUid.setBounds(10, 83, 104, 13);
		frame.getContentPane().add(lblAdminUid);

		textAdminUid = new JTextField();
		textAdminUid.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		textAdminUid.setBounds(124, 82, 156, 19);
		frame.getContentPane().add(textAdminUid);
		textAdminUid.setColumns(10);

		JLabel lblNewLabel = new JLabel("PASSWORD");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(10, 131, 96, 13);
		frame.getContentPane().add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		passwordField.setBounds(124, 130, 156, 19);
		frame.getContentPane().add(passwordField);

		JButton btnLoginAdmin = new JButton("LOGIN");
		btnLoginAdmin.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		btnLoginAdmin.setBounds(90, 193, 85, 21);
		frame.getContentPane().add(btnLoginAdmin);

		JButton btnExit = new JButton("ESCI");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		btnExit.setBounds(214, 193, 85, 21);
		frame.getContentPane().add(btnExit);

		btnLoginAdmin.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				Validator validator = new Validator();

				
				String pw = validator.sha1(passwordField.getText());
				String result = proxy.loginAdmin(textAdminUid.getText(), pw, type);
				uid = textAdminUid.getText();
				if(result.equals("EXIST")) {
					JOptionPane.showMessageDialog(frame, "LOGIN EFFETTUATO CON SUCCESSO");
					MainMenuAdmin mainMenuAdmim = new MainMenuAdmin(proxy,getUid());
					mainMenuAdmim.frame.setLocationRelativeTo(null);
					mainMenuAdmim.frame.setVisible(true);
					frame.dispose();
				}else if (result.equals("NOT EXIST")) {
					JOptionPane.showMessageDialog(frame, "EMAIL O PASSWORD NON CORRETTI", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;	
				}
			}
		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

	}

	public String getUid() {
		return uid;

	}

}
