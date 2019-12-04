package application;
import java.util.List;
import java.util.Set;
//import GraphADT;
import java.util.ArrayList;
import java.util.HashSet;
//import java.util.LinkedList;
/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	
	private class Graphnode{
	  String data;
	 List<Graphnode> neighbors = new ArrayList<Graphnode>();
	 
	}
	
	int size;
	int order;
	List<Graphnode> vertexList = new ArrayList<Graphnode>();
	/*
	 * Default no-argument constructor
	 */ 
	public Graph() {
		this.size = 0;//total number of edges in graph
		this.order = 0;//total number of vertices
		
	}

	/**
     * Add new vertex to the graph.
     *
     * If vertex is null or already exists,
     * method ends without adding a vertex or 
     * throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void addVertex(String vertex) {
		if (vertex == null) {
		  return;
		}
		
		else if (vertexList.size() == 0) {
		  Graphnode newNode = new Graphnode();
		  newNode.data = vertex;
		  vertexList.add(newNode);
		  order++;
		  
		}
		else {
		  for (int i = 0; i < vertexList.size(); i++) {
		    if (vertex.equals(vertexList.get(i).data)) {
		      return;
		    }
		  }
		Graphnode newNode = new Graphnode();
        newNode.data = vertex;
        vertexList.add(newNode);
        order++; 
		}
		
        return;
	}

	/**
     * Remove a vertex and all associated 
     * edges from the graph.
     * 
     * If vertex is null or does not exist,
     * method ends without removing a vertex, edges, 
     * or throwing an exception.
     * 
     * Valid argument conditions:
     * 1. vertex is non-null
     * 2. vertex is not already in the graph 
     */
	public void removeVertex(String vertex) {
	  if (findNode(vertex) == null) {
	    return;
	  }
	   Graphnode node = findNode(vertex);
	   int edges = node.neighbors.size();
	   node.neighbors = null;
	   vertexList.remove(node);
	   order--;
	   size = size - edges;
	}

	/**
     * Add the edge from vertex1 to vertex2
     * to this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * add vertex, and add edge, no exception is thrown.
     * If the edge exists in the graph,
     * no edge is added and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
	 
	  
	  if (vertex1.equals(vertex2)) {
	    return;
	  }
	  if (findNode(vertex1) == null && findNode(vertex2) == null) {
	  addVertex(vertex1);
	  addVertex(vertex2);
	  findNode(vertex1).neighbors.add(findNode(vertex2));
	  
	}
	else if (findNode(vertex1) == null) {
	  addVertex(vertex1);
	  findNode(vertex1).neighbors.add(findNode(vertex2)); 
	}
	else if (findNode(vertex2) == null) {
	  addVertex(vertex2);
	  findNode(vertex1).neighbors.add(findNode(vertex2)); 
	}
	else {
	  Graphnode node1 = findNode(vertex1);
	  Graphnode node2 = findNode(vertex2);
	  for (int i = 0; i < node1.neighbors.size(); i++) {
	    if (findNode(vertex1).neighbors.get(i).data.equals(vertex2))
	      return;
	  }
	  node1.neighbors.add(node2);
	  
	  
	}
	size++;
	
	return;
	  
	}
	
	private Graphnode findNode(String vertex) {
	  if (vertex == null) {
	    return null;
	  }
	  for (int i = 0; i < vertexList.size(); i++) {
	    if (vertexList.get(i).data.equals(vertex)) {
	      return vertexList.get(i);
	    }
	  }
	  return null;
	}
	
	/**
     * Remove the edge from vertex1 to vertex2
     * from this graph.  (edge is directed and unweighted)
     * If either vertex does not exist,
     * or if an edge from vertex1 to vertex2 does not exist,
     * no edge is removed and no exception is thrown.
     * 
     * Valid argument conditions:
     * 1. neither vertex is null
     * 2. both vertices are in the graph 
     * 3. the edge from vertex1 to vertex2 is in the graph
     */
	public void removeEdge(String vertex1, String vertex2) {
	  if(findNode(vertex1) != null && findNode(vertex2) != null && adjTo(vertex1, vertex2)) {
	    findNode(vertex1).neighbors.remove(findNode(vertex2));
	    size--;
	  }
	  return;
	}	
	
	private boolean adjTo(String vertex1, String vertex2) {
	  Graphnode node1 = findNode(vertex1);
	  
	  for (int i = 0; i < node1.neighbors.size(); i++) {
	    if (node1.neighbors.get(i).data.equals(vertex2)) {
	      return true;
	    }
	  }
	  return false;
	}
	/**
     * Returns a Set that contains all the vertices
     * 
	 */
	public Set<String> getAllVertices() {
	  Set<String> vertexSet = new HashSet<String>(); 
	  for (int i = 0; i < vertexList.size() ; i++) {
	   vertexSet.add(vertexList.get(i).data);
	  }
	  
	  return vertexSet;
	}

	/**
     * Get all the neighbor (adjacent) vertices of a vertex
     *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
	  List<String> adjVertices = new ArrayList<String>();
	  Graphnode vNode = findNode(vertex);
	  for (int i = 0; i <vNode.neighbors.size(); i++) {
	      adjVertices.add(vNode.neighbors.get(i).data);
	  }
	  return adjVertices;
	}
	
	/**
     * Returns the number of edges in this graph.
     */
    public int size() {
        return size;
    }

	/**
     * Returns the number of vertices in this graph.
     */
	public int order() {
        return order;
    }
}
