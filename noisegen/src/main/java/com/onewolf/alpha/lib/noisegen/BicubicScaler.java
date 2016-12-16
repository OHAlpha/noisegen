package com.onewolf.alpha.lib.noisegen;

import com.onewolf.alpha.lib.linalg.ArrMat;
import com.onewolf.alpha.lib.linalg.Matrix;
import com.onewolf.alpha.lib.util.Arrays;

import flanagan.interpolation.BiCubicInterpolation;

class CubicInterpolator {
	public double getValue(double[] p, double x) {
		return p[1] + 0.5 * x * (p[2] - p[0]
				+ x * (2.0 * p[0] - 5.0 * p[1] + 4.0 * p[2] - p[3] + x * (3.0 * (p[1] - p[2]) + p[3] - p[0])));
	}
}

class BicubicInterpolator extends CubicInterpolator {
	private double[] arr = new double[4];
	
	public double getValue(double[][] p, double x, double y) {
		arr[0] = super.getValue(p[0], y);
		arr[1] = super.getValue(p[1], y);
		arr[2] = super.getValue(p[2], y);
		arr[3] = super.getValue(p[3], y);
		return super.getValue(arr, x);
	}
	
	public double getValue(double[] p, double x, double y) {
		arr[0] = super.getValue(Arrays.subArray(p, 0, 1, 4), y);
		arr[1] = super.getValue(Arrays.subArray(p, 4, 1, 4), y);
		arr[2] = super.getValue(Arrays.subArray(p, 8, 1, 4), y);
		arr[3] = super.getValue(Arrays.subArray(p, 12, 1, 4), y);
		return super.getValue(arr, x);
	}
}

public class BicubicScaler implements Scaler {
	
	protected static final BicubicInterpolator bcip = new BicubicInterpolator();
	
	protected static final Matrix pre = new ArrMat(4, 4,
			new double[] { 1, 0, 0, 0, 0, 0, 1, 0, -3, 3, -2, -1, 2, -2, 1, 1 });
	
	protected static final Matrix post = new ArrMat(4, 4,
			new double[] { 1, 0, -3, 2, 0, 0, 3, -2, 0, 1, -2, 1, 0, 0, -1, 1 });
	
	public static Matrix coeff(Matrix f, Matrix fx, Matrix fy, Matrix fxy) {
		Matrix F = new ArrMat(4, 4);
		F.sub(0, 0, 2, 2, f.mat());
		F.sub(2, 0, 2, 2, fx.mat());
		F.sub(0, 2, 2, 2, fy.mat());
		F.sub(2, 2, 2, 2, fxy.mat());
		return pre.multiply(F).multiply(post);
	}
	
	public static double at(Matrix coeff, double x, double y) {
		return new ArrMat(1, 4, new double[] { 1, x, x * x, x * x * x }).multiply(coeff)
				.multiply(new ArrMat(4, 1, new double[] { 1, y, y * y, y * y * y })).at(0, 0);
	}
	
	public static void interpolate(Matrix grid, Matrix f, Matrix fx, Matrix fy, Matrix fxy) {
		int width = grid.n(), height = grid.m();
		Matrix coeff = coeff(f, fx, fy, fxy);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				grid.at(j, i, at(coeff, 1.0 * i / width, 1.0 * j / height));
	}
	
	public static void interpolate4(Matrix grid, double[] f) {
		int width = grid.n(), height = grid.m();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				grid.at(j, i, bcip.getValue(f, i, j));
	}
	
	public static void interpolateF(Matrix grid, Matrix f, Matrix fx, Matrix fy, Matrix fxy) {
		int width = grid.n(), height = grid.m();
		BiCubicInterpolation inter = new BiCubicInterpolation(new double[] { 0, 1 }, new double[] { 0, 1 },
				f.transpose2D(), fx.transpose2D(), fy.transpose2D(), fxy.transpose2D());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				grid.at(j, i, inter.interpolate(1.0 * i / width, 1.0 * j / height));
	}
	
	public static double[] dfdx(double[] grid, int width, int height) {
		double[] fx = new double[width * height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				double f1 = 0, f2 = 0;
				if (i == 0)
					f1 = grid[j * width + width - 1];
				else
					f1 = grid[j * width + i - 1];
				if (i == width - 1)
					f2 = grid[j * width];
				else
					f2 = grid[j * width + i + 1];
				fx[j * width + i] = (f2 - f1) / 2;
			}
		return fx;
	}
	
	public static double[] dfdy(double[] grid, int width, int height) {
		double[] fy = new double[width * height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				double f1 = 0, f2 = 0;
				if (j == 0)
					f1 = grid[(height - 1) * width + i];
				else
					f1 = grid[(j - 1) * width + i];
				if (j == height - 1)
					f2 = grid[i];
				else
					f2 = grid[(j + 1) * width + i];
				fy[j * width + i] = (f2 - f1) / 2;
			}
		return fy;
	}
	
