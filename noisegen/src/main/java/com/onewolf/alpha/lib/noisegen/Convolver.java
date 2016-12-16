package com.onewolf.alpha.lib.noisegen;

public interface Convolver {

	double[] convolveAround(double[] grid, int width, int height, double[] kernel, int kernelwidth, int kernelHeight);

	double[] convolveOut(double[] grid, int width, int height, double[] kernel, int kernelwidth, int kernelHeight);

	double[] convolveIn(double[] grid, int width, int height, double[] kernel, int kernelwidth, int kernelHeight);

}
