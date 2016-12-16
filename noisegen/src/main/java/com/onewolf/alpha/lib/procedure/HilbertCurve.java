package com.onewolf.alpha.lib.procedure;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Reader;
import java.util.Arrays;

import com.onewolf.alpha.lib.noisegen.GridPermuter;
import com.onewolf.alpha.lib.noisegen.LSystemGridPermuter;
import com.onewolf.alpha.lib.noisegen.LSystemGridPermuter.IterationFinder;

public class HilbertCurve {
	
	public static final LSystem hilbert = new LSystem("A", new char[] { 'A', 'B' },
			new String[] { "-BF+AFA+FB-", "+AF-BFB-FA+" });
	
	public static final GridPermuter permuter = new LSystemGridPermuter(hilbert, 0, 1, new IterationFinder() {
		
		@Override
		public int[] size(int target) {
			int i = 1;
			for (; (1 << i) < target && i < 8; i++)
				;
			i--;
			int size = 1 << i;
			// System.out.printf("iteration: %d, size: %d\n", i, size);
			return new int[] { i, size };
		}
		
	});
	
	static {
		File dir = new File("lsystem//hilbert");
		if (dir.exists()) {
			String[] iterations = dir.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("iteration-") && name.endsWith(".txt")
							&& name.length() == ("iteration-".length() + ".txt".length() + 3);
				}
				
			});
			int[] its = new int[iterations.length];
			int i = 0;
			for (String name : iterations) {
				int level = Integer.parseInt(name.substring("iteration-".length(), "iteration-".length() + 3), 10);
				its[i] = level;
				i++;
			}
			Arrays.sort(its);
			for (i = 0; i < its.length; i++)
				hilbert.setString(its[i], new File(dir, String.format("iteration-%03d.txt", its[i])));
			iterations = dir.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("render-") && name.endsWith(".txt")
							&& name.length() == ("render-".length() + ".txt".length() + 3);
				}
				
			});
			its = new int[iterations.length];
			i = 0;
			for (String name : iterations) {
				int level = Integer.parseInt(name.substring("render-".length(), "render-".length() + 3), 10);
				its[i] = level;
				i++;
			}
			Arrays.sort(its);
			for (i = 0; i < its.length; i++)
				hilbert.setRender(its[i], new File(dir, String.format("render-%03d.txt", its[i])));
		}
	}
	
	public static String iteration(int i) {
		return hilbert.iteration(i);
	}
	
	public static Reader reader(int i, boolean collect) {
		return hilbert.reader(i, collect);
	}
	
	public static Reader renderReader(int i, boolean collect) {
		return hilbert.renderReader(i, collect);
	}
	
}
