package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * Default constructor
     */
    public BestFirstSearch() {
        super();
    }

    /**
     * Returns the Algorithm name
     * @return Algorithm name
     */
    public String getName(){ return "Best First Search"; }

    /**
     * This function solve a searchable problem with Best first search algorithm
     * @param prob - object that implements searchable interface
     * @return Solution object
     */
    @Override
    public Solution solve(ISearchable prob) {

        if (prob == null)
            return null;
        resetProb();

        Comparator<AState> pathSorter = Comparator.comparing(AState::getM_pathCoast);
        PriorityQueue<AState> queue = new PriorityQueue<>(pathSorter);
        queue.add(prob.getStartState());

        AState state = super.solve(prob, queue);
        return new Solution(super.getSolutionPath(state));
    }
}
