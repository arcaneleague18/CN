import java.util.*;

/**
 * Demonstrates Caesar Cipher encryption and decryption with safe input and clear output.
 * Provides static utility methods for encrypting and decrypting strings with alphabetic and non-alphabetic characters.
 */
public class CaesarCipher {
    /**
     * Main method for interactive demonstration.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift key: ");
        int key;
        try {
            key = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid key. Exiting.");
            sc.close();
            return;
        }
        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
    /**
     * Encrypts text using Caesar Cipher.
     * @param text Input string to encrypt
     * @param key Shift value (may be negative, zero, or positive)
     * @return Encrypted string, with only alphabetic characters shifted
     */
    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                // Ensure wrap-around for negative shifts as well
                c = (char) ((c - base + (key % 26) + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }
    /**
     * Decrypts text encrypted by Caesar Cipher.
     * @param text Encrypted string
     * @param key Original shift value used for encryption
     * @return Decrypted (original) string
     */
    public static String decrypt(String text, int key) {
        // Decrypt by shifting in the opposite direction
        return encrypt(text, 26 - (key % 26)); // Reverse the shift with modulo
    }
    /**
     * Private constructor to prevent instantiation.
     */
    private CaesarCipher() {}
}
