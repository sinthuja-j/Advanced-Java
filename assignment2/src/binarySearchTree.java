/*
 * Assignment 4: Pac Man
 * Author: Sinthuja Jeevarajhan
 * Professor: Oba
 * Purpose: This class implements an ordered dictonary using a BST- each node stores the attributes. 
 * Only the internal nodes in this tree store information- leaves are nodes that do not store data
 * Date: Nov 19 2022
 */
public class BinarySearchTree implements BinarySearchTreeADT {
	//root of class BNode
	private BNode root; 
	//Binary Search Tree constructor- initializes the root as a new node
	public BinarySearchTree() {
		root= new BNode(); 
	}
	//get method that calls the helper function- getNode, if node is not in the tree- null is returned
	public Pel get(BNode r, Location key) {
		return getNode(r,key).getData(); //calls getData to return the key attribute stored in this node
	}
	//helper method getNode- returns the node that is being searched for, searched using the root and key
	//returns the spot where the node should've been if it doesn't exist
	private BNode getNode(BNode r, Location key) {
		//if the root is a leaf- return the root
		if (r.isLeaf()) {
			return r; 
		}
		//if the root has children
		else {
			//if the root key attributes are equal- return the root as it is found
			if (r.getData().getLocus().compareTo(key) == 0) {
				return r; 
			//otherwise- if the key is less than the root- recursivley search the left subtree
			} else if (r.getData().getLocus().compareTo(key) == 1) {
				return getNode(r.leftChild(), key); 
			//if the key is greater than the root- recursively search the right subtree
			} else {
				return getNode(r.rightChild(), key); 
			}
		}
	}
	//Inserts the node newData in the tree if no data item with the same key is in the tree
	//If a node exists with the same key the duplicated key exception is thrown
	public void put(BNode r, Pel newData) throws DuplicatedKeyException {
		//calls the getNode helper method to find the node with the key of newData
		BNode p= getNode(r, newData.getLocus()); 
		//is the node is not a leaf- then it stores information, so the key exists so we throw an exception
		if (p.isLeaf() == false) {
			throw new DuplicatedKeyException("key exists"); 
		} else {
			//otherwise the node is a leaf so we can insert the information of new data
			p.setContent(newData);
			//initialize left and right children of the node
			BNode left= new BNode(); 
			BNode right= new BNode(); 
			p.setLeftChild(left);
			p.setRightChild(right);
			//set the parent of the left and right children to be the node found
			left.setParent(p);
			right.setParent(p);
		}
	}
	//helper method to return the smallest node in the tree
	//travereses the left most path to obtain the smallest node in the tree
	private BNode smallestNode(BNode r) {
		while (r.isLeaf() == false) {
			r=r.leftChild(); 
		} 
		//once the last leftmost leaf node is found, return its parent as that is the smallest node
		return r.parent(); 
	}
	//helper method to return the largest node in the tree
	//traverses the left most path to obtain the largest node in the tree
	private BNode largestNode(BNode r) {
		while(r.isLeaf() == false) {
			r=r.rightChild(); 
		}
		//once the last rightmost leaf node is found return its parent as that is the largest node
		return r.parent(); 
	}
	//Removes the data item with the given key
	//if the key is not found the Inexistent Key Exception is thrown 
	public void remove(BNode r, Location key) throws InexistentKeyException {
		//calls the helper getNode method to find the node with the given key
		BNode p= getNode(r, key); 
		//if the node is a leaf- then the key does not exist- so an exception is thrown
		if (p.isLeaf() == true){
			throw new InexistentKeyException("key doesn't exist");
		}
		//otherwise the node needs to be removed
		else{
			//if either the left or right child is a leaf
			//then that node and the leaf node can be removed and the parent node will point to which ever child is an internal node
			if (p.leftChild().isLeaf() || p.rightChild().isLeaf()){
				//execute if the left child is a leaf
				if(p.leftChild().isLeaf()){
					BNode right = p.rightChild();
					BNode parent = p.parent();
					//if the node is the root- need to find a new root
					if (parent == null){
						root = right;
						right.setParent(null);
					}
					//if parent points to internal child
					else{
						if(parent.rightChild().equals(p)){
							parent.setRightChild(right);
							right.setParent(parent);
						}
						else{
							parent.setLeftChild(right);
							right.setParent(parent);
						}
					}
				}
				//execute if the right child is a leaf
				else{
					BNode left = p.leftChild();
					BNode parent = p.parent();
					//if the node is the root- need to find a new root
					if (parent == null){
						root = left ;
						left.setParent(null);
					}
					else{
						//if parent points to internal child
						if(parent.rightChild().equals(p)){
							parent.setRightChild(left);
							left.setParent(parent);
				}
						else{
							parent.setLeftChild(left);
							left.setParent(parent);
						}
				}}}
			
			else{
				//traverse right subtree of the node to find the smallest in the right subtree
				BNode small = smallestNode(p.rightChild());
				//replace the data of the node p with the smallest node
				p.setContent(small.getData());
				BNode smallparent = small.parent();
				//the parent points to the right child of the smallest node
				if(smallparent.leftChild().equals(small)){
					smallparent.setLeftChild(small.rightChild());
				}
				else{
					smallparent.setRightChild(small.rightChild());
				}
				small.rightChild().setParent(smallparent);
			}
			
		}
		
	}
	
