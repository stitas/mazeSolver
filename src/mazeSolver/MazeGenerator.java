/* 
 * 
 * Randomized Prim’s Algorithm consists of the following steps:
 *
 *	Start with a grid full of walls
 *	Pick a cell, mark it as part of the maze. Add the walls of the cell to the walls of the list
 *	While there are walls in the list:
 *	1. Pick a random wall from the list. If only one of the two cells that the wall divides is visited, then:
 *		a) Make the wall a passage and mark the unvisited cell as part of the maze
 *		b) Add the neighboring walls of the cell to the wall list.
 *	2. Remove the wall from the list
 *
*/

package mazeSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeGenerator {
	private int width;
	private int height;
	private int wall = 1;
	private int cell = 0;
	private int undefined = 2;
	private int start = 3;
	private int finish = 4;
	
	MazeGenerator(int height, int width){
		this.height = height;
		this.width = width;
	}
	
	// Creates primary maze array
	private int[][] generatePrimaryArr(){
		int[][] maze = new int[height][width];
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[0].length; j++) {
				maze[i][j] = undefined;
			}
		}
		return maze;
	}
	
	// Generates the maze
	public int[][] generateMaze(){
		int[][] maze = generatePrimaryArr();
		List<List<Integer>> walls = new ArrayList<List<Integer>>();
		int startingHeight = (int) (Math.random() * height);
		int startingWidth = (int) (Math.random() * width);
		
		// Check if the starting point is not on the edge and if it is change its position
		if(startingHeight == 0) {
			startingHeight += 1;
		}
		if(startingHeight == height - 1) {
			startingHeight -= 1;
		}
		
		if(startingWidth == 0) {
			startingWidth += 1;
		}
		if(startingWidth == width - 1) {
			startingWidth -= 1;
		}
		
		// Make the starting point a cell and add the points surrounding it to a list of walls
		maze[startingHeight][startingWidth] = cell;
		walls.add(Arrays.asList(startingHeight - 1, startingWidth));
		walls.add(Arrays.asList(startingHeight + 1, startingWidth));
		walls.add(Arrays.asList(startingHeight, startingWidth - 1));
		walls.add(Arrays.asList(startingHeight, startingWidth + 1));
		
		// Make the points surrounding the cell in to walls
		maze[startingHeight - 1][startingWidth] = wall;
		maze[startingHeight + 1][startingWidth] = wall;
		maze[startingHeight][startingWidth - 1] = wall;
		maze[startingHeight][startingWidth + 1] = wall;
		
		while(walls.size() != 0) {
			// Get a random wall and check if left,right,top,bottom of it are undefined and then if true set it to a cell and make neighboring points to walls if the are not cells 
			List<Integer> randomWall = walls.get((int)(Math.random() * walls.size() - 1));
			
			// Left = undefined, Right = cell
			if(randomWall.get(1) != 0) {
				if(maze[randomWall.get(0)][randomWall.get(1) - 1] == undefined && maze[randomWall.get(0)][randomWall.get(1) + 1] == cell) {
					int surroundingCells = checkSurroundingCells(randomWall, maze);
					if(surroundingCells < 2) {
						maze[randomWall.get(0)][randomWall.get(1)] = cell;
						
						// Top
						if(randomWall.get(0) != 0) {
							if(maze[randomWall.get(0) - 1][randomWall.get(1)] != cell) {
								maze[randomWall.get(0) - 1][randomWall.get(1)] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0) - 1,randomWall.get(1)))){
								walls.add(Arrays.asList(randomWall.get(0) - 1,randomWall.get(1)));
							}
						}
						
						// Bottom
						if(randomWall.get(0) != height - 1) {
							if(maze[randomWall.get(0) + 1][randomWall.get(1)] != cell) {
								maze[randomWall.get(0) + 1][randomWall.get(1)] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0) + 1,randomWall.get(1)))){
								walls.add(Arrays.asList(randomWall.get(0) + 1,randomWall.get(1)));
							}
						}
						
						// Left
						if(randomWall.get(1) != 0) {
							if(maze[randomWall.get(0)][randomWall.get(1) - 1] != cell) {
								maze[randomWall.get(0)][randomWall.get(1) - 1] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0),randomWall.get(1) - 1))){
								walls.add(Arrays.asList(randomWall.get(0),randomWall.get(1) - 1));
							}
						}
					}
				}
				walls.remove(randomWall);
			}
			
			// Left = cell, Right = undefined
			if(randomWall.get(1) != width - 1) {
				if(maze[randomWall.get(0)][randomWall.get(1) + 1] == undefined && maze[randomWall.get(0)][randomWall.get(1) - 1] == cell) {
					int surroundingCells = checkSurroundingCells(randomWall, maze);
					if(surroundingCells < 2) {
						maze[randomWall.get(0)][randomWall.get(1)] = cell;
						
						// Top
						if(randomWall.get(0) != 0) {
							if(maze[randomWall.get(0) - 1][randomWall.get(1)] != cell) {
								maze[randomWall.get(0) - 1][randomWall.get(1)] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0) - 1,randomWall.get(1)))){
								walls.add(Arrays.asList(randomWall.get(0) - 1,randomWall.get(1)));
							}
						}
						
						// Bottom
						if(randomWall.get(0) != height - 1) {
							if(maze[randomWall.get(0) + 1][randomWall.get(1)] != cell) {
								maze[randomWall.get(0) + 1][randomWall.get(1)] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0) + 1,randomWall.get(1)))){
								walls.add(Arrays.asList(randomWall.get(0) + 1,randomWall.get(1)));
							}
						}
						
						// Right
						if(randomWall.get(1) != width - 1) {
							if(maze[randomWall.get(0)][randomWall.get(1) + 1] != cell) {
								maze[randomWall.get(0)][randomWall.get(1) + 1] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0),randomWall.get(1) + 1))){
								walls.add(Arrays.asList(randomWall.get(0),randomWall.get(1) + 1));
							}
						}
					}
				}
				walls.remove(randomWall);
			}
			
			// Top = undefined, Bottom = cell
			if(randomWall.get(0) != 0) {
				if(maze[randomWall.get(0) - 1][randomWall.get(1)] == undefined && maze[randomWall.get(0) + 1][randomWall.get(1)] == cell) {
					int surroundingCells = checkSurroundingCells(randomWall, maze);
					if(surroundingCells < 2) {
						maze[randomWall.get(0)][randomWall.get(1)] = cell;
						
						// Top
						if(randomWall.get(0) != 0) {
							if(maze[randomWall.get(0) - 1][randomWall.get(1)] != cell) {
								maze[randomWall.get(0) - 1][randomWall.get(1)] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0) - 1,randomWall.get(1)))){
								walls.add(Arrays.asList(randomWall.get(0) - 1,randomWall.get(1)));
							}
						}
						
						// Right
						if(randomWall.get(1) != width - 1) {
							if(maze[randomWall.get(0)][randomWall.get(1) + 1] != cell) {
								maze[randomWall.get(0)][randomWall.get(1) + 1] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0),randomWall.get(1) + 1))){
								walls.add(Arrays.asList(randomWall.get(0),randomWall.get(1) + 1));
							}
						}
						
						// Left
						if(randomWall.get(1) != 0) {
							if(maze[randomWall.get(0)][randomWall.get(1) - 1] != cell) {
								maze[randomWall.get(0)][randomWall.get(1) - 1] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0),randomWall.get(1) - 1))){
								walls.add(Arrays.asList(randomWall.get(0),randomWall.get(1) - 1));
							}
						}
					}
				}
				walls.remove(randomWall);
			}
			
			// Top = cell, Bottom = undefined
			if(randomWall.get(0) != height -1) {
				if(maze[randomWall.get(0) + 1][randomWall.get(1)] == undefined && maze[randomWall.get(0) - 1][randomWall.get(1)] == cell) {
					int surroundingCells = checkSurroundingCells(randomWall, maze);
					if(surroundingCells < 2) {
						maze[randomWall.get(0)][randomWall.get(1)] = cell;
						
						// Bottom
						if(randomWall.get(0) != height - 1) {
							if(maze[randomWall.get(0) + 1][randomWall.get(1)] != cell) {
								maze[randomWall.get(0) + 1][randomWall.get(1)] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0) + 1,randomWall.get(1)))){
								walls.add(Arrays.asList(randomWall.get(0) + 1,randomWall.get(1)));
							}
						}
						
						// Right
						if(randomWall.get(1) != width - 1) {
							if(maze[randomWall.get(0)][randomWall.get(1) + 1] != cell) {
								maze[randomWall.get(0)][randomWall.get(1) + 1] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0),randomWall.get(1) + 1))){
								walls.add(Arrays.asList(randomWall.get(0),randomWall.get(1) + 1));
							}
						}
						
						// Left
						if(randomWall.get(1) != 0) {
							if(maze[randomWall.get(0)][randomWall.get(1) - 1] != cell) {
								maze[randomWall.get(0)][randomWall.get(1) - 1] = wall;
							}
							if(!walls.contains(Arrays.asList(randomWall.get(0),randomWall.get(1) - 1))){
								walls.add(Arrays.asList(randomWall.get(0),randomWall.get(1) - 1));
							}
						}
					}
				}
				walls.remove(randomWall);
			}
		}
		makeWalls(height,width, maze);
		makeEntranceExit(height, width, maze);
		return maze;
	}
	
	// Function that turns unvisited points into walls
	private void makeWalls(int height, int width, int[][] maze) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(maze[i][j] == undefined) {
					maze[i][j] = wall;
				}
			}
		}
	}
	
	// Function that creates the entrance and exit
	private void makeEntranceExit(int height, int width, int[][] maze) {
		// Entrance
		for(int i = 0; i < width; i++) {
			if(maze[1][i] == cell) {
				maze[0][i] = start;
				break;
			}
		}
		
		// Exit
		for(int j = width - 1; j > 0; j--) {
			if(maze[height - 2][j] == cell) {
				maze[height - 1][j] = finish;
				break;
			}
		}
	}
	
	// Function that returns how many cells surround the selected wall
	private int checkSurroundingCells(List<Integer> randWall, int[][] maze) {
		int surroundingCells = 0;
		
		//Top
		if(maze[randWall.get(0) - 1][randWall.get(1)] == cell) {
			surroundingCells++;
		}
		// Bottom
		if(maze[randWall.get(0) + 1][randWall.get(1)] == cell) {
			surroundingCells++;
		}
		// Left
		if(maze[randWall.get(0)][randWall.get(1) - 1] == cell) {
			surroundingCells++;
		}
		// Right
		if(maze[randWall.get(0)][randWall.get(1) + 1] == cell) {
			surroundingCells++;
		}
		
		return surroundingCells;
	}
	
	
}
