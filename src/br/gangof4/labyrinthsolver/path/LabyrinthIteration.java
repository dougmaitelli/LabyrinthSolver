package br.gangof4.labyrinthsolver.path;

import br.gangof4.labyrinthsolver.base.AbstractLabyrinth;
import br.gangof4.labyrinthsolver.labyrinth.Labyrinth;

public class LabyrinthIteration extends AbstractLabyrinth<WalkBlock> {
    
    public LabyrinthIteration(Labyrinth labyrinth) {
    	super(labyrinth.getWidth(), labyrinth.getHeight(), WalkBlock.class);
    	
    	for (int y = 0; y < labyrinth.getHeight(); y++) {
            for (int x = 0; x < labyrinth.getWidth(); x++) {
            	blocks[x][y] = new WalkBlock(this, labyrinth.getBlock(x, y));
            }
        }
    	
    	this.setEntrance(this.getBlock(labyrinth.getEntrance().getPoint()));
    	this.setExit(this.getBlock(labyrinth.getExit().getPoint()));
    }
    
}
