import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private GraphVisualizer graphVisualizer;
	private MenuBar menuBar;
	private PanelSettings panelS;
	private PanelInfo panelI;
	
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
		setJMenuBar(menuBar);
		
		int h = 200;
		int l = 1760;
		panelS = new PanelSettings();
		panelS.setPreferredSize(new Dimension(l, h));
		getContentPane().add(panelS, BorderLayout.SOUTH);
		
		h = 990;
		l = 300;
		panelI = new PanelInfo();
		panelI.setPreferredSize(new Dimension(l, h));
		getContentPane().add(panelI, BorderLayout.EAST);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}
}
