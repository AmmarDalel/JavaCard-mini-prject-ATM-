package atm_sys;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.sun.javacard.apduio.Apdu;
import com.sun.javacard.apduio.CadT1Client;
import com.sun.javacard.apduio.CadTransportException;



public class Main implements ActionListener {
    // Constantes
    public static final byte CLA_MONAPPLET = (byte) 0xB0;
    public static final byte INS_TEST_CODE_PIN = 0x00;
    public static final byte INS_INTERROGER_COMPTE = 0x01;
    public static final byte INS_INCREMENTER_COMPTE = 0x02;
    public static final byte INS_DECREMENTER_COMPTE = 0x03;
    public static final byte INS_INITIALISER_COMPTE = 0x04;

	//singal that the PIN validation is required 
    final static short SW_EXCEED_TRY_LIMIT = 0x6321;
    	
	//verification pin échoué 
	final static short SW_VERIFICATION_FAILED= 0x6300 ;
	
	/*Exception du solde insufisant  */
	final static short SW_NEGATIVE_BALANCE = 0x6A85 ;
	
	//solde atteint son maximum 
	final static short SW_MAX_BALANCE= 0x6301 ;
    
    static CadT1Client cad ;
  

    // Fenêtre ATM
    protected static ATM window;
    //fenetre 2 
    protected static f1 f ;
    //fenetre de voir solde 
    protected static voirSolde S ;
   //fenetre de crediter
    protected static crediter c ;
    //fenetre de debiter 
    protected static debiter d ;
    //fenetre d'initialiser compte 
    protected static initialiser i ;
    //fenetre de réçue 
    protected static recu r ;
    
     static Main main ;
     static int nbTentatives = 0;
     static final int MAX_TENTATIVES = 3;
    

