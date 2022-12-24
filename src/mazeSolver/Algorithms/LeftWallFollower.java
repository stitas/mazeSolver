package mazeSolver.Algorithms;

import java.util.ArrayList;
import java.util.List;

/*
 * Wall Follower algorithm solves a maze by placing an agent inside the maze.
 * The agent explores the paths to find the target while keeping itself constantly sticking to right or left side walls.
 * The agent keeps moving until it reaches the target point.
 * 
 */

public class LeftWallFollower {

	private int[][] maze;
	private int x, y;
	private List<Integer> path = new ArrayList<Integer>();
	private String direction;
	
	public LeftWallFollower(int[][] maze, int xStart, int yStart) {
		this.maze = maze;
		this.x = xStart;
		this.y = yStart;
		this.direction = "DOWN";
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
	
	private void directionDown() {
		if(left()) {
			addLeft();
			direction = "LEFT";
		}
		else if(down()) {
			addDown();
			direction = "DOWN";
		}
		else if(right()) {
			addRight();
			direction = "RIGHT";
		}
		else {
			addUp();
			direction = "UP";
		}
	}
	
	private void directionRight() {
		if(down()) {
			addDown();
			direction = "DOWN";
		}	
		else if(right()) {
			addRight();
			direction = "RIGHT";
		}
		else if(up()) {
			addUp();
			direction = "UP";
		}
		else {
			addLeft();
			direction = "LEFT";
		}
	}
	
	private void directionLeft() {
		if(up()) {
			addUp();
			direction = "UP";
		}
		else if(left()) {
			addLeft();
			direction = "LEFT";
		}
		else if(down()) {
			addDown();
			direction = "DOWN";
		}
		else {
			addRight();
			direction = "RIGHT";
		}
	}
	
	private void directionUp() {
		if(right()) {
			addRight();
			direction = "RIGHT";
		}
		else if(up()) {
			addUp();
			direction = "UP";
		}
		else if(left()) {
			addLeft();
			direction = "LEFT";
		}
		else {
			addDown();
			direction = "DOWN";
		}
	}
	
	private void addDown() {
		int dx = 1;
		int dy = 0;
		x += dx;
		y += dy;
		path.add(x);
		path.add(y);
	}
	
	private void addUp() {
		int dx = -1;
		int dy = 0;
		x += dx;
		y += dy;
		path.add(x);
		path.add(y);
	}
	
	private void addLeft() {
		int dx = 0;
		int dy = -1;
		x += dx;
		y += dy;
		path.add(x);
		path.add(y);
	}
	
	private void addRight() {
		int dx = 0;
		int dy = 1;
		x += dx;
		y += dy;
		path.add(x);
		path.add(y);
	}
	
	private boolean down() {
		int dx = 1;
		int dy = 0;
		
		if(isValid(x + dx, y + dy)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean up() {
		int dx = -1;
		int dy = 0;
		
		if(isValid(x + dx, y + dy)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean right() {
		int dx = 0;
		int dy = 1;
		
		if(isValid(x + dx, y + dy)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean left() {
		int dx = 0;
		int dy = -1;
		
		if(isValid(x + dx, y + dy)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void getPath() {
		while(!isEnd(x, y)) {
			switch(direction) {
				case "DOWN": directionDown();
				break;
				case "UP": directionUp();
				break;
				case "LEFT": directionLeft();
				break;
				case "RIGHT": directionRight();
				break;
			}
		}
	}
	
	public List<Integer> solveMaze() {
		getPath();
		return path;
	}
}
