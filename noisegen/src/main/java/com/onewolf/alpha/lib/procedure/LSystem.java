package com.onewolf.alpha.lib.procedure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class LSystem {
	
	public static String readerToString(Reader in) {
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[1024];
		int n;
		try {
			while ((n = in.read(buffer)) > 0)
				builder.append(String.valueOf(buffer, 0, n));
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public interface LString {
		
		Reader reader();
		
		String string();
		
	}
	
	public class StringLString implements LString {
		
		private String string;
		
		public StringLString(String string) {
			super();
			this.string = string;
		}
		
		@Override
		public Reader reader() {
			return new BufferedReader(new StringReader(string));
		}
		
		@Override
		public String string() {
			return string;
		}
		
	}
	
	public class FileLString implements LString {
		
		private File string;
		
		public FileLString(File string) {
			super();
			this.string = string;
		}
		
		@Override
		public Reader reader() {
			try {
				return new BufferedReader(new FileReader(string));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		@Override
		public String string() {
			return readerToString(reader());
		}
		
	}
	
	public class RReader extends Reader {
		
		int iteration;
		
		StringBuilder builder;
		
		Deque<Character> buffer;
		
		Reader source;
		
		public RReader(Reader source, int iteration, boolean collect) {
			this.source = source;
			this.iteration = iteration;
			if (collect)
				builder = new StringBuilder();
			buffer = new LinkedList<>();
		}
		
		@Override
		public void close() throws IOException {
			source.close();
			if (builder != null && renders.size() == iteration)
				renders.add(new StringLString(builder.toString()));
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
			if (builder != null && strings.size() == iteration)
				strings.add(new StringLString(builder.toString()));
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
	
	private LinkedList<LString> strings;
	
	private LinkedList<LString> renders;
	
	public LSystem(String axiom, char[] constants, String[] rules) {
		super();
		this.rules = new TreeMap<>();
		for (int i = 0; i < Math.min(constants.length, rules.length); i++)
			this.rules.put(constants[i], rules[i]);
		strings = new LinkedList<>();
		strings.add(new StringLString(axiom));
		renders = new LinkedList<>();
		renders.add(new StringLString(readerToString(new RReader(new StringReader(axiom), 0, false))));
	}
	
	public String iteration(int i) {
		while (i >= strings.size())
			strings.add(process(strings.getLast()));
		return strings.get(i).string();
	}
	
	public Reader reader(int i, boolean collect) {
		return i < strings.size() ? strings.get(i).reader() : new LReader(i, collect);
	}
	
	public Reader renderReader(int i, boolean collect) {
		return i < renders.size() ? renders.get(i).reader() : new RReader(reader(i, collect), i, collect);
	}
	
	public String string(int i, boolean collect) {
		return i < strings.size() ? strings.get(i).string() : readerToString(new LReader(i, collect));
	}
	
	public String renderString(int i, boolean collect) {
		return i < renders.size() ? renders.get(i).string()
				: readerToString(new RReader(reader(i, collect), i, collect));
	}
	
	protected LString process(LString source) {
		StringBuilder builder = new StringBuilder();
		Reader in = source.reader();
		try {
			for (int i = in.read(); i > 0; i = in.read()) {
				char c = Character.toUpperCase((char) i);
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
		} catch (Exception e) {
		}
		return new StringLString(builder.toString());
	}
	
	void setString(int i, String string) {
		if (strings.size() == i)
			strings.add(new StringLString(string));
	}
	
	void setString(int i, File string) {
		if (strings.size() == i)
			strings.add(new FileLString(string));
	}
	
	void setRender(int i, String string) {
		if (renders.size() == i)
			renders.add(new StringLString(string));
	}
	
	void setRender(int i, File string) {
		if (renders.size() == i)
			renders.add(new FileLString(string));
	}
	
	public int size() {
		return Math.max(stringSize(), renderSize());
	}
	
	public int stringSize() {
		return strings.size();
	}
	
	public int renderSize() {
		return renders.size();
	}
	
}
