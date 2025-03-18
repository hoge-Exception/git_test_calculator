package com.example.calculator;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
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
}