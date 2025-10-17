package com.demo.tries;

public class MainMethod {

	public static void main(String[] args) {
		Trie trie = new Trie();

		trie.insertWord("cat");
		trie.insertWord("bat");
		trie.insertWord("apple");
        trie.insertWord("app");
        trie.insertWord("application");
        trie.insertWord("apex");
        trie.insertWord("banana");
		
        System.out.println(trie.loadSuggestion("app"));
        
        System.out.println(trie.loadSuggestion("ap"));

	}
}
