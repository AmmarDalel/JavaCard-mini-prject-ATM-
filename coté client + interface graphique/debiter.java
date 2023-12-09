package atm_sys;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class debiter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextField textField;
	
	protected static Main main  ;
	
	protected JLabel lblOperationAvecSucees ;
	
	
	//-----------les boutons -----------
	//autre
	protected JButton btnNewButton_3_7 ;
	//200d 
	protected JButton btnNewButton_3_6 ;
	//100d
	protected JButton btnNewButton_3_5 ;
	//80d 
	protected JButton btnNewButton_3_4  ;
	//60d
	protected JButton btnNewButton_3_3 ;
	//40d
	protected JButton btnNewButton_3_2  ;
	//20d
	protected JButton btnNewButton_3_1 ;
	//enter 
	protected JButton btnNewButton_1_1_1_6_1_3 ;
	
	
	
	//retour 
	protected JButton btnNewButton_3 ;


	//constructeur 
	public debiter( Main main ) {
		this.main=main ;
		initialize();
	}
	public void initialize() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("MD BANK");
		setBounds(100, 100, 701, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		panel_3.setBounds(113, 136, 455, 240);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		final JLabel lblNewLabel_1 = new JLabel("     Choose the amount to debit  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(111, 63, 220, 30);
		panel_3.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(142, 103, 150, 20);
		panel_3.add(textField);
		
		JLabel lblNewLabel_2 = new JLabel("Back");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBackground(new Color(240, 240, 240));
		lblNewLabel_2.setBounds(10, 205, 45, 13);
		panel_3.add(lblNewLabel_2);
		
		final JLabel lblNewLabel_2_1 = new JLabel("  20");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_1.setBounds(10, 154, 45, 13);
		panel_3.add(lblNewLabel_2_1);
		
		final JLabel lblNewLabel_2_2 = new JLabel("  40");
		lblNewLabel_2_2.setForeground(Color.BLACK);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_2.setBounds(10, 94, 45, 13);
		panel_3.add(lblNewLabel_2_2);
		
		final JLabel lblNewLabel_2_3 = new JLabel("  60");
		lblNewLabel_2_3.setForeground(Color.BLACK);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_3.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_3.setBounds(10, 34, 45, 13);
		panel_3.add(lblNewLabel_2_3);
		
		final JLabel lblNewLabel_2_4 = new JLabel("  80");
		lblNewLabel_2_4.setForeground(Color.BLACK);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_4.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_4.setBounds(400, 206, 45, 13);
		panel_3.add(lblNewLabel_2_4);
		
		final JLabel lblNewLabel_2_5 = new JLabel("  100");
		lblNewLabel_2_5.setForeground(Color.BLACK);
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_5.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_5.setBounds(400, 155, 45, 13);
		panel_3.add(lblNewLabel_2_5);
		
		final JLabel lblNewLabel_2_6 = new JLabel("  200");
		lblNewLabel_2_6.setForeground(Color.BLACK);
		lblNewLabel_2_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_6.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_6.setBounds(400, 95, 45, 13);
		panel_3.add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_7 = new JLabel("Other");
		lblNewLabel_2_7.setForeground(Color.BLACK);
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_7.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_7.setBounds(400, 35, 45, 13);
		panel_3.add(lblNewLabel_2_7);
		
		//affichage du résultat d'opération 
		 lblOperationAvecSucees = new JLabel("");
		lblOperationAvecSucees.setForeground(Color.RED);
		lblOperationAvecSucees.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOperationAvecSucees.setBounds(126, 169, 200, 25);
		panel_3.add(lblOperationAvecSucees);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 128, 128));
		panel_4.setBounds(55, 400, 390, 222);
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");

				textField.setText(textField.getText()+"1");

			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/one.png")));
		btnNewButton_1_1.setBounds(5, 5, 96, 55);
		panel_4.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");

				textField.setText(textField.getText()+"2");

			}
		});
		btnNewButton_1_1_1.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/two.png")));
		btnNewButton_1_1_1.setBounds(100, 5, 96, 55);
		panel_4.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");

				textField.setText(textField.getText()+"3");

			}
		});
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/three.png")));
		btnNewButton_1_1_1_1.setBounds(196, 5, 96, 55);
		panel_4.add(btnNewButton_1_1_1_1);
		
		JButton btnNewButton_1_1_1_2 = new JButton("");
		btnNewButton_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");

				textField.setText(textField.getText()+"4");

			}
		});
		btnNewButton_1_1_1_2.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/four.png")));
		btnNewButton_1_1_1_2.setBounds(5, 58, 96, 55);
		panel_4.add(btnNewButton_1_1_1_2);
		
		JButton btnNewButton_1_1_1_3 = new JButton("");
		btnNewButton_1_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"5");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_1_1_1_3.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/five.png")));
		btnNewButton_1_1_1_3.setBounds(100, 58, 96, 55);
		panel_4.add(btnNewButton_1_1_1_3);
		
		JButton btnNewButton_1_1_1_4 = new JButton("");
		btnNewButton_1_1_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"6");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_1_1_1_4.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/six.png")));
		btnNewButton_1_1_1_4.setBounds(196, 58, 96, 55);
		panel_4.add(btnNewButton_1_1_1_4);
		
		JButton btnNewButton_1_1_1_5 = new JButton("");
		btnNewButton_1_1_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"7");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_1_1_1_5.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/seven.png")));
		btnNewButton_1_1_1_5.setBounds(5, 112, 96, 55);
		panel_4.add(btnNewButton_1_1_1_5);
		
		JButton btnNewButton_1_1_1_6 = new JButton("");
		btnNewButton_1_1_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"8");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_1_1_1_6.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/eight.png")));
		btnNewButton_1_1_1_6.setBounds(100, 112, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6);
		
		JButton btnNewButton_1_1_1_7 = new JButton("");
		btnNewButton_1_1_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"9");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_1_1_1_7.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/nine.png")));
		btnNewButton_1_1_1_7.setBounds(196, 112, 95, 54);
		panel_4.add(btnNewButton_1_1_1_7);
		
		JButton btnNewButton_1_1_1_6_1 = new JButton("");
		btnNewButton_1_1_1_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText()+"0");
				Audio.playSound("sound.wav");


			}
		});
		btnNewButton_1_1_1_6_1.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/zero.png")));
		btnNewButton_1_1_1_6_1.setBounds(100, 164, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1);
		
		JButton btnNewButton_1_1_1_6_1_1 = new JButton("");
		btnNewButton_1_1_1_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1_1_6_1_1.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/empty.png")));
		btnNewButton_1_1_1_6_1_1.setBounds(5, 164, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1_1);
		
		JButton btnNewButton_1_1_1_6_1_2 = new JButton("");
		btnNewButton_1_1_1_6_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1_1_6_1_2.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/empty.png")));
		btnNewButton_1_1_1_6_1_2.setBounds(194, 164, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1_2);
		
		
		//enter 
		 btnNewButton_1_1_1_6_1_3 = new JButton("");
		btnNewButton_1_1_1_6_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				
			}
		});
		btnNewButton_1_1_1_6_1_3.addActionListener(main);
		
		btnNewButton_1_1_1_6_1_3.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/enter.png")));
		btnNewButton_1_1_1_6_1_3.setBounds(289, 164, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1_3);
		
		JButton btnNewButton_1_1_1_6_1_1_1 = new JButton("");
		btnNewButton_1_1_1_6_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1_1_6_1_1_1.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/empty.png")));
		btnNewButton_1_1_1_6_1_1_1.setBounds(289, 112, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1_1_1);
		
		JButton btnNewButton_1_1_1_6_1_1_2 = new JButton("");
		btnNewButton_1_1_1_6_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				textField.setText("");

			}
		});
		btnNewButton_1_1_1_6_1_1_2.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/clear.png")));
		btnNewButton_1_1_1_6_1_1_2.setBounds(289, 58, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1_1_2);
		
		JButton btnNewButton_1_1_1_6_1_1_3 = new JButton("");
		btnNewButton_1_1_1_6_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				System.exit(0);
			}
		});
		
		btnNewButton_1_1_1_6_1_1_3.setIcon(new ImageIcon(f1.class.getResource("/atm_sys/cancel.png")));
		btnNewButton_1_1_1_6_1_1_3.setBounds(290, 5, 96, 55);
		panel_4.add(btnNewButton_1_1_1_6_1_1_3);
		
		//retour 
		 btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				
			}
		});
		btnNewButton_3.addActionListener(main);
		
		btnNewButton_3.setBounds(18, 328, 85, 50);
		btnNewButton_3.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		getContentPane().add(btnNewButton_3);
		
		//20d
		 btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				textField.setText(lblNewLabel_2_1.getText());

			}
		});
		btnNewButton_3_1.addActionListener(main);
		
		btnNewButton_3_1.setBounds(18, 268, 85, 50);
		btnNewButton_3_1.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		getContentPane().add(btnNewButton_3_1);
		
		//40d
		 btnNewButton_3_2 = new JButton("");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				textField.setText(lblNewLabel_2_2.getText());

			}
		});
		btnNewButton_3_2.addActionListener(main);
		
		btnNewButton_3_2.setBounds(18, 213, 85, 50);
		btnNewButton_3_2.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		getContentPane().add(btnNewButton_3_2);
		
		//60d
		 btnNewButton_3_3 = new JButton("");
		btnNewButton_3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");

				textField.setText(lblNewLabel_2_3.getText());

			}
		});
		 btnNewButton_3_3.addActionListener(main);
		 
		btnNewButton_3_3.setBounds(18, 157, 85, 50);
		btnNewButton_3_3.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR GAUCHE.png")));
		getContentPane().add(btnNewButton_3_3);
		
		//80d
		 btnNewButton_3_4 = new JButton("");
		btnNewButton_3_4.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_4.setBounds(578, 328, 85, 50);
		btnNewButton_3_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				textField.setText(lblNewLabel_2_4.getText());

			}
		});
		btnNewButton_3_4.addActionListener(main) ;
		getContentPane().add(btnNewButton_3_4);
		
		//100d
		 btnNewButton_3_5 = new JButton("");
		btnNewButton_3_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				textField.setText(lblNewLabel_2_5.getText());

			}
		});
		btnNewButton_3_5.addActionListener(main);
		
		btnNewButton_3_5.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_5.setBounds(578, 268, 85, 50);
		getContentPane().add(btnNewButton_3_5);
		
		//200d
		btnNewButton_3_6 = new JButton("");
		btnNewButton_3_6.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				textField.setText(lblNewLabel_2_6.getText());

			}
		});
		btnNewButton_3_6.addActionListener(main);
		
		btnNewButton_3_6.setBounds(578, 213, 85, 50);
		getContentPane().add(btnNewButton_3_6);
		
		//autre
		btnNewButton_3_7 = new JButton("");
		btnNewButton_3_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("sound.wav");
				lblNewLabel_1.setText("Enter the amount of your choice :");

			}
		});
		btnNewButton_3_7.addActionListener(main);
		
		btnNewButton_3_7.setIcon(new ImageIcon(ATM.class.getResource("/atm_sys/RETOUR D.png")));
		btnNewButton_3_7.setBounds(578, 157, 85, 50);
		getContentPane().add(btnNewButton_3_7);
		
		Label label = new Label("   ATM MACHINE");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Century Gothic", Font.BOLD, 42));
		label.setBounds(162, 36, 350, 59);
		getContentPane().add(label);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(recu.class.getResource("/atm_sys/LOGO (2).png")));
		lblNewLabel_3.setBounds(28, 10, 110, 116);
		getContentPane().add(lblNewLabel_3);
		 
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(recu.class.getResource("/atm_sys/CASH3.png")));
		lblNewLabel1.setBounds(88, 640, 300, 36);
		getContentPane().add(lblNewLabel1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(debiter.class.getResource("/atm_sys/lecteur.png")));
		lblNewLabel_4.setBounds(455, 400, 224, 152);
		getContentPane().add(lblNewLabel_4);
		
		

}	
}
