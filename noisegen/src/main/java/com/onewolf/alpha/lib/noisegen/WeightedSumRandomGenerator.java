package com.onewolf.alpha.lib.noisegen;

public class WeightedSumRandomGenerator implements RandomGenerator {
    
    private RandomGenerator[] gen;
    
    private double[] weight;
    
    private int size;
    
    public WeightedSumRandomGenerator(RandomGenerator[] gen, double[] weight ) {
        this.gen = gen;
        this.weight = weight;
        this.size = Math.min(gen.length,weight.length);
    }
	
	public byte[] nextBytes(int size) {
	    throw new RuntimeException("not implemented");
	}
	
	public long nextLong() {
	    double out = 0;
	    for(int i = 0; i < size; i++)
	        out += weight[i] * gen[i].nextLong()/Long.MAX_VALUE;
	    return (long) (out * Long.MAX_VALUE);
	}
	
	public int nextInt() {
	    double out = 0;
	    for(int i = 0; i < size; i++)
	        out += weight[i] * gen[i].nextLong()/Long.MAX_VALUE;
	    return (int) (out * Integer.MAX_VALUE);
	}
	
	public short nextShort() {
	    double out = 0;
	    for(int i = 0; i < size; i++)
	        out += weight[i] * gen[i].nextLong()/Long.MAX_VALUE;
	    return (short) (out * Short.MAX_VALUE);
	}
	
	public byte nextByte() {
	    double out = 0;
	    for(int i = 0; i < size; i++)
	        out += weight[i] * gen[i].nextLong()/Long.MAX_VALUE;
	    return (byte) (out * Byte.MAX_VALUE);
	}
	
}