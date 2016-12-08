package com.onewolf.alpha.lib.noisegen;

public class Noise {
	
	public static int[] randomGrid(int width, int height, RandomGenerator gen, GridPermuter perm) {
	    int[] grid = new int[width*height];
	    int[] rand = gen.bulkInt(width*height);
	    GridPermuter.applyGrid(GridPermuter.APPLY_METHOD_SET, width, height, rand, grid, perm);
	    return grid;
	}
	
	public static int[][] randomGrid2D(int width, int height, RandomGenerator gen, GridPermuter perm) {
	    int[][] grid = new int[height][width];
	    int[] rand = gen.bulkInt(width*height);
	    GridPermuter.applyGrid2D(GridPermuter.APPLY_METHOD_SET, width, height, rand, grid, perm);
	    return grid;
	}
	
}
