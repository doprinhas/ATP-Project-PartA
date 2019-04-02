package alogrithms.mazeGenerators;

public class SimpleMazeGenerator extends AMazeGenerator {

    public Maze generate(int rows, int columns)
    {
        Maze simpleMaze = new Maze(rows, columns);
        for(int i=0; i<rows; i++)
            for(int j=0; j<columns; j++) {
                if(j%2 == 0)
                    simpleMaze.maze[i][j] = 0;
                else
                    simpleMaze.maze[i][j] = 1;
            }
        makeWay(simpleMaze);
        return simpleMaze;
    }



    public static void main(String[] args) {
        SimpleMazeGenerator a = new SimpleMazeGenerator();
        Maze test = new Maze(5,5);
        for(int i=0; i<test.getmRows(); i++)
            for(int j=0; j<test.getmColumns(); j++)
                test.maze[i][j] = 1;
            a.findPath(test,0,0);
        for(int i=0; i<test.getmRows(); i++) {
            for (int j = 0; j < test.getmColumns(); j++)
                System.out.print(test.maze[i][j]);
        System.out.println();
        }
    }
}
