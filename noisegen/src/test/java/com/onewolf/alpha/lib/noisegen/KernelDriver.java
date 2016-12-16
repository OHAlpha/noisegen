package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.onewolf.alpha.lib.image.Images;

public class KernelDriver {
	
	public static void main(String[] args) throws IOException {
		String name = "test-047";
		int c = 10;
		int size = c * 2 + 1;
		int scale = 100;
		double[] impulse = new double[size * size];
		impulse[c * size + c] = 1000000;
		Convolver conv = new DefaultConvolver();
		double[] grid = Noise.softenLinear(impulse, size, size, size - 2, conv, 1);
		int[] pixels = Noise.grayscale(Noise.d2iGrid(grid));
		pixels = Noise.scale(pixels, size, size, scale);
		BufferedImage image = Images.createImage(pixels, size * scale, size * scale);
		Images.saveImage(image, String.format("image//%s-linear.png", name));
		grid = Noise.softenGaussian(impulse, size, size, 50, size, conv, 1);
		pixels = Noise.grayscale(Noise.d2iGrid(grid));
		pixels = Noise.scale(pixels, size, size, scale);
		image = Images.createImage(pixels, size * scale, size * scale);
		Images.saveImage(image, String.format("image//%s-gaussian.png", name));
	}
	
}
