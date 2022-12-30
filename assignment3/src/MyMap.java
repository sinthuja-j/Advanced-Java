import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Author: Sinthuja Jeevarajhan
 * Professor Oba
 * Assignment 5: Graphs
 * Purpose: This class represents a road map. Graph used to store the map.
 * Date: Nov 28 2022
 */
public class MyMap {
	private Graph graph;
	private int width, length, start, end, privRoads, constRoads;
	Stack path = new Stack();
	Iterator nodes;

	// Constructor for building a graph from input file from specified parameter
	// Graph represents a roadmap
	// If the input file doesn't exist throws MapException
	MyMap(String inputFile) throws MapException {
		try {
			// create file stream to read the input
			BufferedReader input = new BufferedReader(new FileReader(inputFile));

			Integer.parseInt(input.readLine()); // ignore scale value
			// first line stores the id of the starting node
			start = Integer.parseInt(input.readLine());
			// second line stores the id of the ending node
			end = Integer.parseInt(input.readLine());
			// third line stores the width
			width = Integer.parseInt(input.readLine());
			// fouth line stores the length
			length = Integer.parseInt(input.readLine());
			// fifth line stores the max number of private roads
			privRoads = Integer.parseInt(input.readLine());
			// sixth line stores the max number of construction nodes
			constRoads = Integer.parseInt(input.readLine());
			// create a new graph with the appropriate size
			graph = new Graph(width * length);
			// create graph with nodes

			// String to hold the input
			String line;
			// count to keep track of the nodes
			int count = 0;
			// read until file is empty
			while ((line = input.readLine()) != null) {
				// horizontal edges
				for (int i = 1; i < line.length(); i = i + 2) {
					// if the street is private- add edge that is public
					if (line.charAt(i) == 'V') {
						graph.addEdge(graph.getNode(count), graph.getNode(count++), "public");
					}
					// if the street is private- add edge that is private
					if (line.charAt(i) == 'V') {
						graph.addEdge(graph.getNode(count), graph.getNode(count++), "private");
					}
					// if the street is construction- add edge that is private
					if (line.charAt(i) == 'C') {
						graph.addEdge(graph.getNode(count), graph.getNode(count++), "construction");
					}
					count++;
				}
				// vertical edges
				line = input.readLine();
				// set count equal to width to create vertical edges
				count = width;
				if (line != null) {
					for (int i = 0; i < line.length(); i = i + 2) {
						// if the street is public- add edge that is public
						if (line.charAt(i) == 'P') {
							graph.addEdge(graph.getNode(count), graph.getNode(count++), "public");
						}
						// if the street is private- add edge that is private
						if (line.charAt(i) == 'V') {
							graph.addEdge(graph.getNode(count), graph.getNode(count++), "private");
						}
						// if the street is private- add edge that is counstruction
						if (line.charAt(i) == 'C') {
							graph.addEdge(graph.getNode(count), graph.getNode(count++), "construction");
						}
						count++;
					}
				}

			}

			// close file
			input.close();
		} catch (Exception e) {
			throw new MapException("input file doesn't exist");
		}
	}

	// accessor method- return the graph
	public Graph getGraph() {

		return graph;
	}

	// accessor method- return the starting node
	public int getStartingNode() {
		return start;
	}

	// accessor method- returns ending node
	public int getDestinationNode() {
		return end;
	}

	// accessor method- returns the max number of private roads
	public int maxPrivateRoads() {
		return privRoads;
	}

	// accessor method- returns the max number of construction nodes
	public int maxConstructionRoads() {
		return constRoads;
	}

	// helper method- path(s, d)- we implemented in lectures
	private boolean path(Node s, Node d, int maxPrivate, int maxConstruction) {
		try {
			// node to traverse the incident edge Iterator
			Node cur;
			// private road variable that can be modified
			int privateRoads = maxPrivate;
			// construction road variable that can be modified
			int constructionRoads = maxConstruction;
			// iterator of edges incident on s
			nodes = graph.incidentEdges(s);
			// push s onto the stack
			path.push(s);
			// mark the node
			s.markNode(true);
			// if s has reached the destination- return true
			if (s.equals(d)) {
				return true;
			}
			// traverse the incident edges on s
			while (nodes != null) {
				// first incident edge
				cur = (Node) nodes.next();
				// get the edge between s and the first incident node
				Edge edge = graph.getEdge(s, d);
				// if the edge type is private decrement private
				if (edge.getType() == "private") {
					privateRoads--;
					// if there are no more private roads- exit the loop
					if (privateRoads == 0) {
						privateRoads = maxPrivate;
						break;
					}
				}
				// if the edge type is construction decrement construction
				if (edge.getType() == "construction") {
					constructionRoads--;
					// if there are no more construction roads- exit the loop
					if (constructionRoads == 0) {
						constructionRoads = maxConstruction;
						break;
					}
				}
				// if the node is not marked
				if (cur.getMark() != true) {
					// if one of the incident edges on cur- equals d- return true
					if (path(cur, d, privateRoads, constructionRoads) == true) {
						return true;
					}
				}
				// if there are no incident edges left- break the loop
				if (!nodes.hasNext()) {
					break;
				}
			}
			// remove the traversed incident edge- as it was not the correct path
			path.pop();
			// return false as this was the incorrect path
			return false;
			// if GraphException faced- throw exception and return false
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// method to find a path from the start to destination using private and
	// construction roads
	public Iterator findPath(int start, int destination, int maxPrivate, int maxConstruction) {
		try {
			// get the start and ending nodes
			Node s = graph.getNode(start), d = graph.getNode(destination);

			// if the helper path function returns true- return the iterator of path (global
			// variable) otherwise return null
			if (path(s, d, maxPrivate, maxConstruction) == true) {
				return path.iterator();
			} else {
				return null;
			}
			// if an exception was thrown return null
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
