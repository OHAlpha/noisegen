package com.onewolf.alpha.lib.linalg;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.onewolf.alpha.lib.linalg.MatrixFieldGen.GeneratedMatrixSet;

public abstract class MatrixTest {

	@Before
	public void setUp() throws Exception {
		r = new Random();
		iterations = r.nextInt(6) + 5;
		delta = .001;
	}

	@After
	public void tearDown() throws Exception {
	}

	protected GeneratedMatrixSet gms;

	protected Matrix mat;

	protected Random r;

	protected int iterations;

	protected double delta;

	public MatrixTest(GeneratedMatrixSet gms, Matrix mat) {
		this.gms = gms;
		this.mat = mat;
	}

	@Test
	public void testM() {
		assertEquals(gms.size, mat.m());
	}

	@Test
	public void testN() {
		assertEquals(gms.size, mat.n());
	}

	@Test
	public void testAtIntInt() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			int col = r.nextInt(gms.size);
			assertEquals(gms.matrix[row * gms.size + col], mat.at(row, col), delta);
			assertEquals(gms.rows[row][col], mat.at(row, col), delta);
			assertEquals(gms.columns[col][row], mat.at(row, col), delta);
		}
	}

	@Test
	public void testAtIntIntDouble() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			int col = r.nextInt(gms.size);
			double a, b;
			a = gms.matrix[row * gms.size + col];
			// assertEquals(a, mat.at(row, col, b = r.nextDouble() * 360),
			// delta);
			mat.at(row, col, b = r.nextDouble() * 360);
			a = b;
			// assertEquals(a, mat.at(row, col, b = r.nextDouble() * 360),
			// delta);
			mat.at(row, col, b = r.nextDouble() * 360);
			a = b;
			assertEquals(a, mat.at(row, col), delta);
		}
	}

	@Test
	public void testRowInt() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			double[] actual = mat.row(row);
			assertNotNull(actual);
			for (int j = 0; j < gms.size; j++)
				assertEquals(gms.rows[row][j], actual[j], delta);
		}
	}

	@Test
	public void testRowIntDoubleArray() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			double[] rand = MatrixFieldGen.randArrG(gms.size, -1000, 360);
			double[] actual = mat.row(row, rand);
			// assertNotNull(actual);
			// for (int j = 0; j < gms.size; j++)
			// assertEquals(gms.rows[row][j], actual[j], delta);
			actual = mat.row(row);
			assertNotNull(actual);
			for (int j = 0; j < gms.size; j++)
				assertEquals(rand[j], actual[j], delta);
		}
	}

	@Test
	public void testRowIntIntInt() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			int start = r.nextInt(gms.size - 1);
			int len = r.nextInt(gms.size - start);
			double[] actual = mat.row(row, start, len);
			assertNotNull(actual);
			for (int j = 0; j < len; j++)
				assertEquals(gms.rows[row][start + j], actual[j], delta);
		}
	}

	@Test
	public void testRowIntIntIntDoubleArray() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			int start = r.nextInt(gms.size - 1);
			int len = r.nextInt(gms.size - start);
			double[] rand = MatrixFieldGen.randArrG(len, -1000, 360);
			double[] actual = mat.row(row, start, len, rand);
			// assertNotNull(actual);
			// for (int j = 0; j < len; j++)
			// assertEquals(gms.rows[row][start + j], actual[j], delta);
			actual = mat.row(row, start, len);
			assertNotNull(actual);
			for (int j = 0; j < len; j++)
				assertEquals(rand[j], actual[j], delta);
		}
	}

	@Test
	public void testRowIntIntArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRowIntIntArrayDoubleArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testColInt() {
		for (int i = 0; i < iterations; i++) {
			int col = r.nextInt(gms.size);
			double[] actual = mat.col(col);
			assertNotNull(actual);
			for (int j = 0; j < gms.size; j++)
				assertEquals(gms.columns[col][j], actual[j], delta);
		}
	}

	@Test
	public void testColIntDoubleArray() {
		for (int i = 0; i < iterations; i++) {
			int col = r.nextInt(gms.size);
			double[] rand = MatrixFieldGen.randArrG(gms.size, -1000, 360);
			double[] actual = mat.col(col, rand);
			// assertNotNull(actual);
			// for (int j = 0; j < gms.size; j++)
			// assertEquals(gms.columns[col][j], actual[j], delta);
			actual = mat.col(col);
			assertNotNull(actual);
			for (int j = 0; j < gms.size; j++)
				assertEquals(rand[j], actual[j], delta);
		}
	}

	@Test
	public void testColIntIntInt() {
		for (int i = 0; i < iterations; i++) {
			int col = r.nextInt(gms.size);
			int start = r.nextInt(gms.size - 1);
			int len = r.nextInt(gms.size - start);
			double[] actual = mat.col(col, start, len);
			assertNotNull(actual);
			for (int j = 0; j < len; j++)
				assertEquals(gms.columns[col][start + j], actual[j], delta);
		}
	}

	@Test
	public void testColIntIntIntDoubleArray() {
		for (int i = 0; i < iterations; i++) {
			int col = r.nextInt(gms.size);
			int start = r.nextInt(gms.size - 1);
			int len = r.nextInt(gms.size - start);
			double[] rand = MatrixFieldGen.randArrG(len, -1000, 360);
			double[] actual = mat.col(col, start, len, rand);
			// assertNotNull(actual);
			// for (int j = 0; j < len; j++)
			// assertEquals(gms.columns[col][start + j], actual[j], delta);
			actual = mat.col(col, start, len);
			assertNotNull(actual);
			for (int j = 0; j < len; j++)
				assertEquals(rand[j], actual[j], delta);
		}
	}

	@Test
	public void testColIntIntArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testColIntIntArrayDoubleArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSubIntIntIntInt() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size - 1);
			int col = r.nextInt(gms.size - 1);
			int m = r.nextInt(gms.size - row);
			int n = r.nextInt(gms.size - col);
			double[] rand = MatrixFieldGen.randArrG(m * n, -1000, 360);
			double[] actual = mat.sub(row, col, m, n, rand);
			// assertNotNull(actual);
			// for (int j = 0; j < m; j++)
			// for (int k = 0; k < n; k++) {
			// assertEquals(gms.matrix[(row + j) * gms.size + col + k], actual[j
			// * n + k], delta);
			// assertEquals(gms.rows[row + j][col + k], actual[j * n + k],
			// delta);
			// assertEquals(gms.columns[col + k][row + j], actual[j * n + k],
			// delta);
			// }
			actual = mat.sub(row, col, m, n);
			assertNotNull(actual);
			for (int j = 0; j < m; j++)
				for (int k = 0; k < n; k++)
					assertEquals(rand[j * n + k], actual[j * n + k], delta);
		}
	}

	@Test
	public void testSubIntIntIntIntDoubleArray() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size - 1);
			int col = r.nextInt(gms.size - 1);
			int m = r.nextInt(gms.size - row);
			int n = r.nextInt(gms.size - col);
			double[] actual = mat.sub(row, col, m, n);
			assertNotNull(actual);
			for (int j = 0; j < m; j++)
				for (int k = 0; k < n; k++) {
					assertEquals(gms.matrix[(row + j) * gms.size + col + k], actual[j * n + k], delta);
					assertEquals(gms.rows[row + j][col + k], actual[j * n + k], delta);
					assertEquals(gms.columns[col + k][row + j], actual[j * n + k], delta);
				}
		}
	}

	@Test
	public void testSubMatIntIntIntInt() {
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size - 1);
			int col = r.nextInt(gms.size - 1);
			int m = r.nextInt(gms.size - row);
			int n = r.nextInt(gms.size - col);
			Matrix subMat = mat.subMat(row, col, m, n);
			assertNotNull(subMat);
			assertEquals(m, subMat.m());
			assertEquals(n, subMat.n());
			double[] actual = subMat.mat();
			assertNotNull(actual);
			for (int j = 0; j < m; j++)
				for (int k = 0; k < n; k++) {
					assertEquals(gms.matrix[(row + j) * gms.size + col + k], actual[j * n + k], delta);
					assertEquals(gms.rows[row + j][col + k], actual[j * n + k], delta);
					assertEquals(gms.columns[col + k][row + j], actual[j * n + k], delta);
				}
		}
	}

	@Test
	public void testSubIntArrayIntArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSubIntArrayIntArrayDoubleArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSubMatIntArrayIntArray() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testMat() {
		double[] actual = mat.mat();
		assertNotNull(actual);
		assertArrayEquals(gms.matrix, actual, delta);
	}

	@Test
	public void testMatDoubleArray() {
		double[] rand = MatrixFieldGen.randArrG(gms.size * gms.size, -1000, 360);
		double[] actual = mat.mat(rand);
		// assertNotNull(actual);
		// assertArrayEquals(gms.matrix, actual, delta);
		actual = mat.mat();
		assertNotNull(actual);
		assertArrayEquals(rand, actual, delta);
	}

	@Test
	public void testTranspose() {
		double[] actual = mat.transpose();
		assertNotNull(actual);
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			int col = r.nextInt(gms.size);
			assertEquals(gms.matrix[row * gms.size + col], actual[col * gms.size + row], delta);
			assertEquals(gms.rows[row][col], actual[col * gms.size + row], delta);
			assertEquals(gms.columns[col][row], actual[col * gms.size + row], delta);
		}
	}

	@Test
	public void testTransposeMat() {
		Matrix transposeMat = mat.transposeMat();
		assertNotNull(transposeMat);
		assertEquals(gms.size, transposeMat.m());
		assertEquals(gms.size, transposeMat.n());
		double[] actual = transposeMat.mat();
		assertNotNull(actual);
		for (int i = 0; i < iterations; i++) {
			int row = r.nextInt(gms.size);
			int col = r.nextInt(gms.size);
			assertEquals(gms.matrix[row * gms.size + col], actual[col * gms.size + row], delta);
			assertEquals(gms.rows[row][col], actual[col * gms.size + row], delta);
			assertEquals(gms.columns[col][row], actual[col * gms.size + row], delta);
		}
	}

}
