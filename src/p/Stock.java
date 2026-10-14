package p;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stock {

	JFrame Stock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock window = new Stock();
					window.Stock.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Stock() {
		initialize();
		Connect();
		Table();
		afficherStock();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtrechercher;
	private JTable txttable;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
			System.out.println("connexion etablie");
		
	} catch(Exception e) {
		e.printStackTrace(); }
		}
	private void Table() {
		try {
			Connect();
			String [] entet = {"Id","Nom","Quantite","Prix"};
			String [] ligne = new String[4];
			DefaultTableModel model = new DefaultTableModel(null,entet);
			String sql = "select * from livre";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ligne[0] = rs.getString("Id");
				ligne[1] = rs.getString("Nom");
				ligne[2] = rs.getString("Quantite");
				
				model.addRow(ligne);
				
					}
			txttable.setModel(model);
			con.close();
	}catch(Exception e3){
		e3.printStackTrace();}
	}
	public void afficherStock() {
		String [] entet = {"Id","Nom","Quantite","Prix"};
        DefaultTableModel model = new DefaultTableModel(null,entet);
       
        model.setRowCount(0);
        txttable.setModel(model);
        try {
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
        	Statement st= con.createStatement();
        	ResultSet rs= st.executeQuery("SELECT * FROM  livre");
        	model.setRowCount(0);
        	while(rs.next()) {
        		Object[] ligne = {
        				rs.getInt("Id"),
        				rs.getString("Nom"),
        				rs.getInt("Quantite"),
        				rs.getInt("Prix")
        		};
        		model.addRow(ligne);
        	}
        	txttable.setModel(model);
            rs.close();
            st.close();
            con.close();
        } catch(Exception e10) {
        	JOptionPane.showMessageDialog(null, "Erreur:" + e10.getMessage());
        }
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Stock = new JFrame();
		Stock.getContentPane().setBackground(Color.GRAY);
		Stock.setBounds(180, 100, 646, 525);
		Stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Stock.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STOCKS DES FOURNITURES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 622, 49);
		Stock.getContentPane().add(lblNewLabel);
		
		txtrechercher = new JTextField();
		txtrechercher.setBounds(63, 135, 289, 32);
		Stock.getContentPane().add(txtrechercher);
		txtrechercher.setColumns(10);
		
		JButton btnNewButton = new JButton("RECHERCHER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String critere= txtrechercher.getText();
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
                     String sql= "SELECT* FROM livre WHERE Nom LIKE ?";
                     PreparedStatement pst= con.prepareStatement(sql);
                     pst.setString(1, "%" + critere + "%");
                     ResultSet rs= pst.executeQuery();
                 	String [] entet = {"Id","Noms","Quantite","Prix"};
                     DefaultTableModel model = new DefaultTableModel(null,entet);
                     while (rs.next()) {
                    	 Object[] row= {
                    			 rs.getInt("Id"),
                    			 rs.getString("Nom"),
                    			 rs.getInt("Quantite"),
                    			 rs.getInt("Prix")
                    	 };
                    	 model.addRow(row);
                     }
                     String nomrecherche= txtrechercher.getText().trim();
     				boolean trouve=false;
     				for(int i=0; i< txttable.getRowCount();i++) {
     					String nomProduit= txttable.getValueAt(i, 2).toString();
     					if(nomProduit.equalsIgnoreCase(nomrecherche)) {
     						trouve= true;
     						txttable.setRowSelectionInterval(i, i);
     						break;
     					}
     				  }	
     				      if(!trouve) {
     				    	  JOptionPane.showMessageDialog(null, "Le produit n'est pas disponible");
     				      }
                     txttable.setModel(model);
                     con.close();
				}catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erreur de recherche");
			}
				
			}});
		btnNewButton.setBounds(377, 134, 129, 33);
		Stock.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 187, 600, 244);
		Stock.getContentPane().add(scrollPane);
		
		txttable = new JTable();
		scrollPane.setViewportView(txttable);
		
		JButton btnNewButton_1 = new JButton("SUIVANT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock.dispose();
				Menu_principal window = new Menu_principal();
				window.Menu.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(503, 441, 85, 32);
		Stock.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("RETOUR");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock.dispose();
				Login window = new Login();
				window.Login.setVisible(true);
			}
		});
		btnNewButton_1_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_1.setBounds(63, 441, 85, 32);
		Stock.getContentPane().add(btnNewButton_1_1);
	}
}
