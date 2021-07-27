
package advcyberprojectmitm;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;


public class PMSecurityPerson2 {
    public BigInteger hB;
	private BigInteger key;
	private BigInteger Y;

	public PMSecurityPerson2(){

	}
//main method m 
	public void getFromPMSecurityPerson1(CyclicGroup G, BigInteger g, BigInteger hA){
            //now person 2 will select any random value Y,jo hume cyclicGroup .java lake dega
            // Y <- Zq
		Y = G.getRandomElement(); 
                //ab person 2 pulic key calculate karega 
                // hB = g ^ Y mod p
		hB = G.powMod(g, Y); 
                //person 2 -->calculating secret key using public key of person 1
                // key = hA ^ Y mod p
		key = G.powMod(hA, Y); 
	}

	public BigInteger getKey(){
		return key;
	}

	public void sendMessageToPMSecurityPerson1(PMSecurityPerson1 A, String message) throws Exception{
		System.out.println("**************** Person2 is sending a message ****************");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
                 System.out.println();
		A.receiveMessage(cipher);
	}
	
	public void sendMessageToPMSecurityPerson1(PMSecurityPerson1 A, Hacker E, String message) throws Exception{
		System.out.println("**************** Person2 is sending a message ****************");
		System.out.println("Message: " + message);
		byte[] cipher = CryptoUtil.encrypt(message, key);
		System.out.println("Cipher: " + new String(cipher, "UTF-8"));
                 System.out.println();
		E.getMessageFromPMSecurityPerson2(cipher, A);
	}
	
	public void receiveMessage(byte[] cipherText) throws UnsupportedEncodingException{
		System.out.println("**************** Person2 Received Message ****************");
		System.out.println("Cipher: " + new String(cipherText, "UTF-8"));
		String message = CryptoUtil.decrypt(cipherText, key);
		System.out.println("Message: " + message);
	}
}


