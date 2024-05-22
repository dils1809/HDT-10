package hdt10;

import java.util.*;

public class FloydWarshall {
    private int[][] dist;
    private int[][] next;
    private final int INF = Integer.MAX_VALUE;

    public FloydWarshall(int[][] graph) {
        int n = graph.length;
        dist = new int[n][n];
        next = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
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

    public List<Integer> reconstructPath(int start, int end) {
        if (next[start][end] == -1) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        int at = start;
        while (at != end) {
            if (at == -1) {
                return null;
            }
            path.add(at);
            at = next[at][end];
        }
        if (next[at][end] == -1) {
            return null;
        }
        path.add(end);
        return path;
    }
}


