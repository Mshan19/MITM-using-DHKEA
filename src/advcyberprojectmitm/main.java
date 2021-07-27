
package advcyberprojectmitm;


public class main {
public static void main(String[] args) {
   boolean isEav = true; //Set to true to add eavsdropper.s
		PMSecurityPerson1 A = new PMSecurityPerson1(256); 
                //Input is security parameter. 
		PMSecurityPerson2 B = new PMSecurityPerson2();
		
		if(isEav){
			Hacker E = new Hacker();
                        E.hackerdrop(A, B);
                        System.out.println("Person1 key: " + A.getKey());
                        System.out.println();
                        System.out.println("Person2 key: " + B.getKey());
                        System.out.println();
   //iss moment p jab public key exchange ho rahi ho gi  P1-->P2 m ,tab hacker capture kr lega keys ko
			System.out.println("Hacker Key Person1: " + E.getKeyA());
                        System.out.println();
			System.out.println("Hacker Key Person2: " + E.getKeyB());
                        System.out.println();
			
			try {
				A.sendMessageToPMSecurityPerson2(B, E, "When u r leaving");
				System.out.println();
				B.sendMessageToPMSecurityPerson1(A, E, "Tonight");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		//yeha p public key share ho ri hai  btw person 1 and person 2
		B.getFromPMSecurityPerson1(A.G, A.g, A.hA);
		A.getFromPMSecurityPerson2(B.hB);
		System.out.println("Person2 key: " + B.getKey());
		System.out.println("Person1 key: " + A.getKey());
		
    }
}
    