    public static void main(String[] args) {
    	main= new Main() ;
        EventQueue.invokeLater(() -> {
        	 window = new ATM(main);
             window.frmMdBank.setVisible(true);
             
             Audio.playSound("welcome.wav");

             
            createConnection();
            //la fenetre de choix 
            f = new f1(main);
          //la fenetre de voir solde 
            S = new voirSolde(main);
           //la fenetre de crediter 
            c = new crediter(main);
            //la fenetre de debiter 
            d = new debiter(main);
            //la fenetre de récu 
            r = new recu(main);
            //la fenetre d'initialiser compte 
             i = new initialiser(main);
           
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//System.out.println("la fenetre f "+f.hashCode());
    	
        try {
        	//System.out.println("un bouton  est à l'écoute "+e.getSource());

            int testCodePin = 0;
            
            //----------------------------- écoute de la fenetre d'acceuil ----------------

            if (e.getSource() == window.btnNewButton_2_13 && window.passwordField.getPassword().length == 4) {
                int password = Integer.parseInt(new String(window.passwordField.getPassword()));
              //  System.out.println("Le mot de passe : " + password);

                try {
                    testCodePin = verifyPin(cad, password);
                } catch (CadTransportException | IOException ex) {
                    ex.printStackTrace(); // Consider logging the exception instead
                }
                if (testCodePin == 0x9000) {
                    window.frmMdBank.setVisible(false);
                    f.setVisible(true);

                } else {
                	if (testCodePin == SW_EXCEED_TRY_LIMIT || testCodePin == SW_VERIFICATION_FAILED) {
                	    ++nbTentatives;
                	    window.lblPinIncorrect.setText("                        Invalid Card PIN ");
                	    window.lblPinIncorrectVeuillez_1.setText("                    Please try again  !!!!!");

                	    if (nbTentatives >= MAX_TENTATIVES) {
                	    	 window.lblPinIncorrect.setText("  Maximum number of attempts achieved ");
 	                        window.lblPinIncorrectVeuillez_1.setText("                  Please take your card !!");
	                        Audio.playSound("card sound.wav");

                	        Timer timer = new Timer(3000, new ActionListener() {
                	            @Override
                	            public void actionPerformed(ActionEvent e) {
                	                SwingUtilities.invokeLater(new Runnable() {
                	                    @Override
                	                    public void run() {
                	                       
                	                    }
                	                });

                	                System.exit(0);
                	            }
                	        });
                	        timer.setRepeats(false);
                	        timer.start();
                	    }
                	}


                }
            } 
            //bouton clear 
            if (e.getSource() == window.btnNewButton_2_11) {
                window.lblPinIncorrect.setText(" ");
                window.lblPinIncorrectVeuillez_1.setText(" ");
            }
            
            //bouton de quitter 
            if (e.getSource() == window.btnNewButton_4) {
            	Audio.playSound("card sound.wav");

    	        Timer timer = new Timer(3000, new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) { 
                        try {
							closeConnection();
						} catch (CadTransportException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    	                System.exit(0);
    	            }
    	        });
    	        timer.setRepeats(false);
    	        timer.start();
              
            }
                
                //----------------------------- écoute de la fenetre des choix f1  ----------------
            //cancel 
            if(e.getSource()==f.btnNewButton_1_1_1_6_1_1_3) {
		        Audio.playSound("card sound.wav");

    	        Timer timer = new Timer(3000, new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) { 	               
    	                System.exit(0);
    	            }
    	        });
    	        timer.setRepeats(false);
    	        timer.start();
            }
            
             // bouton voir solde 
             
            if (e.getSource() == f.btnNewButton_3_5) {
            //	System.out.println("bouton enter est écouter ");
                f.setVisible(false);
                S.setVisible(true);
                try {
                    int solde = interrogerCompte(cad);
                    S.lblNewLabel_3.setText("                     "+String.valueOf(solde)+" TND");
                } catch (CadTransportException | IOException e1) {
                    // Gérer les exceptions de manière appropriée, par exemple, afficher un message d'erreur
                    e1.printStackTrace();
                }

            }  
                //bouton de crediter 
            if (e.getSource() == f.btnNewButton_3_4) {
                f.setVisible(false);
                c.setVisible(true); 
                Audio.playSound("insert-your-cash.wav");

    	        Timer timer2 = new Timer(4000, new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) { 	               
    	            	
						   	           
            }  });
    	        timer2.setRepeats(false);
    	        timer2.start();
    	        
            }
            // bouton de debiter 
            if(e.getSource() == f.btnNewButton_3) {
            	f.setVisible(false);
				d.setVisible(true);				
                
            } 
            
            // bouton initialise compte 
            if(e.getSource()==f.btnNewButton_3_1) {
            	f.setVisible(false);
				i.setVisible(true);
				
				if (initialiserCompte(cad) != 0x9000) {
                    //System.out.println("Erreur : Status word différent de 0x9000");
            		c.lblOperationAvecSucees.setText("        Error !!");
                } else {
            		c.lblOperationAvecSucees.setText("        balance = 0 TND ");
                }
				
            }
            
            //bouton réçu             
            if(e.getSource()==f.btnNewButton_3_6) {
            	f.setVisible(false);				
				r.setVisible(true);				
            }
            
           
           
            
            
            //----------------------------- écoute de la fenetre de voir solde   ----------------
           
            // bouton retour 
            if(e.getSource()==S.btnNewButton_3) {
            	S.setVisible(false);
            	f.setVisible(true);
            }
            
            //bouton quitter 
            if(e.getSource()==S.btnNewButton_1_1_1_6_1_1_3) {
            	 try {
                     closeConnection();
                     S.setVisible(false);
                 } catch (CadTransportException ex) {
                     System.out.println(ex);
                 }
            }
            
            
                        
            
            //----------------------------- écoute de la fenetre créditer ----------------
         
            //enter
            if(e.getSource()==c.btnNewButton_1_1_1_6_1_3 && c.textField.getText().trim().length()!=0) {
            	int montant=Integer.parseInt(c.textField.getText().trim());
            	
							if (incrementerCompte( cad, montant)  == SW_MAX_BALANCE) {
							    //System.out.println("Erreur : Status word différent de 0x9000");
								//c.lblOperationAvecSucees.setText("        Error !!");
								// Exemple d'utilisation pour afficher un message d'information
								JOptionPane.showMessageDialog(null, "maximum balance reached !");
								
							}
							else {
								c.lblOperationAvecSucees.setText("        Successful operation ");
								c.setVisible(false);
								r.setVisible(true);
								r.lblNewLabel_1_3.setText("credited Amount :");
							    r.lblNewLabel_1_5.setText(Integer.toString(montant)+" TND");

							 
							}
    	            }
    	        
            	
            
            //retour
            if(e.getSource()==c.btnNewButton_3) {
        		/*c.lblOperationAvecSucees.setText("");
        		c.textField.setText("");*/
            	c.setVisible(false);
            	f.setVisible(true);
            }
            
            //supprimer le continue de l'affichage du message du succée ou d'érreur
            if(e.getSource()==c.btnNewButton_3 || e.getSource()==c.btnNewButton_1_1_1_6_1_3) {
            	c.lblOperationAvecSucees.setText("");
        		c.textField.setText("");
            }
            
            
            
            
            //----------------------------- écoute de la fenetre débiter ----------------
       
          //enter
            if(e.getSource()==d.btnNewButton_1_1_1_6_1_3 && d.textField.getText().trim().length()!=0) {
            	int montant=Integer.parseInt(d.textField.getText().trim());
				d.lblOperationAvecSucees.setText("         !!");
            	//take-your-cash
				if (decrementerCompte( cad, montant)  != 0x9000 ) {
					// System.out.println("dalel") ;

					// Exemple d'utilisation pour afficher un message d'information
					JOptionPane.showMessageDialog(null, "Insufficient balance !");
					d.lblOperationAvecSucees.setText("        Insufficient balance !!");

            	
				}
				else {
					Audio.playSound("débiter -.wav");
	            	
	                Timer timer1 = new Timer(13000, new ActionListener() {
	    	            @Override
	    	            public void actionPerformed(ActionEvent e) { 	               
	    	            	
					            	Audio.playSound("take-your-cash.wav");
					            	//d.lblOperationAvecSucees.setText("        Successful operation ");
									d.setVisible(false);
									r.setVisible(true);
									r.lblNewLabel_1_3.setText("debited Amount :");
								    r.lblNewLabel_1_5.setText(Integer.toString(montant)+" TND");


								 
							   	            }
	    	        });
	    	        timer1.setRepeats(false);
	    	        timer1.start();
				}

            	
            }
          
            

            //retour
            if(e.getSource()==d.btnNewButton_3) {
            	//d.lblOperationAvecSucees.setText("");
        	/*	d.textField.setText("");*/
            	d.setVisible(false);
            	f.setVisible(true);
            }
            
            //supprimer le continue de l'affichage du message du succée ou d'érreur
            if(e.getSource()==d.btnNewButton_3 || e.getSource()==d.btnNewButton_1_1_1_6_1_3) {
            	d.lblOperationAvecSucees.setText("");
        		d.textField.setText("");
            }
            
            
            
            //----------------------------- écoute de la fenetre d'initialiser  ----------------
            //initialiserCompte(CadT1Client cad)
            if(e.getSource()==i.btnNewButton_3) {
            	i.setVisible(false);
            	f.setVisible(true);
            }
            
            
            
            //----------------------------- écoute de la fenetre de réçu ----------------
            //cancel 
            if(e.getSource()==r.btnNewButton_1_1_1_6_1_1_3) {
		        Audio.playSound("card sound.wav");

    	        Timer timer = new Timer(3000, new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) { 	               
    	                System.exit(0);
    	            }
    	        });
    	        timer.setRepeats(false);
    	        timer.start();
            }
            //retour 
            if(e.getSource()==r.btnNewButton_3) {
            	r.setVisible(false);
            	f.setVisible(true);
            }
            



        
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    private static void createConnection() {
        try {
            Socket sckCarte = new Socket("localhost", 9025);
            BufferedInputStream input = new BufferedInputStream(sckCarte.getInputStream());
            BufferedOutputStream output = new BufferedOutputStream(sckCarte.getOutputStream());

            sckCarte.setTcpNoDelay(true);
            cad = new CadT1Client(input, output);
            cad.powerUp();

            // Sélection de l'applet
            selectApplet(cad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() throws CadTransportException {
        try {
            if (cad != null) {
                cad.powerDown();
                cad = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 private static int verifyPin(CadT1Client cad , int passeworld) throws CadTransportException, IOException {
	 int password = Integer.parseInt(new String(window.passwordField.getPassword()));
     System.out.println(password); 
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = CLA_MONAPPLET;
        apdu.command[Apdu.INS] = INS_TEST_CODE_PIN;
        apdu.command[Apdu.P1] = 0x00;
        apdu.command[Apdu.P2] = 0x00;
        apdu.setLc(0x04);

        byte[] pinBytes = new byte[4];
        pinBytes[0] = (byte) ((password / 1000) % 10);
        pinBytes[1] = (byte) ((password / 100) % 10);
        pinBytes[2] = (byte) ((password / 10) % 10);
        pinBytes[3] = (byte) (password % 10);

        apdu.setDataIn(pinBytes);
        cad.exchangeApdu(apdu);

    
        return apdu.getStatus() ;
        	
        	
        
    }

    private static void selectApplet(CadT1Client cad) throws CadTransportException, IOException {
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = 0x00;
        apdu.command[Apdu.INS] = (byte) 0xA4;
        apdu.command[Apdu.P1] = 0x04;
        apdu.command[Apdu.P2] = 0x00;
        byte[] appletAID = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x00, 0x00};
        apdu.setDataIn(appletAID);
        cad.exchangeApdu(apdu);
        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur lors de la sélection de l'applet ");
            System.exit(1);
        }
        System.out.println("true 1 " + apdu.getStatus());
    }
    
    private static int interrogerCompte(CadT1Client cad) throws CadTransportException, IOException {
    	int responseValue =0 ;
        Apdu apdu = createApdu(CLA_MONAPPLET, INS_INTERROGER_COMPTE, (short) 0, (short) 0, null);
        cad.exchangeApdu(apdu);

        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur : status word différent de 0x9000");
        } else {
            responseValue = byteArrayToInt(apdu.getDataOut());
            //System.out.println("La carte contient : " + responseValue + " TND");
            
        }
        return responseValue ;
    }
    
    private static int incrementerCompte(CadT1Client cad, int montant) throws CadTransportException, IOException {
        Apdu apdu = createApdu(CLA_MONAPPLET, INS_INCREMENTER_COMPTE, (short) 0, (short) 0, null);

        byte[] data = { (byte) (montant >> 8), (byte) (montant & 0xFF) };
        apdu.setDataIn(data);

        cad.exchangeApdu(apdu);

        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur : Status word différent de 0x9000");
        } else {
            System.out.println("OK");
        }
        return apdu.getStatus() ;
    }
    
    private static int decrementerCompte(CadT1Client cad , int montant) throws CadTransportException, IOException {
        Apdu apdu = createApdu(CLA_MONAPPLET, INS_DECREMENTER_COMPTE, (short) 0, (short) 0, null);

       /* Scanner scanner = new Scanner(System.in);
        short montant = scanner.nextShort();*/
        byte[] data = { (byte) (montant >> 8), (byte) (montant & 0xFF) };
        apdu.setDataIn(data);

        cad.exchangeApdu(apdu);

        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur : status word différent de 0x9000   !   "+apdu.getStatus());
			//d.lblOperationAvecSucees.setText("        Insufficient balance !!");

        } else {
            System.out.println("OK");
        }
        return apdu.getStatus() ;
    }
    
    private static int  initialiserCompte(CadT1Client cad) throws CadTransportException, IOException {
        Apdu apdu = createApdu(CLA_MONAPPLET, INS_INITIALISER_COMPTE, (short) 0, (short) 0, null);
        cad.exchangeApdu(apdu);

        if (apdu.getStatus() != 0x9000) {
            System.out.println("Erreur : status word différent de 0x9000");
        } else {
            System.out.println("OK");
        }
        return apdu.getStatus() ;
    }
    
    private static Apdu createApdu(byte cla, byte ins, short p1, short p2, byte[] data) {
        Apdu apdu = new Apdu();
        apdu.command[Apdu.CLA] = cla;
        apdu.command[Apdu.INS] = ins;
        apdu.command[Apdu.P1] = (byte) p1;
        apdu.command[Apdu.P2] = (byte) p2;

        if (data != null) {
            apdu.setLc((byte) data.length);
            apdu.setDataIn(data);
        }

        apdu.setLe(0xFF); // You can customize Le value as needed
        /*Valeur 0xFF : Dans cette ligne de code, la valeur 0xFF est utilisée comme valeur pour l'indicateur Le. 
         * Cette valeur maximale de 0xFF (255 en décimal) signifie que l'application hôte est prête à recevoir jusqu'à 255 octets
         *  de données en réponse. Cela offre une flexibilité, car cela peut être utile lorsque la longueur de la réponse n'est pas 
         *  connue à l'avance.
         */

        return apdu;
    }
    private static int byteArrayToInt(byte[] array) {
        int value = 0;
        for (byte b : array) {
            value = (value << 8) | (b & 0xFF);
        }
        return value;
    }



}
