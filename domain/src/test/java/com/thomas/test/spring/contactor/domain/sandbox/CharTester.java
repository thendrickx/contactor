package com.thomas.test.spring.contactor.domain.sandbox;

public class CharTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte character = 45;

		String s = new String(new byte[] { character });

		System.out.println(s);

	}

}
