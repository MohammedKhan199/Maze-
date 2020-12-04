package edu.uwm.cs351;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * A 2 dimensional maze.
 * It is entered on the last row at the left
 * and is exited at the last column at the top.
 */
public class Maze {

	private final int rows, columns;
	private boolean[][] ropen, copen;
	
	/* Explanation:
	 * ropen[i,j] is true if one can get from cell [i,j] to cell [i+1,j]
	 * copen[i,j] is true is one can get from cell [i,j] to cell[i,j+1];
	 */
	
	/**
	 * Create a maze of the given size, in which everything is blocked.
	 * @param rows number of rows, must be positive
	 * @param columns number of columns, must be positive
	 */
	public Maze(int rows, int columns) {
		if (rows < 1 || columns < 1) throw new IllegalArgumentException("Maze must not be empty");
		this.rows = rows;
		this.columns = columns;
		ropen = new boolean[rows-1][columns];
		copen = new boolean[rows][columns-1];
	}
	
	public int rows() { return rows; }
	public int columns() { return columns; }
	
	private void checkCell(int i, int j) {
		if (i < 0 || rows <= i) throw new IllegalArgumentException("row " + i + " out of range [0," + rows + ")");
		if (j < 0 || columns <= j) throw new IllegalArgumentException("column " + j + " out of range [0," + columns + ")");
	}
	
	/**
	 * Is this cell open to the left?
	 * As a special case, the bottom left cell is open to the left
	 * (this is the maze entry).
	 * @param i row
	 * @param j column
	 * @return whether there is an opening to the left.
	 * @exception IllegalArgumentException if coordinates out of range.
	 */
	public boolean isOpenLeft(int i, int j) {
		// TODO: implement this method.
		checkCell(i,j);
		if(i==rows-1&& j==0) {
			return true;
		}
		j--;
		if(j<0) {
			return false;
		}
		return copen[i][j]; 
		
	}
	
	/**
	 * Is this cell open to the right?
	 * @param i row
	 * @param j column
	 * @return whether there is an opening to the right.
	 * @exception IllegalArgumentException if coordinates out of range.
	 */
	public boolean isOpenRight(int i, int j) {
        checkCell(i,j);
        if(j>=columns-1) {
        	return false;
        }
		return copen[i][j]; // TODO: implement this method.
	}
	
	/**
	 * Is this cell open above?
	 * As special case, the top right cell is open above
	 * (this is the maze exit).
	 * @param i row
	 * @param j column
	 * @return whether there is an opening going up
	 * @exception IllegalArgumentException if coordinates out of range.
	 */
	public boolean isOpenUp(int i, int j) {
		 // TODO: implement this method.
		checkCell(i,j);
		if(i==0 && j==columns-1) {
			return true;
		}
		i--;
		if(i<0) {
			return false;
		}
		return ropen[i][j];
	}
	
	/**
	 * Is this cell open below?
	 * @param i row
	 * @param j column
	 * @return whether there is an opening down down
	 * @exception IllegalArgumentException if coordinates out of range.
	 */
	public boolean isOpenDown(int i, int j) {
		// TODO: implement this method.
		checkCell(i,j);
		if(i>=rows-1) {
			return false;
		}
		return ropen[i][j];
	}
	
	
	/// Mutators
	
	public void setOpenRight(int i, int j, boolean open) {
		checkCell(i,j);
		checkCell(i,j+1);
		copen[i][j] = open;
	}
	
	public void setOpenDown(int i, int j, boolean open) {
		checkCell(i,j);
		checkCell(i+1,j);
		ropen[i][j] = open;
	}
	
	
	/**
	 * Read in contents of a maze printed with ASCII graphics from the file.
	 * The rows and columns are already set (do not read these).
	 * Example (assuming rows = 3, columns = 4)
	 * <pre>
	 * +-+-+-+ +
	 * |   |   |
	 * + + + +-+
	 * | |     |
	 * + +---+ +
	 *       | |
	 * +-----+-+      
	 * </pre>
	 * @param r buffered reader to read lines from
	 * @throws IOException if a problem happens with reading
	 * @throws ParseException if the maze is badly formatted.
	 * (The implementation is also permitted to simply overlook 
	 * format errors)
	 */
	public void read(BufferedReader r) throws IOException {
		// TODO: Implement this method
	   String input= r.readLine();
	   int rcount=0;
	   int ccount=0;
	   while(input!=null) {
		 input= r.readLine();
		 
		    if(ccount<copen.length-1) {
		    	int i=1;
		    	for(int j=0;j<copen[ccount].length;j++) {
		    		char s=input.charAt(i);
		    		if(s==' ') {
		    			i++;
		    		}
		    		if(input.charAt(i)=='|') {
		    			copen[ccount][j]=false;
		    		}
		    		else {
		    			copen[ccount][j]=true;
		    		}
		    	    i++;
		    	}
		    	ccount++;
		    } 
		    
		    input =r.readLine();
		    if(rcount<ropen.length-1) {
		    	int i=1;
		        // input.split("+");
		         
		    	for(int j=0;j<ropen[rcount].length;j++) {
		    		if(input.charAt(i)=='+') {
		    			i++;
		    		}
		    		if(input.charAt(i)=='-') {
		    			ropen[rcount][j]=false;
		    		}
		    		else {
		    			ropen[rcount][j]=true;
		    		}
		    		i++;
		    	}
		    	rcount++;
		    }
	  }
		
	}
	
