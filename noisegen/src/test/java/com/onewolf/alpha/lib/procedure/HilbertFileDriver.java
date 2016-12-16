package com.onewolf.alpha.lib.procedure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.concurrent.Semaphore;

public class HilbertFileDriver {
	
	public static class Saver implements Runnable {
		
		Reader in;
		
		Writer string;
		
		Writer render;
		
		public int iteration;
		
		public Saver(Reader toFile, File sf, File rf, int iteration) {
			in = toFile;
			this.iteration = iteration;
			try {
				string = new BufferedWriter(new FileWriter(sf));
				render = new BufferedWriter(new FileWriter(rf));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			int cur;
			try {
				while ((cur = in.read()) > 0) {
					switch (cur) {
						case '+':
						case '-':
						case 'F':
							render.write(cur);
						case 'A':
						case 'B':
							string.write(cur);
					}
				}
				string.flush();
				string.close();
				render.flush();
				render.close();
				System.out.printf("iteration %d finished writing\n", iteration);
			} catch (IOException e) {
			}
		}
		
	}
	
	public static class Generator implements Runnable {
		
		Reader in;
		
		final int[] buffer = new int[4096];
		
		int curIndex = 0;
		
		boolean complete = false;
		
		int fileIndex = 0;
		
		Reader toFile = new Reader() {
			
			@Override
			public void close() throws IOException {
			}
			
			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				int i;
				for (i = off; i < off + len; i++) {
					int c = writeChar();
					if (c < 1)
						return i;
					cbuf[i] = (char) c;
				}
				return i;
			}
			
		};
		
		boolean next;
		
		int nextIndex = 0;
		
		Semaphore free = new Semaphore(4096);
		
		Semaphore unwritten = new Semaphore(0);
		
		Semaphore unconsumed = new Semaphore(0);
		
		Semaphore mutex = new Semaphore(1);
		
		Reader toNext = new Reader() {
			
			@Override
			public void close() throws IOException {
			}
			
			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				int i;
				for (i = off; i < off + len; i++) {
					int c = consumeChar();
					if (c < 1)
						return i;
					cbuf[i] = (char) c;
				}
				return i;
			}
			
		};
		
		public int iteration;
		
		public Generator(Reader last, boolean next, int iteration) {
			in = last;
			this.next = next;
			this.iteration = iteration;
		}
		
		@Override
		public void run() {
			int cur;
			try {
				while ((cur = in.read()) > 0) {
					char[] arr = null;
					switch (cur) {
						case '+':
						case '-':
						case 'F':
							arr = new char[] { (char) cur };
							break;
						case 'A':
							arr = "-BF+AFA+FB-".toCharArray();
							break;
						case 'B':
							arr = "+AF-BFB-FA+".toCharArray();
					}
					for (char c : arr) {
						pushChar(c);
					}
				}
				pushChar(-1);
				System.out.printf("iteration %d finished parsing\n", iteration);
			} catch (IOException e) {
			}
		}
		
		private void pushChar(int c) {
			try {
				free.acquire();
				mutex.acquire();
				buffer[curIndex++] = c;
				curIndex %= 4096;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mutex.release();
				unwritten.release();
				if (next)
					unconsumed.release();
			}
		}
		
		private int writeChar() {
			int c = 0;
			try {
				unwritten.acquire();
				mutex.acquire();
				c = buffer[fileIndex++];
				fileIndex %= 4096;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mutex.release();
				if (!next || unwritten.availablePermits() >= unconsumed.availablePermits())
					free.release();
			}
			return c;
		}
		
		private int consumeChar() {
			int c = 0;
			try {
				unconsumed.acquire();
				mutex.acquire();
				c = buffer[nextIndex++];
				nextIndex %= 4096;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				mutex.release();
				if (unconsumed.availablePermits() >= unwritten.availablePermits())
					free.release();
			}
			return c;
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		int target = 15, start = 0;
		Reader last = null;
		File dir = new File("lsystem//hilbert");
		if (!dir.exists())
			dir.mkdirs();
		String[] iterations = dir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("iteration-") && name.endsWith(".txt")
						&& name.length() == ("iteration-".length() + ".txt".length() + 3);
			}
			
		});
		if (iterations.length == 0) {
			last = new StringReader("A");
		} else {
			for (String name : iterations) {
				int level = Integer.parseInt(name.substring("iteration-".length(), "iteration-".length() + 3), 10);
				start = Math.max(start, level);
			}
			File file = new File(dir, String.format("iteration-%03d.txt", start));
			last = new BufferedReader(new FileReader(file));
			System.out.printf("Starting from %d\n", start);
		}
		for (int i = start + 1; i <= target; i++) {
			Generator gen = new Generator(last, i < target, i);
			new Thread(gen).start();
			new Thread(new Saver(gen.toFile, new File(dir, String.format("iteration-%03d.txt", i)),
					new File(dir, String.format("render-%03d.txt", i)), i)).start();
			last = gen.toNext;
		}
	}
	
}
