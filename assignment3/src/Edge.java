/*
 * Author: Sinthuja Jeevarajhan
 * Professor Oba
 * Assignment 5: Graphs
 * Purpose: This class represents an edge of a graph
 * Date: Nov 28 2022
 */
public class Edge {
	//endpoints of the edge
	private Node u, v; 
	//type of edge- public, private or construction
	private String type; 
	//constructor- initalizes the values
	public Edge(Node u, Node v, String type) {
		this.u=u; 
		this.v=v; 
		this.type= type; 
	}
	//accessor method - returns the first end point
	public Node firstNode() {
		return u; 
	}
	//accessor method- returns the second end point
	public Node secondNode() {
		return v; 
	}

	// accessor method- returns the type of node
	public String getType() {
		return type; 
	}
}
