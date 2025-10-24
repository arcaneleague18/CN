import java.util.*;

class Main {

    public static String stuff(String message, char FLAG, char ESC) {
        Stack<Character> stack = new Stack<>();

        // Push start flag
        stack.push(FLAG);

        for (char c : message.toCharArray()) {
            if (c == FLAG || c == ESC) {
                stack.push(ESC);  // stuff escape character
            }
            stack.push(c);
        }

        // Push end flag
        stack.push(FLAG);

        // Build encoded string from stack
        StringBuilder encoded = new StringBuilder();
        for (char c : stack) {
            encoded.append(c);
        }

        return encoded.toString();
    }

    public static String unstuff(String stuffed, char FLAG, char ESC) {
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
