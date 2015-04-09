package com.handy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCache<K, V> {

	private static final int MAX = 3;
	private final List<Node> list = new LinkedList<>();
	private final Map<K, Node> map = new HashMap<>();

	public V get(K key) {
		if (!this.map.containsKey(key))
			return null;
		Node n = this.map.get(key);
		this.list.remove(n);
		this.list.add(n);
		return n.value;
	}

	public void set(K key, V value) {
		if (this.list.size() < MAX) {
			Node n = new Node(key, value);
			this.list.add(n);
			this.map.put(key, n);
			return;
		}
		this.map.remove(this.list.remove(0).key);
		set(key, value);
	}

	private class Node {
		public K key;
		public V value;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
