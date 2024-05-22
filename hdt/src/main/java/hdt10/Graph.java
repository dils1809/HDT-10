package hdt10;

import java.util.*;

public class Graph {
    private Map<String, Integer> cityIndexMap;
    private List<String> cities;
    private int[][] adjacencyMatrix;
    private final int INF = Integer.MAX_VALUE;

    public Graph(int numCities) {
        cities = new ArrayList<>();
        cityIndexMap = new HashMap<>();
        adjacencyMatrix = new int[numCities][numCities];
        for (int i = 0; i < numCities; i++) {
            Arrays.fill(adjacencyMatrix[i], INF);
            adjacencyMatrix[i][i] = 0;
        }
    }

    public void addCity(String city) {
        if (!cityIndexMap.containsKey(city)) {
            cityIndexMap.put(city, cities.size());
            cities.add(city);
        }
    }

    public void addEdge(String city1, String city2, int distance) {
        int idx1 = cityIndexMap.get(city1);
        int idx2 = cityIndexMap.get(city2);
        adjacencyMatrix[idx1][idx2] = distance;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public List<String> getCities() {
        return cities;
    }

    public int getCityIndex(String city) {
        return cityIndexMap.get(city);
    }

    public boolean cityExists(String city) {
        return cityIndexMap.containsKey(city);
    }

    public void removeEdge(String city1, String city2) {
        int idx1 = cityIndexMap.get(city1);
        int idx2 = cityIndexMap.get(city2);
        adjacencyMatrix[idx1][idx2] = INF;
    }
}
