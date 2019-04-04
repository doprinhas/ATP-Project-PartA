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
        MazeState state = new MazeState(m_maze.getStartPosition());
        return state;
    }

    @Override
    public AState getGoalState() {
        MazeState state = new MazeState(m_maze.getGoalPosition());
        return state;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> successors = new ArrayList<>();

        return null;
    }
}
