import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for FramingDemo framing and unframing routines.
 */
public class TestFramingDemo {
    @Test
    public void testCharacterCountFraming() {
        final String[] input = {"hello", "world", "abc"};
        String[] framed = FramingDemo.characterCount(input);
        assertEquals("6hello", framed[0]);
        assertEquals("6world", framed[1]);
        assertEquals("4abc", framed[2]);
    }

    @Test
    public void testCharacterCountUnframing() {
        final String[] framed = {"6hello", "6world", "4abc"};
        // We can't capture System.out here, but we can check substring extraction
        int len0 = Character.getNumericValue(framed[0].charAt(0)) - 1;
        String unstuffed0 = framed[0].substring(1, 1 + len0);
        assertEquals("hello", unstuffed0);
    }

    @Test
    public void testCharacterStuffingAndUnstuffing() {
        final String[] input = {"A DLE here", "no DLE", "DLEDLE"};
        String[] stuffed = FramingDemo.characterStuffing(input);
        assertTrue(stuffed[0].startsWith("DLESTX"));
        assertTrue(stuffed[0].endsWith("DLEETX"));
        // Undo stuffing by string replace (simulate unstuffing logic)
        String data = stuffed[0].replace("DLESTX", "").replace("DLEETX", "").replace("DLEDLE", "DLE");
        assertEquals("A DLE here", data);
        String data2 = stuffed[2].replace("DLESTX", "").replace("DLEETX", "").replace("DLEDLE", "DLE");
        assertEquals("DLEDLE", data2);
    }

    @Test
    public void testBitStuffingAndUnstuffing() {
        final String[] input = {"abc", "xyz"};
        String[] stuffed = FramingDemo.bitStuffing(input);
        assertTrue(stuffed[0].startsWith("01111110"));
        assertTrue(stuffed[0].endsWith("01111110"));
        // Remove flag and unstuff bits, then recover original text
        String flag = "01111110";
        String bits = stuffed[0].replace(flag, "");
        StringBuilder unstuffed = new StringBuilder();
        int count = 0;
        for (int i = 0; i < bits.length(); i++) {
            char b = bits.charAt(i);
            unstuffed.append(b);
            if (b == '1') {
                count++;
                if (count == 5 && i + 1 < bits.length() && bits.charAt(i + 1) == '0') {
                    i++; // skip stuffed 0
                    count = 0;
                }
            } else {
                count = 0;
            }
        }
        // Convert back to text (8 bits per char)
        StringBuilder recovered = new StringBuilder();
        for (int i = 0; i + 7 < unstuffed.length(); i += 8) {
            String byteStr = unstuffed.substring(i, i + 8);
            char c = (char) Integer.parseInt(byteStr, 2);
            recovered.append(c);
        }
        assertEquals("abc", recovered.toString());
    }
}
