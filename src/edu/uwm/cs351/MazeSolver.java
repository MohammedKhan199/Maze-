package edu.uwm.cs351;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import edu.uwm.cs351.Maze.Cell;

/**
 * Try to solve a maze.
 */
public class MazeSolver {
	private final Maze maze;
	private Stack<Maze.Cell> pending = new Stack<Cell>();
	private Cell[][] visited;
	
	/**
	 * Create a maze solver for this maze.
	 * @param m maze to solve, must not be null
	 */
	public MazeSolver(Maze m) {
		maze = m;
		visited = new Cell[maze.rows()][maze.columns()];
	}

	/**
	 * Try to find a path, and return a solution display:
	 * either a path solution display, if a path was found,
	 * or a visited solution display if no path was found.
	 * @return solution display (must not be null)
	 */
	public SolutionDisplay findPath() {
		SolutionDisplay s;
	    boolean tried[][]= new boolean[maze.rows()][maze.columns()];
		pending.add(maze.makeCell(maze.rows()-1,0));
		List<Maze.Cell> li= new ArrayList<>();
		int row=;
		int col=0;
	    while(!pending.isEmpty()) {
			
			Cell c=pending.pop();
		    if(visited[row][col]==null) {
		    	visited[row][col]=c;
		    	tried[row][col]=true;
		    	li.add(c);
		    }
		    else {
		    	continue;
		    }
		    
		    if(row==0 && col==maze.columns()-1) {
		    	s=new PathSolutionDisplay(maze,li);
		    	return s;
		    }
		    
		   
		    if(maze.isOpenUp(row, col)) {
		    	row=row-1;
		    	if(visited[row][col]==null) {
		    		pending.add(maze.makeCell(row, col));
		    	}
		    }

		    else if(maze.isOpenRight(row, col)) {
		    	col=col+1;
		    	if(visited[row][col]==null) {
		    		pending.add(maze.makeCell(row, col));
		    	}
		    }
		    else if(maze.isOpenLeft(row,col)) {
		    	col=col-1;
		    	if(visited[row][col]==null) {
		    		pending.add(maze.makeCell(row, col));
		    	}
		    }
		    
		    if(maze.isOpenDown(row, col)) {
		    	row=row+1;
		    	if(visited[row][col]==null) {
		    		pending.add(maze.makeCell(row, col));
		    	}
		    }
		   
		}
	    s=new VisitedSolutionDisplay(maze,tried);
		return s; // TODO: implement this method
	}
	// Our solution uses a helper method to avoid repeating code.  This is optional.
}
