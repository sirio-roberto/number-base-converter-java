package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
        BigDecimal decimalBig = new BigDecimal(decimalNumber);

        String[] bigDecimalArray = decimalBig.toString().split("\\.");

        BigInteger integerBig = new BigInteger(bigDecimalArray[0]);
        getRemainderRecursively(integerBig, baseBig, result);

        result.reverse();
        if (bigDecimalArray.length > 1) {
            result.append(".");
            BigDecimal fractionalPart = new BigDecimal("0." + bigDecimalArray[1]);
            BigDecimal baseDecimal = new BigDecimal(baseBig);
            calculateFractionalRecursively(fractionalPart, baseDecimal, result, 4);
        }
        return result.toString();
    }

    private static void calculateFractionalRecursively(BigDecimal fractional, BigDecimal baseDec, StringBuilder result, int depth) {
        fractional = fractional.multiply(baseDec);
        BigInteger decimalPart = fractional.toBigInteger();
        int charIndex = decimalPart.intValue();
        result.append(POSSIBLE_CHARS.charAt(charIndex));

        fractional = fractional.subtract(new BigDecimal(decimalPart));

        if (depth > 0 && !fractional.equals(BigDecimal.ZERO)) {
            calculateFractionalRecursively(fractional, baseDec, result, depth - 1);
        }
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
        BigDecimal bigDecimal = BigDecimal.ZERO;
        int power;
        boolean removeFractionalPart = true;
        if (numberToConvert.indexOf('.') > -1) {
            power = -numberToConvert.substring(numberToConvert.indexOf('.')).length() + 1;
            removeFractionalPart = false;
        } else {
            power = 0;
        }
        for (int i = numberToConvert.length() - 1; i >= 0; i--) {
            if (numberToConvert.charAt(i) != '.') {
                double decimal = POSSIBLE_CHARS.indexOf(numberToConvert.charAt(i)) * Math.pow(baseNum, power);
                bigDecimal = bigDecimal.add(BigDecimal.valueOf(decimal));
                power++;
            }
        }
        // TODO: I may need to round it later, not now
        if (removeFractionalPart) {
            return bigDecimal.toString().substring(0, bigDecimal.toString().indexOf('.'));
        }
        return bigDecimal.setScale(5, RoundingMode.HALF_UP).toString();
    }
}
