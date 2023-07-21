package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyz";

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
                        System.out.println("Conversion result: " + getConversionResult(secondLevelCommand, srcBase, targetBase));
                    }

                    System.out.println();
                } while (!"/back".equals(secondLevelCommand));
            }

        } while (!"/exit".equals(firstLevelCommand));
    }

    private static String getConversionResult(String numberToConvert, String srcBase, String targetBase) {
        if ("10".equals(targetBase)) {
            return convertoToDecimal(numberToConvert, srcBase);
        }
        String decimalNumber;
        if (!"10".equals(srcBase)) {
            decimalNumber = convertoToDecimal(numberToConvert, srcBase);
        } else {
            decimalNumber = numberToConvert;
        }
        return decimalNumber;
    }

    private static String convertoToDecimal(String numberToConvert, String srcBase) {
        int baseNum = Integer.parseInt(srcBase);
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i = 0; i < numberToConvert.length(); i++) {
            int currentReversedIndex = numberToConvert.length() - 1 - i;
            double decimal = POSSIBLE_CHARS.indexOf(numberToConvert.charAt(currentReversedIndex)) * Math.pow(baseNum, i);
            bigInteger = bigInteger.add(BigInteger.valueOf((long) decimal));
        }
        return bigInteger.toString();
    }

//    private static void showToDecimalMenu() {
//        System.out.print("Enter source number: ");
//        String sourceNumber = scanner.nextLine();
//        System.out.print("Enter source base: ");
//        int base = Integer.parseInt(scanner.nextLine());
//
//        System.out.println("Conversion to decimal result: " + convertToDecimal(sourceNumber, base));
//    }
//
//    private static int convertToDecimal(String sourceNumber, int base) {
//        return Integer.parseInt(sourceNumber, base);
//    }

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
