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
        return convertFromDecimal(decimalNumber, targetBase);
    }

    private static String convertFromDecimal(String decimalNumber, String targetBase) {
        StringBuilder result = new StringBuilder();
        int baseNum = Integer.parseInt(targetBase);
        BigInteger baseBig = BigInteger.valueOf(baseNum);
        BigInteger decimalBig = new BigInteger(decimalNumber);

        getRemainderRecursively(decimalBig, baseBig, result);
        return result.reverse().toString();
    }

    private static void getRemainderRecursively(BigInteger decimalBig, BigInteger baseBig, StringBuilder result) {
        int modResult = Integer.parseInt(String.valueOf(decimalBig.mod(baseBig)));
        char indexChar = POSSIBLE_CHARS.charAt(modResult);
        result.append(indexChar);

        if (decimalBig.divide(baseBig).equals(BigInteger.ONE)) {
            result.append(decimalBig.divide(baseBig));
        } else if (!decimalBig.divide(baseBig).equals(BigInteger.ZERO)) {
            getRemainderRecursively(decimalBig.divide(baseBig), baseBig, result);
        }
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
}
