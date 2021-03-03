package bfs;

import org.junit.Test;
import org.junit.Assert;



public class GraphCreationTest extends Assert {

    @Test
    public void testAddEdgeToGraph() {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        boolean expected = graph.getEdges(0).contains(1);
        assertTrue(expected);
    }

}
