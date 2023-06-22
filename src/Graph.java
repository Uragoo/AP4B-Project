import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.lang.Math;

public class Graph {
	private Map<Integer, Node> nodes;
	private Map<Node, List<Node>> adjacents;
	public Node startNode;
	public Node endNode;
	private int x;
	private int y;
	
	//Constructors
	public Graph() {
		nodes = new HashMap<>();
		adjacents = new HashMap<>();
	}
	
	//Getters and setters
	public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public List<Node> getNodes() {
		return new ArrayList<>(nodes.values());
	}
	
	public Node getNode(int id) {
		return nodes.get(id);
	}

	public List<Node> getVoisins(Node node) {
		List<Node> voisins = adjacents.get(node);
		if (voisins == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(voisins);
	}
	
	/**
	 * Moves the graph following the dragged mouse
	 *  
	 *  @param dx and dy, the coordinates where the graph should be moved
	 *  
	 */
	public void moveGraph(int dx, int dy) {
    	x = dx;
    	y = dy;
    	
    	for (Node node : nodes.values()) {
    		node.move(dx, dy);
    	}
    }
	
	/**
	 * Adds a new node
	 *  
	 *  @param x and y, the coordinates of the node
	 *  @param id, the id of the node
	 *  
	 */
	public void addNode(int id, int x, int y) {
		Node node = new Node(id, x, y);
		nodes.put(id,  node);
		adjacents.put(node,  new ArrayList<>());
	}
	
	/**
	 * Removes a node
	 *  
	 *  @param n, the node to remove
	 *  
	 */
	public void removeNode(Node n) {
		for (int i = 1; i < nodes.size(); i++) {
			List<Node> voisins = adjacents.get(getNode(i));
			if (voisins != null) {
				voisins.remove(n);
			}
		}
		nodes.remove(n.getId());
	}
	
	/**
	 * Adds an edge between 2 nodes
	 *  
	 *  @param sourceId and targetId, respectively the source node and the target node from where the edge will be created
	 *  
	 */
	public void addEdge(int sourceId, int targetId) {
		Node source = nodes.get(sourceId);
		Node target = nodes.get(targetId);
		
		if (source != null && target != null) {
			List<Node> voisins = adjacents.get(source);
			voisins.add(target);
		}
	}
	
	/**
	 * Removes an edge between 2 nodes
	 *  
	 *  @param node1 and node2, the nodes linked by the edge to remove
	 *  
	 */
	public void removeEdge(Node node1, Node node2) {
		List<Node> voisins1 = adjacents.get(node1);
		List<Node> voisins2 = adjacents.get(node2);
		voisins1.remove(node2);
		voisins2.remove(node1);
	}
	
	/**
	 * Gets the weight needed in the shortest path algorithm : basically the distance between the 2 nodes
	 *  
	 *  @param source and target, the 2 nodes we want to get the distance from
	 *  
	 *  @return the distance between the 2 nodes
	 *  
	 */
	public double getWeight(Node source, Node target) {
		int x1 = source.getX();
		int y1 = source.getY();
		int x2 = target.getX();
		int y2 = target.getY();
		
		return Math.sqrt((x2 - x1)^2 + (y2 - y1)^2);
	}
	
	/**
	 * The recursive part of the shortest path algorithm which will return the final shortest path
	 *  
	 *  @param start and end, respectively the starting and ending node from the path
	 *  @param predecessors, the map containing the nodes composing the shortest path ordered form the end to the start
	 *  
	 *  @return the final shortest path in order from the start to the end
	 *  
	 */
	private List<Node> shortestPathRecursive(Node start, Node end, Map<Node, Node> predecessors) {
		if (start == end) {
			List<Node> path = new ArrayList<>();
			path.add(start);
			return path;
		}
		
		List<Node> path = shortestPathRecursive(start, predecessors.get(end), predecessors);
		path.add(end);
		return path;
	}
	
	/**
	 * Shortest path algorithm using the Dijkstra algorithm
	 *  
	 *  @param startNode and endNode, respectively the starting and ending node from the path
	 *  
	 *  @return the shortest path between the starting and ending node
	 *  
	 */
	public List<Node> shortestPath(Node startNode, Node endNode) {
		Map<Node, Integer> distances = new HashMap<>();
		Map<Node, Node> predecessors = new HashMap<>();
		PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		for (Node node : nodes.values()) {
			distances.put(node, Integer.MAX_VALUE);
		}
		distances.put(startNode, 0);
		queue.add(startNode);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
								
			for (Node voisin : getVoisins(current)) {
				int distance = distances.get(current) + (int) getWeight(current, voisin);
				
				if (distance < distances.get(voisin)) {
					
					distances.put(voisin, distance);
					predecessors.put(voisin, current);
					queue.add(voisin);
				}
			}
		}
			
		List<Node> path = shortestPathRecursive(startNode, endNode, predecessors);
		
		return path;
	}
}
