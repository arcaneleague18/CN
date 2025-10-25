import java.util.*;

class Main {
    static final int INF = 999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of nodes: ");
        int n = sc.nextInt();
        int[][] cost = new int[n][n];
        int[][] dist = new int[n][n];
        int[][] nextHop = new int[n][n];

        System.out.println("Enter cost matrix (use 999 for no direct link): ");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
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
    }
}
