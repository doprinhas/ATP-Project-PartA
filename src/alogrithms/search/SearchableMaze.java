package alogrithms.search;

import alogrithms.mazeGenerators.Maze;
import alogrithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchableMaze implements ISearchable{

    private Maze m_maze;
    private MazeState m_startState;
    private MazeState m_goalState;
    private HashMap<String, MazeState> m_states;

    final double DIAGONAL_COAST = 15;
    final double STRAIGHT_COAST = 10;

    /**
     * SearchableMaze Constructor
     * @param maze - Maze Object
     */
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

    /**
     * Getter - Returns maze start state
     * @return maze start state
     */
    @Override
    public AState getStartState() {
        return m_startState;
    }

    /**
     * Returns maze goal state
     * @return maze goal state
     */
    @Override
    public AState getGoalState() {
        return m_goalState;
    }

    /**
     * Returns an ArrayList of all successors of a curtain position
     * @param curr_state - AState
     * @return successors of curr_state
     */
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

    /**
     * Returns a string
     * @param row - row number
     * @param col - column number
     * @return String - (row,col)
     */
    private String getKey(int row, int col){
        return "(" + row + "," + col + ")";
    }

    /**
     * This function sets coast for all successors of a curtain state
     * @param successors - ArrayList of successors
     * @param row - row number of the current State
     * @param col - col number of the current State
     */
    private void setCoast(ArrayList<AState> successors, int row , int col){

        ArrayList<String> keys = new ArrayList<>();

        keys.add(getKey(row - 1, col + 1));
        keys.add(getKey(row + 1, col + 1));
        keys.add(getKey(row + 1, col - 1));
        keys.add(getKey(row - 1, col - 1));

        for (AState s: successors) {
            for (String key: keys) {
                if (s.equals(m_states.get(key)))
                    s.setM_coast(DIAGONAL_COAST);
                else
                    s.setM_coast(STRAIGHT_COAST);
            }
        }
    }

}
