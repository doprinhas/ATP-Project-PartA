package alogrithms.search;

import alogrithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState (Object o, AState prev , double coast){
        super(o , prev , coast);
    }

    public MazeState(Object position , double coast){
        super(position, coast);
    }

    public MazeState(Object position){
        super(position);
    }

    public MazeState(AState state){
        this.m_state = state.m_state;
        this.m_prev = state.m_prev;
        this.m_coast = state.m_coast;
        this.m_pathCoast = state.m_pathCoast;
    }

}
