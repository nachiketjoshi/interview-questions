package com.nachi;

public class ReverseLinkedList {

	public Node reverse(Node head) {
		if (head == null)
			return null;
		if (head.next == null)
			return head;

		Node temp = head.next;
		Node restReversed = reverse(temp);
		temp.next = head;

		return restReversed;
	}

	public static Node reverse2(Node head) {
		if (head == null)
			return null;

		Node two = head.next;
		if (two == null)
			return head;

		Node three = two.next;
		two.next = head;
		head.next = null;

		if (three == null)
			return two;

		Node current = three;
		Node prev = two;

		while (current != null) {
			Node next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}

	private class Node {
		public Node next;
	}
}
