import java.util.*;

/**
 * Demonstrates Caesar Cipher encryption and decryption with safe input and clear output.
 */
public class CaesarCipher {
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
     */
    static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) ((c - base + key) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }
    /**
     * Decrypts text encrypted by Caesar Cipher.
     */
    static String decrypt(String text, int key) {
        return encrypt(text, 26 - (key % 26)); // Reverse the shift with modulo
    }
    private CaesarCipher() {}
}
