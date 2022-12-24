package mazeSolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import mazeSolver.Algorithms.BreadthFirstSearch;
import mazeSolver.Algorithms.DepthFirstSearch;
import mazeSolver.Algorithms.LeftWallFollower;
import mazeSolver.Algorithms.RandomMouseAlgorithm;
import mazeSolver.Algorithms.RightWallFollower;

public class MazePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 1000, HEIGHT = 900 ;
	private int MAZEWIDTH = 50;
	private int MAZEHEIGHT = 40; 
	private int SPEED = 1;
	private MazeGenerator mazeGenerator;
	private JButton start, shuffle, clear;
	private JComboBox<String> algorithmSelection;
	private JTextField speedInput, widthInput, heightInput;
	private JLabel timeLabel, speedLabel;
	private int[][] maze = {{}};
	private List<Integer> path = new ArrayList<Integer>();
	private List<Integer> pathUpdated = new ArrayList<Integer>();
	private int xStart, yStart;
	private Timer timer = new Timer(1,this);;
	private Timer timeTimer = new Timer(1,this);
	private int n = 0;
	private String[] algorithms = {"Random Mouse", "Right Wall Follower", "Left Wall Follower", "Breadth First Search","Depth First Search"};
	private boolean running;
	private long startTime, endTime, elapsedTime = 0;
	
	// Initialize panel
	public MazePanel(){
		this.setLayout(null);
		this.setBackground(Color.ORANGE);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		start = new JButton("Start");
		start.setBounds(20, 825, 100, 50);
		start.addActionListener(this);
		
		shuffle = new JButton("New maze");
		shuffle.setBounds(130, 825, 100, 50);
		shuffle.addActionListener(this);
		
		clear = new JButton("Clear path");
		clear.setBounds(240, 825, 100, 50);
		clear.addActionListener(this);
		
		algorithmSelection = new JComboBox<String>(algorithms);
		algorithmSelection.setBounds(350, 825, 150, 50);
		
		widthInput = new JTextField("Width");
		widthInput.setBounds(510,825,100,50);
		
		heightInput = new JTextField("Height");
		heightInput.setBounds(620,825,100,50);
		
		speedInput = new JTextField("Speed");
		speedInput.setBounds(730, 825, 50, 50);
		
		timeLabel = new JLabel("Elapsed time: ");
		timeLabel.setBounds(800, 820, 150, 25);
		
		speedLabel = new JLabel("Speed: ");
		speedLabel.setBounds(800, 855, 150, 25);
		
		this.add(start);
		this.add(shuffle);
		this.add(clear);
		this.add(algorithmSelection);
		this.add(speedInput);
		this.add(timeLabel);
		this.add(speedLabel);
		this.add(widthInput);
		this.add(heightInput);
		
		timeTimer.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Color color;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, MAZEWIDTH*(WIDTH/MAZEWIDTH), MAZEHEIGHT*((HEIGHT-100)/MAZEHEIGHT));
		
		// Draw maze
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[0].length; j++) {
				switch(maze[i][j]){
					case 0: color = Color.WHITE;
					break;
					case 1: color = Color.BLACK;
					break;
					case 3: color = Color.GREEN;
					xStart = i;
					yStart = j;
					break;
					case 4: color = Color.RED;
					break;
					default: color = Color.WHITE;
				}
				g.setColor(color);
				g.fillRect((WIDTH/MAZEWIDTH)*j, ((HEIGHT-100)/MAZEHEIGHT)*i, (WIDTH/MAZEWIDTH), ((HEIGHT-100)/MAZEHEIGHT));
			}
		}
		
		// Draw Path
		for(int i = 0; i < pathUpdated.size(); i += 2) {
			int pathX = pathUpdated.get(i);
			int pathY = pathUpdated.get(i+1);
			g.setColor(Color.RED);
			g.fillRect((WIDTH/MAZEWIDTH)*pathY+(((WIDTH/MAZEWIDTH)-((WIDTH/MAZEWIDTH)/2))/2), ((HEIGHT-100)/MAZEHEIGHT)*pathX+((((HEIGHT-100)/MAZEHEIGHT)-(((HEIGHT-100)/MAZEHEIGHT)/2))/2), (WIDTH/MAZEWIDTH)/2, ((HEIGHT-100)/MAZEHEIGHT)/2);
			// x*pathY+((x-y)/2), x*pathX+((x-y)/2), y, y
		}
		
		for(int i = 0; i < pathUpdated.size(); i += 2) {
			if(i > 4) {
				int prevX = pathUpdated.get(i-4);
				int prevY = pathUpdated.get(i-3);
				g.setColor(Color.GREEN);
				g.fillRect((WIDTH/MAZEWIDTH)*prevY+(((WIDTH/MAZEWIDTH)-((WIDTH/MAZEWIDTH)/2))/2), ((HEIGHT-100)/MAZEHEIGHT)*prevX+((((HEIGHT-100)/MAZEHEIGHT)-(((HEIGHT-100)/MAZEHEIGHT)/2))/2), (WIDTH/MAZEWIDTH)/2, ((HEIGHT-100)/MAZEHEIGHT)/2);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clear) {
			path.clear();
			pathUpdated.clear();
			repaint();
		}
		// Shuffle maze
		if(e.getSource() == shuffle) {
			try {
				MAZEHEIGHT = Integer.parseInt(heightInput.getText());
				MAZEWIDTH = Integer.parseInt(widthInput.getText());
			}
			catch (NumberFormatException n) {
				MAZEHEIGHT = 40;
				MAZEWIDTH = 50;
			}
			mazeGenerator = new MazeGenerator(MAZEHEIGHT, MAZEWIDTH);
			maze = mazeGenerator.generateMaze();
			path.clear();
			pathUpdated.clear();
			repaint();
		}
		// Start algorithm
		if(e.getSource() == start && running == false) {
			elapsedTime = 0;
			endTime = 0;
			startTime = System.nanoTime();
			try{
		       SPEED = Integer.parseInt(speedInput.getText());
		    }
		    catch (NumberFormatException n){
		       SPEED = 1;
		    }
			timer = new Timer(SPEED,this);
			timer.start();
			speedLabel.setText("Speed: " + SPEED);
			String selectedAlgorithm = algorithmSelection.getItemAt(algorithmSelection.getSelectedIndex());
			switch(selectedAlgorithm) {
				case "Random Mouse":
					RandomMouseAlgorithm RMA = new RandomMouseAlgorithm(maze, xStart, yStart);
					path = RMA.solveMaze();
					break;
				case "Right Wall Follower":
					RightWallFollower RWF = new RightWallFollower(maze, xStart, yStart);
					path = RWF.solveMaze();
					break;
				case "Left Wall Follower":
					LeftWallFollower LWF = new LeftWallFollower(maze, xStart, yStart);
					path = LWF.solveMaze();
					break;
				case "Breadth First Search":
					BreadthFirstSearch BFS = new BreadthFirstSearch(maze, xStart, yStart);
					path = BFS.solveMaze();
					break;
				case "Depth First Search":
					DepthFirstSearch DFS = new DepthFirstSearch(maze, xStart, yStart);
					path = DFS.solveMaze();
					break;
			}
			n = 0;
		}
		// Timer to repaint
		if(e.getSource() == timer) {
			if(path.size() > 0 && path.size() > n) {
				running = true;
				pathUpdated.add(path.get(n));
				pathUpdated.add(path.get(n+1));
				n += 2;
				repaint();
			}
			else {
				running = false;
				timer.stop();
			}
		}
		// Timer to calculate algorithm drawing time
		if(e.getSource() == timeTimer && timer.isRunning()) {
			endTime = System.nanoTime();
			elapsedTime = endTime - startTime;
			timeLabel.setText("Elapsed time: " + String.format("%.2f", (double)elapsedTime/1000000000) + "s");
		}
	}
}
