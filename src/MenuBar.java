import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private JMenu fileMenu, editorMenu, help;
	
	/** The exit. */
	private JMenuItem fileExportItinerary, fileChangeMap, about, helpitem, exit, addVertex; 
		
	/**
	 * Instantiates a new menu bar.
	 */
	public MenuBar(){
		super();
		fileMenu = new JMenu("File");
	
		
		exit = new JMenuItem("Exit");
		fileExportItinerary = new JMenuItem("Export Itinerary");
		fileChangeMap = new JMenuItem("Change Map");		
		fileMenu.add(fileExportItinerary);
		fileMenu.add(fileChangeMap);
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
		
		fileExportItinerary.addActionListener(new ActionListener() {
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
		return fileExportItinerary;
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
		return new JMenuItem[]{fileExportItinerary, fileChangeMap, addVertex, about, helpitem, exit};
		
	}
}
