package br.gangof4.labyrinthsolver.base;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Point setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Point setY(int y) {
        this.y = y;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Point) obj).getX() && this.y == ((Point) obj).getY();
    }

    @Override
    public String toString() {
        return "P(" + x + "," + y + ")";
    }
    
    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
