import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for CRCComputation and CRCAlt routines.
 * Ensures that CRC encoding and codeword generation is correct.
 */
public class TestCRC {
    /**
     * Tests CRCComputation with standard CRC-12 generator.
     */
    @Test
    public void testCRC12() {
        String data = "11010011101100";
        String generator = "1100000001111"; // CRC-12
        String crc = CRCComputation.computeCRC(data, generator);
        String appended = data + crc;
        // If we divide appended codeword by generator, remainder must be zero
        String remainder = CRCComputation.divide(appended, generator);
        // All zeros
        assertTrue(remainder.matches("0+"));
    }

    /**
     * Tests CRCAlt with arbitrary generator, matches CRCComputation.
     */
    @Test
    public void testCRCAltMatchesComputation() {
        String data = "1010101";
        String generator = "1101";
        String crc = CRCComputation.computeCRC(data, generator);
        String appended = data + crc;
        String remainder = CRCAlt.divide(appended, generator);
        assertTrue(remainder.matches("0+"));
    }

    /**
     * Tests that CRCAlt codeword generation is as specified.
     */
    @Test
    public void testCRCAltCodeword() {
        String data = "1001";
        String generator = "1011";
        int genLen = generator.length();
        String appendedData = data + "0".repeat(genLen - 1);
        String remainder = CRCAlt.divide(appendedData, generator);
        String codeword = data + remainder;
        // When divided by generator, codeword should yield zero remainder
        String rem = CRCAlt.divide(codeword, generator);
        assertTrue(rem.matches("0+"));
    }
}
