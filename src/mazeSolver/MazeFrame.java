package mazeSolver;

import javax.swing.JFrame;

public class MazeFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	MazePanel mazePanel = new MazePanel();
	
	MazeFrame(){
		this.setLayout(null);
		this.setTitle("Maze Algorithm Visualizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAutoRequestFocus(false);
		this.setContentPane(mazePanel);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
