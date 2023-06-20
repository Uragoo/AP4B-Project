import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements KeyListener {
	private GraphVisualizer graphVisualizer;
	private MenuBar menuBar;
	
	public MainWindow(GraphVisualizer graphVisualizer) {
		this.graphVisualizer = graphVisualizer;
		
		//Récuperation de l'apparence du système d'exploitation pour l'exportation de fichier notamment
		String nativeLF = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(nativeLF);
		} 
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
		
		setTitle("Graph Editor");
		setSize(1760, 990);
		setResizable(true);
		
		getContentPane().add(graphVisualizer);
		
		menuBar = new MenuBar();
		menuBar.getAddVertex().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				graphVisualizer.addVertex();
			}
		});
		/*menuBar.getImportItem().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				graphVisualizer = new GraphVisualizer(Application.getGraphFromFile());
				repaint();
			}
			
		});*/
		
		setJMenuBar(menuBar);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		addKeyListener(this);
		
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
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
		case '':
			System.out.print("ALLO ??");
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
