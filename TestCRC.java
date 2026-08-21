import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for CRCComputation and CRCAlt routines.
 * Ensures that CRC encoding, codeword generation, and division logic are correct.
 *
 * Test coverage includes:
 * - CRC computation and codeword validation with standard generator polynomials (CRC-12)
 * - Cross-checking alternative implementations (CRCAlt vs CRCComputation)
 * - Codeword construction/validation for arbitrary data and generator inputs
 */
public class TestCRC {
    /**
     * Tests CRCComputation with standard CRC-12 generator, ensuring the computed codeword is valid.
     * When the codeword (data + CRC remainder) is divided by the generator, the remainder must be all zeros.
     */
    @Test
    public void testCRC12() {
        String data = "11010011101100";
        String generator = "1100000001111"; // CRC-12
        String crc = CRCComputation.computeCRC(data, generator);
        String appended = data + crc;
        // If we divide appended codeword by generator, remainder must be zero
        String remainder = CRCComputation.divide(appended, generator);
        // All zeros expected in remainder
        assertTrue(remainder.matches("0+"));
    }

    /**
     * Tests CRCAlt with arbitrary generator, matches CRCComputation.
     * Verifies that both implementations produce compatible codewords (remainder is zero).
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
     * Tests that CRCAlt codeword generation is as specified for arbitrary data and generator.
     * Ensures that when the generated codeword is divided by the generator, the remainder is all zeros.
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
