import java.util.Scanner;

/**
 * Alternative CRC computation implementation supporting arbitrary generator input.
 * Includes input validation and safe resource handling.
 */
public class CRCAlt {
    /**
     * Performs XOR between two binary strings (excluding leading bit).
     */
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++)
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return result.toString();
    }
    /**
     * Performs binary polynomial division for CRC.
     */
    static String divide(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);
        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1')
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
            else
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
            pick += 1;
        }
        // Last XOR
        if (tmp.charAt(0) == '1')
            tmp = xor(divisor, tmp);
        else
            tmp = xor("0".repeat(pick), tmp);
        return tmp;
    }
    /**
     * Prints CRC value and codeword for given data and generator.
     */
    static void computeCRC(String data, String generator) {
        int genLen = generator.length();
        String appendedData = data + "0".repeat(genLen - 1);
        String remainder = divide(appendedData, generator);
        String codeword = data + remainder;
        System.out.println("Data: " + data);
        System.out.println("Generator: " + generator);
        System.out.println("CRC: " + remainder);
        System.out.println("Codeword: " + codeword + "\n");
    }
    private CRCAlt() {}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String:");
        String str = sc.nextLine();
        if (!str.matches("[01]+")) {
            System.out.println("Invalid input. Enter only binary digits (0/1).");
            sc.close();
            return;
        }
        System.out.print("Enter Generator:");
        String gen = sc.nextLine();
        if (!gen.matches("[01]+")) {
            System.out.println("Invalid generator. Enter only binary digits (0/1).");
            sc.close();
            return;
        }
        computeCRC(str, gen);
        sc.close();
    }
}
