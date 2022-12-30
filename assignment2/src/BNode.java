/*
 * Assignment 4: Pac Man
 * Author: Sinthuja Jeevarajhan
 * Professor: Oba
 * Purpose:
 * Date: Nov 19 2022
 */

public class BNode {
	//value of the node stored in the tree, stores the key attribute as well as its location and colour
	private Pel value; 
	//References to the left and right child and the parent of the node
	private BNode left, right, parent; 
	//superloaded constructor that initializes the left, right child and the parent of the node as well as the node value
	public BNode(Pel value, BNode left, BNode right, BNode parent) {
		this.value=value; 
		this.left= left; 
		this.right= right; 
		this.parent= parent; 
		 
	}
	//constructor that initializes a leaf node- all its attributes are null
	public BNode() {
		value=null; 
		left=null; 
		right=null;
		parent= null; 
	}
	//Accessor method- returns the parent of this node
	public BNode parent() {
		return parent; 
	}
	//Modifier method- updates the value of the parent
	public void setParent(BNode newParent) {
		parent= newParent; 
	}
	//Modifier method- updates the left child with node p
	public void setLeftChild(BNode p) {
		left= p; 
	}
	//Modifier method- updates the right child with node p
	public void setRightChild(BNode p) {
		right=p; 
	}
	//Modifier method- updates the content of the node with the Pel object value
	public void setContent(Pel value) {
		this.value=value; 
		
	}
	//Returns true if the node is a leaf- all its attributes are null
	public boolean isLeaf() {
		if (value == null && left ==null && right == null) {
			return true; 
		}
		else return false; 
	}
	//Accessor method- returns the data stored in the node
	public Pel getData() {
		return value; 
	}
	//Accessor method- returns the node of the left child
	public BNode leftChild() {
		return left; 
	}
	//Accessor method- returns the node of the right child
	public BNode rightChild() {
		return right; 
	}
	

	
}
