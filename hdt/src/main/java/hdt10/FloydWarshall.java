package hdt10;

public class FloydWarshall {
    private int[][] dist;
    private int[][] next;

    public FloydWarshall(int[][] graph) {
        int n = graph.length;
        dist = new int[n][n];
        next = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MAX_VALUE && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public int[][] getDistances() {
        return dist;
    }

    public int[][] getNext() {
        return next;
    }

    public List<Integer> reconstructPath(int u, int v) {
        if (next[u][v] == -1) return null;
        List<Integer> path = new ArrayList<>();
        while (u != v) {
            path.add(u);
            u = next[u][v];
        }
        path.add(v);
        return path;
    }
}

