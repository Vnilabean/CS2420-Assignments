package assign07;

import assign06.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilityTest {

    @BeforeEach
    void setUp() {
        Graph sample = new Graph();
        sample.addEdge("a", "b");
        sample.addEdge("b", "c");
        sample.addEdge("c", "d");
        sample.addEdge("b", "d");
        sample.addEdge("d", "a");
    }

    @Test
    void areConnected() {
        ArrayList<Integer> source = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        source.add(1);
        source.add(2);
        source.add(5);
        source.add(2);
        source.add(5);
        dest.add(2);
        dest.add(5);
        dest.add(9);
        dest.add(8);
        dest.add(3);
        System.out.println(GraphUtility.areConnected(source, dest, 1, 3));
    }

    @Test
    void areConnectedSmall() {
        ArrayList<Integer> source = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        source.add(2);
        source.add(1);

        dest.add(3);
        dest.add(4);

        System.out.println(GraphUtility.areConnected(source, dest, 1, 2));
    }

    @Test
    void shortestPath() {
        ArrayList<Integer> source = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        source.add(1);
        source.add(1);
        source.add(2);
        source.add(5);
        source.add(2);
        source.add(5);
        dest.add(2);
        dest.add(9);
        dest.add(5);
        dest.add(9);
        dest.add(8);
        dest.add(3);
        System.out.println(GraphUtility.shortestPath(source, dest, 1, 3));
    }

    @Test
    void sort() {
        ArrayList<Integer> source = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        source.add(1);
        source.add(1);
        source.add(2);
        source.add(5);
        source.add(2);
        source.add(5);
        dest.add(2);
        dest.add(9);
        dest.add(5);
        dest.add(9);
        dest.add(8);
        dest.add(3);
        Graph g = new Graph<>();
        for (int i = 0; i < source.size(); i++) {
            g.addEdge(source.get(i), dest.get(i));
        }
//        System.out.println(g.generateDot());
        System.out.println(GraphUtility.sort(source, dest));
        System.out.println(g.generateDot());
    }

    @Test
    void throwsCycle() {
        ArrayList<Integer> source = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        source.add(1);
        source.add(2);
        source.add(2);
        source.add(5);
        source.add(2);
        source.add(5);
        source.add(2);
        source.add(3);
        dest.add(2);
        dest.add(11);
        dest.add(5);
        dest.add(9);
        dest.add(8);
        dest.add(3);
        dest.add(3);
        dest.add(1);
        Graph g = new Graph<>();
        for (int i = 0; i < source.size(); i++) {
            g.addEdge(source.get(i), dest.get(i));
        }
        System.out.println(g.generateDot());
        // should throw since 1>2 and 2>1
        System.out.println(GraphUtility.sort(source, dest));
        try {
            GraphUtility.sort(source, dest);
        } catch (IllegalArgumentException e) {

        }


    }

    @Test
    void throwsSmallCycle() {
        ArrayList<Integer> source = new ArrayList<>();
        ArrayList<Integer> dest = new ArrayList<>();
        source.add(1);

        dest.add(2);

        Graph g = new Graph<>();
        for (int i = 0; i < source.size(); i++) {
            g.addEdge(source.get(i), dest.get(i));
        }
        System.out.println(g.generateDot());
        // should throw since 1>2 and 2>1
        System.out.println(GraphUtility.sort(source, dest));
    }
}