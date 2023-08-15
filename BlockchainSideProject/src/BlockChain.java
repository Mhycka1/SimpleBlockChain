import java.util.ArrayList;
import com.google.gson.*;

public class BlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	public static void main(String[] args) {
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Attempting to mine block 1...");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Yo im the second block", 
				blockchain.get(blockchain.size()-1).hash));
		System.out.println("Attempting to mine block 2...");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hey im the third block",
				blockchain.get(blockchain.size()-1).hash));
		System.out.println("Attempting to mine block 3...");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain i Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain");
		System.out.println(blockchainJson);
	}
	
	/*This method determines a blockchain's validity by looping through the */
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		/*does the same thing with the target string as done in the block class*/
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		/*A for loop to that iterates through the blockchain to make sure the 
		 * hash of the previous block equates to the current block*/
		for(int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			/*compares the current hash and the calculated hash for the same*/
			if(!currentBlock.hash.equals(currentBlock.findHash())){
				System.out.println("Current hashes aren't equal");
				return false;
			}
			
			/*compares previousBlock hash and calculated previousBlock hash*/
			if(!previousBlock.hash.equals(currentBlock.previousHash)){
				System.out.println("Previous hashes not equal");
				return false;
			}
			/*checks if the hash has been solved*/
			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		
		return true;
		
	}

}
