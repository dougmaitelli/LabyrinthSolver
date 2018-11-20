package br.gangof4.labyrinthsolver.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.gangof4.labyrinthsolver.labyrinth.Labyrinth;

public class LabyrinthSolver {

	private boolean solutionFound = false;
	private LabyrinthIteration labyrinth;
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
		
		Stack<WalkBlock> stack = new Stack<WalkBlock>();
		stack.add(labyrinth.getEntrance());
		labyrinth.getEntrance().setOptimalStepDistance(0);
		
		this.solutionFound = false;
		
		while (stack.size() > 0) {
			WalkBlock current = stack.pop();
			
            List<WalkBlock> posibilities = current.getWalkableAdjacentBlocks();

            for (WalkBlock nb : posibilities) {
            	if (nb.isEntrance()) {
            		continue;
            	}
            	
                if (nb.isWalkable()) {
                	if (nb.getOptimalStepDistance() == -1 || current.getOptimalStepDistance() < nb.getOptimalStepDistance() - 1) {
                		if (nb.isExit()) {
                    		this.solutionFound = true;
                    	} else {
                    		stack.add(nb);
                    	}
                		
                        nb.walkFrom(current);
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
    	if (!this.solutionFound) {
			return -1;
		}
    	
    	return this.getPath().get(this.getPath().size() - 1).getOptimalStepDistance();
    }
}
