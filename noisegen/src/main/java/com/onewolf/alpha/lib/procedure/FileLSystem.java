package com.onewolf.alpha.lib.procedure;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class FileLSystem {
	
	public class RReader extends Reader {
		
		Deque<Character> buffer;
		
		Reader source;
		
		public RReader(Reader source) {
			this.source = source;
			buffer = new LinkedList<>();
		}
		
		@Override
		public void close() throws IOException {
			source.close();
		}
		
		@Override
		public int read(char[] buf, int start, int len) throws IOException {
			int n = 0;
			do
				for (int i = 0; i < len && !buffer.isEmpty(); i++)
					buf[start + n++] = buffer.pollFirst();
			while (n < len && fillBuffer() > 0);
			return n;
		}
		
		private int fillBuffer() throws IOException {
			int n = 0;
			while (buffer.size() < BUFFER_SIZE) {
				int cur = source.read();
				if (cur < 1)
					return n;
				switch (cur) {
					case 'F':
					case '+':
					case '-':
						buffer.addLast((char) cur);
						n++;
						break;
					default:
						;
				}
			}
			return n;
		}
		
	}
	
	private static final int BUFFER_SIZE = 1024;
	
	public class LReader extends Reader {
		
		int iteration;
		
		StringBuilder builder;
		
		Deque<Character> buffer;
		
		Reader previous;
		
		public LReader(int iteration, boolean collect) {
			this.iteration = iteration;
			if (collect)
				builder = new StringBuilder();
			buffer = new LinkedList<>();
			previous = reader(iteration - 1, collect);
		}
		
		@Override
		public void close() throws IOException {
			previous.close();
			if (strings.size() == iteration)
				strings.add(builder.toString());
		}
		
		@Override
		public int read(char[] buf, int start, int len) throws IOException {
			int n = 0;
			do
				for (int i = 0; i < len && !buffer.isEmpty(); i++)
					buf[start + n++] = buffer.pollFirst();
			while (n < len && fillBuffer() > 0);
			if (builder != null)
				builder.append(String.valueOf(buf, start, n));
			return n;
		}
		
		private int fillBuffer() throws IOException {
			int n = 0;
			while (buffer.size() < BUFFER_SIZE) {
				int cur = previous.read();
				if (cur < 1)
					return n;
				String string = null;
				switch (cur) {
					case 'F':
					case '+':
					case '-':
						string = "" + (char) cur;
						break;
					default:
						string = rules.get((char) cur);
				}
				try {
					for (char c : string.toCharArray())
						buffer.addLast(c);
				} catch (NullPointerException e) {
					throw new RuntimeException(String.format("cur: %c(%d)", (char) cur, cur), e);
				}
				n += string.length();
			}
			return n;
		}
		
	}
	
	private Map<Character, String> rules;
	
	private LinkedList<String> strings;
	
	public FileLSystem(String axiom, char[] constants, String[] rules) {
		super();
		this.rules = new TreeMap<>();
		for (int i = 0; i < Math.min(constants.length, rules.length); i++)
			this.rules.put(constants[i], rules[i]);
		strings = new LinkedList<>();
		strings.add(axiom);
	}
	
	public String iteration(int i) {
		while (i >= strings.size())
			strings.add(process(strings.getLast()));
		return strings.get(i);
	}
	
	public Reader reader(int i, boolean collect) {
		return i < strings.size() ? new StringReader(strings.get(i)) : new LReader(i, collect);
	}
	
	public Reader renderReader(int i, boolean collect) {
		return new RReader(reader(i, collect));
	}
	
	protected String process(String source) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
			char c = Character.toUpperCase(source.charAt(i));
			switch (c) {
				case 'F':
				case '+':
				case '-':
					builder.append(c);
					break;
				default:
					builder.append(rules.get(c));
			}
		}
		return builder.toString();
	}
	
	void setString(int i, String string) {
		if (strings.size() == i)
			strings.add(string);
	}
	
}
