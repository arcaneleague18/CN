import java.util.*;

/**
 * Demonstrates sorting of frames before delivery (out-of-order reception scenario).
 * Includes safe input handling.
 */
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int n;
        try {
            n = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number of frames. Exiting.");
            sc.close();
            return;
        }
        int[] frames = new int[n];
        System.out.println("Enter frame numbers (in random order): ");
        for (int i = 0; i < n; i++) {
            try {
                frames[i] = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid frame number. Exiting.");
                sc.close();
                return;
            }
        }
        Arrays.sort(frames); // sorting frames before delivery
        System.out.println("\nFrames after sorting (ready for delivery):");
        for (int f : frames) {
            System.out.println("Frame " + f + " delivered");
        }
        sc.close();
    }
}
