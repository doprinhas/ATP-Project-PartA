package alogrithms.search;

import alogrithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{

    private Maze m_maze;

    SearchableMaze(Maze maze){

        if (maze != null)
            m_maze = maze;

    }

    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        return null;
    }
}
