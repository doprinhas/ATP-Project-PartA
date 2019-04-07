package alogrithms.search;

import alogrithms.mazeGenerators.Position;

public class MazeState extends AState{

    /**
     * MazeState Constructor
     * @param obj - maze state
     */
    MazeState(Object obj){
        if (!(obj instanceof Position)){}
        this.m_state = obj;
        this.m_prev = null;
        this.m_coast = -1;
    }

    /**
     * Copy Constructor
     * @param state - maze state
     */
    MazeState(AState state){
        this.m_state = state.m_state;
        this.m_prev = state.m_prev;
        this.m_coast = state.m_coast;
        this.m_pathCoast = state.m_pathCoast;
    }

}
