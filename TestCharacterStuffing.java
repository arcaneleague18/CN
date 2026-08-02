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
        char FLAG = '~';
        char ESC = '}';
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
            "01111110", "11111", "111110", "00000", "1010101011", "111111111111"
        };
        for (String data : bitstreams) {
            String stuffed = BitStuffingString.bitStuff(data);
            String unstuffed = BitStuffingString.bitUnstuff(stuffed);
            assertEquals(data, unstuffed);
        }
    }
}
