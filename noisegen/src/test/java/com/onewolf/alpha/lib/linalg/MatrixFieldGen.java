package com.onewolf.alpha.lib.linalg;

import java.io.PrintStream;
import java.util.Random;

public class MatrixFieldGen {

	private static Random r = new Random();

	public static double[] randArrU(int size, double min, double max) {
		double[] out = new double[size];
		for (int i = 0; i < size; i++)
			out[i] = min + (max - min) * r.nextDouble();
		return out;
	}

	public static double[] randArrG(int size, double mean, double deviation) {
		double[] out = new double[size];
		for (int i = 0; i < size; i++)
			out[i] = mean + deviation * r.nextGaussian();
		return out;
	}

	public static void randMat(PrintStream out, String field, double[] mat, int size) {
		double[][] rows = new double[size][size], cols = new double[size][size];
		if (field == null)
			out.printf("new GeneratedMatrixSet( %d, new double[] {\n", size);
		else
			out.printf("GeneratedMatrixSet %s = new GeneratedMatrixSet( %d, new double[] {\n", field, size);
		for (int i = 0; i < size * size; i += size) {
			// out.printf("\t/* line %d start */ ", i / size + 1);
			out.print("\t");
			for (int j = 0; j < size; j++) {
				out.printf("%f, ", mat[i + j]);
				rows[i / size][j] = mat[i + j];
				cols[j][i / size] = mat[i + j];
			}
			// out.printf(" /* line %d end */\n", i / size + 1);
			out.println();
		}
		out.print("}, new double[][] {\n");
		for (int i = 0; i < size; i++) {
			out.print("\t{");
			for (int j = 0; j < size; j++)
				out.printf("%f, ", rows[i][j]);
			out.print("},\n");
		}
		out.print("}, new double[][] {\n");
		for (int i = 0; i < size; i++) {
			out.print("\t{");
			for (int j = 0; j < size; j++)
				out.printf("%f, ", cols[i][j]);
			out.print("},\n");
		}
		out.print("} )");
	}

	public static class GeneratedMatrixSet {

		int size;

		double[] matrix;

		double[][] rows, columns;

		public GeneratedMatrixSet(int size, double[] matrix, double[][] rows, double[][] columns) {
			super();
			this.size = size;
			this.matrix = matrix;
			this.rows = rows;
			this.columns = columns;
		}
	}

	public static void main(String[] args) {
		int[] sizes = { 2, 3, 4, 10, 20 };
		// int[] sizes = { 4 };
		System.out.println('{');
		for (int size : sizes) {
			randMat(System.out, null, randArrU(size * size, 0, 1), size);
			System.out.println(',');
			randMat(System.out, null, randArrU(size * size, -1, 1), size);
			System.out.println(',');
			randMat(System.out, null, randArrG(size * size, 0, 1), size);
			System.out.println(',');
		}
		System.out.print('}');
	}

}
