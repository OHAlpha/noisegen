package com.onewolf.alpha.lib.random;

public class HighQualityGenerator implements RandomGenerator {

	private HighQualityRandom hqr = new HighQualityRandom();

	@Override
	public byte[] nextBytes(int size) {
		byte[] num = new byte[size];
		int n = 0;
		while (n < size) {
			long t = nextLong();
			for (int i = 0; i < 8 && n < size; i++)
				num[size - 1 - n++] = (byte) (t >> 8 * i);
		}
		return num;
	}

	@Override
	public long nextLong() {
		return hqr.nextLong();
	}

	@Override
	public int nextInt() {
		return hqr.next(32);
	}

	@Override
	public short nextShort() {
		return (short) hqr.next(16);
	}

	@Override
	public byte nextByte() {
		return (byte) hqr.next(8);
	}

	@Override
	public float nextFloat() {
		return Float.intBitsToFloat(hqr.next(16));
	}

	@Override
	public double nextDouble() {
		return Double.longBitsToDouble(hqr.nextLong());
	}

}