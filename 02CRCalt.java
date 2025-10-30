import java.util.Scanner;


public class crc_and_othermeth {

    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++)
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return result.toString();
    }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String:");
        String str = sc.nextLine();
        System.out.print("Enter Generator:");
        String gen = sc.nextLine();
        computeCRC(str, gen);
    }
}