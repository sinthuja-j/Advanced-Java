/*
 * Author: Sinthuja Jeevarajhan
 * Professor Oba
 * Assignment 5: Graphs
 * Purpose: This class represents a node of the graph
 * Date: Nov 28 2022
 */

public class Node {
	// id of a node- int value between 0 and n-1
	private int id;
	// marks node with true or false- used when traversing graph to mark visisted
	// nodes
	private boolean mark;

	// constructor- creates node with given id
	public Node(int id) {
		this.id = id;
	}

	// modifier method- sets mark with the given value
	public void markNode(boolean mark) {
		this.mark = mark;
	}

	// accessor method- returns mark
	public boolean getMark() {
		return mark;
	}

	// accessor method- returns id
	public int getId() {
		return id;
	}
}
