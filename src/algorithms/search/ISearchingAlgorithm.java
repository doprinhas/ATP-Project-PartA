package algorithms.search;

/**
 * Interface for A searching Algorithm
 */
public interface ISearchingAlgorithm {
    Solution solve(ISearchable s);
    int getNumberOfNodesEvaluated();
    String getName();

}
