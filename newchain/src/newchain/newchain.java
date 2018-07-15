package newchain;

import java.util.ArrayList; 
import com.google.gson.*;

public class newchain {
	
	 
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	public static void main(String[] args) 
	{
		//add our blocks to the blockchain ArrayList:
		
				blockchain.add(new Block("First block", "0"));
				System.out.println("Trying to Mine block 1... ");
				//get the first object in the blockchain array list and send it over to the mineblock method to be solved for that number of 0's
				blockchain.get(0).mineBlock(difficulty);
				
				blockchain.add(new Block("2nd block",blockchain.get(blockchain.size()-1).hash));
				System.out.println("Trying to Mine block 2... ");
				blockchain.get(1).mineBlock(difficulty);
				
				blockchain.add(new Block("3rd Block",blockchain.get(blockchain.size()-1).hash));
				System.out.println("Trying to Mine block 3... ");
				blockchain.get(2).mineBlock(difficulty);	
				
				System.out.println("\nBlockchain is Valid: " + isChainValid());
				
				String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
				System.out.println("\nThe block chain: ");
				System.out.println(blockchainJson);

	}
	
	
	/* This method checks if each block has a solved hash from it's mining*/
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}
