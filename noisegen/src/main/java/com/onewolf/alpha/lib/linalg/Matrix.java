package com.onewolf.alpha.lib.linalg;

import com.onewolf.alpha.lib.math.type.AdditiveAbelian;
import com.onewolf.alpha.lib.math.type.MultiplicativeAlgebra;
import com.onewolf.alpha.lib.util.Arrays;

public interface Matrix extends AdditiveAbelian<Matrix>, MultiplicativeAlgebra<Matrix> {

	int m();

	int n();

	double at(int row, int col);

	double at(int row, int col, double val);

	double[] row(int row);

	double[] row(int row, double[] vals);

	double[] row(int row, int col, int len);

	double[] row(int row, int col, int len, double[] vals);

	double[] row(int row, int[] cols);

	double[] row(int row, int[] cols, double[] vals);

	double[] col(int col);

	double[] col(int col, double[] vals);

	double[] col(int col, int row, int len);

	double[] col(int col, int row, int len, double[] vals);

	double[] col(int col, int[] rows);

	double[] col(int col, int[] rows, double[] vals);

	double[] sub(int row, int col, int m, int n);

	double[] sub(int row, int col, int m, int n, double[] vals);

	default SubMatrix subMat(int row, int col, int m, int n) {
		return new DefaultSubMatrix(this, row, col, m, n);
	}

	double[] sub(int[] rows, int[] cols);

	double[] sub(int[] rows, int[] cols, double[] vals);

	default SliceMatrix subMat(int[] rows, int[] cols) {
		return new DefaultSliceMatrix(this, rows, cols);
	}

	double[] mat();

	double[] mat(double[] vals);

	default double[][] mat2D() {
		int m = m(), n = n();
		double[][] arr = new double[m][n];
		double[] mat = mat();
		for (int i = 0; i < m; i++)
			Arrays.arrayCopy(mat, i * n, 1, arr[i], 0, 1, n);
		return arr;
	}

	default double[][] mat2D(double[][] vals) {
		int m = m(), n = n();
		double[][] arr = new double[m][n];
		double[] mat = mat();
		for (int i = 0; i < m; i++) {
			Arrays.arrayCopy(mat, i * n, 1, arr[i], 0, 1, n);
			Arrays.arrayCopy(vals[i], 0, 1, mat, i * n, 1, n);
		}
		mat(mat);
		return arr;
	}

	double[] transpose();

	double[] transpose(double[] vals);

	default double[][] transpose2D() {
		int m = m(), n = n();
		double[][] arr = new double[m][n];
		double[] transpose = transpose();
		for (int i = 0; i < m; i++)
			Arrays.arrayCopy(transpose, i * n, 1, arr[i], 0, 1, n);
		return arr;
	}

	default double[][] transposet2D(double[][] vals) {
		int m = m(), n = n();
		double[][] arr = new double[m][n];
		double[] transpose = transpose();
		for (int i = 0; i < m; i++) {
			Arrays.arrayCopy(transpose, i * n, 1, arr[i], 0, 1, n);
			Arrays.arrayCopy(vals[i], 0, 1, transpose, i * n, 1, n);
		}
		transpose(transpose);
		return arr;
	}

	default Matrix transposeMat() {
		return new DefaultTransposeMatrix(this);
	}

	default Matrix scaled(double scale) {
		int m = m(), n = n();
		Matrix scaled = new ArrMat(m, n);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				scaled.at(i, j, at(i, j) * scale);
		return scaled;
	}

	default Matrix scale(double scale) {
		int m = m(), n = n();
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				at(i, j, at(i, j) * scale);
		return this;
	}

	@Override
	default Matrix add(Matrix mat) {
		int m = m(), n = n();
		if (mat.m() != m || mat.n() != n)
			throw new IllegalArgumentException(
					String.format("matrix sizes must match: (%dx%d) <> (%dx%d)", m, n, mat.m(), mat.n()));
		Matrix sum = new ArrMat(m, n);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				sum.at(i, j, at(i, j) + mat.at(i, j));
		return sum;
	}

	@Override
	default Matrix addTo(Matrix mat) {
		int m = m(), n = n();
		if (mat.m() != m || mat.n() != n)
			throw new IllegalArgumentException(
					String.format("matrix sizes must match: (%dx%d) <> (%dx%d)", m, n, mat.m(), mat.n()));
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				at(i, j, at(i, j) + mat.at(i, j));
		return this;
	}

	@Override
	default Matrix subtract(Matrix mat) {
		int m = m(), n = n();
		if (mat.m() != m || mat.n() != n)
			throw new IllegalArgumentException(
					String.format("matrix sizes must match: (%dx%d) <> (%dx%d)", m, n, mat.m(), mat.n()));
		Matrix difference = new ArrMat(m, n);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				difference.at(i, j, at(i, j) - mat.at(i, j));
		return difference;
	}

	@Override
	default Matrix subtractFrom(Matrix mat) {
		int m = m(), n = n();
		if (mat.m() != m || mat.n() != n)
			throw new IllegalArgumentException(
					String.format("matrix sizes must match: (%dx%d) <> (%dx%d)", m, n, mat.m(), mat.n()));
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				at(i, j, at(i, j) - mat.at(i, j));
		return this;
	}

	@Override
	default Matrix multiply(Matrix mat) {
		int m = m(), s = n(), n;
		if (mat.m() != s)
			throw new IllegalArgumentException(String.format("matrix must have %d rows: %d", s, mat.m()));
		n = mat.n();
		Matrix product = new ArrMat(m, n);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				double val = 0;
				for (int k = 0; k < s; k++)
					val += at(i, k) + mat.at(k, j);
				product.at(i, j, val);
			}
		return product;
	}

	@Override
	default Matrix multiplyTo(Matrix mat) {
		int m = m(), n = n();
		if (mat.m() != n)
			throw new IllegalArgumentException(String.format("matrix must have %d rows: %d", n, mat.m()));
		if (mat.n() != n)
			throw new IllegalArgumentException(String.format("matrix must have %d columns: %d", n, mat.n()));
		Matrix product = new ArrMat(m, n);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				double val = 0;
				for (int k = 0; k < n; k++)
					val += at(i, k) + mat.at(k, j);
				product.at(i, j, val);
			}
		mat(product.mat());
		return this;
	}

	public static interface SubMatrix extends SliceMatrix {

		@Override
		Matrix superMat();

		int subRow();

		int subCol();

		int subM();

		int subN();

		// int rowStartF();
		//
		// int colStartF();
		//
		// int rowLenF();
		//
		// int colLenF();

		@Override
		default int[] rows() {
			int m = m();
			int s = subRow();
			int[] rs = new int[m];
			for (int i = 0; i < m; i++)
				rs[i] = i + s;
			return rs;
		}

		@Override
		default int[] cols() {
			int n = n();
			int s = subCol();
			int[] cs = new int[n];
			for (int i = 0; i < n; i++)
				cs[i] = i + s;
			return cs;
		}

	}

	public static interface SliceMatrix extends Matrix {

		Matrix superMat();

		int[] rows();

		int[] cols();

		// int[] rowsF();
		//
		// int[] colsF();

	}

}
