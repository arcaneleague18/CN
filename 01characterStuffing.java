import java.util.*;

/**
 * Demonstrates character stuffing and unstuffing for data transmission.
 * Uses FLAG and ESC characters for framing and escaping.
 */
class Main {

    /**
     * Encodes a message using character stuffing, escaping FLAG and ESC characters.
     * @param message Message to be stuffed
     * @param FLAG Delimiter flag character
     * @param ESC Escape character
     * @return Stuffed message with framing flags
     */
    public static String stuff(String message, char FLAG, char ESC) {
        Stack<Character> stack = new Stack<>();

        // Add start flag
        stack.push(FLAG);

        for (char c : message.toCharArray()) {
            if (c == FLAG || c == ESC) {
                stack.push(ESC);  // stuff escape character before special char
            }
            stack.push(c);
        }

        // Add end flag
        stack.push(FLAG);

        // Build encoded string from stack
        StringBuilder encoded = new StringBuilder();
        for (char c : stack) {
            encoded.append(c);
        }

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
        if (stuffed.length() < 2) return "";
        Stack<Character> stack = new Stack<>();

        // Extract only the inner data (ignore FLAGs)
        String innerData = stuffed.substring(1, stuffed.length() - 1);
        boolean escapeNext = false;

        for (char c : innerData.toCharArray()) {
            if (escapeNext) {
                stack.push(c);
                escapeNext = false;
            } else if (c == ESC) {
                escapeNext = true;
            } else {
                stack.push(c);
            }
        }

        // Build unstuffed message from stack
        StringBuilder unstuffed = new StringBuilder();
        for (char c : stack) {
            unstuffed.append(c);
        }

        return unstuffed.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char FLAG = '~';
        char ESC = '}';

        System.out.println("Enter message:");
        String message = sc.nextLine();

        String encoded = stuff(message, FLAG, ESC);
        System.out.println("Stuffed message: " + encoded);

        String decoded = unstuff(encoded, FLAG, ESC);
        System.out.println("Unstuffed message: " + decoded);

        sc.close();
    }
}
