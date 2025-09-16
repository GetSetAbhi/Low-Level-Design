package com.demo.lld;

public class CacheDemo {

	public static void main(String[] args) {
		Cache<Integer, String> cache = new Cache(3);
		
		cache.put(1, "One");
		cache.put(2, "Two");
		cache.put(3, "Three");
		cache.put(4, "Four");
		
		cache.put(2, "Five");
		cache.put(5, "Six");
	}

}
