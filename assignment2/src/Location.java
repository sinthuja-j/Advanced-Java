/*
 * Assignment 4: Pac Man
 * Author: Sinthuja Jeevarajhan
 * Professor: Oba
 * Purpose: This class represents the location(x,y) of a pel. 
 * Date: Nov 19 2022
 */

public class Location {
	//x and y variables that represents the location of the Pel
	private int x, y; 
	//constructor that creates an object of class location, initializes the object with the specified x and y coords
	public Location(int x, int y) {
		this.x= x; 
		this.y= y; 
	}
	//accessor method to get the x coordinate
	public int getx() {
		return x; 
	}
	//accessor method to get the y coordinate
	public int gety() {
		return y; 
	}
	//compareTo method that sees if the two objects are located at the same coordinate
	public int compareTo(Location p) {
		if (this.gety() > p.gety()) {
			return 1; 
		}
		if (this.gety() == p.gety() && this.getx() > p.getx()) {
			return 1;
		}
		//returns 0 if the two objects are located at the same location
		if (this.gety() == p.gety() && this.getx() == p.getx()) {
			return 0; 
		}
		if (this.gety() < p.gety()) {
			return -1; 
		}
		if (this.gety() == p.gety() && this.getx() < p.getx()) {
			return -1; 
		}
		else return 1; 
	}
}
