package com.onewolf.alpha.lib.noisegen;

public interface GridPermuter {

	public static final int APPLY_METHOD_SET = 0;

	public static final int APPLY_METHOD_ADD = 1;

	int[] permuteGrid(int width, int height);

	int[] permuteGrid2D(int width, int height);

	public static void applyGrid(int method, int width, int height, int[] source, int[] dest, GridPermuter perm) {
		int[] order = perm.permuteGrid(width, height);
		for (int i = 0; i < order.length; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[i]] += source[i];
					break;
			}
	}

	public static void applyGrid2D(int method, int width, int height, int[] source, int[][] dest, GridPermuter perm) {
		int[] order = perm.permuteGrid2D(width, height);
		for (int i = 0; i < width * height; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[2 * i + 1]][order[2 * i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[2 * i + 1]][order[2 * i]] += source[i];
					break;
			}
	}

	public static void applyGrid(int method, int width, int height, float[] source, float[] dest, GridPermuter perm) {
		int[] order = perm.permuteGrid(width, height);
		for (int i = 0; i < order.length; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[i]] += source[i];
					break;
			}
	}

	public static void applyGrid(int method, int width, int height, long[] source, long[] dest, GridPermuter perm) {
		int[] order = perm.permuteGrid(width, height);
		for (int i = 0; i < order.length; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[i]] += source[i];
					break;
			}
	}

	public static void applyGrid2D(int method, int width, int height, long[] source, long[][] dest, GridPermuter perm) {
		int[] order = perm.permuteGrid2D(width, height);
		for (int i = 0; i < width * height; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[2 * i + 1]][order[2 * i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[2 * i + 1]][order[2 * i]] += source[i];
					break;
			}
	}

	public static void applyGrid2D(int method, int width, int height, float[] source, float[][] dest,
			GridPermuter perm) {
		int[] order = perm.permuteGrid2D(width, height);
		for (int i = 0; i < width * height; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[2 * i + 1]][order[2 * i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[2 * i + 1]][order[2 * i]] += source[i];
					break;
			}
	}

	public static void applyGrid(int method, int width, int height, double[] source, double[] dest, GridPermuter perm) {
		int[] order = perm.permuteGrid(width, height);
		for (int i = 0; i < order.length; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[i]] += source[i];
					break;
			}
	}

	public static void applyGrid2D(int method, int width, int height, double[] source, double[][] dest,
			GridPermuter perm) {
		int[] order = perm.permuteGrid2D(width, height);
		for (int i = 0; i < width * height; i++)
			switch (method) {
				case APPLY_METHOD_SET:
					dest[order[2 * i + 1]][order[2 * i]] = source[i];
					break;
				case APPLY_METHOD_ADD:
					dest[order[2 * i + 1]][order[2 * i]] += source[i];
					break;
			}
	}

}