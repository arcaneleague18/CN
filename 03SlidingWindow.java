import java.util.*;

/**
 * Demonstrates the Sliding Window Protocol for reliable data transmission.
 * Handles user input for total frames and window size, with safe checks.
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of frames: ");
        int frames;
        try {
            frames = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input (not an integer). Exiting.");
            sc.close();
            return;
        }
        System.out.print("Enter window size: ");
        int windowSize;
        try {
            windowSize = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input (not an integer). Exiting.");
            sc.close();
            return;
        }
        int i = 1; // current frame index
        while (i <= frames) {
            System.out.println("\nSending frames...");
            for (int j = i; j < i + windowSize && j <= frames; j++) {
                System.out.println("Sent frame " + j);
            }
            System.out.print("Enter ACK for the last successfully received frame: ");
            int ack;
            try {
                ack = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input (not an integer). Resending from frame " + i);
                sc.nextLine(); // clear bad input
                continue;
            }
            if (ack >= i && ack < i + windowSize) {
                i = ack + 1;
                System.out.println("Sliding window to frame " + i);
            } else {
                System.out.println("No valid ACK received. Resending from frame " + i);
            }
        }
        System.out.println("\nAll frames sent successfully!");
        sc.close();
    }
}
