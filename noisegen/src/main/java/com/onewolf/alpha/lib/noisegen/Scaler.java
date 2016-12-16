package com.onewolf.alpha.lib.noisegen;

public interface Scaler {
	
	public long[] scale(long[] grid, int width, int height, int scale);
	
	public int[] scale(int[] grid, int width, int height, int scale);
	
	public short[] scale(short[] grid, int width, int height, int scale);
	
	public byte[] scale(byte[] grid, int width, int height, int scale);
	
	public float[] scale(float[] grid, int width, int height, int scale);
	
	public double[] scale(double[] grid, int width, int height, int scale);
	
}
