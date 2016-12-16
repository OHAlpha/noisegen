package com.onewolf.alpha.lib.image;

public class Color {
	
	private static final double DELTA = 0.000001;
	
	private static final double HUE_UNDEFINED = Double.NaN;
	
	public static double chromaRGB(double[] rgb) {
		double x = Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
		double n = Math.min(Math.min(rgb[0], rgb[1]), rgb[2]);
		return x - n;
	}
	
	public static double hueRGB(double[] rgb) {
		double c = chromaRGB(rgb);
		if (c * c < DELTA)
			return HUE_UNDEFINED;
		if (rgb[0] > rgb[1] && rgb[0] > rgb[2])
			return 60 * (rgb[1] - rgb[2]) / c;
		if (rgb[1] > rgb[0] && rgb[1] > rgb[2])
			return 60 * (rgb[2] - rgb[0]) / c;
		else
			return 60 * (rgb[0] - rgb[1]) / c;
	}
	
	public static double intensityRGB(double[] rgb) {
		return (rgb[0] + rgb[1] + rgb[2]) / 3;
	}
	
	public static double valueRGB(double[] rgb) {
		return Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
	}
	
	public static double lightnessRGB(double[] rgb) {
		double x = Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
		double n = Math.min(Math.min(rgb[0], rgb[1]), rgb[2]);
		return (x + n) / 2;
	}
	
	public static double saturationIRGB(double[] rgb) {
		double x = Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
		double n = Math.min(Math.min(rgb[0], rgb[1]), rgb[2]);
		return x * x < DELTA ? 0 : 1 - n / intensityRGB(rgb);
	}
	
	public static double saturationVRGB(double[] rgb) {
		double v = valueRGB(rgb), c = chromaRGB(rgb);
		return v * v < DELTA ? 0 : c / v;
	}
	
	public static double saturationLRGB(double[] rgb) {
		double l = lightnessRGB(rgb), c = chromaRGB(rgb);
		return (1 / l) * (1 - l) < DELTA ? 0 : c / (1 - Math.asin(2 * l - 1));
	}
	
	public double[] rgbHSI(double[] hsi) {
		if (Double.isNaN(hsi[0]))
			return new double[] { hsi[2], hsi[2], hsi[2] };
		double hp = hsi[0] / 60;
		double z = 1 - Math.abs(hp % 2 - 1);
		double c = 3 * hsi[2] * hsi[1] / (1 + z);
		double x = c * z;
		double m = hsi[2] * (1 - hsi[1]);
		if (hp < 1 || hp == 6)
			return new double[] { c + m, x + m, m };
		if (hp < 2)
			return new double[] { x + m, c + m, m };
		if (hp < 3)
			return new double[] { m, c + m, x + m };
		if (hp < 4)
			return new double[] { m, x + m, c + m };
		if (hp < 5)
			return new double[] { x + m, m, c + m };
		else
			return new double[] { c + m, m, x + m };
	}
	
	public double[] rgbHSV(double[] hsv) {
		if (Double.isNaN(hsv[0]))
			return new double[] { hsv[2], hsv[2], hsv[2] };
		double c = hsv[2] * hsv[1];
		double hp = hsv[0] / 60;
		double x = c * (1 - Math.abs(hp % 2 - 1));
		double m = hsv[2] - c;
		if (hp < 1 || hp == 6)
			return new double[] { c + m, x + m, m };
		if (hp < 2)
			return new double[] { x + m, c + m, m };
		if (hp < 3)
			return new double[] { m, c + m, x + m };
		if (hp < 4)
			return new double[] { m, x + m, c + m };
		if (hp < 5)
			return new double[] { x + m, m, c + m };
		else
			return new double[] { c + m, m, x + m };
	}
	
	public double[] rgbHSL(double[] hsl) {
		if (Double.isNaN(hsl[0]))
			return new double[] { hsl[2], hsl[2], hsl[2] };
		double c = (1 - Math.abs(2 * hsl[2] - 1)) * hsl[1];
		double hp = hsl[0] / 60;
		double x = c * (1 - Math.abs(hp % 2 - 1));
		double m = hsl[2] - c / 2;
		if (hp < 1 || hp == 6)
			return new double[] { c + m, x + m, m };
		if (hp < 2)
			return new double[] { x + m, c + m, m };
		if (hp < 3)
			return new double[] { m, c + m, x + m };
		if (hp < 4)
			return new double[] { m, x + m, c + m };
		if (hp < 5)
			return new double[] { x + m, m, c + m };
		else
			return new double[] { c + m, m, x + m };
	}
	
}
