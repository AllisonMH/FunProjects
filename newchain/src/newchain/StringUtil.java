package newchain;

import java.security.MessageDigest;

public class StringUtil {
	public static String applySha256 (String input){
		try 
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			//creates a byte array called hash that will store our String input as an SHA hash object
			byte [] hash = digest.digest(input.getBytes("UTF-8"));
			
			StringBuffer hexString = new StringBuffer(); //this helps store the hash as a hexidecimal number
			
			for(int i =0; i< hash.length; i++)
				{
					String hex = Integer.toHexString(0xff & hash[i]);
					
					if(hex.length() ==1) hexString.append('0');
					hexString.append(hex);
				
				}
			return hexString.toString(); //The hex generated by the Hash is returned as a usable string 
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}