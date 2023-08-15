import java.util.Date;

public class Block {

	/*A class called block that defines the objects that will hold the data and
	 * be linked together*/
	
	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = findHash();
	}
	
	public String findHash() {
		/*We pass in all the block parameters and information because we don't
		 * want any part of the block and it's information tampered with so we
		 * compute the hash with all information*/
		String hash = stringUtility.useSha256(previousHash + 
				Long.toString(timeStamp) + Integer.toString(nonce) + data); 
		
		return hash;
	}
	
	/*A method to serve as a miner and be a proof-of-work. The method takes in 
	 * an int called difficulty which is the number of 0's that must be solved
	 * for.*/
	public void mineBlock(int difficulty) {
		
		/*the target string consists of the number of characters in the 
		 * parameter 'difficulty. The replace method is used to replace all the
		 * null characters with a 0. This target is what mined blocks need to 
		 * match to be considered valid*/
		String target = new String(new char[difficulty]).replace('\0', '0');
		
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = findHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
	
}
