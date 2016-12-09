package com.onewolf.alpha.lib.noisegen;

public class SinPhaseRandomGenerator implements RandomGenerator {
    
    private RandomGenerator gen;
    
    private double step;
    
    private double amplitude;
    
    private double frequency;
    
    private double factor;
    
    private double t = 0;
    
    private int index;
    
    double[] buffer;
    
    public SinPhaseRandomGenerator(RandomGenerator gen, double step, double amplitude, double frequency, double factor, int buffer_size) {
        this.gen = gen;
        this.step = step;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.factor = factor;
        this.buffer = new double[buffer_size];
        index = buffer_size;
        fillBuffer();
        index = 0;
    }
    
    protected void fillBuffer() {
        for(int i = 0; i < index; i++) {
            buffer[i] = amplitude * Math.sin(frequency*(t+factor*gen.nextLong()/Long.MAX_VALUE));
            t += step;
        }
    }
    
    protected void checkIndex() {
        if(index == buffer.length) {
            fillBuffer();
            index = 0;
        }
    }
	
	public byte[] nextBytes(int size) {
	    throw new RuntimeException("not implemented");
	}
	
	public long nextLong() {
	    long out = (long) (Long.MAX_VALUE * buffer[index++]);
	    checkIndex();
	    return out;
	}
	
	public int nextInt() {
	    int out = (int) (Integer.MAX_VALUE * buffer[index++]);
	    checkIndex();
	    return out;
	}
	
	public short nextShort() {
	    short out = (short) (Short.MAX_VALUE * buffer[index++]);
	    checkIndex();
	    return out;
	}
	
	public byte nextByte() {
	    byte out = (byte) (Byte.MAX_VALUE * buffer[index++]);
	    checkIndex();
	    return out;
	}
	
}