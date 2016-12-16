package com.onewolf.alpha.lib.noisegen;

public class DefaultGridPermuter implements GridPermuter {
	
	@Override
	public int[] permuteGrid(int width, int height) {
		int[] out = new int[width * height];
		for (int i = 0; i < out.length; i++)
			out[i] = i;
		return out;
	}
	
	@Override
	public int[] permuteGrid2D(int width, int height) {
		int out[] = new int[2 * width * height];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				out[2 * (i * width + j)] = j;
				out[2 * (i * width + j) + 1] = i;
			}
		return out;
	}
	
}