	//returns the pel object with the smallest key larger than the given key
	//returns null if the key has no predecessor
	public Pel successor(BNode r, Location key) {
		//if the root is the only node in the tree, than the root is the successor therefore there is none
		if (r.isLeaf()) {
			return null; 
		//otherwise if the root has children
		} else {
			//call the helper method to find the node with the given key
			BNode p= getNode(r,key); 
			//now traverse the tree to find its successor
			// if the node is internal and the right child is an internal node then return the smallest node in that subtree
			if (p.isLeaf() == false && p.rightChild().isLeaf() == false) {
				//return the smallest node in the right subtree
				return smallestNode(p.rightChild()).getData(); 
			//otherwise if the predecessor is not in the left subtree
			//travel up to find the successor of the node
			} else {
				BNode parent= p.parent(); 
				//travel up the tree until you find a node larger than the key stored in node p
				while (parent != null && parent.rightChild() == p) {
					p=parent; 
					parent= parent.parent(); 
				} 
				//if the node was found then return the data- which is the key of the successor
				if (parent != null) {
					return parent.getData(); 
				} else {
					//otherwise the node has no successor- return null
					return null; 
				}
			}
		}
	}
	//returns the pel object with the largest key smaller than the given one
	//returns null if the key has no predecessor
	public Pel predecessor (BNode r, Location key) {
		//if the the root has no children return null
		if (r.isLeaf()) {
			return null; 
		//otherwise if the root has children
		} else {
			//call the helper method to find the node with the given key
			BNode p= getNode(r,key); 
			//now traverse the tree to find its predecessor
			// if the node is internal and the left child is an internal node then return the largest node in the left subtree
			if (p.isLeaf() == false && p.rightChild().isLeaf() == false) {
				return largestNode(p.leftChild()).getData(); 
				//otherwise if the predecessor is not in the left subtree
				//travel up until you find the successor
			} else {
				BNode parent= p.parent(); 
				//travel up the tree until you find a node smaller than the key stored in node p
				while (parent != null && parent.leftChild() == p) {
					p=parent; 
					parent= parent.parent(); 
				} 
				//if the node was found then return the data which is the key of the successor
				if (parent != null) {
					return parent.getData(); 
				} else {
					//otherwise the node has no predecessor so null is returned
					return null; 
				}
			}
		}
	}
	//returns the pel object of the smallest key
	public Pel smallest(BNode r) throws EmptyTreeException {
		//if the root has no children then throw an exception as the tree doesn't contain data
		if (r.isLeaf() == true ) {
			throw new EmptyTreeException("tree is empty"); 
		} else {
			//call the helper function to find the node with the smallest key
			//call the get data function to return the pel object of that node
			return smallestNode(r).getData(); 
		}
	}
	//returns the pel object of the largest key
	public Pel largest(BNode r) throws EmptyTreeException {
		//if the root has no children then throw an exception as the tree doesn't contain data
		if (r.isLeaf() == true) {
			throw new EmptyTreeException("tree is empty"); 
		} else {
			//call the helper function to find the node with the largest key
			//call the get data function to return the pel object of that node
			return largestNode(r).getData(); 
		}
 	}
	//returns the root of the BST
	public BNode getRoot() {
		return root; 
	}
 	
 }
