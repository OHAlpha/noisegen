package com.onewolf.alpha.lib.random;

public class WeightedSumGenerator implements RandomGenerator {

	private RandomGenerator[] gen;

	private double[] weight;

	private int size;

	public WeightedSumGenerator(RandomGenerator[] gen, double[] weight) {
		this.gen = gen;
		this.weight = weight;
		size = Math.min(gen.length, weight.length);
	}

	@Override
	public byte[] nextBytes(int size) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long nextLong() {
		double out = 0;
		for (int i = 0; i < size; i++)
			out += weight[i] * gen[i].nextLong() / Long.MAX_VALUE;
		return (long) (out / weight.length * Long.MAX_VALUE);
	}

	@Override
	public int nextInt() {
		double out = 0;
		for (int i = 0; i < size; i++)
			out += weight[i] * gen[i].nextLong() / Long.MAX_VALUE;
		return (int) (out / weight.length * Integer.MAX_VALUE);
	}

	@Override
	public short nextShort() {
		double out = 0;
		for (int i = 0; i < size; i++)
			out += weight[i] * gen[i].nextLong() / Long.MAX_VALUE;
		return (short) (out / weight.length * Short.MAX_VALUE);
	}

	@Override
	public byte nextByte() {
		double out = 0;
		for (int i = 0; i < size; i++)
			out += weight[i] * gen[i].nextLong() / Long.MAX_VALUE;
		return (byte) (out / weight.length * Byte.MAX_VALUE);
	}

	@Override
	public float nextFloat() {
		double out = 0;
		for (int i = 0; i < size; i++)
			out += weight[i] * gen[i].nextLong() / Long.MAX_VALUE;
		return (float) out / weight.length;
	}

	@Override
	public double nextDouble() {
		double out = 0;
		for (int i = 0; i < size; i++)
			out += weight[i] * gen[i].nextLong() / Long.MAX_VALUE;
		return out / weight.length;
	}

}