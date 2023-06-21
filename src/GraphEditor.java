public class GraphEditor {
	public static MainWindow mainWindow;
	private static Graph graph;
	private static GraphVisualizer visualizer;
	
	public static void main(String[] args) {
		
		String inputFile = "E:\\Users\\theo-\\eclipse-workspace\\Projet AP4B\\testShortestPath.txt";
		
		graph = Application.getGraphFromFile(inputFile);
		
		visualizer = new GraphVisualizer(graph);
		
		mainWindow = new MainWindow(visualizer);
		
	}
}
