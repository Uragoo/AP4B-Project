import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private JMenu fileMenu, editorMenu, help;
	
	/** The exit. */
	private JMenuItem fileExportGraph, fileImportGraph, about, helpitem, exit, addVertex; 
		
	/**
	 * Instantiates a new menu bar.
	 */
	public MenuBar(){
		super();
		fileMenu = new JMenu("File");
	
		
		exit = new JMenuItem("Exit");
		fileExportGraph = new JMenuItem("Export Graph");
		fileImportGraph = new JMenuItem("Import Graph");		
		fileMenu.add(fileExportGraph);
		fileMenu.add(fileImportGraph);
		fileMenu.add(exit);
		
		
		editorMenu = new JMenu("Editor");
		addVertex = new JMenuItem("Add Vertex");
		editorMenu.add(addVertex);
		
		help = new JMenu("?");
		helpitem = new JMenuItem("Help me !");
		about = new JMenuItem("About");
		help.add(helpitem);
		help.add(about);
		
		add(fileMenu);
		add(editorMenu);
		add(help);
		
		fileExportGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Application.saveGraphInFile(GraphVisualizer.getGraph());
			}
		});
	
	}
	
	/**
	 * Gets the export item.
	 *
	 * @return the export item
	 */
	public JMenuItem getExportItem(){
		return fileExportGraph;
	}
	
	public JMenuItem getImportItem(){
		return fileImportGraph;
	}
	
	
	public JMenuItem getAddVertex() {
		return this.addVertex;
	}
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public JMenuItem[] getItems(){
		return new JMenuItem[]{fileExportGraph, fileImportGraph, addVertex, about, helpitem, exit};
		
	}
}
