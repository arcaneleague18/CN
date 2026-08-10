import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic tests for stuffing/unstuffing routines (character and bit).
 * Demonstrates that encoding and then decoding yields the original data.
 */
public class TestCharacterStuffing {
    /**
     * Tests character stuffing and unstuffing for a variety of inputs.
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
     * Tests bit stuffing and unstuffing for a variety of bit streams.
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
     * Tests character stuffing and unstuffing with only FLAG and ESC chars repeatedly.
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
     * Tests bit stuffing with null or empty input explicitly.
     */
    @Test
    public void testBitStuffingEmptyInput() {
        String stuffed = BitStuffingString.bitStuff("");
        assertEquals("", stuffed);
        String unstuffed = BitStuffingString.bitUnstuff(stuffed);
        assertEquals("", unstuffed);
    }
}
