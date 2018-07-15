package newchain;

import java.util.Date; 

public class Block {
	public String hash; 
	public String previousHash;
	private String data; //actual message data
	private long timeStamp; // time in milliseconds 
	private int nonce; // nonce's are random numbers picked by the mining method so that the hash of block will be less than or equal to the target string below
	
	//block constructor 
	public Block (String data, String previousHash){
		this.data = data; 
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); /*Have to calculate hash after we set the other values*/
	}
	
	//calculate the Hash based on the blocks actual data, the time that it came in, the previous hash and the nonce 
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data );
			return calculatedhash;
	}
	
	/*Method takes in an int difficulty which is the number of 0's that must be solved for */
	public void mineBlock(int difficulty) {
		//create a string through a char array with the "difficulty" number of 0's
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0, difficulty).equals(target)){
			nonce ++; 
			hash = calculateHash();
		}
		System.out.println("Block Mined!!!: " + hash);
	}


}
