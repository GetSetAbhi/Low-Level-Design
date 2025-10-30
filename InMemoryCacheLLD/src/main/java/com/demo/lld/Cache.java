package com.demo.lld;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, V> {

	private Map<K, V> cacheMap = new HashMap<>();
	private EvictionStrategy<K> evictionStrategy = new LRUEvictionStrategy();
	private int capacity;

	public Cache(int capacity) {
		this.capacity = capacity;
	}

	public void put(K key, V value) {
		this.cacheMap.put(key, value);
		evictionStrategy.accessedKey(key);
		if (this.cacheMap.size() > this.capacity) {
			K oldKey = evictionStrategy.evictKey();
			this.cacheMap.remove(oldKey);
		}
	}

	public V get(K key) {
		try {
			if (this.cacheMap.containsKey(key)) {
				evictionStrategy.accessedKey(key);
				V val = this.cacheMap.get(key);
				return val;
			}
			return null;
		} finally {
		}
	}
}
