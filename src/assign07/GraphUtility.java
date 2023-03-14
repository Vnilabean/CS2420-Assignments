package assign07;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Contains several methods for solving problems on generic, directed, unweighted,
 sparse graphs.
 *
 * @author Erin Parker & Philippe Gonzalez & Conner Francis
 * @version March 3, 2022
 */
public class GraphUtility {

	/**
	 * This method must use the recursive depth-first search algorithm presented in lecture to determine whether
	 * there is a path from the vertex with srcData to the vertex with dstData in the graph.
	 * Throws an IllegalArgumentException if there does not exist a vertex in the graph with srcData,
	 * and likewise for dstData.
	 * @param sources the list of sources
	 * @param destinations the list of destinations
	 * @param srcData data from the node in the sources list
	 * @param dstData data from the destinations list
	 * @return true or false if there is a connection between the node in the source and destination lists
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) throws IllegalArgumentException {
		if(sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}
		Graph g = new Graph();
		for(int i = 0; i < sources.size(); i++) {
			g.addEdge(sources.get(i), destinations.get(i));
		}
		Stack<Type> stack = new Stack<>();
		return depthFirstSearch(g, stack, g.getVertex(srcData), dstData);
	}

	/**
	 * areConnected's depth-first search method
	 * @param data
	 * @param current
	 * @param dstData
	 * @return
	 * @param <Type>
	 */
	private static <Type> boolean depthFirstSearch(Graph data, Stack stack, Vertex current, Type dstData){
		if(current.getData().equals(dstData)){
			return true;
		}
		Iterator i = current.edges();
		while (i.hasNext()) {
			Edge edge = (Edge) i.next();
			if(!edge.getOtherVertex().getVisited()) {
				stack.push(edge.getOtherVertex());
			}

		}
		while (!stack.isEmpty()) {
			return depthFirstSearch(data, stack, (Vertex) stack.pop(), dstData);
		}
		return false;
	}

	/**
	 * This method must use the breadth-first search algorithm presented in the lecture to find the
	 * shortest path from the vertex with srcData to the vertex with dstData in the graph.
	 * Throws an IllegalArgumentException if there does not exist a vertex in the graph with srcData,
	 * and likewise for dstData.  Also, throws an IllegalArgumentException if there does not exist a
	 * path between the two vertices.
	 * @param sources list of sources
	 * @param destinations list of destinations
	 * @param srcData data from the source
	 * @param dstData data from the destination
	 * @return the list of the first node to the end of the shortest path to the targeted destination
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		if(sources.size() != destinations.size() || !areConnected(sources, destinations, srcData, dstData)) {
			throw new IllegalArgumentException();
		}
		Graph g = new Graph();
		for(int i = 0; i < sources.size(); i++) {
			g.addEdge(sources.get(i), destinations.get(i));
		}
		return breadthFirstSearch(sources, destinations, g.getVertex(srcData) , dstData);
	}

	/**
	 * recursive method for shortest path method
	 * @param sources
	 * @param destinations
	 * @param current
	 * @param dstData
	 * @return
	 * @param <Type>
	 */
	private static <Type> List<Type> breadthFirstSearch(List<Type> sources, List<Type> destinations, Vertex current, Type dstData){
		Vertex startingV = current;
		ArrayList<Type> data = new ArrayList<>();
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.offer(current);

		while (!q.isEmpty()) {
			current = q.poll();
			Iterator i = current.edges();
			current.setVisited(true);
			if(current.getData().equals(dstData)) {
				Vertex previous = current.getPrev();
				data.add((Type) current.getData());
				while(current != startingV) {
					current = current.getPrev();
					data.add((Type) current.getData());
				}
				Collections.reverse(data);
				return data;
			}
			while (i.hasNext()) {
				Edge connectedEdge = (Edge) i.next();
				if(!connectedEdge.getOtherVertex().getVisited()) {
					q.offer(connectedEdge.getOtherVertex());
					connectedEdge.getOtherVertex().setVisited(true);
					connectedEdge.getOtherVertex().setPrev(current);
				}
			}
		}
		return data;
	}

	/**
	 * This method must use the topological sort algorithm presented in lecture to generate a sorted
	 * ordering of the vertices in the graph. Note that a graph may have more than one valid ordering,
	 * and any such ordering is accepted. Throws an IllegalArgumentException if the graph contains a cycle
	 * (recall topological sort works only on acyclic graphs).
	 * @param sources
	 * @param destinations
	 * @return
	 * @param <Type>
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		if(sources.size() != destinations.size()) {
			throw new IllegalArgumentException();
		}
		return null;
	}

	/**
	 * topologicalSort for the sort method
	 * @param sources
	 * @param destinations
	 * @return
	 * @param <Type>
	 */
	private static <Type> List<Type> topologicalSort(List<Type> sources, List<Type> destinations){
		return null;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 *
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments
	 * --accepts one edge per line or edges terminated with ;
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 *
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 * "sources" list that can be passed to the public methods in this
	 * class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 * "destinations" list that can be passed to the public methods in
	 * this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String>
			sources, ArrayList<String> destinations) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		scan.useDelimiter(";|\n");
		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();
			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);
		}
		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);
			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;
				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;
				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}
			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;
			line = scan.next();
			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}
		scan.close();
	}
}