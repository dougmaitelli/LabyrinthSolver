package br.gangof4.labyrinthsolver.path;

import br.gangof4.labyrinthsolver.base.AbstractBlock;
import br.gangof4.labyrinthsolver.labyrinth.Block;

public class WalkBlock extends AbstractBlock<WalkBlock> {

	private int optimalStepDistance = -1;

	public WalkBlock(LabyrinthIteration labyrinth, Block block) {
		super(labyrinth, block.getPoint(), block.isWalkable());
	}

	public int getOptimalStepDistance() {
		return optimalStepDistance;
	}
	
	public void setOptimalStepDistance(int optimalStepDistance) {
		this.optimalStepDistance = optimalStepDistance;
	}

	public void walkFrom(WalkBlock block) {
		this.optimalStepDistance = block.getOptimalStepDistance() + 1;
	}

	@Override
	public String toString() {
		return super.toString() + " Distance: " + this.optimalStepDistance;
	}

}
