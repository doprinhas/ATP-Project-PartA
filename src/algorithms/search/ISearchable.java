package algorithms.search;

import java.util.ArrayList;

/**
 * Interface for a Searchable Problem
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState s);
}
