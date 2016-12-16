/**
 *
 */
package com.onewolf.alpha.lib.linalg;

import com.onewolf.alpha.lib.util.Arrays;

/**
 * @author her5398
 *
 */
public class ArrMat implements Matrix {

	int m, n;

	double[] mat;

	public ArrMat(int m, int n) {
		super();
		if (m < 1)
			throw new IllegalArgumentException(String.format("m must be a positive integer: %d", m));
		if (n < 1)
			throw new IllegalArgumentException(String.format("n must be a positive integer: %d", n));
		this.m = m;
		this.n = n;
		mat = new double[m * n];
	}

	public ArrMat(int m, int n, double[] mat) {
		this(m, n);
		if (mat == null)
			throw new NullPointerException("mat cannot be null");
		this.mat = new double[m * n];
		Arrays.arrayCopy(mat, 0, 1, this.mat, 0, 1, Math.min(m * n, mat.length));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#m()
	 */
	@Override
	public int m() {
		return m;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#n()
	 */
	@Override
	public int n() {
		return n;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#at(int, int)
	 */
	@Override
	public double at(int row, int col) {
		return mat[row * n + col];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#at(int, int, double)
	 */
	@Override
	public double at(int row, int col, double val) {
		double out = mat[row * n + col];
		mat[row * n + col] = val;
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int)
	 */
	@Override
	public double[] row(int row) {
		double[] out = new double[n];
		Arrays.arrayCopy(mat, row * n, 1, out, 0, 1, n);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, double[])
	 */
	@Override
	public double[] row(int row, double[] vals) {
		Arrays.arrayCopy(vals, 0, 1, mat, row * n, 1, n);
		double[] out = new double[n];
		Arrays.arrayCopy(mat, row * n, 1, out, 0, 1, n);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int, int)
	 */
	@Override
	public double[] row(int row, int col, int len) {
		double[] out = new double[len];
		Arrays.arrayCopy(mat, row * n + col, 1, out, 0, 1, len);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int, int, double[])
	 */
	@Override
	public double[] row(int row, int col, int len, double[] vals) {
		Arrays.arrayCopy(vals, 0, 1, mat, row * n + col, 1, len);
		double[] out = new double[len];
		Arrays.arrayCopy(mat, row * n + col, 1, out, 0, 1, len);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int[])
	 */
	@Override
	public double[] row(int row, int[] cols) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int[], double[])
	 */
	@Override
	public double[] row(int row, int[] cols, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int)
	 */
	@Override
	public double[] col(int col) {
		double[] out = new double[m];
		Arrays.arrayCopy(mat, col, n, out, 0, 1, m);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, double[])
	 */
	@Override
	public double[] col(int col, double[] vals) {
		Arrays.arrayCopy(vals, 0, 1, mat, col, n, m);
		double[] out = new double[m];
		Arrays.arrayCopy(mat, col, n, out, 0, 1, m);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int, int)
	 */
	@Override
	public double[] col(int col, int row, int len) {
		double[] out = new double[len];
		Arrays.arrayCopy(mat, row * n + col, n, out, 0, 1, len);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int, int, double[])
	 */
	@Override
	public double[] col(int col, int row, int len, double[] vals) {
		Arrays.arrayCopy(vals, 0, 1, mat, row * n + col, n, len);
		double[] out = new double[len];
		Arrays.arrayCopy(mat, row * n + col, n, out, 0, 1, len);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int[])
	 */
	@Override
	public double[] col(int col, int[] rows) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int[], double[])
	 */
	@Override
	public double[] col(int col, int[] rows, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#sub(int, int, int, int)
	 */
	@Override
	public double[] sub(int row, int col, int m, int n) {
		double[] out = new double[m * n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				out[i * n + j] = mat[(row + i) * this.n + col + j];
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#sub(int, int, int, int,
	 * double[])
	 */
	@Override
	public double[] sub(int row, int col, int m, int n, double[] vals) {
		if (row < 0)
			throw new IllegalArgumentException("negative row");
		if (col < 0)
			throw new IllegalArgumentException("negative col");
		if (row >= this.m)
			throw new IllegalArgumentException("row out of bounds");
		if (col >= this.n)
			throw new IllegalArgumentException("col out of bounds");
		if (row + m > this.m)
			throw new IllegalArgumentException("too many rows");
		if (col + n > this.n)
			throw new IllegalArgumentException("too many cols");
		double[] out = new double[m * n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				out[i * n + j] = mat[(row + i) * this.n + col + j];
				mat[(row + i) * this.n + col + j] = vals[i * n + j];
			}
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#sub(int[], int[])
	 */
	@Override
	public double[] sub(int[] rows, int[] cols) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#sub(int[], int[], double[])
	 */
	@Override
	public double[] sub(int[] rows, int[] cols, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#mat()
	 */
	@Override
	public double[] mat() {
		double[] out = new double[m * n];
		Arrays.arrayCopy(mat, 0, 1, out, 0, 1, m * n);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#mat(double[])
	 */
	@Override
	public double[] mat(double[] vals) {
		double[] out = new double[m * n];
		Arrays.arrayCopy(mat, 0, 1, out, 0, 1, m * n);
		Arrays.arrayCopy(vals, 0, 1, mat, 0, 1, m * n);
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#transpose()
	 */
	@Override
	public double[] transpose() {
		double[] out = new double[m * n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				out[j * m + i] = mat[i * n + j];
		return out;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#transpose()
	 */
	@Override
	public double[] transpose(double[] vals) {
		double[] out = new double[m * n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				out[j * m + i] = mat[i * n + j];
				mat[i * n + j] = vals[j * m + i];
			}
		return out;

	}

	public double[] subCyclic(int row, int col, int m, int n) {
		row = row % this.m;
		if (row < 0)
			row += this.m;
		col = col % this.n;
		if (col < 0)
			col += this.n;
		if (row + m > this.m) {
			Matrix sub = new ArrMat(m, n);
			Matrix top = sub.subMat(0, 0, this.m - row, n);
			Matrix bottom = sub.subMat(this.m - row, 0, m - this.m + row, n);
			top.mat(subCyclic(row, col, this.m - row, n));
			bottom.mat(subCyclic(0, col, m - this.m + row, n));
			return sub.mat();
		}
		if (col + n > this.n) {
			Matrix sub = new ArrMat(m, n);
			Matrix left = sub.subMat(0, 0, m, this.n - col);
			Matrix right = sub.subMat(0, this.n - col, m, n - this.n + col);
			left.mat(subCyclic(row, col, m, this.n - col));
			right.mat(subCyclic(row, 0, m, n - this.n + col));
			return sub.mat();
		}
		double[] out = new double[m * n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				out[i * n + j] = mat[(row + i) * this.n + col + j];
		return out;
	}

}
