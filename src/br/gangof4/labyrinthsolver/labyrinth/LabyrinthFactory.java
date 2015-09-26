package br.gangof4.labyrinthsolver.labyrinth;

import br.gangof4.labyrinthsolver.base.Point;

public class LabyrinthFactory {
	
	private int width;
	private int height;
    public int wallProbability = 5;
	
	public LabyrinthFactory(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWallProbability() {
		return wallProbability;
	}

	public LabyrinthFactory setWallProbability(int wallProbability) {
		this.wallProbability = wallProbability;
		return this;
	}
	
	private Point randomPoint() {
        return new Point((int) (Math.random() * width), (int) (Math.random() * height));
    }

	public Labyrinth generate() {
		Labyrinth lab = new Labyrinth(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean isWalkable = true;

                if ((int) (Math.random() * wallProbability) % wallProbability == 0) {
                    isWalkable = false;
                }

                lab.setBlock(x, y, new Block(lab, new Point(x, y), isWalkable));
            }
        }

        lab.setEntrance(lab.getBlock(randomPoint().setX(0)));
        lab.getEntrance().setWalkable(true);
        lab.setExit(lab.getBlock(randomPoint().setX(width - 1)));
        lab.getExit().setWalkable(true);
        
        return lab;
	}
}
