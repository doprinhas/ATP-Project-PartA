package alogrithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    DepthFirstSearch(ISearchable prob) {
        super(prob);
    }

    @Override
    public Solution solve(ISearchable prob) {

        if (prob == null)
            return null;

        AState state = solve(prob.getStartState());
        return new Solution(super.getSolutionPath(state));
    }

    /**
     * help function that implements the DFS algorithm
     * and returns the last state (Goal state)
     */
    private AState solve(AState state){

        Stack<AState> stack = new Stack<>();
        stack.push(state);

        AState curr_state = null;

        while (!stack.isEmpty()){
            curr_state = stack.pop();
            if (!curr_state.getM_isDiscoverd()){
                curr_state.setM_isDiscoverd(true);
                for (AState s: m_prob.getAllSuccessors(curr_state)) {
                    stack.push(s);
                }
            }
        }
        return null;
    }



}
