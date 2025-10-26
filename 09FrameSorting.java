import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int n = sc.nextInt();
        
        int[] frames = new int[n];
        System.out.println("Enter frame numbers (in random order): ");
        for (int i = 0; i < n; i++) {
            frames[i] = sc.nextInt();
        }

        Arrays.sort(frames); // sorting frames before delivery

        System.out.println("\nFrames after sorting (ready for delivery):");
        for (int f : frames) {
            System.out.println("Frame " + f + " delivered");
        }
    }
}
