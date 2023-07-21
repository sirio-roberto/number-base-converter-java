package converter;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String userCommand;
        do {
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            userCommand = scanner.nextLine();

            switch (userCommand) {
                case "/from" -> showFromDecimalMenu();
                case "/to" -> showToDecimalMenu();
            }

            System.out.println();
        } while (!"/exit".equals(userCommand));
    }

    private static void showToDecimalMenu() {
        System.out.print("Enter source number: ");
        String sourceNumber = scanner.nextLine();
        System.out.print("Enter source base: ");
        int base = Integer.parseInt(scanner.nextLine());

        System.out.println("Conversion to decimal result: " + convertToDecimal(sourceNumber, base));
    }

    private static int convertToDecimal(String sourceNumber, int base) {
        return Integer.parseInt(sourceNumber, base);
    }

    private static void showFromDecimalMenu() {
        System.out.print("Enter number in decimal system: ");
        int decimalNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter target base: ");
        int chosenBase = Integer.parseInt(scanner.nextLine());

        System.out.println("Conversion result: " + convertFromDecimal(decimalNumber, chosenBase));
    }

    private static String convertFromDecimal(int decimalNumber, int chosenBase) {
        return switch (chosenBase) {
            case 2 -> Integer.toBinaryString(decimalNumber);
            case 8 -> Integer.toOctalString(decimalNumber);
            case 16 -> Integer.toHexString(decimalNumber);
            default -> "Unknown base!";
        };
    }
}
