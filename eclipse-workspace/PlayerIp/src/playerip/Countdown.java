package playerip;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import clientip.Proxy;
import javax.swing.JPanel;
import java.awt.Color;

public class Countdown {

	public JFrame frmCountDown;
	private JLabel lblCountdown;
	private JPanel panel;
	private JLabel lblNewLabel;
	Boolean flag = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proxy proxy = new Proxy();
					String nomePartita = "";
					LoginPlayer loginPlayer = new LoginPlayer(proxy);
					String email = loginPlayer.getEmail();
					Countdown window = new Countdown(proxy,email,nomePartita);
					window.frmCountDown.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Countdown(Proxy proxy,String email,String nomePartita)  {
		frmCountDown = new JFrame();
		frmCountDown.setBounds(100, 100, 432, 151);
		frmCountDown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCountDown.getContentPane().setLayout(null);

		JLabel lblTimer = new JLabel("INIZIO MATCH TRA:");
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblTimer.setBounds(10, 68, 200, 27);
		frmCountDown.getContentPane().add(lblTimer);

		lblCountdown = new JLabel("");
		lblCountdown.setHorizontalAlignment(SwingConstants.CENTER);
		lblCountdown.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblCountdown.setBounds(224, 68, 183, 27);
		frmCountDown.getContentPane().add(lblCountdown);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 415, 48);
		frmCountDown.getContentPane().add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("IL PAROLIERE");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblNewLabel.setBounds(45, 10, 328, 28);
		panel.add(lblNewLabel);


		Thread t = new Thread() {

			@Override
			public void run() {

				int i=5;
				while(flag) {
					while(i>0) {


						lblCountdown.setText(""+i--);


						try {
							Thread.sleep(1000L); // controllo avviene ogni secondo
						} catch (InterruptedException e) {

							System.out.println("thread interrotto");
						}

						if(i==0) { // scadono 30 si avvia boogleGui
							flag = false;					
							BoggleGui boggleGui = new BoggleGui(proxy,email,nomePartita);
							boggleGui.frmParoliere.setLocationRelativeTo(null);
							boggleGui.frmParoliere.setVisible(true);
							frmCountDown.dispose();
						}

					}

				}
			}
		};
		t.start();
	}

}