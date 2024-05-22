package hdt10;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph(100); // Cambia el número de nodos según sea necesario

        // Cargar el grafo desde un archivo
        try {
            BufferedReader br = new BufferedReader(new FileReader("guategrafo.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String city1 = parts[0];
                String city2 = parts[1];
                int distance = Integer.parseInt(parts[2]);
                graph.addCity(city1);
                graph.addCity(city2);
                graph.addEdge(city1, city2, distance);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ruta más corta entre dos ciudades");
            System.out.println("2. Ciudad centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Finalizar");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (option == 1) {
                System.out.print("Ciudad origen: ");
                String origin = scanner.nextLine();
                System.out.print("Ciudad destino: ");
                String destination = scanner.nextLine();
                if (graph.cityExists(origin) && graph.cityExists(destination)) {
                    printShortestPath(graph, origin, destination);
                } else {
                    System.out.println("Una o ambas ciudades no existen en el grafo.");
                }
            } else if (option == 2) {
                FloydWarshall fw = new FloydWarshall(graph.getAdjacencyMatrix());
                String centerCity = GraphCenter.findGraphCenter(graph, fw);
                System.out.println("La ciudad centro del grafo es: " + centerCity);
            } else if (option == 3) {
                System.out.println("Seleccione una acción:");
    System.out.println("a) Interrumpir tráfico entre un par de ciudades");
    System.out.println("b) Establecer nueva conexión entre ciudades");
    char action = scanner.next().charAt(0);
    scanner.nextLine();  // Consumir la nueva línea después del carácter

    if (action == 'a') {
        System.out.print("Ingrese el nombre de la ciudad origen: ");
        String city1 = scanner.nextLine();
        System.out.print("Ingrese el nombre de la ciudad destino: ");
        String city2 = scanner.nextLine();
        
        if (graph.cityExists(city1) && graph.cityExists(city2)) {
            graph.removeEdge(city1, city2);
            graph.removeEdge(city2, city1); // Eliminar conexión en ambas direcciones
            System.out.println("Se ha interrumpido el tráfico entre " + city1 + " y " + city2);
        } else {
            System.out.println("Una o ambas ciudades no existen en el grafo.");
        }
    } else if (action == 'b') {
        System.out.print("Ingrese el nombre de la ciudad origen: ");
        String city1 = scanner.nextLine();
        System.out.print("Ingrese el nombre de la ciudad destino: ");
        String city2 = scanner.nextLine();
        System.out.print("Ingrese la distancia entre las ciudades: ");
        int distance = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea después del entero
        
        if (graph.cityExists(city1) && graph.cityExists(city2)) {
            graph.addEdge(city1, city2, distance);
            System.out.println("Se ha establecido una nueva conexión entre " + city1 + " y " + city2 + " con distancia " + distance + " KM");
        } else {
            System.out.println("Una o ambas ciudades no existen en el grafo.");
        }
    } else {
        System.out.println("Opción no válida. Por favor seleccione 'a' o 'b'.");
    }
            } else if (option == 4) {
                break;
            }
        }
        scanner.close();
    }

    public static void printShortestPath(Graph graph, String origin, String destination) {
        int[][] adjMatrix = graph.getAdjacencyMatrix();
        FloydWarshall fw = new FloydWarshall(adjMatrix);
        int startIdx = graph.getCityIndex(origin);
        int endIdx = graph.getCityIndex(destination);
        List<Integer> path = fw.reconstructPath(startIdx, endIdx);

        if (path == null) {
            System.out.println("No existe un camino entre " + origin + " y " + destination);
        } else {
            System.out.print("El camino más corto entre " + origin + " y " + destination + " es: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(graph.getCities().get(path.get(i)));
                if (i != path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            System.out.println("Distancia total: " + fw.getDistances()[startIdx][endIdx]);
        }
    }
}
