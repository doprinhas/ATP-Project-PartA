package alogrithms.search;

import alogrithms.mazeGenerators.Position;

public class MazeState extends AState{

    MazeState(Position position , double coast){
        super(position, coast);
    }

    MazeState(Position position){
        super(position);
    }

}
