package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Vertex<T> {

	// used to id the Vertex
	private T data;
	private boolean isVisited = false;
	private Vertex prev;

	// adjacency list
	private LinkedList<Edge> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(T name) {
		this.data = name;
		this.adj = new LinkedList<Edge>();
	}

	public Vertex getPrev(){
		return prev;
	}

	public void setPrev(Vertex p) {
		prev = p;
	}

	public boolean getVisited(){
		return this.isVisited;
	}

	public void setVisited(boolean state){
		isVisited = state;
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public T getData() {
		return data;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex otherVertex) {
		adj.add(new Edge(otherVertex));
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge> edges() {
		return adj.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + data + " adjacent to vertices ";
		Iterator<Edge> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
}