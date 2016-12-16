package com.onewolf.alpha.lib.linalg;

import com.onewolf.alpha.lib.linalg.Matrix.SubMatrix;

public class DefaultSubMatrix implements SubMatrix {

	int row, col, m, n;

	Matrix mat;

	public DefaultSubMatrix(Matrix mat, int row, int col, int m, int n) {
		super();
		if (row < 0)
			throw new IllegalArgumentException("negative row");
		if (col < 0)
			throw new IllegalArgumentException("negative col");
		if (row >= mat.m())
			throw new IllegalArgumentException("row out of bounds");
		if (col >= mat.n())
			throw new IllegalArgumentException("col out of bounds");
		if (row + m > mat.m())
			throw new IllegalArgumentException("too many rows");
		if (col + n > mat.n())
			throw new IllegalArgumentException("too many cols");
		this.mat = mat;
		this.row = row;
		this.col = col;
		this.m = m;
		this.n = n;
	}

	// @Override
	// public int[] rowsF() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public int[] colsF() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public int m() {
		return m;
	}

	@Override
	public int n() {
		return n;
	}

	@Override
	public double at(int row, int col) {
		return mat.at(this.row + row, this.col + col);
	}

	@Override
	public double at(int row, int col, double val) {
		return mat.at(this.row + row, this.col + col, val);
	}

	@Override
	public double[] row(int row) {
		return mat.row(this.row + row, col, n);
	}

	@Override
	public double[] row(int row, double[] vals) {
		return mat.row(this.row + row, col, n, vals);
	}

	@Override
	public double[] row(int row, int col, int len) {
		return mat.row(this.row + row, this.col + col, len);
	}

	@Override
	public double[] row(int row, int col, int len, double[] vals) {
		return mat.row(this.row + row, this.col + col, len, vals);
	}

	@Override
	public double[] row(int row, int[] cols) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] row(int row, int[] cols, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] col(int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] col(int col, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] col(int col, int row, int len) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] col(int col, int row, int len, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] col(int col, int[] rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] col(int col, int[] rows, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] sub(int row, int col, int m, int n) {
		return mat.sub(this.row + row, this.col + col, m, n);
	}

	@Override
	public double[] sub(int row, int col, int m, int n, double[] vals) {
		return mat.sub(this.row + row, this.col + col, m, n, vals);
	}

	@Override
	public double[] sub(int[] rows, int[] cols) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] sub(int[] rows, int[] cols, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] mat() {
		return mat.sub(row, col, m, n);
	}

	@Override
	public double[] mat(double[] vals) {
		return mat.sub(row, col, m, n, vals);
	}

	@Override
	public double[] transpose() {
		// TODO inefficient
		return new ArrMat(m, n, mat()).transpose();
	}

	@Override
	public Matrix transposeMat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix superMat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int subRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int subCol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int subM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int subN() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double[] transpose(double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public int rowStartF() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int colStartF() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int rowLenF() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int colLenF() {
	// // TODO Auto-generated method stub
	// return 0;
	// }

}
