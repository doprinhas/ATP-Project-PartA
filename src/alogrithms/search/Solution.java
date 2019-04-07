package alogrithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> m_sol;

    /**
     * Default Constructor
     * @param sol - ArrayList of AState
     */
    Solution(ArrayList<AState> sol){
        m_sol = new ArrayList<>();
        if (sol != null && sol.size() > 0)
            m_sol.addAll(sol);
    }

    /**
     * Returns an ArrayList of the Solution Path
     * @return ArrayList of the Solution Path
     */
    public ArrayList<AState> getSolutionPath(){
        return m_sol;
    }
}
