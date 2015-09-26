package br.gangof4.labyrinthsolver;

import br.gangof4.labyrinthsolver.base.AbstractBlock;
import br.gangof4.labyrinthsolver.base.AbstractLabyrinth;
import br.gangof4.labyrinthsolver.labyrinth.Labyrinth;
import br.gangof4.labyrinthsolver.labyrinth.LabyrinthFactory;
import br.gangof4.labyrinthsolver.path.LabyrinthSolver;
import br.gangof4.labyrinthsolver.path.WalkBlock;

public class Main {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 10;
    public static final int WALL_PROBALITY = 5;

    public static void main(String[] args) {
        Labyrinth lab = new LabyrinthFactory(WIDTH,  HEIGHT).setWallProbability(WALL_PROBALITY).generate();
        
        printLabyrinth(lab, null);

        System.out.println();
        System.out.println();
        System.out.println();
        
        LabyrinthSolver solver = new LabyrinthSolver(lab);

        if (solver.solve()) {
            printLabyrinth(solver.getLabyrinth(), solver);
        }

        System.out.println("Entrance: " + lab.getEntrance().getPoint());
        System.out.println("Exit: " + lab.getExit().getPoint());
        System.out.print("S->");
        for (WalkBlock b : solver.getPath()) {
            System.out.print(b.getPoint() + "->");
        }
        System.out.println("E");
    }

    public static <B extends AbstractBlock<?>> void printLabyrinth(AbstractLabyrinth<B> lab, LabyrinthSolver solver) {
        for (int z = 0; z <= WIDTH * 2; z++) {
            System.out.print("-");
        }
        System.out.println();

        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("|");

            for (int x = 0; x < WIDTH; x++) {
                if (lab.getBlock(x, y).equals(lab.getEntrance())) {
                    System.out.print("E");
                } else if (lab.getBlock(x, y).equals(lab.getExit())) {
                    System.out.print("S");
                } else {
                    if (solver != null && solver.getPath().contains(lab.getBlock(x, y))) {
                        System.out.print("X");
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
