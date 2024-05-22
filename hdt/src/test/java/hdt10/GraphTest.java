package hdt10;

import static org.junit.Assert.*;
import org.junit.Test;

public class GraphTest {

    @Test
    public void testAddCity() {
        Graph graph = new Graph(5);
        graph.addCity("City1");
        graph.addCity("City2");
        assertTrue(graph.cityExists("City1"));
        assertTrue(graph.cityExists("City2"));
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph(5);
        graph.addCity("City1");
        graph.addCity("City2");
        graph.addEdge("City1", "City2", 10);
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        assertEquals(10, adjacencyMatrix[graph.getCityIndex("City1")][graph.getCityIndex("City2")]);
    }

    @Test
    public void testRemoveEdge() {
        Graph graph = new Graph(5);
        graph.addCity("City1");
        graph.addCity("City2");
        graph.addEdge("City1", "City2", 10);
        graph.removeEdge("City1", "City2");
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        assertEquals(Integer.MAX_VALUE, adjacencyMatrix[graph.getCityIndex("City1")][graph.getCityIndex("City2")]);
    }

}



