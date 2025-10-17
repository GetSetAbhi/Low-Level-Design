package com.demo.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

	public char value;
	public Map<Character, TrieNode> children;
	public boolean isWord;
	
	public TrieNode(char value) {
		super();
		this.value = value;
		this.children = new HashMap<>();
		this.isWord = false;
	}

	@Override
	public String toString() {
		return "TrieNode [value=" + value + "]";
	}
}
