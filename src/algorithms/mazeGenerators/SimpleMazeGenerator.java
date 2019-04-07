package algorithms.mazeGenerators;

/**
 * The class generates simple mazes.
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    public Maze generate(int rows, int columns)
    {
        Maze simpleMaze = new Maze(rows, columns);
        simpleMaze.setStartPos(new Position(0,0));
        for(int i=0; i<rows; i++)
            for(int j=0; j<columns; j++) {
                simpleMaze.maze[i][j] = 1;
            }
        findPath(simpleMaze);
        randomWalls(simpleMaze);
        return simpleMaze;
    }

    /**
     * Find a path from starting point to ending point in full-of-walls maze by going only down and right
     * @param maze Maze to find a path in
     */
    private void findPath(Maze maze){
        Position position = new Position(0,0);
        maze.breakWall(position);
        while(position.getRowIndex() != maze.getRows()-1 || position.getColumnIndex() != maze.getColumns()-1){
            int random = (int)((Math.random()*2)+1);
            if(random == 1) {
                if (position.getRowIndex() + 1 < maze.getRows()) {
                    position.setRow(position.getRowIndex() + 1);
                    maze.breakWall(position);
                }
            }
            else
                if(position.getColumnIndex() + 1 < maze.getColumns()) {
                    position.setColumn(position.getColumnIndex() + 1);
                    maze.breakWall(position);
                }
        }
        maze.setEndPos(position);
    }

    /**
     * Randomly putting walls in the maze
     * @param maze Maze to put walls in
     */
    private void randomWalls(Maze maze){
        for(int i=0; i<maze.getRows(); i++)
            for(int j=0; j<maze.getColumns(); j++) {
                if(maze.maze[i][j] == 1){
                    if(Math.random() < 0.3)
                        maze.maze[i][j] = 0;
                    else
                        maze.maze[i][j] = 1;

                }
            }
    }
}
