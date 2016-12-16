package com.onewolf.alpha.lib.noisegen;

import java.io.IOException;
import java.io.Reader;

import com.onewolf.alpha.lib.procedure.LSystem;

public class LSystemGridPermuter implements GridPermuter {
	
	public static interface IterationFinder {
		
		int[] size(int target);
		
	}
	
	protected LSystem system;
	
	int start;
	
	int direction;
	
	IterationFinder finder;
	
	public LSystemGridPermuter(LSystem system, int start, int direction, IterationFinder finder) {
		super();
		this.system = system;
		this.start = start;
		this.direction = direction;
		this.finder = finder;
	}
	
	public int[] permuteGrid(int[] iteration) {
		int size = iteration[1];
		int[] perm = new int[size * size];
		int i = 0;
		int[] p = new int[2];
		switch (start) {
			case 3:
				p[0] = 0;
				p[1] = size - 1;
				break;
			case 2:
				p[0] = p[1] = size - 1;
				break;
			case 1:
				p[0] = size - 1;
				p[1] = 0;
				break;
			case 0:
			default:
				p[0] = p[1] = 0;
				break;
		}
		int d = direction;
		Reader in = system.renderReader(iteration[0], true);
		int c;
		// String dir = "";
		// String ind = "";
		try {
			while ((c = in.read()) > 0 && i < perm.length) {
				// dir += (char) c + ", ";
				switch (c) {
					case '+':
						d = (d + 1) % 4;
						// ind += "-, ";
						break;
					case '-':
						d = (d + 3) % 4;
						// ind += "-, ";
						break;
					case 'F':
						perm[i++] = p[1] * size + p[0];
						// ind += String.format("(%d,%d)->", p[0], p[1]);
						switch (d) {
							case 0:
								p[0]++;
								break;
							case 1:
								p[1]++;
								break;
							case 2:
								p[0]--;
								break;
							case 3:
								p[1]--;
						}
						// ind += String.format("(%d,%d), ", p[0], p[1]);
				}
			}
			if (i < perm.length)
				perm[i++] = p[1] * size + p[0];
		} catch (IOException e) {
		}
		// System.out.println(dir);
		// System.out.println(ind);
		// System.out.println(Arrays.toString(perm));
		return perm;
	}
	
	@Override
	public int[] permuteGrid(int width, int height) {
		// if(width!=height)
		// throw new IllegalArgumentException("width must equal height");
		int[] iteration = finder.size(Math.min(width, height));
		int size = iteration[1];
		int[] lperm = permuteGrid(iteration);
		double wr = 1.0 * width / size;
		double hr = 1.0 * height / size;
		int[] perm = new int[width * height];
		int[] xs = new int[size], ys = new int[size];
		for (int i = 0; i < size; i++) {
			xs[i] = (int) Math.floor(wr * i);
			ys[i] = (int) Math.floor(hr * i);
		}
		int i = 0;
		for (int j = 0; j < size * size; j++) {
			int p = lperm[j];
			int lx = p % size, ly = p / size;
			int fx = xs[lx], tx = size - lx == 1 ? width : xs[lx + 1];
			int fy = ys[ly], ty = size - ly == 1 ? height : ys[ly + 1];
			for (int k = fx; k < tx; k++)
				for (int l = fy; l < ty; l++)
					perm[i++] = l * width + k;
		}
		// System.out.println(Arrays.toString(perm));
		return perm;
	}
	
	@Override
	public int[] permuteGrid2D(int width, int height) {
		// if(width!=height)
		// throw new IllegalArgumentException("width must equal height");
		int[] iteration = finder.size(Math.min(width, height));
		int size = iteration[1];
		int[] lperm = permuteGrid(iteration);
		double wr = 1.0 * width / size;
		double hr = 1.0 * height / size;
		int[] perm = new int[2 * width * height];
		int[] xs = new int[size], ys = new int[size];
		for (int i = 0; i < size; i++) {
			xs[i] = (int) Math.floor(wr * i);
			ys[i] = (int) Math.floor(hr * i);
		}
		int i = 0;
		for (int j = 0; j < size * size; j++) {
			int p = lperm[j];
			int lx = p % size, ly = p / size;
			int fx = xs[lx], tx = size - lx == 1 ? width : xs[lx + 1];
			int fy = ys[ly], ty = size - ly == 1 ? height : ys[ly + 1];
			for (int k = fx; k < tx; k++)
				for (int l = fy; l < ty; l++) {
					perm[i++] = k;
					perm[i++] = l;
				}
		}
		return perm;
	}
	
}
