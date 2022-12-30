/*
 * Program: Record class 
 * Purpose: This class represents the records that will be stored in the hash table. 
 * Author: Sinthuja Jeevarajhan
 * Class: 2210
 * Professor Solis Oba
 */
public class Record {
	private String key; 
	private int score, level; 
	//record constructor initalizes the key, score and level
	public Record(String key, int score, int level) {
		this.key= key; 
		this.score= score; 
		this.level= level; 
	}
	//accessor method to get the key
	public String getKey() {
		return this.key; 
	}
	//accessor method to get the score
	public int getScore() {
		return this.score; 
	}
	//accessor method to get th level
	public int getLevel() {
		return this.level; 
	}
}
