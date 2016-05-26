package com.nachi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MaximumElement {
	
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int numLines = in.nextInt();
        Deque<Integer> stack = new ArrayDeque<>();
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b.compareTo(a));
        for (int i = 0; i < numLines; i++) {
            int command = in.nextInt();
            if (command == 1) {
            	Integer newElement = Integer.valueOf(in.nextInt());
                stack.push(newElement);
                queue.add(newElement);
            } else if (command == 2) {
                queue.remove(stack.pop());
            } else if (command == 3) {
                System.out.println(queue.peek());
            }
        }
        in.close();
    }
}
