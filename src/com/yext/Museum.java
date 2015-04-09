package com.yext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Museum {

	public void museum(int[][] museum, List<Coordinate> guards,
			List<Coordinate> closed) {
		Queue<Coordinate> q = new LinkedList<>();
		q.addAll(guards);
		while (!q.isEmpty()) {
			Coordinate c = q.poll();
			for (Coordinate neighbor : getNeighbors(c, museum.length)) {
				if (closed.contains(neighbor))
					continue;
				if (guards.contains(neighbor))
					continue;
				if (museum[neighbor.x][neighbor.y] != 0)
					continue;
				museum[neighbor.x][neighbor.y] = museum[c.x][c.y] + 1;
				q.add(neighbor);
			}
		}
	}

	private List<Coordinate> getNeighbors(Coordinate c, int max) {
		List<Coordinate> l = new ArrayList<>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue;
				if (i != 0 && j != 0)
					continue;
				Coordinate n = new Coordinate(c.x + i, c.y + j);
				if (n.x < 0 || n.x > max - 1 || n.y < 0 || n.y > max - 1)
					continue;
				l.add(n);
			}
		}
		return l;
	}

	private class Coordinate {
		public final int x;
		public final int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