	// @Override
	// public double[] scale(double[] grid, int width, int height, int scale) {
	// double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height),
	// fxy = dfdx(fy, width, height);
	// Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height,
	// width, fx),
	// Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
	// int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
	// Matrix out = new ArrMat(X, Y);
	// for (int i = 0; i < width - 1; i++)
	// for (int j = 0; j < height - 1; j++) {
	// interpolate(out.subMat(j * scale, i * scale, scale + 1, scale + 1),
	// F.subMat(j, i, 2, 2),
	// Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
	// }
	// return out.mat();
	// }
	
	// @Override
	// public double[] scale(double[] grid, int width, int height, int scale) {
	// ArrMat F = new ArrMat(height, width, grid);
	// int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
	// Matrix out = new ArrMat(X, Y);
	// for (int i = 0; i < width - 1; i++)
	// for (int j = 0; j < height - 1; j++) {
	// interpolate4(out.subMat(j * scale, i * scale, scale + 1, scale + 1),
	// F.subCyclic(j - 1, i - 1, 4, 4));
	// }
	// return out.mat();
	// }
	
	@Override
	public double[] scale(double[] grid, int width, int height, int scale) {
		double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height), fxy = dfdx(fy, width, height);
		Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height, width, fx),
				Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
		int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
		Matrix out = new ArrMat(Y, X);
		for (int i = 0; i < width - 1; i++)
			for (int j = 0; j < height - 1; j++)
				interpolateF(out.subMat(j * scale, i * scale, scale + 1, scale + 1), F.subMat(j, i, 2, 2),
						Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
		return out.mat();
	}
	
	@Override
	public long[] scale(long[] gridl, int width, int height, int scale) {
		double[] grid = Arrays.doubleArray(gridl);
		double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height), fxy = dfdx(fy, width, height);
		Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height, width, fx),
				Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
		int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
		Matrix out = new ArrMat(Y, X);
		for (int i = 0; i < width - 1; i++)
			for (int j = 0; j < height - 1; j++)
				interpolateF(out.subMat(j * scale, i * scale, scale + 1, scale + 1), F.subMat(j, i, 2, 2),
						Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
		return Arrays.longArray(out.mat());
	}
	
	@Override
	public int[] scale(int[] gridi, int width, int height, int scale) {
		double[] grid = Arrays.doubleArray(gridi);
		double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height), fxy = dfdx(fy, width, height);
		Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height, width, fx),
				Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
		int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
		Matrix out = new ArrMat(Y, X);
		for (int i = 0; i < width - 1; i++)
			for (int j = 0; j < height - 1; j++)
				interpolateF(out.subMat(j * scale, i * scale, scale + 1, scale + 1), F.subMat(j, i, 2, 2),
						Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
		return Arrays.intArray(out.mat());
	}
	
	@Override
	public short[] scale(short[] grids, int width, int height, int scale) {
		double[] grid = Arrays.doubleArray(grids);
		double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height), fxy = dfdx(fy, width, height);
		Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height, width, fx),
				Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
		int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
		Matrix out = new ArrMat(Y, X);
		for (int i = 0; i < width - 1; i++)
			for (int j = 0; j < height - 1; j++)
				interpolateF(out.subMat(j * scale, i * scale, scale + 1, scale + 1), F.subMat(j, i, 2, 2),
						Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
		return Arrays.shortArray(out.mat());
	}
	
	@Override
	public byte[] scale(byte[] gridb, int width, int height, int scale) {
		double[] grid = Arrays.doubleArray(gridb);
		double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height), fxy = dfdx(fy, width, height);
		Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height, width, fx),
				Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
		int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
		Matrix out = new ArrMat(Y, X);
		for (int i = 0; i < width - 1; i++)
			for (int j = 0; j < height - 1; j++)
				interpolateF(out.subMat(j * scale, i * scale, scale + 1, scale + 1), F.subMat(j, i, 2, 2),
						Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
		return Arrays.byteArray(out.mat());
	}
	
	@Override
	public float[] scale(float[] gridf, int width, int height, int scale) {
		double[] grid = Arrays.doubleArray(gridf);
		double[] fx = dfdx(grid, width, height), fy = dfdy(grid, width, height), fxy = dfdx(fy, width, height);
		Matrix F = new ArrMat(height, width, grid), Fx = new ArrMat(height, width, fx),
				Fy = new ArrMat(height, width, fy), Fxy = new ArrMat(height, width, fxy);
		int X = scale * (width - 1) + 1, Y = scale * (height - 1) + 1;
		Matrix out = new ArrMat(Y, X);
		for (int i = 0; i < width - 1; i++)
			for (int j = 0; j < height - 1; j++)
				interpolateF(out.subMat(j * scale, i * scale, scale + 1, scale + 1), F.subMat(j, i, 2, 2),
						Fx.subMat(j, i, 2, 2), Fy.subMat(j, i, 2, 2), Fxy.subMat(j, i, 2, 2));
		return Arrays.floatArray(out.mat());
	}
	
}
