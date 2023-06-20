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
	public MouseListener activeListener;
	
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
		
		setVisible(true);
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
	
	public void setStartNode() {
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						graph.startNode = node;
						repaint();
						removeMouseListener(this);
					}
				}
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	public void setEndNode() {
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						graph.endNode = node;
						repaint();
						removeMouseListener(this);
					}
				}
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	private boolean isInsideNode(Node node, int x, int y) {
		int nodeRadius = node.RADIUS;
		int nodeX = node.getX();
		int nodeY = node.getY();
		
		
		return x <= nodeX + nodeRadius && x >= nodeX - nodeRadius && y <= nodeY + nodeRadius && y >= nodeY - nodeRadius;
	}
	
	public void addVertex() {
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					
					
					int x = e.getX();
					int y = e.getY();
					int nodeId = getNextId();
					graph.addNode(nodeId, x, y);
					Node node = graph.getNode(nodeId);
					
					repaint(node.getX() - node.RADIUS, node.getY() - node.RADIUS, node.RADIUS * 2, node.RADIUS * 2);
					removeMouseListener(this);
				}
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	public void selectVertex() {
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						removeMouseListener(this);
					}
				}
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	public void removeVertex() {
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						graph.removeNode(node);
						repaint();
						break;
					}
				}
				removeMouseListener(this);
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	public void addEdge() {
		MouseListener ml = new MouseAdapter() {
			Node node1;
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						if (node1 == null) {
							node1 = node;
						} else if (node != node1) {
							List<Node> voisins1 = graph.getVoisins(node1);
							List<Node> voisins2 = graph.getVoisins(node);
							boolean exists = false;
							for (Node voisin : voisins1) {
								if (voisin == node) {
									exists = true;
								}
							}
							for (Node voisin : voisins2) {
								if (voisin == node) {
									exists = true;
								}
							}
							if (!exists) {
								graph.addEdge(node1.getId(), node.getId());
								repaint();
								removeMouseListener(this);
							}
						}
					}
				}
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	public void removeEdge() {
		MouseListener ml = new MouseAdapter() {
			Node node1;
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				
				for (Node node : graph.getNodes()) {
					if (isInsideNode(node, x, y)) {
						if (node1 == null) {
							node1 = node;
						} else if (node != node1) {
							graph.removeEdge(node1, node);
							repaint();
							removeMouseListener(this);
						}
					}
				}
			}
		};
		removeMouseListener(activeListener);
		activeListener = ml;
		addMouseListener(ml);
	}
	
	
	
	private void handleNodeSelection(Node node) {
		//System.out.print("Node clicked\n");
	}
	
	private void handleNodeHover(Node node) {
		int nodeId = node.getId();
		
		Graphics g = this.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.drawString(Integer.toString(nodeId), node.getX()-(node.RADIUS/5), node.getY()+(node.RADIUS/5));
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
			int x = node.getX();
			int y = node.getY();
			
			if (node == graph.startNode) {
				g2d.setColor(Color.GREEN);
			} else if (node == graph.endNode) {
				g2d.setColor(Color.RED);
			} else {
				g2d.setColor(Color.BLUE);
			}
			g2d.fillOval(x - node.RADIUS, y - node.RADIUS, node.RADIUS * 2, node.RADIUS * 2);			
		}
		
		
	}
}
