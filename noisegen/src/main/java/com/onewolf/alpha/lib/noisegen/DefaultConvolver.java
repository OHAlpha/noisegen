package com.onewolf.alpha.lib.noisegen;

import com.onewolf.alpha.lib.util.Arrays;

public class DefaultConvolver implements Convolver {
	
	@Override
	public double[] convolveAround(double[] grid, int width, int height, double[] kernel, int kernelWidth,
			int kernelHeight) {
		double kernelSize = Arrays.sum(kernel);
		int kw = (kernelWidth - 1) / 2, kh = (kernelHeight - 1) / 2;
		double[] out = new double[width * height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				double o = 0;
				for (int k = 0; k < kernelWidth; k++)
					for (int l = 0; l < kernelHeight; l++) {
						int kx = i + k - kw;
						int ky = j + l - kh;
						o += grid[(ky + height) % height * width + (kx + width) % width] * kernel[l * kernelWidth + k];
					}
				out[j * width + i] = o / kernelSize;
			}
		return out;
	}
	
	@Override
	public double[] convolveOut(double[] grid, int width, int height, double[] kernel, int kernelWidth,
			int kernelHeight) {
		double kernelSize = Arrays.sum(kernel);
		// int kw = (kernelWidth - 1) / 2, kh = (kernelHeight - 1) / 2;
		int W = width + kernelWidth - 1, H = height + kernelHeight - 1;
		double[] out = new double[W * H];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				double g = grid[j * width + i];
				for (int k = 0; k < kernelWidth; k++)
					for (int l = 0; l < kernelHeight; l++)
						out[(j + l) * W + i + k] += g * kernel[l * kernelWidth + k] / kernelSize;
			}
		return out;
	}
	
	@Override
	public double[] convolveIn(double[] grid, int width, int height, double[] kernel, int kernelWidth,
			int kernelHeight) {
		double kernelSize = Arrays.sum(kernel);
		int kw = (kernelWidth - 1) / 2, kh = (kernelHeight - 1) / 2;
		int W = width - kernelWidth + 1, H = height - kernelHeight + 1;
		double[] out = new double[W * H];
		for (int i = 0; i < W; i++)
			for (int j = 0; j < H; j++) {
				double o = 0;
				for (int k = 0; k < kernelWidth; k++)
					for (int l = 0; l < kernelHeight; l++)
						o += grid[(j + l - kh) * width + i + k - kw] * kernel[l * kernelWidth + k];
				out[j * width + i] = o / kernelSize;
			}
		return out;
	}
	
}
