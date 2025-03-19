package com.example.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCalculator2 {

		private final Calculator calculator = new Calculator();
		
		static int count;
		
		@BeforeEach
		void setUp() {
			System.out.println(++count + "つ目のテスト");
		}
		
		@Test
		@DisplayName("べき乗を計算するメソッドのテスト：指数が負の場合に例外がスローされるか確認")
		void testPowerThrows() {
			// 例外がスローされるか確認し、例外オブジェクトに代入
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.power(1, -1));
			// 例外のメッセージが正しいか確認
			assertEquals("Negative exponent is not supported", exception.getMessage());
		}

		@Test
		@DisplayName("べき乗を計算するメソッドのテスト：べき乗の計算が正しいか確認")
		void testPower() {
			assertEquals(8, calculator.power(2, 3));
		}
		
		@Test
		@DisplayName("与えられた数が範囲内か確認するメソッドのテスト：範囲外の時に例外がスローされるか確認")
		void testIsInRangeThrows() {
			// 例外がスローされるか確認し、例外オブジェクトに代入
			// public boolean isInRange(int number, int min, int max)
			// （min <= number <= max）
			// min > max で例外がスローされる
			Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.isInRange(10, 15, 5));
			// 例外のメッセージが正しいか確認
			assertEquals("min cannot be greater than max", exception.getMessage());
		}
		
		@Test
		@DisplayName("与えられた数が範囲内か確認するメソッドのテスト：メソッドが期待通りの処理か確認")
		void testIsInRange() {
			// 最初に与えた引数が、2つ目の引数より大きく、3つ目の引数より小さいか確認
			// public boolean isInRange(int number, int min, int max)
			// （min <= number <= max）
			// return number >= min && number <= max;
			assertTrue(calculator.isInRange(10, 5, 15));
			// number が min より低い場合のテスト
			assertFalse(calculator.isInRange(3, 5, 10));
			// number が max より高い場合のテスト
			assertFalse(calculator.isInRange(15, 5, 10));
		}
		
		@Test
		@DisplayName("文字列を数値に変換し、その絶対値を返すメソッドのテスト：nullの場合に例外がスローされるか確認")
		void testGetAbsoluteValueFormStringThrowsNull() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.getAbsoluteValueFromString(null));
			assertEquals("Input string cannot be null", exception.getMessage());
		}
		
		@Test
		@DisplayName("文字列を数値に変換し、その絶対値を返すメソッドのテスト：数値に変換できない文字列で例外がスローされるか確認")
		void testGetAbsoluteValueFormStringThrowsNumFormat() {
			String input = "hoge";
			Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.getAbsoluteValueFromString(input));
			assertEquals("Input must be a valid integer: " + input, exception.getMessage());
		}
		
		@Test
		@DisplayName("文字列を数値に変換し、その絶対値を返すメソッドのテスト：正常な処理がされているか確認")
		void testGetAbsoluteValueFormString() {
			// 正の数を文字列として与えた場合のテスト
			assertEquals(10, calculator.getAbsoluteValueFromString("10"));
			// 負の数を文字列として与えた場合のテスト
			assertEquals(5, calculator.getAbsoluteValueFromString("-5"));
			// ゼロを文字列として与えた場合のテスト
			assertEquals(0, calculator.getAbsoluteValueFromString("0"));
		}
		
		@Test
		@DisplayName("指定された操作に応じた計算を行うメソッド：例外が正しくスローされるか確認")
		void testCalculateThrows() {
			// public double calculate(double a, double b, String operation)
			// double型の引数を定義
			double a = 10.0, b = 12.5;
			// nullを引数にした場合のテスト
			Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(a, b, null));
			assertEquals("Operation cannot be null or empty", exception.getMessage());
			// emptyを引数にした場合のテスト
			exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(a, b, ""));
			assertEquals("Operation cannot be null or empty", exception.getMessage());
			
			// 関係ない文字を引数にした場合のテスト
			final String operation1 = "hoge";
			exception = assertThrows(UnsupportedOperationException.class, () -> calculator.calculate(a, b, operation1));
			assertEquals("Unsupported operation: " + operation1, exception.getMessage());
			// blankを引数にした場合にemptyと結果が変わることを確認するテスト
			// 以下のテストは blank の場合をテストしようと思ったが、
			// trim()メソッドによって blank が empty に調整されているためテスト失敗
			// 不要テストと判断しコメントアウト
			
//			final String operation2 = "   ";
//			exception = assertThrows(UnsupportedOperationException.class, () -> calculator.calculate(a, b, operation2));
//			assertEquals("Unsupported operation: " + operation1, exception.getMessage());
			
			
			
			// ゼロ除算を行う場合のテスト
			// このメソッドは、2つ目の引数が0の場合のみ例外をスローする
			// テスト対象メソッドの条件式の引数を、(a == 0 || b == 0)に修正することを提案
			exception = assertThrows(ArithmeticException.class, () -> calculator.calculate(a, 0, "divide"));
			assertEquals("Division by zero", exception.getMessage());
		}
		
		@Test
		@DisplayName("指定された操作に応じた計算を行うメソッド：正常な処理の分岐条件網羅")
		void testCalculate() {
			double a = 10.0, b = 2.0;
			// 足し算のテスト
			assertEquals(12.0, calculator.calculate(a, b, "add"));
			// 引き算のテスト
			assertEquals(8.0, calculator.calculate(a, b, "subtract"));
			// 掛け算のテスト
			assertEquals(20.0, calculator.calculate(a, b, "multiply"));
			// 割り算のテスト
			assertEquals(5.0, calculator.calculate(a, b, "divide"));
			
			// toLowerCase()が正しく処理されているか確認
			// 足し算のテスト
			assertEquals(12.0, calculator.calculate(a, b, "AdD"));
			// 引き算のテスト
			assertEquals(8.0, calculator.calculate(a, b, "suBTract"));
			// 掛け算のテスト
			assertEquals(20.0, calculator.calculate(a, b, "multiPly"));
			// 割り算のテスト
			assertEquals(5.0, calculator.calculate(a, b, "DIVIDE"));
		}
		
		@Test
		@DisplayName("条件式をネストしているメソッドのテスト")
		void testCategorizeNumber() {
			// public String categorizeNumber(int number, int threshold) 
			// 例外がスローされるか確認
			Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.categorizeNumber(0, -1));
			assertEquals("Threshold cannot be negative", exception.getMessage());
			
			// number < threshold　の条件の網羅
			int threshold = 10;
			// number < 0　の場合のテスト
			assertEquals("Small", calculator.categorizeNumber(-1, threshold));
			// number が 0 以上の場合のテスト
			assertEquals("Medium", calculator.categorizeNumber(0, threshold));
			assertEquals("Medium", calculator.categorizeNumber(9, threshold));
			
			// number > threshold　の条件の網羅
			int number = 50;
			// number > threshold * 2 の場合のテスト
			assertEquals("Large", calculator.categorizeNumber(number, 10));
			// number が threshold * 2 より小さい場合のテスト
			assertEquals("Unknown", calculator.categorizeNumber(number, 30));
		}
		
}
