/*
 * Assignment 4: Pac Man
 * Author: Sinthuja Jeevarajhan
 * Professor: Oba
 * Purpose: Stores the specifications for the object of the game, check if two objects intersect
 * Date: Nov 19 2022
 */
//Implements the ADT structure MyObjectADT
public class MyObject implements MyObjectADT {
	//stores the descriptors of the object declared as private
	private int id, height, width; 
	//type of object- fixed, user, computer, or target
	String type; 
	//stores the Location of the object
	private Location pos; 
	//BST storing all of the nodes in the object (pixels of within where the object is bounded by)
	BinarySearchTree t; 
	//public constructor- initializes the object with its attributes
	public MyObject (int id, int width, int height, String type, Location pos) {
		this.id= id;
		this.height= height; 
		this.type= type;
		this.type= type; 
		this.pos= pos; 
		t= new BinarySearchTree(); 	
	}
	//Modifier method- updates the type of object
	public void setType(String type) {
		this.type= type; 
	}
	//Accessor method- returns the width of the object
	public int getWidth() {
		return width; 
	}
	//Accessor method- returns the height of the object
	public int getHeight() {
		return height; 
	}
	//Accessor method- returns the type of object
	public String getType() {
		return type; 
	}
	//Accessor method- returns the id of the object
	public int getId() {
		return id; 
	}
	//Accessor method- returns the location of the object
	public Location getLocus() {
		return pos; 
	}
	//Modifier method- updates the Location with value
	public void setLocus(Location value) {
		pos= value; 
	} 
	//Inserts the pixel into the search tree asssociated with this MyObject
	//Throws duplicated key exception if an error occurs when inserting the Pel object pix into the tree
	public void addPel(Pel pix) throws DuplicatedKeyException {
		try {
			//calls the put method from the BinarySearchTree class to insert the node into the tree t
			t.put(t.getRoot(), pix);
			//exception is thrown if key exists
		} catch(Exception e) {
			throw new DuplicatedKeyException("key exists"); 
		}
	}
	public boolean intersects (MyObject otherObject)  {
		//find the x and y value for this object
		int x= getLocus().getx(); 
		int y= getLocus().gety(); 
		//find the x and y value for the other object
		int otherX= otherObject.getLocus().getx(); 
		int otherY= otherObject.getLocus().gety(); 
		//initiate a Pel object to store the smallest node from the tree
		Pel smallest= null; 
		
		try {
			//find the smallest node in the tree
			smallest= otherObject.t.smallest(t.getRoot()); 
		//thrown if the tree is empty
		} catch (EmptyTreeException e) {
			e.printStackTrace(); //unable to throw exception as method function in assignment instruction doesn't state to throw EmptyTreeException- printing stack trace if an exception is found
		}
		//check the rectangles intersect
		while (t.successor(t.getRoot(), smallest.getLocus()) != null) {
			x= smallest.getLocus().getx()+ x-otherX; 
			y= smallest.getLocus().gety()+ y-otherY; 
			
			if (otherObject.findPix(new Location(x,y))) {
				return true; 
			}
			smallest= t.successor(t.getRoot(), smallest.getLocus()); 
			return false; 
		}
		
		try {	
			//if this figure coordinates are higher then they don't intersect- return false
			if (pos.gety() > otherObject.getLocus().gety() + otherObject.getHeight()) {
				return false; 
			}
			//if this figure coordinates are lower then they don't interesect- return false
			if (pos.gety() + height < otherObject.getLocus().gety()) {
				return false;
			} else {
				//The y coordinates intersect therefore check x coordinates
				//if x coordinates of this object is greater than the other- return false they don't intersect
				if (pos.getx() > otherObject.getLocus().getx() + otherObject.getWidth()) {
					return false; 
					//if the x coordinates of this object is smaller than the other- return false they don't intersect
				} else if (pos.getx() + width < otherObject.getLocus().getx()) {
					return false; 
				} else {
					//otherwise this means there is an intersection so we need to look at the pixels and scan the tree
					//look through every node in the tree by recursively calling the successor method
					Location largest= t.largest(t.getRoot()).getLocus(); 
					for(Pel e = t.smallest(t.getRoot()); e.getLocus().compareTo(largest) < 0; e = t.successor(t.getRoot(), e.getLocus())) {
						if (otherObject.findPix(findPosition(e, otherObject)) == true) {
							return true; //if the pixels intersect then we return true- there is an intersection
						}
					}
				}
			}
			return false; 
		} catch (EmptyTreeException e)  { //if the tree is empty return false- no intersection
			e.printStackTrace();
			return false; 
		}
		
	}
	//Calls the get method in the Binary Search tree to see if the node exists
	//Created a helper method to make the intersects method easier to read
	private boolean findPix(Location p) {
		Pel node= t.get(t.getRoot(), p); 
		if(node != null) {
			return true; 
		} else {
			return false; 
		}
	}
	//Created a helper method to get the position of the object 
	//Created to simplify the intersects method
	private Location findPosition(Pel p, MyObject otherObject) {
		int x= (p.getLocus().getx()) + (this.getLocus().getx()) - (otherObject.getLocus().getx());
		int y= (p.getLocus().gety()) + (this.getLocus().gety()) - (otherObject.getLocus().gety()); 
		return new Location(x,y);
	}
	
}
