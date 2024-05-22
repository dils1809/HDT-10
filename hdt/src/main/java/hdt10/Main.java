package hdt10;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = loadGraph("guategrafo.txt");
            FloydWarshall fw = new FloydWarshall(graph.getAdjacencyMatrix());
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Ruta más corta entre dos ciudades");
                System.out.println("2. Ciudad centro del grafo");
                System.out.println("3. Modificar grafo");
                System.out.println("4. Finalizar");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Ciudad origen: ");
                        String city1 = scanner.nextLine();
                        System.out.print("Ciudad destino: ");
                        String city2 = scanner.nextLine();
                        printShortestPath(graph, fw, city1, city2);
                        break;
                    case 2:
                        System.out.println("Centro del grafo: " + GraphCenter.findGraphCenter(graph, fw));
                        break;
                    case 3:
                        modifyGraph(graph, fw, scanner);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Graph loadGraph(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        
        Set<String> citiesSet = new HashSet<>();
        for (String l : lines) {
            String[] parts = l.split(" ");
            citiesSet.add(parts[0]);
            citiesSet.add(parts[1]);
        }

        Graph graph = new Graph(citiesSet.size());
        for (String city : citiesSet) {
            graph.addCity(city);
        }

        for (String l : lines) {
            String[] parts = l.split(" ");
            graph.addEdge(parts[0], parts[1], Integer.parseInt(parts[2]));
        }

        return graph;
    }

    private static void printShortestPath(Graph graph, FloydWarshall fw, String city1, String city2) {
        int u = graph.getCityIndex(city1);
        int v = graph.getCityIndex(city2);
        List<Integer> path = fw.reconstructPath(u, v);
        if (path == null) {
            System.out.println("No hay camino entre " + city1 + " y " + city2);
        } else {
            System.out.print("Ruta más corta: ");
            for (int i : path) {
                System.out.print(graph.getCities().get(i) + " ");
            }
            System.out.println("\nDistancia: " + fw.getDistances()[u][v]);
        }
    }

    private static void modifyGraph(Graph graph, FloydWarshall fw, Scanner scanner) {
        System.out.println("Seleccione modificación:");
        System.out.println("a) Interrupción de tráfico entre dos ciudades");
        System.out.println("b) Establecer conexión entre dos ciudades con distancia");

        String option = scanner.nextLine();
        if (option.equals("a")) {
            System.out.print("Ciudad origen: ");
            String city1 = scanner.nextLine();
            System.out.print("Ciudad destino: ");
            String city2 = scanner.nextLine();
            graph.removeEdge(city1, city2);
        } else if (option.equals("b")) {
            System.out.print("Ciudad origen: ");
            String city1 = scanner.nextLine();
            System.out.print("Ciudad destino: ");
            String city2 = scanner.nextLine();
            System.out.print("Distancia: ");
            int distance = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            graph.addEdge(city1, city2, distance);
        } else {
            System.out.println("Opción no válida.");
            return;
        }
        fw = new FloydWarshall(graph.getAdjacencyMatrix());
    }
}
