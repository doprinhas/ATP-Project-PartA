package algorithms.mazeGenerators;

public interface IMazeGenerator {

    /**
     * Generating a maze in the size of [rows X columns]
     * @param rows Represents the number of rows in the maze. rows has to be positive
     * @param columns Represents the number of columns in the maze. columns has to be positive
     * @return If incorrect input - returns null. <br> Otherwise - returns Maze object
     */
    Maze generate(int rows, int columns);

    Maze generate(int floors, int rows, int columns);

    /**
     * Measuring the maze generate function time
     * @param rows Represents the number of rows in the maze. rows has to be positive
     * @param columns Represents the number of columns in the maze. columns has to be positive
     * @return Maze generating time in millis
     */
    long measureAlgorithmTimeMillis(int rows, int columns);

    long measureAlgorithmTimeMillis(int floors, int rows, int columns);

}
