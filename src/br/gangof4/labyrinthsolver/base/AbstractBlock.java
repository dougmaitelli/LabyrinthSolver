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
    
    public boolean isEntrance() {
    	return this.labyrinth.getEntrance().equals(this);
    }
    
    public boolean isExit() {
    	return this.labyrinth.getExit().equals(this);
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
    
    public List<B> getAdjacentBlocks() {
    	List<B> nbs = new ArrayList<B>();
    	
    	B block;
    	block = getBlockOnRight();
    	if (block != null) {
    		nbs.add(block);
    	}
    	block = getBlockOnLeft();
    	if (block != null) {
    		nbs.add(block);
    	}
    	block = getBlockOnTop();
    	if (block != null) {
    		nbs.add(block);
    	}
    	block = getBlockBelow();
    	if (block != null) {
    		nbs.add(block);
    	}
    	
    	return nbs;
    }
    
    public List<B> getWalkableAdjacentBlocks() {
    	List<B> nbs = new ArrayList<B>();
    	
    	for (B nb : this.getAdjacentBlocks()) {
    		if (nb.isWalkable()) {
    			nbs.add(nb);
    		}
    	}
    	
    	return nbs;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) {
    		return false;
    	}
    	
    	return this.point.equals(((AbstractBlock<?>) obj).getPoint());
    }
    
    public int compareTo(B o) {
        Double d1 = Point.getDistance(labyrinth.getExit().getPoint(), this.getPoint());
        Double d2 = Point.getDistance(labyrinth.getExit().getPoint(), o.getPoint());

        return d1.compareTo(d2);
    }
    
    @Override
    public String toString() {
    	return this.point.toString();
    }
}
