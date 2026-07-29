import java.util.*;

/**
 * Demonstrates Distance Vector Routing Algorithm for computing shortest paths in a network.
 * Handles user input and validates integer entries.
 */
public class DistanceVectorRouting {
    static final int INF = 999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of nodes: ");
        int n;
        try {
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number of nodes. Exiting.");
            sc.close();
            return;
        }
        if (n <= 0) {
            System.out.println("Number of nodes must be positive.");
            sc.close();
            return;
        }
        int[][] cost = new int[n][n];
        int[][] dist = new int[n][n];
        int[][] nextHop = new int[n][n];
        System.out.println("Enter cost matrix (use 999 for no direct link): ");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                try {
                    cost[i][j] = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid cost entry. Exiting.");
                    sc.close();
                    return;
                }
                dist[i][j] = cost[i][j];
                nextHop[i][j] = j;
            }
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    for (int k = 0; k < n; k++)
                        if (dist[i][j] > cost[i][k] + dist[k][j]) {
                            dist[i][j] = cost[i][k] + dist[k][j];
                            nextHop[i][j] = k;
                            changed = true;
                        }
        } while (changed);
        System.out.println("\nRouting Tables:");
        for (int i = 0; i < n; i++) {
            System.out.println("For router " + i + ":");
            System.out.println("Dest\tNextHop\tDist");
            for (int j = 0; j < n; j++)
                System.out.println(j + "\t" + nextHop[i][j] + "\t" + dist[i][j]);
            System.out.println();
        }
        sc.close();
    }
}
