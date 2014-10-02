package br.gangof4.labyrinth;

public class Main {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 10;
    public static final int WALL_PROBALITY = 5;

    public static void main(String[] args) {
        Labyrinth lab = new Labyrinth(new Block[WIDTH][HEIGHT]);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                boolean isWalkable = true;

                if ((int) (Math.random() * WALL_PROBALITY) % WALL_PROBALITY == 0) {
                    isWalkable = false;
                }

                lab.setBlock(x, y, new Block(new Point(x, y), isWalkable));
            }
        }

        lab.setEntrance(randomPoint());
        lab.getBlock(lab.getEntrance().getX(), lab.getEntrance().getY()).setWalkable(true);
        lab.setExit(randomPoint());
        lab.getBlock(lab.getExit().getX(), lab.getExit().getY()).setWalkable(true);

        printLabyrinth(lab);

        System.out.println();
        System.out.println();
        System.out.println();

        if (lab.resolve()) {
            printLabyrinth(lab);
        }

        System.out.println("Entrada: " + lab.getEntrance());
        System.out.println("Saida: " + lab.getExit());
        System.out.print("E->");
        for (Point p : lab.getPath()) {
            System.out.print(p + "->");
        }
        System.out.println("S");
    }

    public static Point randomPoint() {
        return new Point((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
    }

    public static void printLabyrinth(Labyrinth lab) {
        for (int z = 0; z <= WIDTH * 2; z++) {
            System.out.print("-");
        }
        System.out.println();

        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("|");

            for (int x = 0; x < WIDTH; x++) {
                if (lab.getBlock(x, y).getPoint().equals(lab.getEntrance())) {
                    System.out.print("E");
                } else if (lab.getBlock(x, y).getPoint().equals(lab.getExit())) {
                    System.out.print("S");
                } else {
                    if (lab.getPath().contains(lab.getBlock(x, y).getPoint())) {
                        System.out.print("*");
                    } else if (lab.getBlock(x, y).isWalkable()) {
                        System.out.print(" ");
                    } else {
                        System.out.print("#");
                    }
                }

                System.out.print("|");
            }

            System.out.println();

            for (int z = 0; z <= WIDTH * 2; z++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
