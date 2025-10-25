import java.util.*;

public class CRCComputation {

    // Function to perform XOR between two binary strings
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // Function to perform CRC division
    static String divide(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);

        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1') {
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
            } else {
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
            }
            pick++;
        }

        // For the last bits
        if (tmp.charAt(0) == '1')
            tmp = xor(divisor, tmp);
        else
            tmp = xor("0".repeat(pick), tmp);

        return tmp;
    }

    // Function to compute CRC code
    static String computeCRC(String data, String generator) {
        int genLen = generator.length();
        String appendedData = data + "0".repeat(genLen - 1);
        String remainder = divide(appendedData, generator);
        return remainder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data bits (binary): ");
        String data = sc.nextLine();

        // Standard generator polynomials
        String crc12 = "1100000001111";    // CRC-12: x^12 + x^11 + x^3 + x^2 + x + 1
        String crc16 = "11000000000000101"; // CRC-16: x^16 + x^15 + x^2 + 1
        String crcCCITT = "10001000000100001"; // CRC-CCITT: x^16 + x^12 + x^5 + 1

        // Compute CRCs
        String rem12 = computeCRC(data, crc12);
        String rem16 = computeCRC(data, crc16);
        String remCCITT = computeCRC(data, crcCCITT);

        // Display results
        System.out.println("\n--- CRC Results ---");
        System.out.println("CRC-12    Remainder: " + rem12);
        System.out.println("CRC-16    Remainder: " + rem16);
        System.out.println("CRC-CCITT Remainder: " + remCCITT);

        System.out.println("\nTransmitted Frames:");
        System.out.println("CRC-12    Frame: " + data + rem12);
        System.out.println("CRC-16    Frame: " + data + rem16);
        System.out.println("CRC-CCITT Frame: " + data + remCCITT);

        sc.close();
    }
}
