package alogrithms.search;

import alogrithms.mazeGenerators.Maze;

public class SearchableMaze implements ISearchable{

    Maze m_maze;

    SearchableMaze(Maze maze){

        if (maze != null)
            m_maze = maze;

    }

}
