package alogrithms.search;

import alogrithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {
    AMazeGenerator m_gEmpty = new EmptyMazeGenerator();
    AMazeGenerator m_gSimple = new SimpleMazeGenerator();
    AMazeGenerator m_gMy = new MyMazeGenerator();
    // Empty maze
    Maze emptySmallMaze = m_gEmpty.generate(11, 55);
    Maze emptyBigMaze = m_gEmpty.generate(1000, 1000);
    Maze emptyWrongMaze = m_gEmpty.generate(-10, -10);
    SearchableMaze s_mSmallEmpty = new SearchableMaze(emptySmallMaze);
    SearchableMaze s_mBigEmpty = new SearchableMaze(emptyBigMaze);
    SearchableMaze s_mWrongEmpty = new SearchableMaze(emptyWrongMaze);

    // Simple maze
    Maze simpleSmallMaze = m_gSimple.generate(55, 11);
    Maze simpleBigMaze = m_gSimple.generate(1000, 750);
    Maze simpleWrongMaze = m_gSimple.generate(-10, -10);
    SearchableMaze s_mSmallSimple = new SearchableMaze(simpleSmallMaze);
    SearchableMaze s_mBigSimple = new SearchableMaze(simpleBigMaze);
    SearchableMaze s_mWrongSimple = new SearchableMaze(simpleWrongMaze);

    // My maze
    Maze MySmallMaze = m_gSimple.generate(9, 61);
    Maze MyBigMaze = m_gSimple.generate(650, 1000);
    Maze MyWrongMaze = m_gSimple.generate(-10, -10);
    SearchableMaze s_mSmallMy = new SearchableMaze(MySmallMaze);
    SearchableMaze s_mBigMy = new SearchableMaze(MyBigMaze);
    SearchableMaze s_mWrongMy = new SearchableMaze(MyWrongMaze);

    BestFirstSearch bfs = new BestFirstSearch();
    Solution sol1;
    Solution sol2;


    @Test
    void solve() {
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
            Position pos = null;
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

        if(!solPath.get(solPath.size()-1).equals(maze.getGoalPosition()))
            return false;

        return true;
    }
}