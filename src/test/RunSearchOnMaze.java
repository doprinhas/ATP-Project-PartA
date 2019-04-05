package test;

import alogrithms.mazeGenerators.IMazeGenerator;
import alogrithms.mazeGenerators.Maze;
import alogrithms.mazeGenerators.MyMazeGenerator;
import alogrithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args){
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(30,30);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        maze.print();


        solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblem(searchableMaze, new DepthFirstSearch());

    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher){
        //solve searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //prints solution path
        System.out.println("Solution Path: ");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0 ; i < solutionPath.size() ; i++)
            System.out.println(String.format("%s.%s", i, solutionPath.get(i)));
    }
}

