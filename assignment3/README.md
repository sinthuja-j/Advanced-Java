🗺️ Roadmap Navigation System

🚀 Overview

This project implements a roadmap navigation system that finds a valid path between two locations while adhering to road restrictions. The roadmap consists of three types of roads:
🔹 Public Roads 🛣️ – Always accessible.
🔹 Private Roads 🚧 – Require a fee; limited usage allowed.
🔹 Construction Roads 🚜 – Under repair; slow traffic; limited usage allowed.

The goal is to find a valid path from the starting location 📍 to the destination 🎯 while respecting the maximum allowed number of private and construction roads in the path.

🎯 Key Features

✔️ Graph Representation: The roadmap is stored as an undirected graph, where:

Nodes 🏙️ represent intersections or dead ends.
Edges 🛤️ represent roads between locations.
✔️ Pathfinding Algorithm: A modified Depth-First Search (DFS) is used to find a valid path while ensuring:

The number of private roads used does not exceed the allowed limit.
The number of construction roads used does not exceed the allowed limit.
✔️ Custom Input Handling: Reads a roadmap file 📄 containing:

Grid-based roadmap layout with different road types.
Start & End nodes.
Allowed limits for private and construction roads.
✔️ Graph Traversal: Efficient adjacency list/matrix representation for optimized searching.

🏗 Classes Implemented

🌟 1. Node (Represents a location in the graph)

✅ Methods:

Node(int id): Creates a node with a unique ID.
markNode(boolean mark): Marks the node as visited or unvisited during traversal.
boolean getMark(): Returns true if the node is marked.
int getId(): Returns the node ID.

🔗 2. Edge (Represents a road)

✅ Methods:

Edge(Node u, Node v, String type): Connects two nodes with a road type.
Node firstNode() / secondNode(): Returns the endpoints of the road.
String getType(): Returns the road type (public, private, or construction).
🌐 3. Graph (Represents the roadmap as a graph)

✅ Methods (as per GraphADT interface):

Graph(int n): Creates a graph with n nodes.
Node getNode(int id): Retrieves a node by its ID.
addEdge(Node u, Node v, String edgeType): Adds a road between two nodes.
Iterator incidentEdges(Node u): Returns all connected roads for a node.
Edge getEdge(Node u, Node v): Retrieves the road between two nodes.
boolean areAdjacent(Node u, Node v): Checks if two nodes are directly connected.

🏙️ 4. MyMap (Handles input parsing & pathfinding)

✅ Methods:

MyMap(String inputFile): Reads the roadmap file and constructs the graph.
Graph getGraph(): Returns the roadmap graph.
int getStartingNode() / getDestinationNode(): Returns the start/destination nodes.
int maxPrivateRoads() / maxConstructionRoads(): Returns the road restrictions.
Iterator findPath(int start, int destination, int maxPrivate, int maxConstruction):
Uses DFS to find a valid path considering the road constraints.

📂 Technologies Used

🔹 Java ☕ – Core language for logic & graph structures.
🔹 Graph Algorithms 🌐 – Used for efficient traversal.
🔹 Adjacency List/Matrix 🔗 – Optimized graph representation.
🔹 Java Iterators 🔄 – For dynamic path retrieval.
