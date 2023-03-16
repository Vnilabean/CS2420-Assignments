package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Contains several methods for solving problems on generic, directed, unweighted,
 * sparse graphs.
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
     *
     * @param sources      the list of sources
     * @param destinations the list of destinations
     * @param srcData      data from the node in the sources list
     * @param dstData      data from the destinations list
     * @param <Type>       Generic Object
     * @return true or false if there is a connection between the node in the source and destination lists
     * @throws IllegalArgumentException if sizes of sources and destinations are unequal and srcData or dstData are not in
     *                                  their respective lists
     */
    public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) throws IllegalArgumentException {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException();
        }
        boolean contains = false;
        for (int i = 0; i < sources.size(); i++) {
            if (sources.get(i).equals(srcData) || destinations.get(i).equals(srcData)) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new IllegalArgumentException();
        }

        Graph g = new Graph();
        for (int i = 0; i < sources.size(); i++) {
            g.addEdge(sources.get(i), destinations.get(i));
        }
        Stack<Type> stack = new Stack<>();
        return depthFirstSearch(g, stack, g.getVertex(srcData), dstData);
    }


    /**
     * areConnected's depth-first search method
     *
     * @param data    Graph created in the driver method
     * @param current current/starting vertex
     * @param dstData destination data we are looking for
     * @param <Type>  Generic object
     * @return true or false depending on if there is a path from current Vertex to vertex with dstData
     */
    private static <Type> boolean depthFirstSearch(Graph data, Stack stack, Vertex current, Type dstData) {
        if (current.getData().equals(dstData)) {
            return true;
        }
        Iterator i = current.edges();
        while (i.hasNext()) {
            Edge edge = (Edge) i.next();
            if (!edge.getOtherVertex().getVisited()) {
                stack.push(edge.getOtherVertex());
            }
        }
        while (!stack.isEmpty()) {
            current.setVisited(true);
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
     *
     * @param sources      list of sources
     * @param destinations list of destinations
     * @param srcData      data from the source
     * @param dstData      data from the destination
     * @param <Type>       Generic Object
     * @return the list of the first node to the end of the shortest path to the targeted destination
     * @throws IllegalArgumentException
     */
    public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
            throws IllegalArgumentException {
        if (sources.size() != destinations.size() || !areConnected(sources, destinations, srcData, dstData)) {
            throw new IllegalArgumentException();
        }
        Graph g = new Graph();
        for (int i = 0; i < sources.size(); i++) {
            g.addEdge(sources.get(i), destinations.get(i));
        }
        return breadthFirstSearch(sources, destinations, g.getVertex(srcData), dstData);
    }

    /**
     * recursive method for shortest path method
     *
     * @param sources      list of source vertices
     * @param destinations list of destination vertices
     * @param current      vertex to be looked at
     * @param dstData      the targeted vertex. (determine if there is a path to this vertex)
     * @param <Type>       Generic Object
     * @return A List of Type (vertices that make up the shortest path)
     */
    private static <Type> List<Type> breadthFirstSearch(List<Type> sources, List<Type> destinations, Vertex current, Type dstData) {
        Vertex startingV = current;
        ArrayList<Type> data = new ArrayList<>();
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.offer(current);
        while (!q.isEmpty()) {
            current = q.poll();
            Iterator i = current.edges();
            current.setVisited(true);
            if (current.getData().equals(dstData)) {
                Vertex previous = current.getPrev();
                data.add((Type) current.getData());
                while (current != startingV) {
                    current = current.getPrev();
                    data.add((Type) current.getData());
                }
                Collections.reverse(data);
                return data;
            }
            while (i.hasNext()) {
                Edge connectedEdge = (Edge) i.next();
                if (!connectedEdge.getOtherVertex().getVisited()) {
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
     *
     * @param sources      list of source vertices
     * @param destinations list of destination vertices
     * @param <Type>       Generic Object
     * @return A List of Type (returns a topologically sorted list of the given graph vertex connections)
     * @throws IllegalArgumentException
     */
    public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException();
        }
        return topologicalSort(sources, destinations);
    }

    /**
     * topologicalSort for the sort method
     *
     * @param sources      source vertex connected to destination vertex
     * @param destinations destination vertex of source vertex
     * @param <Type>       Generic Object
     * @return returns a topologically sorted list of the given graph vertex connections
     */
    private static <Type> List<Type> topologicalSort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
        Graph g = new Graph();
        for (int i = 0; i < sources.size(); i++)
            g.addEdge(sources.get(i), destinations.get(i));
        Queue<Vertex> q = new LinkedList<>();
        ArrayList<Type> sorted = new ArrayList<>(sources.size());
        ArrayList<Type> endPoints = new ArrayList<>();
        ArrayList<Type> startPoints = new ArrayList<>();
        Vertex current = g.getVertex(sources.get(0));
        q.offer(current);
        while (!q.isEmpty()) {
            current = q.poll();
            Iterator i = current.edges();
			/*
			if current node has been visited, checks if the neighbor nodes
			have also been visited making it a loop and throwing an exception
			*/
            if (current.getVisited()) {
                while (i.hasNext()) {
                    Edge testEdge = (Edge) i.next();
                    if (testEdge.getOtherVertex().getVisited())
                        throw new IllegalArgumentException();
                }
                i = current.edges();
            }
            // if vertex is an end point // no neighbors then add to end of list
            if (!i.hasNext() && !current.getVisited()) {
                endPoints.add((Type) current.getData());
                current.setVisited(true);
            }
            // if vertex is a starting no or no in deg then add to start of list
            if (!current.getVisited() && current.getInDeg() == 0) {
                startPoints.add(0, (Type) current.getData());
                current.setVisited(true);
            }
            // if vertex has incoming deg and has neighbors then add to middle of list
            // here if it passed the last two checks and is also not visited it will add it since I can only be a middle vertex
            if (!current.getVisited()) {
                sorted.add((Type) current.getData());
                current.setVisited(true);
            }
            while (i.hasNext()) {
                Edge connectedEdge = (Edge) i.next();
                q.offer(connectedEdge.getOtherVertex());
            }
        }
        // combines lists
        for (Type i : startPoints)
            sorted.add(0, i);

        for (Type i : endPoints)
            sorted.add(sorted.size(), i);
        return sorted;
    }

    /**
     * Builds "sources" and "destinations" lists according to the edges
     * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
     * data type is String.
     * <p>
     * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
     * --accepts \\-style comments
     * --accepts one edge per line or edges terminated with ;
     * --does not accept attributes in [] (e.g., [label = "a label"])
     *
     * @param filename     - name of the DOT file
     * @param sources      - empty ArrayList, when method returns it is a valid
     *                     "sources" list that can be passed to the public methods in this
     *                     class
     * @param destinations - empty ArrayList, when method returns it is a valid
     *                     "destinations" list that can be passed to the public methods in
     *                     this class
     */
    public static void buildListsFromDot(String filename, ArrayList<String>
            sources, ArrayList<String> destinations) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        scan.useDelimiter(";|\n");
        // Determine if graph is directed (i.e., look for "digraph id {").
        String line = "", edgeOp = "";
        while (scan.hasNext()) {
            line = scan.next();
            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");
            if (line.indexOf("digraph") >= 0) {
                edgeOp = "->";
                line = line.replaceFirst(".*\\{", "");
                break;
            }
        }
        if (edgeOp.equals("")) {
            System.out.println("DOT graph must be directed (i.e., digraph).");
            scan.close();
            System.exit(0);
        }
        // Look for edge operator -> and determine the source and destination
        // vertices for each edge.
        while (scan.hasNext()) {
            String[] substring = line.split(edgeOp);
            for (int i = 0; i < substring.length - 1; i += 2) {
                // remove " and trim whitespace from node string on the left
                String vertex1 = substring[0].replace("\"", "").trim();
                // if string is empty, try again
                if (vertex1.equals(""))
                    continue;
                // do the same for the node string on the right
                String vertex2 = substring[1].replace("\"", "").trim();
                if (vertex2.equals(""))
                    continue;
                // indicate edge between vertex1 and vertex2
                sources.add(vertex1);
                destinations.add(vertex2);
            }
            // do until the "}" has been read
            if (substring[substring.length - 1].indexOf("}") >= 0)
                break;
            line = scan.next();
            // Skip //-style comments.
            line = line.replaceFirst("//.*", "");
        }
        scan.close();
    }
}