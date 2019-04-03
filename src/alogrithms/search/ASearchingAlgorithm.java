package alogrithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected ISearchable m_prob;
    protected int evaluatedNodes;

    ASearchingAlgorithm(ISearchable prob){
        if (m_prob != null) {
            m_prob = prob;
            evaluatedNodes = 0;
        }
    }

    public int getNumberOfNodesEvaluated(){
        return evaluatedNodes;
    }

    public String getName(){
        return "";
    }

    protected ArrayList<AState> getSolutionPath(AState state){

        ArrayList<AState> result_path = new ArrayList<>();

        while (!state.equals(m_prob.getStartState())){
            result_path.add(0 , state);
            state = state.getPrev();
        }

        return result_path;
    }

}
