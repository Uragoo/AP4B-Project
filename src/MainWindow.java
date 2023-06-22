import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements KeyListener {
	private GraphVisualizer graphVisualizer;
	public MenuBar menuBar;
	public JPanel panelInfo;
	public int MAX_HEIGHT = 990;
	public int MAX_WIDTH = 1760;
	
	//Constructor
	public MainWindow(GraphVisualizer graphVisualizer) {
		this.graphVisualizer = graphVisualizer;
		
		//Getting the OS style for the file selection
		String nativeLF = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(nativeLF);
		} 
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
		
		//Set the window caracteristics
		setTitle("Graph Editor");
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setResizable(true);
		
		getContentPane().add(graphVisualizer);
		
		//Create and add the MenuBar
		menuBar = new MenuBar();
		//Listen to the Add Vertex Menu from the Menu Bar
		//Allow the user to add a new vertex to the graph
		menuBar.getAddVertex().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				graphVisualizer.addVertex();
			}
		});
		
		setJMenuBar(menuBar);
		
		//Listen to the exit button of the window
		//Close the window if it's pressed
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		//Add a listener for the keyboard
		addKeyListener(this);
			
		setVisible(true);
	}
	
	//Listen if a keyboard key is pressed
	@Override
	public void keyTyped(KeyEvent e) {
		//Depeding on the key pressed, allow the user to edit the graph
		switch (e.getKeyChar()) {
		case 'a':
			graphVisualizer.addVertex();
			break;
		case 'e':
			graphVisualizer.addEdge();
			break;
		case 'r':
			graphVisualizer.removeVertex();
			break;
		case 't':
			graphVisualizer.removeEdge();
			break;
		case 's':
			graphVisualizer.setStartNode();
			break;
		case 'f':
			graphVisualizer.setEndNode();
			break;
		case 'p':
			graphVisualizer.getShortestPath();
			break;
		case '':
			removeMouseListener(graphVisualizer.activeListener);
			graphVisualizer.activeListener = null;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
