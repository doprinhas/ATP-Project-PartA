package alogrithms.search;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int evaluatedNodes;
    protected HashMap<String, AState> m_states;

    protected ASearchingAlgorithm(){
        m_states = new HashMap<>();
        evaluatedNodes = 0;
    }

    public int getNumberOfNodesEvaluated(){
        return evaluatedNodes;
    }

    public abstract String getName();

    protected ArrayList<AState> getSolutionPath(AState state){

        if (state == null)
            return null;

        ArrayList<AState> result_path = new ArrayList<>();

        while (state.getPrev() != null){
            result_path.add(0 , state);
            state = state.getPrev();
        }
        result_path.add(0 , state);
        return result_path;
    }

}
