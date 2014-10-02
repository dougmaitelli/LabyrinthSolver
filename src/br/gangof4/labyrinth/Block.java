package br.gangof4.labyrinth;

public class Block implements Comparable<Block> {

    private Labyrinth labyrinth;
    private Point point;
    private boolean walkable = true;
    private int passCount = 0;

    public Block(Labyrinth labyrinth, Point point) {
        this.labyrinth = labyrinth;
        this.point = point;
    }

    public Block(Point point, boolean walkable) {
        this.point = point;
        this.walkable = walkable;
    }

    public Labyrinth getLabyrinth() {
        return labyrinth;
    }

    public void setLabyrinth(Labyrinth labyrinth) {
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

    public int getPassCount() {
        return passCount;
    }

    public void walkTo() {
        passCount++;
    }

    @Override
    public int compareTo(Block o) {
        Integer pC1 = this.getPassCount();
        Integer pC2 = o.getPassCount();

        Integer c = pC1.compareTo(pC2);

        if (c == 0) {
            Double d1 = Point.getDistance(labyrinth.getExit(), this.getPoint());
            Double d2 = Point.getDistance(labyrinth.getExit(), o.getPoint());

            return d1.compareTo(d2);
        }

        return c;
    }
}
