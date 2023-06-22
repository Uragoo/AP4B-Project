import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private JMenu fileMenu, editorMenu;
	
	private JMenuItem fileNew, fileExportGraph, fileImportGraph, exit, addVertex; 
		
	//Constructor
	public MenuBar(){
		super();
		//Initialize the File Menu
		fileMenu = new JMenu("File");
	
		//Initialize the Menu Items
		exit = new JMenuItem("Exit");
		fileNew = new JMenuItem("New Graph");
		fileExportGraph = new JMenuItem("Export Graph");
		fileImportGraph = new JMenuItem("Import Graph");	
		fileMenu.add(fileNew);
		fileMenu.add(fileExportGraph);
		fileMenu.add(fileImportGraph);
		fileMenu.add(exit);
		
		//Initialize the Editor Menu
		editorMenu = new JMenu("Editor");
		addVertex = new JMenuItem("Add Vertex");
		editorMenu.add(addVertex);
		
		add(fileMenu);
		add(editorMenu);
		
		//Listen to the File Export Item
		//Save the graph in a txt file when pressed
		fileExportGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Application.saveGraphInFile(GraphVisualizer.getGraph());
			}
		});
	
	}
	
	/**
	 * Gets the new file item.
	 * 
	 * @return the new file item
	 */
	public JMenuItem getNewItem(){
		return fileNew;
	}
	
	/**
	 * Gets the export item.
	 * 
	 * @return the export item
	 */
	public JMenuItem getExportItem(){
		return fileExportGraph;
	}
	
	/**
	 * Gets the import item.
	 * 
	 * @return the import item
	 */
	public JMenuItem getImportItem(){
		return fileImportGraph;
	}
	
	
	/**
	 * Gets the add vertex item.
	 * 
	 * @return the add vertex item
	 */
	public JMenuItem getAddVertex() {
		return this.addVertex;
	}
}
