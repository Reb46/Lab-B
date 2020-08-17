package playerip;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import clientip.Proxy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class PleaseWait {

	//elementi gui
	public JFrame frmPlease;
	private ImageIcon loading;
	private JButton btnExit;
	private JPanel panel;
	private JLabel lblTitle;
	private JLabel lblInfo;
	private JLabel lblPleaseAwait;
	
	
	private String nick = "";
	private String nomePartita = "";
	boolean flag = true;
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proxy proxy = new Proxy();
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					String email  = loginPlayer.getEmail();
					GameList gameList = new GameList(proxy, email);
					String nomePartita = gameList.getNamePartita();
					PleaseWait window = new PleaseWait(proxy,email,nomePartita);
					window.frmPlease.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public PleaseWait(Proxy proxy,String email,String nameGame)  {
		frmPlease = new JFrame();
		frmPlease.setBounds(100, 100, 450, 300);
		frmPlease.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loading = new ImageIcon("attendi.gif");
		nick = proxy.getNick(email);
		
		nomePartita = nameGame;
		frmPlease.getContentPane().setLayout(null);
		
		lblPleaseAwait = new JLabel("Attendi l'inzio del match " + nick, loading, JLabel.HORIZONTAL);
		lblPleaseAwait.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblPleaseAwait.setBounds(0, 84, 436, 63);
		frmPlease.getContentPane().add(lblPleaseAwait);

		btnExit = new JButton("ABBANDONA");
		btnExit.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnExit.setBounds(282, 219, 133, 21);
		frmPlease.getContentPane().add(btnExit);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 436, 48);
		frmPlease.getContentPane().add(panel);
		panel.setLayout(null);

		lblTitle = new JLabel("IL PAROLIERE");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblTitle.setBounds(38, 10, 328, 21);
		panel.add(lblTitle);

		lblInfo = new JLabel("COMPLIMENTI SEI STATO AGGIUNTO ALLA PARTITA");
		lblInfo.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(25, 62, 362, 13);
		frmPlease.getContentPane().add(lblInfo);

		// abbandono partita
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Confermi l'abbandono della partita?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				// se durante l'attesa abbandono la partita e sono l'unico iscritto vengo eliminato dalla partita e quest'ultima annullata. 
				//Ritorno al menu principale
				if(response == JOptionPane.YES_OPTION) {
					String result = proxy.setAbbandono(nomePartita, nick); 
					if(result.equals("ANNULLATA LISTA VUOTA")) {
						JOptionPane.showMessageDialog(frmPlease, "PARTITA ANNULLATA");
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frmPlease.dispose();
					
						// altrimenti se non sono l'unico iscritto alla partita vengo eliminato dal match e ritorno al menu principale
					}else if (result.equals("UTENTE ELIMINATO")) {
						JOptionPane.showMessageDialog(frmPlease, "ELIMINATO DAL MATCH");
						MainMenuPlayer mainMenuPlayer = new MainMenuPlayer(proxy, email);
						mainMenuPlayer.frame.setLocationRelativeTo(null);
						mainMenuPlayer.frame.setVisible(true);
						frmPlease.dispose();
					}
				}
			}
		});





	}


}