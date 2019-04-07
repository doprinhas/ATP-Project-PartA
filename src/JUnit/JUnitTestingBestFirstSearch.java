package JUnit;

import algorithms.mazeGenerators.*;
import algorithms.search.AState;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {
    private AMazeGenerator m_gEmpty = new EmptyMazeGenerator();
    private AMazeGenerator m_gSimple = new SimpleMazeGenerator();
    private AMazeGenerator m_gMy = new MyMazeGenerator();
    // Empty maze
    private Maze emptySmallMaze = m_gEmpty.generate(0, 0);
    private Maze emptyBigMaze = m_gEmpty.generate(1000, 1000);
    private Maze emptyWrongMaze = m_gEmpty.generate(-10, -10);
    private SearchableMaze s_mSmallEmpty = new SearchableMaze(emptySmallMaze);
    private SearchableMaze s_mBigEmpty = new SearchableMaze(emptyBigMaze);
    private SearchableMaze s_mWrongEmpty = new SearchableMaze(emptyWrongMaze);

    // Simple maze
    private Maze simpleSmallMaze = m_gSimple.generate(55, 11);
    private Maze simpleBigMaze = m_gSimple.generate(1000, 750);
    private Maze simpleWrongMaze = m_gSimple.generate(-10, -10);
    private SearchableMaze s_mSmallSimple = new SearchableMaze(simpleSmallMaze);
    private SearchableMaze s_mBigSimple = new SearchableMaze(simpleBigMaze);
    private SearchableMaze s_mWrongSimple = new SearchableMaze(simpleWrongMaze);

    // My maze
    private Maze MySmallMaze = m_gMy.generate(9, 61);
    private Maze MyBigMaze = m_gMy.generate(650, 1000);
    private Maze MyWrongMaze = m_gMy.generate(-10, -10);
    private SearchableMaze s_mSmallMy = new SearchableMaze(MySmallMaze);
    private SearchableMaze s_mBigMy = new SearchableMaze(MyBigMaze);
    private SearchableMaze s_mWrongMy = new SearchableMaze(MyWrongMaze);

    private BestFirstSearch bfs = new BestFirstSearch();


    @Test
    @SuppressWarnings("Duplicates")
    void solve() {
        Solution sol1;
        Solution sol2;

        // Empty maze
        sol1 = bfs.solve(s_mSmallEmpty);
        sol2 = bfs.solve(s_mBigEmpty);
        assertTrue(checkSol(sol1, emptySmallMaze, s_mSmallEmpty));
        assertTrue(checkSol(sol2, emptyBigMaze, s_mBigEmpty));

        // Simple maze
        sol1 = bfs.solve(s_mSmallSimple);
        sol2 = bfs.solve(s_mBigSimple);
        assertTrue(checkSol(sol1, simpleSmallMaze, s_mSmallSimple));
        assertTrue(checkSol(sol2, simpleBigMaze, s_mBigSimple));

        // My maze
        sol1 = bfs.solve(s_mSmallMy);
        sol2 = bfs.solve(s_mBigMy);
        assertTrue(checkSol(sol1, MySmallMaze, s_mSmallMy));
        assertTrue(checkSol(sol2, MyBigMaze, s_mBigMy));

        // Wrong maze
        sol1 = bfs.solve(s_mWrongEmpty);
        assertTrue(checkSol(sol1, emptyWrongMaze, s_mWrongEmpty));
        sol2 = bfs.solve(s_mWrongMy);
        assertTrue(checkSol(sol2, MyWrongMaze, s_mWrongMy));
        sol1 = bfs.solve(s_mWrongSimple);
        assertTrue(checkSol(sol1, simpleWrongMaze, s_mWrongSimple));


    }

    private boolean checkSol(Solution sol, Maze maze, SearchableMaze sm) {
        ArrayList<AState> solPath = sol.getSolutionPath();
        for (int i=0; i<solPath.size(); i++) {
            AState state = solPath.get(i);
            Position pos;
            if(state.getM_state() instanceof  Position)
                pos = (Position)state.getM_state();
            else
                return false;

            if(i == 0 && !pos.equals(maze.getStartPosition()))
                return false;

            if(maze.getValue(pos) == 1)
                return false;

            if(i != solPath.size()-1 && !sm.getAllSuccessors(state).contains(solPath.get(i+1)))
                return false;
        }

        Position goalPos = (Position)solPath.get(solPath.size()-1).getM_state();
        return goalPos.equals(maze.getGoalPosition());

    }
}