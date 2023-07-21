package converter;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String firstLevelCommand;
        do {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            firstLevelCommand = scanner.nextLine();

            if (!"/exit".equals(firstLevelCommand)) {
                String[] bases = firstLevelCommand.split(" ");
                String srcBase = bases[0];
                String targetBase = bases[1];

                String secondLevelCommand;
                do {
                    System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) ",
                            srcBase, targetBase);
                    secondLevelCommand = scanner.nextLine();

                    if (!"/back".equals(secondLevelCommand)) {
                        System.out.println("Conversion result: " + getConversionResult(srcBase, targetBase));
                    }

                    System.out.println();
                } while (!"/back".equals(secondLevelCommand));
            }

        } while (!"/exit".equals(firstLevelCommand));
    }

    private static String getConversionResult(String srcBase, String targetBase) {
        return null;
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
