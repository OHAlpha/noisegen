package com.onewolf.alpha.lib.random;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public interface RandomGenerator {

	byte[] nextBytes(int size);

	long nextLong();

	int nextInt();

	short nextShort();

	byte nextByte();

	float nextFloat();

	double nextDouble();

	default byte[] fill(int size, byte[] arr) {
		for (int i = 0; i < arr.length / size; i++) {
			byte[] t = nextBytes(size);
			for (int j = 0; j < size; j++)
				arr[i * size + j] = t[j];
		}
		return arr;
	}

	default long[] fill(long[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = nextLong();
		return arr;
	}

	default int[] fill(int[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = nextInt();
		return arr;
	}

	default short[] fill(short[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = nextShort();
		return arr;
	}

	default byte[] fill(byte[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = nextByte();
		return arr;
	}

	default float[] fill(float[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = nextFloat();
		return arr;
	}

	default double[] fill(double[] arr) {
		for (int i = 0; i < arr.length; i++)
			arr[i] = nextDouble();
		return arr;
	}

	default byte[] bulkBytes(int size, int length) {
		byte[] arr = new byte[length * size];
		return fill(size, arr);
	}

	default long[] bulkLong(int length) {
		long[] arr = new long[length];
		return fill(arr);
	}

	default int[] bulkInt(int length) {
		int[] arr = new int[length];
		return fill(arr);
	}

	default short[] bulkShort(int length) {
		short[] arr = new short[length];
		return fill(arr);
	}

	default byte[] bulkByte(int length) {
		byte[] arr = new byte[length];
		return fill(arr);
	}

	default float[] bulkFloat(int length) {
		float[] arr = new float[length];
		return fill(arr);
	}

	default double[] bulkDouble(int length) {
		double[] arr = new double[length];
		return fill(arr);
	}

	default Stream<byte[]> bytesStream(int size) {
		return Stream.generate(() -> nextBytes(size));
	}

	default LongStream longStream(long[] arr) {
		return LongStream.generate(() -> nextLong());
	}

	default IntStream intStream(int[] arr) {
		return IntStream.generate(() -> nextInt());
	}

	default IntStream shortStream(short[] arr) {
		return IntStream.generate(() -> nextShort());
	}

	default IntStream byteStream(byte[] arr) {
		return IntStream.generate(() -> nextByte());
	}

	default Stream<Float> floatStream(float[] arr) {
		return Stream.generate(() -> nextFloat());
	}

	default DoubleStream doubleStream(double[] arr) {
		return DoubleStream.generate(() -> nextDouble());
	}

}