# Number Base Converter

This program allows you to convert numbers between different bases (radix). It supports bases from 2 to 36.

## How to Use

1. Run the program, and you'll be prompted to enter two numbers in the format: `{source base} {target base}`. For example, `10 2` means you want to convert from base 10 to base 2.

2. After entering the bases, you can start converting numbers. You'll be asked to enter a number in the source base, and the program will provide the equivalent number in the target base.

3. To exit the program, type `/exit` as the first-level command.

## Conversion Examples

**Example 1: Converting from Base 10 to Base 2**

```agsl
Enter two numbers in format: {source base} {target base} (To quit type /exit)
10 2
Enter number in base 10 to convert to base 2 (To go back type /back)
42
Conversion result: 101010
```

**Example 2: Converting from Base 24 to Base 37**

```agsl
Enter two numbers in format: {source base} {target base} (To quit type /exit)
35 24
Enter number in base 35 to convert to base 24 (To go back type /back)
4n4.l1nd4
Conversion result: 9ll.ea94j
```


## Supported Bases

The program supports source and target bases in the range from 2 to 36. The characters used for representation are `0-9` and `a-z`, where `a` represents 10, `b` represents 11, and so on, up to `z`, which represents 35.

## Note

- For non-integer numbers, the program rounds the results to 5 decimal places using the `HALF_UP` rounding mode.
- To cancel the current conversion and go back to the base selection, type `/back` when asked for a number to convert.

Enjoy converting numbers in different bases!
