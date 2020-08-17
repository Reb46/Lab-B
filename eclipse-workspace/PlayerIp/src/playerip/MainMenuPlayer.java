package playerip;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import clientip.Proxy;

public class MainMenuPlayer {
	// elementi gui
	public JFrame frame;
	private JPanel panel;
	private JLabel lblMainAdmin;
	private JButton btnEditProfile;
	private JButton btnExit;
	private JButton btnNewGame;
	private	JButton btnGameList;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					String email = loginPlayer.getEmail();
					MainMenuPlayer window = new MainMenuPlayer(proxy,email);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainMenuPlayer(Proxy proxy,String email) {
		frame = new JFrame();
		frame.setBounds(100, 100, 604, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 588, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblMainAdmin = new JLabel("MAIN MENU PLAYER");
		lblMainAdmin.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblMainAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainAdmin.setForeground(Color.WHITE);
		lblMainAdmin.setBackground(new Color(255, 255, 255));
		lblMainAdmin.setBounds(120, 10, 330, 21);
		panel.add(lblMainAdmin);

		btnEditProfile = new JButton("MODIFICA PROFILO");
		btnEditProfile.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnEditProfile.setBounds(107, 91, 152, 21);
		frame.getContentPane().add(btnEditProfile);

		btnExit = new JButton("ESCI");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExit.setBounds(243, 216, 85, 21);
		frame.getContentPane().add(btnExit);

		btnNewGame = new JButton("NUOVA PARTITA");
		btnNewGame.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnNewGame.setBounds(328, 91, 152, 21);
		frame.getContentPane().add(btnNewGame);

		btnGameList = new JButton("LISTA MATCH");
		btnGameList.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnGameList.setBounds(205, 151, 152, 21);
		frame.getContentPane().add(btnGameList);


		// button per accedere al frame di modifica del proprio profilo
		btnEditProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditProfilePlayer editProfilePlayer = new EditProfilePlayer(proxy, email);
				editProfilePlayer.frame.setLocationRelativeTo(null);
				editProfilePlayer.frame.setVisible(true);
				frame.dispose();


			}
		});

		// button per accedere al frame di creazione di una nuova partita
		btnNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewGame newGame = new NewGame(proxy,email);
				newGame.frmNewGame.setLocationRelativeTo(null);
				newGame.frmNewGame.setVisible(true);
				frame.dispose();

			}
		});

		// button per effettuare il logout
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPlayer loginPlayer= new LoginPlayer(proxy);
				loginPlayer.frame.setLocationRelativeTo(null);
				loginPlayer.frame.setVisible(true);
				frame.dispose();
			}
		});


		// button per accedere alla lista delle partita organizzate
		btnGameList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GameList gameList = new GameList(proxy,email);
				gameList.frmGameList.setLocationRelativeTo(null);
				gameList.frmGameList.setVisible(true);
				frame.dispose();
			}
		});

	}
}
