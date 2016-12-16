package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.onewolf.alpha.lib.image.Images;
import com.onewolf.alpha.lib.linalg.ArrMat;
import com.onewolf.alpha.lib.linalg.Matrix;
import com.onewolf.alpha.lib.random.RandomGenerator;
import com.onewolf.alpha.lib.random.UniformDoubleGenerator;

public class TurbulenceDriver {

	public static void main(String[] args) throws IOException {
		String name = args[0];
		int size = Integer.parseInt(args[1]);
		int levels[] = new int[args.length - 2];
		for (int i = 2; i < args.length; i++)
			levels[i - 2] = Integer.parseInt(args[i]);
		int num = levels.length;
		int factor = num - 1;
		int power = 1 << factor;
		int mod = size % power;
		switch (mod) {
			case 0:
				size++;
				break;
			case 1:
				break;
			default:
				size += power - mod + 1;
		}
		System.out.println("size: " + size);
		System.out.println("power: " + power);
		RandomGenerator gen = new UniformDoubleGenerator();
		GridPermuter perm = new DefaultGridPermuter();
		Convolver conv = new DefaultConvolver();
		double[][] softs = new double[num][];
		int scale = power;
		Scaler scaler = new BicubicScaler();
		double weight = 0.5;
		Matrix sum = new ArrMat(size, size);
		File dir = new File("image//" + name);
		if (!dir.exists())
			dir.mkdir();
		for (int i = 0; i < num; i++) {
			int cursize = (size - 1) / scale + 1;
			System.out.println("\tscale: " + scale);
			System.out.println("\tcursize: " + cursize);
			double[] rand = Noise.randomGrid(cursize, cursize, gen, perm);
			int[] grid = Noise.grayscale(Noise.d2iGrid(rand));
			BufferedImage image = Images.createImage(grid, cursize, cursize);
			Images.saveImage(image, String.format("image//%s//rand-%02d.png", name, i));
			double[] soft = Noise.softenLinear(rand, cursize, cursize, levels[i], conv, 1);
			grid = Noise.grayscale(Noise.d2iGrid(soft));
			image = Images.createImage(grid, cursize, cursize);
			Images.saveImage(image, String.format("image//%s//soft-%02d-%04d.png", name, i, levels[i]));
			softs[i] = scaler.scale(soft, cursize, cursize, scale);
			System.out.println("\tscaled size: " + Math.sqrt(softs[i].length));
			grid = Noise.grayscale(Noise.d2iGrid(softs[i]));
			image = Images.createImage(grid, size, size);
			Images.saveImage(image, String.format("image//%s//soft-%02d-%04d-scaled.png", name, i, levels[i]));
			sum.addTo(new ArrMat(size, size, softs[i]).scale(weight));
			grid = Noise.grayscale(Noise.d2iGrid(sum.mat()));
			image = Images.createImage(grid, size, size);
			Images.saveImage(image, String.format("image//%s//soft-%02d-%04d-sum.png", name, i, levels[i]));
			scale /= 2;
			weight /= 2;
		}
	}

}
