import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter total number of frames: ");
        int frames = sc.nextInt();
        System.out.print("Enter window size: ");
        int windowSize = sc.nextInt();
        
        int i = 1; // current frame index
        
        while (i <= frames) {
            System.out.println("\nSending frames...");
            
            for (int j = i; j < i + windowSize && j <= frames; j++) {
                System.out.println("Sent frame " + j);
            }
            
            System.out.print("Enter ACK for the last successfully received frame: ");
            int ack = sc.nextInt();
            
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
