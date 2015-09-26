package br.gangof4.labyrinthsolver.path;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.gangof4.labyrinthsolver.labyrinth.Labyrinth;

public class LabyrinthSolver {

	private LabyrinthIteration labyrinth;
    private WalkBlock current;
    private List<WalkBlock> path = new LinkedList<WalkBlock>();
    
    public LabyrinthSolver(Labyrinth labyrinth) {
    	this.labyrinth = new LabyrinthIteration(labyrinth);
    }
    
    public LabyrinthIteration getLabyrinth() {
		return labyrinth;
	}
    
    public List<WalkBlock> getPath() {
        return path;
    }
	
	public boolean solve() {
		current = labyrinth.getEntrance();
		
		while (!current.equals(labyrinth.getExit())) {
            List<WalkBlock> posibilities = current.getNeightbors();

            Collections.sort(posibilities);

            boolean found = false;
            for (WalkBlock b : posibilities) {
                if (b.isWalkable()) {
                    path.add(b);
                    b.walkTo();
                    current = b;
                    
                    // WRONG!
                    //if (current.getPassCount() >= 4) {
                    //	return false;
                    //}
                    
                    found = true;
                    break;
                }
            }
            
            if (found) {
                continue;
            }
            
            return false;
        }

        return true;
	}
}
