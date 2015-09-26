package br.gangof4.labyrinthsolver.base;

import java.util.ArrayList;
import java.util.List;

public class AbstractBlock<B extends AbstractBlock<?>> implements Comparable<B> {

    private AbstractLabyrinth<B> labyrinth;
    private Point point;
    private boolean walkable = true;

    public AbstractBlock(AbstractLabyrinth<B> labyrinth, Point point) {
        this.labyrinth = labyrinth;
        this.point = point;
    }

    public AbstractBlock(AbstractLabyrinth<B> labyrinth, Point point, boolean walkable) {
        this.labyrinth = labyrinth;
        this.point = point;
        this.walkable = walkable;
    }

    public AbstractLabyrinth<?> getLabyrinth() {
        return labyrinth;
    }

    public void setLabyrinth(AbstractLabyrinth<B> labyrinth) {
        this.labyrinth = labyrinth;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
    
    public B getBlockOnRight() {
    	if (getPoint().getX() == labyrinth.getWidth() - 1) {
    		return null;
    	}
    	
    	return labyrinth.getBlock(this.getPoint().getX() + 1, this.getPoint().getY());
    }
    
    public B getBlockOnLeft() {
    	if (getPoint().getX() == 0) {
    		return null;
    	}
    	
    	return labyrinth.getBlock(this.getPoint().getX() - 1, this.getPoint().getY());
    }
    
    public B getBlockOnTop() {
    	if (getPoint().getY() == 0) {
    		return null;
    	}
    	
    	return labyrinth.getBlock(this.getPoint().getX(), this.getPoint().getY() - 1);
    }
    
    public B getBlockBelow() {
    	if (getPoint().getY() == labyrinth.getHeight() - 1) {
    		return null;
    	}
    	
    	return labyrinth.getBlock(this.getPoint().getX(), this.getPoint().getY() + 1);
    }
    
    public List<B> getNeightbors() {
    	List<B> neightbors = new ArrayList<B>();
    	
    	B block;
    	block = getBlockOnRight();
    	if (block != null) {
    		neightbors.add(block);
    	}
    	block = getBlockOnLeft();
    	if (block != null) {
    		neightbors.add(block);
    	}
    	block = getBlockOnTop();
    	if (block != null) {
    		neightbors.add(block);
    	}
    	block = getBlockBelow();
    	if (block != null) {
    		neightbors.add(block);
    	}
    	
    	return neightbors;
    }
    
    public int compareTo(B o) {
        Double d1 = Point.getDistance(labyrinth.getExit().getPoint(), this.getPoint());
        Double d2 = Point.getDistance(labyrinth.getExit().getPoint(), o.getPoint());

        return d1.compareTo(d2);
    }
}
