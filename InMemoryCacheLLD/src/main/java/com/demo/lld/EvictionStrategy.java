package com.demo.lld;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public interface EvictionStrategy<K> {
	public void accessedKey(K key);

	public K evictKey();
}

class LRUEvictionStrategy<K> implements EvictionStrategy<K> {
	
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
}

class Cache<K, V> {

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

class LFUEvictionStrategy<K> implements EvictionStrategy<K> {
	Map<K, Integer> keyFrequency = new HashMap<>();
	Map<Integer, LinkedHashSet<K>> freqToKeys = new HashMap<>();
	int minFreq;
	
	@Override
	public void accessedKey(K key) {
		int oldFreq = keyFrequency.getOrDefault(key, 0);
		int newFrequency = oldFreq + 1;
		keyFrequency.put(key, newFrequency);
		if (oldFreq > 0) {
			LinkedHashSet<K> oldKeySet = freqToKeys.get(oldFreq);
			oldKeySet.remove(key);
			if (oldKeySet.size() == 0) {
				freqToKeys.remove(oldFreq);
				if (oldFreq == minFreq) {
					while (!freqToKeys.containsKey(minFreq) && !freqToKeys.isEmpty()) {
						minFreq += 1;
					}
				}
			}
		} else {
			minFreq = 1;
		}
		freqToKeys.computeIfAbsent(newFrequency, i -> new LinkedHashSet<>()).add(key);
	}

	@Override
	public K evictKey() {
		LinkedHashSet<K> set = freqToKeys.get(minFreq);
		K key = set.getFirst();
		set.remove(key);
		if (set.size() == 0) {
			freqToKeys.remove(minFreq);
			while (!freqToKeys.containsKey(minFreq) && !freqToKeys.isEmpty()) {
				minFreq += 1;
			}
		}
		return key;
	}
}