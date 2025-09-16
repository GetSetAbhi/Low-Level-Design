package com.demo.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	private Map<Character, TrieNode> children;
	private boolean isWordEnd;
	
	public TrieNode() {
		this.children = new HashMap<>();
		this.isWordEnd = false;
	}
	
	public boolean isWordEnd() {
		return isWordEnd;
	}
	public void setWordEnd(boolean isWordEnd) {
		this.isWordEnd = isWordEnd;
	}
	public Map<Character, TrieNode> getChildren() {
		return children;
	}
	
	
}
