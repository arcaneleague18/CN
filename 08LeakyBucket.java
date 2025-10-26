import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter bucket size: ");
        int bucketSize = sc.nextInt();
        System.out.print("Enter outgoing rate: ");
        int outRate = sc.nextInt();
        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();

        int[] packets = new int[n];
        System.out.println("Enter packet sizes: ");
        for (int i = 0; i < n; i++) packets[i] = sc.nextInt();

        int remaining = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\nPacket " + (i+1) + " of size " + packets[i]);
            if (packets[i] + remaining > bucketSize) {
                System.out.println("Packet dropped!");
            } else {
                remaining += packets[i];
                System.out.println("Bucket filled: " + remaining + "/" + bucketSize);
            }

            remaining -= outRate;
            if (remaining < 0) remaining = 0;
            System.out.println("After outgoing, remaining: " + remaining);
        }
    }
}
