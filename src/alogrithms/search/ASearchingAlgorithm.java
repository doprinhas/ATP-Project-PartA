package alogrithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected ISearchable m_prob;
    protected int evaluatedNodes;


    protected ASearchingAlgorithm(){
        m_prob = null;
        evaluatedNodes = 0;

    }

    protected ASearchingAlgorithm(ISearchable prob){
        if (prob != null) {
            m_prob = prob;
            evaluatedNodes = 0;
        }
    }

    public int getNumberOfNodesEvaluated(){
        return evaluatedNodes;
    }

    public abstract String getName();



    protected ArrayList<AState> getSolutionPath(AState state){

        ArrayList<AState> result_path = new ArrayList<>();

        while (state.getPrev() != null){
            result_path.add(0 , state);
            state = state.getPrev();
        }
        result_path.add(0 , state);
        return result_path;
    }

}
