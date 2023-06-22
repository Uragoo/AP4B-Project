import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Entry point for this project.
 *
 * @author Alexis Kasmi
 * @author Théo Gouin
 */
public class GraphEditor {
	public static MainWindow mainWindow;
	private static Graph graph;
	private static GraphVisualizer visualizer;
	
	public static void main(String[] args) {
		
		//Getting the OS style for the file selection
		String nativeLF = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(nativeLF);
		} 
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
		
		graph = new Graph();
		
		visualizer = new GraphVisualizer(graph);
		
		mainWindow = new MainWindow(visualizer);
		
		
		//Listen to the Import Graph Menu from the JMenuBar
		//Import a new graph chosen by the user and display it
		mainWindow.menuBar.getImportItem().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				visualizer = new GraphVisualizer(Application.getGraphFromFile());
				mainWindow.setVisible(false);
				mainWindow = new MainWindow(visualizer);
				mainWindow.setVisible(true);
			}
			
		});
		
		
		//Listen to the New Graph Menu from the JMenuBar
		//Display a new blank graph
		mainWindow.menuBar.getNewItem().addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent ev) {
				visualizer = new GraphVisualizer(new Graph());
				mainWindow.setVisible(false);
				mainWindow = new MainWindow(visualizer);
				mainWindow.setVisible(true);
			}
			
		});
		
	}
}
