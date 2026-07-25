import java.util.*;

/**
 * Dijkstra's Algorithm implementation for shortest path in a weighted graph
 * represented by an adjacency matrix. Handles user input and prints shortest
 * distances from a specified source vertex.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V;
        try {
            V = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number of vertices. Exiting.");
            sc.close();
            return;
        }
        if (V <= 0) {
            System.out.println("Number of vertices must be positive.");
            sc.close();
            return;
        }
        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix (0 if no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                try {
                    graph[i][j] = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for edge weight. Exiting.");
                    sc.close();
                    return;
                }
            }
        }
        System.out.print("Enter source vertex: ");
        int src;
        try {
            src = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid source vertex. Exiting.");
            sc.close();
            return;
        }
        if (src < 0 || src >= V) {
            System.out.println("Source vertex out of range.");
            sc.close();
            return;
        }
        int[] dist = new int[V];       // shortest distances
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        // Main Dijkstra's algorithm loop
        for (int count = 0; count < V; count++) {
            // Find the unvisited vertex with minimum distance
            int minDist = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < V; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            if (u == -1) break; // All reachable vertices visited
            visited[u] = true;
            // Update distances of neighbors
            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        // Print shortest distances
        System.out.println("\nVertex \tDistance from Source " + src);
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
        sc.close();
    }
}
