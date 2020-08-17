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
	// elementi gui
	public JFrame frame;
	private JPanel panel;
	private JTextField textUidAdmin;
	private JLabel lblEditAdmin;
	private JLabel lblUid;
	private JLabel lblPasswAdmin;
	private JLabel lblOldPw;
	private JLabel lblNewPw;
	private JLabel lblCheckOld;
	private JLabel lblCheckNew;
	private JButton btnEditPw;
	private JButton btnMain;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;



	private Validator validator = new Validator();
	private String type = "admin";
	boolean isUid;
	boolean isOkPw;
	boolean checkOld;


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

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 548, 48);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblEditAdmin = new JLabel("MODIFICA PROFILO ADMIN");
		lblEditAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAdmin.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblEditAdmin.setForeground(Color.WHITE);
		lblEditAdmin.setBounds(10, 10, 538, 25);
		panel.add(lblEditAdmin);

		lblUid = new JLabel("UID");
		lblUid.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 13));
		lblUid.setBounds(10, 83, 46, 13);
		frame.getContentPane().add(lblUid);

		lblPasswAdmin = new JLabel("PASSWORD");
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

		btnEditPw = new JButton("MODIFICA PASSWORD");
		btnEditPw.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		btnEditPw.setBounds(395, 182, 143, 21);
		frame.getContentPane().add(btnEditPw);

		lblOldPw = new JLabel("PASSWORD ATTUALE");
		lblOldPw.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblOldPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblOldPw.setBounds(136, 164, 95, 13);
		frame.getContentPane().add(lblOldPw);

		lblNewPw = new JLabel("NUOVA PASSWORD");
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

		lblCheckOld = new JLabel("");
		lblCheckOld.setFont(new Font("Century Gothic", Font.BOLD, 9));
		lblCheckOld.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOld.setBounds(135, 214, 96, 13);
		frame.getContentPane().add(lblCheckOld);

		lblCheckNew = new JLabel("");
		lblCheckNew.setFont(new Font("Century Gothic", Font.BOLD, 9));
		lblCheckNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckNew.setBounds(263, 212, 96, 13);
		frame.getContentPane().add(lblCheckNew);

		btnMain = new JButton("INDIETRO");
		btnMain.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnMain.setBounds(206, 241, 85, 21);
		frame.getContentPane().add(btnMain);

		passwordOld.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {

				String pw = validator.sha1(passwordOld.getText());

				String result = proxy.checkPasswordAdmin(uid, pw);// controlla che la vecchia password coincida
				if(result.equals("EQUAL")){ // se coincide
					checkOld = true;
					lblCheckOld.setText("LA PASSWORD COINCIDE"); // avviso da jlabel
					passwordOld.setBorder(new LineBorder(Color.GREEN,1)); // bordo si tinge di verde

				}else { // se non coincide
					checkOld = false;	
					lblCheckOld.setText("LA PASSWORD NON COINCIDE"); // avviso da jlabel
					passwordOld.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
				}

			}


		});
		
		
		passwordNew.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void keyReleased(KeyEvent e) {
				// se la nuova password rispetta i criteri richiesti
				if(validator.isPassword(passwordNew.getText())) {
					isOkPw = true;
					passwordNew.setBorder(new LineBorder(Color.GREEN)); // // bordo si tinge di verde
					lblCheckNew.setText("Formato valido"); // avviso da jlabel

					// se la password non rispetta i criteri richiesti
				}else if(!validator.isPassword(passwordNew.getText())) {
					isOkPw = false;
					passwordNew.setBorder(new LineBorder(Color.RED,1)); // bordo si tinge di rosso
					lblCheckNew.setText("Formato non valido"); // avviso da jlabel

				}
			}

		});

		btnEditPw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// se la vecchia password non coincide o quella nuova non rispetta i criteri richiesti
				if(checkOld == false || isOkPw == false) {
					JOptionPane.showMessageDialog(frame, "La vecchia password non coincide o\n formato non valido", "ERRORE", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int response = JOptionPane.showConfirmDialog(null, "Confermi la modifica?", "Conferma", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION) {

					@SuppressWarnings("deprecation")
					String result = proxy.editPasswordAdmin(uid, passwordNew.getText());
					if(result.equals("UPDATE")) { // se la password viene modificata correttamente
						JOptionPane.showMessageDialog(frame, "Modifica avvenuta con successo.");
						LoginAdmin loginAdmin = new LoginAdmin(proxy); // ritorno alla schermata di login
						loginAdmin.frame.setLocationRelativeTo(null);
						loginAdmin.frame.setVisible(true);
						frame.dispose();
					}else { // altrimenti ricevo un msg di errore e ritorno al menu principale
						JOptionPane.showMessageDialog(frame, "Modifica non effettuata", "ERRORE", JOptionPane.ERROR_MESSAGE);
						MainMenuAdmin mainMenuAdmim = new MainMenuAdmin(proxy, uid);
						mainMenuAdmim.frame.setLocationRelativeTo(null);
						mainMenuAdmim.frame.setVisible(true);
						frame.dispose();
					}
				}
			}
		});

		// button di ritorno al menu principale
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
