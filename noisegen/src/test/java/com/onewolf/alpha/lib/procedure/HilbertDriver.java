package com.onewolf.alpha.lib.procedure;

import java.io.IOException;

public class HilbertDriver {
	
	static String[] iterations = { "A", "-BF+AFA+FB-", "-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-",
			"-+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+F+-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-F-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-+F+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+-",
			"-+-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-F-+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+F+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+-F-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-+F+-+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+F+-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-F-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-+F+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+-F-+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+F+-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-F-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-+F+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+-+F+-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-F-+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+F+-BF+AFA+FB-F-+AF-BFB-FA+F+AF-BFB-FA+-F-BF+AFA+FB-+-F-+AF-BFB-FA+F+-BF+AFA+FB-F-BF+AFA+FB-+F+AF-BFB-FA+-+-" };
	
	public static void main(String[] args) throws IOException {
		// int n = 4;
		// String last = HilbertCurve.hilbert.string(n, true);
		// System.out.printf("#%d: %b\n", n, last, last.equals(iterations[n]));
		// String string = HilbertCurve.iteration(0);
		// System.out.printf("%s: %b\n", string, string.equals(iterations[0]));
		// for (int i = 1; i < n + 1; i++) {
		// string = HilbertCurve.iteration(i);
		// System.out.printf("%s: %b\n", string, string.equals(iterations[i]));
		// String render = LSystem.readerToString(HilbertCurve.renderReader(i,
		// false));
		// System.out.printf("%s: %b\n", render,
		// render.equals(iterations[i].replaceAll("A|B", "")));
		// }
		int n = 4;
		String last = HilbertCurve.hilbert.string(n, true);
		System.out.printf("hilbert.strings: %d, hilbert.renders: %d\n", HilbertCurve.hilbert.stringSize(),
				HilbertCurve.hilbert.renderSize());
		System.out.printf("#%d: %b\n", n, last, last.equals(iterations[n]));
		System.out.printf("#8.length(): %d\n", HilbertCurve.hilbert.string(8, false).length());
	}
	
}
