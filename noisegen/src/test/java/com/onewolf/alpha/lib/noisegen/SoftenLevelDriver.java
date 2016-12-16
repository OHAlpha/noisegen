package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.onewolf.alpha.lib.image.Images;
import com.onewolf.alpha.lib.random.RandomGenerator;
import com.onewolf.alpha.lib.random.UniformDoubleGenerator;

public class SoftenLevelDriver {

	public static void main(String[] args) throws IOException {
		String name = args[0];
		int size = Integer.parseInt(args[1]);
		int width = size, height = size;
		int levels[] = new int[args.length - 2];
		for (int i = 2; i < args.length; i++)
			levels[i - 2] = Integer.parseInt(args[i]);
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
		Images.saveImage(image, name + "-rand.png");
		Convolver conv = new DefaultConvolver();
		for (int i : levels) {
			double[] soft = Noise.softenLinear(rand, width, height, i, conv, 1);
			grid = Noise.grayscale(Noise.d2iGrid(soft));
			image = Images.createImage(grid, width, height);
			Images.saveImage(image, String.format("%s-soft-%04d.png", name, i));
		}
	}

}
