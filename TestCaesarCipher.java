import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for CaesarCipher encryption and decryption routines.
 * Ensures correctness for various keys, wrap-around, and non-alphabetic characters.
 */
public class TestCaesarCipher {
    @Test
    public void testEncryptDecryptSimple() {
        String text = "HelloWorld";
        int key = 3;
        String encrypted = CaesarCipher.encrypt(text, key);
        assertEquals("KhoorZruog", encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, key);
        assertEquals(text, decrypted);
    }

    @Test
    public void testEncryptDecryptWithSpacesAndSymbols() {
        String text = "Hello, World! 123";
        int key = 5;
        String encrypted = CaesarCipher.encrypt(text, key);
        assertEquals("Mjqqt, Btwqi! 123", encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, key);
        assertEquals(text, decrypted);
    }

    @Test
    public void testEncryptDecryptWithZeroShift() {
        String text = "abcXYZ";
        int key = 0;
        String encrypted = CaesarCipher.encrypt(text, key);
        assertEquals("abcXYZ", encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, key);
        assertEquals(text, decrypted);
    }

    @Test
    public void testEncryptDecryptWithNegativeShift() {
        String text = "abcXYZ";
        int key = -3;
        String encrypted = CaesarCipher.encrypt(text, key);
        assertEquals("xyzUVW", encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, key);
        assertEquals(text, decrypted);
    }

    @Test
    public void testEncryptDecryptWithLargeKey() {
        String text = "abc";
        int key = 52; // full cycle
        String encrypted = CaesarCipher.encrypt(text, key);
        assertEquals("abc", encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, key);
        assertEquals(text, decrypted);
    }

    @Test
    public void testEncryptDecryptAllLetters() {
        String text = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int key = 13;
        String encrypted = CaesarCipher.encrypt(text, key);
        assertEquals("nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM", encrypted);
        String decrypted = CaesarCipher.decrypt(encrypted, key);
        assertEquals(text, decrypted);
    }
}
