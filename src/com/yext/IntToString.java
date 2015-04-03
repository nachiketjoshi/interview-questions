package com.yext;

public class IntToString {

	public static String convert(int n) {
		boolean negative = false;
		String s = "";
		if (n < 0) {
			negative = true;
			n *= -1;
		}
		while (n > 0) {
			s = (char) ('0' + (n % 10)) + s;
			n = n / 10;
		}
		return (negative) ? "-" + s : s;
	}
}
