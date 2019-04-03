package alogrithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> m_sol = null;

    Solution(ArrayList<AState> sol){
        if (sol != null && sol.size() > 0){
            for (AState s: sol) {
                m_sol.add(s);
                sol.remove(s);
            }
        }
    }

    public ArrayList<AState> getSolutionPath(){
        return m_sol;
    }
}
