package projetJavaCard;

import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISOException;
import javacard.framework.OwnerPIN;
import javacard.framework.Util;
import javacard.framework.ISO7816;

public class MonApplet extends Applet {
	/* Constantes */
	public static final byte CLA_MONAPPLET = (byte) 0xB0;
	public static final byte INS_TEST_CODE_PIN = 0x00;
	public static final byte INS_INTERROGER_COMPTE = 0x01;
	public static final byte INS_INCREMENTER_COMPTE = 0x02;
	public static final byte INS_DECREMENTER_COMPTE = 0x03;
	public static final byte INS_INITIALISER_COMPTE = 0x04;
	
	
	//le maximum de code pin erroner 
	public final static byte MAX_ERROR_PIN= (byte) 0x03 ;
	
	//longeur maximale du code pin 
	public final static byte MAX_PIN_LENGTH= (byte) 0x04 ;
	
	private byte[] INITPIN= {(byte) 3 , (byte) 1 , (byte) 1 , (byte) 9 } ;
	
	
	//------------- EXCEPTION --------------//
	
	/*Exception le montant est négative */
	final static short SW_NEGATIVE_MONTANT = 0x6A85 ;  
	
	//singal that the PIN validation is required 
	final static short SW_EXCEED_TRY_LIMIT=0x6321 ;
	
	//verification du code pin échoué 
	final static short SW_VERIFICATION_FAILED= 0x6300 ;
	
	//solde atteint son maximum 
	final static short SW_MAX_BALANCE= 0x6301 ;
	
	/* Variables */
	 private byte pinTriesRemaining;
	private short montant ;
	
	OwnerPIN pin ;

	private MonApplet() {
		montant=0 ;
		pin = new OwnerPIN(MAX_ERROR_PIN , MAX_PIN_LENGTH);
		//initialiser le mot de passe
		pin.update(INITPIN, (short) 0, (byte)0x04);
		 pinTriesRemaining = MAX_ERROR_PIN;
	}
	

	public boolean select() {
		//pas de selection si le pin est blocker 
		if(pin.getTriesRemaining()==0) return false ;
		return true ;
	}
	
	public void deselect() {
		pin.reset();
	}

	public static void install(byte bArray[], short bOffset, byte bLength)
			throws ISOException {

			new MonApplet().register();
			}
	
	public void process(APDU apdu) throws ISOException {
			byte[] buffer = apdu.getBuffer();
			
			if (this.selectingApplet()) return;
			
			if (buffer[ISO7816.OFFSET_CLA] != CLA_MONAPPLET) {
			ISOException.throwIt(ISO7816.SW_CLA_NOT_SUPPORTED);
			}
			
			switch (buffer[ISO7816.OFFSET_INS]) {
			case INS_TEST_CODE_PIN:
				verifycodepin(apdu);
			break;
			case INS_INTERROGER_COMPTE:
				Interroger(apdu) ;
			break;
			case INS_INCREMENTER_COMPTE:
				crediter(apdu) ;
			//apdu.setOutgoingAndSend((short) 0, (short) 1);
			break;
			 case INS_DECREMENTER_COMPTE:
				 debiter(apdu); 
			break;
			 case INS_INITIALISER_COMPTE :
				 montant= 0 ;
				
				 break ;
			default:ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
			}
		

	}
		//retirer un montant 
		private void debiter(APDU apdu) {
			//recuperation buffer
			byte[] buffer=apdu.getBuffer(); 
			//récupération de nombre des bites du champs CDATA 
			byte numBytes = (byte) (buffer[ISO7816.OFFSET_LC]) ;
			//récupération de nombre des bites lues
			byte byteRead = (byte) (apdu.setIncomingAndReceive()) ;
			
			// récupérer le montant à débiter 
            if (numBytes != 2) {
                ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
            }

            // Reconstituer la valeur short à partir des octets reçus
                                           // les octets du poids le plus faible          // les octets du poids le plus fort sans signe (0XFF) (car byte en java est signé)
            short receivedValue = (short) (((short) buffer[ISO7816.OFFSET_CDATA] << 8) | (buffer[ISO7816.OFFSET_CDATA + 1] & 0xFF));
			
			//vérifier le nouvelle montant 
			if((short) (montant-receivedValue) < (short) 0)
				ISOException.throwIt(SW_NEGATIVE_MONTANT) ;
			montant = (short) (montant - receivedValue) ;
		}
		
