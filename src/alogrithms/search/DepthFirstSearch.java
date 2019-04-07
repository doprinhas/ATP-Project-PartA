package alogrithms.search;

import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch(){
        super();
        m_states = new HashMap<>();
    }

    public String getName(){ return "Depth First Search"; }


    @Override
    public Solution solve(ISearchable prob) {

        if (prob == null)
            return null;
        resetProb();

        AState state = solve(prob , prob.getStartState());
        return new Solution(super.getSolutionPath(state));
    }

    /**
     * help function that implements the DFS algorithm
     * and returns the last state (Goal state)
     */
    private AState solve(ISearchable prob , AState state){

        Stack<AState> stack = new Stack<>();
        stack.push(state);

        AState curr_state;

        while (!stack.isEmpty()){
            curr_state = stack.pop();
            if (curr_state.equals(prob.getGoalState()))
                return curr_state;
            else {
                if (!m_states.containsKey(curr_state.toString())) {
                    m_states.put(curr_state.toString() , curr_state);
                    for (AState s : prob.getAllSuccessors(curr_state)) {
                        if (!m_states.containsKey(s.toString())) {
                            s.setPrev(curr_state);
                            stack.push(s);
                            evaluatedNodes++;
                        }
                    }
                }
            }
        }
        return null;
    }



}
