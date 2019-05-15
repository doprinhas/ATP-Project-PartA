package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {

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
