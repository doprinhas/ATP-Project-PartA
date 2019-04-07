package alogrithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    /**
     * Default Constructor
     */
    public BreadthFirstSearch(){
        super();
        m_states = new HashMap<>();
    }

    /**
     * Returns the Algorithm name
     * @return Algorithm name
     */
    public String getName(){ return "Breadth First Search"; }

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

        Queue<AState> queue = new LinkedList<>();
        queue.add(prob.getStartState());

        AState state = solve(prob, queue);
        return new Solution(super.getSolutionPath(state));
    }

    /**
     * Help function that implements the BFS algorithm
     * and returns the last state (Goal state)
     */
     AState solve(ISearchable prob, Queue<AState> queue){
        ArrayList<AState> successors;
        AState curr_state;
        //BFS
        while (!queue.isEmpty()){
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
    }

}
