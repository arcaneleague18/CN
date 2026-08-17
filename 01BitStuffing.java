import java.util.*;

/**
 * Demonstrates bit stuffing and unstuffing for HDLC-like framing schemes.
 * Provides safe input checks and closes resources properly.
 */
public class BitStuffingString {

    /**
     * Performs bit stuffing on a binary string (inserts '0' after five consecutive '1's).
     * @param data Bit string (only '0' and '1')
     * @return Bit-stuffed string (may be empty if data is null or empty)
     */
    public static String bitStuff(String data) {
        if (data == null || data.isEmpty()) return "";
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

    /**
     * Performs bit unstuffing on a bit-stuffed string (removes '0' after five consecutive '1's).
     * @param stuffed Bit-stuffed string (only '0' and '1')
     * @return Original unstuffed bit string (may be empty if stuffed is null or empty)
     */
    public static String bitUnstuff(String stuffed) {
        if (stuffed == null || stuffed.isEmpty()) return "";
        StringBuilder unstuffed = new StringBuilder();
        int count = 0;
        for (int i = 0; i < stuffed.length(); i++) {
            char bit = stuffed.charAt(i);
            unstuffed.append(bit);
            if (bit == '1') {
                count++;
                // If 5 consecutive 1s are found, skip the next bit (stuffed 0)
                if (count == 5) {
                    if (i + 1 < stuffed.length() && stuffed.charAt(i + 1) == '0') {
                        i++; // skip stuffed '0'
                    }
                    count = 0; // reset counter
                }
            } else {
                count = 0;
            }
        }
        return unstuffed.toString();
    }

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private BitStuffingString() {}

    /**
     * Main method for demonstration and manual testing.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String FLAG = "01111110";
        System.out.print("Enter bit stream (only 0s and 1s): ");
        String data = sc.nextLine();
        if (!data.matches("[01]+")) {
            System.out.println("Invalid input. Enter only 0s and 1s.");
            sc.close();
            return;
        }
        // Add framing flags
        String frame = FLAG + data + FLAG;
        System.out.println("\nOriginal Frame: " + frame);
        // Perform bit stuffing
        String stuffedData = bitStuff(data);
        String stuffedFrame = FLAG + stuffedData + FLAG;
        System.out.println("After Bit Stuffing: " + stuffedFrame);
        // Perform bit unstuffing
        String extracted = stuffedData;
        String unstuffed = bitUnstuff(extracted);
        System.out.println("After Bit Unstuffing: " + unstuffed);
        sc.close();
    }
}
