import java.util.*;

/**
 * Simulates the Leaky Bucket algorithm for rate control.
 * Handles safe integer input and reports dropped/bucketed packets.
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bucket size: ");
        int bucketSize;
        try {
            bucketSize = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid bucket size. Exiting.");
            sc.close();
            return;
        }
        System.out.print("Enter outgoing rate: ");
        int outRate;
        try {
            outRate = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid outgoing rate. Exiting.");
            sc.close();
            return;
        }
        System.out.print("Enter number of packets: ");
        int n;
        try {
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number of packets. Exiting.");
            sc.close();
            return;
        }
        int[] packets = new int[n];
        System.out.println("Enter packet sizes: ");
        for (int i = 0; i < n; i++) {
            try {
                packets[i] = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid packet size. Exiting.");
                sc.close();
                return;
            }
        }
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
        sc.close();
    }
}
