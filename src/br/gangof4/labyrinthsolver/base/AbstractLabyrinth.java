package br.gangof4.labyrinthsolver.base;

import java.lang.reflect.Array;

import br.gangof4.labyrinthsolver.path.LabyrinthSolver;

public class AbstractLabyrinth<B extends AbstractBlock<?>> {

	private int width;
	private int height;
    protected B[][] blocks;
    private B entrance;
    private B exit;

    @SuppressWarnings("unchecked")
	public AbstractLabyrinth(int width, int height, Class<B> blockType) {
		this.width = width;
		this.height = height;
		
		this.blocks = (B[][]) Array.newInstance(blockType, width, height);
	}
    
    public AbstractLabyrinth(B[][] blocks) {
    	this.width = blocks[0].length;
    	this.height = blocks.length;
    	
        this.blocks = blocks;
    }
    
    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public B getEntrance() {
        return entrance;
    }

    public void setEntrance(B entrance) {
        this.entrance = entrance;
    }

    public B getExit() {
        return exit;
    }

    public void setExit(B exit) {
        this.exit = exit;
    }

    public B getBlock(int x, int y) {
        return blocks[x][y];
    }
    
    public B getBlock(Point point) {
        return getBlock(point.getX(), point.getY());
    }
    
    public void setBlock(int x, int y, B block) {
        this.blocks[x][y] = block;
    }
    
    public void printLabyrinth() {
    	this.printLabyrinth(null);
    }
    
    public void printLabyrinth(LabyrinthSolver solver) {
    	this.printLabyrinth(solver, false);
    }
    
    public void printLabyrinth(LabyrinthSolver solver, boolean printDistances) {
    	this.printLabyrinthWallRow();

        for (int y = 0; y < this.getHeight(); y++) {
            System.out.print("|");

            for (int x = 0; x < this.getWidth(); x++) {
            	B block = this.getBlock(x, y);
            	
                if (block.equals(this.getEntrance())) {
                    System.out.print(" E ");
                } else if (block.equals(this.getExit())) {
                    System.out.print(" S ");
                } else {
                    if (solver != null && solver.getPath().contains(block)) {
                        System.out.print(" X ");
                    } else if (block.isWalkable()) {
                    	if (printDistances) {
                    		System.out.print(String.format("%3d", solver.getLabyrinth().getBlock(x, y).getOptimalStepDistance()));
                    	} else {
                            System.out.print("   ");
                    	}
                    } else {
                        System.out.print("\u2588\u2588\u2588");
                    }
                }
            }

            System.out.println("|");
        }
        
        this.printLabyrinthWallRow();
    }
    
    private void printLabyrinthWallRow() {
        System.out.print("+");
         for (int z = 0; z < this.getWidth(); z++) {
             System.out.print("---");
         }
         System.out.println("+");
    }

}
