package br.gangof4.labyrinthsolver.path;

import br.gangof4.labyrinthsolver.base.AbstractBlock;
import br.gangof4.labyrinthsolver.base.Point;
import br.gangof4.labyrinthsolver.labyrinth.Block;

public class WalkBlock extends AbstractBlock<WalkBlock> {
	
    private int passCount = 0;
    
    public WalkBlock(LabyrinthIteration labyrinth, Block block) {
        super(labyrinth, block.getPoint(), block.isWalkable());
    }
    
    public int getPassCount() {
        return passCount;
    }

    public void walkTo() {
        passCount++;
    }
    
    public int compareTo(WalkBlock o) {
        Integer pC1 = this.getPassCount();
        Integer pC2 = o.getPassCount();

        Integer c = pC1.compareTo(pC2);

        if (c == 0) {
            Double d1 = Point.getDistance(getLabyrinth().getExit().getPoint(), this.getPoint());
            Double d2 = Point.getDistance(getLabyrinth().getExit().getPoint(), o.getPoint());

            return d1.compareTo(d2);
        }

        return c;
    }

}
