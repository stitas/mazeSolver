package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * 
 * The DFS algorithm is a recursive algorithm that uses the idea of backtracking.
 * It involves exhaustive searches of all the nodes by going ahead, if possible, else by backtracking.
 * 
 */


public class DepthFirstSearch {

	int[][] maze;
	int x, y;
	private List<Integer> path = new ArrayList<Integer>();
	private List<List<Integer>> visited = new ArrayList<List<Integer>>();
	private Stack<List<Integer>> stack = new Stack<List<Integer>>();
	
	public DepthFirstSearch(int[][] maze, int x, int y) {
		this.maze = maze;
		this.x = x;
		this.y = y;
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
	
	private void getPath() {
		stack.push(Arrays.asList(x,y));
		visited.add(Arrays.asList(x,y));
		path.add(x);
		path.add(y);
		while(!stack.isEmpty()) {
			List<Integer> cell = stack.pop();
			
			if(maze[cell.get(0)][cell.get(1)] == 4) {
				break;
			}
			
			if(isValid(cell.get(0)+1, cell.get(1)) && !visited.contains(Arrays.asList(cell.get(0)+1, cell.get(1)))) {
				path.add(cell.get(0)+1);
				path.add(cell.get(1));
				stack.push(Arrays.asList(cell.get(0)+1, cell.get(1)));
				visited.add(Arrays.asList(cell.get(0)+1, cell.get(1)));
			}
			if(isValid(cell.get(0)-1, cell.get(1)) && !visited.contains(Arrays.asList(cell.get(0)-1, cell.get(1)))) {
				path.add(cell.get(0)-1);
				path.add(cell.get(1));
				stack.push(Arrays.asList(cell.get(0)-1, cell.get(1)));
				visited.add(Arrays.asList(cell.get(0)-1, cell.get(1)));
			}
			if(isValid(cell.get(0), cell.get(1)+1) && !visited.contains(Arrays.asList(cell.get(0), cell.get(1)+1))) {
				path.add(cell.get(0));
				path.add(cell.get(1)+1);
				stack.push(Arrays.asList(cell.get(0), cell.get(1)+1));
				visited.add(Arrays.asList(cell.get(0), cell.get(1)+1));
			}
			if(isValid(cell.get(0), cell.get(1)-1) && !visited.contains(Arrays.asList(cell.get(0), cell.get(1)-1))) {
				path.add(cell.get(0));
				path.add(cell.get(1)-1);
				stack.push(Arrays.asList(cell.get(0), cell.get(1)-1));
				visited.add(Arrays.asList(cell.get(0), cell.get(1)-1));
			}
		}
	}
	
	public List<Integer> solveMaze() {
		getPath();
		return path;
	}
	
}
