package com.nachi;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTraversal {

	public void dfs(Node head) {
		head.visited = true;
		// DO STUFF
		if (head.children == null)
			return;
		for (Node child : head.children) {
			if (!child.visited)
				dfs(child);
		}
	}

	public static void bfs(Node head) {
		Queue<Node> toBeProcessed = new LinkedList<>();
		toBeProcessed.add(head);
		head.visited = true;
		while (!toBeProcessed.isEmpty()) {
			Node next = toBeProcessed.poll();
			// DO STUFF
			if (next.children == null)
				continue;
			for (Node child : next.children) {
				if (child.visited)
					continue;
				toBeProcessed.add(child);
				child.visited = true;
			}
		}
	}

	private class Node {
		public List<Node> children;
		public boolean visited;
	}
}
