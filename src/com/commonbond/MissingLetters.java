package com.commonbond;

public class MissingLetters {

	public static String listMissingLetters(String s) {
		char[] inputCharacters = s.toCharArray();
		boolean[] alphabetsSeen = new boolean[26];

		for (int i = 0; i < inputCharacters.length; i++) {
			char inputChar = inputCharacters[i];
			if (inputChar > 127)
				continue; // skip non ASCII characters
			if (!Character.isLetter(inputChar))
				continue;
			if (Character.isUpperCase(inputChar))
				inputChar = Character.toLowerCase(inputChar);
			alphabetsSeen[inputChar - 'a'] = true;
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < alphabetsSeen.length; i++) {
			if (alphabetsSeen[i] == false) {
				builder.append((char) (i + 'a'));
			}
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out
				.println(listMissingLetters("A quick brown fox jumps over the lazy dog"));
		System.out
				.println(listMissingLetters("Four score and seven years ago"));
		System.out
				.println(listMissingLetters("To be or not to be, that is the question!"));
		System.out.println(listMissingLetters(""));
	}
}
