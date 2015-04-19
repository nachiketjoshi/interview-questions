package com.nachi;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 7869319492434763731L;
	private final int maxSize;

	public LRUCache(int maxSize) {
		super(maxSize + 1, 1, true);
		this.maxSize = maxSize;
	}

	@Override
	protected boolean removeEldestEntry(Entry<K, V> eldest) {
		return this.size() > this.maxSize;
	}
}
