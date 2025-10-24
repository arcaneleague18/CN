import java.util.*;

public class BitStuffingString {

    // Function to perform bit stuffing
    public static String bitStuff(String data) {
        StringBuilder stuffed = new StringBuilder();
        int count = 0;

        for (char bit : data.toCharArray()) {
            stuffed.append(bit);

            if (bit == '1') {
                count++;
                // If 5 consecutive 1s found, insert a 0
                if (count == 5) {
                    stuffed.append('0');
                    count = 0; // reset counter
                }
            } else {
                count = 0; // reset on '0'
            }
        }
        return stuffed.toString();
    }

    // Function to perform bit unstuffing
    public static String bitUnstuff(String stuffed) {
        StringBuilder unstuffed = new StringBuilder();
        int count = 0;

        for (int i = 0; i < stuffed.length(); i++) {
            char bit = stuffed.charAt(i);
            unstuffed.append(bit);

            if (bit == '1') {
                count++;
                // If 5 consecutive 1s are found, skip the next bit (stuffed 0)
                if (count == 5) {
                    i++;        // skip next bit (stuffed '0')
                    count = 0;  // reset counter
                }
            } else {
                count = 0;
            }
        }
        return unstuffed.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // HDLC flag used for framing
        String FLAG = "01111110";

        System.out.print("Enter bit stream (only 0s and 1s): ");
        String data = sc.nextLine();

        // Add framing flags
        String frame = FLAG + data + FLAG;
        System.out.println("\nOriginal Frame: " + frame);

        // Perform bit stuffing
        String stuffedData = bitStuff(data);
        String stuffedFrame = FLAG + stuffedData + FLAG;
        System.out.println("After Bit Stuffing: " + stuffedFrame);

        // Perform bit unstuffing
        String extracted = stuffedData; // removing flag for receiver
        String unstuffed = bitUnstuff(extracted);
        System.out.println("After Bit Unstuffing: " + unstuffed);

        sc.close();
    }
}
