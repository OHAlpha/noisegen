package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.onewolf.alpha.lib.image.Images;
import com.onewolf.alpha.lib.procedure.HilbertCurve;

public class HilbertPermuterDriver {
	
	public static void main(String[] args) throws IOException {
		// String name = args[0];
		// int width = Integer.parseInt(args[1]);
		// int height = Integer.parseInt(args[2]);
		// int scale = Integer.parseInt(args[3]);
		int p = 1 << 6;
		String name = "test-035";
		int width = (8 + 5) * p;
		int height = (8 + 2) * p;
		int scale = 128 / p;
		System.out.printf("width: %d, height: %d, scale: %d\n", width, height, scale);
		GridPermuter perm = HilbertCurve.permuter;
		// Scaler scaler = new BicubicScaler();
		int[] grad = new int[width * height];
		for (int i = 0; i < grad.length; i++) {
			long v = (long) Integer.MAX_VALUE * i / grad.length;
			v *= 2;
			v -= Integer.MAX_VALUE;
			grad[i] = (int) v;
		}
		int[] grid = new int[width * height];
		GridPermuter.applyGrid(GridPermuter.APPLY_METHOD_SET, width, height, grad, grid, perm);
		// System.out.println(Arrays.toString(grad));
		// System.out.println(Arrays.toString(grid));
		int[] pixels = Noise.grayscale(Noise.scale(grid, width, height, scale));
		// BufferedImage image = Images.createImage(pixels, (width - 1) * scale
		// + 1, (height - 1) * scale + 1);
		BufferedImage image = Images.createImage(pixels, width * scale, height * scale);
		Images.saveImage(image, String.format("image//%s-turbulence.png", name));
	}
	
}
