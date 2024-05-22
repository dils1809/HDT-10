import networkx as nx
import numpy as np

def load_graph(filename):
    G = nx.DiGraph()
    with open(filename, 'r') as file:
        for line in file:
            parts = line.strip().split()
            city1, city2, distance = parts[0], parts[1], int(parts[2])
            G.add_edge(city1, city2, weight=distance)
    return G

def floyd_warshall(G):
    return dict(nx.floyd_warshall_predecessor_and_distance(G))

def find_center(G, dist_matrix):
    min_max_dist = float('inf')
    center = None
    for node in G.nodes():
        max_dist = max(dist_matrix[node].values())
        if max_dist < min_max_dist:
            min_max_dist = max_dist
            center = node
    return center

def main():
    G = load_graph('guategrafo.txt')
    pred, dist = floyd_warshall(G)
    print("Centro del grafo:", find_center(G, dist))

if __name__ == "__main__":
    main()
