package br.gangof4.labyrinthsolver.labyrinth;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import br.gangof4.labyrinthsolver.base.Point;

class MazeGenerator {

	private int width;
	private int height;
	private int[][] maze;
	
	private Stack<Point> stack = new Stack<>();
	private Random rand = new Random();

	public MazeGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		this.maze = new int[width][height];
	}

	public int[][] generateMaze() {
		stack.push(new Point(0, 0));
		
		while (!stack.empty()) {
			Point next = stack.pop();
			
			if (validNextNode(next)) {
				maze[next.getY()][next.getX()] = 1;
				ArrayList<Point> neighbors = findNeighbors(next);
				randomlyAddNodesToStack(neighbors);
			}
		}
		
		return maze;
	}

	private boolean validNextNode(Point node) {
		int numNeighboringOnes = 0;
		
		for (int y = node.getY() - 1; y < node.getY() + 2; y++) {
			for (int x = node.getX() - 1; x < node.getX() + 2; x++) {
				if (pointOnGrid(x, y) && pointNotNode(node, x, y) && maze[y][x] == 1) {
					numNeighboringOnes++;
				}
			}
		}
		
		return (numNeighboringOnes < 3) && maze[node.getY()][node.getX()] != 1;
	}

	private void randomlyAddNodesToStack(ArrayList<Point> nodes) {
		int targetIndex;
		
		while (!nodes.isEmpty()) {
			targetIndex = rand.nextInt(nodes.size());
			stack.push(nodes.remove(targetIndex));
		}
	}

	private ArrayList<Point> findNeighbors(Point node) {
		ArrayList<Point> neighbors = new ArrayList<>();
		
		for (int y = node.getY() - 1; y < node.getY() + 2; y++) {
			for (int x = node.getX() - 1; x < node.getX() + 2; x++) {
				if (pointOnGrid(x, y) && pointNotCorner(node, x, y) && pointNotNode(node, x, y)) {
					neighbors.add(new Point(x, y));
				}
			}
		}
		
		return neighbors;
	}

	private Boolean pointOnGrid(int x, int y) {
		return x >= 0 && y >= 0 && x < this.width && y < this.height;
	}

	private Boolean pointNotCorner(Point node, int x, int y) {
		return (x == node.getX() || y == node.getY());
	}

	private Boolean pointNotNode(Point node, int x, int y) {
		return !(x == node.getX() && y == node.getY());
	}
}