package mazeSolver.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 
 * The Breadth First Search algorithm is a common way to solve node-based path executions. 
 * Given a graph of nodes, BFS will basically collect all the possible paths that can be traveled, and visit them until the destination node is reached. 
 * 
 */


public class BreadthFirstSearch {

	private int[][] maze;
	private int x,y;
	private List<Integer> path = new ArrayList<Integer>();
	private List<List<Integer>> visited = new ArrayList<List<Integer>>();
	private Queue<List<Integer>> q = new LinkedList<List<Integer>>();
	
	public BreadthFirstSearch(int[][] maze, int x, int y) {
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
		q.add(Arrays.asList(x,y));
		visited.add(Arrays.asList(x,y));
		path.add(x);
		path.add(y);
		
		while(!q.isEmpty()) {
			List<Integer> cell = q.remove();
			
			if(isValid(cell.get(0)+1,cell.get(1)) && !visited.contains(Arrays.asList(cell.get(0)+1,cell.get(1)))) {
				q.add(Arrays.asList(cell.get(0)+1,cell.get(1)));
				path.add(cell.get(0)+1);
				path.add(cell.get(1));
				visited.add(Arrays.asList(cell.get(0)+1,cell.get(1)));
				if(maze[cell.get(0)+1][cell.get(1)] == 4) {
					break;
				}
			}
			if(isValid(cell.get(0)-1,cell.get(1)) && !visited.contains(Arrays.asList(cell.get(0)-1,cell.get(1)))) {
				q.add(Arrays.asList(cell.get(0)-1,cell.get(1)));
				path.add(cell.get(0)-1);
				path.add(cell.get(1));
				visited.add(Arrays.asList(cell.get(0)-1,cell.get(1)));
				if(maze[cell.get(0)-1][cell.get(1)] == 4) {
					break;
				}
			}
			if(isValid(cell.get(0),cell.get(1)+1) && !visited.contains(Arrays.asList(cell.get(0),cell.get(1)+1))) {
				q.add(Arrays.asList(cell.get(0),cell.get(1)+1));
				path.add(cell.get(0));
				path.add(cell.get(1)+1);
				visited.add(Arrays.asList(cell.get(0),cell.get(1)+1));
				if(maze[cell.get(0)][cell.get(1)+1] == 4) {
					break;
				}
			}
			if(isValid(cell.get(0),cell.get(1)-1) && !visited.contains(Arrays.asList(cell.get(0),cell.get(1)-1))) {
				q.add(Arrays.asList(cell.get(0),cell.get(1)-1));
				path.add(cell.get(0));
				path.add(cell.get(1)-1);
				visited.add(Arrays.asList(cell.get(0),cell.get(1)-1));
				if(maze[cell.get(0)][cell.get(1)-1] == 4) {
					break;
				}
			}		
		}
	}
	
	public List<Integer> solveMaze(){
		getPath();
		return path;
	}
	
	
}
