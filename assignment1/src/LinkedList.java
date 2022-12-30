/*
 * Linked list Helper Class- represents a linked list stored in each value of the hash table- with functions to perform
 * Author: Sinthuja Jeevarajhan
 */

public class LinkedList {

		//number of elements in the list
		private int numElems;
		//head node- the first node in the list
		private Node head;				
		
		//super loaded constructor- initialize a linked list with the parameter node as the head
		public LinkedList(Node h){		
			// set the head as the first node
			head=h;
			//increment the number of elements to one
			numElems=1;
		}
		//initialize a new linked list
		public LinkedList(){	
			//initialize the head to null
			head=null;
			//set the number of elements to 0
			numElems=0;
		}
		
		//put method- adds the record into the table
		public int putHelper(Record rec){
			//helper variable- indicates if a collsion occured
			int collisionCheck = 0;			
			//create a new node that stores the record
			Node node = new Node(rec);	
			
			//if there are existing elements in the linked list- a collision has occured
			if(numElems!=0)	{			
				collisionCheck = 1;
			}
			//set the head as the next node
			node.setNext(head);  	
			//set the new node as the head
			head=node;	
			//increment the number of elements
			numElems++;		
			//return 1 if there was a collision 0 if there wasn't
			return collisionCheck;		
		}
		//remove method- removes a record from the list- throws InexistentKeyException if the key does not exist
		public void removeHelper(String key) throws InexistentKeyException{
			//if there are no elements- throw an exception
			if(numElems==0)	{							
				throw new InexistentKeyException("no nodes in list");
			}
			//set the previous node to null			
			Node prev = null;
			//set the current node to the head
			Node curr = head;
			//helper variable to indicate if the record was found
			boolean found = false;
			//if the record was the first element in the linked list- remove it
			if(curr.getRecord().getKey().equals(key)){	
				head=curr.getNext();
				//decrement the number of items
				numElems--;
			}
			else{
				//traverse the linked list until you find the record or you reach the end of the list (null)
				while(curr != null && found == false){	
					//if you found the record- change found to true 
					if(curr.getRecord().getKey().equals(key)){
						found = true;				
					}
					//otherwise keep traversing through the list
					else {		
						prev = curr;
						curr = curr.getNext();
					}
				}
			
				//if the key wasn't found- throw an excpection because they key does not exist
				if (found == false) 		
					throw new InexistentKeyException("key doesnt exist");
			
				//if there is only one element in the list- set head to null
				if(numElems==1){	
					head=null;
					//decrement the number of items
					numElems--;
				}
				//remove the item from the list and decrement the number of items
				else {
				prev.setNext(curr.getNext());	
				numElems--;	
				}
			}
		}
		//method to retrieve a record when given the key
		public Record getHelper(String key) {
			//return null if there are no records in the list
			if(numElems==0)							
				return null;
		
			Node curr = head;
			//traverse until you find the record or reach the end of the list
			while(curr != null){	
				//if you find the record- return it
				if(curr.getRecord().getKey().equals(key)) 
					return curr.getRecord();				

				//traverse the list
				curr=curr.getNext();		
			}
			//return null if the record wasn't found
			return null;							
		}
		
}
