package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filename: Graph.java Project: p4
 * Author: Leo Brunette
 * Net ID: 9080224448
 * Course: CS400 - LEC 002
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

	/*
	 * Default no-argument constructor
	 */
	private ArrayList<GraphNode> graphNodes;

	private class GraphNode {
		private String vertex;
		private ArrayList<String> adjList;

		public GraphNode(String v) {
			vertex = v;
			adjList = new ArrayList<String>(0);
		}

		private String getVertex() {
			return vertex;
		}

		private ArrayList<String> getAdjList() {
			return adjList;
		}

		private void addEdge(String n) {
			adjList.add(n);
		}
	}

	public Graph() {
		graphNodes = new ArrayList<GraphNode>(0);
	}
	public boolean contains(String vertex) {
		for(GraphNode node : graphNodes) {
			
		}
	}
	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 */
	public void addVertex(String vertex) {
		boolean exists = false;
		if (vertex != null) {
			for (int i = 0; i < graphNodes.size(); i++) {
				if (graphNodes.get(i).getVertex().contentEquals(vertex)) {
					exists = true;
				}
			}
			if (!exists) {
				graphNodes.add(new GraphNode(vertex));
			}
		}
	}

	/**
	 * Remove a vertex and all associated edges from the graph.
	 * 
	 * If vertex is null or does not exist, method ends without removing a vertex,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 */
	public void removeVertex(String vertex) {
		if (vertex != null) {
			for (int i = 0; i < graphNodes.size(); i++) {
				if (graphNodes.get(i).getVertex().equals(vertex)) {
					graphNodes.remove(i);
				}
			}
		}
	}

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
	 * unweighted) If either vertex does not exist, add vertex, and add edge, no
	 * exception is thrown. If the edge exists in the graph, no edge is added and no
	 * exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
		boolean vert1 = false;
		for (int n = 0; n < graphNodes.size(); n++) {
			if (graphNodes.get(n).getVertex().equals(vertex1)) {
				vert1 = true;
			}
		}
		if (!vert1) {
			graphNodes.add(new GraphNode(vertex1));
		}
		boolean vert2 = false;
		for (int n = 0; n < graphNodes.size(); n++) {
			if (graphNodes.get(n).getVertex().equals(vertex2)) {
				vert2 = true;
			}
		}
		if (!vert2) {
			graphNodes.add(new GraphNode(vertex2));
		}
		for (int i = 0; i < graphNodes.size(); i++) {
			if (graphNodes.get(i).getVertex().equals(vertex1)) {
				graphNodes.get(i).addEdge(vertex2);
			}
			if (graphNodes.get(i).getVertex().equals(vertex2)) {
				graphNodes.get(i).addEdge(vertex1);
			}
		}
	}

	/**
	 * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed
	 * and unweighted) If either vertex does not exist, or if an edge from vertex1
	 * to vertex2 does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge from vertex1 to vertex2 is in the graph
	 */
	public void removeEdge(String vertex1, String vertex2) {
		if (vertex1 != null && vertex2 != null) {
			for (int i = 0; i < graphNodes.size(); i++) {
				if (graphNodes.get(i).getVertex().equals(vertex1)) {
					for (int n = 0; n < graphNodes.get(i).getAdjList().size(); n++) {
						if (graphNodes.get(i).getAdjList().get(n).equals(vertex2)) {
							graphNodes.get(i).getAdjList().remove(n);
						}
					}
				}
				if (graphNodes.get(i).getVertex().equals(vertex2)) {
					for (int x = 0; x < graphNodes.get(i).getAdjList().size(); x++) {
						if (graphNodes.get(i).getAdjList().get(x).equals(vertex1)) {
							graphNodes.get(i).getAdjList().remove(x);
						}
					}
				}
			}
		}
	}

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 */
	public Set<String> getAllVertices() {
		Set<String> vertices = new HashSet<String>(graphNodes.size());
		for (int i = 0; i < graphNodes.size(); i++) {
			vertices.add(graphNodes.get(i).getVertex());
		}
		return vertices;
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		if (vertex == null) {
			return null;
		}
		for (int i = 0; i < graphNodes.size(); i++) {
			if (graphNodes.get(i).getVertex().equals(vertex)) {
				return (List<String>) graphNodes.get(i).getAdjList();
			}
		}
		return null;
	}

	/**
	 * Returns the number of edges in this graph.
	 */
	public int size() {
		int edges = 0;
		for (int i = 0; i < graphNodes.size(); i++) {
			edges += graphNodes.get(i).getAdjList().size();
		}
		return edges;
	}

	/**
	 * Returns the number of vertices in this graph.
	 */
	public int order() {
		return graphNodes.size();
	}
}
