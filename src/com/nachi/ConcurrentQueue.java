package com.nachi;

import java.util.concurrent.atomic.AtomicReference;

/** http://www.research.ibm.com/people/m/michael/podc-1996.pdf */
public class ConcurrentQueue<V> {

	private AtomicReference<Node> Head;
	private AtomicReference<Node> Tail;

	public ConcurrentQueue() {
		Node init = new Node(null); // Allocate a free node
		this.Head = new AtomicReference<>(init); // Both Head and Tail point to it
		this.Tail = new AtomicReference<>(init); // Both Head and Tail point to it
	}

	public void enqueue(V value) {
		Node node = new Node(value); // Allocate a new node
		Node tail;
		while (true) { // Keep trying until Enqueue is done
			tail = this.Tail.get();
			Node next = tail.next.get();
			if (tail == this.Tail.get()) { // Are tail and next consistent?
				if (next == null) { // Was Tail pointing to the last node?
					if (tail.next.compareAndSet(next, node)) // Try to link node at the end of the linked list
						break; // Enqueue is done. Exit loop

				} else // Tail was not pointing to the last node
					this.Tail.compareAndSet(tail, next); // Try to swing Tail to the next node
			}
		}
		this.Tail.compareAndSet(tail, node); // Enqueue is done. Try to swing Tail to the inserted node
	}

	public V dequeue() {
		while (true) { // Keep trying until Dequeue is done
			Node head = this.Head.get();
			Node tail = this.Tail.get();
			Node next = head.next.get();
			if (head == this.Head.get()) { // Are head, tail, and next consistent?
				if (head == tail) { // Is queue empty or Tail falling behind?
					if (next == null) // Is queue empty?
						return null; // Queue is empty, couldn’t dequeue
					this.Tail.compareAndSet(tail, next); // Tail is falling behind. Try to advance it
				} else { // No need to deal with Tail
					V value = next.value; // Read value before CAS, otherwise another dequeue might free the next node
					if (this.Head.compareAndSet(head, next)) // Try to swing Head to the next node
						return value; // Queue was not empty, dequeue succeeded
				}
			}
		}
	}

	private class Node {

		final AtomicReference<Node> next;
		final V value;

		public Node(V value) {
			this.next = new AtomicReference<>(null); // Set next pointer of node to NULL
			this.value = value; // Copy enqueued value into node
		}
	}
}
