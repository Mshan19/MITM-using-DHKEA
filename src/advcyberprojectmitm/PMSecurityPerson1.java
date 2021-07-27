
package advcyberprojectmitm;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;


public class PMSecurityPerson1 {
    
    public CyclicGroup G;
	private BigInteger X;
	public BigInteger hA;
	public BigInteger g;
	private BigInteger key;
	
	public PMSecurityPerson1(int n){
            
		G = new CyclicGroup(n);
		g = G.getGenerator();
               // to communicate with Person2 person1 should share its public key 
              //toh public key calculate krne k leye 
             //person 1 ne random Value X select kiya , jo hume Cyclic group lake dega 
             // X <- Zq
		X = G.getRandomElement(); 
                //public key calculate 
       // hA = g^X mod p
		hA = G.powMod(g, X); 
	}
	
        //here it's taking key of Person2
	public void getFromPMSecurityPerson2(BigInteger hB){
            //person 1 -->calculating secret key using public key of person 2
		key = G.powMod(hB, X);
	}
	
	public BigInteger getKey(){
		return key;
	}
        //this msg is for person 2
        public void sendMessageToPMSecurityPerson2(PMSecurityPerson2 B, String message) throws Exception{
		System.out.println("****************Person1 is sending a message ****************");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
                System.out.println();
		B.receiveMessage(cipher);
	}
        
	//this message is for person 2 pr hacker capture karega
	public void sendMessageToPMSecurityPerson2(PMSecurityPerson2 B, Hacker E, String message) throws Exception{
		System.out.println("**************** Person1 is sending a message ****************");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
               //it transfer unicode to matching binary string
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
                 System.out.println();
                E.getMessageFromPMSecurityPerson1(cipher, B);
	}
	//yeh message return to person2 se hona tha pr it will come via hacker se aayega 
	public void receiveMessage(byte[] cipherText) throws UnsupportedEncodingException{
		System.out.println("**************** Person1 Received Message ****************");
		System.out.println("Cipher: " + new String(cipherText, "UTF-8"));
		String message = CryptoUtil.decrypt(cipherText, key);
		System.out.println("Message: " + message);
	}
	}   


    
    

