package com.onewolf.alpha.lib.noisegen;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public interface RandomGenerator {
	
	byte[] nextBytes(int size);
	
	long nextLong();
	
	int nextInt();
	
	short nextShort();
	
	byte nextByte();
	
	default byte[] fillBytes(int size, byte[] arr) {
	    for(int i = 0; i < arr.length / size; i++) {
	        byte[] t = nextBytes(size);
	        for(int j = 0; j < size; j++)
	            arr[i*size+j] = t[j];
	    }
	    return arr;
	}
	
	default long[] fillLong(long[] arr) {
	    for(int i = 0; i < arr.length; i++) {
	        arr[i] = nextLong();
	    }
	    return arr;
	}
	
	default int[] fillInt(int[] arr) {
	    for(int i = 0; i < arr.length; i++) {
	        arr[i] = nextInt();
	    }
	    return arr;
	}
	
	default short[] fillShort(short[] arr) {
	    for(int i = 0; i < arr.length; i++) {
	        arr[i] = nextShort();
	    }
	    return arr;
	}
	
	default byte[] fillByte(byte[] arr) {
	    for(int i = 0; i < arr.length; i++) {
	        arr[i] = nextByte();
	    }
	    return arr;
	}
	
	default byte[] bulkBytes(int size, int length) {
	    byte[] arr = new byte[length * size];
	    return fillBytes(size, arr);
	}
	
	default long[] bulkLong(int length) {
	    long[] arr = new long[length];
	    return fillLong(arr);
	}
	
	default int[] bulkInt(int length) {
	    int[] arr = new int[length];
	    return fillInt(arr);
	}
	
	default short[] bulkShort(int length) {
	    short[] arr = new short[length];
	    return fillShort(arr);
	}
	
	default byte[] bulkByte(int length) {
	    byte[] arr = new byte[length];
	    return fillByte(arr);
	}
	
	default Stream bytesStream(int size) {
	    return Stream.generate(() -> this.nextBytes(size));
	}
	
	default LongStream longStream(long[] arr) {
	    return LongStream.generate(() -> this.nextLong());
	}
	
	default IntStream intStream(int[] arr) {
	    return IntStream.generate(() -> this.nextInt());
	}
	
	default IntStream shortStream(short[] arr) {
	    return IntStream.generate(() -> this.nextShort());
	}
	
	default IntStream byteStream(byte[] arr) {
	    return IntStream.generate(() -> this.nextByte());
	}
	
}