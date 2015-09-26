package br.gangof4.labyrinthsolver.base;

import java.lang.reflect.Array;

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

}
