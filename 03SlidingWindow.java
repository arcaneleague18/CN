import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frames = sc.nextInt();
        int windowSize = sc.nextInt();
        
        int i = 1;
        while(i <= frames) {
            System.out.println("Frames sending");
            
            for(int j = i; j < i + windowSize && j <= frames; j++) {
                System.out.println("sent"+ j + "frame");
            }
            System.out.println("Enter ack: ");
            int ack = sc.nextInt();
            
            if(ack >=i){
                i = ack+1;
                System.out.println("Rolling back to frame"+ack);
            } else {
                System.out.println("no ack recieved");
            }
        }
        System.out.println("All frames sent successfully");
    }
}
