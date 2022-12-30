/*
 * Node Helper Class- represents an individual node, and includes functions to perform
 * Author: Sinthuja Jeevarajhan
 */
public class Node {
	
	    //represents the link to the next node
		private Node next;
		//represents the data item stored in each node
		private Record record;
		
		//Node constructor- initalizes a node and stores a record object
		public Node(Record record){
			this.record = record;
			next=null;
		}
		//function to retrieve the next node
		public Node getNext(){
			return next;
		}
		//function to get the record stored in the node
		public Record getRecord(){
			return record;
		}
		//function to set the next node
		public void setNext(Node next){
			this.next=next;
		}
		//function to change the value of the node
		public void setRecord(Record record){
			this.record=record;
		}
		
}

