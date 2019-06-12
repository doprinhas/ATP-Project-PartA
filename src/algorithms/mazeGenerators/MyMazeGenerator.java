package algorithms.mazeGenerators;


import java.util.ArrayList;
import java.util.Random;

/**
 * The class generates complicated mazes.
 */
public class MyMazeGenerator extends AMazeGenerator {

    final private int UP_DOWN_PERCENT = 20;

    @Override
    public Maze generate(int floors, int rows, int columns) {
        Maze myMaze = new Maze(floors, rows, columns);
        gridMaze(myMaze); // Gridding the maze

        ArrayList<Wall> wallList = new ArrayList<>();

        Position startPos = new Position(0, 0, 0);
        myMaze.breakWall(startPos);
        myMaze.setStartPos(startPos);

        addWallsToList(startPos, wallList, myMaze);

        while(!wallList.isEmpty()){
            Random rand = new Random();
            int randomWall = rand.nextInt(wallList.size());
            Wall wall = wallList.get(randomWall);
            if(myMaze.getValue(wall.adjCell1) == 0 && myMaze.getValue(wall.adjCell2) == -1){
                myMaze.breakWall(wall.position);
                myMaze.breakWall(wall.adjCell2);
                addWallsToList(wall.adjCell2, wallList, myMaze);
            }
            if(myMaze.getValue(wall.adjCell1) == -1 && myMaze.getValue(wall.adjCell2) == 0){
                myMaze.breakWall(wall.position);
                myMaze.breakWall(wall.adjCell1);
                addWallsToList(wall.adjCell1, wallList, myMaze);

            }
            if(wallList.size() == 1) // Making the last cell as the ending point
                myMaze.setEndPos(wall.adjCell2);
            wallList.remove(randomWall);
        }
        return myMaze;
    }

    @Override
    public Maze generate(int rows, int columns) {
        return generate(1, rows, columns);
    }

    /**
     * Recieves cell in the maze, and adds his surrounding walls to the wall list
     * @param pos The position of the maze cell
     * @param wallList List of walls. updated by the given maze cell
     * @param maze The currently used maze
     */
    private void addWallsToList(Position pos, ArrayList<Wall> wallList, Maze maze){
        if(pos.getColumnIndex() < maze.getColumns()) {
            if (pos.getRowIndex() < maze.getRows() - 1 && maze.maze[pos.getFloorIndex()][pos.getRowIndex() + 1][pos.getColumnIndex()] == 1) {
                wallList.add(new Wall(new Position(pos.getFloorIndex(),pos.getRowIndex() + 1, pos.getColumnIndex())));
            }
            if (pos.getRowIndex() > 0 && maze.maze[pos.getFloorIndex()][pos.getRowIndex() - 1][pos.getColumnIndex()] == 1) {
                wallList.add(new Wall(new Position(pos.getFloorIndex(),pos.getRowIndex() - 1, pos.getColumnIndex())));
            }
        }

        if(pos.getRowIndex() < maze.getRows()) {
            if (pos.getColumnIndex() < maze.getColumns() - 1 && maze.maze[pos.getFloorIndex()][pos.getRowIndex()][pos.getColumnIndex() + 1] == 1) {
                wallList.add(new Wall(new Position(pos.getFloorIndex(), pos.getRowIndex(), pos.getColumnIndex() + 1)));
            }
            if (pos.getColumnIndex() > 0 && maze.maze[pos.getFloorIndex()][pos.getRowIndex()][pos.getColumnIndex() - 1] == 1) {
                wallList.add(new Wall(new Position(pos.getFloorIndex(), pos.getRowIndex(), pos.getColumnIndex() - 1)));
            }
        }

        if(getRandomBool() && !pos.equals(maze.getStartPosition()))
            if(pos.getFloorIndex() < maze.getFloors())
                if(pos.getFloorIndex() < maze.getFloors() - 1 && maze.maze[pos.getFloorIndex() + 1][pos.getRowIndex()][pos.getColumnIndex()] == 1)
                    wallList.add(new Wall(new Position(pos.getFloorIndex() + 1, pos.getRowIndex(), pos.getColumnIndex())));

        if(getRandomBool())
            if(pos.getFloorIndex() < maze.getFloors()) {
                if(pos.getFloorIndex() > 0 && maze.maze[pos.getFloorIndex() - 1][pos.getRowIndex()][pos.getColumnIndex()] == 1)
                    wallList.add(new Wall(new Position(pos.getFloorIndex() - 1, pos.getRowIndex(), pos.getColumnIndex())));
            }
    }

    private Random random = new Random();

    private boolean getRandomBool()
    {
        return random.nextInt(100) <= UP_DOWN_PERCENT;
    }

    /**
     * Deviding the maze to 1x1 cells
     * @param maze Maze to grid
     */
    private void gridMaze(Maze maze) {
        for (int i = 0; i < maze.getFloors(); i++) {
            for (int j = 0; j < maze.getRows(); j++)
                for (int k = 0; k < maze.getColumns(); k++) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            if (k % 2 != 0) {
                                maze.maze[i][j][k] = 1;
                            } else if (k % 2 == 0)
                                maze.maze[i][j][k] = -1;
                        } else
                            maze.maze[i][j][k] = 1;
                    } else
                        maze.maze[i][j][k] = 1;
                }
        }

    }

    /**
     * Class representing a wall in the maze
     * Position represents the wall position in the maze, and adjCell1/2 representing the two cells he is seperating
     */
    private class Wall{
        private Position position;
        private Position adjCell1;
        private Position adjCell2;

        Wall(Position pos){
            this.position = pos;
            if(pos.getFloorIndex()%2 == 0) {
                if (pos.getRowIndex() % 2 != 0) {
                    this.adjCell1 = new Position(pos.getFloorIndex(), pos.getRowIndex() - 1, pos.getColumnIndex());
                    this.adjCell2 = new Position(pos.getFloorIndex(), pos.getRowIndex() + 1, pos.getColumnIndex());
                }
                else {
                    this.adjCell1 = new Position(pos.getFloorIndex(), pos.getRowIndex(), pos.getColumnIndex() - 1);
                    this.adjCell2 = new Position(pos.getFloorIndex(), pos.getRowIndex(), pos.getColumnIndex() + 1);
                }
            }
            else{
                this.adjCell1 = new Position(pos.getFloorIndex() - 1, pos.getRowIndex(), pos.getColumnIndex());
                this.adjCell2 = new Position(pos.getFloorIndex() + 1, pos.getRowIndex(), pos.getColumnIndex());
            }

        }
    }
}