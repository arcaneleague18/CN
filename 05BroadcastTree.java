import java.util.*;

/**
 * Demonstrates building a broadcast tree (BFS spanning tree) from a network adjacency matrix.
 * Includes input validation and clear output.
 */
public class BroadcastTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Number of devices in subnet (nodes)
        System.out.print("Enter number of devices (including router): ");
        int n;
        try {
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number of devices. Exiting.");
            sc.close();
            return;
        }
        if (n <= 0) {
            System.out.println("Number of devices must be positive.");
            sc.close();
            return;
        }
        // Adjacency matrix for network connections
        int[][] network = new int[n][n];
        System.out.println("Enter adjacency matrix (1 for link, 0 for no link):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    network[i][j] = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid entry for adjacency. Exiting.");
                    sc.close();
                    return;
                }
            }
        }
        System.out.print("Enter the source node (router index 0 to " + (n-1) + "): ");
        int src;
        try {
            src = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid source node. Exiting.");
            sc.close();
            return;
        }
        if (src < 0 || src >= n) {
            System.out.println("Source node out of range.");
            sc.close();
            return;
        }
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);
        // BFS traversal to form broadcast tree
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < n; v++) {
                if (network[u][v] == 1 && !visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    q.add(v);
                }
            }
        }
        System.out.println("\nBroadcast Tree (child -> parent):");
        for (int i = 0; i < n; i++) {
            if (i != src && parent[i] != -1)
                System.out.println("Device " + i + " <-- " + parent[i]);
        }
        sc.close();
    }
}
