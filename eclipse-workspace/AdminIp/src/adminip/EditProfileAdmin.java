package adminip;



import java.awt.EventQueue;
import javax.swing.JFrame;
import clientip.Proxy;
import entitiesip.Validator;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;

public class EditProfileAdmin {

	public JFrame frame;
	private JTextField textUidAdmin;
	private Validator validator = new Validator();
	private String type = "admin";
	boolean isUid;
	boolean isOkPw;
	boolean checkOld;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Proxy proxy = new Proxy();
					LoginAdmin loginAdmin = new LoginAdmin(proxy);
					String uid = loginAdmin.getUid();
					EditProfileAdmin window = new EditProfileAdmin(proxy,uid);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public EditProfileAdmin(Proxy proxy,String uid)  {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 9));
		frame.setBounds(100, 100, 562, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 548, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblEditAdmin = new JLabel("MODIFICA PROFILO ADMIN");
		lblEditAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAdmin.setFont(new Font("Century Gothic", Font.PLAIN, 28));
		lblEditAdmin.setForeground(Color.WHITE);
		lblEditAdmin.setBounds(10, 10, 538, 25);
		panel.add(lblEditAdmin);

		JLabel lblUid = new JLabel("UID");
		lblUid.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblUid.setBounds(10, 83, 46, 13);
		frame.getContentPane().add(lblUid);

		JLabel lblPasswAdmin = new JLabel("PASSWORD");
		lblPasswAdmin.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblPasswAdmin.setBounds(10, 184, 77, 13);
		frame.getContentPane().add(lblPasswAdmin);

		textUidAdmin = new JTextField();
		textUidAdmin.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		textUidAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		textUidAdmin.setEditable(false);
		textUidAdmin.setBounds(135, 82, 96, 19);
		frame.getContentPane().add(textUidAdmin);
		textUidAdmin.setColumns(10);
		String userId = proxy.getAdmin(type);

		textUidAdmin.setText(userId);

		JButton btnEditPw = new JButton("MODIFICA PASSWORD");
		btnEditPw.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		btnEditPw.setBounds(395, 182, 143, 21);
		frame.getContentPane().add(btnEditPw);

		JLabel lblOldPw = new JLabel("PASSWORD ATTUALE");
		lblOldPw.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblOldPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblOldPw.setBounds(136, 164, 95, 13);
		frame.getContentPane().add(lblOldPw);

		JLabel lblNewPw = new JLabel("NUOVA PASSWORD");
		lblNewPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPw.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewPw.setBounds(263, 164, 96, 13);
		frame.getContentPane().add(lblNewPw);

		passwordOld = new JPasswordField();
		passwordOld.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordOld.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		passwordOld.setBounds(135, 183, 96, 19);
		frame.getContentPane().add(passwordOld);

		passwordNew = new JPasswordField();
		passwordNew.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordNew.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		passwordNew.setBounds(263, 183, 96, 19);
		frame.getContentPane().add(passwordNew);

		JLabel lblCheckOld = new JLabel("");
		lblCheckOld.setFont(new Font("Century Gothic", Font.BOLD, 9));
		lblCheckOld.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOld.setBounds(135, 214, 96, 13);
		frame.getContentPane().add(lblCheckOld);

		JLabel lblCheckNew = new JLabel("");
		lblCheckNew.setFont(new Font("Century Gothic", Font.BOLD, 9));
		lblCheckNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckNew.setBounds(263, 212, 96, 13);
		frame.getContentPane().add(lblCheckNew);

		JButton btnMain = new JButton("INDIETRO");
		btnMain.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnMain.setBounds(206, 241, 85, 21);
		frame.getContentPane().add(btnMain);

		passwordOld.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {

				String pw = validator.sha1(passwordOld.getText());

				String result = proxy.checkPasswordAdmin(uid, pw);// controlla che la vecchia password coincida
				if(result.equals("EQUAL")){
					checkOld = true;
					lblCheckOld.setText("LA PASSWORD COINCIDE");
					passwordOld.setBorder(new LineBorder(Color.GREEN,1));
				}else {
					checkOld = false;	
					lblCheckOld.setText("LA PASSWORD NON COINCIDE");
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
					String result = proxy.editPasswordAdmin(uid, passwordNew.getText());
					if(result.equals("UPDATE")) {
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						LoginAdmin loginAdmin = new LoginAdmin(proxy);
						loginAdmin.frame.setLocationRelativeTo(null);
						loginAdmin.frame.setVisible(true);
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuAdmin mainMenuAdmim = new MainMenuAdmin(proxy, uid);
						mainMenuAdmim.frame.setLocationRelativeTo(null);
						mainMenuAdmim.frame.setVisible(true);
						frame.dispose();
					}
				}
			}
		});

		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuAdmin mainMenuAdmin = new MainMenuAdmin(proxy, uid);
				mainMenuAdmin.frame.setLocationRelativeTo(null);
				mainMenuAdmin.frame.setVisible(true);
				frame.dispose();
			}
		});

	}
}
