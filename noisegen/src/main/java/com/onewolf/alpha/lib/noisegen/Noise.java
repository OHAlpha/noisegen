package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.onewolf.alpha.lib.image.Images;
import com.onewolf.alpha.lib.linalg.ArrMat;
import com.onewolf.alpha.lib.linalg.Matrix;
import com.onewolf.alpha.lib.random.RandomGenerator;

public class Noise {
	
	public static double[] randomGrid(int width, int height, RandomGenerator gen, GridPermuter perm) {
		double[] grid = new double[width * height];
		double[] rand = gen.bulkDouble(width * height);
		GridPermuter.applyGrid(GridPermuter.APPLY_METHOD_SET, width, height, rand, grid, perm);
		return grid;
	}
	
	public static double[][] randomGrid2D(int width, int height, RandomGenerator gen, GridPermuter perm) {
		double[][] grid = new double[height][width];
		double[] rand = gen.bulkDouble(width * height);
		GridPermuter.applyGrid2D(GridPermuter.APPLY_METHOD_SET, width, height, rand, grid, perm);
		return grid;
	}
	
	public static double[] softenLinear(double[] grid, int width, int height, Convolver conv, int method) {
		switch (method) {
			case 0:
				return conv.convolveIn(grid, width, height, new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 3, 3);
			case 1:
				return conv.convolveAround(grid, width, height, new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 3, 3);
			case 2:
				return conv.convolveOut(grid, width, height, new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 3, 3);
		}
		return null;
	}
	
	public static double[] softenLinear(double[] grid, int width, int height, int levels, Convolver conv, int method) {
		double[] kernel = softenKernel(levels);
		switch (method) {
			case 0:
				return conv.convolveIn(grid, width, height, kernel, levels + 2, levels + 2);
			case 1:
				return conv.convolveAround(grid, width, height, kernel, levels + 2, levels + 2);
			case 2:
				return conv.convolveOut(grid, width, height, kernel, levels + 2, levels + 2);
		}
		return null;
	}
	
	public static double[] softenGaussian(double[] grid, int width, int height, double theta, int size, Convolver conv,
			int method) {
		double[] kernel = gaussian(theta, size);
		switch (method) {
			case 0:
				return conv.convolveIn(grid, width, height, kernel, size, size);
			case 1:
				return conv.convolveAround(grid, width, height, kernel, size, size);
			case 2:
				return conv.convolveOut(grid, width, height, kernel, size, size);
		}
		return null;
	}
	
	/**
	 * Calculates the discrete value at x,y of the 2D gaussian distribution.
	 *
	 * @param theta
	 *            the theta value for the gaussian distribution
	 * @param x
	 *            the point at which to calculate the discrete value
	 * @param y
	 *            the point at which to calculate the discrete value
	 * @return the discrete gaussian value
	 */
	public static double gaussianDiscrete2D(double theta, int x, int y) {
		double g = 0;
		for (double ySubPixel = y - 0.5; ySubPixel < y + 0.55; ySubPixel += 0.1)
			for (double xSubPixel = x - 0.5; xSubPixel < x + 0.55; xSubPixel += 0.1)
				g = g + 1 / (2 * Math.PI * theta * theta)
						* Math.pow(Math.E, -(xSubPixel * xSubPixel + ySubPixel * ySubPixel) / (2 * theta * theta));
		g = g / 121;
		// System.out.println(g);
		return g;
	}
	
	/**
	 * Calculates several discrete values of the 2D gaussian distribution.
	 *
	 * @param theta
	 *            the theta value for the gaussian distribution
	 * @param size
	 *            the number of discrete values to calculate (pixels)
	 * @return 2Darray (size*size) containing the calculated discrete values
	 */
	public static double[][] gaussian2D(double theta, int size) {
		double[][] kernel = new double[size][size];
		for (int j = 0; j < size; ++j)
			for (int i = 0; i < size; ++i)
				kernel[i][j] = gaussianDiscrete2D(theta, i - size / 2, j - size / 2);
			
		double sum = 0;
		for (int j = 0; j < size; ++j)
			for (int i = 0; i < size; ++i)
				sum = sum + kernel[i][j];
			
		return kernel;
	}
	
