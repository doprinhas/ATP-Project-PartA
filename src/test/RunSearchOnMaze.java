package test;

import alogrithms.mazeGenerators.IMazeGenerator;
import alogrithms.mazeGenerators.Maze;
import alogrithms.mazeGenerators.MyMazeGenerator;
import alogrithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze {
    public static void main(String[] args){
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(10,10);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        maze.print();

        long lStartTime = System.nanoTime();
        solveProblem(searchableMaze, new BreadthFirstSearch());
        long lEndTime = System.nanoTime();
        System.out.println("Elapsed time in seconds: " + (double)(lEndTime-lStartTime) / 1000000000);

        lStartTime = System.nanoTime();
        solveProblem(searchableMaze, new DepthFirstSearch());
        lEndTime = System.nanoTime();
        System.out.println("Elapsed time in seconds: " + (double)(lEndTime-lStartTime) / 1000000000);

        lStartTime = System.nanoTime();
        solveProblem(searchableMaze, new BestFirstSearch());
        lEndTime = System.nanoTime();
        System.out.println("Elapsed time in seconds: " + (double)(lEndTime-lStartTime) / 1000000000);
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

