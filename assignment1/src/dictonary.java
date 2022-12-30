/*
 * Program: Dictionary 
 * Purpose: This class represents the structure that will store the record. The hash table implementation uses the seperate chaining method
 * Author: Sinthuja Jeevarajhan
 * Class: 2210
 * Professor Solis Oba
 */
public class Dictionary implements DictionaryADT {

	//Hash table- of type LinkedList which I have included in the assignment submission
	private LinkedList[] T; 
	private int numRecords=0; 
	private int size; 
					
	//constructor that allows you to create a table of preferred size
	public Dictionary(int size) {
		//initializing the table
		T= new LinkedList[size]; 
		//setting the size according to parameter
		this.size=size; 
		//initializing each value in the table as a linked list to store multiple data elements
		for (int i=0; i< size; i++) {
			T[i]= new LinkedList(); 
		}
		
	}
	//hash function- seperate chaining
	public int h(String key) {
		//value for the mod- prime number
		int x= 33; 
		//cast the value to an int 
		int value= (int)key.charAt(key.length()-1); 
		//polynomial hash code function
		for (int i=key.length()-2; i >=0; i--) {
			value=((value*x) + (int)key.charAt(i)) % size; 
			
		}
		//return the position in the table
		return value; 
	}
	//put method to add a record into the array- throws duplicate key exception
	public int put(Record rec) throws DuplicatedKeyException {
		//get the record
		String record= rec.getKey(); 
		//if the record does not exist, increment the number of records and use the add method to insert the record into the table
		if (get(record) == null) {
			numRecords++; 
			return T[h(record)].putHelper(rec); 
		}
		else {
			//throws an excpetion- the key already exists
			throw new DuplicatedKeyException("duplicate key"); 
			
		}
	}
	//remove function- throws InexistentKeyException if the key does not exist
	public void remove(String key) throws InexistentKeyException{
		//if the record exists and decrement the number of records, remove it else throw exception
		try {
			T[h(key)].removeHelper(key);
			numRecords--; 
			
		} catch(InexistentKeyException e) {
			throw new InexistentKeyException("key does not exist"); 
		}
	}
	//get function- retrieves the record from the table
	public Record get(String key) {
		return T[h(key)].getHelper(key); 
	}
	// returns number of records
	public int numRecords() {
		return numRecords; 
	}
	
}