	public static double[] gaussian(double theta, int size) {
		double[] kernel = new double[size * size];
		for (int j = 0; j < size; ++j)
			for (int i = 0; i < size; ++i)
				kernel[i * size + j] = gaussianDiscrete2D(theta, i - size / 2, j - size / 2);
			
		double sum = 0;
		for (int j = 0; j < size; ++j)
			for (int i = 0; i < size; ++i)
				sum = sum + kernel[i * size + j];
			
		return kernel;
	}
	
	public static double[] softenKernel(int levels) {
		switch (levels) {
			case 1:
				return new double[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			case 2:
				return new double[] { 1, 2, 2, 1, 2, 4, 4, 2, 2, 4, 4, 2, 1, 2, 2, 1 };
			case 3:
				return new double[] { 1, 2, 3, 2, 1, 2, 4, 6, 4, 2, 3, 6, 9, 6, 3, 2, 4, 6, 4, 2, 1, 2, 3, 2, 1 };
			default:
				int s = levels + 2;
				double[] out = new double[s * s];
				for (int i = 0; i < s; i++)
					for (int j = 0; j < s; j++) {
						int I, J;
						if (i < 2)
							I = i + 1;
						else if (i > s - 3)
							I = s - 1 - i;
						else
							I = 3;
						if (j < 2)
							J = j + 1;
						else if (j > s - 3)
							J = s - 1 - j;
						else
							J = 3;
						out[j * s + i] = I * J;
					}
				return out;
		}
	}
	
	public static int[] scale(int[] grid, int width, int height, int scale) {
		int W = width * scale;
		int H = height * scale;
		int[] out = new int[width * height * scale * scale];
		/*
		 * for(int x = 0; x < width; x++) for(int y = 0; y < height; y++)
		 * for(int i = 0; i < scale; i++) for(int j = 0; j < scale; j++)
		 * out[(y*scale+j)*W+x*scale+i] = grid[y*width+x];
		 */
		for (int i = 0; i < W; i++)
			for (int j = 0; j < H; j++)
				out[j * W + i] = grid[j / scale * width + i / scale];
		return out;
		
	}
	
	public static double[] weightedSum(int n, double[][] grid, double[] factors, double factor) {
		double[] out = new double[grid[0].length];
		for (int i = 0; i < out.length; i++) {
			long sum = 0;
			for (int j = 0; j < n; j++)
				sum += factor * factors[j] * grid[j][i];
			out[i] = sum / grid.length;
		}
		return out;
	}
	
	public static int[] d2iGrid(double[] grid) {
		double max = max(grid), min = min(grid), range = max - min, mean = (max + min) / 2, radius = range / 2;
		int[] out = new int[grid.length];
		for (int i = 0; i < grid.length; i++)
			out[i] = (int) (Integer.MAX_VALUE * (grid[i] - mean) / radius);
		return out;
	}
	
	private static double min(double[] arr) {
		double min = arr[0];
		for (double e : arr)
			min = Math.min(min, e);
		return min;
	}
	
	private static double max(double[] arr) {
		double max = arr[0];
		for (double e : arr)
			max = Math.max(max, e);
		return max;
	}
	
	public static int[] grayscale(int[] grid) {
		for (int i = 0; i < grid.length; i++) {
			int gray = (int) ((0.5 * grid[i] / Integer.MAX_VALUE + 0.5) * 255);
			if (gray > 255)
				gray = 255;
			else if (gray < 0)
				gray = 0;
			grid[i] = 0xff000000 | (gray & 0xff) << 16 | (gray & 0xff) << 8 | gray & 0xff;
		}
		return grid;
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[] kernel,
			int kernelSize, Scaler scaler, int size, int levels) {
		return turbulence(gen, perm, conv, kernel, kernelSize, kernelSize, scaler, size, size, levels);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[][] kernels,
			int[] kernelSize, Scaler scaler, int size) {
		return turbulence(gen, perm, conv, kernels, kernelSize, kernelSize, scaler, size, size);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[] kernel,
			int kernelSize, Scaler scaler, int width, int height, int levels) {
		return turbulence(gen, perm, conv, kernel, kernelSize, kernelSize, scaler, width, height, levels);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[][] kernels,
			int[] kernelSize, Scaler scaler, int width, int height) {
		return turbulence(gen, perm, conv, kernels, kernelSize, kernelSize, scaler, width, height);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[] kernel,
			int kernelWidth, int kernelHeight, Scaler scaler, int size, int levels) {
		return turbulence(gen, perm, conv, kernel, kernelWidth, kernelHeight, scaler, size, size, levels);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[][] kernels,
			int[] kernelWidth, int[] kernelHeight, Scaler scaler, int size) {
		return turbulence(gen, perm, conv, kernels, kernelWidth, kernelHeight, scaler, size, size);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[] kernel,
			int kernelWidth, int kernelHeight, Scaler scaler, int width, int height, int levels) {
		double[][] kernels = new double[levels][];
		for (int i = 0; i < levels; i++)
			kernels[i] = kernel;
		int[] kernelWidthA = new int[levels];
		int[] kernelHeightA = new int[levels];
		Arrays.fill(kernelWidthA, kernelWidth);
		Arrays.fill(kernelHeightA, kernelHeight);
		return turbulence(gen, perm, conv, kernels, kernelWidthA, kernelHeightA, scaler, width, height);
	}
	
	public static BufferedImage turbulence(RandomGenerator gen, GridPermuter perm, Convolver conv, double[][] kernels,
			int[] kernelWidth, int[] kernelHeight, Scaler scaler, int width, int height) {
		int num = kernels.length;
		int factor = num - 1;
		int power = 1 << factor;
		System.out.printf("factor: %d, power: %d\n", factor, power);
		if (width < power + 1) {
			width = power + 1;
			System.out.printf("size < power + 1 => size: %d\n", width);
		} else {
			int mod = width % power;
			switch (mod) {
				case 0:
					width++;
					System.out.printf("size = 0 mod power => size: %d\n", width);
					break;
				case 1:
					System.out.printf("size is acceptable\n");
					break;
				default:
					width += power - mod + 1;
					System.out.printf("size > 1 mod power => size: %d\n", width);
			}
		}
		if (height < power + 1) {
			height = power + 1;
			System.out.printf("size < power + 1 => size: %d\n", height);
		} else {
			int mod = height % power;
			switch (mod) {
				case 0:
					height++;
					System.out.printf("size = 0 mod power => size: %d\n", height);
					break;
				case 1:
					System.out.printf("size is acceptable\n");
					break;
				default:
					height += power - mod + 1;
					System.out.printf("size > 1 mod power => size: %d\n", height);
			}
		}
		int scale = power;
		double weight = 0.5;
		Matrix sum = new ArrMat(width, height);
		for (int i = 0; i < num; i++) {
			int curwidth = (width - 1) / scale + 1;
			int curheight = (height - 1) / scale + 1;
			double[] grid = Noise.randomGrid(curwidth, curheight, gen, perm);
			// grid = Noise.softenLinear(grid, curwidth, curheight,
			// softenLevels[i], conv, 1);
			grid = conv.convolveAround(grid, curwidth, curheight, kernels[i], kernelWidth[i], kernelHeight[i]);
			grid = scaler.scale(grid, curwidth, curheight, scale);
			sum.addTo(new ArrMat(width, height, grid).scale(weight));
			scale /= 2;
			weight /= 2;
		}
		int[] pixels = Noise.grayscale(Noise.d2iGrid(sum.mat()));
		return Images.createImage(pixels, width, height);
	}
	
}
