/**
 *
 */
package com.onewolf.alpha.lib.linalg;

/**
 * @author her5398
 *
 */
public class DefaultTransposeMatrix implements Matrix {

	Matrix mat;

	public DefaultTransposeMatrix(Matrix mat) {
		super();
		this.mat = mat;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#m()
	 */
	@Override
	public int m() {
		return mat.n();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#n()
	 */
	@Override
	public int n() {
		return mat.m();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#at(int, int)
	 */
	@Override
	public double at(int row, int col) {
		return mat.at(col, row);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#at(int, int, double)
	 */
	@Override
	public double at(int row, int col, double val) {
		return mat.at(col, row, val);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int)
	 */
	@Override
	public double[] row(int row) {
		return mat.col(row);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, double[])
	 */
	@Override
	public double[] row(int row, double[] vals) {
		return mat.col(row, vals);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int, int)
	 */
	@Override
	public double[] row(int row, int col, int len) {
		return mat.col(row, col, len);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int, int, double[])
	 */
	@Override
	public double[] row(int row, int col, int len, double[] vals) {
		return mat.col(row, col, len, vals);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int[])
	 */
	@Override
	public double[] row(int row, int[] cols) {
		return mat.col(row, cols);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#row(int, int[], double[])
	 */
	@Override
	public double[] row(int row, int[] cols, double[] vals) {
		return mat.col(row, cols, vals);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int)
	 */
	@Override
	public double[] col(int col) {
		return mat.row(col);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, double[])
	 */
	@Override
	public double[] col(int col, double[] vals) {
		return mat.row(col, vals);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int, int)
	 */
	@Override
	public double[] col(int col, int row, int len) {
		return mat.row(col, row, len);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int, int, double[])
	 */
	@Override
	public double[] col(int col, int row, int len, double[] vals) {
		return mat.row(col, row, len, vals);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int[])
	 */
	@Override
	public double[] col(int col, int[] rows) {
		return mat.row(col, rows);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#col(int, int[], double[])
	 */
	@Override
	public double[] col(int col, int[] rows, double[] vals) {
		return mat.row(col, rows, vals);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#sub(int, int, int, int)
	 */
	@Override
	public double[] sub(int row, int col, int m, int n) {
		// TODO inefficient
		return mat.subMat(col, row, n, m).transpose();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#sub(int, int, int, int,
	 * double[])
	 */
	@Override
	public double[] sub(int row, int col, int m, int n, double[] vals) {
		// TODO inefficient
		return mat.subMat(col, row, n, m).transposeMat().mat(vals);
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
		return mat.transpose();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#mat(double[])
	 */
	@Override
	public double[] mat(double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#transpose()
	 */
	@Override
	public double[] transpose() {
		return mat.mat();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.onewolf.alpha.lib.linalg.Matrix#transposeMat()
	 */
	@Override
	public Matrix transposeMat() {
		return mat;
	}
	
	@Override
	public double[] transpose(double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

}
