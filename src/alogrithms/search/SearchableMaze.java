package alogrithms.search;

import alogrithms.mazeGenerators.Maze;
import alogrithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SearchableMaze implements ISearchable{

    private Maze m_maze;
    private MazeState m_startState;
    private MazeState m_goalState;
    private HashMap<String, MazeState> m_states;

    public SearchableMaze(Maze maze){

        if (maze != null){
            m_maze = maze;
            m_startState = new MazeState(maze.getStartPosition());
            m_goalState  = new MazeState(maze.getGoalPosition());
            m_states = new HashMap<>();
            String key;
            for (int i = 0 ; i < maze.getRows() ; i++)
                for (int j = 0 ; j < maze.getColumns() ; j++)
                    if (maze.isAPass(i , j)) {
                        //key = (i,j)
                        key = "(" + i + "," + j + ")";
                        MazeState ms = new MazeState(maze.getPosition(i,j));
                        ms.setM_pathCoast(Double.POSITIVE_INFINITY);
                        m_states.put(key, ms);
                    }
            m_states.get("(0,0)").setM_pathCoast(0);

        }
    }

    @Override
    public AState getStartState() {
        return m_startState;
    }

    @Override
    public AState getGoalState() {
        return m_goalState;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState curr_state) {

        if (curr_state == null || !(curr_state.getM_state() instanceof Position))
            return null;

        int curr_row = ((Position) curr_state.getM_state()).getRowIndex();
        int curr_col = ((Position) curr_state.getM_state()).getColumnIndex();

        ArrayList<AState> successors = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();

        keys.add(getKey(curr_row - 1, curr_col));
        keys.add(getKey(curr_row - 1, curr_col + 1));
        keys.add(getKey(curr_row , curr_col +1));
        keys.add(getKey(curr_row + 1, curr_col + 1));
        keys.add(getKey(curr_row + 1, curr_col));
        keys.add(getKey(curr_row + 1, curr_col - 1));
        keys.add(getKey(curr_row , curr_col - 1));
        keys.add(getKey(curr_row - 1, curr_col - 1));

        for (String key: keys) {
            if (m_states.containsKey(key))
                successors.add(new MazeState(m_states.get(key)));
        }

        for (AState successor: successors) {
            if (curr_state.getPrev() != null && curr_state.getPrev().equals(successor)){
                successors.remove(successor);
                break;
            }
        }
        setCoast(successors , curr_row , curr_col);
        return successors;
    }

    private String getKey(int row, int col){
        return "(" + row + "," + col + ")";
    }

    private void setCoast(ArrayList<AState> successors, int row , int col){

        ArrayList<String> keys = new ArrayList<>();

        keys.add(getKey(row - 1, col + 1));
        keys.add(getKey(row + 1, col + 1));
        keys.add(getKey(row + 1, col - 1));
        keys.add(getKey(row - 1, col - 1));

        for (AState s: successors) {
            for (String key: keys) {
                if (s.equals(m_states.get(key)))
                    s.setM_coast(15);
                else
                    s.setM_coast(10);
            }
        }
    }

}
