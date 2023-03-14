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
        System.out.println(GraphUtility.areConnected(source,dest,1,3));
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
        dest.add(3);
        dest.add(5);
        dest.add(9);
        dest.add(8);
        dest.add(3);
        System.out.println(GraphUtility.shortestPath(source,dest,1,3));
    }

    @Test
    void sort() {
    }
}