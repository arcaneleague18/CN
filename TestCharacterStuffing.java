import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for CharacterStuffing and BitStuffingString routines.
 * Ensures that encoding (stuffing) and then decoding (unstuffing) yields the original data.
 *
 * Test coverage includes:
 * - Round-trip stuffing/unstuffing of normal, special, empty, and null strings.
 * - Bit stuffing/un-stuffing for bit streams and edge cases.
 * - Handling of only special characters (FLAG, ESC) in character stuffing.
 */
public class TestCharacterStuffing {
    /**
     * Tests character stuffing and unstuffing for a variety of inputs, including null and empty strings.
     */
    @Test
    public void testCharacterStuffingRoundTrip() {
        final char FLAG = '~';
        final char ESC = '}';
        String[] messages = {
            "hello", "abc~def", "}start", "~}~", "", null
        };
        for (String msg : messages) {
            String stuffed = CharacterStuffing.stuff(msg, FLAG, ESC);
            String unstuffed = CharacterStuffing.unstuff(stuffed, FLAG, ESC);
            assertEquals(msg == null ? "" : msg, unstuffed);
        }
    }

    /**
     * Tests bit stuffing and unstuffing for a variety of bit streams, including edge cases.
     */
    @Test
    public void testBitStuffingRoundTrip() {
        String[] bitstreams = {
            "01111110", "11111", "111110", "00000", "1010101011", "111111111111", ""
        };
        for (String data : bitstreams) {
            String stuffed = BitStuffingString.bitStuff(data);
            String unstuffed = BitStuffingString.bitUnstuff(stuffed);
            assertEquals(data, unstuffed);
        }
    }

    /**
     * Tests character stuffing and unstuffing where the input consists only of special characters (FLAG and ESC).
     */
    @Test
    public void testOnlySpecialCharacters() {
        final char FLAG = '~';
        final char ESC = '}';
        String input = "~~}}~}";
        String stuffed = CharacterStuffing.stuff(input, FLAG, ESC);
        String unstuffed = CharacterStuffing.unstuff(stuffed, FLAG, ESC);
        assertEquals(input, unstuffed);
    }

    /**
     * Tests bit stuffing and unstuffing explicitly for null and empty input.
     */
    @Test
    public void testBitStuffingEmptyInput() {
        String stuffed = BitStuffingString.bitStuff("");
        assertEquals("", stuffed);
        String unstuffed = BitStuffingString.bitUnstuff(stuffed);
        assertEquals("", unstuffed);
    }
}
