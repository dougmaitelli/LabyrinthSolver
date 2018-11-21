package br.gangof4.labyrinthsolver.path;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.gangof4.labyrinthsolver.labyrinth.Labyrinth;

public class LabyrinthSolver {

	private LabyrinthIteration labyrinth;
	private boolean solutionFound = false;
	private int minOptimalDistance = -1;
	private List<WalkBlock> path;

	public LabyrinthSolver(Labyrinth labyrinth) {
		this.labyrinth = new LabyrinthIteration(labyrinth);
	}

	public LabyrinthIteration getLabyrinth() {
		return labyrinth;
	}

	public boolean solve() {
		if (this.solutionFound) {
			return true;
		}

		Queue<WalkBlock> toCalculate = new LinkedList<WalkBlock>();
		toCalculate.add(labyrinth.getEntrance());
		labyrinth.getEntrance().setOptimalStepDistance(0);

		this.solutionFound = false;

		while (toCalculate.size() > 0) {
			WalkBlock current = toCalculate.remove();

			List<WalkBlock> posibilities = current.getWalkableAdjacentBlocks();

			for (WalkBlock nb : posibilities) {
				if (nb.isEntrance()) {
					continue;
				}

				// Skip next blocks if their distance is already higher than the found one
				if (this.minOptimalDistance != -1 && this.minOptimalDistance <= current.getOptimalStepDistance()) {
					continue;
				}

				if (nb.isWalkable()) {
					if (nb.getOptimalStepDistance() == -1
							|| current.getOptimalStepDistance() < nb.getOptimalStepDistance() - 1) {
						nb.walkFrom(current);

						if (nb.isExit()) {
							this.solutionFound = true;

							// Update the total distance if a better one is found
							if (this.minOptimalDistance == -1
									|| nb.getOptimalStepDistance() < this.minOptimalDistance) {
								this.minOptimalDistance = nb.getOptimalStepDistance();
							}

							continue;
						}

						// Recalculating blocks is OK, but not necessary if they are already pending for calculation
						if (!toCalculate.contains(nb)) {
							toCalculate.add(nb);
						}

					}
				}
			}
		}

		return this.solutionFound;
	}

	public List<WalkBlock> getPath() {
		if (this.path != null) {
			return this.path;
		}

		this.path = new ArrayList<WalkBlock>();

		if (this.solutionFound) {
			WalkBlock current = this.labyrinth.getExit();

			while (!current.isEntrance()) {
				List<WalkBlock> posibilities = current.getWalkableAdjacentBlocks();

				WalkBlock next = null;

				for (WalkBlock nb : posibilities) {
					if (nb.getOptimalStepDistance() == -1) {
						continue;
					}

					if (current.getOptimalStepDistance() <= nb.getOptimalStepDistance()) {
						continue;
					}

					if (next == null || nb.getOptimalStepDistance() < next.getOptimalStepDistance()) {
						next = nb;
					}
				}

				path.add(0, next);
				current = next;
			}
		}

		return this.path;
	}

	public int getTotalDistance() {
		return this.minOptimalDistance;
	}
}
