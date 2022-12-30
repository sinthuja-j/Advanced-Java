
/*
 * Author: Sinthuja Jeevarajhan
 * Professor Oba
 * Assignment 5: Graphs
 * Purpose: This class represents an undirected graph- using an adjacency matrix
 * Date: Nov 28 2022
 */
import java.util.*;

//class implements - GraphADT
public class Graph implements GraphADT {
	// number of nodes
	private int numNodes;
	// adjacency of matrix to store the edges
	private Edge matrix[][];
	// array of nodes to store the nodes
	private Node nodes[];

	// Constructor- creates a graph with n nodes and no edges
	// id of the nodes are 0, 1... , n-1
	public Graph(int n) {
		numNodes = n;
		// initialize the arrays with the number of nodes
		matrix = new Edge[n][n];
		nodes = new Node[n];
		// initialize the node array with a new node object
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}

	}

	// returns the node with the specified id
	// if no id with this exists, method should throw a GraphException
	public Node getNode(int n) throws GraphException {
		// node that isn't between 0 and n-1 is invalid
		if (n < 0 || n >= numNodes)
			throw new GraphException("node does not exist");
		else
			// return the array of nodes
			return nodes[n];
	}

	// adds an edge of given type connecting u and v
	// throws GraphException if node doesn't exist or if graph already contains edge
	// connecting given nodes
	public void addEdge(Node u, Node v, String edgeType) throws GraphException {
		try {
			// check and u and v exist using getNode method
			getNode(u.getId());
			getNode(v.getId());
			// create adjcency matrix of the specified size
			Edge e = matrix[u.getId()][v.getId()];
			// if it is null then initialize each position with a new edge connecting with
			// the node
			if (e == null) {
				matrix[u.getId()][v.getId()] = new Edge(u, v, edgeType);
				matrix[v.getId()][u.getId()] = new Edge(v, u, edgeType);
			} else {
				// throw if the node already exists
				throw new GraphException("node exists already");
			}

		}
		// throw if the node doesn't exist
		catch (GraphException e) {
			throw new GraphException("node does not exist");
		}

	}

	// returns java iterator storing all edge incident on u
	// returns null if u doesn't have any edges incident on it
	public Iterator incidentEdges(Node u) throws GraphException {
		try {
			// check if node exists
			getNode(u.getId());
			// create a new stack to store the edges
			Stack edges = new Stack();
			// store each edge in a stack
			for (int i = 0; i < numNodes; i++) {
				// add the edges to the stack
				if (matrix[u.getId()][i] != null)
					edges.push(matrix[u.getId()][i]);
			}
			// return the iterator to the stack
			return edges.iterator();

		}
		// if the node doesn't exist throw this exception
		catch (GraphException e) {
			throw new GraphException("node does not exist");
		}

	}

	// returns the edge connecting u and v
	// throws GraphException if no edge between u and v
	public Edge getEdge(Node u, Node v) throws GraphException {
		try {
			// check if u and v exist
			getNode(u.getId());
			getNode(v.getId());
			// create adjaency matrix with id of u and v
			Edge edge = matrix[u.getId()][v.getId()];
			// edge exists return it
			if (edge != null) {
				return edge;
			} else {
				// else throw this exception as the edge doesn't exist
				throw new GraphException("edge does not exist");
			}
		} catch (GraphException e) {
			// throw if the node doesn't exist
			throw new GraphException("node does not exist");
		}
	}

	// returns true if nodes u and v are adjcent- returns false otherwise
	public boolean areAdjacent(Node u, Node v) throws GraphException {

		try {
			// check if u and v exist
			getNode(u.getId());
			getNode(v.getId());
			// create an adjacency matrix - with ids of u and v
			Edge edge = matrix[u.getId()][v.getId()];
			// if the edge doesn't exist return false otherwise return true
			if (edge == null)
				return false;
			else
				return true;
		}
		// throw exception if the node doesn't exist
		catch (GraphException e) {
			throw new GraphException("node does not exist");
		}
	}

}
