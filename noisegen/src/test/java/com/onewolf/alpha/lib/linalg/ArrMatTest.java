package com.onewolf.alpha.lib.linalg;

//import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.onewolf.alpha.lib.linalg.MatrixFieldGen.GeneratedMatrixSet;

@RunWith(Parameterized.class)
public class ArrMatTest extends MatrixTest {

	@Parameters
	public static Collection<Object[]> parameters() {
		return GeneratedMatrices.toList(GeneratedMatrices.iteration_000);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	public ArrMatTest(GeneratedMatrixSet gms) {
		super(gms, new ArrMat(gms.size, gms.size, gms.matrix));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArrMatIntIntZeroM() {
		new ArrMat(0, 1 + r.nextInt(10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArrMatIntIntZeroN() {
		new ArrMat(1 + r.nextInt(10), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArrMatIntIntNegativeM() {
		new ArrMat(-1 - r.nextInt(10), 1 + r.nextInt(10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArrMatIntIntNegativeN() {
		new ArrMat(1 + r.nextInt(10), -1 - r.nextInt(10));
	}

	@Test(expected = NullPointerException.class)
	public void testArrMatIntIntDoubleArrayNullMat() {
		new ArrMat(1 + r.nextInt(10), 1 + r.nextInt(10), null);
	}

}
