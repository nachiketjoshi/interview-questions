package com.yext;

public class TreeTraversal {

	public static Node lca(Node a, Node b) {

		int heightA = height(a);
		int heightB = height(b);

		if (heightA > heightB) {
			for (int i = heightA - heightB; i-- > 0;) {
				a = a.parent;
			}
		} else if (heightB > heightA) {
			for (int i = heightB - heightA; i-- > 0;) {
				b = b.parent;
			}
		}

		while (a != null && b != null) {
			if (a.equals(b))
				return a;
			a = a.parent;
			b = b.parent;
		}
		return null;
	}

	private static int height(Node n) {
		int i = 0;
		while (n.parent != null) {
			i++;
			n = n.parent;
		}
		return i;
	}

	private class Node {
		public Node parent;
	}
}
