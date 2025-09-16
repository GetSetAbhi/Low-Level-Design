package com.demo.tries;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	
	private TrieNode root;
	
	public Trie() {
		this.root = new TrieNode();
	}

	public void insertWord(String word) {
		TrieNode node = root;
		char[] arr = word.toCharArray();
		for (char c : arr) {
			node = node.getChildren().computeIfAbsent(c, k -> new TrieNode());
		}
		node.setWordEnd(true);
	}

	public List<String> suggestions(String prefix) {
		TrieNode node = findNode(prefix);
		List<String> words = new ArrayList<>();
		if (node != null) {
			StringBuilder sb = new StringBuilder(prefix);
			dfs(node, sb, words);
		}
		return words;
	}

	private void dfs(TrieNode node, StringBuilder sb, List<String> words) {
		if (node.isWordEnd()) {
			words.add(sb.toString());
		}
		for (char c : node.getChildren().keySet()) {
			TrieNode temp = node.getChildren().get(c);
			sb.append(c);
			dfs(temp, sb, words);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private TrieNode findNode(String prefix) {
		TrieNode node = root;
		char[] arr = prefix.toCharArray();
		for (char c : arr) {
			if (node.getChildren().containsKey(c)) {
				node = node.getChildren().get(c);
			} else {
				return null;
			}
		}
		return node;
	}
}
