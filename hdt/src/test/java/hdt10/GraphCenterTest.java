package hdt10;

import static org.junit.Assert.*;
import org.junit.Test;


public class GraphCenterTest {

    @Test
    public void testFindGraphCenter() {
        Graph graph = new Graph(5);
        graph.addCity("City1");
        graph.addCity("City2");
        graph.addCity("City3");
        graph.addCity("City4");
        graph.addCity("City5");
        
        graph.addEdge("City1", "City2", 5);
        graph.addEdge("City1", "City3", 3);
        graph.addEdge("City1", "City4", 10);
        graph.addEdge("City2", "City4", 1);
        graph.addEdge("City3", "City4", 2);
        
        int[][] distances = {
            {0, 5, 3, 10, Integer.MAX_VALUE},
            {5, 0, Integer.MAX_VALUE, 1, Integer.MAX_VALUE},
            {3, Integer.MAX_VALUE, 0, 2, Integer.MAX_VALUE},
            {10, 1, 2, 0, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        
        FloydWarshall fw = new FloydWarshall(distances);
        
        String center = GraphCenter.findGraphCenter(graph, fw);
        
        assertEquals("City4", center);
    }


}


