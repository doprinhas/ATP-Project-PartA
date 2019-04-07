package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    int evaluatedNodes;
    HashMap<String, AState> m_states;

    /**
     * ASearchingAlgorithm Constructor
     */
    ASearchingAlgorithm(){
        m_states = new HashMap<>();
        evaluatedNodes = 0;
    }

    /**
     * Getter - Returns the number of evaluated nodes
     * @return Number of evaluated nodes
     */
    public int getNumberOfNodesEvaluated(){
        return evaluatedNodes;
    }

    /**
     * Returns the searching algorithm Name
     * @return algorithm name
     */
    public abstract String getName();

    /**
     * This function gets a State and return an Array list that contains
     * the path to that state
     * @param state - AState
     * @return the path to the state
     */
    ArrayList<AState> getSolutionPath(AState state){

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

    /**
     * Resets the problem variables
     */
    void resetProb(){
        m_states = new HashMap<>();
        evaluatedNodes = 0;
    }

}
