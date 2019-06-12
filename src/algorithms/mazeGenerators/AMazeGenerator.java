package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int rows, int columns);

    public abstract Maze generate(int floors, int rows, int columns);

    public long measureAlgorithmTimeMillis(int rows, int columns)
    {
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        return System.currentTimeMillis() - startTime;
    }

    public long measureAlgorithmTimeMillis(int floors, int rows, int columns)
    {
        long startTime = System.currentTimeMillis();
        generate(floors, rows, columns);
        return System.currentTimeMillis() - startTime;
    }
}
