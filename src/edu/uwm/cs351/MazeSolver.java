
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
		int row=maze.rows()-1;
		int col=0;
	    while(!pending.isEmpty()) {
			
			Cell c=pending.pop();
			row=c.row;
			col=c.column;
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
		    int r=-1;
	    	int c1=-1;
		   
		        if(maze.isOpenRight(row, col) && c1!=col+1 && r!=row) {
			    	r=row;
			    	c1=col+1;
			    	if(r==0 && c1==maze.columns()-1) {
			    		li.add(maze.makeCell(r, c1));
				    	s=new PathSolutionDisplay(maze,li);
				    	VisitedCheck(r,c1,pending);
				    	return s;
				    }
			    	VisitedCheck(r,c1,pending);
			    	 
			    	
			    }
		       if(row>0 && maze.isOpenUp(row, col) && r!=row-1 && c1!=col) {
		        		c1=col;
		        		r=row-1;
		        		
		        		if(r==0 && c1==maze.columns()-1) {
		        			li.add(maze.makeCell(r, c1));
					    	s=new PathSolutionDisplay(maze,li);
					    	VisitedCheck(r,c1,pending);
					    	return s;
					    }
		        		VisitedCheck(r,c1,pending);
			    }
		        if(col>0 &&maze.isOpenLeft(row,col) && r!=row && c1!=col-1) {
			    	    r=row;
			    	    c1=col-1;
			    	    if(r==0 && c1==maze.columns()-1) {
		        			li.add(maze.makeCell(r, c1));
					    	s=new PathSolutionDisplay(maze,li);
					    	VisitedCheck(r,c1,pending);
					    	return s;
					    }
			    	    VisitedCheck(r,c1,pending);
			    	}
			    
		        if(maze.isOpenDown(row, col) && c1!=col && r!=row+1) {
			    	r=row+1;
			    	c1=col;
			    	if(r==0 && c1==maze.columns()-1) {
			    		li.add(maze.makeCell(r, c1));
				    	s=new PathSolutionDisplay(maze,li);
				    	VisitedCheck(r,c1,pending);
				    	return s;
				    }
			    	VisitedCheck(r,c1,pending);
			    }
		      
		  
		    
		    
		}
	    s=new VisitedSolutionDisplay(maze,tried);
		return s; // TODO: implement this method
	}
	// Our solution uses a helper method to avoid repeating code.  This is optional.

	private boolean VisitedCheck(int row, int col, Stack<Cell> pending2) {
		if(visited[row][col]==null) {
			pending.add(maze.makeCell(row, col));
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
