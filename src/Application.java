import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;

public class Application {
	public static Graph getGraphFromFile(String file) {
		Graph graph = new Graph();
		
		//JFileChooser fileChooser = new JFileChooser();
		//fileChooser.setSelectedFile(new File("fileToSave.txt"));
		//int retrival = fileChooser.showSaveDialog(GraphEditor.mainWindow);
		//if (retrival == JFileChooser.APPROVE_OPTION) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) { //fileChooser.getSelectedFile()
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(" ");
					if (parts.length == 3) {
						int nodeId = Integer.parseInt(parts[0]);
						int nodeX = Integer.parseInt(parts[1]);
						int nodeY = Integer.parseInt(parts[2]);
						graph.addNode(nodeId, nodeX, nodeY);
					} else if (parts.length == 2) {
						int sourceId = Integer.parseInt(parts[0]);
						int targetId = Integer.parseInt(parts[1]);
						graph.addEdge(sourceId, targetId);
						//graph.addEdge(targetId, sourceId);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		//}
		return graph;
	}
	
	public static void saveGraphInFile(Graph graph) {
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("fileToSave.txt"));
			int retrival = fileChooser.showSaveDialog(GraphEditor.mainWindow);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
					List<Node> nodes = graph.getNodes();
					for (Node node : nodes) {
						writer.write(Integer.toString(node.getId()) + " " + Integer.toString(node.getInitX()) + " " + Integer.toString(node.getInitY()));
						writer.newLine();
					}
					for (Node node : nodes) {
						List<Node> voisins = graph.getVoisins(node);
						for (Node voisin : voisins) {
							writer.write(Integer.toString(node.getId()) + " " + Integer.toString(voisin.getId()));
							writer.newLine();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			
		}
	}
}
