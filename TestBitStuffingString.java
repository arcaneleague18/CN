import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for BitStuffingString routines.
 * Ensures correctness for bit stuffing and unstuffing, including edge cases.
 */
public class TestBitStuffingString {
    /**
     * Tests bit stuffing and unstuffing for basic cases and round-trip integrity.
     */
    @Test
    public void testBitStuffingRoundTrip() {
        String[] bitstreams = {
            "01111110", // HDLC flag
            "11111",    // should stuff a zero
            "111110",   // one stuffing, trailing zero
            "00000",    // no stuffing
            "1010101011", // no stuffing
            "111111111111", // multiple stuffings
            "", // empty
            null // null input
        };
        for (String data : bitstreams) {
            String stuffed = BitStuffingString.bitStuff(data);
            String unstuffed = BitStuffingString.bitUnstuff(stuffed);
            if (data == null || data.isEmpty()) {
                assertEquals("", stuffed);
                assertEquals("", unstuffed);
            } else {
                assertEquals(data, unstuffed);
            }
        }
    }

    /**
     * Tests bit stuffing for a string with exactly five consecutive 1s.
     */
    @Test
    public void testStuffAfterFiveOnes() {
        String input = "11111";
        String stuffed = BitStuffingString.bitStuff(input);
        assertEquals("111110", stuffed);
        String unstuffed = BitStuffingString.bitUnstuff(stuffed);
        assertEquals(input, unstuffed);
    }

    /**
     * Tests bit stuffing for a string with multiple sequences of five 1s.
     */
    @Test
    public void testMultipleStuffings() {
        String input = "11111111111"; // 11 1's
        String stuffed = BitStuffingString.bitStuff(input);
        // Should have two stuffed zeros
        assertEquals("1111101111101", stuffed);
        String unstuffed = BitStuffingString.bitUnstuff(stuffed);
        assertEquals(input, unstuffed);
    }

    /**
     * Tests that bitStuff returns empty string for null or empty input.
     */
    @Test
    public void testEmptyAndNullInput() {
        assertEquals("", BitStuffingString.bitStuff(""));
        assertEquals("", BitStuffingString.bitStuff(null));
        assertEquals("", BitStuffingString.bitUnstuff(""));
        assertEquals("", BitStuffingString.bitUnstuff(null));
    }

    /**
     * Tests that bitUnstuff returns the same string when there are no stuffed zeros.
     */
    @Test
    public void testNoStuffingNeeded() {
        String input = "00000";
        String stuffed = BitStuffingString.bitStuff(input);
        assertEquals(input, stuffed);
        String unstuffed = BitStuffingString.bitUnstuff(stuffed);
        assertEquals(input, unstuffed);
    }
}
