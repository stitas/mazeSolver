//package mazeSolver.Algorithms;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class AStar {
//
//	private int[][] maze;
//	private int x, y;
//	private int xEnd, yEnd;
//	private List<Integer> path = new ArrayList<Integer>();
//	private List<List<Integer>> open = new ArrayList<List<Integer>>();
//	private List<List<Integer>> closed = new ArrayList<List<Integer>>();
//	
//	public AStar(int[][] maze, int xStart, int yStart, int xEnd, int yEnd) {
//		this.maze = maze;
//		this.x = xStart;
//		this.y = yStart;
//		this.xEnd = xEnd;
//		this.yEnd = yEnd;
//	}
//	
//	private boolean isValid(int x, int y) {
//		if(maze.length > x && x >= 0 && maze[0].length > y && y >= 0) {
//			if(maze[x][y] != 1) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
//		else {
//			return false;
//		}
//	}
//	
//	private int f(int xCurr, int yCurr) {
//		return g(xCurr, yCurr) + h(xCurr, yCurr);
//	}
//	
//	private int g(int xCurr, int yCurr) {
//		int dx = Math.abs(x - xCurr);
//		int dy = Math.abs(y - yCurr);
//		return dx + dy;
//	}
//	
//	private int h(int xCurr, int yCurr) {
//		int dx = Math.abs(xEnd - xCurr);
//		int dy = Math.abs(yEnd - yCurr);
//		return dx + dy;
//	}
//	
//	private List<List<Integer>> getNeighbours(List<Integer> cell){
//		List<List<Integer>> neighbours = null;
//		if(isValid(cell.get(0)+1,cell.get(1))) {
//			neighbours.add(Arrays.asList(cell.get(0)+1,cell.get(1),f(cell.get(0)+1,cell.get(1)), g(cell.get(0)+1,cell.get(1)), h(cell.get(0)+1,cell.get(1))));
//		}
//		if(isValid(cell.get(0)-1,cell.get(1))) {
//			neighbours.add(Arrays.asList(cell.get(0)-1,cell.get(1),f(cell.get(0)-1,cell.get(1)), g(cell.get(0)-1,cell.get(1)), h(cell.get(0)-1,cell.get(1))));
//		}
//		if(isValid(cell.get(0),cell.get(1)+1)) {
//			neighbours.add(Arrays.asList(cell.get(0),cell.get(1)+1,f(cell.get(0),cell.get(1)+1), g(cell.get(0),cell.get(1)+1), h(cell.get(0),cell.get(1)+1)));
//		}
//		if(isValid(cell.get(0),cell.get(1)-1)) {
//			neighbours.add(Arrays.asList(cell.get(0),cell.get(1)-1,f(cell.get(0),cell.get(1)-1), g(cell.get(0),cell.get(1)-1), h(cell.get(0),cell.get(1)-1)));
//		}
//		return neighbours;
//	}
//	
//	private void getPath() {
//		open.add(Arrays.asList(x,y,f(x,y),g(x,y),h(x,y)));
//		List<Integer> cell = open.get(0);
//		List<List<Integer>> neighbours = null;
//		while(maze[x][y] != 4) {
//			int fScore = 100000;
//			for(List<Integer> openCell : open) {
//				if(openCell.get(2) < fScore) {
//					cell = openCell;
//					fScore = openCell.get(2);
//				}
//			}
//			if(maze[cell.get(0)][cell.get(1)] == 4) {
//				break;
//			}
//			else {
//				closed.add(cell);
//				neighbours = getNeighbours(cell);
//				for(List<Integer> neighbour : neighbours) {
//					if(neighbour.get(3) < cell.get(3) && closed.contains(neighbour)) {
//						
//					}
//				}
//			}
//		}
//	}
//	
//}
