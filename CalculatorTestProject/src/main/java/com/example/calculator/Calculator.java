package com.example.calculator;

public class Calculator {
	
//	林テスト0101
//	0321のテスト追加01
	

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
    
    /**
     * 与えられた数値が特定の条件に基づいてカテゴリに分類されるかを判定。
     * 2重の条件分岐を含み、例外もスローする可能性あり。
     * @param number 判定対象の数値
     * @param threshold 閾値
     * @return カテゴリ名（"Small", "Medium", "Large"）または "Unknown"
     * @throws IllegalArgumentException 閾値が負の場合
     */
    public String categorizeNumber(int number, int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold cannot be negative");
        }

        if (number < threshold) {
            if (number < 0) {
                return "Small"; // 負の数かつ閾値未満
            } else {
                return "Medium"; // 正の数だが閾値未満
            }
        } else {
            if (number > threshold * 2) {
                return "Large"; // 閾値の2倍を超える
            } else {
                return "Unknown"; // 閾値以上だが2倍以下
            }
        }
    }
}