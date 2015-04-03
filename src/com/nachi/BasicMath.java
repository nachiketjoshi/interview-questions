package com.nachi;

public class BasicMath {

	public int gcd(int x, int y) {
		if (y == 0)
			return x;
		return gcd(y, x % y);
	}

	public int lcm(int x, int y) {
		if (x == 0 && y == 0)
			return 0;
		return (x * y) / gcd(x, y);
	}
}
