package br.gangof4.labyrinthsolver.labyrinth;

import br.gangof4.labyrinthsolver.base.Point;

public class LabyrinthFactory {

	private int width;
	private int height;
	private static int WALL_PROBABILITY = 5;

	public LabyrinthFactory(int width, int height) {
		this.width = width;
		this.height = height;
	}

	private Point randomPoint() {
		return new Point((int) (Math.random() * width), (int) (Math.random() * height));
	}

	public Labyrinth generateRandom() {
		Labyrinth lab = new Labyrinth(width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				boolean isWalkable = true;

				if ((int) (Math.random() * WALL_PROBABILITY) % WALL_PROBABILITY == 0) {
					isWalkable = false;
				}

				lab.setBlock(x, y, new Block(lab, new Point(x, y), isWalkable));
			}
		}

		lab.setEntrance(lab.getBlock(randomPoint()));
		lab.getEntrance().setWalkable(true);
		lab.setExit(lab.getBlock(randomPoint()));
		lab.getExit().setWalkable(true);

		return lab;
	}
	
	public Labyrinth generateMaze() {
		MazeGenerator mg = new MazeGenerator(this.width, this.height);
		int[][] maze = mg.generateMaze();
		
		Labyrinth lab = new Labyrinth(this.width, this.height);
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				boolean isWalkable = maze[y][x] == 1;

				lab.setBlock(x, y, new Block(lab, new Point(x, y), isWalkable));
			}
		}
		
		lab.setEntrance(lab.getBlock(new Point(0,0)));
		lab.getEntrance().setWalkable(true);
		lab.setExit(lab.getBlock(new Point(this.width - 1, this.height - 1)));
		lab.getExit().setWalkable(true);
		
		return lab;
	}

}
