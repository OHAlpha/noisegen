/**
 *
 */
package com.onewolf.alpha.lib.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author her5398
 *
 */
@RunWith(Parameterized.class)
public class BitsTest {

	@Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
				{ (byte) 2, (byte) 2, (byte) 2, (short) 2, (short) 2, (short) 2, 2, 2, 2, 2l, 2l, 2l },
				{ (byte) 6, (byte) 4, (byte) 2, (short) 6, (short) 4, (short) 2, 6, 4, 2, 6l, 4l, 2l },
				{ (byte) 19, (byte) 16, (byte) 1, (short) 19, (short) 16, (short) 1, 19, 16, 1, 19l, 16l, 1l },
				{ (byte) 22, (byte) 16, (byte) 2, (short) 22, (short) 16, (short) 2, 22, 16, 2, 22l, 16l, 2l },
				{ (byte) 23, (byte) 16, (byte) 1, (short) 23, (short) 16, (short) 1, 23, 16, 1, 23l, 16l, 1l },
				{ (byte) 24, (byte) 16, (byte) 8, (short) 24, (short) 16, (short) 8, 24, 16, 8, 24l, 16l, 8l },
				{ (byte) 16, (byte) 16, (byte) 16, (short) 16, (short) 16, (short) 16, 16, 16, 16, 16l, 16l, 16l },
				{ (byte) 50, (byte) 32, (byte) 2, (short) 50, (short) 32, (short) 2, 50, 32, 2, 50l, 32l, 2l } });
	}

	byte bn, bl, bh;

	short sn, sl, sh;

	int in, il, ih;

	long ln, ll, lh;

	public BitsTest(Byte bn, Byte bh, Byte bl, Short sn, Short sh, Short sl, Integer in, Integer ih, Integer il,
			Long ln, Long lh, Long ll) {
		super();
		this.bn = bn;
		this.bl = bl;
		this.bh = bh;
		this.sn = sn;
		this.sl = sl;
		this.sh = sh;
		this.in = in;
		this.il = il;
		this.ih = ih;
		this.ln = ln;
		this.ll = ll;
		this.lh = lh;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#highBit(byte)}.
	 */
	@Test
	public void testHighBitByte() {
		assertEquals(bh, Bits.highBit(bn));
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#lowBit(byte)}.
	 */
	@Test
	public void testLowBitByte() {
		byte a = Bits.lowBit(bn);
		assertEquals(String.format("low(%d) = %d != %d", bn, bl, a), bl, a);
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#highBit(short)}.
	 */
	@Test
	public void testHighBitShort() {
		assertEquals(sh, Bits.highBit(sn));
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#lowBit(short)}.
	 */
	@Test
	public void testLowBitShort() {
		short a = Bits.lowBit(sn);
		assertEquals(String.format("low(%d) = %d != %d", sn, sl, a), sl, a);
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#highBit(int)}.
	 */
	@Test
	public void testHighBitInt() {
		assertEquals(ih, Bits.highBit(in));
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#lowBit(int)}.
	 */
	@Test
	public void testLowBitInt() {
		int a = Bits.lowBit(in);
		assertEquals(String.format("low(%d) = %d != %d", in, il, a), il, a);
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#highBit(long)}.
	 */
	@Test
	public void testHighBitLong() {
		assertEquals(lh, Bits.highBit(ln));
	}

	/**
	 * Test method for {@link com.onewolf.alpha.lib.util.Bits#lowBit(long)}.
	 */
	@Test
	public void testLowBitLong() {
		long a = Bits.lowBit(ln);
		assertEquals(String.format("low(%d) = %d != %d", ln, ll, a), ll, a);
	}

}
