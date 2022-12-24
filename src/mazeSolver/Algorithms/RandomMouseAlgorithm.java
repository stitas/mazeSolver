package mazeSolver.Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* 
 * 
 * A mouse is let out in to the maze and its moves are random
 * A very slow algorithm
 * Is it an algorithm ?
 * 
 * 
 */

public class RandomMouseAlgorithm {
	
	private int[][] maze;
	private int x, y;
	private List<Integer> path = new ArrayList<Integer>();
	
	public RandomMouseAlgorithm(int[][] maze, int xStart, int yStart){
		super();
		this.maze = maze;
		this.x = xStart;
		this.y = yStart;
	}
	
	private boolean isValid(int x, int y) {
		if(maze.length > x && x >= 0 && maze[0].length > y && y >= 0) {
			if(maze[x][y] != 1) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	private boolean isEnd(int x, int y) {
		if(maze.length > x && x >= 0 && maze[0].length > y && y >= 0) {
			if(maze[x][y] == 4) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	private void down() {
		int dx = 1;
		int dy = 0;
		
		if(isValid(x + dx, y + dy)) {
			x += dx;
			y += dy;
			path.add(x);
			path.add(y);
		}
	}
	
	private void up() {
		int dx = -1;
		int dy = 0;
		
		if(isValid(x + dx, y + dy)) {
			x += dx;
			y += dy;
			path.add(x);
			path.add(y);
		}
	}
	
	private void right() {
		int dx = 0;
		int dy = 1;
		
		if(isValid(x + dx, y + dy)) {
			x += dx;
			y += dy;
			path.add(x);
			path.add(y);
		}
	}
	
	private void left() {
		int dx = 0;
		int dy = -1;
		
		if(isValid(x + dx, y + dy)) {
			x += dx;
			y += dy;
			path.add(x);
			path.add(y);
		}
	}
	
	private void getPath() {
		Random rand = new Random();
		int num;
		while(!isEnd(x, y)) {
			num = rand.nextInt(4);
			switch(num) {
				case 0: down();
				break;
				case 1: up();
				break;
				case 2: right();
				break;
				case 3: left();
				break;
			}
		}
	}
	
	public List<Integer> solveMaze(){
		getPath();
		return path;
	}
	
}
