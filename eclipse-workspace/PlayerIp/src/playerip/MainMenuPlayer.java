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

	public JFrame frame;


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

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 588, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblMainAdmin = new JLabel("MAIN MENU PLAYER");
		lblMainAdmin.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblMainAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainAdmin.setForeground(Color.WHITE);
		lblMainAdmin.setBackground(new Color(255, 255, 255));
		lblMainAdmin.setBounds(120, 10, 330, 21);
		panel.add(lblMainAdmin);

		JButton btnEditProfilePlr = new JButton("MODIFICA PROFILO");
		btnEditProfilePlr.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnEditProfilePlr.setBounds(107, 91, 152, 21);
		frame.getContentPane().add(btnEditProfilePlr);

		JButton btnExitPlayer = new JButton("ESCI");
		btnExitPlayer.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExitPlayer.setBounds(243, 216, 85, 21);
		frame.getContentPane().add(btnExitPlayer);

		JButton btnPlay = new JButton("NUOVA PARTITA");
		btnPlay.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnPlay.setBounds(328, 91, 152, 21);
		frame.getContentPane().add(btnPlay);

		JButton btnGameList = new JButton("LISTA MATCH");
		btnGameList.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnGameList.setBounds(205, 151, 152, 21);
		frame.getContentPane().add(btnGameList);


		btnEditProfilePlr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditProfilePlayer editProfilePlayer = new EditProfilePlayer(proxy, email);
				editProfilePlayer.frame.setLocationRelativeTo(null);
				editProfilePlayer.frame.setVisible(true);
				frame.dispose();


			}
		});

		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NewGame newGame = new NewGame(proxy,email);
				newGame.frmNewGame.setLocationRelativeTo(null);
				newGame.frmNewGame.setVisible(true);
				frame.dispose();

			}
		});

		btnExitPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPlayer loginPlayer= new LoginPlayer(proxy);
				loginPlayer.frame.setLocationRelativeTo(null);
				loginPlayer.frame.setVisible(true);
				frame.dispose();
			}
		});



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
