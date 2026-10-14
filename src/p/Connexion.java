package p;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Menu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Connexion {

	JFrame Connexion;
	private JTextField txtnoms;
	private JTextField txtprenoms;
	private JPasswordField txtpasswords;
	private JTextField txttelephone;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion window = new Connexion();
					window.Connexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Connexion() {
		initialize();
		Connect();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
			System.out.println("connexion etablie");
		
	} catch(Exception e) {
		e.printStackTrace(); }
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connexion = new JFrame();
		Connexion.getContentPane().setBackground(Color.LIGHT_GRAY);
		Connexion.setResizable(false);
		Connexion.setBounds(100, 100, 739, 583);
		Connexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Connexion.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 128));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(-11, 0, 261, 563);
		Connexion.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(335, 10, 321, 33);
		Connexion.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NOMS :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(275, 73, 70, 27);
		Connexion.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PRENOMS :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(275, 151, 88, 27);
		Connexion.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PASSWORDS :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(275, 232, 123, 27);
		Connexion.getContentPane().add(lblNewLabel_1_2);
		
		txtnoms = new JTextField();
		txtnoms.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			String nom= txtnoms.getText().trim();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
				System.out.println("connexion etablie");
				PreparedStatement pst= con.prepareStatement("SELECT Password FROM utilisateur WHERE Noms=?");
				pst.setString(1, nom);
				ResultSet rs= pst.executeQuery();
				if(rs.next()) {
					String mdp= rs.getString("Password");
					txtpasswords.setText(mdp);
				}
				else {
					txtpasswords.setText("");
				}
				rs.close();
				pst.close();
			
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}});
		txtnoms.setBounds(399, 75, 252, 27);
		Connexion.getContentPane().add(txtnoms);
		txtnoms.setColumns(10);
		
		txtprenoms = new JTextField();
		txtprenoms.setColumns(10);
		txtprenoms.setBounds(399, 153, 252, 27);
		Connexion.getContentPane().add(txtprenoms);
		
		txtpasswords = new JPasswordField();
		txtpasswords.setBounds(399, 235, 252, 25);
		Connexion.getContentPane().add(txtpasswords);
		
		JLabel lblNewLabel_1_3 = new JLabel("TELEPHONE :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(275, 322, 103, 27);
		Connexion.getContentPane().add(lblNewLabel_1_3);
		
		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(399, 324, 252, 27);
		Connexion.getContentPane().add(txttelephone);
		
		JButton btnNewButton = new JButton("ENREGISTRER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect();
					pst = con.prepareStatement("insert into utilisateur (Noms, Prenoms, Password, Telephone)values (?, ?, ?, ? )");
					pst.setString(1, txtnoms.getText());
					pst.setString(2, txtprenoms.getText());
					pst.setString(3, new String(txtpasswords.getPassword()));
					pst.setString(4, txttelephone.getText());
					pst.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(null,txtnoms.getText()+"Ajouter");
				}
			catch(Exception e2) {
				e2.printStackTrace();
			}
			}});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(307, 454, 127, 33);
		Connexion.getContentPane().add(btnNewButton);
		
		JButton btnConnecter = new JButton("CONNECTER");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connect();
					
					pst = con.prepareStatement("select* from utilisateur where Noms=? and Password=?");
					pst.setString(1, txtnoms.getText());
					pst.setString(2, new String(txtpasswords.getPassword()));
					
					ResultSet rs= pst.executeQuery();
					if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Connexion réussie");
							
							Connexion.dispose();
							Stock window = new Stock();
							window.Stock.setVisible(true);
						}
						
						
					 else {
						JOptionPane.showMessageDialog(null, " Password incorrect");
						txtpasswords.setText("");
					}
					
					con.close();
					
				}catch(Exception e3) {
					
					JOptionPane.showMessageDialog(null, "Erreur:"+ e3.getMessage());
				}
				
			}
		});
		btnConnecter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConnecter.setBounds(561, 454, 127, 33);
		Connexion.getContentPane().add(btnConnecter);
	}
}