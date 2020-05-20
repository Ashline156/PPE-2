package SA2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Identification {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtUsername;
	public String priviledge;
	public String myname;
	private JPasswordField passwordField;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Identification window = new Identification();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Identification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMonParadis = new JLabel("MON PARADIS");
		lblMonParadis.setForeground(Color.WHITE);
		lblMonParadis.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblMonParadis.setBounds(131, 28, 182, 39);
		frame.getContentPane().add(lblMonParadis);
		
		JLabel lblUser = new JLabel("Nom d'Utilisateur :");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUser.setForeground(new Color(255, 250, 250));
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUser.setBounds(12, 100, 129, 16);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Mot de Passe :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setBounds(39, 129, 102, 19);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		
		txtUsername.setBounds(153, 97, 177, 22);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnLogin = new JButton("connexion");
		btnLogin.setBackground(Color.CYAN);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = txtUsername.getText();
				String password = passwordField.getText();
				
				
//				System.out.print(username);	
//				System.out.print(password);	
				
				
				JLabel alertUser = new JLabel("");
				alertUser.setBounds(255, 72, 56, 16);
				frame.getContentPane().add(alertUser);
				
				JLabel alertPassword = new JLabel("");
				alertPassword.setBounds(255, 124, 56, 16);
				frame.getContentPane().add(alertPassword);
				
				txtUsername.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						alertUser.setText("");
					}
				});
				
				passwordField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						alertPassword.setText("");
					}
				});
				
		/////////////////////////validation////////////////////////////////		
				
			if(username.trim().length()==0 && password.trim().length()==0) {
				
				JOptionPane.showMessageDialog(null, "BOTH FIELDS ARE NULL");
				
				 alertUser.setText("Username is empty");
				 alertPassword.setText("Password is empty");
								
			}	else if(username.trim().length()==0 ) {
				
				 alertUser.setText("Username is empty");
				
				
				
			}else if(password.trim().length()==0 ) {
				
				 alertPassword.setText("Password is empty");
				
				
			}else {
				 dbc db = new dbc();
				
				try{
					
					 Connection con =db.db_connect();
					 PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
					 stmt.setString(1, username);
					 stmt.setString(2, password);
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next()) {
						String role =rs.getString(5);
						priviledge = role;
						
						
						String prenom=rs.getString(2);
						JOptionPane.showMessageDialog(null,"Bienvenue"+ prenom);
						myname = prenom;
						
						frame.dispose();
						Dash welcome = new Dash(priviledge, myname);
						welcome.setVisible(true);
						
						
				//		JOptionPane.showMessageDialog(null, role);
	//					if (role.equals("Administrateur")) {
		//					AdminInterface top= new AdminInterface();
			//				top.setVisible(true);
							
							
							
//						}else {
//							
//							
//						}
						
						
					}else {
						
						JOptionPane.showMessageDialog(null, "not in database");
						
						
					}
					
					
				}catch(Exception Error) {
					System.out.print(Error);
					
				}
				
				
			}
			
	
			
			
			
			}
		});
		btnLogin.setBounds(267, 178, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("sortie");
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(57, 178, 97, 25);
		frame.getContentPane().add(btnExit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(153, 127, 177, 22);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Documents\\Eclipse\\SA2\\images\\identification.jpg"));
		lblNewLabel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(lblNewLabel);
		
		
		

		

	
		
////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
