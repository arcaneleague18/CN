import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Number of devices in subnet (nodes)
        System.out.print("Enter number of devices (including router): ");
        int n = sc.nextInt();
        
        // Adjacency matrix for network connections
        int[][] network = new int[n][n];
        System.out.println("Enter adjacency matrix (1 for link, 0 for no link):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                network[i][j] = sc.nextInt();
            }
        }
        
        System.out.print("Enter the source node (router index 0 to " + (n-1) + "): ");
        int src = sc.nextInt();
        
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
    }
}
