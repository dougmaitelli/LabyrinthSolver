package br.gangof4.labyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Labyrinth {

    private Block[][] blocks;
    private Point entrance;
    private Point exit;
    private Point current;
    private List<Point> path = new LinkedList<Point>();

    public Labyrinth(Block[][] blocks) {
        this.blocks = blocks;
    }

    public Point getEntrance() {
        return entrance;
    }

    public void setEntrance(Point entrance) {
        this.entrance = entrance;

        this.current = entrance;
    }

    public Point getExit() {
        return exit;
    }

    public void setExit(Point exit) {
        this.exit = exit;
    }

    public Point getCurrent() {
        return current;
    }

    public void setCurrent(Point current) {
        this.current = current;
    }

    public List<Point> getPath() {
        return path;
    }

    public Block getBlock(int x, int y) {
        return blocks[x][y];
    }
    
    public void setBlock(int x, int y, Block block) {
        block.setLabyrinth(this);
        
        this.blocks[x][y] = block;
    }

    public boolean resolve() {
        while (!current.equals(exit)) {
            List<Block> posibilities = new ArrayList<Block>();

            if (current.getY() > 0) {
                posibilities.add(blocks[current.getX()][current.getY() - 1]);
            }

            if (current.getX() < blocks.length - 1) {
                posibilities.add(blocks[current.getX() + 1][current.getY()]);
            }

            if (current.getY() < blocks[0].length - 1) {
                posibilities.add(blocks[current.getX()][current.getY() + 1]);
            }

            if (current.getX() > 0) {
                posibilities.add(blocks[current.getX() - 1][current.getY()]);
            }

            Collections.sort(posibilities);

            boolean found = false;
            for (Block b : posibilities) {
                if (b.isWalkable()) {
                    path.add(b.getPoint());
                    b.walkTo();
                    current = b.getPoint();
                    found = true;
                    break;
                }
            }
            
            if (found) {
                continue;
            }
            
            return false;
        }

        return true;
    }
}
