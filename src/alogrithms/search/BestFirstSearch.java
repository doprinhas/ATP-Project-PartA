package alogrithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

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

        Comparator<AState> pathSorter = Comparator.comparing(AState::getM_pathCoast);
        PriorityQueue<AState> queue = new PriorityQueue<>(pathSorter);
        queue.add(prob.getStartState());

        AState state = solve(prob, queue);
        return new Solution(super.getSolutionPath(state));
    }
/*    *//**
     * help function that implements the BFS algorithm
     * and returns the last state (Goal state)
     *//*
    private AState solve(AState state, ISearchable prob) {

        HashMap<String , AState> close = new HashMap<>();
        queue.add(state);

        ArrayList<AState> successors;
        AState curr_state;
        AState removedState;
        double currPathCoast;
        //BFS
        while (!queue.isEmpty()) {
            curr_state = queue.remove();
            if (curr_state.equals(prob.getGoalState()))
                return curr_state;
            else{
                successors = prob.getAllSuccessors(curr_state);
                for (AState s: successors) {
                    if (!m_states.containsKey(s.toString())){
                        s.setPrev(curr_state);
                        m_states.put(s.toString() , s);
                        queue.add(s);
                        evaluatedNodes++;
                    }
                }
            }

        }
        return null;
    }*/

}
