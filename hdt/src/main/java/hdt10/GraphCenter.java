package hdt10;

public class GraphCenter {
    public static String findGraphCenter(Graph graph, FloydWarshall fw) {
        int[][] dist = fw.getDistances();
        List<String> cities = graph.getCities();
        int n = cities.size();
        int minMaxDist = Integer.MAX_VALUE;
        int centerIndex = -1;
        
        for (int i = 0; i < n; i++) {
            int maxDist = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] > maxDist) {
                    maxDist = dist[i][j];
                }
            }
            if (maxDist < minMaxDist) {
                minMaxDist = maxDist;
                centerIndex = i;
            }
        }
        return cities.get(centerIndex);
    }
}

