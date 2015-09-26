package br.gangof4.labyrinthsolver.labyrinth;

import br.gangof4.labyrinthsolver.base.AbstractLabyrinth;

public class Labyrinth extends AbstractLabyrinth<Block> {
    
	public Labyrinth(int width, int height) {
		super(width, height, Block.class);
	}
    
}
