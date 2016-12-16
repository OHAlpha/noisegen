package com.onewolf.alpha.lib.math.type;

public interface AdditiveAbelian<T> extends AdditiveAlgebra<T> {

	T subtract(T t);

	T subtractFrom(T t);

	static <T> T sum(AdditiveAlgebra<T> a, T b) {
		return a.add(b);
	}

	static <T> T difference(AdditiveAbelian<T> a, T b) {
		return a.subtract(b);
	}

}
