package alogrithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> m_sol;

    Solution(ArrayList<AState> sol){

        m_sol = new ArrayList<>();
        if (sol != null && sol.size() > 0)
            for (AState s: sol)
                m_sol.add(s);
    }

    public ArrayList<AState> getSolutionPath(){
        return m_sol;
    }
}
