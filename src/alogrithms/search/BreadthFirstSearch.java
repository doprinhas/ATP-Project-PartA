package alogrithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch(ISearchable prob) {
        super(prob);
    }

    /**
     * This function solve a searchable problem with Breadth first search algorithm
     * @param prob - object that implements searchable interface
     * @return Solution object
     */
    @Override
    public Solution solve(ISearchable prob) {

        if (prob == null)
            return null;

        AState state = solve(prob.getStartState());
        return new Solution(super.getSolutionPath(state));
    }
    /**
     * help function that implements the BFS algorithm
     * and returns the last state (Goal state)
     */
    private AState solve(AState state){
        LinkedList<AState> list = new LinkedList<>();
        list.add(state);

        ArrayList<AState> successors;
        AState curr_state;
        //BFS
        while (!list.isEmpty()){
            curr_state = list.removeFirst();
           if (curr_state.equals(m_prob.getGoalState()))
               return curr_state;
            else{
                successors = m_prob.getAllSuccessors(curr_state);
               for (AState s: successors) {
                   if (!s.getM_isDiscoverd()){
                       s.setM_isDiscoverd(true);
                       s.setPrev(curr_state);
                       list.add(s);
                       evaluatedNodes++;
                   }
               }
           }
        }
        return null;
    }

}
