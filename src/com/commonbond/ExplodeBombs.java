package com.commonbond;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplodeBombs {

	private static final char BOMB = 'B';
	private static final char MULTI_SHRAPNEL = 'X';
	private static final char RIGHT_SHRAPNEL = '>';
	private static final char LEFT_SHRAPNEL = '<';
	private static final char EMPTY = '.';

	public static String[] explode(String bombs, int force) {
		if (bombs.length() < 1 || bombs.length() > 50) {
			System.out.println("Invalid length : " + bombs.length());
			return null;
		}
		if (force < 1 || force > 10) {
			System.out.println("Invalid force : " + force);
			return null;
		}

		List<String> results = new ArrayList<>();
		results.add(bombs); // initial configuration

		String currentChamber = explodeAllBombs(bombs, force);
		// loop until no more shrapnel in the chamber
		while (!isChamberEmpty(currentChamber)) {
			results.add(currentChamber);
			currentChamber = nextIteration(currentChamber, force);
		}
		results.add(currentChamber);// empty chamber
		return results.toArray(new String[results.size()]);
	}

	/** Get next iteration of moving shrapnel */
	private static String nextIteration(String currentChamber, int force) {
		char[] newChamber = new char[currentChamber.length()];
		Arrays.fill(newChamber, EMPTY);

		for (int i = 0; i < currentChamber.length(); i++) {
			switch (currentChamber.charAt(i)) {
			case LEFT_SHRAPNEL:
				moveLeft(newChamber, i, force);
				break;
			case RIGHT_SHRAPNEL:
				moveRight(newChamber, i, force);
				break;
			case MULTI_SHRAPNEL:
				moveLeft(newChamber, i, force);
				moveRight(newChamber, i, force);
				break;
			}
		}
		return new String(newChamber);
	}

	/** Explode all the initially set bombs */
	private static String explodeAllBombs(String currentChamber, int force) {
		char[] newChamber = new char[currentChamber.length()];
		Arrays.fill(newChamber, EMPTY);

		for (int i = 0; i < currentChamber.length(); i++) {
			if (currentChamber.charAt(i) == BOMB) {
				moveLeft(newChamber, i, force);
				moveRight(newChamber, i, force);
			}
		}
		return new String(newChamber);
	}

	/** Calculates next left shrapnel position and sets it */
	private static void moveLeft(char[] chamber, int index, int force) {
		int leftShrapnelPosition = index - force;
		if (leftShrapnelPosition >= 0) {
			switch (chamber[leftShrapnelPosition]) {
			case EMPTY:
				chamber[leftShrapnelPosition] = LEFT_SHRAPNEL;
				break;
			case RIGHT_SHRAPNEL:
				chamber[leftShrapnelPosition] = MULTI_SHRAPNEL;
				break;
			}
		}
	}

	/** Calculates next right shrapnel position and sets it */
	private static void moveRight(char[] chamber, int index, int force) {
		int rightShrapnelPosition = index + force;
		if (rightShrapnelPosition < chamber.length) {
			switch (chamber[rightShrapnelPosition]) {
			case EMPTY:
				chamber[rightShrapnelPosition] = RIGHT_SHRAPNEL;
				break;
			case LEFT_SHRAPNEL:
				chamber[rightShrapnelPosition] = MULTI_SHRAPNEL;
				break;
			}
		}
	}

	/** Checks to see absence of bombs and shrapnel */
	private static boolean isChamberEmpty(String chamber) {
		return chamber.indexOf(BOMB) < 0 && chamber.indexOf(LEFT_SHRAPNEL) < 0
				&& chamber.indexOf(RIGHT_SHRAPNEL) < 0 && chamber.indexOf(MULTI_SHRAPNEL) < 0;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(explode("..B....", 2)));
		System.out.println(Arrays.toString(explode("..B.B..B", 10)));
		System.out.println(Arrays.toString(explode("B.B.B.BB.", 2)));
		System.out.println(Arrays.toString(explode("..B.B..B", 1)));
		System.out.println(Arrays.toString(explode("..B.BB..B.B..B...", 1)));
	}
}
