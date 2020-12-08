
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
			
			Cell c=pending.peek();
			row=c.row;
			col=c.column;
		    if(visited[row][col]==null) {
		    	visited[row][col]=c;
		    	tried[row][col]=true;
		    	li.add(c);
		    }
		    
		    if(row==0 && col==maze.columns()-1) {
		    	s=new PathSolutionDisplay(maze,li);
		    	return s;
		    }
		    int r=-1;
	    	int c1=-1;
		   
		        boolean newedge=false;
		       if(row>0 && maze.isOpenUp(row, col)) {
		        		c1=col;
		        		r=row-1;
		        		if(VisitedCheck(r,c1,visited)) {
			        		pending.add(maze.makeCell(r, c1));
			        		newedge=true;
			        	}
		         }
		       if(maze.isOpenRight(row, col)) {
			    	r=row;
			    	c1=col+1;
			    	if(VisitedCheck(r,c1,visited)) {
		        		pending.add(maze.makeCell(r, c1));
		        		newedge=true;
		        	}
			    	
			    }
		       
		       if(maze.isOpenDown(row, col)) {
			    	r=row+1;
			    	c1=col;
			    	
			    	if(VisitedCheck(r,c1,visited)) {
		        		pending.add(maze.makeCell(r, c1));
		        		newedge=true;
		        	}
		        }
		        if(col>0 &&maze.isOpenLeft(row,col)) {
			    	    r=row;
			    	    c1=col-1;
			    	    if(VisitedCheck(r,c1,visited)) {
			        		pending.add(maze.makeCell(r, c1));
			        		newedge=true;
			        	}
			        		
			    }
			    
		        if(newedge==false) {
		        	li.remove(c);
		        	pending.pop();
		        }
		        
		      
		  
		    
		    
		}
	    s=new VisitedSolutionDisplay(maze,tried);
		return s; // TODO: implement this method
	}
	// Our solution uses a helper method to avoid repeating code.  This is optional.

	private boolean VisitedCheck(int row, int col, Cell[][] visited) {
		
		if(visited[row][col]==null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
