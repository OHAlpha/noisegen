package com.onewolf.alpha.lib.random;

import java.util.Random;

public class UniformDoubleGenerator implements RandomGenerator {

	Random r = new Random();

	@Override
	public byte[] nextBytes(int size) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public long nextLong() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public int nextInt() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public short nextShort() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public byte nextByte() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public float nextFloat() {
		return r.nextFloat();
	}

	@Override
	public double nextDouble() {
		return r.nextDouble();
	}

}
