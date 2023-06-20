import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void addNode(int id, int x, int y) {
		Node node = new Node(id, x, y);
		nodes.put(id,  node);
		adjacents.put(node,  new ArrayList<>());
	}
	
	public void removeNode(Node n) {
		for (int i = 1; i < nodes.size(); i++) {
			List<Node> voisins = adjacents.get(getNode(i));
			voisins.remove(n);
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
}
