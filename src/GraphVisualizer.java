import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class GraphVisualizer extends JPanel {
	private static Graph graph;
	private double scaleFactor = 1.0;
	private int initialMouseX;
	private int initialMouseY;
	private boolean isDragging = false;
	
	@SuppressWarnings("static-access")
	public GraphVisualizer(Graph graph) {
		this.graph = graph;
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialMouseX = e.getX();
				initialMouseY = e.getY();
				isDragging = true;
			}
			
			public void mouseReleased(MouseEvent e) {
				isDragging = false;
			}
			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDragging) {
					int dx = e.getX() - initialMouseX;
					int dy = e.getY() - initialMouseY;
					graph.moveGraph(dx, dy);
					
					initialMouseX = e.getX();
					initialMouseY = e.getY();
					
					repaint();
				}
			}
			
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
		        int y = e.getY();
		        
		        for (Node node : graph.getNodes()) {
		        	if (isInsideNode(node, x, y)) {
		        		handleNodeHover(node);
		        		break;
		        	}
		        }
			}
		});
		
		addMouseWheelListener(new MouseAdapter() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    scaleFactor *= 1.1;
                } else {
                    scaleFactor /= 1.1;
                }
                repaint();
            }
        });
		
		centerWindowOnScreen();
		setVisible(true);
	}
	
	private void centerWindowOnScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = getWidth();
		int windowHeight = getHeight();
		int windowX = (screenWidth - windowWidth) / 2;
		int windowY = (screenHeight - windowHeight) / 2;
		setLocation(windowX, windowY);
	}
	
	public static Graph getGraph() {
		return graph;
	}
	
	public int getNextId() {
		int maxId = 0;
		for (Node node : graph.getNodes()) {
			int nodeId = node.getId();
			if (nodeId > maxId) { maxId = nodeId; }
		}
		return maxId + 1;
	}
	
	public void addVertex() {
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				System.out.print(" current x = " + x + " current y = " + y + "\n");
				graph.addNode(getNextId(), x, y);
				Node node = graph.getNode(getNextId() - 1);
				System.out.print("Node x : " + node.getX() + " y : " + node.getY());
				int nodeRadius = 20;
				
				repaint(node.getX() - node.RADIUS, node.getY() - node.RADIUS, node.RADIUS * 2, node.RADIUS * 2);
				removeMouseListener(this);
			}
		});
	}
	
	public void selectVertex() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						handleNodeSelection(node);
						break;
					}
				}
			}
		});
	}
	
	private boolean isInsideNode(Node node, int x, int y) {
		int nodeRadius = node.RADIUS;
		int nodeX = node.getX();
		int nodeY = node.getY();
		
		
		return x <= nodeX + nodeRadius && x >= nodeX - nodeRadius && y <= nodeY + nodeRadius && y >= nodeY - nodeRadius;
	}
	
	private void handleNodeSelection(Node node) {
		System.out.print("Node clicked\n");
	}
	
	private void handleNodeHover(Node node) {
		int nodeId = node.getId();
		
		Graphics g = this.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.drawString(Integer.toString(nodeId), node.getX()-(node.RADIUS/5), node.getY()+(node.RADIUS/5));
		System.out.print("Node hovered\n");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(scaleFactor, scaleFactor);
		
		List<Node> nodes = graph.getNodes();
		int nodeRadius = 20;
		
		//On dessine les  arrêtes
		List<Node> allNodes = graph.getNodes();
		for (Node node : allNodes) {
			List<Node> voisins = graph.getVoisins(node);
			int x1 = node.getX();
			int y1 = node.getY();
			
			for (Node voisin : voisins) {
				int x2 = voisin.getX();
				int y2 = voisin.getY();
				
				g2d.setColor(Color.BLACK);
				g2d.drawLine(x1, y1, x2, y2);
			}
		}
		
		//On dessine les sommets
		for (Node node : nodes) {
			int nodeId = node.getId();
			int x = node.getX();
			int y = node.getY();
			
			g2d.setColor(Color.BLUE);
			g2d.fillOval(x - node.RADIUS, y - node.RADIUS, node.RADIUS * 2, node.RADIUS * 2);			
		}
		
		
	}
}
