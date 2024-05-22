package hdt10;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class FloydWarshallTest {

    @Test
    public void testFloydWarshall() {
        int[][] graph = {
            {0, 5, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        FloydWarshall fw = new FloydWarshall(graph);
        int[][] distances = fw.getDistances();

        assertEquals(0, distances[0][0]);
        assertEquals(5, distances[0][1]);
        assertEquals(8, distances[0][2]);
        assertEquals(9, distances[0][3]);
    }

    @Test
    public void testReconstructPath() {
        int[][] graph = {
            {0, 5, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        FloydWarshall fw = new FloydWarshall(graph);
        List<Integer> path = fw.reconstructPath(0, 3);

        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(0);
        expectedPath.add(2);
        expectedPath.add(3);

        assertEquals(expectedPath, path);
    }

}

