package com.onewolf.alpha.lib.util;

public class Arrays {
	
	public static void arrayCopy(Object[] src, int src_start, int src_skip, Object[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(byte[] src, int src_start, int src_skip, byte[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(char[] src, int src_start, int src_skip, char[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(short[] src, int src_start, int src_skip, short[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(int[] src, int src_start, int src_skip, int[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(long[] src, int src_start, int src_skip, long[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(float[] src, int src_start, int src_skip, float[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static void arrayCopy(double[] src, int src_start, int src_skip, double[] dst, int dst_start, int dst_skip,
			int len) {
		if (src_skip == 1 && dst_skip == 1)
			System.arraycopy(src, src_start, dst, dst_start, len);
		else
			for (int i = 0; i < len; i++)
				dst[dst_start + i * dst_skip] = src[src_start + i * src_skip];
	}
	
	public static int[] subArray(int[] arr, int start, int skip, int len) {
		int[] out = new int[len];
		arrayCopy(arr, start, skip, out, 0, 1, len);
		return out;
	}
	
	public static float[] subArray(float[] arr, int start, int skip, int len) {
		float[] out = new float[len];
		arrayCopy(arr, start, skip, out, 0, 1, len);
		return out;
	}
	
	public static double[] subArray(double[] arr, int start, int skip, int len) {
		double[] out = new double[len];
		arrayCopy(arr, start, skip, out, 0, 1, len);
		return out;
	}
	
	public static double sum(double[] arr) {
		double sum = 0;
		for (double e : arr)
			sum += e;
		return sum;
	}
	
	public static long[] longArray(double[] arr) {
		long[] out = new long[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (long) arr[i];
		return out;
	}
	
	public static int[] intArray(double[] arr) {
		int[] out = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (int) arr[i];
		return out;
	}
	
	public static short[] shortArray(double[] arr) {
		short[] out = new short[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (short) arr[i];
		return out;
	}
	
	public static byte[] byteArray(double[] arr) {
		byte[] out = new byte[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (byte) arr[i];
		return out;
	}
	
	public static float[] floatArray(double[] arr) {
		float[] out = new float[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (float) arr[i];
		return out;
	}
	
	public static double[] doubleArray(long[] arr) {
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (double) arr[i];
		return out;
	}
	
	public static double[] doubleArray(int[] arr) {
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (double) arr[i];
		return out;
	}
	
	public static double[] doubleArray(short[] arr) {
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (double) arr[i];
		return out;
	}
	
	public static double[] doubleArray(byte[] arr) {
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (double) arr[i];
		return out;
	}
	
	public static double[] doubleArray(float[] arr) {
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			out[i] = (double) arr[i];
		return out;
	}
	
}
