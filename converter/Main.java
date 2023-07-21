package converter;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number in decimal system: ");
        int decimalNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter target base: ");
        int chosenBase = Integer.parseInt(scanner.nextLine());
        System.out.println("Conversion result: " + convertDecimalNumber(decimalNumber, chosenBase));
    }

    private static String convertDecimalNumber(int decimalNumber, int chosenBase) {
        return switch (chosenBase) {
            case 2 -> Integer.toBinaryString(decimalNumber);
            case 8 -> Integer.toOctalString(decimalNumber);
            case 16 -> Integer.toHexString(decimalNumber);
            default -> "Unknown base!";
        };
    }
}
