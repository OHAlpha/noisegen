package com.onewolf.alpha.lib.noisegen;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.onewolf.alpha.lib.image.Images;
import com.onewolf.alpha.lib.random.*;

public class TurbulenceNoiseDriver {
	
	public static void main(String[] args) throws IOException {
		// String name = args[0];
		// int size = Integer.parseInt(args[1]);
		// int levels = Integer.parseInt(args[2]);
		// int softenLevel = Integer.parseInt(args[3]);
		String name = "test-048";
		int size = 1000;
		int levels = 4;
		int softenLevel = 5;
		System.out.printf("size: %d, levels: %d, softenLevel: %d\n", size, levels, softenLevel);
		RandomGenerator gen = new SinePhaseGenerator(new HighQualityGenerator(), .1, 1000, 1.0, 3, 1024);
		// RandomGenerator gen = new UniformDoubleGenerator();
		GridPermuter perm = new DefaultGridPermuter();
		// GridPermuter perm = HilbertCurve.permuter;
		Convolver conv = new DefaultConvolver();
		Scaler scaler = new BicubicScaler();
		double[] kernel = Noise.gaussian(1.0 * softenLevel * softenLevel / 2, softenLevel);
		BufferedImage image = Noise.turbulence(gen, perm, conv, kernel, softenLevel, scaler, size, levels);
		Images.saveImage(image, String.format("image//%s-turbulence.png", name));
	}
	
}