		//ajouter un montant 
		private void crediter(APDU apdu) {
			byte[] buffer=apdu.getBuffer();					
			// byte[] data = new byte[2];
	            short numBytes = apdu.setIncomingAndReceive();

	            if (numBytes != 2) {
	                ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
	            }
	           
	           
	            // Reconstituer la valeur short à partir des octets reçus
	                                           // les octets du poids le plus faible          // les octets du poids le plus fort sans signe (0XFF) (car byte en java est signé)
	            short receivedValue = (short) (((short) buffer[ISO7816.OFFSET_CDATA] << 8) | (buffer[ISO7816.OFFSET_CDATA + 1] & 0xFF));
	           
	            //-----------
	            
	            /*if ((short)65536 -(short) montant  == 0) {
	                ISOException.throwIt(SW_MAX_BALANCE);
	            }*/
	            /*else {
	            	if((short)65536 -(short) montant < (short)receivedValue )ISOException.throwIt(SW_MAX_BALANCE);
	            }*/
	            
	            montant += receivedValue;
		}
		
		private void Interroger(APDU apdu) {
			byte[] buffer=apdu.getBuffer();
			
			//la langueur sortante 
			short le= apdu.setOutgoing();
			
			if (le<2) {
				ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
			}
			
			//fixer la langeur sortante en 2 octets 
			apdu.setOutgoingLength((byte) 2);
			/*prend les 8 bits les plus significatifs de la variable balance (les bits situés à droite),
			 *  en les déplaçant vers la droite de 8 positions (en effectuant un décalage binaire vers
			 *   la droite de 8 bits). Ensuite, elle convertit ces 8 bits en un octet (byte) et stocke
			 *    cette valeur à l'index 0 du tampon buffer. Cela correspond à l'octet le plus significatif
			 *     de la valeur balance.
			 */
			buffer[0]= (byte) ( montant >>8) ;
			/*
			 Cette ligne effectue un "et logique" (bitwise AND) entre la variable balance et la valeur 
			 hexadécimale 0xFF (qui est 11111111 en binaire). Cela a pour effet de conserver uniquement 
			 les 8 bits les moins significatifs de balance. Ensuite, elle convertit ces 8 bits en un 
			 octet (byte) et stocke cette valeur à l'index 1 du tampon buffer. Cela correspond à l'octet 
			 le moins significatif de la valeur balance.
			 */
			buffer[1]= (byte) ( montant & 0xFF) ;
			
			/*
			  Cette ligne place la valeur de 16 bits de balance dans le tampon à l'index 0 en utilisant 
			  la méthode Util.setShort
			 */
			//1  Util.setShort(buffer, (short)0, montant ) ;
			
			//apdu.setOutgoingAndSend((short) 0, (short) 2) ;
			//1 apdu.sendBytes((short) 0, (short) 2);/*: Cela envoie les données de réponse (2 octets) depuis
			                                        //le tampon, en commençant par l'index 0, vers l'application hôte.*/
			apdu.sendBytesLong(buffer, (short)0,(short) 2) ;
			
			 			
			
		}

		
		private void verifycodepin(APDU apdu) {
	        byte[] buffer = apdu.getBuffer();

	        // Récupérer le code PIN
	        //le nombre des bites luent 
	        byte byteRead = (byte) (apdu.setIncomingAndReceive());

	        // Vérifier le code PIN
	        if (pinTriesRemaining == 0) {
	            ISOException.throwIt(SW_EXCEED_TRY_LIMIT);
	        }

	        // Assurez-vous que byteRead est égal à la longueur attendue du code PIN
	        if (byteRead != MAX_PIN_LENGTH) {
	            ISOException.throwIt(ISO7816.SW_WRONG_LENGTH);
	        }

	        // Copier le code PIN entré dans un tableau de bytes temporaire
	        byte[] enteredPIN = new byte[MAX_PIN_LENGTH];
	        Util.arrayCopy(buffer, ISO7816.OFFSET_CDATA, enteredPIN, (short) 0, MAX_PIN_LENGTH);

	        // Effectuez la vérification du code PIN
	        if (!pin.check(enteredPIN, (short) 0, MAX_PIN_LENGTH)) {
	            // Décrémenter le nombre d'essais restants pour le code PIN
	            pinTriesRemaining--;
	            ISOException.throwIt(SW_VERIFICATION_FAILED);
	        }

	        // Réinitialiser le nombre d'essais restants si la vérification est réussie
	        pinTriesRemaining = MAX_ERROR_PIN;
	    }

}
