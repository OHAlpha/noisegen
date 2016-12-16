package com.onewolf.alpha.lib.procedure;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadedLSystem {
	
	private static final int DEFAULT_BUFFER_SIZE = 256;
	
	private static final Executor LSTRING_EXECUTOR = Executors.newFixedThreadPool(10);
	
	public class LString {
		
		public class Collector {
			
			protected int bufferSize;
			
			NavigableMap<Integer, Integer> threads;
			
			NavigableMap<Integer, Integer> positions;
			
			NavigableMap<Integer, Integer> free;
			
			public Collector(int size) {
				this(size, DEFAULT_BUFFER_SIZE);
			}
			
			public Collector(int size, int bufferSize) {
				super();
				this.bufferSize = bufferSize;
				threads = new TreeMap<>();
				positions = new TreeMap<>();
				free = new TreeMap<>();
				free.put(0, size);
			}
			
			public boolean register(int id, int start) {
				Entry<Integer, Integer> f = free.floorEntry(start);
				Integer p = threads.floorKey(start);
				if (f == null || f.getValue() < start || p != null && start - p < bufferSize)
					return false;
				threads.put(start, id);
				positions.put(id, start);
				free.put(start, f.getValue());
				free.put(f.getKey(), start);
				return true;
			}
			
			public boolean submit(int id, String string) {
				synchronized (LString.this) {
					int position = positions.get(id);
					int length = string.length();
					Entry<Integer, Integer> fSpace = free.floorEntry(position);
					Entry<Integer, Integer> lSpace = free.floorEntry(position + length);
					NavigableMap<Integer, Integer> spaces = free.subMap(fSpace.getKey(), true, lSpace.getKey(), true);
					for (Integer start : spaces.navigableKeySet()) {
						int end = spaces.get(start);
						if (start >= position)
							free.remove(start);
						else
							free.put(start, position);
						if (end > position + length)
							free.put(position + length, end);
						int s = start > position ? start - position : 0;
						int e = end < position + length ? end - position : length;
						submitInternal(position + s, string.substring(s, e));
					}
					if (free.isEmpty()) {
						complete = true;
						LString.this.notifyAll();
					}
					return spaces.lastEntry().getValue() > position + length;
				}
			}
			
			private void submitInternal(int position, String string) {
				synchronized (components) {
					components.put(position, string);
					for (char c : string.toCharArray())
						if (constantCounts.containsKey(c))
							constantCounts.put(c, constantCounts.get(c) + 1);
						else
							constantCounts.put(c, 1);
					components.notifyAll();
				}
			}
			
		}
		
		public class LStringProcessor implements Runnable {
			
			private int id;
			
			private Collector collector;
			
			private LString source;
			
			private int start;
			
			public LStringProcessor(Collector collector, int id, LString source, int start) {
				this.collector = collector;
				this.id = id;
				this.source = source;
				this.start = start;
			}
			
			@Override
			public void run() {
				process(collector, id, source, start, collector.bufferSize);
			}
			
		}
		
		public class LStringReader extends Reader {
			
			private int cur;
			
			public LStringReader(int start) {
				super();
				cur = start;
			}
			
			@Override
			public void close() throws IOException {
			}
			
			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				int n = 0;
				while (n < len) {
					Entry<Integer, String> component = components.floorEntry(cur);
					synchronized (components) {
						while (component == null) {
							try {
								components.wait();
							} catch (InterruptedException e) {
							}
							component = components.floorEntry(cur);
						}
					}
					String componentString = component.getValue();
					int p = 0;
					for (int i = cur - component.getKey(); i < componentString.length() && n < len; i++)
						cbuf[n + p++] = componentString.charAt(i);
					n += p;
					cur += p;
				}
				return n;
			}
			
		}
		
		private NavigableMap<Integer, String> components;
		
		private Map<Character, Integer> constantCounts;
		
		boolean complete = false;
		
		public LString(String axiom) {
			components = new TreeMap<>();
			components.put(0, axiom);
			constantCounts = new TreeMap<>();
			for (char c : axiom.toCharArray())
				if (constantCounts.containsKey(c))
					constantCounts.put(c, constantCounts.get(c) + 1);
				else
					constantCounts.put(c, 1);
			complete = true;
		}
		
		public LString(LString previous, int numThreads, Executor executor) {
			components = new TreeMap<>();
			constantCounts = new TreeMap<>();
			int size = previous.nextSize();
			Collector collector = new Collector(size);
			for (int i = 0; i < numThreads; i++)
				executor.execute(new LStringProcessor(collector, i, previous, size * i / numThreads));
		}
		
		public int nextSize() {
			int size = 0;
			for (char c : constantCounts.keySet())
				switch (c) {
					case 'F':
					case '+':
					case '-':
						size += constantCounts.get(c);
					default:
						size += constantCounts.get(c) * rules.get(c).length();
				}
			return size;
		}
		
		public Reader openReader(int start) {
			return new LStringReader(start);
		}
		
		public boolean isComplete() {
			return complete;
		}
		
		public String toString() {
			if (!isComplete())
				return super.toString();
			StringBuilder builder = new StringBuilder();
			for (Integer index : components.navigableKeySet())
				builder.append(components.get(index));
			return builder.toString();
		}
		
	}
	
	private Map<Character, String> rules;
	
	private LinkedList<LString> strings;
	
	public ThreadedLSystem(String axiom, char[] constants, String[] rules) {
		super();
		this.rules = new TreeMap<>();
		for (int i = 0; i < Math.min(constants.length, rules.length); i++)
			this.rules.put(constants[i], rules[i]);
		strings = new LinkedList<>();
		strings.add(new LString(axiom));
	}
	
	public Reader iteration(int i) {
		while (i >= strings.size())
			strings.add(new LString(strings.getLast(), strings.size() + 1, LSTRING_EXECUTOR));
		return strings.get(i).openReader(0);
	}
	
	public String iterationString(int i) {
		while (i >= strings.size())
			strings.add(new LString(strings.getLast(), strings.size() + 1, LSTRING_EXECUTOR));
		LString string = strings.get(i);
		synchronized (string) {
			while (!string.isComplete())
				try {
					string.wait();
				} catch (InterruptedException e) {
				}
		}
		return string.toString();
	}
	
	protected boolean process(LString.Collector collector, int id, LString source, int start, int bufferSize) {
		if (!collector.register(id, start))
			return false;
		Reader in = source.openReader(start);
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[bufferSize];
		do
			try {
				int n = in.read(buffer);
				for (int i = 0; i < n; i++)
					switch (buffer[i]) {
						case 'F':
						case '+':
						case '-':
							builder.append(buffer[i]);
						default:
							builder.append(rules.get(buffer[i]));
					}
			} catch (Exception e) {
			}
		while (collector.submit(id, builder.toString()));
		return true;
	}
	
}
