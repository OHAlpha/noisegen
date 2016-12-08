package com.onewolf.alpha.lib.noisegen;

public class HighQualityRandomGenerator implements RandomGenerator {
    
    private HighQualityRandom hqr = new HighQualityRandom();
	
	public byte[] nextBytes(int size) {
	    byte[] num = new byte[size];
	    int n = 0;
	    while(n < size) {
	        long t = nextLong();
	        for(int i = 0; i < 8 && n < size; i++)
	            num[size - 1 - n++] = (byte) (t >> (8 * i));
	    }
	    return num;
	}
	
	public long nextLong() {
	    return hqr.nextLong();
	}
	
	public int nextInt() {
	    return hqr.next(32);
	}
	
	public short nextShort() {
	    return (short) hqr.next(16);
	}
	
	public byte nextByte() {
	    return (byte) hqr.next(8);
	}
	
}