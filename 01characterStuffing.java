import java.util.*;

/**
 * Demonstrates character stuffing and unstuffing for data transmission.
 * Uses FLAG and ESC characters for framing and escaping.
 */
public class CharacterStuffing {

    /**
     * Encodes a message using character stuffing, escaping FLAG and ESC characters.
     * @param message Message to be stuffed
     * @param FLAG Delimiter flag character
     * @param ESC Escape character
     * @return Stuffed message with framing flags
     */
    public static String stuff(String message, char FLAG, char ESC) {
        if (message == null) return String.valueOf(FLAG) + FLAG;
        StringBuilder encoded = new StringBuilder();
        // Add start flag
        encoded.append(FLAG);
        for (char c : message.toCharArray()) {
            if (c == FLAG || c == ESC) {
                encoded.append(ESC); // escape special char
            }
            encoded.append(c);
        }
        // Add end flag
        encoded.append(FLAG);
        return encoded.toString();
    }

    /**
     * Decodes a message by removing character stuffing and framing flags.
     * @param stuffed Stuffed message with flags
     * @param FLAG Delimiter flag character
     * @param ESC Escape character
     * @return Original message after unstuffing
     */
    public static String unstuff(String stuffed, char FLAG, char ESC) {
        if (stuffed == null || stuffed.length() < 2) return "";
        String innerData = stuffed.substring(1, stuffed.length() - 1);
        StringBuilder unstuffed = new StringBuilder();
        boolean escapeNext = false;
        for (char c : innerData.toCharArray()) {
            if (escapeNext) {
                unstuffed.append(c);
                escapeNext = false;
            } else if (c == ESC) {
                escapeNext = true;
            } else {
                unstuffed.append(c);
            }
        }
        return unstuffed.toString();
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private CharacterStuffing() {}

    /**
     * Main method for demonstration and manual testing.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final char FLAG = '~';
        final char ESC = '}';
        System.out.println("Enter message:");
        String message = sc.nextLine();
        String encoded = stuff(message, FLAG, ESC);
        System.out.println("Stuffed message: " + encoded);
        String decoded = unstuff(encoded, FLAG, ESC);
        System.out.println("Unstuffed message: " + decoded);
        sc.close();
    }
}
