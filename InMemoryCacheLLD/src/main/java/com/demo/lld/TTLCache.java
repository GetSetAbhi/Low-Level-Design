package com.demo.lld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TTLCache<K, V> {

	private Map<K, CacheEntry<V>> cacheMap = new HashMap<>();
	private EvictionWithTTl<K> evictionStrategy = new LRUWithTTLEvictionStrategy<K>();
	private ScheduledExecutorService scheduledExecutorService;
	private int capacity;
	private long ttl;

	public TTLCache(int capacity) {
		this.capacity = capacity;
		this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		this.ttl = 5000;
		System.out.println("By Default TTL of 2 seconds is set");
		this.scheduledExecutorService.scheduleAtFixedRate(() -> observeKeys(), 0, 3, TimeUnit.SECONDS);
	}

	public void put(K key, V value) {
		this.cacheMap.put(key, new CacheEntry<V>(value));
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
				return this.cacheMap.get(key).value;
			}
			return null;
		} finally {
		}
	}

	public void observeKeys() {
		long currentTime = System.currentTimeMillis();
		List<K> keys = this.cacheMap.entrySet().stream()
				.filter(e -> currentTime - e.getValue().timeInMillis >= this.ttl).map(e -> e.getKey())
				.collect(Collectors.toList());
		for (K key : keys) {
			evictionStrategy.removeKey(key);
			this.cacheMap.remove(key);
		}
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}
}
