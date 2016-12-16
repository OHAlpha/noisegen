package com.onewolf.alpha.lib.util;

public class Bits {

	public static byte highBit(byte n) {
		byte a = n, b = (byte) (a & a - 1);
		while (b != 0) {
			a = b;
			b = (byte) (a & a - 1);
		}
		return a;
	}

	public static byte lowBit(byte n) {
		return (byte) ((n ^ n - 1) + 1 >> 1);
	}

	public static short highBit(short n) {
		short a = n, b = (short) (a & a - 1);
		while (b != 0) {
			a = b;
			b = (short) (a & a - 1);
		}
		return a;
	}

	public static short lowBit(short n) {
		return (short) ((n ^ n - 1) + 1 >> 1);
	}

	public static int highBit(int n) {
		int a = n, b = a & a - 1;
		while (b != 0) {
			a = b;
			b = a & a - 1;
		}
		return a;
	}

	public static int lowBit(int n) {
		return (n ^ n - 1) + 1 >> 1;
	}

	public static long highBit(long n) {
		long a = n, b = a & a - 1;
		while (b != 0) {
			a = b;
			b = a & a - 1;
		}
		return a;
	}

	public static long lowBit(long n) {
		return (n ^ n - 1) + 1 >> 1;
	}

}
