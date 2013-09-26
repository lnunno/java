package logic;

import java.awt.Point;

public class World {
	
	//DEFAULT values
	private static final int UNDER_POPULATION = 2;
	private static final int OVER_POPULATION = 3;
	private static final int REPRODUCTION = 3;
//	private static final int UNDER_POPULATION = 10;
//	private static final int OVER_POPULATION = 6;
//	private static final int REPRODUCTION = 1;
	private Cell[][] board;
	private int width;
	private int height;
	
	public World(int width, int height){
		this.width = width;
		this.height = height;
		board = new Cell[height][width];
	}
	
	public World(int dim){
		this.width = dim;
		this.height = dim;
		board = new Cell[dim][dim];
	}
	
	public void makeCell(int x, int y){
		if(x >= width || x < 0){
			System.err.println("Out of bounds! At x="+x+" y="+y);
			return;
		}
		board[y][x] = new Cell(new Point(x,y), this);
	}
	
	public int numberOfNeighbors(int x, int y){
		int count = 0;
		if(!validCoord(x,y)) return count;
		if(isAlive(x+1,y)) ++count;
		if(isAlive(x-1,y)) ++count;
		if(isAlive(x,y+1)) ++count;
		if(isAlive(x,y-1)) ++count;
		if(isAlive(x+1,y+1)) ++count;
		if(isAlive(x+1,y-1)) ++count;
		if(isAlive(x-1,y+1)) ++count;
		if(isAlive(x-1,y-1)) ++count;
		return count;
	}
	
	private boolean isAlive(int x, int y) {
		if(!validCoord(x, y)) return false;
		if(board[y][x] == null) return false;
		return true;
	}
	
	public void nextIteration(){
		Cell[][] nextBoard = new Cell[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				int numNeighbors = numberOfNeighbors(j, i);
				if(isAlive(j,i)){
					if(numNeighbors < UNDER_POPULATION || numNeighbors > OVER_POPULATION){
						//DEATH.
						nextBoard[i][j] = null;
					}
					else{
					//Else live, stay the same.
					nextBoard[i][j] = board[i][j];
					}
				}
				else{
					if(numNeighbors == REPRODUCTION){
						nextBoard[i][j] = new Cell(new Point(j,i), this);
					}
				}
			}
		}
		board = nextBoard;
	}

	private boolean validCoord(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return false;
		else return true;
	}
	
	public String toString(){
		String ret = "";
		for(int i = 0; i < board.length; i++){
			ret += "\n";
			for(int j = 0; j < board[0].length; j++){
				if(board[i][j] != null) ret += "*";
				else ret += "-";
			}
		}
		return ret;
	}
	
	public void printWorld(){
		System.out.println(this.toString());
	}

	/**
	 * @return the board
	 */
	public Cell[][] getBoard() {
		return board;
	}
	
	

}
