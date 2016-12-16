package com.onewolf.alpha.lib.linalg;

import com.onewolf.alpha.lib.linalg.Matrix.SliceMatrix;

public class DefaultSliceMatrix implements SliceMatrix {

	int[] rows, cols;

	Matrix mat;

	public DefaultSliceMatrix(Matrix mat, int[] rows, int[] cols) {
		super();
		this.mat = mat;
		this.rows = rows;
		this.cols = cols;
	}

	@Override
	public int m() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int n() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double at(int row, int col) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double at(int row, int col, double val) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] row(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] row(int row, double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] row(int row, int col, int len) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] row(int row, int col, int len, double[] vals) {
		// TODO Auto-generated method stub
		return null;
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
	public double[] sub(int row, int col, int rows, int cols) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] sub(int row, int col, int rows, int cols, double[] vals) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] mat(double[] vals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] transpose() {
		// TODO Auto-generated method stub
		return null;
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
	public int[] rows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] cols() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double[] transpose(double[] vals) {
		// TODO Auto-generated method stub
		return null;
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

}
