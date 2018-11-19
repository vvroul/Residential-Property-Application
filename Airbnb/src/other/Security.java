package other;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class Security {
     
	public static String encryptPassword(String unecryptedPassword) {
    	
        String salt = "RandomStringForExtraSecurity@#$!%^&*(*)1234567890";
        MessageDigest messageDigest=null;
        
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update((unecryptedPassword+salt).getBytes());
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return (new BigInteger(messageDigest.digest())).toString(16);
         
    }
     
    public static boolean verifyPassword(String unecryptedPassword, String encryptedPasswordFromDatabase) {
    	
        String encryptedLoginPagePassword = encryptPassword(unecryptedPassword);
        
        if (encryptedLoginPagePassword.equals(encryptedPasswordFromDatabase)) {
        	return true;
        }
        
        return false;
        
    }
     
     
 
}
