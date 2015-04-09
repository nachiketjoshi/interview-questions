package com.yext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MakeChange {

	public static List<Integer> makeChange(int[] coins, int amount) {
		return minCoins(coins, amount, new HashMap<Integer, List<Integer>>());
	}

	private static List<Integer> minCoins(int[] coins, int amount,
			Map<Integer, List<Integer>> minCoins) {
		if (amount == 0)
			return new ArrayList<>();
		if (minCoins.containsKey(Integer.valueOf(amount)))
			return minCoins.get(Integer.valueOf(amount));

		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			if (coin > amount)
				continue;
			List<Integer> temp = minCoins(coins, amount - coin, minCoins);
			int numCoins = temp.size();
			if (numCoins < min) {
				min = numCoins;
				List<Integer> newList = new ArrayList<>(temp);
				newList.add(Integer.valueOf(coin));
				minCoins.put(Integer.valueOf(amount), newList);
			}
		}
		return minCoins.get(Integer.valueOf(amount));
	}

	public static void main(String[] args) {
		int[] coins = { 1, 5, 10, 25, 50, 100 };
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int next = r.nextInt(100);
			System.out.println(next + " = " + makeChange(coins, next));
		}
	}
}
