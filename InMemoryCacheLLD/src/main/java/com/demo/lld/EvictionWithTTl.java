package com.demo.lld;

interface TTLKeyRemoval<K> {
	public void removeKey(K key);
}

class CacheEntry<V> {
	public V value;
	public long timeInMillis;
	
	public CacheEntry(V value) {
		this.value = value;
		this.timeInMillis = System.currentTimeMillis();
	}
}

abstract class EvictionWithTTl<K> implements EvictionStrategy<K>, TTLKeyRemoval<K> {
	
}
