package com.onewolf.alpha.lib.math.type;

public interface AdditiveAlgebra<T> {

	T add(T t);

	T addTo(T t);

	static <T> T sum(AdditiveAlgebra<T> a, T b) {
		return a.add(b);
	}

}
