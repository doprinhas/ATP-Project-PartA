package alogrithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * default constructor
     */
    public BestFirstSearch() {
        super();
    }

    public String getName(){ return "Best First Search"; }
    /**
     * This function solve a searchable problem with Breadth first search algorithm
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

        AState state = solve(prob, queue);
        return new Solution(super.getSolutionPath(state));
    }
}
