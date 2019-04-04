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
    public ArrayList<AState> getAllSuccessors(AState curr_state) {

        if (curr_state == null)
            return null;

        ArrayList<Object> successors = m_maze.getNeighbors(curr_state.m_state);
        ArrayList<AState> AState_successors = new ArrayList<>();
        AState tmp_state;

        for (Object successor: successors) {
            if (!curr_state.getPrev().equals(successor)){
                AState_successors.add(new MazeState(successor , curr_state , -1));

            }
        }
        return AState_successors;
    }
}
