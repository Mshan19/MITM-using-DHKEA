
package advcyberprojectmitm;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Hacker {
 private BigInteger Z;
	private BigInteger keyA;
	private BigInteger keyB;
	private BigInteger hC;
	
	public Hacker(){

	}
	//ab hacker ka task hai --to generate any 2 random secret key or private key for person 1 and person2 
	public void hackerdrop(PMSecurityPerson1 A, PMSecurityPerson2 B){
            ////now Hacker will select any random value Z,jo hume cyclicGroup .java lake dega
            // Z <- Zq
		Z = A.G.getRandomElement();
                //ab Hacker pulic key calculate karega 
                // hc = g ^ Z mod p
		hC = A.G.powMod(A.g, Z);
                //hacker will calculate secret key for person1
		keyA = A.G.powMod(A.hA, Z);
		B.getFromPMSecurityPerson1(A.G, A.g, hC);
                //hacker will calculate secret key for person2
		keyB = A.G.powMod(B.hB, Z);
		A.getFromPMSecurityPerson2(hC);
	}
	
	public BigInteger getKeyA(){
		return keyA;
	}
	
	public BigInteger getKeyB(){
		return keyB;
	}
	
	public void getMessageFromPMSecurityPerson1(byte[] cipher, PMSecurityPerson2 B) throws UnsupportedEncodingException{
		System.out.println("**************** Hacker Got Message From Person 1 ****************");
		System.out.println("Cipher Person1:  " + new String(cipher, "UTF-8"));
		String message = CryptoUtil.decrypt(cipher, keyA);
		System.out.println("Message: " + message);
		byte[] cipherB = CryptoUtil.encrypt(message, keyB);
		System.out.println("Cipher Person2:  " + new String(cipherB, "UTF-8"));
                 System.out.println();
		B.receiveMessage(cipherB);
	}
	
	public void getMessageFromPMSecurityPerson2(byte[] cipher, PMSecurityPerson1 A) throws UnsupportedEncodingException{
		System.out.println("**************** Hacker Got Message From Person2 ****************");
		System.out.println("Cipher Person2:  " + new String(cipher, "UTF-8"));
		String message = CryptoUtil.decrypt(cipher, keyB);
		System.out.println("Message: " + message);
		byte[] cipherA = CryptoUtil.encrypt(message, keyA);
		System.out.println("Cipher Person1:  " + new String(cipherA, "UTF-8"));
                 System.out.println();
		A.receiveMessage(cipherA);
        }   
}
    

