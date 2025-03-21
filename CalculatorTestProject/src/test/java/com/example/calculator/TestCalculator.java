package com.example.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestCalculator {
	
	// test
	
	private final Calculator calculator = new Calculator();
	
	private int expected;
	private int actual;
	private boolean isActual;

	@BeforeAll
	static void initAll() throws Exception {
		System.out.println("@BeforeAll：テスト開始前に1回だけ実行");
	}
	
	@BeforeEach
	void setUp() {
		System.out.println("@BeforeEach：テストメソッド前に毎回呼ばれる");
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("@AfterEach：テストメソッド後に毎回呼ばれる");
	}
	
	@AfterAll
	static void tearDownAll() {
		System.out.println("@AfterAll：テスト終了後に1回だけ実行");
	}
	
	@Test
	@DisplayName("足し算メソッドが正しいかテスト") // @DisplayName：結果表示をカスタマイズ
    void testAdd() {
		// 3と5を引数にしたaddメソッドが、8を返しているかテスト
		expected = 8;
		actual = calculator.add(3, 5);
        assertEquals(expected, actual);
        // -2と3を引数にしたaddメソッドが、1を返しているかテスト
        // 負の数を扱えるかを確認
        expected = 1;
        actual = calculator.add(-2, 3);
        assertEquals(expected, actual);
    }
	
	@Test
	@DisplayName("引き算メソッドが正しいかテスト")
	void testSubtract() {
		// 正の数同士の引き算
		expected = 3;
		actual = calculator.subtract(7, 4);
		assertEquals(expected, actual);
		// 結果が負になる引き算、結果が特定の値と異なることを確認
		expected = 0;
		actual = calculator.subtract(2, 5);
		assertNotEquals(expected, actual);
	}
	
	@Test
	@DisplayName("掛け算メソッドが正しいかテスト")
	void testMultiply() {
		// ゼロとの掛け算
		expected = 0;
		actual = calculator.multiply(5, 0);
		assertEquals(expected, actual);
		// 結果が正しい範囲内にあるか
		isActual = (calculator.multiply(100, 200) > 0);
		assertTrue(isActual);
	}
	
	@Test
	@DisplayName("割り算メソッドが正しいかテスト")
	void testDivide() {
		// 通常の割り算
		// double型の変数を用意するのが面倒のため記述内容を変更
		assertEquals(5.0, calculator.divide(10.0, 2.0));
		// ゼロでの除算　例外がスローされるか確認
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(5.0, 0));
		// 負の数の割り算
		assertEquals(-3.0, calculator.divide(-6.0, 2.0));
	}
	
	@Test
	@DisplayName("0より大きいか確認するメソッドのテスト")
	void testIsPositive() {
		// 正の数を入力
		assertTrue(calculator.isPositive(5));
		// ゼロを入力
		assertFalse(calculator.isPositive(0));
		// 負の数を入力
		assertFalse(calculator.isPositive(-3));
	}
	
	@Test
	@DisplayName("与えた文字列によって戻り値が変わるメソッドのテスト")
	void testGetOperationName() {
		// addを入力
		assertEquals("Addition", calculator.getOperationName("add"));
		// nullを入力
		assertEquals("Unknown", calculator.getOperationName(null));
		// 未定義の操作
		assertNotNull(calculator.getOperationName("multiply"));
		assertEquals("Unknown", calculator.getOperationName("multiply"));
		// 大文字小文字の違い　toLowerCase()メソッドが機能しているか確認
		assertEquals("Addition", calculator.getOperationName("ADD"));
	}
}