	/**
	 * Write out the maze in a specific textual and human readable form.
	 * For example:
	 * <pre>
	 * +-+-+-+ +
	 * |   |   |
	 * + + + +-+
	 * | |     |
	 * + +---+ +
	 *       | |
	 * +-----+-+      
	 * </pre>
	 * 
	 * @param pw print writer to write to, must not be null
	 */
	public void write(PrintWriter pw) {
		// TODO: Print this maze using the same format read expects
		for(int i=0;i<columns;i++) {
			pw.write("+");
			if(i!=columns-1) {
				pw.write("-");
			}
			else {
				pw.write(" ");
			}
			
		}
		pw.write("+");
		pw.write("\n");
		int count=0;
		while(count<=rows-1) {
			
			if(count<rows-1) {
			    pw.write("|");
			}
			else {
				pw.write(" ");
			}
			
			for(int i=0;i<copen.length;i++) {
				
				for(int j=0;j<copen[i].length;j++) {
					pw.write(" ");
					if(copen[i][j]==false) {
						pw.write("|");
					}
					else {
						pw.write(" ");
					}
					
				}
			
		}
		   pw.write(" ");
		   pw.write("|");
		    pw.write("\n");
	if(count<rows-1) {
		for(int i=0;i<ropen.length;i++) {
			for(int j=0;j<ropen[i].length;j++) {
				pw.write("+");
				if(ropen[i][j]==false) {
					pw.write("-");
				}
				else {
					pw.write(" ");
				}
				pw.write("+");
			}
		
		}
		pw.write("\n");
	}
		//pw.write("\n");
	
		count++;
		
	}
		//pw.write("\n");
		
		for(int i=0;i<columns;i++) {
			pw.write("+");
			pw.write("-");
			
		}
		pw.write("+");
		
	}

	
	/// NB: The rest of this class is done for you
	
	/**
	 * Clear maze (everything is the same open/closed).
	 * @param open whether everything should be open (or closed)
	 */
	public void clear(boolean open) {
		for (int i = 0; i < rows; ++i) {
			for (int j=0; j < columns; ++j) {
				if (i+1 != rows) ropen[i][j] = open;
				if (j+1 != columns) copen[i][j] = open;
			}
		}
	}
	
	/**
	 * Exception thrown by {@link #read} if it notices a problem.
	 *
	 */
	public static class ParseException extends IOException {
		/**
		 * Keep Eclipse happy
		 */
		private static final long serialVersionUID = 1L;

		public ParseException(String s) { super(s); }
	}
	
	/**
	 * A representation of an [row,column] coordinate.
	 * You may this useful when implementing methods, but there's
	 * no requirement that you use it.
	 */
	public class Cell {
		public final int row, column;
		public Cell(int i, int j) {
			checkCell(i,j);
			this.row = i;
			this.column = j;
		}
		@Override
		public String toString() {
			return "[" + row + "," + column + "]"; // useful for debugging
		}
		@Override
		public boolean equals(Object x) {
			if (!(x instanceof Cell)) return false;
			Cell c = (Cell)x;
			return row == c.row && column == c.column;
		}
		@Override
		public int hashCode() {
			return (row << 8) ^ column;
		}
	}
	
	/** Create a cell for row i and column j.
	 * This is a convenience method for creating new cells,
	 * since the Java syntax for doing so in a different class is tricky.
	 * You don't need to use this method.
	 * @param i row
	 * @param j column
	 * @return new Cell(i,j)
	 */
	public Cell makeCell(int i, int j) {
		return new Cell(i,j);
	}
	
	private static final int SQUARESIZE = 10;

	public static void usage()
	{
		System.out.println("Usage: maze arg... where the following arguments are supported:");
		System.out.println("  --read <filename>");
		System.out.println("  --solve");
		System.out.println("  --print");
		System.out.println("There must be a maze loaded before it can be solved/printed.");
		System.exit(1);
	}
	
	public static void main(String[] args) {
		Maze maze = null;
		SolutionDisplay solution = null;
		for (int i=0; i < args.length; ++i) {
			if (args[i].equals("--read")) {
				try {
					if (++i == args.length) usage();
					maze = fromFile(args[i]);
				} catch (NumberFormatException e) {
					System.out.println("Badly formatted number of rows (or columns): " + e);
					return;
				} catch (FileNotFoundException e) {
					System.out.println("File not found: " + args[i]);
					return;
				} catch (IOException e) {
					System.out.println("Problem reading file: " + e);
					return;
				}
				System.out.println("Loaded maze from " + args[i]);
			} else if (args[i].equals("--print")) {
				if (maze != null) {
					PrintWriter pw = new PrintWriter(System.out);
					pw.println(maze.rows() + " " + maze.columns());
					maze.write(pw);
					pw.close();
				}
			} else if (args[i].equals("--solve")) {
				if (maze == null) usage();
				if ((solution = new MazeSolver(maze).findPath()) instanceof PathSolutionDisplay) {
					System.out.println("A path was found.");
				} else {
					System.out.println("No path was found.");
				}
			} else if (args[i].equals("--help")) {
				usage();
			} else {
				System.out.println("Option not understood: " + args[i]);
			}
		}
		if (maze == null) usage();
		final Maze m = maze;
		final SolutionDisplay sd = solution;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new JFrame("Maze");
				MazeDisplay md = new MazeDisplay(m,SQUARESIZE);
				md.setSolution(sd);
				f.setContentPane(md);
				f.setSize((2*m.columns()+1)*SQUARESIZE,(2*m.rows()+3)*SQUARESIZE);
				f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				f.setVisible(true);
			}
		});
	}

	public static Maze fromFile(String fileName) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String firstLine = br.readLine();
			String[] nums = firstLine.split(" ");
			if (nums.length != 2) throw new ParseException("wrong format for first line of maze: " + firstLine);
			int rows = Integer.parseInt(nums[0]);
			int columns = Integer.parseInt(nums[1]);
			Maze m = new Maze(rows,columns);
			m.read(br);
			return m;
		}
	}
	
}
