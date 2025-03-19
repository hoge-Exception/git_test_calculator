package com.example.calculator;

public class Calculator {

    // 既存の基本的なメソッド（一部改良）
    public int add(int a, int b) {
        return a + b; // オーバーフロー考慮なしでシンプルに
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return a / b;
    }

    public boolean isPositive(int number) {
        return number > 0;
    }

    public String getOperationName(String operation) {
        if (operation == null) {
            return "Unknown";
        }
        switch (operation.toLowerCase()) {
            case "add":
                return "Addition";
            case "subtract":
                return "Subtraction";
            default:
                return "Unknown";
        }
    }

    // 新しいメソッド（条件分岐や例外を追加）
    /**
     * べき乗を計算。指数が負の場合は例外をスロー。
     */
    public long power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Negative exponent is not supported");
        }
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * 与えられた数が範囲内（min <= number <= max）か判定。
     * min > max の場合は例外をスロー。
     */
    public boolean isInRange(int number, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }
        return number >= min && number <= max;
    }

    /**
     * 文字列を数値に変換し、その絶対値を返す。
     * 文字列がnullまたは数値に変換できない場合は例外をスロー。
     */
    public int getAbsoluteValueFromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        try {
            int number = Integer.parseInt(input);
            return Math.abs(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a valid integer: " + input);
        }
    }

    /**
     * 指定された操作に応じた計算を行う（条件分岐）。
     * 操作が未定義または無効な場合は例外をスロー。
     */
    public double calculate(double a, double b, String operation) {
        if (operation == null || operation.trim().isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be null or empty");
        }
        switch (operation.toLowerCase()) {
            case "add":
                return a + b;
            case "subtract":
                return a - b;
            case "multiply":
                return a * b;
            case "divide":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new UnsupportedOperationException("Unsupported operation: " + operation);
        }
    }
}