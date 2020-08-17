package adminip;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import clientip.Proxy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JTextField;

public class MainMenuAdmin {
	
	//elementi gui
	public JFrame frame;
	private JPanel panel;
	private JTextField textUidAdmin;
	private JLabel lblMainAdmin;
	private JButton btnEditProfileAdmin;
	private JButton btnExitAdmin;
	private JButton btnEditUser;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					LoginAdmin loginAdmin = new LoginAdmin(proxy);
					String uid = loginAdmin.getUid();
					MainMenuAdmin window = new MainMenuAdmin(proxy,uid);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainMenuAdmin(Proxy proxy,String uid){

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 436, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblMainAdmin = new JLabel("MAIN MENU ADMIN");
		lblMainAdmin.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblMainAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainAdmin.setForeground(Color.WHITE);
		lblMainAdmin.setBackground(new Color(255, 255, 255));
		lblMainAdmin.setBounds(26, 10, 389, 21);
		panel.add(lblMainAdmin);

		btnEditProfileAdmin = new JButton("EDIT PROFILO");
		btnEditProfileAdmin.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditProfileAdmin.setBounds(54, 102, 122, 21);
		frame.getContentPane().add(btnEditProfileAdmin);

		btnExitAdmin = new JButton("ESCI");
		btnExitAdmin.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnExitAdmin.setBounds(193, 155, 75, 21);
		frame.getContentPane().add(btnExitAdmin);

		textUidAdmin = new JTextField();
		textUidAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		textUidAdmin.setEditable(false);
		textUidAdmin.setBounds(163, 51, 131, 19);
		frame.getContentPane().add(textUidAdmin);
		textUidAdmin.setColumns(10);
		textUidAdmin.setText(uid);

		btnEditUser = new JButton("EDIT PROFILO USER");
		btnEditUser.setFont(new Font("Century Gothic", Font.BOLD, 9));
		btnEditUser.setBounds(261, 102, 114, 21);
		frame.getContentPane().add(btnEditUser);



		//// button per accedere alla schermata di edit profilo user
		btnEditUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ProfilePlayer profilePlayer = new ProfilePlayer(proxy,uid);
				profilePlayer.frame.setLocationRelativeTo(null);
				profilePlayer.frame.setVisible(true);
				frame.dispose();


			}
		});


		// button per accedere alla schermata di edit profilo admin
		btnEditProfileAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditProfileAdmin editProfileAdmin = new EditProfileAdmin(proxy,uid);
				editProfileAdmin.frame.setLocationRelativeTo(null);
				editProfileAdmin.frame.setVisible(true);
				frame.dispose();

			}
		});



		// button di uscita, ritorno alla schermata di login
		btnExitAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginAdmin loginAdmin = new LoginAdmin(proxy);
				loginAdmin.frame.setVisible(true);
				loginAdmin.frame.setLocationRelativeTo(null);
				frame.dispose();
			}
		});

	}
}
