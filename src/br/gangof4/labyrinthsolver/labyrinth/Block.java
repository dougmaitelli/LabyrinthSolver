package br.gangof4.labyrinthsolver.labyrinth;

import br.gangof4.labyrinthsolver.base.AbstractBlock;
import br.gangof4.labyrinthsolver.base.Point;

public class Block extends AbstractBlock<Block> {

    public Block(Labyrinth labyrinth, Point point) {
        super(labyrinth, point);
    }

    public Block(Labyrinth labyrinth, Point point, boolean walkable) {
        super(labyrinth, point, walkable);
    }

}
