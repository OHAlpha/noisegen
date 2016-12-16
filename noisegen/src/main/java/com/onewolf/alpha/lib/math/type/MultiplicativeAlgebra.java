package com.onewolf.alpha.lib.math.type;

public interface MultiplicativeAlgebra<T> {

	T multiply(T t);

	T multiplyTo(T t);

	static <T> T product(MultiplicativeAlgebra<T> a, T b) {
		return a.multiply(b);
	}

}
