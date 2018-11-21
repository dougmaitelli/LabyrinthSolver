package br.gangof4.labyrinthsolver;

import br.gangof4.labyrinthsolver.labyrinth.Labyrinth;
import br.gangof4.labyrinthsolver.labyrinth.LabyrinthFactory;
import br.gangof4.labyrinthsolver.path.LabyrinthSolver;
import br.gangof4.labyrinthsolver.path.WalkBlock;

public class Main {

	public static final int WIDTH = 25;
	public static final int HEIGHT = 25;

	public static void main(String[] args) {
		System.out.println("Random Labyrinth");
		Labyrinth lab1 = new LabyrinthFactory(WIDTH, HEIGHT).generateRandom();

		printLabyrinthTest(lab1);
		
		System.out.println("");
		System.out.println("---------------");

		System.out.println("Maze Labyrinth");
		Labyrinth lab2 = new LabyrinthFactory(WIDTH, HEIGHT).generateMaze();

		printLabyrinthTest(lab2);
	}

	private static void printLabyrinthTest(Labyrinth lab) {
		System.out.println("Entrance: " + lab.getEntrance().getPoint());
		System.out.println("Exit: " + lab.getExit().getPoint());

		lab.printLabyrinth(null);

		System.out.println();
		System.out.println();

		LabyrinthSolver solver = new LabyrinthSolver(lab);

		boolean solved = solver.solve();

		if (solved) {
			lab.printLabyrinth(solver, true);

			System.out.print("S->");
			for (WalkBlock b : solver.getPath()) {
				System.out.print(b.getPoint() + "->");
			}
			System.out.println("E");

			System.out.println("Total Distance: " + solver.getTotalDistance());
		} else {
			System.out.println("No solution found");
		}
	}
}
