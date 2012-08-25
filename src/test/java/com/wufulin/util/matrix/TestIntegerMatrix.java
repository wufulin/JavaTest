package com.wufulin.util.matrix;

public class TestIntegerMatrix {
	public static void main(String[] args) {
		Integer[][] m1 = new Integer[][] { { 1, 2, 3 }, { 4, 5, 6 },
				{ 7, 8, 9 } };
		Integer[][] m2 = new Integer[][] { { 1, 1, 1 }, { 2, 2, 2 },
				{ 0, 0, 0 } };

		IntegerMatrix integerMatrix = new IntegerMatrix();
		long start=System.currentTimeMillis();
		System.out.println("\nm1 + m2 is ");
		integerMatrix.printResult(m1, m2, integerMatrix.addMatrix(m1, m2), '+');
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		
		start=System.currentTimeMillis();
		System.out.println("\nm1 * m2 is ");
		integerMatrix.printResult(m1, m2, integerMatrix.multiply(m1, m2), '*');
		end=System.currentTimeMillis();
		System.out.println(end-start);
	}
}
