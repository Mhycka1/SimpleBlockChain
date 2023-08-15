import java.security.MessageDigest;

public class stringUtility {
	
	/*A static class that will invoke the SHA256 cryptographic algorithm to 
	 * create the blockchain hashes*/
	public static String useSha256(String input) {
		try {
			/*we create an instance of the MessageDigest class, which is used to
			 * perform hash functions*/
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			/*this is where the sha256 gets applied to the input. A hash is 
			 * created from using the 'digest' instance to compute the hash of
			 * the input string's bytes*/
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer();
			/*This loop iterates over every byte of the hash and converts
			 * each byte to a hexadecimal string.*/
			for(int i = 0; i < hash.length; i++) {
				/*The 0xff & hash[i] is performed to make sure the byte is 
				 * treated as an unsigned value*/
				String hex = Integer.toHexString(0xff & hash[i]);
				/*If the string is only one character then a leading 0 is added
				 * for consistency so every byte is represented by 
				 * two characters*/
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			
			return hexString.toString();
						
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
}
