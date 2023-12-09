package atm_sys;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Panel;
import java.awt.Label;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadT1Client;
import com.sun.javacard.apduio.CadTransportException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ATM  {

	private static  Main main ;
	protected JFrame frmMdBank;
	protected JPasswordField passwordField;
	
	protected JLabel lblNewLabel_3 ;
	protected JButton btnNewButton_2 ;
	protected JButton btnNewButton_2_2 ;
	protected JButton btnNewButton_2_3 ;
	protected JButton btnNewButton_2_4 ;
	protected JButton btnNewButton_2_5 ;
	protected JButton btnNewButton_2_6 ;
	protected JButton btnNewButton_2_7 ;
	protected JButton btnNewButton_2_8 ;
	protected JButton btnNewButton_2_9 ;
	protected JButton btnNewButton_2_10 ;
	protected JButton btnNewButton_2_11 ;
	protected JButton btnNewButton_2_12 ;
	protected JButton btnNewButton_2_13 ;
	
	protected JButton btnNewButton_4 ;
	
	protected JPanel panel_3  ;
	
	protected JLabel lblPinIncorrectVeuillez_1 ;
	JLabel lblPinIncorrect ;
	
	//constructeur 
	public ATM(Main main) {
		this.main=main ;
		initialize();
		
		
	}
	
		
	  
	private void initialize() {

		frmMdBank = new JFrame();
		frmMdBank.getContentPane().setBackground(new Color(255, 255, 255));
		frmMdBank.setTitle("MD BANK");
		frmMdBank.setBounds(100, 100, 701, 730);
		frmMdBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMdBank.getContentPane().setLayout(null);
		frmMdBank.setLocationRelativeTo(null);
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 240, 240));
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		panel_3.setBounds(113, 136, 455, 240);
		frmMdBank.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		lblNewLabel_3 = new JLabel("ENTER YOUR PIN ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(167, 80, 200, 30);
		panel_3.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(166, 120, 100, 19);
		panel_3.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("    WELCOME TO MD BANK");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel_2.setBounds(72, 10, 400, 50);
		panel_3.add(lblNewLabel_2);

		//PIN incorrect veuillez saisir une autre fois !!!!!
		lblPinIncorrectVeuillez_1 = new JLabel("");
		lblPinIncorrectVeuillez_1.setForeground(new Color(255, 0, 0));
		lblPinIncorrectVeuillez_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPinIncorrectVeuillez_1.setBounds(94, 177, 250, 30);
		panel_3.add(lblPinIncorrectVeuillez_1);

		lblPinIncorrect = new JLabel("");
		lblPinIncorrect.setForeground(Color.RED);
		lblPinIncorrect.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPinIncorrect.setBounds(94, 149, 250, 30);
		panel_3.add(lblPinIncorrect);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 128, 128));
		panel_4.setBounds(55, 400, 390, 222);
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmMdBank.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
				
		btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"3");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_2.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/three.png")));
		btnNewButton_2.setBounds(196, 5, 96, 55);
		panel_4.add(btnNewButton_2);
		
		btnNewButton_2_2 = new JButton("");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"4");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_2_2.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/four.png")));
		btnNewButton_2_2.setBounds(5, 58, 96, 55);
		panel_4.add(btnNewButton_2_2);
		
		btnNewButton_2_3 = new JButton("");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"7");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_2_3.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/seven.png")));
		btnNewButton_2_3.setBounds(5, 112, 96, 55);;
		panel_4.add(btnNewButton_2_3);
		
		btnNewButton_2_4 = new JButton("");
		btnNewButton_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_4.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/empty.png")));
		btnNewButton_2_4.setBounds(5, 164, 96, 55);
		panel_4.add(btnNewButton_2_4);
		
		btnNewButton_2_5 = new JButton("");
		btnNewButton_2_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"5");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_2_5.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/five.png")));
		btnNewButton_2_5.setBounds(100, 58, 96, 55);
		panel_4.add(btnNewButton_2_5);
		
		btnNewButton_2_6 = new JButton("");
		btnNewButton_2_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"8");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_2_6.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/eight.png")));
		btnNewButton_2_6.setBounds(100, 112, 96, 55);
		panel_4.add(btnNewButton_2_6);
		
		btnNewButton_2_7 = new JButton("");
		btnNewButton_2_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"0");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_2_7.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/zero.png")));
		btnNewButton_2_7.setBounds(100, 164, 96, 55);
		panel_4.add(btnNewButton_2_7);
		
		btnNewButton_2_8 = new JButton("");
		btnNewButton_2_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"6");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_2_8.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/six.png")));
		btnNewButton_2_8.setBounds(196, 58, 96, 55);
		panel_4.add(btnNewButton_2_8);
		
		btnNewButton_2_9 = new JButton("");
		btnNewButton_2_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"9");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_2_9.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/nine.png")));
		btnNewButton_2_9.setBounds(196, 112, 95, 54);
		panel_4.add(btnNewButton_2_9);
		
		btnNewButton_2_10 = new JButton("");
		btnNewButton_2_10.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/empty.png")));
		btnNewButton_2_10.setBounds(289, 112, 96, 55);
		panel_4.add(btnNewButton_2_10);
		
		btnNewButton_2_11 = new JButton("");
		btnNewButton_2_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_2_11.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/clear.png")));
		btnNewButton_2_11.setBounds(289, 58, 96, 55);
		btnNewButton_2_11.addActionListener(main);
		panel_4.add(btnNewButton_2_11);
		
		btnNewButton_2_12 = new JButton("");
		btnNewButton_2_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton_2_12.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/empty.png")));
		btnNewButton_2_12.setBounds(194, 164, 96, 55);
		panel_4.add(btnNewButton_2_12);
		
		btnNewButton_2_13 = new JButton("");
		btnNewButton_2_13.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/enter.png")));
		btnNewButton_2_13.setBounds(289, 164, 96, 55);
		panel_4.add(btnNewButton_2_13);
		btnNewButton_2_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				/*if(ATM.this.passwordField.getPassword().length != 0) {
	                int password = Integer.parseInt(new String(ATM.this.passwordField.getPassword()));
	                boolean test=false ;
					 try {
						test=verifyPin( cad,  password) ;
					} catch (CadTransportException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 if (test) {
						// si le code pin est correcte en va passer dans la 2 ème fenetre 
							
						  // Supprimer le contenu actuel du panneau
						   panel_3.removeAll();
						   panel_3.revalidate();
						   panel_3.repaint();

						   // Ajouter le nouveau contenu au panneau
						   JPanel panel_3_nouveau = new JPanel();
						   panel_3_nouveau.setBackground(new Color(255, 255, 255));
						   panel_3_nouveau.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
						   panel_3_nouveau.setBounds(113, 136, 455, 240);
						   frmMdBank.getContentPane().add(panel_3_nouveau);
						   panel_3_nouveau.setLayout(null);

						   JLabel lblNewLabel_3 = new JLabel("   Débiter");
						   lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
						   lblNewLabel_3.setForeground(new Color(0, 0, 0));
						   lblNewLabel_3.setBounds(10, 200, 70, 30);
						   panel_3_nouveau.add(lblNewLabel_3);
						   
						   

						   // Ajoutez les autres composants au nouveau contenu ici.
						   JLabel lblNewLabel_3_1 = new JLabel("  initialiser");
						lblNewLabel_3_1.setForeground(Color.BLACK);
						lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_1.setBounds(10, 143, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_1);

						JLabel lblNewLabel_3_2 = new JLabel("  ******");
						lblNewLabel_3_2.setForeground(Color.BLACK);
						lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_2.setBounds(10, 90, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_2);

						JLabel lblNewLabel_3_2_1 = new JLabel("  ******");
						lblNewLabel_3_2_1.setForeground(Color.BLACK);
						lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_2_1.setBounds(10, 33, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_2_1);

						JLabel lblNewLabel_3_2_2 = new JLabel("  Créditer");
						lblNewLabel_3_2_2.setForeground(Color.BLACK);
						lblNewLabel_3_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_2_2.setBounds(375, 200, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_2_2);

						JLabel lblNewLabel_3_2_3 = new JLabel("voir solde");
						lblNewLabel_3_2_3.setForeground(Color.BLACK);
						lblNewLabel_3_2_3.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_2_3.setBounds(375, 143, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_2_3);

						JLabel lblNewLabel_3_2_4 = new JLabel("     reçu");
						lblNewLabel_3_2_4.setForeground(Color.BLACK);
						lblNewLabel_3_2_4.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_2_4.setBounds(375, 90, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_2_4);

						JLabel lblNewLabel_3_2_5 = new JLabel("  ******");
						lblNewLabel_3_2_5.setForeground(Color.BLACK);
						lblNewLabel_3_2_5.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_3_2_5.setBounds(375, 33, 70, 30);
						panel_3_nouveau.add(lblNewLabel_3_2_5);

						JLabel lblNewLabel_1 = new JLabel("");
						lblNewLabel_1.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/LOGO (2).png")));
						lblNewLabel_1.setBounds(174, 63, 110, 116);
						panel_3_nouveau.add(lblNewLabel_1);
						   // Rafraîchir l'affichage du panneau
						   panel_3_nouveau.revalidate();
						   panel_3_nouveau.repaint();
					 }
				}
				
				*/
				
				
			}
		});
		btnNewButton_2_13.addActionListener(main) ;
		
		//cancel
		btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				//System.exit(0);
			}
		});
		btnNewButton_4.addActionListener(main);
		
		btnNewButton_4.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/cancel.png")));
		btnNewButton_4.setBounds(290, 5, 96, 55);
		panel_4.add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"2");
				Audio.playSound("sound.wav");
		
			
           
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/two.png")));
		btnNewButton_1.setBounds(100, 5, 96, 55);
		panel_4.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                passwordField.setText(passwordField.getText()+"1");
				Audio.playSound("sound.wav");



			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/one.png")));
		btnNewButton_1_1.setBounds(5, 5, 96, 55);
		panel_4.add(btnNewButton_1_1);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(18, 328, 85, 50);
		btnNewButton_3.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		frmMdBank.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.setBounds(18, 268, 85, 50);
		btnNewButton_3_1.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		frmMdBank.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3_2.setBounds(18, 213, 85, 50);
		btnNewButton_3_2.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		frmMdBank.getContentPane().add(btnNewButton_3_2);
		
		JButton btnNewButton_3_3 = new JButton("");
		btnNewButton_3_3.setBounds(18, 157, 85, 50);
		btnNewButton_3_3.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		frmMdBank.getContentPane().add(btnNewButton_3_3);
		
		JButton btnNewButton_3_4 = new JButton("");
		btnNewButton_3_4.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_4.setBounds(578, 328, 85, 50);
		btnNewButton_3_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmMdBank.getContentPane().add(btnNewButton_3_4);
		
		JButton btnNewButton_3_5 = new JButton("");
		btnNewButton_3_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3_5.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_5.setBounds(578, 268, 85, 50);
		frmMdBank.getContentPane().add(btnNewButton_3_5);
		
		JButton btnNewButton_3_6 = new JButton("");
		btnNewButton_3_6.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3_6.setBounds(578, 213, 85, 50);
		frmMdBank.getContentPane().add(btnNewButton_3_6);
		
		JButton btnNewButton_3_7 = new JButton("");
		btnNewButton_3_7.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_7.setBounds(578, 157, 85, 50);
		frmMdBank.getContentPane().add(btnNewButton_3_7);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/LOGO (2).png")));
		lblNewLabel_1.setBounds(32, 10, 146, 117);
		frmMdBank.getContentPane().add(lblNewLabel_1);
		
		Label label = new Label("   ATM MACHINE");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Century Gothic", Font.BOLD, 42));
		label.setBounds(158, 39, 350, 59);
		frmMdBank.getContentPane().add(label);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(recu.class.getResource("/atm_sys/CASH3.png")));
		lblNewLabel1.setBounds(88, 640, 300, 36);
		frmMdBank.getContentPane().add(lblNewLabel1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/lecteur.png")));
		lblNewLabel_4.setBounds(455, 400, 224, 152);
		frmMdBank.getContentPane().add(lblNewLabel_4);
		
		
		
	}
	
	
	
	 

	
}
