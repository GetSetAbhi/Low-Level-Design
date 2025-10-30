package com.demo.lld;

import java.util.LinkedHashSet;

public class LRUWithTTLEvictionStrategy<K> extends EvictionWithTTl<K> {
	
	private LinkedHashSet<K> hashSet = new LinkedHashSet<>();

	@Override
	public void accessedKey(K key) {
		if (hashSet.contains(key)) {
			hashSet.remove(key);
		}
		hashSet.add(key);
	}

	@Override
	public K evictKey() {
		if (hashSet.size() > 0) {
			K key = hashSet.iterator().next();
			hashSet.remove(key);
			return key;
		}
		return null;
	}

	@Override
	public void removeKey(K key) {
		System.out.println("Removed key : " + key);
		this.hashSet.remove(key);
	}
}
