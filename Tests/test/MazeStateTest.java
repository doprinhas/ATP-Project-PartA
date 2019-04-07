package test;

import alogrithms.mazeGenerators.Position;
import alogrithms.search.MazeState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeStateTest {
     public void constructor(){
          Position p = new Position(2,2);
          MazeState ms = new MazeState(p);
          assertEquals("(2,2)", ms.toString());
     }
}