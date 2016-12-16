package com.onewolf.alpha.lib.math.type;

public interface MultiplicativeAbelian<T> extends MultiplicativeAlgebra<T> {

	T divide(T t);

	T divideFrom(T t);

	static <T> T product(MultiplicativeAlgebra<T> a, T b) {
		return a.multiply(b);
	}

	static <T> T quotient(MultiplicativeAbelian<T> a, T b) {
		return a.divide(b);
	}

}
