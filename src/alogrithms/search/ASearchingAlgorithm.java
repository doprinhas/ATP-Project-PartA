package alogrithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected ISearchable m_prob;

    ASearchingAlgorithm(ISearchable prob){
        if (m_prob != null)
            m_prob = prob;
    }

}
