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
	
	public Graph() {
		nodes = new HashMap<>();
		adjacents = new HashMap<>();
	}
	
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
	
	public void moveGraph(int dx, int dy) {
    	x += dx;
    	y = dy;
    	
    	for (Node node : nodes.values()) {
    		node.move(dx, dy);
    	}
    }
	
	public void addNode(int id, int x, int y) {
		Node node = new Node(id, x, y);
		nodes.put(id,  node);
		adjacents.put(node,  new ArrayList<>());
	}
	
	public void removeNode(Node n) {
		for (int i = 1; i < nodes.size(); i++) {
			List<Node> voisins = adjacents.get(getNode(i));
			if (voisins != null) {
				voisins.remove(n);
			}
		}
		nodes.remove(n.getId());
	}
	
	public void addEdge(int sourceId, int targetId) {
		Node source = nodes.get(sourceId);
		Node target = nodes.get(targetId);
		
		if (source != null && target != null) {
			List<Node> voisins = adjacents.get(source);
			voisins.add(target);
		}
	}
	
	public void removeEdge(Node node1, Node node2) {
		List<Node> voisins1 = adjacents.get(node1);
		List<Node> voisins2 = adjacents.get(node2);
		voisins1.remove(node2);
		voisins2.remove(node1);
	}
	
	public double getWeight(Node source, Node target) {
		int x1 = source.getX();
		int y1 = source.getY();
		int x2 = target.getX();
		int y2 = target.getY();
		
		return Math.sqrt((x2 - x1)^2 + (y2 - y1)^2);
	}
	
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
	
	//Algorithme de plus court chemin utilisant l'algorithme de Dijkstra
	public List<Node> shortestPath(Node startNode, Node endNode) {
		Map<Node, Integer> distances = new HashMap<>();
		Map<Node, Node> predecessors = new HashMap<>();
		PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		for (Node node : nodes.values()) {
			distances.put(node, Integer.MAX_VALUE);
		}
		distances.put(startNode, 0);
		queue.add(startNode);
		//predecessors.put(null, startNode);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
					
			//Node lastVoisin = null;
			
			for (Node voisin : getVoisins(current)) {
				int distance = distances.get(current) + (int) getWeight(current, voisin);
				
				if (distance < distances.get(voisin)) {
					
					distances.put(voisin, distance);
					/*if (lastVoisin != null) {
						System.out.print("" + current.getId() + "  " + voisin.getId() + "  "+ lastVoisin.getId() + "\n");
						predecessors.remove(lastVoisin);
					}*/
					predecessors.put(voisin, current);
					//queue.remove(voisin);
					queue.add(voisin);
					//lastVoisin = voisin;
				}
			}
		}
		
		/*List<Node> path = new ArrayList<>();
		Node current = endNode;
		while (current != null) {
			path.add(0, current);
			System.out.print("" + current.getId() + "\n");
			current = predecessors.get(current);
		}
		//path.add(startNode);*/
		
		List<Node> path = shortestPathRecursive(startNode, endNode, predecessors);
		
		return path;
	}
}
