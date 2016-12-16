package com.onewolf.alpha.lib.math.type;

public interface Numeric<T> extends AdditiveAbelian<T>, MultiplicativeAbelian<T> {

	static <T> T sum(AdditiveAlgebra<T> a, T b) {
		return a.add(b);
	}

	static <T> T difference(AdditiveAbelian<T> a, T b) {
		return a.subtract(b);
	}

	static <T> T product(MultiplicativeAlgebra<T> a, T b) {
		return a.multiply(b);
	}

	static <T> T quotient(MultiplicativeAbelian<T> a, T b) {
		return a.divide(b);
	}

}
