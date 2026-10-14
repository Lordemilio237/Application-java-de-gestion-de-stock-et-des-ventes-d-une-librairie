package p;

import java.awt.EventQueue;
import  com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
public class Menu_principal {

	JFrame Menu;
	private JTextField txtnoms;
	private JTextField txtprix;
	private JTextField txttotal;
	private JTextField txtpaiement;
	private JTextField txtreste;
	private JTable txttable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal window = new Menu_principal();
					window.Menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public Menu_principal() {
		initialize();
		Connect();
		Table();
		
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
	private void Table() {
		try {
			Connect();
			String [] entet = {"Code","Noms","Prix","Quantité","Paiement","Total","Reste"};
			String [] ligne = new String[8];
			DefaultTableModel model = new DefaultTableModel(null,entet);
			String sql = "select * from fourniture";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ligne[0] = rs.getString("Code");
				ligne[1] = rs.getString("Noms");
				ligne[2] = rs.getString("Prix");
				ligne[3] = rs.getString("Quantité");
				ligne[4] = rs.getString("Paiement");
				ligne[5] = rs.getString("Total");
				ligne[6] = rs.getString("Reste");
				model.addRow(ligne);
				
					}
			txttable.setModel(model);
			con.close();
	}catch(Exception e3){
		e3.printStackTrace();}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Menu = new JFrame();
		Menu.getContentPane().setBackground(Color.GRAY);
		Menu.setResizable(false);
		Menu.setTitle("Menu");
		Menu.setBounds(100, 100, 817, 631);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 777, 75);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		Menu.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DE  STOCK  D' UNE LIBRAIRIE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 10, 689, 55);
		panel.add(lblNewLabel);
		lblNewLabel.setBackground(Color.BLUE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 106, 777, 260);
		Menu.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("NOMS :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 27, 63, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PRIX :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 104, 63, 30);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("QUANTITE :");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(378, 27, 91, 30);
		panel_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("TOTAL :");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(378, 88, 63, 30);
		panel_1.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("PAIEMENT :");
		lblNewLabel_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_4.setBounds(378, 146, 91, 30);
		panel_1.add(lblNewLabel_1_1_4);
		
		JLabel lblNewLabel_1_1_5 = new JLabel("RESTE :");
		lblNewLabel_1_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_5.setBounds(378, 198, 63, 30);
		panel_1.add(lblNewLabel_1_1_5);
		
		txtnoms = new JTextField();
		txtnoms.setColumns(10);
		txtnoms.setBounds(75, 30, 166, 30);
		panel_1.add(txtnoms);
		
		txtprix = new JTextField();
		txtprix.setColumns(10);
		txtprix.setBounds(75, 107, 166, 30);
		panel_1.add(txtprix);
		
		txttotal = new JTextField();
		txttotal.setColumns(10);
		txttotal.setBounds(479, 88, 166, 30);
		panel_1.add(txttotal);
		
		txtpaiement = new JTextField();
		txtpaiement.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				int Total = Integer.parseInt(txttotal.getText());
				int Paiement = Integer.parseInt(txtpaiement.getText());
				int Reste = Total - Paiement;
				txtreste.setText(String.valueOf(Reste));
			}
		});
		txtpaiement.setColumns(10);
		txtpaiement.setBounds(479, 146, 166, 30);
		panel_1.add(txtpaiement);
		
		txtreste = new JTextField();
		txtreste.setColumns(10);
		txtreste.setBounds(479, 198, 166, 30);
		panel_1.add(txtreste);
		
		JSpinner txtquantite = new JSpinner();
		txtquantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int Prix = Integer.parseInt(txtprix.getText());
				int Quantite = Integer.parseInt(txtquantite.getValue().toString());
				int Total = Prix* Quantite;
				txttotal.setText(String.valueOf(Total));
				if(txtprix.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer un prix");
					return;
				}
			}
		});
		txtquantite.setBounds(479, 30, 166, 30);
		panel_1.add(txtquantite);
		
		JButton btnNewButton = new JButton("ENREGISTRER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect();
					String nom= txtnoms.getText();
					int quantite= Integer.parseInt(txtquantite.getValue().toString());
					PreparedStatement check= con.prepareStatement("SELECT * FROM livre WHERE Nom=?");
					check.setString(1, txtnoms.getText());
					ResultSet rs= check.executeQuery();
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "Ce produit n'existe pas dans le stocks!");
						return;
					}
					pst= con.prepareStatement("insert into fourniture ( Noms, Prix, Quantité, Total, Paiement, Reste)values (?,?,?,?,?,?)");
					pst.setString(1, txtnoms.getText());
					pst.setString(2, txtprix.getText());
					pst.setString(3, txtquantite.getValue().toString());
					pst.setString(4, txttotal.getText());
					pst.setString(5, txtpaiement.getText());
					pst.setString(6, txtreste.getText());
					pst.executeUpdate();
					
					enregistrerVente(nom,quantite);
					
						
					con.close();
					JOptionPane.showMessageDialog(null,txtnoms.getText()+"Ajouter");
					Table();
					
			}catch(Exception e2){
				e2.printStackTrace();	
			}}
			public void enregistrerVente(String nom, int quantiteVendue) {
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
					String sqlSelect="SELECT Quantite FROM livre WHERE Nom=?";
					PreparedStatement pstSelect= con.prepareStatement(sqlSelect);
					pstSelect.setString(1, nom);
					System.out.println("Exécution de la requete SELECT pour:"+nom);
					ResultSet rs= pstSelect.executeQuery();
					
				
			
					if(rs.next()) {
						int quantiteDispo= rs.getInt("Quantite");
						System.out.println("Produit trouvé. Quantité disponible:"+quantiteDispo);
						if(quantiteVendue <= quantiteDispo) {
							int nouvelleQuantite= quantiteDispo-quantiteVendue;
							String sqlUpdate= "UPDATE livre SET Quantite= ? WHERE Nom=?";
							PreparedStatement pstUpdate= con.prepareStatement(sqlUpdate);
							pstUpdate.setInt(1, nouvelleQuantite);
							pstUpdate.setString(2, nom);
							pstUpdate.executeUpdate();
							pstUpdate.close();
							JOptionPane.showMessageDialog(null, "Produit enregistré, stock mis à jour.");
							afficherStock();	
						} else 
							if(quantiteVendue > quantiteDispo){
							JOptionPane.showMessageDialog(null, "Quantité demandée supérieur au stock!");
							return;
						             }
					       else {
						System.out.println("Aucun produit trouvé avec le nom"+nom);
						JOptionPane.showMessageDialog(null, "Produit introuvable!");
						return;
					       }
					}
					rs.close();
					pstSelect.close();
					con.close();
					
				}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "ERREUR:"+ e.getMessage());
			}

			
			
			}
			
				
				
			});
	
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 161, 155, 38);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 273, 785, 141);
		panel_1.add(scrollPane_1);
		
		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/librairie?serverTimezone=Europe/Paris","root","");
					int ligne= txttable.getSelectedRow();
					
					if(ligne == -1) {
						JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à supprimer");
						return;
					}
					String nom= txttable.getValueAt(ligne, 1).toString();
					
					int quantiteVendue= Integer.parseInt(txttable.getValueAt(ligne, 3).toString());
					
				PreparedStatement pst1= con.prepareStatement("SELECT Quantite FROM livre WHERE LOWER(TRIM(Nom))=?");
				pst1.setString(1, nom);
				ResultSet rs= pst1.executeQuery();
				int ancienneQuantite = 0;
				if(rs.next()) {
					 ancienneQuantite=rs.getInt("Quantite");
					 DefaultTableModel model = (DefaultTableModel)  txttable.getModel();
					System.out.println("Quantité trouvée dans livre:"+ ancienneQuantite);
				}else {
					JOptionPane.showMessageDialog(null, "Produit non trouvé dans la table 'livre'");
					rs.close();
					pst1.close();
					con.close();
					return;
				}
				rs.close();
				pst1.close();
				
				System.out.println("Quantité récupérée dans la table:"+ ancienneQuantite);
				int nouvelleQuantite=  ancienneQuantite + quantiteVendue;
				PreparedStatement	pstUpdate= con.prepareStatement("UPDATE livre SET Quantite= ? WHERE LOWER(TRIM(Nom))=?");
				pstUpdate.setInt(1, nouvelleQuantite);
				pstUpdate.setString(2, nom);
				pstUpdate.executeUpdate();
				pstUpdate.close();
				System.out.println("Nouvelle quanitité mise à jour:"+ nouvelleQuantite);
					pst= con.prepareStatement("Delete from fourniture where Noms=?");
					pst.setString(1, txtnoms.getText());
					pst.executeUpdate();
					afficherStock();
					con.close();
					JOptionPane.showMessageDialog(null,txtnoms.getText()+"Supprimé et stock remis.");
					
					Table();
			}catch(Exception e4){
				e4.printStackTrace();
			}
				
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSupprimer.setBounds(10, 209, 155, 38);
		panel_1.add(btnSupprimer);
		
		JButton Imprimer = new JButton("IMPRIMER");
		Imprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect();
					Document doc= new Document();
					PdfWriter.getInstance(doc, new FileOutputStream("rapport.pdf"));
					doc.open();
					
					  doc.add(new Paragraph("RAPPORT DE STOCKS LIBRAIRIE"));
						doc.add(new Paragraph("Noms:"+ txtnoms.getText()));
						doc.add(new Paragraph("Prix:"+ txtprix.getText()));
						doc.add(new Paragraph("Quantite:"+ txtquantite.getValue().toString()));
						doc.add(new Paragraph("Total:"+ txttotal.getText()));
						doc.add(new Paragraph("Paiement:"+ txtpaiement.getText()));
						doc.add(new Paragraph("Reste:"+ txtreste.getText()));
						PdfPTable table= new PdfPTable(7);
						table.setWidthPercentage(100);
						table.addCell("Code");
						table.addCell("Noms");
						table.addCell("Prix");
						table.addCell("Quantité");
						table.addCell("Total");
						table.addCell("Paiement");
						table.addCell("Reste");
						
					String sql="SELECT * FROM fourniture";
					Statement st = con.createStatement();
					ResultSet rs=st.executeQuery( sql);
					while(rs.next()) {
						table.addCell(rs.getString("Code"));
						table.addCell(rs.getString("Noms"));
						table.addCell(rs.getString("Prix"));
						table.addCell(rs.getString("Quantité"));
						table.addCell(rs.getString("Total"));
						table.addCell(rs.getString("Paiement"));
						table.addCell(rs.getString("Reste"));
					}
					
					doc.add(table);
				  
					
					doc.close();
					con.close();
					JOptionPane.showMessageDialog(null, "PDF généré avec succès");
					
			}catch(Exception e5) {
				e5.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERREUR:"+ e5.getMessage());
			}
			}});
		
		Imprimer.setFont(new Font("Tahoma", Font.BOLD, 14));
		Imprimer.setBounds(188, 205, 155, 38);
		panel_1.add(Imprimer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 371, 783, 174);
		Menu.getContentPane().add(scrollPane);
		
		txttable = new JTable();
		scrollPane.setViewportView(txttable);
		txttable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("RETOUR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.dispose();
				Stock window = new Stock();
				window.Stock.setVisible(true);
				
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(10, 556, 101, 28);
		Menu.getContentPane().add(btnNewButton_1);
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
        	txttable.repaint();
            rs.close();
            st.close();
            con.close();
        } catch(Exception e10) {
        	JOptionPane.showMessageDialog(null, "Erreur:" + e10.getMessage());
        }
	}
}
