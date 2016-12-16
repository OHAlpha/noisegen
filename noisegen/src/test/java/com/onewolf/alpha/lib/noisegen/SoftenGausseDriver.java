package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.onewolf.alpha.lib.image.Images;
import com.onewolf.alpha.lib.random.RandomGenerator;
import com.onewolf.alpha.lib.random.UniformDoubleGenerator;

public class SoftenGausseDriver {
	
	public static void main(String[] args) throws IOException {
		// String name = args[0];
		// int size = Integer.parseInt(args[1]);
		// int width = size, height = size;
		// double theta = Double.parseDouble(args[1]);
		// int levels[] = new int[args.length - 3];
		// for (int i = 3; i < args.length; i++)
		// levels[i - 3] = Integer.parseInt(args[i]);
		String name = "test-045";
		int size = 500;
		int width = size, height = size;
		int levels[] = new int[] { 1, 2, 3, 5, 10, 20, 50 };
		RandomGenerator gen = new UniformDoubleGenerator();
		GridPermuter perm = new DefaultGridPermuter();
		double[] rand = Noise.randomGrid(width, height, gen, perm);
		// System.out.println(java.util.Arrays.toString(Arrays.subArray(rand, 0,
		// 1, 100)));
		int[] grid = Noise.d2iGrid(rand);
		// System.out.println(java.util.Arrays.toString(Arrays.subArray(grid, 0,
		// 1, 100)));
		grid = Noise.grayscale(grid);
		// System.out.println(java.util.Arrays.toString(Arrays.subArray(grid, 0,
		// 1, 100)));
		BufferedImage image = Images.createImage(grid, width, height);
		File dir = new File(String.format("image//%s", name));
		if (!dir.exists())
			dir.mkdirs();
		Images.saveImage(image, String.format("image//%s//rand.png", name));
		Convolver conv = new DefaultConvolver();
		for (int i : levels) {
			double[] kernel = Noise.gaussian(1.0 * i * i / 2, i);
			double[] soft = conv.convolveAround(rand, width, height, kernel, i, i);
			// double[] soft = Noise.softenGaussian(rand, width, height, 1.0 * i
			// * i / 2, i, conv, 1);
			grid = Noise.grayscale(Noise.d2iGrid(soft));
			image = Images.createImage(grid, width, height);
			Images.saveImage(image, String.format("image//%s//soft-%04d.png", name, i));
		}
	}
	
}
