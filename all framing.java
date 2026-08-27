import java.util.*;

/**
 * Demonstrates three framing methods: Character Count, Character Stuffing,
 * and Bit Stuffing, with corresponding unframing/unstuffing routines.
 * Includes input validation and improved comments for maintainability.
 *
 * Utility class: prevents instantiation.
 */
public class FramingDemo {
    /**
     * Character Count Framing: Prefixes each frame with its length.
     * @param frames Array of data frames
     * @return Array of framed data
     */
    static String[] characterCount(String[] frames) {
        System.out.println("\nCharacter Count Framing:");
        String[] framed = new String[frames.length];
        for (int i = 0; i < frames.length; i++) {
            framed[i] = (frames[i].length() + 1) + frames[i];
            System.out.println(framed[i]);
        }
        return framed;
    }
    /**
     * Unframes data using Character Count method.
     * @param framedData Array of framed data
     */
    static void characterCountUnstuff(String[] framedData) {
        System.out.println("\nCharacter Count Unframing:");
        for (String data : framedData) {
            int len = Character.getNumericValue(data.charAt(0)) - 1;
            if (len < 0 || len > data.length() - 1) {
                System.out.println("Invalid frame: " + data);
                continue;
            }
            String unstuffed = data.substring(1, 1 + len);
            System.out.println(unstuffed);
        }
    }
    /**
     * Character Stuffing: Escapes 'DLE' in data and adds framing start/end markers.
     * @param frames Array of data frames
     * @return Array of character-stuffed frames
     */
    static String[] characterStuffing(String[] frames) {
        System.out.println("\nCharacter Stuffing Framing:");
        String[] framed = new String[frames.length];
        String start = "DLESTX", end = "DLEETX";
        for (int i = 0; i < frames.length; i++) {
            String stuffed = frames[i].replace("DLE", "DLEDLE");
            framed[i] = start + stuffed + end;
            System.out.println(framed[i]);
        }
        return framed;
    }
    /**
     * Unframes Character Stuffed data.
     * @param stuffedFrames Array of character-stuffed frames
     */
    static void characterUnstuffing(String[] stuffedFrames) {
        System.out.println("\nCharacter Stuffing Unframing:");
        for (String frame : stuffedFrames) {
            String data = frame.replace("DLESTX", "")
                               .replace("DLEETX", "")
                               .replace("DLEDLE", "DLE");
            System.out.println(data);
        }
    }
    /**
     * Bit Stuffing: Inserts '0' after five consecutive '1's in the bit stream.
     * @param frames Array of data frames
     * @return Array of bit-stuffed (framed) strings
     */
    static String[] bitStuffing(String[] frames) {
        System.out.println("\nBit Stuffing Framing:");
        String flag = "01111110";
        String[] framed = new String[frames.length];
        for (int i = 0; i < frames.length; i++) {
            StringBuilder bits = new StringBuilder();
            for (char c : frames[i].toCharArray())
                bits.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
            StringBuilder stuffed = new StringBuilder();
            int count = 0;
            for (char b : bits.toString().toCharArray()) {
                stuffed.append(b);
                if (b == '1') {
                    count++;
                    if (count == 5) {
                        stuffed.append('0'); // Stuff a 0 after five 1s
                        count = 0;
                    }
                } else count = 0;
            }
            framed[i] = flag + stuffed + flag;
            System.out.println(framed[i]);
        }
        return framed;
    }
    /**
     * Removes bit stuffing and framing flags, recovers original text.
     * @param stuffedFrames Array of bit-stuffed frames
     */
    static void bitUnstuffing(String[] stuffedFrames) {
        System.out.println("\nBit Unstuffing:");
        String flag = "01111110";
        for (String frame : stuffedFrames) {
            // Remove flags
            String bits = frame.replace(flag, "");
            // Remove stuffed 0s
            StringBuilder unstuffed = new StringBuilder();
            int count = 0;
            for (int i = 0; i < bits.length(); i++) {
                char b = bits.charAt(i);
                unstuffed.append(b);
                if (b == '1') {
                    count++;
                    if (count == 5 && i + 1 < bits.length() && bits.charAt(i + 1) == '0') {
                        i++; // Skip the stuffed zero
                        count = 0;
                    }
                } else count = 0;
            }
            // Convert binary to text (8 bits per char)
            StringBuilder recovered = new StringBuilder();
            for (int i = 0; i + 7 < unstuffed.length(); i += 8) {
                String byteStr = unstuffed.substring(i, i + 8);
                try {
                    char c = (char) Integer.parseInt(byteStr, 2);
                    recovered.append(c);
                } catch (NumberFormatException e) {
                    // Ignore invalid conversions
                }
            }
            System.out.println("Recovered Text: " + recovered);
        }
    }
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private FramingDemo() {}

    /**
     * Main method to demonstrate all three framing methods with user input.
     */
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
        sc.nextLine(); // consume newline
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter frame " + (i + 1) + ": ");
            data[i] = sc.nextLine();
        }
        // Perform framing
        String[] cc = characterCount(data);
        String[] cs = characterStuffing(data);
        String[] bs = bitStuffing(data);
        // Perform unframing
        characterCountUnstuff(cc);
        characterUnstuffing(cs);
        bitUnstuffing(bs);
        sc.close();
    }
}
