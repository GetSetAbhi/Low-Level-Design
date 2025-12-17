package com.demo.tries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
	
	private TrieNode root;
	
	public Trie() {
		this.root = new TrieNode('/');
	}

	public void insertWord(String word) {
		TrieNode node = root;
		char[] arr = word.toCharArray();
		for (char c : arr) {
			node = node.children.computeIfAbsent(c, k -> new TrieNode(c));
		}
		node.isWord = true;
	}

	public List<String> loadSuggestion(String word) {
		TrieNode node = this.getNode(word);
		if (node != null) {
			List<String> results = new ArrayList<>();
			StringBuilder sb = new StringBuilder(word);
			if (node.isWord) {
				results.add(sb.toString());
			}
			for (char c : node.children.keySet()) {
				TrieNode temp = node.children.get(c);
				dfs(temp, sb, results);
			}
			return results;
		}
		return Collections.EMPTY_LIST;
	}

	private void dfs(TrieNode node, StringBuilder sb, List<String> results) {
		if (node == null) {
			return;
		}
		sb.append(node.value);
		if (node.isWord) {
			results.add(sb.toString());
		}
		for (char c : node.children.keySet()) {
			TrieNode temp = node.children.get(c);
			dfs(temp, sb, results);
		}
		sb.deleteCharAt(sb.length() - 1);
	}

	private TrieNode getNode(String word) {
		char[] carr = word.toCharArray();
		TrieNode node = this.root;
		for (char c : carr ) {
			if (node.children.containsKey(c)) {
				node = node.children.get(c);
			} else {
				node = null;
				break;
			}
		}
		return node;
	}

	/*public List<String> getAllWords() {
		List<String> result = new ArrayList<>();
		for (char c : this.root.children.keySet()) {
			TrieNode node = this.root.children.get(c);
			StringBuilder sb = new StringBuilder();
			dfs(node, sb, result);
		}
		return result;
	}
	
	public List<String> loadSuggestion(String word) {
		TrieNode node = this.getNode(word);
		List<String> result = new ArrayList<>();
		if (node.isWord) {
			result.add(word);
		}
		StringBuilder sb = new StringBuilder(word);
		if (node != null) {
			for (char c : node.children.keySet()) {
				TrieNode temp = node.children.get(c);
				dfs(temp, sb, result);
			}
		}
		return result;
	}

	private void dfs(TrieNode node, StringBuilder sb, List<String> result) {
		if (node == null) return;
		
		sb.append(node.value);
		if (node.isWord) {
			result.add(sb.toString());
		}
		for (char c : node.children.keySet()) {
			TrieNode temp = node.children.get(c);
			dfs(temp, sb, result);
		}
		sb.deleteCharAt(sb.length() - 1);
	}
	
	private TrieNode getNode(String word) {
		if (word != null && word.length() > 0) {
			TrieNode node = this.root;
			for (char c : word.toCharArray()) {
				node = node.children.get(c);
			}
			return node;
		}
		return null;
	}*/
}
