package p;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Login {

	JFrame Login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Login = new JFrame();
		Login.setResizable(false);
		Login.getContentPane().setBackground(Color.GRAY);
		Login.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new LineBorder(new Color(0, 0,0)));
		panel.setBounds(-185, -78, 863, 750);
		Login.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("S'INSCRIRE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login.dispose();
				Connexion window = new Connexion();
				window.Connexion.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(424, 278, 129, 48);
		panel.add(btnNewButton_1);
		Login.setBounds(100, 100, 643, 447);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
